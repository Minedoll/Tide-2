package com.li64.tide.network.messages;

import com.li64.tide.network.TideMessages;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

//? if >=1.21.1 {
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public interface TidePacketPayload extends CustomPacketPayload {
    ResourceLocation id();

    @Override
    default @NotNull Type<? extends CustomPacketPayload> type() {
        return TideMessages.ID_TO_TYPE.computeIfAbsent(this.id(), Type::new);
    }
}
//?} else {
/*public interface TidePacketPayload {
    ResourceLocation id();
}
*///?}
