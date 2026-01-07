package com.li64.tide.registries.entities.models;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class AlphaFishModel extends FishModel {
    public static final ModelLayerLocation MODEL_LOCATION = createModelLocation("alpha_fish");

    public AlphaFishModel(EntityRendererProvider.Context context) {
        super(context, MODEL_LOCATION);
        this.addSwimAnimation("front/head", 0.6f, -0.08f);
        this.addSwimAnimation("tail", 0.6f, 0.25f);
    }

    public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

        PartDefinition front = root.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -2.5F, 1.5F, 2.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(0.5F, -4.5F, 0.5F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 22.5F, -2.5F));

        PartDefinition head = front.addOrReplaceChild("head", CubeListBuilder.create().texOffs(12, 11).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -0.5F, 1.5F));
        head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

        front.addOrReplaceChild("fin_l", CubeListBuilder.create().texOffs(18, 4).addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.5F, 1.5F, 0.0F, 0.0F, 0.7854F));
        front.addOrReplaceChild("fin_r", CubeListBuilder.create().texOffs(18, 6).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, 1.5F, 0.0F, 0.0F, -0.7854F));

        root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 6.0F));

        return LayerDefinition.create(mesh, 32, 32);
	}
}