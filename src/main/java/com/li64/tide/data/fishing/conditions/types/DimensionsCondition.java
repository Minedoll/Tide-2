package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.List;

public class DimensionsCondition extends FishingCondition {
    public static final MapCodec<DimensionsCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Level.RESOURCE_KEY_CODEC.listOf().fieldOf("dimensions").forGetter(DimensionsCondition::getDimensions)
    ).apply(instance, DimensionsCondition::new));

    private final List<ResourceKey<Level>> dimensions;

    public DimensionsCondition(List<ResourceKey<Level>> dimensions) {
        this.dimensions = dimensions;
    }

    public List<ResourceKey<Level>> getDimensions() {
        return dimensions;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.DIMENSION;
    }

    @Override
    public boolean test(FishingContext context) {
        return this.dimensions.stream().anyMatch(dim -> context.dimension() == dim);
    }

    public boolean isOverworldOnly() {
        return dimensions.size() == 1 && dimensions.get(0) == Level.OVERWORLD;
    }
}
