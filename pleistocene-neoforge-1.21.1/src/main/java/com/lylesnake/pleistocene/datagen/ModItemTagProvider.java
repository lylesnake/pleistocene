package com.lylesnake.pleistocene.datagen;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.block.ModBlocks;
import com.lylesnake.pleistocene.item.ModItems;
import com.lylesnake.pleistocene.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Pleistocene.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.SWORDS)
                .add(ModItems.FLINT_SWORD.get())
                .add(ModItems.BONE_SWORD.get())
                .add(ModItems.IMBUED_SWORD.get())
                .add(ModItems.OBSIDIAN_SWORD.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.FLINT_PICKAXE.get())
                .add(ModItems.BONE_PICKAXE.get())
                .add(ModItems.IMBUED_PICKAXE.get())
                .add(ModItems.OBSIDIAN_PICKAXE.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.FLINT_SHOVEL.get())
                .add(ModItems.BONE_SHOVEL.get())
                .add(ModItems.IMBUED_SHOVEL.get())
                .add(ModItems.OBSIDIAN_SHOVEL.get());

        tag(ItemTags.AXES)
                .add(ModItems.HAND_AXE.get())
                .add(ModItems.FLINT_AXE.get())
                .add(ModItems.BONE_AXE.get())
                .add(ModItems.IMBUED_AXE.get())
                .add(ModItems.OBSIDIAN_AXE.get());

        tag(ItemTags.HOES)
                .add(ModItems.FLINT_HOE.get())
                .add(ModItems.BONE_HOE.get())
                .add(ModItems.IMBUED_HOE.get())
                .add(ModItems.OBSIDIAN_HOE.get());

        tag(Tags.Items.TOOLS_SHEAR)
                .add(ModItems.FLINT_SHEARS.get());

        tag(ModTags.Items.HAND_TOOLS)
                .add(ModItems.HAND_AXE.get());

        tag(ModTags.Items.FLINT_TOOLS)
                .add(ModItems.FLINT_AXE.get())
                .add(ModItems.FLINT_SHOVEL.get())
                .add(ModItems.FLINT_SWORD.get())
                .add(ModItems.FLINT_PICKAXE.get())
                .add(ModItems.FLINT_HOE.get());

        tag(ModTags.Items.BONE_TOOLS)
                .add(ModItems.BONE_AXE.get())
                .add(ModItems.BONE_SHOVEL.get())
                .add(ModItems.BONE_SWORD.get())
                .add(ModItems.BONE_PICKAXE.get())
                .add(ModItems.BONE_HOE.get());

        tag(ModTags.Items.IMBUED_TOOLS)
                .add(ModItems.IMBUED_AXE.get())
                .add(ModItems.IMBUED_SHOVEL.get())
                .add(ModItems.IMBUED_SWORD.get())
                .add(ModItems.IMBUED_PICKAXE.get())
                .add(ModItems.IMBUED_HOE.get());

        tag(ModTags.Items.OBSIDIAN_TOOLS)
                .add(ModItems.OBSIDIAN_AXE.get())
                .add(ModItems.OBSIDIAN_SHOVEL.get())
                .add(ModItems.OBSIDIAN_SWORD.get())
                .add(ModItems.OBSIDIAN_PICKAXE.get())
                .add(ModItems.OBSIDIAN_HOE.get());

    }
}