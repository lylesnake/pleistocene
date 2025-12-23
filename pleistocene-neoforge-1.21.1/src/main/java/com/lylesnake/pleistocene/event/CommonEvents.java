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

        // ZOMBIES
        if (type == EntityType.ZOMBIE && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.ZOMBIE_VILLAGER && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.ZOMBIE_HORSE && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.HUSK && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.DROWNED && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        }

        // SKELETONS
        if (type == EntityType.SKELETON && Config.DISABLE_SKELETONS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.STRAY && Config.DISABLE_SKELETONS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.BOGGED && Config.DISABLE_SKELETONS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        }

        // UNCATEGORIZED
        if (type == EntityType.CREEPER && Config.DISABLE_CREEPERS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.ENDERMAN && Config.DISABLE_ENDERMEN.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.WARDEN && Config.DISABLE_WARDEN.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        }

        // SPIDERS
        if (type == EntityType.SPIDER && Config.DISABLE_SPIDERS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.CAVE_SPIDER && Config.DISABLE_SPIDERS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        }

        // HUMANOIDS
        if (type == EntityType.WITCH && Config.DISABLE_HOSTILE_HUMANOIDS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.EVOKER && Config.DISABLE_HOSTILE_HUMANOIDS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.PILLAGER && Config.DISABLE_HOSTILE_HUMANOIDS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.RAVAGER && Config.DISABLE_HOSTILE_HUMANOIDS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        } if (type == EntityType.VINDICATOR && Config.DISABLE_HOSTILE_HUMANOIDS.get()) {
            event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
        }

    }

    @SubscribeEvent
    public static void onSpawnPlacement(MobSpawnEvent.SpawnPlacementCheck event) {
        EntityType<?> type = event.getEntityType();

        // ZOMBIES
        if (type == EntityType.ZOMBIE && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.ZOMBIE_VILLAGER && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.ZOMBIE_HORSE && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.HUSK && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.DROWNED && Config.DISABLE_ZOMBIES.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }

        // SKELETONS
        if (type == EntityType.SKELETON && Config.DISABLE_SKELETONS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.STRAY && Config.DISABLE_SKELETONS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.BOGGED && Config.DISABLE_SKELETONS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }

        // UNCATEGORIZED
        if (type == EntityType.CREEPER && Config.DISABLE_CREEPERS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.ENDERMAN && Config.DISABLE_ENDERMEN.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.WARDEN && Config.DISABLE_WARDEN.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }

        // SPIDERS
        if (type == EntityType.SPIDER && Config.DISABLE_SPIDERS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.CAVE_SPIDER && Config.DISABLE_SPIDERS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }

        // HUMANOIDS
        if (type == EntityType.WITCH && Config.DISABLE_HOSTILE_HUMANOIDS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.EVOKER && Config.DISABLE_HOSTILE_HUMANOIDS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.PILLAGER && Config.DISABLE_HOSTILE_HUMANOIDS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.RAVAGER && Config.DISABLE_HOSTILE_HUMANOIDS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        } if (type == EntityType.VINDICATOR && Config.DISABLE_HOSTILE_HUMANOIDS.get()) {
            event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
        }

    }
}
