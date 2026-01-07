package com.li64.tide.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {
    //? if >=1.21 {
    @Inject(at = @At("HEAD"), method = "draw", cancellable = true)
    private static void draw(ItemStack weapon, ItemStack ammo, LivingEntity shooter, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (shooter.getRandom().nextFloat() < 0.6f) cir.setReturnValue(List.of(Items.ARROW.getDefaultInstance()));
    }
    //?}
}