package com.li64.tide.data.fishing.modifiers.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.modifiers.FishingModifier;
import com.li64.tide.data.fishing.modifiers.FishingModifierType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class ConditionalModifier extends FishingModifier {
    public static final MapCodec<ConditionalModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            FishingCondition.CODEC.fieldOf("if").forGetter(ConditionalModifier::getCondition),
            Codec.FLOAT.fieldOf("multiplier").forGetter(ConditionalModifier::getMultiplier)
    ).apply(instance, ConditionalModifier::new));

    private final FishingCondition condition;
    private final float multiplier;

    public ConditionalModifier(FishingCondition condition, float multiplier) {
        this.condition = condition;
        this.multiplier = multiplier;
    }

    public FishingCondition getCondition() {
        return this.condition;
    }
    public float getMultiplier() {
        return this.multiplier;
    }

    @Override
    public FishingModifierType<?> type() {
        return FishingModifierType.CONDITIONAL;
    }

    @Override
    public double apply(double weight, FishingContext context) {
        return condition.test(context) ? weight * multiplier : weight;
    }
}
