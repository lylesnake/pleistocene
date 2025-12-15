package com.lylesnake.pleistocene.datagen;

import com.lylesnake.pleistocene.block.ModBlocks;
import com.lylesnake.pleistocene.item.ModItems;
import com.lylesnake.pleistocene.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.awt.event.ItemListener;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput){
        List<ItemLike> OBSIDIAN_SMELTABLES = List.of(Blocks.OBSIDIAN);

        // ------ BLOCKS ------

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FLINT_BLOCK.get())
                .pattern("FF")
                .pattern("FF")
                .define('F', Items.FLINT)
                .unlockedBy("has_flint", has(Items.FLINT))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.FLINT, 4)
                .requires(ModBlocks.FLINT_BLOCK.get())
                .unlockedBy("has_flint_block", has(ModBlocks.FLINT_BLOCK.get()))
                .save(recipeOutput, "pleistocene:flint_from_flint_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.COBBLESTONE, 1)
                .requires(ModTags.Items.ROCKS)
                .requires(ModTags.Items.ROCKS)
                .requires(ModTags.Items.ROCKS)
                .requires(ModTags.Items.ROCKS)
                .unlockedBy("has_rock", has(ModTags.Items.ROCKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DRYING_RACK.get())
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHOPPING_BLOCK.get())
                .pattern("L")
                .define('L', ItemTags.LOGS)
                .unlockedBy("has_log", has(ItemTags.LOGS))
                .save(recipeOutput);


        // ------ INGREDIENTS ------

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PROCESSED_FUR.get())
                .requires(ModTags.Items.PELTS)
                .requires(ModItems.WEAK_SOLUTION.get())
                .unlockedBy("has_weak_solution", has(ModItems.WEAK_SOLUTION.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.WEAK_SOLUTION.get())
                .requires(Items.WATER_BUCKET)
                .requires(ModItems.ASH.get())
                .unlockedBy("has_ash", has(ModItems.ASH.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TWINE.get())
                .pattern("G ")
                .pattern(" G")
                .unlockedBy("has_shears", has(ModItems.FLINT_SHEARS.get()))
                .define('G', Items.SHORT_GRASS)
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RAWHIDE.get())
                .pattern("RR")
                .pattern("RR")
                .unlockedBy("has_rabbit_hide", has(Items.RABBIT_HIDE))
                .define('R', Items.RABBIT_HIDE)
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.KNAPPED_FLINT.get(), 3)
                .requires(Items.FLINT)
                .requires(ModTags.Items.ROCKS)
                .unlockedBy("has_flint", has(Items.FLINT))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.KNAPPED_BONE.get(), 3)
                .requires(Items.BONE)
                .requires(ModTags.Items.ROCKS)
                .unlockedBy("has_bone", has(Items.BONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IMBUED_BONE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.AMETHYST_SHARD)
                .define('B', Items.BONE)
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AMETHYST_SILK.get())
                .pattern("ASA")
                .pattern("SAS")
                .pattern("ASA")
                .define('A', Items.AMETHYST_SHARD)
                .define('S', Items.STRING)
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);

        // smelt obsidian block to get obsidian shard
        oreSmelting(recipeOutput, OBSIDIAN_SMELTABLES,
                RecipeCategory.MISC,
                ModItems.OBSIDIAN_SHARD.get(),
                0.25f, 200,
                "obsidian");


        // ------ TOOLS ------

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FIRE_STICKS.get())
                .pattern(" S")
                .pattern("S ")
                .unlockedBy("has_stick", has(Items.STICK))
                .define('S', Items.STICK)
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.HAND_AXE.get())
                .pattern("F")
                .unlockedBy("has_flint", has(Items.FLINT))
                .define('F', Items.FLINT)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FLINT_SHEARS.get())
                .pattern(" F")
                .pattern("F ")
                .unlockedBy("has_flint", has(Items.FLINT))
                .define('F', Items.FLINT)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FLINT_AXE.get())
                .pattern("FF")
                .pattern("FS")
                .pattern(" S")
                .unlockedBy("has_knapped_flint", has(ModItems.KNAPPED_FLINT.get()))
                .define('F', ModItems.KNAPPED_FLINT.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FLINT_HOE.get())
                .pattern("FF")
                .pattern("S ")
                .pattern("S ")
                .unlockedBy("has_knapped_flint", has(ModItems.KNAPPED_FLINT.get()))
                .define('F', ModItems.KNAPPED_FLINT.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FLINT_PICKAXE.get())
                .pattern("FFF")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_knapped_flint", has(ModItems.KNAPPED_FLINT.get()))
                .define('F', ModItems.KNAPPED_FLINT.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FLINT_SHOVEL.get())
                .pattern("F")
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_knapped_flint", has(ModItems.KNAPPED_FLINT.get()))
                .define('F', ModItems.KNAPPED_FLINT.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FLINT_SWORD.get())
                .pattern("F")
                .pattern("F")
                .pattern("S")
                .unlockedBy("has_knapped_flint", has(ModItems.KNAPPED_FLINT.get()))
                .define('F', ModItems.KNAPPED_FLINT.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BONE_AXE.get())
                .pattern("BB")
                .pattern("BS")
                .pattern(" S")
                .unlockedBy("has_knapped_bone", has(ModItems.KNAPPED_BONE.get()))
                .define('B', ModItems.KNAPPED_BONE.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BONE_HOE.get())
                .pattern("BB")
                .pattern("S ")
                .pattern("S ")
                .unlockedBy("has_knapped_bone", has(ModItems.KNAPPED_BONE))
                .define('B', ModItems.KNAPPED_BONE.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BONE_PICKAXE.get())
                .pattern("BBB")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_knapped_bone", has(ModItems.KNAPPED_BONE.get()))
                .define('B', ModItems.KNAPPED_BONE.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BONE_SHOVEL.get())
                .pattern("B")
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_knapped_bone", has(ModItems.KNAPPED_BONE.get()))
                .define('B', ModItems.KNAPPED_BONE.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.BONE_SWORD.get())
                .pattern("B")
                .pattern("B")
                .pattern("S")
                .unlockedBy("has_knapped_bone", has(ModItems.KNAPPED_BONE))
                .define('B', ModItems.KNAPPED_BONE.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.IMBUED_AXE.get())
                .pattern("II")
                .pattern("IS")
                .pattern(" S")
                .unlockedBy("has_imbued_bone", has(ModItems.IMBUED_BONE.get()))
                .define('I', ModItems.IMBUED_BONE.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.IMBUED_HOE.get())
                .pattern("II")
                .pattern("S ")
                .pattern("S ")
                .unlockedBy("has_imbued_bone", has(ModItems.IMBUED_BONE.get()))
                .define('I', ModItems.IMBUED_BONE.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.IMBUED_SHOVEL.get())
                .pattern("I")
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_imbued_bone", has(ModItems.IMBUED_BONE.get()))
                .define('I', ModItems.IMBUED_BONE.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.IMBUED_PICKAXE.get())
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_imbued_bone", has(ModItems.IMBUED_BONE.get()))
                .define('I', ModItems.IMBUED_BONE.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IMBUED_SWORD.get())
                .pattern("I")
                .pattern("I")
                .pattern("S")
                .unlockedBy("has_imbued_bone", has(ModItems.IMBUED_BONE.get()))
                .define('I', ModItems.IMBUED_BONE.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.OBSIDIAN_AXE.get())
                .pattern("OO")
                .pattern("OS")
                .pattern(" S")
                .unlockedBy("has_obsidian_shard", has(ModItems.OBSIDIAN_SHARD.get()))
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.OBSIDIAN_HOE.get())
                .pattern("OO")
                .pattern("S ")
                .pattern("S ")
                .unlockedBy("has_obsidian_shard", has(ModItems.OBSIDIAN_SHARD))
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.OBSIDIAN_SHOVEL.get())
                .pattern("O")
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_obsidian_shard", has(ModItems.OBSIDIAN_SHARD.get()))
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.OBSIDIAN_PICKAXE.get())
                .pattern("OOO")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_obsidian_shard", has(ModItems.OBSIDIAN_SHARD.get()))
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('S', Items.STICK)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_SWORD.get())
                .pattern("O")
                .pattern("O")
                .pattern("S")
                .unlockedBy("has_obsidian_shard", has(ModItems.OBSIDIAN_SHARD.get()))
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('S', Items.STICK)
                .save(recipeOutput);

        // ------ ARMORS ------

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HIDE_MASK.get())
                .pattern("GHG")
                .pattern(" B ")
                .unlockedBy("has_hide", has(ModItems.RAWHIDE.get()))
                .define('G', ModItems.TWINE.get())
                .define('H', ModItems.RAWHIDE.get())
                .define('B', Items.BONE)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HIDE_CHESTPIECE.get())
                .pattern("  H")
                .pattern(" H ")
                .pattern("H  ")
                .unlockedBy("has_hide", has(ModItems.RAWHIDE.get()))
                .define('H', ModItems.RAWHIDE.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HIDE_LOINCLOTH.get())
                .pattern("HHH")
                .pattern(" H ")
                .unlockedBy("has_hide", has(ModItems.RAWHIDE.get()))
                .define('H', ModItems.RAWHIDE.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HIDE_SANDALS.get())
                .pattern("G G")
                .pattern("H H")
                .unlockedBy("has_hide", has(ModItems.RAWHIDE.get()))
                .define('G', ModItems.TWINE.get())
                .define('H', ModItems.RAWHIDE.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FUR_HOOD.get())
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy("has_processed_fur", has(ModItems.PROCESSED_FUR.get()))
                .define('X', ModItems.PROCESSED_FUR.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FUR_TUNIC.get())
                .pattern("X X")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy("has_processed_fur", has(ModItems.PROCESSED_FUR.get()))
                .define('X', ModItems.PROCESSED_FUR.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FUR_LEGGINGS.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_processed_fur", has(ModItems.PROCESSED_FUR.get()))
                .define('X', ModItems.PROCESSED_FUR.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FUR_BOOTS.get())
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_processed_fur", has(ModItems.PROCESSED_FUR.get()))
                .define('X', ModItems.PROCESSED_FUR.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IMBUED_HOOD.get())
                .pattern("SSS")
                .pattern("SBS")
                .unlockedBy("has_bone", has(ModItems.IMBUED_BONE.get()))
                .define('S', ModItems.AMETHYST_SILK.get())
                .define('B', ModItems.IMBUED_BONE.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IMBUED_PONCHO.get())
                .pattern("SSS")
                .pattern("BBB")
                .pattern("S S")
                .unlockedBy("has_bone", has(ModItems.IMBUED_BONE.get()))
                .define('S', ModItems.AMETHYST_SILK.get())
                .define('B', ModItems.IMBUED_BONE.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IMBUED_LEGGINGS.get())
                .pattern("BSB")
                .pattern("B B")
                .pattern("S S")
                .unlockedBy("has_bone", has(ModItems.IMBUED_BONE.get()))
                .define('S', ModItems.AMETHYST_SILK.get())
                .define('B', ModItems.IMBUED_BONE.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.IMBUED_BOOTS.get())
                .pattern("S S")
                .pattern("B B")
                .unlockedBy("has_bone", has(ModItems.IMBUED_BONE.get()))
                .define('S', ModItems.AMETHYST_SILK.get())
                .define('B', ModItems.IMBUED_BONE.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_HELMET.get())
                .pattern("LOL")
                .pattern("O O")
                .unlockedBy("has_obsidian", has(ModItems.OBSIDIAN_SHARD.get()))
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('L', Items.LEATHER)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_CHESTPLATE.get())
                .pattern("LOL")
                .pattern("LOL")
                .pattern("O O")
                .unlockedBy("has_obsidian", has(ModItems.OBSIDIAN_SHARD.get()))
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('L', Items.LEATHER)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_LEGGINGS.get())
                .pattern("OGO")
                .pattern("O O")
                .pattern("L L")
                .unlockedBy("has_obsidian", has(ModItems.OBSIDIAN_SHARD.get()))
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('L', Items.LEATHER)
                .define('G', Items.RAW_GOLD)
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_BOOTS.get())
                .pattern("O O")
                .pattern("L L")
                .unlockedBy("has_obsidian", has(ModItems.OBSIDIAN_SHARD.get()))
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('L', Items.LEATHER)
                .save(recipeOutput);

        // add more recipes here such as oreSmelting or campfireCooking
        // campfireCooking(recipeOutput, ModItems.ITEM_1.get(), RecipeCategory, ModItems.ITEM_2.get(), 0.25f, 100, "recipe_name");
    }



}
