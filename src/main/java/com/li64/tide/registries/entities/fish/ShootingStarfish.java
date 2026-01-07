package com.li64.tide.registries.entities.fish;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class ShootingStarfish extends AbstractTideFish {
    public ShootingStarfish(EntityType<? extends ShootingStarfish> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean doesFlop() {
        return false;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (random.nextFloat() < 0.5f) {
            this.level().addParticle(ParticleTypes.END_ROD,
                    this.getRandomX(0.6), this.getRandomY(), this.getRandomZ(0.6),
                    0.0, 0.0, 0.0);
        }
    }
}
