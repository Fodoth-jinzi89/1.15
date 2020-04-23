package cofh.lib.item;

import cofh.lib.util.helpers.SecurityHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.TRUE;

public class ArmorItemCoFH extends ArmorItem {

    protected Supplier<Boolean> showEnchantEffect = TRUE;
    protected Supplier<Boolean> showInItemGroup = TRUE;

    protected Supplier<ItemGroup> displayGroup;

    public ArmorItemCoFH(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {

        super(materialIn, slot, builder);
    }

    public ArmorItemCoFH setDisplayGroup(Supplier<ItemGroup> displayGroup) {

        this.displayGroup = displayGroup;
        return this;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (!showInItemGroup.get()) {
            return;
        }
        super.fillItemGroup(group, items);
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
    protected boolean isInGroup(ItemGroup group) {

        return group == ItemGroup.SEARCH || getCreativeTabs().stream().anyMatch(tab -> tab == group);
    }

    @Override
    public Collection<ItemGroup> getCreativeTabs() {

        return displayGroup != null && displayGroup.get() != null ? Collections.singletonList(displayGroup.get()) : super.getCreativeTabs();
    }

}
