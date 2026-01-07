//? if <1.21 {
/*package com.li64.tide.data.loot;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.loot.LootTable;

public record IdLootTableRef(ResourceLocation id) implements LootTableRef {
    @Override
    public ResourceKey<LootTable> getKey() {
        throw new UnsupportedOperationException("getKey() cannot be called on IdLootTableRef!");
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public LootTable getTable(MinecraftServer server) {
        return server.getLootData().getLootTable(this.id);
    }
}
*///?}