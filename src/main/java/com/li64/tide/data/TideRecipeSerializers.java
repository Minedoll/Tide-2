package com.li64.tide.data;

import com.li64.tide.Tide;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class TideRecipeSerializers {
    public static final RecipeSerializer<? extends CraftingRecipe> FISHY_NOTE_RECIPE = register(
            "fishy_note_recipe", new SimpleCraftingRecipeSerializer<>(FishyNoteRecipe::new));

    public static RecipeSerializer<? extends CraftingRecipe> register(String key, RecipeSerializer<? extends CraftingRecipe> serializer) {
        return Tide.PLATFORM.register(BuiltInRegistries.RECIPE_SERIALIZER, Tide.resource(key), serializer);
    }

    public static void init() {}
}
