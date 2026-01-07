package com.li64.tide.compat.seasons;

import net.minecraft.world.level.Level;
import sereneseasons.api.season.SeasonHelper;

public class SereneSeasonsCompat {
    public static Season getSeason(Level level) {
        return switch (SeasonHelper.getSeasonState(level).getSeason()) {
            case SPRING -> Season.SPRING;
            case SUMMER -> Season.SUMMER;
            case AUTUMN -> Season.FALL;
            case WINTER -> Season.WINTER;
        };
    }
}
