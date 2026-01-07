package com.li64.tide.data.fishing;

import com.li64.tide.Tide;
import com.li64.tide.compat.seasons.Season;
import com.li64.tide.registries.entities.misc.fishing.TideFishingHook;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record FishingContext(
        ServerLevel level,
        @Nullable TideFishingHook hook,
        RandomSource rng,
        Vec3 pos,
        BlockPos blockPos,
        int luck,
        String medium,
        ItemStack activeBait,
        Holder<Biome> exactBiome,
        Holder<Biome> nearestBiome,
        ResourceKey<Level> dimension,
        float temperature,
        int moonPhase,
        Season season
) {
    @Override
    public @NotNull String toString() {
        return "FishingContext{" +
                "level=" + level +
                ", hook=" + hook +
                ", rng=" + rng +
                ", pos=" + pos +
                ", blockPos=" + blockPos +
                ", luck=" + luck +
                ", medium='" + medium + '\'' +
                ", activeBait=" + activeBait +
                ", exactBiome=" + exactBiome +
                ", nearestBiome=" + nearestBiome +
                ", dimension=" + dimension +
                ", temperature=" + temperature +
                ", moonPhase=" + moonPhase +
                ", season=" + season +
                '}';
    }

    public LootParams createFishingLootParams() {
        if (hook == null) {
            Tide.LOG.error("Tried to create loot params for fishing context with a null hook");
            return new LootParams.Builder(level).create(LootContextParamSets.FISHING);
        }
        return new LootParams.Builder(level)
                .withParameter(LootContextParams.ORIGIN, hook.position())
                .withParameter(LootContextParams.TOOL, hook.rod())
                .withParameter(LootContextParams.THIS_ENTITY, hook)
                .withLuck(luck)
                .create(LootContextParamSets.FISHING);
    }
}
