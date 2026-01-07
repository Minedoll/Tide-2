//? if fabric {
package com.li64.tide.compat.seasons;

import io.github.lucaargolo.seasons.FabricSeasons;
import net.minecraft.world.level.Level;

public class FabricSeasonsCompat {
    public static Season getSeason(Level level) {
        return switch (FabricSeasons.getCurrentSeason(level)) {
            case SPRING -> Season.SPRING;
            case SUMMER -> Season.SUMMER;
            case FALL -> Season.FALL;
            case WINTER -> Season.WINTER;
        };
    }
}
//?}