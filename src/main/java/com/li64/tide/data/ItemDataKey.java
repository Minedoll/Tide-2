package com.li64.tide.data;

import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public interface ItemDataKey<T> {
    T get(ItemStack stack);
    T getOrDefault(ItemStack stack, T defaultValue);
    Optional<T> getOptional(ItemStack stack);
    void set(ItemStack stack, T value);
    void remove(ItemStack stack);
    boolean isPresent(ItemStack stack);
}
