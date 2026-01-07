//? if fabric {
package com.li64.tide.datagen.fabric.providers.loot;

import com.li64.tide.data.loot.LootTableRef;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;

/*? if >=1.21 {*/import net.minecraft.resources.ResourceKey;
/*?} else*//*import net.minecraft.resources.ResourceLocation;*/

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public abstract class TideAbstractLootProvider extends SimpleFabricLootTableProvider {
    /*? if >=1.21*/protected final HolderLookup.Provider registries;

    @SuppressWarnings("unused")
    public TideAbstractLootProvider(FabricDataOutput output,
                                    CompletableFuture<HolderLookup.Provider> registryLookup,
                                    LootContextParamSet lootType) {
        super(output/*? if >=1.21 {*/, registryLookup/*?}*/, lootType);
        /*? if >=1.21*/this.registries = registryLookup.join();
    }

    //? if >=1.21 {
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        this.generateLoot((ref, builder) -> output.accept(ref.getKey(), builder));
    }
    //?} else {
    /*public void generate(BiConsumer<ResourceLocation, LootTable.Builder> output) {
        this.generateLoot((ref, builder) -> output.accept(ref.getId(), builder));
    }
    *///?}

    public abstract void generateLoot(TideLootOutput output);

    @FunctionalInterface
    public interface TideLootOutput {
        void accept(LootTableRef ref, LootTable.Builder builder);
    }
}
//?}