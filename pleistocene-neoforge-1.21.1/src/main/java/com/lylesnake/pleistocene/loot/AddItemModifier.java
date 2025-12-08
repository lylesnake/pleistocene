package com.lylesnake.pleistocene.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class AddItemModifier extends LootModifier {
    public static final MapCodec<AddItemModifier> CODEC = RecordCodecBuilder.mapCodec((
            inst) -> LootModifier.codecStart(inst).and(inst.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter((e) -> e.item),
            Codec.FLOAT.fieldOf("chance").forGetter((e) -> e.chance),
            Codec.INT.fieldOf("minamount").forGetter((e) -> e.minAmount),
            Codec.INT.fieldOf("maxamount").forGetter((e) -> e.maxAmount),
            Codec.BOOL.fieldOf("ignoresilktouch").forGetter((e) -> e.ignoreSilkTouch))
    ).apply(inst, AddItemModifier::new));
    private final Item item;
    private final float chance;
    private final int minAmount;
    private final int maxAmount;
    private final boolean ignoreSilkTouch;


    public AddItemModifier(LootItemCondition[] conditionsIn, Item item, float chance, int minAmount, int maxAmount, boolean ignoreSilkTouch) {
        super(conditionsIn);
        this.item = item;
        this.chance = chance;
        this.minAmount = maxAmount;
        this.maxAmount = maxAmount;
        this.ignoreSilkTouch = ignoreSilkTouch;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
        if (!this.ignoreSilkTouch) {
            ItemStack tool = (ItemStack)lootContext.getParamOrNull(LootContextParams.TOOL);
            if (tool != null) {
                Holder<Enchantment> silkTouch = lootContext.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH);
                if (tool.getEnchantmentLevel(silkTouch) >= 1) {
                    return generatedLoot;
                }
            }
        }

        RandomSource random = lootContext.getRandom();
        if (random.nextFloat() < this.chance) {
            generatedLoot.add(new ItemStack(this.item, this.minAmount + random.nextInt(this.maxAmount)));
        }

        return generatedLoot;

    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
