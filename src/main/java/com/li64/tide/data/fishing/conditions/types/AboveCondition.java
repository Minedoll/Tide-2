package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class AboveCondition extends FishingCondition implements DepthRangeHolder {
    public static final MapCodec<AboveCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("y").forGetter(AboveCondition::getMinY)
    ).apply(instance, AboveCondition::new));

    private final int y;

    public AboveCondition(int y) {
        this.y = y;
    }

    @Override
    public int getMinY() {
        return y;
    }

    @Override
    public boolean hasUpperBound() {
        return false;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.ABOVE;
    }

    @Override
    public boolean test(FishingContext context) {
        return context.blockPos().getY() > this.y;
    }
}
