package com.lylesnake.pleistocene.datagen;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Pleistocene.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.WOLF_FUR.get());
        basicItem(ModItems.FOX_FUR.get());
        basicItem(ModItems.SNOW_FOX_FUR.get());
        basicItem(ModItems.POLAR_BEAR_FUR.get());
        basicItem(ModItems.MAMMOTH_FUR.get());
        basicItem(ModItems.PROCESSED_FUR.get());
        basicItem(ModItems.ASH.get());
        basicItem(ModItems.WEAK_SOLUTION.get());
        basicItem(ModItems.RAWHIDE.get());
        basicItem(ModItems.AMETHYST_SILK.get());

        basicItem(ModItems.HIDE_MASK.get());
        basicItem(ModItems.HIDE_CHESTPIECE.get());
        basicItem(ModItems.HIDE_LOINCLOTH.get());
        basicItem(ModItems.HIDE_SANDALS.get());

        basicItem(ModItems.FUR_HOOD.get());
        basicItem(ModItems.FUR_TUNIC.get());
        basicItem(ModItems.FUR_LEGGINGS.get());
        basicItem(ModItems.FUR_BOOTS.get());

        basicItem(ModItems.IMBUED_HOOD.get());
        basicItem(ModItems.IMBUED_PONCHO.get());
        basicItem(ModItems.IMBUED_LEGGINGS.get());
        basicItem(ModItems.IMBUED_BOOTS.get());

        basicItem(ModItems.OBSIDIAN_HELMET.get());
        basicItem(ModItems.OBSIDIAN_CHESTPLATE.get());
        basicItem(ModItems.OBSIDIAN_LEGGINGS.get());
        basicItem(ModItems.OBSIDIAN_BOOTS.get());

        basicItem(ModItems.KNAPPED_FLINT.get());
        basicItem(ModItems.KNAPPED_BONE.get());
        basicItem(ModItems.IMBUED_BONE.get());
        basicItem(ModItems.OBSIDIAN_SHARD.get());
        basicItem(ModItems.TWINE.get());
        basicItem(ModItems.ROCK.get());

        handheldItem(ModItems.HAND_AXE);
        handheldItem(ModItems.FIRE_STICKS);

        handheldItem(ModItems.FLINT_AXE);
        handheldItem(ModItems.FLINT_PICKAXE);
        handheldItem(ModItems.FLINT_SHOVEL);
        handheldItem(ModItems.FLINT_SWORD);
        handheldItem(ModItems.FLINT_HOE);
        basicItem(ModItems.FLINT_SHEARS.get());

        handheldItem(ModItems.BONE_AXE);
        handheldItem(ModItems.BONE_PICKAXE);
        handheldItem(ModItems.BONE_SHOVEL);
        handheldItem(ModItems.BONE_SWORD);
        handheldItem(ModItems.BONE_HOE);

        handheldItem(ModItems.IMBUED_AXE);
        handheldItem(ModItems.IMBUED_PICKAXE);
        handheldItem(ModItems.IMBUED_SHOVEL);
        handheldItem(ModItems.IMBUED_SWORD);
        handheldItem(ModItems.IMBUED_HOE);

        handheldItem(ModItems.OBSIDIAN_AXE);
        handheldItem(ModItems.OBSIDIAN_PICKAXE);
        handheldItem(ModItems.OBSIDIAN_SHOVEL);
        handheldItem(ModItems.OBSIDIAN_SWORD);
        handheldItem(ModItems.OBSIDIAN_HOE);

        withExistingParent(ModItems.MAMMOTH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "item/" + item.getId().getPath()));
    }
}
