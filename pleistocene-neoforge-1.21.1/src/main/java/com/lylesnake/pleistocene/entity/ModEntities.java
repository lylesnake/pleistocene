package com.lylesnake.pleistocene.entity;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.entity.mammoth.MammothEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.commands.PlaceCommand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import software.bernie.geckolib.animatable.GeoEntity;

import java.util.function.Supplier;


public class ModEntities  {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Pleistocene.MODID);

    // set the hitbox here
    public static final Supplier<EntityType<MammothEntity>> MAMMOTH =
            ENTITY_TYPES.register("mammoth", () -> EntityType.Builder.of(MammothEntity::new, MobCategory.CREATURE)
                    .sized(2, 2).build("mammoth"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
