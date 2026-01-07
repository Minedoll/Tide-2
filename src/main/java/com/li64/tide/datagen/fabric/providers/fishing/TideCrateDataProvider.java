//? if fabric {
package com.li64.tide.datagen.fabric.providers.fishing;

import com.li64.tide.data.TideLootTables;
import com.li64.tide.data.fishing.CrateData;
import com.li64.tide.datagen.fabric.providers.SimpleDataOutput;
import com.li64.tide.datagen.fabric.providers.SimpleDataProvider;
import com.li64.tide.registries.TideBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class TideCrateDataProvider extends SimpleDataProvider<CrateData> {
    public TideCrateDataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super("fishing/crates", output, registries);
    }

    @Override
    protected Codec<CrateData> dataCodec() {
        return CrateData.CODEC;
    }

    @Override
    protected void generate(SimpleDataOutput<CrateData> output) {
        CrateData.builder()
                .block(TideBlocks.WOODEN_CRATE)
                .lootTable(TideLootTables.Chests.Crates.SURFACE_FRESHWATER)
                .openWater(true)
                .freshwater()
                .water()
                .overworld()
                .surface()
                .build("surface_freshwater", output);

        CrateData.builder()
                .block(TideBlocks.WOODEN_CRATE)
                .lootTable(TideLootTables.Chests.Crates.SURFACE_SALTWATER)
                .openWater(true)
                .saltwater()
                .water()
                .overworld()
                .surface()
                .build("surface_saltwater", output);

        CrateData.builder()
                .block(TideBlocks.WOODEN_CRATE)
                .lootTable(TideLootTables.Chests.Crates.UNDERGROUND)
                .water()
                .overworld()
                .elevationRange(0, 40)
                .build("underground", output);

        CrateData.builder()
                .block(TideBlocks.WOODEN_CRATE)
                .lootTable(TideLootTables.Chests.Crates.DEEP)
                .water()
                .overworld()
                .below(0)
                .build("deep", output);

        CrateData.builder()
                .block(TideBlocks.OBSIDIAN_CRATE)
                .lootTable(TideLootTables.Chests.Crates.SURFACE_LAVA)
                .lava()
                .overworld()
                .surface()
                .build("surface_lava", output);

        CrateData.builder()
                .block(TideBlocks.OBSIDIAN_CRATE)
                .lootTable(TideLootTables.Chests.Crates.UNDERGROUND_LAVA)
                .lava()
                .overworld()
                .elevationRange(0, 40)
                .build("underground_lava", output);

        CrateData.builder()
                .block(TideBlocks.OBSIDIAN_CRATE)
                .lootTable(TideLootTables.Chests.Crates.DEEP_LAVA)
                .lava()
                .overworld()
                .below(0)
                .build("deep_lava", output);

        CrateData.builder()
                .block(TideBlocks.OBSIDIAN_CRATE)
                .lootTable(TideLootTables.Chests.Crates.NETHER)
                .nether()
                .build("nether", output);

        CrateData.builder()
                .block(TideBlocks.PURPUR_CRATE)
                .lootTable(TideLootTables.Chests.Crates.END)
                .end()
                .build("end", output);
    }

    @Override
    public @NotNull String getName() {
        return "Crate Data";
    }
}
//?}