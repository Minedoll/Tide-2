package com.li64.tide.data.fishing.conditions.types;

public interface DepthRangeHolder {
    default int getMinY() { return 0; }

    default int getMaxY() { return 0; }

    default boolean hasUpperBound() { return true; }

    default boolean hasLowerBound() { return true; }
}
