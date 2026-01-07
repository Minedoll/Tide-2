package com.li64.tide.data;

import com.google.common.collect.ImmutableList;
import com.li64.tide.data.commands.TestType;
import com.li64.tide.data.fishing.CatchResult;
import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.selector.CrateSelector;
import com.li64.tide.data.fishing.selector.FishSelector;
import com.li64.tide.data.fishing.selector.FishingEntry;
import com.li64.tide.data.fishing.selector.FishingRandomSelector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TideFishingManager {
    private final List<FishingEntry> entries;
    private final FishSelector fishSelector;
    private final CrateSelector crateSelector;

    public TideFishingManager() {
        this.fishSelector = new FishSelector();
        this.crateSelector = new CrateSelector();
        ImmutableList.Builder<FishingEntry> builder = ImmutableList.builder();
        builder.addAll(TideData.FISHING_LOOT.get().values());
        builder.add(this.fishSelector);
        builder.add(this.crateSelector);
        this.entries = builder.build();
    }

    public CatchResult selectCatch(FishingContext context) {
        return FishingRandomSelector.select(entries, context);
    }

    public FishSelector getFishSelector() {
        return this.fishSelector;
    }

    public CrateSelector getCrateSelector() {
        return this.crateSelector;
    }

    public Map<FishingEntry, Double> test(FishingContext context, TestType type) {
        Map<FishingEntry, Double> results = new HashMap<>();
        if (type == TestType.FISH) this.fishSelector.getResult(context, results, entry -> entry.matchesTestType(type));
        else if (type == TestType.CRATES) this.crateSelector.getResult(context, results, entry -> entry.matchesTestType(type));
        else FishingRandomSelector.select(entries, context, results, entry -> entry.matchesTestType(type));
        return results;
    }
}
