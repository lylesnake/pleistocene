package com.lylesnake.pleistocene.event;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.entity.ModEntities;
import com.lylesnake.pleistocene.entity.mammoth.MammothRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Pleistocene.MODID, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.MAMMOTH.get(), MammothRenderer::new);
    }
}
