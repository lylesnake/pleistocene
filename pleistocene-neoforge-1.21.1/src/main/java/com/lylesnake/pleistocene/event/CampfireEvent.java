package com.lylesnake.pleistocene.event;


import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.CampfireBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber
public class CampfireEvent {
    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
        if (event.getState().is(BlockTags.CAMPFIRES)) {
            if (! event.getBlockSnapshot().getState().is(BlockTags.CAMPFIRES)) {
                    event.getLevel().setBlock(event.getPos(), event.getState().setValue(CampfireBlock.LIT, false), 3);
            }
        }
    }
}