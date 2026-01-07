package com.li64.tide.registries;
import com.li64.tide.Tide;
import com.li64.tide.data.loot.ApplyFishEntityLengthFunction;
import com.li64.tide.data.loot.RandomizeFishLengthFunction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

import java.util.HashMap;

//? if <1.21 {
/*import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapLike;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.level.storage.loot.Serializer;
*///?}

public class TideLootFunctions {
    public static final HashMap<String, LootItemFunctionType/*? if >=1.21 {*/<?>/*?}*/> LOOT_FUNCTIONS = new HashMap<>();

    //? if >=1.21 {
    public static final LootItemFunctionType<ApplyFishEntityLengthFunction> APPLY_FISH_ENTITY_LENGTH = register(
            "apply_fish_entity_length", new LootItemFunctionType<>(ApplyFishEntityLengthFunction.CODEC));
    public static final LootItemFunctionType<RandomizeFishLengthFunction> RANDOMIZE_FISH_LENGTH = register(
            "randomize_fish_length", new LootItemFunctionType<>(RandomizeFishLengthFunction.CODEC));

    public static <T extends LootItemFunction> LootItemFunctionType<T> register(String key, LootItemFunctionType<T> type) {
        LOOT_FUNCTIONS.put(key, type);
        return Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, Tide.resource(key), type);
    }
    //?} else {
    /*public static final LootItemFunctionType APPLY_FISH_ENTITY_LENGTH = register(
            "apply_fish_entity_length", new LootItemFunctionType(createSerializer(ApplyFishEntityLengthFunction.CODEC)));
    public static final LootItemFunctionType RANDOMIZE_FISH_LENGTH = register(
            "randomize_fish_length", new LootItemFunctionType(createSerializer(RandomizeFishLengthFunction.CODEC)));

    // TODO: check that this works
    private static <T extends LootItemFunction> Serializer<T> createSerializer(MapCodec<T> codec) {
        return new Serializer<>() {
            @Override
            public void serialize(JsonObject json, T object, JsonSerializationContext ctx) {
                codec.encode(object, JsonOps.INSTANCE, JsonOps.INSTANCE.mapBuilder()).build(json);
            }

            @Override
            public @NotNull T deserialize(JsonObject json, JsonDeserializationContext ctx) {
                MapLike<JsonElement> maplike = JsonOps.INSTANCE.getMap(json).getOrThrow(false, Tide.LOG::error);
                return codec.decode(JsonOps.INSTANCE, maplike).resultOrPartial(Tide.LOG::error).orElseThrow();
            }
        };
    }

    public static LootItemFunctionType register(String key, LootItemFunctionType type) {
        LOOT_FUNCTIONS.put(key, type);
        return Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, Tide.resource(key), type);
    }
    *///?}

    public static void init() {}
}
