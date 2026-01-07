//? if forge {
/*package com.li64.tide.loaders.forge;

import com.li64.tide.Tide;
import com.li64.tide.client.gui.TideMenuTypes;
import com.li64.tide.data.TideCriteriaTriggers;
import com.li64.tide.data.TideRecipeSerializers;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.li64.tide.data.fishing.modifiers.FishingModifierType;
import com.li64.tide.network.TideMessages;
import com.li64.tide.registries.*;
import com.li64.tide.registries.entities.TideSpawnConfigs;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

/^
 _ _     __      __    __       ___   .___________. _______     _______   ______   .______     _______   _______     _ _
( | )   |  |    |  |  |  |     /   \  |           ||   ____|   |   ____| /  __  \  |   _  \   /  _____| |   ____|   ( | )
 V V    |  |    |  |__|  |    /  ^  \ `---|  |----`|  |__      |  |__   |  |  |  | |  |_)  | |  |  __   |  |__       V V
        |  |    |   __   |   /  /_\  \    |  |     |   __|     |   __|  |  |  |  | |      /  |  | |_ |  |   __|
        |  |    |  |  |  |  /  _____  \   |  |     |  |____    |  |     |  `--'  | |  |\  \  |  |__| |  |  |____
        |__|    |__|  |__| /__/     \__\  |__|     |_______|   |__|      \______/  | _| `._|  \______|  |_______|

- Lightning64, 2025
 ^/
@Mod(Tide.MOD_ID)
public class ForgeEntrypoint {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tide.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Tide.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Tide.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Tide.MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Tide.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Tide.MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Tide.MOD_ID);
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, Tide.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Tide.MOD_ID);

    public ForgeEntrypoint() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.register(this);

        Tide.initialize();

        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
        ITEMS.register(eventBus);
        ENTITY_TYPES.register(eventBus);
        MENU_TYPES.register(eventBus);
        SOUND_EVENTS.register(eventBus);
        PARTICLE_TYPES.register(eventBus);
        LOOT_FUNCTION_TYPES.register(eventBus);
        RECIPE_SERIALIZERS.register(eventBus);

        TideCriteriaTriggers.init();

        ForgeNetworking.init();
        TideMessages.init(Tide.NETWORK);
    }

    // I hate this and it's all forge's fault
    @SuppressWarnings("deprecation")
    public static <T> T registerSomething(Registry<? super T> registry, String id, T toRegister) {
        if (registry == BuiltInRegistries.ITEM) ITEMS.register(id, () -> (Item) toRegister);
        if (registry == BuiltInRegistries.BLOCK) BLOCKS.register(id, () -> (Block) toRegister);
        if (registry == BuiltInRegistries.BLOCK_ENTITY_TYPE) BLOCK_ENTITIES.register(id, () -> (BlockEntityType<?>) toRegister);
        if (registry == BuiltInRegistries.ENTITY_TYPE) ENTITY_TYPES.register(id, () -> (EntityType<?>) toRegister);
        if (registry == BuiltInRegistries.MENU) MENU_TYPES.register(id, () -> (MenuType<?>) toRegister);
        if (registry == BuiltInRegistries.SOUND_EVENT) SOUND_EVENTS.register(id, () -> (SoundEvent) toRegister);
        if (registry == BuiltInRegistries.PARTICLE_TYPE) PARTICLE_TYPES.register(id, () -> (ParticleType<?>) toRegister);
        if (registry == BuiltInRegistries.LOOT_FUNCTION_TYPE) LOOT_FUNCTION_TYPES.register(id, () -> (LootItemFunctionType) toRegister);
        if (registry == BuiltInRegistries.RECIPE_SERIALIZER) RECIPE_SERIALIZERS.register(id, () -> (RecipeSerializer<?>) toRegister);
        return toRegister;
    }

    @SubscribeEvent
    public void onRegister(final RegisterEvent event) {
        event.register(Registries.ITEM, helper -> TideItems.init());
        event.register(Registries.BLOCK, helper -> TideBlocks.init());
        event.register(Registries.BLOCK_ENTITY_TYPE, helper -> TideBlockEntities.init());
        event.register(Registries.ENTITY_TYPE, helper -> TideEntityTypes.init());
        event.register(Registries.MENU, helper -> TideMenuTypes.init());
        event.register(Registries.SOUND_EVENT, helper -> TideSoundEvents.init());
        event.register(Registries.PARTICLE_TYPE, helper -> TideParticleTypes.init());
        event.register(Registries.LOOT_FUNCTION_TYPE, helper -> TideLootFunctions.init());
        event.register(Registries.RECIPE_SERIALIZER, helper -> TideRecipeSerializers.init());

        event.register(Registries.CREATIVE_MODE_TAB, helper -> Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB, Tide.resource("tide"),
                TideItems.getCreativeTab(CreativeModeTab.builder()).build()));
    }

    @SubscribeEvent
    public void registerRegistries(final NewRegistryEvent event) {
        event.create(RegistryBuilder.<FishingConditionType<?>>of(TideRegistries.FISHING_CONDITIONS_KEY.location()),
                reg -> TideRegistries.FISHING_CONDITIONS = reg);
        event.create(RegistryBuilder.<FishingModifierType<?>>of(TideRegistries.FISHING_MODIFIERS_KEY.location()),
                reg -> {
                    TideRegistries.FISHING_MODIFIERS = reg;
                    TideRegistries.init();
                });
    }

    @SubscribeEvent
    public void registerSpawnPlacements(final SpawnPlacementRegisterEvent event) {
        TideSpawnConfigs.onRegisterSpawnConfigs(config -> event.register(
                config.entityType(), config.placementType(),
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, config.spawnPredicate(),
                SpawnPlacementRegisterEvent.Operation.REPLACE
        ));
    }

    @SubscribeEvent
    public void registerEntityAttributes(final EntityAttributeCreationEvent event) {
        TideEntityAttributes.REGISTRY.forEach(reg -> event.put(reg.entityType(), reg.attributes()));
    }
}
*///?}
