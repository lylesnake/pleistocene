package com.lylesnake.pleistocene.event;

import com.lylesnake.pleistocene.Pleistocene;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = Pleistocene.MODID)
public class DeathEvents {

    @SubscribeEvent
    public static void onPlayerFreezeDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (event.getSource().is(DamageTypes.FREEZE)) {
            grantAdvancement(
                    player,
                    ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "main/die_by_freezing")
            );
        }
    }

    private static void grantAdvancement(ServerPlayer player, ResourceLocation location) {
        AdvancementHolder holder =
                player.server.getAdvancements().get(location);

        if (holder == null) {
            return;
        }

        AdvancementProgress progress =
                player.getAdvancements().getOrStartProgress(holder);

        if (!progress.isDone()) {
            for (String criterion : progress.getRemainingCriteria()) {
                player.getAdvancements().award(holder, criterion);
            }
        }
    }
}
