package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class LuckCondition extends FishingCondition {
    public static final MapCodec<LuckCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.optionalFieldOf("min", 0).forGetter(LuckCondition::getMinLuck),
            Codec.INT.optionalFieldOf("max", Integer.MAX_VALUE).forGetter(LuckCondition::getMaxLuck)
    ).apply(instance, LuckCondition::new));

    private final int min;
    private final int max;

    public static LuckCondition minimum(int min) {
        return new LuckCondition(min, Integer.MAX_VALUE);
    }

    public static LuckCondition maximum(int max) {
        return new LuckCondition(0, max);
    }

    public LuckCondition(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMinLuck() {
        return min;
    }

    public int getMaxLuck() {
        return max;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.LUCK;
    }

    @Override
    public boolean test(FishingContext context) {
        return context.luck() >= this.min && context.luck() <= this.max;
    }
}
