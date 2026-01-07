package com.li64.tide.registries.blocks.entities;

import com.li64.tide.registries.TideBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ObsidianCrateBlockEntity extends LootCrateBlockEntity {
    public ObsidianCrateBlockEntity(BlockPos pos, BlockState state) {
        super(TideBlockEntities.OBSIDIAN_CRATE, pos, state);
    }
}
