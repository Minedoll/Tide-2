package com.li64.tide.data.journal;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

// TODO: make this data-driven
public enum JournalGroup implements StringRepresentable {
    FRESHWATER("freshwater"),
    MISC("misc"),
    SALTWATER("saltwater"),
    UNDERGROUND("underground"),
    LAVA("lava"),
    VOID("void");

    public static final Codec<JournalGroup> CODEC = StringRepresentable.fromEnumWithMapping(
            JournalGroup::values, name -> name.toLowerCase(Locale.ROOT));

    private final String key;

    JournalGroup(String key) {
        this.key = key;
    }

    @Override
    public @NotNull String getSerializedName() {
        return key;
    }
}
