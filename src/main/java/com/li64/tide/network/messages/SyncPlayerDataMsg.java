package com.li64.tide.network.messages;

import com.li64.tide.Tide;
import com.li64.tide.data.player.TidePlayerData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public record SyncPlayerDataMsg(CompoundTag tag) implements TidePacketPayload {
    public static final ResourceLocation ID = Tide.resource("sync_player_data");
    @Override public ResourceLocation id() { return ID; }

    public SyncPlayerDataMsg(FriendlyByteBuf buf) {
        this(buf.readNbt());
    }

    public static void encode(SyncPlayerDataMsg message, FriendlyByteBuf buf) {
        buf.writeNbt(message.tag);
    }

    public static void handle(SyncPlayerDataMsg message, Player player) {
        TidePlayerData.CLIENT_DATA = new TidePlayerData(message.tag);
    }
}