package com.lylesnake.pleistocene.entity.mammoth;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MammothRenderer extends GeoEntityRenderer<MammothEntity> {

    public MammothRenderer(EntityRendererProvider.Context context) {
        super(context, new MammothModel());
    }

}
