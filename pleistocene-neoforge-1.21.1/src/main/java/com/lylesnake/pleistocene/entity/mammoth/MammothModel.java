package com.lylesnake.pleistocene.entity.mammoth;

import com.lylesnake.pleistocene.Pleistocene;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class MammothModel extends DefaultedEntityGeoModel<MammothEntity> {

    public MammothModel() {
        super(ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "mammoth"));
    }
}
