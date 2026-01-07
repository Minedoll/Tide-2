//? if neoforge || forge {
/*package com.li64.tide.compat.seasons;

import com.teamtea.eclipticseasons.api.util.EclipticUtil;
import net.minecraft.world.level.Level;

public class EclipticSeasonsCompat {
    public static Season getSeason(Level level) {
        return switch (EclipticUtil.getNowSolarTerm(level).getSeason()) {
            case SPRING, NONE -> Season.SPRING;
            case SUMMER -> Season.SUMMER;
            case AUTUMN -> Season.FALL;
            case WINTER -> Season.WINTER;
        };
    }
}
*///?}