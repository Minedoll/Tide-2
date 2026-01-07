package com.li64.tide.data.fishing.selector;

import com.li64.tide.data.commands.TestType;
import com.li64.tide.data.fishing.CatchResult;
import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.modifiers.FishingModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface FishingEntry {
    double weight(FishingContext context);

    boolean shouldKeep(FishingContext context);

    CatchResult getResult(FishingContext context);

    default CatchResult getResult(FishingContext context, Map<FishingEntry, Double> results, Predicate<FishingEntry> entryTest) {
        return this.getResult(context);
    }

    static double modifyWeight(double baseWeight, double quality,
                               FishingContext context) {
        return baseWeight + quality * context.luck();
    }

    default double modifyWeight(double baseWeight, double quality,
                               List<FishingModifier> modifiers,
                               FishingContext context) {
        double weight = modifyWeight(baseWeight, quality, context);
        if (weight <= 0) return 0;
        if (modifiers.isEmpty()) return weight;
        for (var mod : modifiers) {
            weight = mod.apply(weight, context);
            if (weight <= 0) return 0;
        }
        return weight;
    }

    default CatchResult createResult(ItemStack stack) {
        return this.createResult(List.of(stack));
    }

    default CatchResult createResult(List<ItemStack> items) {
        return CatchResult.of(items, this);
    }

    default CatchResult emptyResult() {
        return CatchResult.empty(this);
    }

    default boolean matchesTestType(TestType type) {
        return type == TestType.LOOT;
    }

    default MutableComponent getTestKey() {
        return Component.translatable("commands.fishing.entries.unspecified");
    };
}
