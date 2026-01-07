package com.li64.tide.client.gui.screens.journal.components;

import com.li64.tide.client.gui.screens.journal.ProfileComponent;
import com.li64.tide.util.TideUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public class HorizontalLineComponent extends ProfileComponent {
    private final boolean top;

    public HorizontalLineComponent(boolean top) {
        this.top = top;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, Font font, int x, int y, int mouseX, int mouseY, float partialTick) {
        //? if >=1.21 {
        graphics.blitSprite(top ? ProfileComponent.LINE_TOP : ProfileComponent.LINE_BOTTOM, x, y + 4, ProfileComponent.AREA_WIDTH, 2);
        //?} else {
/*//        graphics.blit(top ? ProfileComponent.LINE_TOP : ProfileComponent.LINE_BOTTOM, x, y + 4, 0, 0,
//                ProfileComponent.AREA_WIDTH, 2, 71, 2);
        TideUtils.blitNineSliced(graphics, top ? ProfileComponent.LINE_TOP : ProfileComponent.LINE_BOTTOM,
                x, y + 4, ProfileComponent.AREA_WIDTH, 2, 3, 0,
                71, 2, 0, 0, 71, 2);
        *///?}
    }

    @Override
    public int getRequiredHeight() {
        return 10;
    }
}
