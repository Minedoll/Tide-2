package com.li64.tide.registries.entities.models;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class InfernoGuppyModel extends GuppyModel {
    public static final ModelLayerLocation MODEL_LOCATION = createModelLocation("inferno_guppy");

    public InfernoGuppyModel(EntityRendererProvider.Context context) {
        super(context, MODEL_LOCATION);
    }

    @Override
    public int brightnessOverride() {
        return 15;
    }
}