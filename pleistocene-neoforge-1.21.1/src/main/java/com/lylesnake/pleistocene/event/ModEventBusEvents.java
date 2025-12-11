package com.lylesnake.pleistocene.event;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.entity.ModEntities;
import com.lylesnake.pleistocene.entity.mammoth.MammothEntity;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = Pleistocene.MODID)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerSpawnPlacements (RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.MAMMOTH.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
