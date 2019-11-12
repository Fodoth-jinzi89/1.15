package cofh.test.entity.projectile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.test.CoFHTest.PRISMARINE_ARROW_ENTITY;
import static cofh.test.CoFHTest.PRISMARINE_ARROW_ITEM;

public class PrismarineArrowEntity extends AbstractArrowEntity {

    public PrismarineArrowEntity(EntityType<? extends PrismarineArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
    }

    public PrismarineArrowEntity(World worldIn, LivingEntity shooter) {

        super(PRISMARINE_ARROW_ENTITY.get(), shooter, worldIn);
    }

    public PrismarineArrowEntity(World worldIn, double x, double y, double z) {

        super(PRISMARINE_ARROW_ENTITY.get(), x, y, z, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {

        return new ItemStack(PRISMARINE_ARROW_ITEM.get());
    }

    @Override
    protected float getWaterDrag() {

        return 0.99F;
    }

    @Override
    public void tick() {

        if (!this.world.isRemote) {
            this.setFlag(6, this.isGlowing());
        }
        this.baseTick();

        boolean flag = this.func_203047_q();
        Vec3d vec3d = this.getMotion();
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(horizontalMag(vec3d));
            this.rotationYaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (double) (180F / (float) Math.PI));
            this.rotationPitch = (float) (MathHelper.atan2(vec3d.y, f) * (double) (180F / (float) Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }
        BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
        BlockState blockstate = this.world.getBlockState(blockpos);
        if (!blockstate.isAir(this.world, blockpos) && !flag) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
            if (!voxelshape.isEmpty()) {
                for (AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList()) {
                    if (axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }
        if (this.arrowShake > 0) {
            --this.arrowShake;
        }
        if (this.isWet()) {
            this.extinguish();
        }
        if (this.inGround && !flag) {
            if (this.inBlockState != blockstate && this.world.areCollisionShapesEmpty(this.getBoundingBox().grow(0.06D))) {
                this.inGround = false;
                this.setMotion(vec3d.mul((this.rand.nextFloat() * 0.2F), (this.rand.nextFloat() * 0.2F), (this.rand.nextFloat() * 0.2F)));
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            } else if (!this.world.isRemote) {
                this.tryDespawn();
            }

            ++this.timeInGround;
        } else {
            this.timeInGround = 0;
            ++this.ticksInAir;
            Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3d2 = vec3d1.add(vec3d);
            RayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d2, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
            if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
                vec3d2 = raytraceresult.getHitVec();
            }
            while (this.isAlive()) {
                EntityRayTraceResult entityraytraceresult = this.func_213866_a(vec3d1, vec3d2);
                if (entityraytraceresult != null) {
                    raytraceresult = entityraytraceresult;
                }
                if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
                    Entity entity = ((EntityRayTraceResult) raytraceresult).getEntity();
                    Entity entity1 = this.getShooter();
                    if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity) entity1).canAttackPlayer((PlayerEntity) entity)) {
                        raytraceresult = null;
                        entityraytraceresult = null;
                    }
                }
                if (raytraceresult != null && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                    this.onHit(raytraceresult);
                    this.isAirBorne = true;
                }
                if (entityraytraceresult == null || this.getPierceLevel() <= 0) {
                    break;
                }
                raytraceresult = null;
            }
            vec3d = this.getMotion();
            double d1 = vec3d.x;
            double d2 = vec3d.y;
            double d0 = vec3d.z;
            if (this.getIsCritical()) {
                for (int i = 0; i < 4; ++i) {
                    this.world.addParticle(ParticleTypes.CRIT, this.posX + d1 * (double) i / 4.0D, this.posY + d2 * (double) i / 4.0D, this.posZ + d0 * (double) i / 4.0D, -d1, -d2 + 0.2D, -d0);
                }
            }
            this.posX += d1;
            this.posY += d2;
            this.posZ += d0;
            float f4 = MathHelper.sqrt(horizontalMag(vec3d));
            if (flag) {
                this.rotationYaw = (float) (MathHelper.atan2(-d1, -d0) * (double) (180F / (float) Math.PI));
            } else {
                this.rotationYaw = (float) (MathHelper.atan2(d1, d0) * (double) (180F / (float) Math.PI));
            }
            this.rotationPitch = (float) (MathHelper.atan2(d2, f4) * (double) (180F / (float) Math.PI));
            while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
                this.prevRotationPitch -= 360.0F;
            }
            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }
            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }
            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }
            this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
            this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
            float f1 = 0.99F;
            if (this.isInWater()) {
                this.world.addParticle(ParticleTypes.BUBBLE, this.posX - d1 * 0.25D, this.posY - d2 * 0.25D, this.posZ - d0 * 0.25D, d1, d2, d0);
                // f1 = this.getWaterDrag();
            }
            this.setMotion(vec3d.scale(f1));
            if (!this.hasNoGravity() && !flag) {
                Vec3d vec3d3 = this.getMotion();
                this.setMotion(vec3d3.x, vec3d3.y - (this.isInWater() ? 0.01D : 0.05D), vec3d3.z);
            }
            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

}