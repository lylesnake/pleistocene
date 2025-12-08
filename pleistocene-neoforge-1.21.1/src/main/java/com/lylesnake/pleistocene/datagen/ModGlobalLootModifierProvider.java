package com.lylesnake.pleistocene.datagen;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.item.ModItems;
import com.lylesnake.pleistocene.loot.AddItemModifier;
import com.lylesnake.pleistocene.loot.ReplaceLootModifier;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Pleistocene.MODID);
    }

    @Override
    protected void start() {
        addToVanillaDrops();
        replaceStoneDrops();
        replaceAnimalDrops();
    }

    private void addToVanillaDrops() {
        this.add("ash_to_campfire",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.CAMPFIRE).build(),
                }, ModItems.ASH.get(), 0.33f, 1, 3, false));
        this.add("fox_fur_to_fox",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/fox")).build(),
                        LootItemKilledByPlayerCondition.killedByPlayer().build()
                }, ModItems.FOX_FUR.get(), 0.5f, 1, 2, true));
        this.add("polar_bear_fur_to_polar_bear",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/polar_bear")).build(),
                        LootItemKilledByPlayerCondition.killedByPlayer().build()
                }, ModItems.POLAR_BEAR_FUR.get(), 0.5f, 2, 4, true));
        this.add("wolf_fur_to_wolf",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/wolf")).build(),
                        LootItemKilledByPlayerCondition.killedByPlayer().build()
                }, ModItems.WOLF_FUR.get(), 0.33f, 1, 3, true));

        this.add("rawhide_to_pig",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/pig")).build(),
                        LootItemKilledByPlayerCondition.killedByPlayer().build()
                }, ModItems.RAWHIDE.get(), 0.33f, 1, 3, true), new ICondition[0]);
    }

    private void replaceStoneDrops()
    {
        // Stone(s)
        add("rocks_to_stone", new ReplaceLootModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.STONE).build(),
        }, Blocks.COBBLESTONE.asItem(), ModItems.ROCK.get(), 2, 4, false));

        add("rocks_to_andesite", new ReplaceLootModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.ANDESITE).build(),
        }, Blocks.ANDESITE.asItem(), ModItems.ROCK.get(), 2, 4, false));

        add("rocks_to_diorite", new ReplaceLootModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIORITE).build(),
        }, Blocks.DIORITE.asItem(), ModItems.ROCK.get(), 2, 4, false));

        add("rocks_to_granite", new ReplaceLootModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRANITE).build(),
        }, Blocks.GRANITE.asItem(), ModItems.ROCK.get(), 2, 4, false));
    }

    private void replaceAnimalDrops() {
        LootItemCondition cow = LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                new EntityPredicate.Builder().of(EntityType.COW).build()).build();
                add("rawhide_to_cow", new ReplaceLootModifier(new LootItemCondition[] {
                 cow,
                 }, Items.LEATHER, ModItems.RAWHIDE.get(), 1, 2, true));

        LootItemCondition mooshroom = LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                new EntityPredicate.Builder().of(EntityType.MOOSHROOM).build()).build();
                add("rawhide_to_mooshroom", new ReplaceLootModifier(new LootItemCondition[] {
                mooshroom,
                }, Items.LEATHER, ModItems.RAWHIDE.get(), 1, 2, true));

        LootItemCondition horse = LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                new EntityPredicate.Builder().of(EntityType.HORSE).build()).build();
                add("rawhide_to_horse", new ReplaceLootModifier(new LootItemCondition[] {
                horse,
                }, Items.LEATHER, ModItems.RAWHIDE.get(), 1, 2, true));

        LootItemCondition llama = LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                new EntityPredicate.Builder().of(EntityType.LLAMA).build()).build();
                add("rawhide_to_llama", new ReplaceLootModifier(new LootItemCondition[] {
                llama,
                }, Items.LEATHER, ModItems.RAWHIDE.get(), 1, 2, true));

        LootItemCondition mule = LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                new EntityPredicate.Builder().of(EntityType.MULE).build()).build();
                add("rawhide_to_mule", new ReplaceLootModifier(new LootItemCondition[] {
                mule,
                }, Items.LEATHER, ModItems.RAWHIDE.get(), 1, 2, true));

        LootItemCondition donkey = LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                new EntityPredicate.Builder().of(EntityType.DONKEY).build()).build();
                add("rawhide_to_donkey", new ReplaceLootModifier(new LootItemCondition[] {
                donkey,
                }, Items.LEATHER, ModItems.RAWHIDE.get(), 1, 2, true));

        LootItemCondition traderLlama = LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                new EntityPredicate.Builder().of(EntityType.TRADER_LLAMA).build()).build();
                add("rawhide_to_trader_llama", new ReplaceLootModifier(new LootItemCondition[] {
                traderLlama,
                }, Items.LEATHER, ModItems.RAWHIDE.get(), 1, 2, true));

    }
}
