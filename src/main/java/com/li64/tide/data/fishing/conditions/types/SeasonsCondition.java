package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.compat.seasons.Season;
import com.li64.tide.compat.seasons.SeasonsCompat;
import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Arrays;
import java.util.List;

public class SeasonsCondition extends FishingCondition {
    public static final MapCodec<SeasonsCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Season.CODEC.listOf().optionalFieldOf("seasons", List.of(Season.values())).forGetter(SeasonsCondition::getSeasons)
    ).apply(instance, SeasonsCondition::new));

    private final List<Season> seasons;

    public SeasonsCondition(List<Season> seasons) {
        this.seasons = seasons;
    }

    public static SeasonsCondition fromSeasons(Season... seasons) {
        return new SeasonsCondition(Arrays.stream(seasons).toList());
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.SEASONS;
    }

    @Override
    public boolean test(FishingContext context) {
        if (!SeasonsCompat.isActive()) return true;
        return seasons.stream().anyMatch(season -> season == context.season());
    }
}
