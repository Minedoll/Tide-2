//? if >=1.21 {
package com.li64.tide.registries.items;

import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.item.TideDataComponents;
import com.li64.tide.data.TideTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class FishSatchelItem extends SimpleTooltipItem {
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);

    public FishSatchelItem(Item.Properties properties) {
        super(properties);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean canPutInSatchel(ItemStack stack) {
        return stack.is(TideTags.Items.FISH) || FishData.get(stack).isPresent();
    }

    public static int getFishCount(ItemStack stack) {
        BundleContents contents = stack.get(DataComponents.BUNDLE_CONTENTS);
        if (contents == null) return 0;
        return contents.size();
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand used) {
        ItemStack satchel = player.getItemInHand(used);
        satchel.set(TideDataComponents.FISH_SATCHEL_OPENED, !satchel.getOrDefault(TideDataComponents.FISH_SATCHEL_OPENED, false));
        player.playSound(SoundEvents.ARMOR_EQUIP_LEATHER.value(), 0.8f, 0.8f + player.level().getRandom().nextFloat() * 0.4f);
        return InteractionResultHolder.success(satchel);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {
        if (action != ClickAction.SECONDARY) return false;
        BundleContents contents = stack.get(DataComponents.BUNDLE_CONTENTS);
        ItemStack other = slot.getItem();
        if (contents == null) return false;
        BundleContents.Mutable mutable = new BundleContents.Mutable(contents);

        if (other.isEmpty()) {
            this.playRemoveOneSound(player);
            ItemStack removed = mutable.removeOne();
            if (removed != null) mutable.tryInsert(slot.safeInsert(removed));
        }
        else if (other.getItem().canFitInsideContainerItems()) {
            if (!canPutInSatchel(other)) return false;
            int i = mutable.tryTransfer(slot, player);
            if (i > 0) this.playInsertSound(player);
        }

        stack.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
        return true;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (!(action == ClickAction.SECONDARY && slot.allowModification(player))) return false;
        return overrideOtherStackedOnMe(stack, other, player, access);
    }

    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Player player, SlotAccess access) {
        BundleContents contents = stack.get(DataComponents.BUNDLE_CONTENTS);
        if (contents == null) return false;
        BundleContents.Mutable mutable = new BundleContents.Mutable(contents);

        if (other.isEmpty()) {
            ItemStack removed = mutable.removeOne();
            if (removed != null) {
                this.playRemoveOneSound(player);
                access.set(removed);
            }
        }
        else {
            if (!canPutInSatchel(other)) return false;
            if (mutable.tryInsert(other) > 0) this.playInsertSound(player);
        }

        stack.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
        return true;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return contents.weight().compareTo(Fraction.ZERO) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return Math.min(1 + Mth.mulAndTruncate(contents.weight(), 12), 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return BAR_COLOR;
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return !stack.has(DataComponents.HIDE_TOOLTIP) && !stack.has(DataComponents.HIDE_ADDITIONAL_TOOLTIP)
                ? Optional.ofNullable(stack.get(DataComponents.BUNDLE_CONTENTS)).map(BundleTooltip::new)
                : Optional.empty();
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> lines, TooltipFlag flag) {
        BundleContents bundleContents = stack.get(DataComponents.BUNDLE_CONTENTS);
        if (bundleContents != null) {
            int i = Mth.mulAndTruncate(bundleContents.weight(), 64);
            lines.add(Component.translatable("item.minecraft.bundle.fullness", i, 64).withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(stack, context, lines, flag);
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        BundleContents bundleContents = itemEntity.getItem().get(DataComponents.BUNDLE_CONTENTS);
        if (bundleContents != null) {
            itemEntity.getItem().set(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            ItemUtils.onContainerDestroyed(itemEntity, bundleContents.itemsCopy());
        }
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public void addTooltip(ItemStack stack, Consumer<Component> tooltip) {
        Style gray = Component.empty().getStyle().withColor(ChatFormatting.GRAY);
        tooltip.accept(Component.translatable("item.tide.fish_satchel.desc_0").setStyle(gray));
        tooltip.accept(Component.translatable("item.tide.fish_satchel.desc_1").setStyle(gray));
    }
}
//?} else {
/*package com.li64.tide.registries.items;

import com.li64.tide.data.TideTags;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.item.TideItemData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class FishSatchelItem extends BundleItem implements TooltipItem {
    public FishSatchelItem(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean canPutInSatchel(ItemStack stack) {
        return stack.is(TideTags.Items.FISH) || FishData.get(stack).isPresent();
    }

    public static int getFishCount(ItemStack stack) {
        return getContentWeight(stack);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {
        if (action != ClickAction.SECONDARY) return false;
        ItemStack other = slot.getItem();
        if (other.isEmpty()) {
            this.playRemoveOneSound(player);
            removeOne(stack).ifPresent(removed -> add(stack, slot.safeInsert(removed)));
        }
        else if (other.getItem().canFitInsideContainerItems()) {
            if (!canPutInSatchel(other)) return false;
            int i = (64 - getContentWeight(stack)) / getWeight(other);
            int j = add(stack, slot.safeTake(other.getCount(), i, player));
            if (j > 0) this.playInsertSound(player);
        }
        return true;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (!(action == ClickAction.SECONDARY && slot.allowModification(player))) return false;
        return overrideOtherStackedOnMe(stack, other, player, access);
    }

    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Player player, SlotAccess access) {
        if (other.isEmpty()) {
            removeOne(stack).ifPresent(itemStack -> {
                this.playRemoveOneSound(player);
                access.set(itemStack);
            });
        }
        else if (other.getItem().canFitInsideContainerItems()) {
            if (!canPutInSatchel(other)) return false;
            int i = add(stack, other);
            if (i > 0) {
                this.playInsertSound(player);
                other.shrink(i);
            }
        }
        return true;
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand used) {
        ItemStack satchel = player.getItemInHand(used);
        TideItemData.FISH_SATCHEL_OPENED.set(satchel, !TideItemData.FISH_SATCHEL_OPENED.getOrDefault(satchel, false));
        player.playSound(SoundEvents.ARMOR_EQUIP_LEATHER, 0.8f, 0.8f + player.level().getRandom().nextFloat() * 0.4f);
        return InteractionResultHolder.success(satchel);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> lines, TooltipFlag flag) {
        super.appendHoverText(stack, level, lines, flag);
        this.addTooltip(stack, lines::add);
    }

    @Override
    public void addTooltip(ItemStack stack, Consumer<Component> tooltip) {
        Style gray = Component.empty().getStyle().withColor(ChatFormatting.GRAY);
        tooltip.accept(Component.translatable("item.tide.fish_satchel.desc_0").setStyle(gray));
        tooltip.accept(Component.translatable("item.tide.fish_satchel.desc_1").setStyle(gray));
    }
}
*///?}