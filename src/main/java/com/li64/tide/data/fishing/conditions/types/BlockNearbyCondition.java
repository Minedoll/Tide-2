package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;

public class BlockNearbyCondition extends FishingCondition {
    public static final MapCodec<BlockNearbyCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            TagKey.codec(Registries.BLOCK).fieldOf("blocks").forGetter(BlockNearbyCondition::getTag),
            Codec.INT.fieldOf("radius").forGetter(BlockNearbyCondition::getRadius)
    ).apply(instance, BlockNearbyCondition::new));

    private final TagKey<Block> tag;
    private final int radius;

    public BlockNearbyCondition(TagKey<Block> tag, int radius) {
        this.tag = tag;
        this.radius = radius;
    }

    public static BlockNearbyCondition inRadius(TagKey<Block> tag, int radius) {
        return new BlockNearbyCondition(tag, radius);
    }

    public TagKey<Block> getTag() {
        return tag;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.BLOCK_NEARBY;
    }

    @Override
    public boolean test(FishingContext context) {
        AABB area = AABB.ofSize(context.pos(), 1.0, 1.0, 1.0).inflate(radius);
        return context.level().getBlockStates(area).anyMatch(state -> state.is(tag));
    }
}
