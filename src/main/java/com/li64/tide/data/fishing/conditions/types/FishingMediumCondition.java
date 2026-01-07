package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.li64.tide.data.fishing.mediums.FishingMedium;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class FishingMediumCondition extends FishingCondition {
    public static final MapCodec<FishingMediumCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("fluid").forGetter(FishingMediumCondition::getMediumId)
    ).apply(instance, FishingMediumCondition::new));

    private final String mediumId;

    public FishingMediumCondition(FishingMedium medium) {
        this(medium.id().getPath());
    }

    public FishingMediumCondition(String mediumId) {
        this.mediumId = mediumId;
    }

    public String getMediumId() {
        return mediumId;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.MEDIUM;
    }

    @Override
    public boolean test(FishingContext context) {
        return context.medium().equals(mediumId);
    }
}
