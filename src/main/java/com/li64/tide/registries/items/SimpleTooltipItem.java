package com.li64.tide.registries.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class SimpleTooltipItem extends Item implements TooltipItem {
    public SimpleTooltipItem(Properties properties) {
        super(properties);
    }

    //? if >=1.21.1 {
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> lines, TooltipFlag flag) {
        super.appendHoverText(stack, context, lines, flag);
        this.addTooltip(stack, lines::add);
    }
    //?} else {
    /*@Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lines, TooltipFlag flag) {
        super.appendHoverText(stack, level, lines, flag);
        this.addTooltip(stack, lines::add);
    }
    *///?}
}
