package com.li64.tide.client.gui.screens.journal.components;

import com.li64.tide.client.gui.screens.journal.FishSilhouette;
import com.li64.tide.client.gui.screens.journal.ProfileComponent;
import com.li64.tide.data.fishing.SizeData;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;

public class SilhouetteComponent extends ProfileComponent {
    private final FishSilhouette silhouette;

    public SilhouetteComponent(SizeData sizeData) {
        if (sizeData == null) this.silhouette = FishSilhouette.MEDIUM;
        else {
            double averageSize = (sizeData.typicalHighCm() + sizeData.typicalLowCm()) / 2.0;
            if (averageSize < 35.0) this.silhouette = FishSilhouette.SMALL;
            else if (averageSize < 100.0) this.silhouette = FishSilhouette.MEDIUM;
            else this.silhouette = FishSilhouette.LARGE;
        }
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, Font font, int x, int y, int mouseX, int mouseY, float partialTick) {
        int centerX = x + AREA_WIDTH / 2;
        int centerY = y + getRequiredHeight() / 2;

        graphics.blit(silhouette.texture,
                centerX - silhouette.width / 2,
                centerY - silhouette.height / 2,
                0, 0,
                silhouette.width, silhouette.height,
                silhouette.width, silhouette.height
        );
    }

    @Override
    public int getRequiredHeight() {
        return silhouette.height + 2;
    }
}
