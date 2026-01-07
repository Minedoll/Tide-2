package com.li64.tide.registries.particles;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
/*? if <1.21*//*import org.joml.Vector3f;*/

import java.util.function.BiConsumer;

public class VoidRipple extends TextureSheetParticle {
    private final BiConsumer<Quaternionf, Camera> facingCameraFunc;
    private final SpriteSet sprites;

    protected VoidRipple(ClientLevel level, double x, double y, double z, SpriteSet sprites, float quadSize) {
        super(level, x, y, z, 0, 0, 0);
        this.lifetime = 8;
        this.hasPhysics = false;
        this.xd = 0;
        this.yd = 0;
        this.zd = 0;
        this.sprites = sprites;
        this.quadSize = quadSize;
        this.setSpriteFromAge(sprites);
        this.facingCameraFunc = (q, cam) -> q.set(0, 0, 0, cam.rotation().w).rotateLocalX(-Mth.HALF_PI);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    //? if >=1.21 {
    @Override
    public @NotNull FacingCameraMode getFacingCameraMode() {
        return (q, cam, f) -> facingCameraFunc.accept(q, cam);
    }
    //?}

    @Override
    public void render(VertexConsumer buffer, Camera cam, float partialTicks) {
        Vec3 camPos = cam.getPosition();
        float x = (float)(Mth.lerp(partialTicks, this.xo, this.x) - camPos.x());
        float y = (float)(Mth.lerp(partialTicks, this.yo, this.y) - camPos.y());
        float z = (float)(Mth.lerp(partialTicks, this.zo, this.z) - camPos.z());
        Quaternionf q = new Quaternionf();
        facingCameraFunc.accept(q, cam);
        if (this.roll != 0.0F) q.rotateZ(Mth.lerp(partialTicks, this.oRoll, this.roll));

        this.renderRotatedQuad(buffer, q, x, y, z, partialTicks);
    }

    protected void renderRotatedQuad(@NotNull VertexConsumer buffer, @NotNull Quaternionf quaternion,
                                     float x, float y, float z, float partialTicks) {
        float size = this.getQuadSize(partialTicks);
        float u0 = this.getU0();
        float u1 = this.getU1();
        float v0 = this.getV0();
        float v1 = this.getV1();
        int color = this.getLightColor(partialTicks);
        this.renderVertex(buffer, quaternion, x, y, z, 1f, -1f, size, u1, v1, color);
        this.renderVertex(buffer, quaternion, x, y, z, 1f, 1f, size, u1, v0, color);
        this.renderVertex(buffer, quaternion, x, y, z, -1f, 1f, size, u0, v0, color);
        this.renderVertex(buffer, quaternion, x, y, z, -1f, -1f, size, u0, v1, color);
    }

    //? if <1.21 {
    /*public final void renderVertex(VertexConsumer buffer, Quaternionf q, float x, float y, float z, float xOffset, float yOffset, float quadSize, float u, float v, int packedLight) {
        Vector3f pos = new Vector3f(xOffset, yOffset, 0.0F).rotate(q).mul(quadSize).add(x, y, z);
        buffer.vertex(pos.x(), pos.y(), pos.z()).uv(u, v).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(packedLight).endVertex();
    }
    *///?}

    public record LargeProvider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {
        @Override
        public @NotNull Particle createParticle(@NotNull SimpleParticleType type,
                                                @NotNull ClientLevel level,
                                                double x, double y, double z,
                                                double vx, double vy, double vz) {
            return new VoidRipple(level, x, y, z, sprites, 0.5f);
        }
    }

    public record SmallProvider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {
        @Override
        public @NotNull Particle createParticle(@NotNull SimpleParticleType type,
                                                @NotNull ClientLevel level,
                                                double x, double y, double z,
                                                double vx, double vy, double vz) {
            return new VoidRipple(level, x, y, z, sprites, 0.25f);
        }
    }
}
