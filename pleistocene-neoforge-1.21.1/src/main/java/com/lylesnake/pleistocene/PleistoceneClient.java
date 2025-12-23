package com.lylesnake.pleistocene;

import com.lylesnake.pleistocene.block.entity.ModBlockEntities;
import com.lylesnake.pleistocene.block.entity.renderer.ChoppingBlockEntityRenderer;
import com.lylesnake.pleistocene.block.entity.renderer.DryingRackBlockEntityRenderer;
import com.lylesnake.pleistocene.entity.ModEntities;
import com.lylesnake.pleistocene.entity.mammoth.MammothRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import software.bernie.geckolib.event.GeoRenderEvent;


import java.util.function.BiConsumer;

@Mod(value = Pleistocene.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Pleistocene.MODID, value = Dist.CLIENT)
public class PleistoceneClient {
    public PleistoceneClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        Pleistocene.LOGGER.info("Pleistocene config should be visible");
        Pleistocene.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }



}
