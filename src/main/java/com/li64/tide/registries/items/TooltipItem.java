package com.li64.tide.registries.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public interface TooltipItem {
    void addTooltip(ItemStack stack, Consumer<Component> tooltip);
}
