package com.lylesnake.pleistocene.item;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Pleistocene.MODID);

    public static final Supplier<CreativeModeTab> PLEISTOCENE_TAB = CREATIVE_MODE_TAB.register("pleistocene_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.WOLF_FUR.get()))
                    .title(Component.translatable("creativetab.pleistocene.pleistocene_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        // ingredients
                        output.accept(ModItems.WOLF_FUR);
                        output.accept(ModItems.FOX_FUR);
                        output.accept(ModItems.SNOW_FOX_FUR);
                        output.accept(ModItems.POLAR_BEAR_FUR);
                        output.accept(ModItems.MAMMOTH_FUR);
                        output.accept(ModItems.ASH);
                        output.accept(ModItems.PROCESSED_FUR);
                        output.accept(ModItems.WEAK_SOLUTION);
                        output.accept(ModItems.TWINE);
                        output.accept(ModItems.RAWHIDE);
                        output.accept(ModItems.AMETHYST_SILK);

                        output.accept(ModItems.KNAPPED_FLINT);
                        output.accept(ModItems.KNAPPED_BONE);
                        output.accept(ModItems.IMBUED_BONE);
                        output.accept(ModItems.OBSIDIAN_SHARD);
                        output.accept(ModItems.ROCK);

                        // blocks
                        output.accept(ModBlocks.FLINT_BLOCK);
                        output.accept(ModBlocks.PEDESTAL);
                        output.accept(ModBlocks.CHOPPING_BLOCK.get());
                        output.accept(ModBlocks.DRYING_RACK.get());

                        // armor
                        output.accept(ModItems.HIDE_MASK);
                        output.accept(ModItems.HIDE_CHESTPIECE);
                        output.accept(ModItems.HIDE_LOINCLOTH);
                        output.accept(ModItems.HIDE_SANDALS);

                        output.accept(ModItems.FUR_HOOD);
                        output.accept(ModItems.FUR_TUNIC);
                        output.accept(ModItems.FUR_LEGGINGS);
                        output.accept(ModItems.FUR_BOOTS);

                        output.accept(ModItems.IMBUED_HOOD);
                        output.accept(ModItems.IMBUED_PONCHO);
                        output.accept(ModItems.IMBUED_LEGGINGS);
                        output.accept(ModItems.IMBUED_BOOTS);

                        output.accept(ModItems.OBSIDIAN_HELMET);
                        output.accept(ModItems.OBSIDIAN_CHESTPLATE);
                        output.accept(ModItems.OBSIDIAN_LEGGINGS);
                        output.accept(ModItems.OBSIDIAN_BOOTS);

                        // TOOLS
                        output.accept(ModItems.HAND_AXE);
                        output.accept(ModItems.FIRE_STICKS);

                        output.accept(ModItems.FLINT_AXE);
                        output.accept(ModItems.FLINT_PICKAXE);
                        output.accept(ModItems.FLINT_SHOVEL);
                        output.accept(ModItems.FLINT_SWORD);
                        output.accept(ModItems.FLINT_HOE);
                        output.accept(ModItems.FLINT_SHEARS);

                        output.accept(ModItems.BONE_AXE);
                        output.accept(ModItems.BONE_PICKAXE);
                        output.accept(ModItems.BONE_SHOVEL);
                        output.accept(ModItems.BONE_SWORD);
                        output.accept(ModItems.BONE_HOE);

                        output.accept(ModItems.IMBUED_AXE);
                        output.accept(ModItems.IMBUED_PICKAXE);
                        output.accept(ModItems.IMBUED_SHOVEL);
                        output.accept(ModItems.IMBUED_SWORD);
                        output.accept(ModItems.IMBUED_HOE);

                        output.accept(ModItems.OBSIDIAN_AXE);
                        output.accept(ModItems.OBSIDIAN_PICKAXE);
                        output.accept(ModItems.OBSIDIAN_SHOVEL);
                        output.accept(ModItems.OBSIDIAN_SWORD);
                        output.accept(ModItems.OBSIDIAN_HOE);

                        // ANIMALS
                        output.accept(ModItems.MAMMOTH_SPAWN_EGG);


                    }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
