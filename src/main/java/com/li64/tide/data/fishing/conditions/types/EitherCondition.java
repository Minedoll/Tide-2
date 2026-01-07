package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class EitherCondition extends FishingCondition {
    public static final MapCodec<EitherCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            FishingCondition.CODEC.fieldOf("a").forGetter(EitherCondition::getConditionA),
            FishingCondition.CODEC.fieldOf("b").forGetter(EitherCondition::getConditionB)
    ).apply(instance, EitherCondition::new));

    private final FishingCondition a;
    private final FishingCondition b;

    public EitherCondition(FishingCondition a, FishingCondition b) {
        this.a = a;
        this.b = b;
    }

    public FishingCondition getConditionA() {
        return a;
    }
    public FishingCondition getConditionB() {
        return b;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.EITHER;
    }

    @Override
    public boolean test(FishingContext context) {
        return a.test(context) || b.test(context);
    }
}
