package com.li64.tide.data.fishing.modifiers;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.registries.TideRegistries;
import com.mojang.serialization.Codec;

public abstract class FishingModifier {
    public static final Codec<FishingModifier> CODEC = TideRegistries.FISHING_MODIFIERS/*? if !forge {*/.byNameCodec()/*?} else*//*.getCodec()*/
            /*? if >= 1.21 {*/.dispatch(FishingModifier::type, FishingModifierType::codec);
            /*?} else*//*.dispatch(FishingModifier::type, t -> t.codec().codec());*/

    public abstract FishingModifierType<?> type();

    public abstract double apply(double weight, FishingContext context);
}