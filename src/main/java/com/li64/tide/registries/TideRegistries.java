package com.li64.tide.registries;

import com.li64.tide.Tide;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.li64.tide.data.fishing.modifiers.FishingModifierType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

/*? if forge*//*import net.minecraftforge.registries.IForgeRegistry;*/

public class TideRegistries {
    public static ResourceKey<Registry<FishingConditionType<?>>> FISHING_CONDITIONS_KEY = ResourceKey.createRegistryKey(Tide.resource("fishing_conditions"));
    public static ResourceKey<Registry<FishingModifierType<?>>> FISHING_MODIFIERS_KEY = ResourceKey.createRegistryKey(Tide.resource("fishing_modifiers"));

    //? if !forge {
    public static Registry<FishingConditionType<?>> FISHING_CONDITIONS = Tide.PLATFORM.createRegistry(FISHING_CONDITIONS_KEY);
    public static Registry<FishingModifierType<?>> FISHING_MODIFIERS = Tide.PLATFORM.createRegistry(FISHING_MODIFIERS_KEY);
    //?} else {
    /*// I hate forge with an intense passion
    public static IForgeRegistry<FishingConditionType<?>> FISHING_CONDITIONS;
    public static IForgeRegistry<FishingModifierType<?>> FISHING_MODIFIERS;
    *///?}

    public static void init() {
        FishingConditionType.register();
        FishingModifierType.register();
    }
}