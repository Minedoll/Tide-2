package com.li64.tide.data.fishing.conditions.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record TimeRange(long min, long max) {
    public static final Codec<TimeRange> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.LONG.fieldOf("min").forGetter(TimeRange::min),
            Codec.LONG.fieldOf("max").forGetter(TimeRange::max)
    ).apply(instance, TimeRange::new));

    public boolean contains(long time) {
        if (min <= max) return time >= min && time <= max;
        else return time <= min && time >= max;
    }
}
