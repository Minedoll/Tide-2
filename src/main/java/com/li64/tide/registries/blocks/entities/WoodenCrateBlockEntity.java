package com.li64.tide.registries.blocks.entities;

import com.li64.tide.registries.TideBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class WoodenCrateBlockEntity extends LootCrateBlockEntity {
    public WoodenCrateBlockEntity(BlockPos pos, BlockState state) {
        super(TideBlockEntities.WOODEN_CRATE, pos, state);
    }
}
