package com.lylesnake.pleistocene;

import net.neoforged.neoforge.common.ModConfigSpec;

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

    public static final ModConfigSpec.BooleanValue DISABLE_SPIDERS = BUILDER
            .comment("Disable spider natural spawning")
            .define("disableSpiders", true);

    public static final ModConfigSpec.BooleanValue DISABLE_HOSTILE_HUMANOIDS = BUILDER
            .comment("Disable hostile humanoids (i.e. witches, raiders, etc) natural spawning")
            .define("disableWitches", true);

    public static final ModConfigSpec.BooleanValue DISABLE_WARDEN = BUILDER
            .comment("Disable Warden natural spawning")
            .define("disableWarden", true);

    static final ModConfigSpec SPEC = BUILDER.build();

}
