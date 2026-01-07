package com.li64.tide.client.gui.screens;

import com.li64.tide.Tide;
import com.li64.tide.client.gui.screens.journal.FishProfile;
import com.li64.tide.client.gui.screens.journal.ProfileComponent;
import com.li64.tide.client.gui.screens.journal.components.HorizontalLineComponent;
import com.li64.tide.client.gui.screens.journal.components.RarityComponent;
import com.li64.tide.client.gui.screens.journal.components.SilhouetteComponent;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.player.TidePlayerData;
import com.li64.tide.registries.TideSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class FishyNoteScreen extends Screen {
    private static final ResourceLocation BG = Tide.resource("textures/gui/journal/fishy_note.png");
    private static final ResourceLocation CHECKMARK = Tide.resource("textures/gui/journal/checkmark.png");
    private static final int WIDTH = 200;
    private static final int HEIGHT = 230;

    private final boolean discovered;
    private final List<ProfileComponent> components;
    private Button xButton;

    public FishyNoteScreen(ItemStack fish) {
        super(Component.translatable("item.tide.fishy_note"));

        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) player.playSound(TideSoundEvents.PAGE_FLIP,
                1.0f, 1.0f + new Random().nextFloat() * 0.2f);

        FishData data = FishData.getOrThrow(fish);
        this.components = FishProfile.buildComponents(data);

        if (!components.isEmpty()) components.add(0, new HorizontalLineComponent(false));
        if (!(components.get(components.size() - 1) instanceof HorizontalLineComponent)) components.add(new HorizontalLineComponent(false));

        this.components.add(0, new SilhouetteComponent(data.size()));
        this.components.add(new RarityComponent(data.profile().rarity()));

        this.discovered = TidePlayerData.CLIENT_DATA.isFishUnlocked(data.fish());
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        int xX = Math.min(width - 19, (width + WIDTH) / 2 - 16);
        int xY = Math.max(3, (height - HEIGHT) / 2);
        this.xButton = Button.builder(Component.literal("X"), button -> this.onClose())
                .bounds(xX, xY, 16, 16).build();
        this.addWidget(xButton);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        /*? if <1.21*//*this.renderBackground(graphics);*/
        super.render(graphics, mouseX, mouseY, partialTick);
        int x = (graphics.guiWidth() - WIDTH) / 2;
        int y = (graphics.guiHeight() - HEIGHT) / 2;
        graphics.blit(BG, x, y, 0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);

        // render "discovered" checkmark
        if (discovered) {
            graphics.blit(CHECKMARK, x + 18, y + 18, 0, 0,
                    16, 16, 16, 16);
        }

        // render profile components
        int cursorY = 0;
        final int padding = 4;
        int totalHeight = components.stream()
                .mapToInt(area -> area.getRequiredHeight() + padding)
                .sum();
        int originY = y + (HEIGHT - totalHeight) / 2;
        for (ProfileComponent area : components) {
            area.render(graphics, font, x + 14, originY + cursorY, mouseX, mouseY, partialTick);
            cursorY += area.getRequiredHeight() + padding;
        }

        if (xButton != null) xButton.render(graphics, mouseX, mouseY, partialTick);
    }
}
