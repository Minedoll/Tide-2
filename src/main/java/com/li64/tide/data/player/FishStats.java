package com.li64.tide.data.player;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;

import java.util.Optional;

public class FishStats {
    public static final Codec<FishStats> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("amount_caught").forGetter(FishStats::getAmountCaught),
            CatchTimestamp.CODEC.optionalFieldOf("initial_catch_date").forGetter(FishStats::getInitialCatchDate),
            Codec.DOUBLE.fieldOf("largest_catch").forGetter(FishStats::getLargestCatch),
            Codec.DOUBLE.fieldOf("smallest_catch").forGetter(FishStats::getSmallestCatch)
    ).apply(instance, FishStats::new));

    private int amountCaught;
    private Optional<CatchTimestamp> initialCatchDate;
    private double largestCatch;
    private double smallestCatch;

    public FishStats() {
        this(0, Optional.empty(), 0, Double.POSITIVE_INFINITY);
    }

    public FishStats(int amountCaught, Optional<CatchTimestamp> initialCatchDate, double largestCatch, double smallestCatch) {
        this.amountCaught = amountCaught;
        this.initialCatchDate = initialCatchDate;
        this.largestCatch = largestCatch;
        this.smallestCatch = smallestCatch;
    }

    public int getAmountCaught() {
        return amountCaught;
    }

    public Optional<CatchTimestamp> getInitialCatchDate() {
        return initialCatchDate;
    }

    public double getLargestCatch() {
        return largestCatch;
    }

    public double getSmallestCatch() {
        return smallestCatch;
    }

    public boolean isEmpty() {
        return amountCaught == 0;
    }

    public void logCatch(CatchTimestamp timestamp, double size) {
        this.amountCaught++;
        if (initialCatchDate.isEmpty()) this.initialCatchDate = Optional.of(timestamp);
        if (size <= 0.0 || size > largestCatch) this.largestCatch = size;
        if (size <= 0.0 || size < smallestCatch) this.smallestCatch = size;
    }
}
