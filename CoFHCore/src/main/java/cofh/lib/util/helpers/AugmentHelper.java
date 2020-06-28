package cofh.lib.util.helpers;

import cofh.lib.item.IAugmentableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cofh.lib.util.constants.Constants.MAX_AUGMENTS;
import static cofh.lib.util.constants.NBTTags.TAG_AUGMENTS;
import static cofh.lib.util.constants.NBTTags.TAG_BLOCK_ENTITY;
import static net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND;

public class AugmentHelper {

    private AugmentHelper() {

    }

    public static boolean isAugmentable(ItemStack item) {

        return !item.isEmpty() && item.getItem() instanceof IAugmentableItem;
    }

    public static int getAugmentSlots(ItemStack item) {

        return !isAugmentable(item) ? 0 : MathHelper.clamp(((IAugmentableItem) item.getItem()).getAugmentSlots(item), 0, MAX_AUGMENTS);
    }

    public static List<ItemStack> getAugments(ItemStack stack) {

        ListNBT augmentTag = getAugmentNBT(stack);
        if (augmentTag.isEmpty()) {
            return Collections.emptyList();
        }
        return getAugments(augmentTag);
    }

    public static boolean validAugment(ItemStack augmentable, ItemStack augment) {

        return isAugmentable(augmentable) && ((IAugmentableItem) augmentable.getItem()).validAugment(augmentable, augment);
    }

    public static ListNBT getAugmentNBT(ItemStack stack) {

        if (stack.getTag() == null) {
            return new ListNBT();
        }
        CompoundNBT blockTag = stack.getChildTag(TAG_BLOCK_ENTITY);
        if (blockTag != null) {
            return blockTag.contains(TAG_AUGMENTS) ? blockTag.getList(TAG_AUGMENTS, TAG_COMPOUND) : new ListNBT();
        }
        return stack.getTag().getList(TAG_AUGMENTS, TAG_COMPOUND);
    }

    public static List<ItemStack> getAugments(ListNBT list) {

        ArrayList<ItemStack> ret = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            ret.add(ItemStack.read(list.getCompound(i)));
        }
        return ret.isEmpty() ? Collections.emptyList() : ret;
    }

}