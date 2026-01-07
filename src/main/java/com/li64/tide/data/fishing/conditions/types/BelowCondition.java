package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class BelowCondition extends FishingCondition implements DepthRangeHolder {
    public static final MapCodec<BelowCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("y").forGetter(BelowCondition::getMaxY)
    ).apply(instance, BelowCondition::new));

    private final int y;

    public BelowCondition(int y) {
        this.y = y;
    }

    @Override
    public int getMaxY() {
        return y;
    }

    @Override
    public boolean hasLowerBound() {
        return false;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.BELOW;
    }

    @Override
    public boolean test(FishingContext context) {
        return context.blockPos().getY() < this.y;
    }
}
