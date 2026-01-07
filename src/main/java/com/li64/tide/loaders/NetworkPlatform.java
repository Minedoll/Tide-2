package com.li64.tide.loaders;
//? if >=1.21 {
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface NetworkPlatform {
    <T extends CustomPacketPayload> void registerClientBoundPacket(Class<T> msgClass, ResourceLocation id,
                                                                   BiConsumer<T, RegistryFriendlyByteBuf> encoder,
                                                                   Function<RegistryFriendlyByteBuf, T> decoder,
                                                                   BiConsumer<T, Player> handler);

    <T extends CustomPacketPayload> void registerServerBoundPacket(Class<T> msgClass, ResourceLocation id,
                                                                   BiConsumer<T, RegistryFriendlyByteBuf> encoder,
                                                                   Function<RegistryFriendlyByteBuf, T> decoder,
                                                                   BiConsumer<T, Player> handler);

    void sendToPlayer(CustomPacketPayload message, ServerPlayer player);

    void sendToServer(CustomPacketPayload message);

    default void registerHandlers() {}

    record ClientMsgRegistration<T extends CustomPacketPayload>(CustomPacketPayload.Type<T> type,
                                                                BiConsumer<T, Player> handler) {}
}
//?} else {
/*import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface NetworkPlatform {
    <T> void registerClientBoundPacket(Class<T> msgClass, ResourceLocation id,
                                       BiConsumer<T, FriendlyByteBuf> encoder,
                                       Function<FriendlyByteBuf, T> decoder,
                                       BiConsumer<T, Player> handler);

    <T> void registerServerBoundPacket(Class<T> msgClass, ResourceLocation id,
                                       BiConsumer<T, FriendlyByteBuf> encoder,
                                       Function<FriendlyByteBuf, T> decoder,
                                       BiConsumer<T, Player> handler);

    <T> void sendToPlayer(T message, ServerPlayer player);

    <T> void sendToServer(T message);

    default void registerHandlers() {};
}
*///?}