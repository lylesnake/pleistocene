package com.lylesnake.pleistocene.datagen;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.block.ModBlocks;
import com.lylesnake.pleistocene.entity.ModEntities;
import com.lylesnake.pleistocene.item.ModItems;
import com.lylesnake.pleistocene.util.ModTextUtils;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, existingFileHelper, List.of(new MyAdvancementGenerator()));
    }

    private static final class MyAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            AdvancementHolder pleistocene = Advancement.Builder.advancement()
                    .display(Blocks.ICE,
                            ModTextUtils.getTranslation("advancement.root"),
                            ModTextUtils.getTranslation("advancement.root.desc"),
                            ResourceLocation.parse("minecraft:textures/block/mud_bricks.png"),
                            AdvancementType.TASK, false, false, false)
                    .addCriterion("spawn", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{}))
                    .save(saver, getNameId("main/root"));

            // Wood crafting branch
            AdvancementHolder oneSharpRock = getAdvancement(pleistocene, ModItems.HAND_AXE.get(), "craft_hand_axe", AdvancementType.TASK, true, true, false)
                    .addCriterion("hand_axe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HAND_AXE.get()))
                    .save(saver, getNameId("main/craft_hand_axe"));

            AdvancementHolder gotWood = getAdvancement(oneSharpRock, Blocks.OAK_LOG, "acquire_logs", AdvancementType.TASK, true, true, false)
                    .addCriterion("a_logs", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_LOG))
                    .addCriterion("b_logs", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BIRCH_LOG))
                    .addCriterion("ch_logs", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CHERRY_LOG))
                    .addCriterion("do_logs", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_LOG))
                    .addCriterion("j_logs", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUNGLE_LOG))
                    .addCriterion("m_logs", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MANGROVE_LOG))
                    .addCriterion("o_logs", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_LOG))
                    .addCriterion("s_logs", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPRUCE_LOG))
                    .addCriterion("cr_logs", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRIMSON_STEM))
                    .addCriterion("w_logs", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WARPED_STEM))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, getNameId("main/acquire_logs"));

            AdvancementHolder chopChop = getAdvancement(gotWood, ModBlocks.CHOPPING_BLOCK.get(), "chop_chop", AdvancementType.TASK, true, true, false)
                    .addCriterion("chopping_block", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.CHOPPING_BLOCK.get()))
                    .save(saver, getNameId("main/craft_chopping_block"));

            AdvancementHolder walkThePlank = getAdvancement(chopChop, Blocks.OAK_PLANKS, "chop_chop", AdvancementType.TASK, true, true, false)
                    .addCriterion("a_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.ACACIA_PLANKS))
                    .addCriterion("b_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.BIRCH_PLANKS))
                    .addCriterion("ch_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CHERRY_PLANKS))
                    .addCriterion("cr_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CRIMSON_PLANKS))
                    .addCriterion("co_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.DARK_OAK_PLANKS))
                    .addCriterion("j_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.JUNGLE_PLANKS))
                    .addCriterion("m_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.MANGROVE_PLANKS))
                    .addCriterion("o_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.OAK_PLANKS))
                    .addCriterion("s_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.SPRUCE_PLANKS))
                    .addCriterion("w_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.WARPED_PLANKS))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, getNameId("main/get_planks"));

            AdvancementHolder craftingTime = getAdvancement(walkThePlank, Blocks.CRAFTING_TABLE, "crafting_time", AdvancementType.GOAL, true, true, false)
                    .addCriterion("crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CRAFTING_TABLE))
                    .save(saver, getNameId("main/craft_crafting_table"));

            // Campfire branch
            AdvancementHolder hearth = getAdvancement(gotWood, ModItems.CAMPFIRE_UNLIT, "hearth", AdvancementType.TASK, true, true, false)
                    .addCriterion("campfire", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CAMPFIRE))
                    .save(saver, getNameId("main/craft_campfire"));

            AdvancementHolder twoSticks = getAdvancement(hearth, ModItems.FIRE_STICKS.get(), "two_sticks", AdvancementType.TASK, true, true, false)
                    .addCriterion("firesticks", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FIRE_STICKS.get()))
                    .save(saver, getNameId("main/craft_firesticks"));

            AdvancementHolder weLitTheFire = getAdvancement(twoSticks, Blocks.CAMPFIRE, "we_lit_the_fire", AdvancementType.TASK, true, true, false)
                    .addCriterion("use_firesticks", ItemDurabilityTrigger.TriggerInstance.changedDurability
                           (Optional.of(ItemPredicate.Builder.item().of(ModItems.FIRE_STICKS.get()).build()), MinMaxBounds.Ints.ANY))
                    .save(saver, getNameId("main/light_campfire"));

            // Tools branch
            AdvancementHolder rockNRoll = getAdvancement(pleistocene, ModItems.ROCK.get(), "rock_n_roll", AdvancementType.TASK, true, true, false)
                    .addCriterion("rock", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ROCK.get()))
                    .save(saver, getNameId("main/acquire_rock"));

            AdvancementHolder primitiveTechnology = getAdvancement(rockNRoll, ModItems.KNAPPED_FLINT.get(), "primitive_technology", AdvancementType.TASK, true, true, false)
                    .addCriterion("knapped_flint", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.KNAPPED_FLINT.get()))
                    .save(saver, getNameId("main/knap_flint"));

            AdvancementHolder aSharperAxe = getAdvancement(primitiveTechnology, ModItems.FLINT_AXE.get(), "a_sharper_axe", AdvancementType.TASK, true, true, false)
                    .addCriterion("flint_axe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLINT_AXE.get()))
                    .save(saver, getNameId("main/craft_flint_axe"));

            AdvancementHolder wasteNotWantNot = getAdvancement(primitiveTechnology, ModItems.KNAPPED_BONE.get(), "waste_not_want_not", AdvancementType.TASK, true, true, false)
                    .addCriterion("knapped_bone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.KNAPPED_BONE.get()))
                    .save(saver, getNameId("main/knap_bone"));

            AdvancementHolder boneToPick = getAdvancement(wasteNotWantNot, ModItems.BONE_PICKAXE.get(), "bone_to_pick", AdvancementType.TASK, true, true, false)
                    .addCriterion("bone_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BONE_PICKAXE.get()))
                    .save(saver, getNameId("main/bone_pickaxe"));

            AdvancementHolder pleasingTheGods = getAdvancement(wasteNotWantNot, ModItems.IMBUED_BONE.get(), "pleasing_the_gods", AdvancementType.TASK, true, true, false)
                    .addCriterion("imbued_bone", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IMBUED_BONE.get()))
                    .save(saver, getNameId("main/craft_imbued_bone"));

            AdvancementHolder forgedInLava = getAdvancement(pleasingTheGods, ModItems.OBSIDIAN_SHARD.get(), "forged_in_lava", AdvancementType.TASK, true, true, false)
                    .addCriterion("obsidian_shard", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_SHARD.get()))
                    .save(saver, getNameId("main/smelt_obsidian"));

            // Armors branch
            AdvancementHolder tarzan = getAdvancement(pleistocene, ModItems.HIDE_LOINCLOTH.get(), "tarzan", AdvancementType.TASK, true, true, false)
                    .addCriterion("hide_loincloth", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HIDE_LOINCLOTH.get()))
                    .save(saver, getNameId("main/craft_hide_loincloth"));

            AdvancementHolder beenFeelingDry = getAdvancement(pleistocene, ModBlocks.DRYING_RACK.get(), "been_feeling_dry", AdvancementType.TASK, true, true, false)
                    .addCriterion("drying_rack", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.DRYING_RACK.get()))
                    .save(saver, getNameId("main/craft_drying_rack"));

            AdvancementHolder notVegan = getAdvancement(beenFeelingDry, Items.LEATHER, "not_vegan", AdvancementType.TASK, true, true, false)
                    .addCriterion("leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
                    .save(saver, getNameId("main/obtain_leather"));

            AdvancementHolder iceAgeFashionista = getAdvancement(notVegan, Items.LEATHER_BOOTS, "ice_age_fashionista", AdvancementType.TASK, true, true, false)
                    .addCriterion("leather_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_HELMET))
                    .addCriterion("leather_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_CHESTPLATE))
                    .addCriterion("leather_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_LEGGINGS))
                    .addCriterion("leather_boots", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER_BOOTS))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, getNameId("main/obtain_leather_armor"));

            AdvancementHolder niceFur = getAdvancement(pleistocene, ModItems.WOLF_FUR, "nice_fur", AdvancementType.TASK, true, true, false)
                    .addCriterion("f_fur", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FOX_FUR))
                    .addCriterion("m_fur", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MAMMOTH_FUR))
                    .addCriterion("p_fur", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POLAR_BEAR_FUR))
                    .addCriterion("w_fur", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.WOLF_FUR))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, getNameId("main/obtain_pelt"));

            AdvancementHolder ancientChemist = getAdvancement(niceFur, ModItems.WEAK_SOLUTION, "ancient_chemist", AdvancementType.TASK, true, true, false)
                    .addCriterion("weak_solution", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.WEAK_SOLUTION))
                    .save(saver, getNameId("main/obtain_weak_solution"));

            AdvancementHolder soFluffy = getAdvancement(ancientChemist, ModItems.WEAK_SOLUTION, "so_fluffy", AdvancementType.TASK, true, true, false)
                    .addCriterion("fur", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PROCESSED_FUR))
                    .save(saver, getNameId("main/process_fur"));

            AdvancementHolder bootsWithTheFur = getAdvancement(soFluffy, ModItems.FUR_BOOTS, "boots_with_the_fur", AdvancementType.TASK, true, true, false)
                    .addCriterion("fur_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FUR_BOOTS))
                    .save(saver, getNameId("main/obtain_fur_boots"));


            // Hidden achievements
            AdvancementHolder mammothHunter = getAdvancement(pleistocene, ModItems.FLINT_SWORD.get(), "mammoth_hunter", AdvancementType.CHALLENGE, true, true, true)
                    .addCriterion("kill_mammoth", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(ModEntities.MAMMOTH.get())))
                    .save(saver, getNameId("main/kill_mammoth"));

            AdvancementHolder fredFlintstone = getAdvancement(aSharperAxe, ModItems.FLINT_PICKAXE.get(), "fred_flintstone", AdvancementType.GOAL, true, true, true)
                    .addCriterion("flint_sword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLINT_SWORD.get()))
                    .addCriterion("flint_axe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLINT_AXE.get()))
                    .addCriterion("flint_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLINT_PICKAXE.get()))
                    .addCriterion("flint_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLINT_SHOVEL.get()))
                    .addCriterion("flint_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLINT_HOE.get()))
                    .addCriterion("flint_shears", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLINT_SHEARS.get()))
                    .save(saver, getNameId("main/has_all_flint_tools"));

            AdvancementHolder reaper = getAdvancement(boneToPick, ModItems.BONE_SWORD.get(), "reaper", AdvancementType.GOAL, true, true, true)
                    .addCriterion("bone_sword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BONE_SWORD.get()))
                    .addCriterion("bone_axe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BONE_AXE.get()))
                    .addCriterion("bone_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BONE_PICKAXE.get()))
                    .addCriterion("bone_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BONE_SHOVEL.get()))
                    .addCriterion("bone_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BONE_HOE.get()))
                    .save(saver, getNameId("main/has_all_bone_tools"));

            AdvancementHolder championOfTheGods = getAdvancement(pleasingTheGods, ModItems.IMBUED_PICKAXE.get(), "champion_of_the_gods", AdvancementType.GOAL, true, true, true)
                    .addCriterion("imbued_sword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IMBUED_SWORD.get()))
                    .addCriterion("imbued_axe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IMBUED_AXE.get()))
                    .addCriterion("imbued_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IMBUED_PICKAXE.get()))
                    .addCriterion("imbued_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IMBUED_SHOVEL.get()))
                    .addCriterion("imbued_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IMBUED_HOE.get()))
                    .save(saver, getNameId("main/has_all_imbued_tools"));

            AdvancementHolder oneWithNature = getAdvancement(tarzan, ModItems.HIDE_MASK, "one_with_nature", AdvancementType.GOAL, true, true, true)
                    .addCriterion("hide_mask", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HIDE_MASK))
                    .addCriterion("hide_chestpiece", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HIDE_CHESTPIECE))
                    .addCriterion("hide_sandals", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HIDE_SANDALS))
                    .save(saver, getNameId("main/has_all_rawhide_armor"));

            AdvancementHolder areYouAFurry = getAdvancement(bootsWithTheFur, ModItems.FUR_HOOD, "are_you_a_furry", AdvancementType.GOAL, true, true, true)
                    .addCriterion("fur_hood", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FUR_HOOD))
                    .addCriterion("fur_tunic", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FUR_TUNIC))
                    .addCriterion("fur_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FUR_LEGGINGS))
                    .save(saver, getNameId("main/has_all_fur_armor"));

            AdvancementHolder spookyScary = getAdvancement(pleasingTheGods, ModItems.IMBUED_HOOD, "spooky_scary", AdvancementType.GOAL, true, true, true)
                    .addCriterion("imbued_hood", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IMBUED_HOOD))
                    .addCriterion("imbued_poncho", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IMBUED_PONCHO))
                    .addCriterion("imbued_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IMBUED_LEGGINGS))
                    .addCriterion("imbued_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IMBUED_BOOTS))
                    .save(saver, getNameId("main/has_all_imbued_armor"));

            AdvancementHolder obsidianObsession = getAdvancement(forgedInLava, ModItems.OBSIDIAN_PICKAXE.get(), "obsidian_obsession", AdvancementType.CHALLENGE, true, true, true)
                    .addCriterion("obsidian_sword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_SWORD.get()))
                    .addCriterion("obsidian_axe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_AXE.get()))
                    .addCriterion("obsidian_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_PICKAXE.get()))
                    .addCriterion("obsidian_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_SHOVEL.get()))
                    .addCriterion("obsidian_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_HOE.get()))
                    .addCriterion("obsidian_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_HELMET.get()))
                    .addCriterion("obsidian_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_CHESTPLATE.get()))
                    .addCriterion("obsidian_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_LEGGINGS.get()))
                    .addCriterion("obsidian_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_BOOTS.get()))
                    .save(saver, getNameId("main/has_all_obsidian_tools_and_armor"));
        }
    }

    protected static Advancement.Builder getAdvancement(AdvancementHolder parent, ItemLike display, String name, AdvancementType frame,
                                                        boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(display,
                ModTextUtils.getTranslation("advancement." + name),
                ModTextUtils.getTranslation("advancement." + name + ".desc"),
                null, frame, showToast, announceToChat, hidden);
    }

    private static String getNameId(String id) {
        return Pleistocene.MODID + ":" + id;
    }

}
