package com.li64.tide.registries;

import com.li64.tide.Tide;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class TideParticleTypes {
    public static final SimpleParticleType VOID_RIPPLE_LARGE = register("void_ripple_large", new SimpleParticleType(true));
    public static final SimpleParticleType VOID_RIPPLE_SMALL = register("void_ripple_small", new SimpleParticleType(true));
    public static final SimpleParticleType MAGIC_CHAIN = register("magic_chain", new SimpleParticleType(true));

    public static SimpleParticleType register(String key, SimpleParticleType particleType) {
        return Tide.PLATFORM.register(BuiltInRegistries.PARTICLE_TYPE, Tide.resource(key), particleType);
    }

    public static void init() {}
}
