package com.lylesnake.pleistocene.datagen;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.block.ModBlocks;
import com.lylesnake.pleistocene.item.ModItems;
import com.lylesnake.pleistocene.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Pleistocene.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ModTags.Blocks.NEEDS_HAND_TOOL)
                .addTag(BlockTags.LOGS)
                .addTag(BlockTags.PLANKS);

        tag(ModTags.Blocks.INCORRECT_FOR_HAND_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .remove(ModTags.Blocks.NEEDS_HAND_TOOL);

        tag(ModTags.Blocks.NEEDS_FLINT_TOOL)
                .addTag(BlockTags.NEEDS_STONE_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_FLINT_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .remove(ModTags.Blocks.NEEDS_FLINT_TOOL);

        tag(ModTags.Blocks.NEEDS_BONE_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_BONE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_BONE_TOOL);

        tag(ModTags.Blocks.NEEDS_IMBUED_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(BlockTags.DIAMOND_ORES);

        tag(ModTags.Blocks.INCORRECT_FOR_IMBUED_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_IMBUED_TOOL);

        tag(ModTags.Blocks.NEEDS_OBSIDIAN_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_OBSIDIAN_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .remove(ModTags.Blocks.NEEDS_OBSIDIAN_TOOL);

    }
}