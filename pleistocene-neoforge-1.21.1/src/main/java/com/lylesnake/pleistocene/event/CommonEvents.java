package com.lylesnake.pleistocene.event;

import com.lylesnake.pleistocene.Config;
import com.lylesnake.pleistocene.Pleistocene;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

@EventBusSubscriber(modid = Pleistocene.MODID)
public class CommonEvents {

    @SubscribeEvent
    public static void onNaturalSpawn(MobSpawnEvent.PositionCheck event) {
        Mob mob = event.getEntity();
        EntityType<?> type = mob.getType();


        if (type == EntityType.ZOMBIE && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        }
        if (type == EntityType.SKELETON && Config.DISABLE_SKELETONS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        }
        if (type == EntityType.CREEPER && Config.DISABLE_CREEPERS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        }
        if (type == EntityType.ENDERMAN && Config.DISABLE_ENDERMEN.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        }
    }

    @SubscribeEvent
    public static void onSpawnPlacement(MobSpawnEvent.SpawnPlacementCheck event) {
        EntityType<?> type = event.getEntityType();

        if (type == EntityType.ZOMBIE && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }
        if (type == EntityType.SKELETON && Config.DISABLE_SKELETONS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }
        if (type == EntityType.CREEPER && Config.DISABLE_CREEPERS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }
        if (type == EntityType.ENDERMAN && Config.DISABLE_ENDERMEN.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }
    }
}
