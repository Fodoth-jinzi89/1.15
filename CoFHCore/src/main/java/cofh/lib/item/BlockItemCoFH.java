package cofh.lib.item;

import cofh.lib.util.helpers.SecurityHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.TRUE;

public class BlockItemCoFH extends BlockItem {

    protected Supplier<Boolean> showEnchantEffect = TRUE;
    protected Supplier<Boolean> showInItemGroup = TRUE;
    protected boolean creative;
    protected int enchantability;

    protected Supplier<ItemGroup> displayGroup;

    public BlockItemCoFH(Block blockIn, Properties builder) {

        super(blockIn, builder);
    }

    public BlockItemCoFH setCreative(boolean creative) {

        this.creative = creative;
        return this;
    }

    public BlockItemCoFH setEnchantability(int enchantability) {

        this.enchantability = enchantability;
        return this;
    }

    public BlockItemCoFH setDisplayGroup(Supplier<ItemGroup> displayGroup) {

        this.displayGroup = displayGroup;
        return this;
    }

    public final boolean isCreative() {

        return creative;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (!showInItemGroup.get()) {
            return;
        }
        if (getBlock() != null) {
            super.fillItemGroup(group, items);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack) {

        return showEnchantEffect.get() && stack.isEnchanted();
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {

        return SecurityHelper.hasSecurity(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {

        return enchantability > 0;
    }

    @Override
    public int getItemEnchantability() {

        return enchantability;
    }

    @Nullable
    public Entity createEntity(World world, Entity location, ItemStack stack) {

        if (SecurityHelper.hasSecurity(stack)) {
            location.setInvulnerable(true);
            ((ItemEntity) location).lifespan = Integer.MAX_VALUE;
        }
        return null;
    }

    @Override
    protected boolean isInGroup(ItemGroup group) {

        return group == ItemGroup.SEARCH || getCreativeTabs().stream().anyMatch(tab -> tab == group);
    }

    @Override
    public Collection<ItemGroup> getCreativeTabs() {

        return displayGroup != null && displayGroup.get() != null ? Collections.singletonList(displayGroup.get()) : super.getCreativeTabs();
    }

}
