package com.lylesnake.pleistocene.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class ReplaceLootModifier extends LootModifier
{
	public static final MapCodec<ReplaceLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst
            -> LootModifier.codecStart(inst).and(inst.group(BuiltInRegistries.ITEM.byNameCodec().fieldOf("olditem")
            .forGetter(e -> e.oldItem), BuiltInRegistries.ITEM.byNameCodec().fieldOf("newitem")
            .forGetter(e -> e.newItem), Codec.INT.fieldOf("minamount")
            .forGetter(e -> e.minAmount), Codec.INT.fieldOf("maxamount")
            .forGetter(e -> e.maxAmount), Codec.BOOL.fieldOf("ignoresilktouch")
            .forGetter(e -> e.ignoreSilkTouch))).apply(inst, ReplaceLootModifier::new));
	
	private final Item oldItem;
	private final Item newItem;
	
	private final int minAmount;
	private final int maxAmount;
	
	private final boolean ignoreSilkTouch;
	
	public ReplaceLootModifier(LootItemCondition[] conditions, Item oldItem, Item newItem, int minAmount, int maxAmount,
                               boolean ignoreSilkTouch) {
		super(conditions);
		
		this.oldItem = oldItem;
		this.newItem = newItem;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.ignoreSilkTouch = ignoreSilkTouch;
	}
	
	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if(!ignoreSilkTouch) {
			ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
			if(tool != null) {
				Holder<Enchantment> silkTouch = context.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT)
                        .getOrThrow(Enchantments.SILK_TOUCH);
				if(tool.getEnchantmentLevel(silkTouch) >= 1)
					return generatedLoot;
			}
		}
		
		if(generatedLoot.removeIf((itemStack) -> itemStack.getItem() == this.oldItem))
			generatedLoot.add(new ItemStack(this.newItem, this.minAmount + context.getRandom().nextInt(this.maxAmount)));
		
		return generatedLoot;
	}
	
	@Override
	public MapCodec<? extends IGlobalLootModifier> codec()
	{
		return CODEC;
	}
}
