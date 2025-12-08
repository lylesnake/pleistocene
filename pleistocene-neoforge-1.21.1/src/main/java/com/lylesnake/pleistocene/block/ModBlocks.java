package com.lylesnake.pleistocene.block;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.block.entity.pedestal.PedestalBlock;
import com.lylesnake.pleistocene.block.entity.choppingblock.ChoppingBlock;
import com.lylesnake.pleistocene.block.entity.dryingrack.DryingRackBlock;
import com.lylesnake.pleistocene.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Pleistocene.MODID);

    // block list
    public static final DeferredBlock<Block> FLINT_BLOCK = registerBlock("flint_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.7f, 2f)
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> PEDESTAL = registerBlock("pedestal",
            () -> new PedestalBlock(BlockBehaviour.Properties.of()
                    .strength(2.0f, 2f)
                    .sound(SoundType.WOOD)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()));

    public static final DeferredBlock<Block> CHOPPING_BLOCK = registerBlock("chopping_block",
            () -> new ChoppingBlock(BlockBehaviour.Properties.of()
                    .strength(2.0f, 2f)
                    .sound(SoundType.WOOD)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()));

    public static final DeferredBlock<Block> DRYING_RACK = registerBlock("drying_rack",
            () -> new DryingRackBlock(BlockBehaviour.Properties.of()
                    .strength(1.0f, 1f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    // registration
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
