package com.lylesnake.pleistocene.sound;

import com.lylesnake.pleistocene.Pleistocene;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Pleistocene.MODID);

    public static final Supplier<SoundEvent> MAMMOTH_AMBIENT = registerSoundEvent("mammoth_ambient");
    public static final Supplier<SoundEvent> MAMMOTH_HURT = registerSoundEvent("mammoth_hurt");
    public static final Supplier<SoundEvent> MAMMOTH_DEATH = registerSoundEvent("mammoth_death");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }


}
