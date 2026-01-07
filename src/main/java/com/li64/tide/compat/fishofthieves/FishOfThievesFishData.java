package com.li64.tide.compat.fishofthieves;

import com.li64.tide.data.TideTags;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.fishing.MinigameBehavior;
import com.li64.tide.data.fishing.conditions.types.StructuresCondition;
import com.li64.tide.data.fishing.conditions.types.WeatherType;
import com.li64.tide.data.journal.FishRarity;
import com.li64.tide.data.journal.JournalGroup;
import com.li64.tide.datagen.fabric.providers.SimpleDataOutput;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;

import java.util.List;

public class FishOfThievesFishData {
    public static final String MOD_ID = "fishofthieves";

    public static void generate(SimpleDataOutput<FishData> output) {
        FishData.builder().fish(MOD_ID, "splashtail")
                .size(45, 60, 115)
                .strength(0.5f).speed(0.95f)
                .behavior(MinigameBehavior.DARTS)
                .selectionWeight(50)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.COMMON)
                .displayData(display -> display.offsets(-0.05f, 0.03f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "plentifin")
                .size(25, 40, 80)
                .strength(0.45f).speed(1.1f)
                .selectionWeight(45)
                .temperature(0.4f, 0.4f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.COMMON)
                .displayData(display -> display.offsets(0f, 0.02f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "islehopper")
                .size(25, 40, 80)
                .strength(0.45f).speed(1.2f)
                .behavior(MinigameBehavior.DARTS)
                .selectionWeight(40)
                .overworld().water().surface().saltwater()
                .foundIn(TideTags.Biomes.HAS_COASTAL_FISH)
                .journalLocation("journal.info.location.coast")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.COMMON)
                .displayData(display -> display.offsets(0.08f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "ancientscale")
                .size(35, 50, 90)
                .strength(0.58f).speed(1.1f)
                .selectionWeight(40)
                .temperature(0.2f, 0.3f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.COMMON)
                .displayData(display -> display.offsets(0.1f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "pondie")
                .size(30, 45, 85)
                .strength(0.4f).speed(1.0f)
                .selectionWeight(32)
                .temperature(0f, 0.6f)
                .overworld().water().surface().freshwater()
                .journalLocation("journal.info.location.freshwater")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.COMMON)
                .displayData(display -> display.offsets(0.04f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "wildsplash")
                .size(30, 45, 85)
                .strength(0.4f).speed(1.0f)
                .selectionWeight(28)
                .overworld().water().surface().freshwater()
                .foundIn(TideTags.Biomes.HAS_JUNGLE_FISH)
                .journalLocation("journal.info.location.jungle")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.COMMON)
                .displayData(display -> display.offsets(0.07f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "devilfish")
                .size(30, 45, 85)
                .strength(0.7f).speed(1.3f)
                .selectionWeight(32)
                .overworld().water().below(40)
                .foundIn(TideTags.Biomes.HAS_DRIPSTONE_FISH)
                .journalLocation("journal.info.location.dripstone_caves")
                .journalGroup(JournalGroup.UNDERGROUND)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display.offsets(-0.02f, 0.02f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "stormfish")
                .size(45, 60, 115)
                .strength(0.75f).speed(1.25f)
                .behavior(MinigameBehavior.JITTER)
                .selectionWeight(20)
                .weather(WeatherType.STORM)
                .journalLocation("journal.info.location.thunderstorms")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.RARE)
                .displayData(display -> display.offsets(0.02f, 0.04f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "battlegill")
                .size(30, 45, 85)
                .strength(0.75f).speed(1.3f)
                .behavior(MinigameBehavior.PLATEAU)
                .selectionWeight(30)
                .condition(StructuresCondition.inStructures(List.of(
                        BuiltinStructures.OCEAN_MONUMENT,
                        BuiltinStructures.PILLAGER_OUTPOST
                )))
                .journalLocation("journal.info.location.monuments_and_outposts")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.VERY_RARE)
                .displayData(display -> display.offsets(0f, 0.03f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "wrecker")
                .size(30, 45, 85)
                .strength(0.78f).speed(1.35f)
                .behavior(MinigameBehavior.JITTER)
                .selectionWeight(30)
                .condition(StructuresCondition.inStructures(List.of(
                        BuiltinStructures.SHIPWRECK,
                        BuiltinStructures.SHIPWRECK_BEACHED,
                        BuiltinStructures.RUINED_PORTAL_OCEAN
                )))
                .journalLocation("journal.info.location.shipwrecks")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.VERY_RARE)
                .displayData(display -> display.offsets(-0.03f, 0.02f, 0f))
                .build(output);
    }
}
