package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class NotCondition extends FishingCondition {
    public static final MapCodec<NotCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            FishingCondition.CODEC.fieldOf("a").forGetter(NotCondition::getConditionA)
    ).apply(instance, NotCondition::new));

    private final FishingCondition condition;

    public NotCondition(FishingCondition condition) {
        this.condition = condition;
    }

    public FishingCondition getConditionA() {
        return condition;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.NOT;
    }

    @Override
    public boolean test(FishingContext context) {
        return !condition.test(context);
    }
}
