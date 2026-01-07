package com.li64.tide.data.fishing.modifiers.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.modifiers.FishingModifier;
import com.li64.tide.data.fishing.modifiers.FishingModifierType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class TemperatureModifier extends FishingModifier {
    public static final MapCodec<TemperatureModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.FLOAT.fieldOf("preferred_temperature").forGetter(TemperatureModifier::getPreferred),
            Codec.FLOAT.fieldOf("temperature_tolerance").forGetter(TemperatureModifier::getTolerance)
    ).apply(instance, TemperatureModifier::new));

    private final float preferred;
    private final float tolerance;

    public TemperatureModifier(float preferred, float tolerance) {
        this.preferred = preferred;
        this.tolerance = tolerance;
    }

    public float getPreferred() {
        return this.preferred;
    }
    public float getTolerance() {
        return this.tolerance;
    }

    @Override
    public FishingModifierType<?> type() {
        return FishingModifierType.TEMPERATURE;
    }

    @Override
    public double apply(double weight, FishingContext context) {
        double inner = Math.abs((context.temperature() - preferred) / tolerance);
        double scalar = Math.max(0, -(inner * inner) + 1);
        return weight * scalar;
    }
}
