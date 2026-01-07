package com.li64.tide.client;

import net.minecraft.client.renderer.ShaderInstance;

public class TideCoreShaders {
    public static ShaderInstance FULL_WHITE;
    public static ShaderInstance FULL_WHITE_ITEM;

    public static ShaderInstance fullWhite() {
        return FULL_WHITE;
    }

    public static ShaderInstance fullWhiteItem() {
        return FULL_WHITE_ITEM;
    }
}
