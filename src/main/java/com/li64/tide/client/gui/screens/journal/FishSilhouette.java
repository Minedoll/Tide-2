package com.li64.tide.client.gui.screens.journal;

import com.li64.tide.Tide;
import net.minecraft.resources.ResourceLocation;

public enum FishSilhouette {
    SMALL("silhouette_small", 16, 11),
    MEDIUM("silhouette_medium", 25, 16),
    LARGE("silhouette_large", 34, 24);

    public final ResourceLocation texture;
    public final int width;
    public final int height;

    FishSilhouette(String textureName, int width, int height) {
        this(Tide.resource("textures/gui/journal/" + textureName + ".png"), width, height);
    }

    FishSilhouette(ResourceLocation texture, int width, int height) {
        this.texture = texture;
        this.width = width;
        this.height = height;
    }
}