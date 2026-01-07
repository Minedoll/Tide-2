package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class OpenWaterCondition extends FishingCondition {
    public static final MapCodec<OpenWaterCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.BOOL.fieldOf("in_open_water").forGetter(OpenWaterCondition::getOpenWater)
    ).apply(instance, OpenWaterCondition::new));

    private final boolean inOpenWater;

    public OpenWaterCondition(boolean inOpenWater) {
        this.inOpenWater = inOpenWater;
    }

    public boolean getOpenWater() {
        return inOpenWater;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.OPEN_WATER;
    }

    @Override
    public boolean test(FishingContext context) {
        if (context.hook() == null) return false;
        return context.hook().isOpenWaterFishing() == inOpenWater;
    }
}
