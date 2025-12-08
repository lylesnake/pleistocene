package com.lylesnake.pleistocene.util;

import com.lylesnake.pleistocene.Pleistocene;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_HAND_TOOL = createTag("needs_hand_tool");
        public static final TagKey<Block> INCORRECT_FOR_HAND_TOOL = createTag("incorrect_for_hand_tool");

        public static final TagKey<Block> NEEDS_FLINT_TOOL = createTag("needs_flint_tool");
        public static final TagKey<Block> INCORRECT_FOR_FLINT_TOOL = createTag("incorrect_for_flint_tool");

        public static final TagKey<Block> NEEDS_BONE_TOOL = createTag("needs_bone_tool");
        public static final TagKey<Block> INCORRECT_FOR_BONE_TOOL = createTag("incorrect_for_bone_tool");

        public static final TagKey<Block> NEEDS_IMBUED_TOOL = createTag("needs_imbued_tool");
        public static final TagKey<Block> INCORRECT_FOR_IMBUED_TOOL = createTag("incorrect_for_imbued_tool");

        public static final TagKey<Block> NEEDS_OBSIDIAN_TOOL = createTag("needs_obsidian_tool");
        public static final TagKey<Block> INCORRECT_FOR_OBSIDIAN_TOOL = createTag("incorrect_for_obsidian_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> PELTS = createTag("pelts");

        public static final TagKey<Item> HAND_TOOLS = createTag("hand_tools");
        public static final TagKey<Item> FLINT_TOOLS = createTag("flint_tools");
        public static final TagKey<Item> BONE_TOOLS = createTag("bone_tools");
        public static final TagKey<Item> IMBUED_TOOLS = createTag("imbued_tools");
        public static final TagKey<Item> OBSIDIAN_TOOLS = createTag("obsidian_tools");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, name));
        }
    }

}
