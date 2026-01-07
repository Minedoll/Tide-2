package com.li64.tide.registries.entities.models;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class StarfishModel extends FishModel {
    public static final ModelLayerLocation MODEL_LOCATION = createModelLocation("starfish");

    public StarfishModel(EntityRendererProvider.Context context) {
        super(context, MODEL_LOCATION);
    }

    @Override
    public float swimAnimScale() {
        return 0.0f;
    }

    @Override
    public boolean flipInAir() {
        return false;
    }

    @Override
    public double xTiltScale() {
        return 0.0f;
    }

    public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.99F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, -2.0F, -5.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        legs.addOrReplaceChild("leg_ne_r1", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, -2.0F, -5.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.2566F, 0.0F));
        legs.addOrReplaceChild("leg_se_r1", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, -2.0F, -5.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.5133F, 0.0F));
        legs.addOrReplaceChild("leg_sw_r1", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, -2.0F, -5.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.5133F, 0.0F));
        legs.addOrReplaceChild("leg_nw_r1", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, -2.0F, -5.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.2566F, 0.0F));

        return LayerDefinition.create(mesh, 16, 16);
	}
}