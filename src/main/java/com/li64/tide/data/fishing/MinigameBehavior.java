package com.li64.tide.data.fishing;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.UnaryOperator;
import static net.minecraft.util.Mth.*;

public enum MinigameBehavior implements UnaryOperator<Float>, StringRepresentable {
    SINE(t -> sin(PI * t)),
    PLATEAU(t -> sin(sin(PI * t) * (PI / 2f))),
    JITTER(t -> 0.9f * sin(PI * t) + 0.1f * sin(7 * PI * t)),
    DARTS(t -> sin(PI * t + 0.3f * sin(5 * t))),
    LINEAR(t -> 1 - 2 * abs((t + 0.5f) % 2 - 1)),
    LINEAR_WRAP(t -> (2 * t + 1) % 2 - 1);

    public static final Codec<MinigameBehavior> CODEC = StringRepresentable.fromEnumWithMapping(
            MinigameBehavior::values, name -> name.toLowerCase(Locale.ROOT));

    private final UnaryOperator<Float> func;

    MinigameBehavior(UnaryOperator<Float> func) {
        this.func = func;
    }

    @Override
    public Float apply(Float t) {
        return func.apply(t);
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
