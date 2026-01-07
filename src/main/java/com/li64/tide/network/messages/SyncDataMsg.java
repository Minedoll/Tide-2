package com.li64.tide.network.messages;

import com.li64.tide.Tide;
import com.li64.tide.data.TideData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public record SyncDataMsg() implements TidePacketPayload {
    public static final ResourceLocation ID = Tide.resource("sync_data");
    @Override public ResourceLocation id() { return ID; }

    public SyncDataMsg(FriendlyByteBuf buf) {
        this();
        TideData.readFromPacket(buf);
    }

    public static void encode(SyncDataMsg message, FriendlyByteBuf buf) {
        TideData.writeToPacket(buf);
    }

    public static void handle(SyncDataMsg message, Player player) {}
}