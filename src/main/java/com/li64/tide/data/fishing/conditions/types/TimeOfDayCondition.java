package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public class TimeOfDayCondition extends FishingCondition {
    public static final MapCodec<TimeOfDayCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            TimeRange.CODEC.listOf().fieldOf("ranges").forGetter(TimeOfDayCondition::getRanges)
    ).apply(instance, TimeOfDayCondition::new));

    private final List<TimeRange> ranges;

    public TimeOfDayCondition(List<TimeRange> ranges) {
        this.ranges = ranges;
    }

    public List<TimeRange> getRanges() {
        return ranges;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.TIME_OF_DAY;
    }

    @Override
    public boolean test(FishingContext context) {
        long time = context.level().getDayTime();
        return ranges.stream().anyMatch(tr -> tr.contains(time));
    }
}
