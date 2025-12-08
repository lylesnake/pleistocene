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

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = Pleistocene.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = Pleistocene.MODID, value = Dist.CLIENT)
public class PleistoceneClient {
    public PleistoceneClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        Pleistocene.LOGGER.info("HELLO FROM PLEISTOCENE CLIENT SETUP");
        Pleistocene.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        // vanilla renderers
        event.registerBlockEntityRenderer(ModBlockEntities.CHOPPING_BLOCK_BE.get(), ChoppingBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.DRYING_RACK_BE.get(), DryingRackBlockEntityRenderer::new);
    }

    public static void registerRenderers(BiConsumer<EntityType<? extends Entity>, EntityRendererProvider> entityRenderers) {

        entityRenderers.accept(ModEntities.MAMMOTH.get(), MammothRenderer::new);

    }
}
