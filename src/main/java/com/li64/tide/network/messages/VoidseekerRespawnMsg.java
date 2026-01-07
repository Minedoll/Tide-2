package com.li64.tide.network.messages;

import com.li64.tide.Tide;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

@SuppressWarnings("unused")
public class VoidseekerRespawnMsg implements TidePacketPayload {
    public static final ResourceLocation ID = Tide.resource("voidseeker_respawn");
    @Override public ResourceLocation id() { return ID; }

    public VoidseekerRespawnMsg() {}

    public VoidseekerRespawnMsg(FriendlyByteBuf buf) {}

    public static void encode(VoidseekerRespawnMsg message, FriendlyByteBuf buf) {}

    public static void handle(VoidseekerRespawnMsg message, Player player) {
        player.respawn();
    }
}