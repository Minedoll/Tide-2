package com.li64.tide.data.fishing.conditions.types;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum WeatherType implements StringRepresentable {
    CLEAR, RAIN, STORM;

    public static final Codec<WeatherType> CODEC = StringRepresentable.fromEnumWithMapping(
            WeatherType::values, name -> name.toLowerCase(Locale.ROOT));

    @Override
    public @NotNull String getSerializedName() {
        return toString().toLowerCase();
    }
}
