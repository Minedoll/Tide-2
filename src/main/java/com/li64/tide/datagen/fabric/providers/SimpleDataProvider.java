//? if fabric {
package com.li64.tide.datagen.fabric.providers;

import com.li64.tide.Tide;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class SimpleDataProvider<T> implements DataProvider {
    private final PackOutput.PathProvider pathProvider;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public SimpleDataProvider(String path, PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, path);
        this.registries = registries;
    }

    @Override
    public final @NotNull CompletableFuture<?> run(CachedOutput output) {
        return this.registries.thenCompose((provider) -> this.run(output, provider));
    }

    protected abstract Codec<T> dataCodec();
    protected abstract void generate(SimpleDataOutput<T> output);

    @SuppressWarnings("unused")
    public CompletableFuture<?> run(final CachedOutput output, final HolderLookup.Provider registries) {
        List<CompletableFuture<?>> list = new ArrayList<>();
        SimpleDataOutput<T> consumer = (path, data) -> list.add(
                //? if >=1.21 {
                DataProvider.saveStable(output, registries, dataCodec(), data, this.pathProvider.json(path))
                //?} else {
                /*DataProvider.saveStable(output, dataCodec().encodeStart(JsonOps.INSTANCE, data)
                        .resultOrPartial(Tide.LOG::error).orElseThrow(), pathProvider.json(path))
                *///?}
        );
        this.generate(consumer);
        return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
    }
}
//?}