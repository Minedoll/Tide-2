//? if neoforge {
/*package com.li64.tide.mixin.stardewfishing;

import com.bonker.stardewfishing.common.FishingHookLogic;
import com.bonker.stardewfishing.server.data.FishingHookAttachment;
import com.li64.tide.util.TideUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FishingHookLogic.class)
public class FishingHookLogicMixin {
    @Inject(method = "modifyRewards(Lnet/minecraft/server/level/ServerPlayer;DI)V", at = @At(value = "HEAD"))
    private static void giveRewards(ServerPlayer player, double accuracy, int qualityBoost, CallbackInfo ci) {
        if (player.fishing == null) return;
        List<ItemStack> rewards = FishingHookAttachment.get(player.fishing).getRewards();
        rewards.forEach(stack -> TideUtils.tryLogCatch(stack, player));
    }
}
*///?} else if forge {
/*package com.li64.tide.mixin.stardewfishing;

import com.bonker.stardewfishing.common.FishingHookLogic;
import com.li64.tide.Tide;
import com.li64.tide.util.TideUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(FishingHookLogic.class)
public class FishingHookLogicMixin {
    @Inject(method = "modifyRewards(Lnet/minecraft/server/level/ServerPlayer;DI)V", at = @At(value = "HEAD"), remap = false)
    private static void modifyRewards(ServerPlayer player, double accuracy, int qualityBoost, CallbackInfo ci) {
        if (player.fishing == null) return;
        List<ItemStack> rewards = FishingHookLogic.getStoredRewards(player.fishing).orElse(new ArrayList<>());
        rewards.forEach(stack -> TideUtils.tryLogCatch(stack, player));
    }
}
*///?}