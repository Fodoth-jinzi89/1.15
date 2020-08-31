package cofh.archersparadox.item.projectile;

import cofh.archersparadox.entity.projectile.MagmaArrowEntity;
import cofh.core.item.ArrowItemCoFH;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MagmaArrowItem extends ArrowItemCoFH {

    public MagmaArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new MagmaArrowEntity(worldIn, shooter);
    }

}
