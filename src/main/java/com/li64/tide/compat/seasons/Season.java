package com.li64.tide.compat.seasons;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum Season implements StringRepresentable {
    SPRING("spring"),
    SUMMER("summer"),
    FALL("fall"),
    WINTER("winter");

    public static final Codec<Season> CODEC = StringRepresentable.fromEnum(Season::values);

    private final String id;

    Season(String id) {
        this.id = id;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.id;
    }
}
