package com.li64.tide.registries.blocks.entities;

import com.li64.tide.registries.TideBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PurpurCrateBlockEntity extends LootCrateBlockEntity {
    public PurpurCrateBlockEntity(BlockPos pos, BlockState state) {
        super(TideBlockEntities.PURPUR_CRATE, pos, state);
    }
}
