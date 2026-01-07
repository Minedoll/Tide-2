package com.li64.tide.registries.items;

import com.li64.tide.Tide;
import com.li64.tide.network.messages.VoidseekerRespawnMsg;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class VoidseekerItem extends SimpleTooltipItem {
    public VoidseekerItem(Properties properties) {
        super(properties);
    }

    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        if (!(entity instanceof ServerPlayer player)) return result;
        player.getCooldowns().addCooldown(this, 20);
        player.wonGame = true;
        Tide.NETWORK.sendToPlayer(new VoidseekerRespawnMsg(), player);
        return result;
    }

    @Override
    public void addTooltip(ItemStack stack, Consumer<Component> tooltip) {
        Style gray = Component.empty().getStyle().withColor(ChatFormatting.GRAY);
        tooltip.accept(Component.translatable("item.tide.voidseeker.desc").setStyle(gray));
    }
}