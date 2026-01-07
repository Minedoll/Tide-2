//? if fabric {
package com.li64.tide.datagen.fabric.providers.loot;

import com.li64.tide.Tide;
import com.li64.tide.data.loot.ApplyFishEntityLengthFunction;
import com.li64.tide.data.loot.LootTableRef;
import com.li64.tide.registries.TideFish;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

//? if >=1.21 {
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.core.registries.Registries;
import java.util.List;
//?}

import java.util.concurrent.CompletableFuture;

public class TideEntityLootProvider extends TideAbstractLootProvider {
    public TideEntityLootProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.ENTITY);
    }

    @Override
    public void generateLoot(TideLootOutput output) {
        TideFish.ORDERED.forEach(item -> {
            String path = "entities/" + BuiltInRegistries.ITEM.getKey(item).getPath();
            output.accept(LootTableRef.createNew(Tide.resource(path)), simpleFishLootTable(item));
        });
    }

    //? if >=1.21 {
    protected static AnyOfCondition.Builder shouldSmeltLoot(HolderLookup.Provider registries) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
        return AnyOfCondition.anyOf(LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity()
                                .flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))),
                LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity()
                                .equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item()
                                        .withSubPredicate(ItemSubPredicates.ENCHANTMENTS,
                                                ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(
                                                        registryLookup.getOrThrow(EnchantmentTags.SMELTS_LOOT), MinMaxBounds.Ints.ANY))))))));
    }
    //?} else {
    /*protected static final EntityPredicate.Builder ENTITY_ON_FIRE = EntityPredicate.Builder.entity()
            .flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build());
    *///?}

    public LootTable.Builder simpleFishLootTable(Item fishItem) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0f))
                .add(LootItem.lootTableItem(fishItem)
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .apply(ApplyFishEntityLengthFunction::new)
                        .apply(SmeltItemFunction.smelted()
                                /*? if >=1.21 {*/.when(shouldSmeltLoot(registries))
                                /*?} else*//*.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))*/
                        )));
    }
}
//?}