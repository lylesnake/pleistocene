package com.lylesnake.pleistocene.entity.mammoth;

import com.lylesnake.pleistocene.Pleistocene;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class MammothModel extends DefaultedEntityGeoModel<MammothEntity> {

    private static final ResourceLocation BABY_MAMMOTH_MODEL =
            ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "geo/entity/mammoth_baby.geo.json");
    private static final ResourceLocation MAMMOTH_MODEL =
            ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "geo/entity/mammoth.geo.json");

    public MammothModel() {
        super(ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "mammoth"));;
    }

    @Override
    public ResourceLocation getModelResource(MammothEntity animatable) {
        if (animatable.isBaby()) {
            return BABY_MAMMOTH_MODEL;
        } return MAMMOTH_MODEL;
    }

    /*
    Model json credits: TeamFossilsArcheology
     */
}
