//? if fabric {
package com.li64.tide.datagen.fabric;

import com.li64.tide.compat.fishingreal.FishingRealConversions;
import com.li64.tide.datagen.fabric.providers.assets.*;
import com.li64.tide.datagen.fabric.providers.loot.*;
import com.li64.tide.compat.stardewfishing.StardewFishingBehaviors;
import com.li64.tide.datagen.fabric.providers.tags.*;
import com.li64.tide.datagen.fabric.providers.fishing.*;
import com.li64.tide.datagen.fabric.providers.recipes.*;
import com.li64.tide.datagen.fabric.providers.advancements.*;
import com.li64.tide.registries.TideEntityTypes;
import com.li64.tide.registries.TideFish;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TideDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        final FabricDataGenerator.Pack pack = generator.createPack();

        TideFish.init();

        pack.addProvider(TideLanguageProvider::new);
        pack.addProvider(TideModelProvider::new);

        pack.addProvider(TideBlockTagsProvider::new);
        pack.addProvider(TideItemTagsProvider::new);
        pack.addProvider(TideBiomeTagsProvider::new);
        pack.addProvider(TideFluidTagsProvider::new);
        pack.addProvider(TideEntityTypeTagsProvider::new);

        pack.addProvider(TideFishingLootProvider::new);
        pack.addProvider(TideChestLootProvider::new);
        pack.addProvider(TideEntityLootProvider::new);
        pack.addProvider(TideBlockLootProvider::new);

        pack.addProvider(TideAdvancementProvider::new);
        pack.addProvider(TideRecipeProvider::new);

        pack.addProvider(TideFishDataProvider::new);
        pack.addProvider(TideCrateDataProvider::new);
        pack.addProvider(TideFishingLootDataProvider::new);

        pack.addProvider(StardewFishingBehaviors::new);
        pack.addProvider(FishingRealConversions::new);
    }
}
//?}