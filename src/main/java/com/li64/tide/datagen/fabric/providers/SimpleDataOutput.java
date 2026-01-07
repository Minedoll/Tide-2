package com.li64.tide.datagen.fabric.providers;

import com.li64.tide.Tide;
import net.minecraft.resources.ResourceLocation;

public interface SimpleDataOutput<T> {
    default void accept(String path, T data) {
        accept(Tide.resource(path), data);
    }

    void accept(ResourceLocation path, T data);
}