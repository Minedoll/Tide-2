//? if fabric {
package com.li64.tide.datagen.fabric.providers.loot;

import com.li64.tide.registries.TideBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class TideBlockLootProvider extends FabricBlockLootTableProvider {
    @SuppressWarnings("unused")
    public TideBlockLootProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output/*? if >=1.21 {*/, registries/*?}*/);
    }

    @Override
    public void generate() {
        this.dropSelf(TideBlocks.ANGLING_TABLE);
        this.dropSelf(TideBlocks.WOODEN_CRATE);
        this.dropSelf(TideBlocks.OBSIDIAN_CRATE);
        this.dropSelf(TideBlocks.PURPUR_CRATE);
        this.dropSelf(TideBlocks.FISH_DISPLAY);
        this.dropSelf(TideBlocks.JELLY_TORCH);
        this.dropSelf(TideBlocks.JELLY_WALL_TORCH);
    }
}
//?}