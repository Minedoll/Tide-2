package com.li64.tide.client.gui;

import com.li64.tide.client.gui.overlays.CastBarOverlay;
import com.li64.tide.client.gui.overlays.CatchMinigameOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class TideGuiOverlays {
    public static void render(GuiGraphics guiGraphics) {
        /*? if >=1.21 {*/float dt = Minecraft.getInstance().getTimer().getRealtimeDeltaTicks();
        /*?} else*//*float dt = Minecraft.getInstance().getDeltaFrameTime();*/
        CatchMinigameOverlay.render(guiGraphics, dt);
        CastBarOverlay.render(guiGraphics, dt);
    }
}
