package com.li64.tide.data.loot;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.loot.LootTable;

/*? if >=1.21*/import net.minecraft.core.registries.Registries;

public interface LootTableRef {
    //? if >=1.21 {
    default ResourceKey<LootTable> get() { return this.getKey(); }

    static LootTableRef createNew(ResourceLocation id) {
        return of(ResourceKey.create(Registries.LOOT_TABLE, id));
    }

    static LootTableRef of(ResourceKey<LootTable> key) {
        return new KeyedLootTableRef(key);
    }
    //?} else {
    /*default ResourceLocation get() { return this.getId(); }

    static LootTableRef createNew(ResourceLocation id) {
        return new IdLootTableRef(id);
    }

    static LootTableRef of(ResourceLocation id) {
        return createNew(id);
    }
    *///?}

    ResourceKey<LootTable> getKey();
    ResourceLocation getId();
    LootTable getTable(MinecraftServer server);
}
