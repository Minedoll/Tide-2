package com.li64.tide.registries;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.ArrayList;

public class TideEntityAttributes {
    public static final ArrayList<Registration<?>> REGISTRY = new ArrayList<>();

    public record Registration<T extends LivingEntity>(EntityType<T> entityType, AttributeSupplier attributes) {}

    public static void init() {}

    public static <T extends LivingEntity> void registerAttributes(EntityType<T> entityType, AttributeSupplier attributes) {
        REGISTRY.add(new Registration<>(entityType, attributes));
    }
}
