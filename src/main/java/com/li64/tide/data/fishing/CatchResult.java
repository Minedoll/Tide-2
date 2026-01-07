package com.li64.tide.data.fishing;

import com.li64.tide.data.fishing.selector.FishingEntry;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public record CatchResult(List<ItemStack> items, Optional<FishingEntry> entry) {
    public static CatchResult of(List<ItemStack> items, FishingEntry entry) {
        return new CatchResult(items, Optional.of(entry));
    }

    public static CatchResult empty(FishingEntry entry) {
        return new CatchResult(List.of(), Optional.of(entry));
    }

    public static CatchResult empty() {
        return new CatchResult(List.of(), Optional.empty());
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean isFish() {
        return entry.map(entry -> entry instanceof FishData).orElse(false);
    }

    public boolean isCrate() {
        return entry.map(entry -> entry instanceof CrateData).orElse(false);
    }

    public boolean isLoot() {
        return entry.map(entry -> entry instanceof FishingLootData).orElse(false);
    }
}
