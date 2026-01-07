//? if neoforge {
/*package com.li64.tide.loaders.neoforge;

import com.li64.tide.Tide;
import com.li64.tide.data.TideData;
import com.li64.tide.data.commands.FishingCommand;
import com.li64.tide.data.commands.JournalCommand;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.player.TidePlayerData;
import com.li64.tide.events.TideEventHandler;
import com.li64.tide.registries.TideFish;
import com.li64.tide.registries.TideItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;

@EventBusSubscriber(modid = Tide.MOD_ID)
public class NeoforgeEventHandler {
    @SubscribeEvent
    public static void registerReloadListeners(final AddReloadListenerEvent event) {
        TideData.onRegisterReloadListeners((id, listener) -> event.addListener(listener));
    }

    @SubscribeEvent
    public static void registerCommands(final RegisterCommandsEvent event) {
        new JournalCommand(event.getDispatcher(), event.getBuildContext());
        new FishingCommand(event.getDispatcher(), event.getBuildContext());
    }

    @SubscribeEvent
    public static void onLootTableLoad(final LootTableLoadEvent event) {
        if (event.getName().toString().equals("minecraft:gameplay/fishing"))
            FishData.VANILLA_FISH_TABLE = event.getTable();
    }

    @SubscribeEvent
    public static void onServerStarted(final ServerStartedEvent event) {
        TideEventHandler.serverStarted();
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(final PlayerEvent.PlayerLoggedInEvent event) {
        TideEventHandler.onPlayerJoinWorld((ServerPlayer) event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.getEntity().level().isClientSide()) return;
        TidePlayerData.getOrCreate((ServerPlayer) event.getOriginal())
                .syncTo((ServerPlayer) event.getEntity());
    }

    @SubscribeEvent
    public static void onFurnaceFuelBurned(final FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().is(TideItems.WOODEN_CRATE)) event.setBurnTime(300);
        if (event.getItemStack().is(TideItems.ANGLING_TABLE)) event.setBurnTime(300);
        if (event.getItemStack().is(TideItems.FISH_DISPLAY)) event.setBurnTime(300);
        if (event.getItemStack().is(TideItems.FISHY_NOTE)) event.setBurnTime(200);
        if (event.getItemStack().is(TideFish.INFERNO_GUPPY)) event.setBurnTime(3200);
    }
}
*///?}