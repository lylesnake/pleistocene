package com.lylesnake.pleistocene.item;

import com.lylesnake.pleistocene.Pleistocene;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Pleistocene.MODID);

    // ARMOR INGREDIENTS
    public static final DeferredItem<Item> WOLF_FUR = ITEMS.register("wolf_fur",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FOX_FUR = ITEMS.register("fox_fur",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SNOW_FOX_FUR = ITEMS.register("snow_fox_fur",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POLAR_BEAR_FUR = ITEMS.register("polar_bear_fur",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PROCESSED_FUR = ITEMS.register("processed_fur",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ASH = ITEMS.register("ash",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WEAK_SOLUTION = ITEMS.register("weak_solution",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAWHIDE = ITEMS.register("rawhide",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMETHYST_SILK = ITEMS.register("amethyst_silk",
            () -> new Item(new Item.Properties()));

    // Tools Ingredients
    public static final DeferredItem<Item> KNAPPED_FLINT = ITEMS.register("knapped_flint",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> KNAPPED_BONE = ITEMS.register("knapped_bone",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IMBUED_BONE = ITEMS.register("imbued_bone",
                () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OBSIDIAN_SHARD = ITEMS.register("obsidian_shard",
                () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TWINE = ITEMS.register("twine",
                () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ROCK = ITEMS.register("rock",
                () -> new Item(new Item.Properties()));

    // Tools
    public static final DeferredItem<FlintAndSteelItem> FIRE_STICKS = ITEMS.register("fire_sticks",
            () -> new FlintAndSteelItem(new Item.Properties().durability(32)));

    public static final DeferredItem<AxeItem> HAND_AXE = ITEMS.register("hand_axe",
                () -> new AxeItem(ModToolTiers.HAND, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.HAND, 5, -3.2f))));

    public static final DeferredItem<AxeItem> FLINT_AXE = ITEMS.register("flint_axe",
                () -> new AxeItem(ModToolTiers.FLINT, new Item.Properties()
                        .attributes(AxeItem.createAttributes(ModToolTiers.FLINT, 6.0F, -3.2F))));
    public static final DeferredItem<SwordItem> FLINT_SWORD = ITEMS.register("flint_sword",
                () -> new SwordItem(ModToolTiers.FLINT, new Item.Properties()
                        .attributes(SwordItem.createAttributes(ModToolTiers.FLINT, 3, -2.4F))));
    public static final DeferredItem<PickaxeItem> FLINT_PICKAXE = ITEMS.register("flint_pickaxe",
                () -> new PickaxeItem(ModToolTiers.FLINT, new Item.Properties()
                        .attributes(PickaxeItem.createAttributes(ModToolTiers.FLINT, 1.0F, -2.8F))));
    public static final DeferredItem<ShovelItem> FLINT_SHOVEL = ITEMS.register("flint_shovel",
                () -> new ShovelItem(ModToolTiers.FLINT, new Item.Properties()
                        .attributes(ShovelItem.createAttributes(ModToolTiers.FLINT, 1.5F, -3.0F))));
    public static final DeferredItem<HoeItem> FLINT_HOE = ITEMS.register("flint_hoe",
                () -> new HoeItem(ModToolTiers.FLINT, new Item.Properties()
                        .attributes(HoeItem.createAttributes(ModToolTiers.FLINT, 0.0F, -3.0F))));
    public static final DeferredItem<Item> FLINT_SHEARS = ITEMS.register("flint_shears",
                () -> new ShearsItem(new Item.Properties().durability(104)
                        .component(DataComponents.TOOL, ShearsItem.createToolProperties())));

    public static final DeferredItem<AxeItem> BONE_AXE = ITEMS.register("bone_axe",
                () -> new AxeItem(ModToolTiers.BONE, new Item.Properties()
                        .attributes(AxeItem.createAttributes(ModToolTiers.BONE, 7.0F, -3.2F))));
    public static final DeferredItem<SwordItem> BONE_SWORD = ITEMS.register("bone_sword",
                () -> new SwordItem(ModToolTiers.BONE, new Item.Properties()
                        .attributes(SwordItem.createAttributes(ModToolTiers.BONE, 3, -2.4F))));
    public static final DeferredItem<PickaxeItem> BONE_PICKAXE = ITEMS.register("bone_pickaxe",
                () -> new PickaxeItem(ModToolTiers.BONE, new Item.Properties()
                        .attributes(PickaxeItem.createAttributes(ModToolTiers.BONE, 1.0F, -2.8F))));
    public static final DeferredItem<ShovelItem> BONE_SHOVEL = ITEMS.register("bone_shovel",
                () -> new ShovelItem(ModToolTiers.BONE, new Item.Properties()
                        .attributes(ShovelItem.createAttributes(ModToolTiers.BONE, 1.5F, -3.0F))));
    public static final DeferredItem<HoeItem> BONE_HOE = ITEMS.register("bone_hoe",
                () -> new HoeItem(ModToolTiers.BONE, new Item.Properties()
                        .attributes(HoeItem.createAttributes(ModToolTiers.BONE, -1.0F, -2.0F))));

    public static final DeferredItem<AxeItem> IMBUED_AXE = ITEMS.register("imbued_axe",
                () -> new AxeItem(ModToolTiers.IMBUED, new Item.Properties()
                        .attributes(AxeItem.createAttributes(ModToolTiers.IMBUED, 6.0F, -3.1F))));
    public static final DeferredItem<SwordItem> IMBUED_SWORD = ITEMS.register("imbued_sword",
                () -> new SwordItem(ModToolTiers.IMBUED, new Item.Properties()
                        .attributes(SwordItem.createAttributes(ModToolTiers.IMBUED, 5, 2.4F))));
    public static final DeferredItem<PickaxeItem> IMBUED_PICKAXE = ITEMS.register("imbued_pickaxe",
                () -> new PickaxeItem(ModToolTiers.IMBUED, new Item.Properties()
                        .attributes(PickaxeItem.createAttributes(ModToolTiers.IMBUED, 1.0F, -2.8F))));
    public static final DeferredItem<ShovelItem> IMBUED_SHOVEL = ITEMS.register("imbued_shovel",
                () -> new ShovelItem(ModToolTiers.IMBUED, new Item.Properties()
                        .attributes(ShovelItem.createAttributes(ModToolTiers.IMBUED, 1.5F, -3.0F))));
    public static final DeferredItem<HoeItem> IMBUED_HOE = ITEMS.register("imbued_hoe",
                () -> new HoeItem(ModToolTiers.IMBUED, new Item.Properties()
                        .attributes(HoeItem.createAttributes(ModToolTiers.IMBUED, -2.0F, -1.0F))));

    public static final DeferredItem<AxeItem> OBSIDIAN_AXE = ITEMS.register("obsidian_axe",
                () -> new AxeItem(ModToolTiers.OBSIDIAN, new Item.Properties()
                        .attributes(AxeItem.createAttributes(ModToolTiers.OBSIDIAN, 5.0F, -3.0F))));
    public static final DeferredItem<SwordItem> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword",
                () -> new SwordItem(ModToolTiers.OBSIDIAN, new Item.Properties()
                        .attributes(SwordItem.createAttributes(ModToolTiers.OBSIDIAN, 3, -2.4F))));
    public static final DeferredItem<PickaxeItem> OBSIDIAN_PICKAXE = ITEMS.register("obsidian_pickaxe",
                () -> new PickaxeItem(ModToolTiers.OBSIDIAN, new Item.Properties()
                        .attributes(PickaxeItem.createAttributes(ModToolTiers.OBSIDIAN, 1.0F, -2.8F))));
    public static final DeferredItem<ShovelItem> OBSIDIAN_SHOVEL = ITEMS.register("obsidian_shovel",
                () -> new ShovelItem(ModToolTiers.OBSIDIAN, new Item.Properties()
                        .attributes(ShovelItem.createAttributes(ModToolTiers.OBSIDIAN, 1.5F, -3.0F))));
    public static final DeferredItem<HoeItem> OBSIDIAN_HOE = ITEMS.register("obsidian_hoe",
                () -> new HoeItem(ModToolTiers.OBSIDIAN, new Item.Properties()
                        .attributes(HoeItem.createAttributes(ModToolTiers.OBSIDIAN, -3.0F, 0.0F))));

    //  Armor
    public static final DeferredItem<ArmorItem> HIDE_MASK = ITEMS.register("hide_mask",
            () -> new ArmorItem(ModArmorMaterials.HIDE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(3))));
    public static final DeferredItem<ArmorItem> HIDE_CHESTPIECE = ITEMS.register("hide_chestpiece",
            () -> new ArmorItem(ModArmorMaterials.HIDE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(3))));
    public static final DeferredItem<ArmorItem> HIDE_LOINCLOTH = ITEMS.register("hide_loincloth",
            () -> new ArmorItem(ModArmorMaterials.HIDE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(3))));
    public static final DeferredItem<ArmorItem> HIDE_SANDALS = ITEMS.register("hide_sandals",
            () -> new ArmorItem(ModArmorMaterials.HIDE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(3))));

    public static final DeferredItem<ArmorItem> FUR_HOOD = ITEMS.register("fur_hood",
            () -> new ArmorItem(ModArmorMaterials.FUR_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(16))));
    public static final DeferredItem<ArmorItem> FUR_TUNIC = ITEMS.register("fur_tunic",
            () -> new ArmorItem(ModArmorMaterials.FUR_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))));
    public static final DeferredItem<ArmorItem> FUR_LEGGINGS = ITEMS.register("fur_leggings",
            () -> new ArmorItem(ModArmorMaterials.FUR_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(16))));
    public static final DeferredItem<ArmorItem> FUR_BOOTS = ITEMS.register("fur_boots",
            () -> new ArmorItem(ModArmorMaterials.FUR_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(16))));

    public static final DeferredItem<ArmorItem> IMBUED_HOOD = ITEMS.register("imbued_hood",
            () -> new ArmorItem(ModArmorMaterials.IMBUED_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(22))));
    public static final DeferredItem<ArmorItem> IMBUED_PONCHO = ITEMS.register("imbued_poncho",
            () -> new ArmorItem(ModArmorMaterials.IMBUED_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(22))));
    public static final DeferredItem<ArmorItem> IMBUED_LEGGINGS = ITEMS.register("imbued_leggings",
            () -> new ArmorItem(ModArmorMaterials.IMBUED_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(22))));
    public static final DeferredItem<ArmorItem> IMBUED_BOOTS = ITEMS.register("imbued_boots",
            () -> new ArmorItem(ModArmorMaterials.IMBUED_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(22))));

    public static final DeferredItem<ArmorItem> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<ArmorItem> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<ArmorItem> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<ArmorItem> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
