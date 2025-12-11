package com.lylesnake.pleistocene.entity.mammoth;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MammothRenderer extends GeoEntityRenderer<MammothEntity> {

    public MammothRenderer(EntityRendererProvider.Context context) {
        super(context, new MammothModel());
    }

    @Override
    public void render(MammothEntity mammoth, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        if (mammoth.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        }

        super.render(mammoth, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

}
