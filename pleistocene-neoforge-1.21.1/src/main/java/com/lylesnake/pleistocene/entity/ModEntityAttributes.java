package com.lylesnake.pleistocene.entity;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.entity.mammoth.MammothEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Pleistocene.MODID)
public class ModEntityAttributes {
    @SubscribeEvent
    public static void register(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MAMMOTH.get(), MammothEntity.createAttributes().build());
    }
}
