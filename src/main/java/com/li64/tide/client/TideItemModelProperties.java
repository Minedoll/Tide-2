package com.li64.tide.client;

import com.li64.tide.Tide;
import com.li64.tide.data.item.TideItemData;
import com.li64.tide.registries.items.FishSatchelItem;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;

public class TideItemModelProperties {
    public static final ResourceLocation CAST_PROPERTY = Tide.resource("cast");
    public static final ClampedItemPropertyFunction CAST_FUNCTION = (stack, level, player, i) -> {
        if (player == null) return 0;
        boolean flag = player.getMainHandItem() == stack;
        boolean flag1 = player.getOffhandItem() == stack;
        if (player.getMainHandItem().getItem() instanceof FishingRodItem) flag1 = false;
        return (flag || flag1) && player instanceof Player && ((Player) player).fishing != null ? 1 : 0;
    };

    public static final ResourceLocation PULLING_PROPERTY = Tide.resource("pulling");
    public static final ClampedItemPropertyFunction PULLING_FUNCTION = (stack, level, player, i) ->
            player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0f : 0.0f;
    public static final ResourceLocation PULL_PROPERTY = Tide.resource("pull");
    public static final ClampedItemPropertyFunction PULL_FUNCTION = (stack, level, player, i) -> {
        if (player == null) return 0f;
        return player.getUseItem() != stack ? 0f : (float)(stack.getUseDuration(/*? if >=1.21 {*/player/*?}*/) - player.getUseItemRemainingTicks()) / 20f;
    };

    public static final ResourceLocation SATCHEL_STATE_PROPERTY = Tide.resource("satchel_state");
    public static final ClampedItemPropertyFunction SATCHEL_STATE_FUNCTION = (stack, level, player, i) -> {
        boolean isOpen = TideItemData.FISH_SATCHEL_OPENED.getOrDefault(stack, false);
        int fishCount = FishSatchelItem.getFishCount(stack);
        if (isOpen) {
            if (fishCount > 0) return fishCount > 1 ? 1f : 0.6f;
            return 0.3f;
        }
        return 0f;
    };
}
