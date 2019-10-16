package cofh.lib.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static cofh.lib.capability.CapabilityShield.SHIELD_ITEM_CAPABILITY;

public class ShieldEvents {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(ShieldEvents.class);
        registered = true;
    }

    private ShieldEvents() {

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handleLivingAttackEvent(LivingAttackEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();

        if (!canBlockDamageSource(entity, source)) {
            return;
        }
        if (source instanceof EntityDamageSource && ((EntityDamageSource) source).getIsThornsDamage()) {
            return;
        }
        ItemStack shield = entity.getActiveItemStack();
        shield.getCapability(SHIELD_ITEM_CAPABILITY).ifPresent(cap -> cap.onBlock(shield, entity, source));
    }

    // region HELPERS
    private static boolean canBlockDamageSource(LivingEntity living, DamageSource source) {

        Entity entity = source.getImmediateSource();
        if (entity instanceof AbstractArrowEntity) {
            AbstractArrowEntity arrow = (AbstractArrowEntity) entity;
            if (arrow.getPierceLevel() > 0) {
                return false;
            }
        }
        if (!source.isUnblockable() && living.isActiveItemStackBlocking()) {
            Vec3d vec3d2 = source.getDamageLocation();
            if (vec3d2 != null) {
                Vec3d vec3d = living.getLook(1.0F);
                Vec3d vec3d1 = vec3d2.subtractReverse(new Vec3d(living.posX, living.posY, living.posZ)).normalize();
                vec3d1 = new Vec3d(vec3d1.x, 0.0D, vec3d1.z);
                return vec3d1.dotProduct(vec3d) < 0.0D;
            }
        }
        return false;
    }
    // endregion
}