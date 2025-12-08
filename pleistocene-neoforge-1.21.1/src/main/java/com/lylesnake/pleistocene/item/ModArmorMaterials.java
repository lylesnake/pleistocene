package com.lylesnake.pleistocene.item;

import com.lylesnake.pleistocene.Pleistocene;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    // Tier 1 - Rawhide + grass
    public static final Holder<ArmorMaterial> HIDE_ARMOR_MATERIAL = register("hide",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 1);
                attribute.put(ArmorItem.Type.LEGGINGS, 2);
                attribute.put(ArmorItem.Type.CHESTPLATE, 2);
                attribute.put(ArmorItem.Type.HELMET, 1);
                attribute.put(ArmorItem.Type.BODY, 3);
            }), 12, 0, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> ModItems.RAWHIDE.get());
    // Tier 2 - retextured vanilla leather
    //Tier 3 - Fur
    public static final Holder<ArmorMaterial> FUR_ARMOR_MATERIAL = register("fur",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 1);
                attribute.put(ArmorItem.Type.LEGGINGS, 3);
                attribute.put(ArmorItem.Type.CHESTPLATE, 5);
                attribute.put(ArmorItem.Type.HELMET, 2);
                attribute.put(ArmorItem.Type.BODY, 7);
            }), 10, 0.5f, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> ModItems.PROCESSED_FUR.get());
    //Tier 4 - Imbued Bone
    public static final Holder<ArmorMaterial> IMBUED_ARMOR_MATERIAL = register("imbued",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 2);
                attribute.put(ArmorItem.Type.LEGGINGS, 5);
                attribute.put(ArmorItem.Type.CHESTPLATE, 6);
                attribute.put(ArmorItem.Type.HELMET, 2);
                attribute.put(ArmorItem.Type.BODY, 5);
            }), 20, 1, 0.1f, SoundEvents.ARMOR_EQUIP_GOLD,
            () -> ModItems.IMBUED_BONE.get());
    //Tier 5 - Obsidian
    public static final Holder<ArmorMaterial> OBSIDIAN_ARMOR_MATERIAL = register("obsidian",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 3);
                attribute.put(ArmorItem.Type.LEGGINGS, 6);
                attribute.put(ArmorItem.Type.CHESTPLATE, 8);
                attribute.put(ArmorItem.Type.HELMET, 3);
                attribute.put(ArmorItem.Type.BODY, 11);
            }), 9, 3, 0.2f, SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> ModItems.OBSIDIAN_SHARD.get());

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance, Holder<SoundEvent> equipSound,
                                                  Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, name);
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));
        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
