//? if <1.21 {
/*package com.li64.tide.data.item;

import com.li64.tide.data.ItemDataKey;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public record NbtItemDataKey<T>(String key, Function<CompoundTag, T> reader,
                                BiConsumer<CompoundTag, T> writer) implements ItemDataKey<T> {
    @Override
    public T get(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null && tag.contains(key) ? reader.apply(tag) : null;
    }

    @Override
    public T getOrDefault(ItemStack stack, T defaultValue) {
        T value = this.get(stack);
        return value != null ? value : defaultValue;
    }

    @Override
    public Optional<T> getOptional(ItemStack stack) {
        return Optional.ofNullable(this.get(stack));
    }

    @Override
    public void set(ItemStack stack, T value) {
        CompoundTag tag = stack.getOrCreateTag();
        writer.accept(tag, value);
    }

    @Override
    public void remove(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null) tag.remove(key);
    }

    @Override
    public boolean isPresent(ItemStack stack) {
        return this.get(stack) != null;
    }
}
*///?}