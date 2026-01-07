package com.li64.tide.compat.seasons;

import com.li64.tide.Tide;
import net.minecraft.world.level.Level;

import java.util.List;

public class SeasonsCompat {
    private static final List<String> SEASON_MODS = List.of("seasons", "eclipticseasons", "sereneseasons");

    public static Season getSeason(Level level) {
        /*? if fabric*/if (Tide.PLATFORM.isModLoaded("seasons")) return FabricSeasonsCompat.getSeason(level);
        /*? if neoforge || forge*//*if (Tide.PLATFORM.isModLoaded("eclipticseasons")) return EclipticSeasonsCompat.getSeason(level);*/
        if (Tide.PLATFORM.isModLoaded("sereneseasons")) return SereneSeasonsCompat.getSeason(level);
        return Season.SPRING;
    }

    public static boolean isActive() {
        return SEASON_MODS.stream().anyMatch(Tide.PLATFORM::isModLoaded);
    }
}