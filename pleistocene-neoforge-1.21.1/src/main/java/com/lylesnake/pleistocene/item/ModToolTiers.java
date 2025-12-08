package com.lylesnake.pleistocene.item;

import com.lylesnake.pleistocene.util.ModTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {

    // Worst specs of all
    public static final Tier HAND = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_HAND_TOOL,
            10, 2.0F, 0.0F, 5, () -> Ingredient.of(Items.FLINT));

    // Stone with Gold enchantment value
    public static final Tier FLINT = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_FLINT_TOOL,
            50, 4.0F, 1.0F, 22, () -> Ingredient.of(ModItems.KNAPPED_FLINT));

    // Iron
    public static final Tier BONE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_BONE_TOOL,
            250, 6.0F, 2.0F, 14, () -> Ingredient.of(ModItems.KNAPPED_BONE));

    // Diamond but more enchantable
    public static final Tier IMBUED = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_IMBUED_TOOL,
            1561, 8.0F, 3.0F, 28, () -> Ingredient.of(ModItems.IMBUED_BONE));

    // Netherite
    public static final Tier OBSIDIAN = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_OBSIDIAN_TOOL,
            2031, 9.0F, 4.0F, 15, () -> Ingredient.of(ModItems.OBSIDIAN_SHARD));
}
