//? if forge {
/*package com.li64.tide.loaders.forge;

import com.li64.tide.loaders.LoaderPlatform;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatform implements LoaderPlatform {
    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public String getMCVersion() {
        return FMLLoader.versionInfo().mcVersion();
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public CompoundTag getPlayerData(ServerPlayer player) {
        return player.getPersistentData();
    }

    @Override
    public <T> T register(Registry<? super T> registry, ResourceLocation id, T toRegister) {
        return ForgeEntrypoint.registerSomething(registry, id.getPath(), toRegister);
    }

    @Override
    public <T> T register(Registry<? super T> registry, ResourceKey<T> id, T toRegister) {
        return ForgeEntrypoint.registerSomething(registry, id.location().getPath(), toRegister);
    }

    @Override
    public <T extends AbstractContainerMenu> MenuType<T> createMenuType(MenuType.MenuSupplier<T> menuSupplier, FeatureFlagSet flags) {
        return new MenuType<>(menuSupplier, flags);
    }
}
*///?}