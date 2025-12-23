package com.lylesnake.pleistocene.util;

import com.lylesnake.pleistocene.Pleistocene;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class ModTextUtils {

    public static MutableComponent getTranslation(String key, Object... args) {
        return Component.translatable(Pleistocene.MODID + "." + key, args);
    }
}
