package com.lylesnake.pleistocene;

import com.lylesnake.pleistocene.block.ModBlocks;
import com.lylesnake.pleistocene.block.entity.ModBlockEntities;
import com.lylesnake.pleistocene.block.entity.choppingblock.ChoppingBlockEntity;
import com.lylesnake.pleistocene.block.entity.dryingrack.DryingRackBlockEntity;
import com.lylesnake.pleistocene.block.entity.renderer.PedestalBlockEntityRenderer;
import com.lylesnake.pleistocene.entity.ModEntities;
import com.lylesnake.pleistocene.item.ModCreativeModeTabs;
import com.lylesnake.pleistocene.item.ModItems;
import com.lylesnake.pleistocene.loot.ModLootModifiers;
import com.lylesnake.pleistocene.recipe.ModRecipeSerializers;
import com.lylesnake.pleistocene.recipe.ModRecipeTypes;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Pleistocene.MODID)
public class Pleistocene {
    public static final String MODID = "pleistocene";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Pleistocene(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModRecipeSerializers.register(modEventBus);
        ModRecipeTypes.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        modEventBus.addListener(this::registerCapabilities);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("Pliestocene is setting up...");
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        ChoppingBlockEntity.registerCapabilities(event);
        DryingRackBlockEntity.registerCapabilities(event);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("Your PLEISTOCENE server is starting");
    }

    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);
        }

    }
}
