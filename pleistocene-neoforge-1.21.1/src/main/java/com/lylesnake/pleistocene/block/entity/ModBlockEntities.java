package com.lylesnake.pleistocene.block.entity;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.block.ModBlocks;
import com.lylesnake.pleistocene.block.entity.choppingblock.ChoppingBlockEntity;
import com.lylesnake.pleistocene.block.entity.dryingrack.DryingRackBlockEntity;
import com.lylesnake.pleistocene.block.entity.pedestal.PedestalBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Pleistocene.MODID);

    public static final Supplier<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
            BLOCK_ENTITIES.register("pedestal_be", () -> BlockEntityType.Builder.of(
                    PedestalBlockEntity::new,
                    ModBlocks.PEDESTAL.get()
            ).build(null));

    public static final Supplier<BlockEntityType<ChoppingBlockEntity>> CHOPPING_BLOCK_BE =
            BLOCK_ENTITIES.register("chopping_block", () -> BlockEntityType.Builder.of(
                    ChoppingBlockEntity::new,
                    ModBlocks.CHOPPING_BLOCK.get()
            ).build(null));

    public static final Supplier<BlockEntityType<DryingRackBlockEntity>> DRYING_RACK_BE =
            BLOCK_ENTITIES.register("drying_rack", () -> BlockEntityType.Builder.of(
                    DryingRackBlockEntity::new,
                    ModBlocks.DRYING_RACK.get()
            ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}