package com.li64.tide.registries.blocks;

import com.mojang.serialization.Codec;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public enum FishDisplayShape implements StringRepresentable {
    SHAPE_1x1("1x1", FishDisplayBlock.SHAPES_1x1),
    SHAPE_2x1("2x1", FishDisplayBlock.SHAPES_2x1),
    SHAPE_3x1("3x1", FishDisplayBlock.SHAPES_3x1);

    public static final Codec<FishDisplayShape> CODEC = StringRepresentable.fromEnum(FishDisplayShape::values);

    private final String id;
    private final Map<Direction, VoxelShape> shapes;

    FishDisplayShape(String id, Map<Direction, VoxelShape> shapes) {
        this.id = id;
        this.shapes = shapes;
    }

    public VoxelShape getShape(Direction direction) {
        return this.shapes.get(direction);
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.id;
    }
}