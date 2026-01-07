package com.li64.tide.data;

import com.li64.tide.registries.items.FishyNoteItem;
import com.li64.tide.util.TideUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

//? if >= 1.21 {
import net.minecraft.core.HolderLookup;
//?} else {
/*import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
*///?}

public class FishyNoteRecipe extends CustomRecipe {
    public FishyNoteRecipe(/*? if <1.21 {*//*ResourceLocation id, *//*?}*/CraftingBookCategory category) {
        super(/*? if <1.21 {*//*id, *//*?}*/category);
    }

    private ItemStack getFishToConvert(/*? if >=1.21 {*/CraftingInput/*?} else {*//*CraftingContainer*//*?}*/ input) {
        ItemStack stack1 = null, stack2 = null;

        for (int i = 0; i < /*? if >=1.21 {*/input.size()/*?} else {*//*input.getContainerSize()*//*?}*/; i++) {
            ItemStack inSlot = input.getItem(i);
            if (inSlot.isEmpty()) continue;
            if (stack1 == null) stack1 = inSlot;
            else {
                if (stack2 != null) return null;
                stack2 = inSlot;
            }
        }

        return stack1 != null && stack2 != null ? getFishFromInputs(stack1, stack2) : null;
    }

    private static ItemStack getFishFromInputs(ItemStack stack1, ItemStack stack2) {
        if (TideUtils.isJournalFish(stack1) && stack2.is(Items.PAPER)) return stack1;
        if (TideUtils.isJournalFish(stack2) && stack1.is(Items.PAPER)) return stack2;
        return null;
    }

    @Override
    public boolean matches(/*? if >=1.21 {*/CraftingInput/*?} else {*//*CraftingContainer*//*?}*/ input, Level level) {
        return getFishToConvert(input) != null;
    }

    @Override
    public @NotNull ItemStack assemble(/*? if >=1.21 {*/CraftingInput input, HolderLookup.Provider registries
                                       /*?} else {*//*CraftingContainer input, RegistryAccess registries*//*?}*/) {
        ItemStack fish = getFishToConvert(input);
        if (fish == null || fish.isEmpty()) return ItemStack.EMPTY;
        return FishyNoteItem.create(new ItemStack(fish.getItem()));
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height > 2;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return TideRecipeSerializers.FISHY_NOTE_RECIPE;
    }
}
