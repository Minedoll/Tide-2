package com.li64.tide.mixin;

import com.li64.tide.registries.items.TideFishingRodItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    //? if >=1.21 {
        @Shadow public abstract int getDamageValue();
        @Shadow public abstract int getMaxDamage();
        //? if fabric {
        @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;setDamageValue(I)V", shift = At.Shift.AFTER),
                method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V")
        public void hurtAndBreak(int damage, ServerLevel level, ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci) {
            if (getDamageValue() >= getMaxDamage() && getItem() instanceof TideFishingRodItem rod) rod.onItemBroken((ItemStack)(Object)this, player);
        }
        //?} else {
        /*@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;setDamageValue(I)V"),
                method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V")
        public void hurtAndBreak(int damage, ServerLevel level, LivingEntity entity, Consumer<Item> onBreak, CallbackInfo ci) {
            if (!(entity instanceof ServerPlayer player)) return;
            if (getDamageValue() >= getMaxDamage() && getItem() instanceof TideFishingRodItem rod) rod.onItemBroken((ItemStack)(Object)this, player);
        }
        *///?}
    //?} else {
    /*@Inject(at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"), method = "hurtAndBreak")
    public <T extends LivingEntity> void hurtAndBreak(int amount, T entity, Consumer<T> onBroken, CallbackInfo ci) {
        if (getItem() instanceof TideFishingRodItem rod && entity instanceof ServerPlayer player) rod.onItemBroken((ItemStack)(Object)this, player);
    }
    *///?}
}