package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class DepthRangeCondition extends FishingCondition implements DepthRangeHolder {
    public static final MapCodec<DepthRangeCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("min_y").forGetter(DepthRangeCondition::getMinY),
            Codec.INT.fieldOf("max_y").forGetter(DepthRangeCondition::getMaxY)
    ).apply(instance, DepthRangeCondition::new));

    private final int minY;
    private final int maxY;

    public DepthRangeCondition(int minY, int maxY) {
        this.minY = minY;
        this.maxY = maxY;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.DEPTH_RANGE;
    }

    @Override
    public boolean test(FishingContext context) {
        return context.blockPos().getY() >= this.minY && context.blockPos().getY() <= this.maxY;
    }
}
