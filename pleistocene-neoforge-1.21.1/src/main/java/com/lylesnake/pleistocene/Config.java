package com.lylesnake.pleistocene;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue DISABLE_ZOMBIES = BUILDER
            .comment("Disable zombie natural spawning")
            .define("disableZombies", true);

    public static final ModConfigSpec.BooleanValue DISABLE_SKELETONS = BUILDER
            .comment("Disable skeleton natural spawning")
            .define("disableSkeletons", true);

    public static final ModConfigSpec.BooleanValue DISABLE_CREEPERS = BUILDER
            .comment("Disable creeper natural spawning")
            .define("disableCreepers", true);

    public static final ModConfigSpec.BooleanValue DISABLE_ENDERMEN = BUILDER
            .comment("Disable enderman natural spawning")
            .define("disableEndermen", true);

    public static final ModConfigSpec.BooleanValue REMOVE_STRUCTURES = BUILDER
            .comment("Remove vanilla structures like dungeons, mansions, etc.")
            .define("removeVanillaStructures", true);

    static final ModConfigSpec SPEC = BUILDER.build();

}
