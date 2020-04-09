package cofh.thermal.expansion.tileentity;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.tileentity.MachineTileProcess;
import cofh.thermal.expansion.inventory.container.MachinePressContainer;
import cofh.thermal.expansion.util.managers.machine.PressRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.thermal.core.init.ThermalTags.Items.MACHINE_DIES;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PRESS_TILE;

public class MachinePressTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(PressRecipeManager.instance()::validInput);
    protected ItemStorageCoFH dieSlot = new ItemStorageCoFH(PressRecipeManager.instance()::validDie);
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_SMALL);

    public MachinePressTile() {

        super(MACHINE_PRESS_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(dieSlot, INPUT);
        inventory.addSlots(OUTPUT, 4);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(outputTank, OUTPUT);
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = PressRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected boolean cacheRenderFluid() {

        if (curRecipe == null) {
            return false;
        }
        FluidStack prevFluid = renderFluid;
        List<FluidStack> recipeOutputFluids = curRecipe.getOutputFluids(this);
        renderFluid = recipeOutputFluids.isEmpty() ? FluidStack.EMPTY : new FluidStack(recipeOutputFluids.get(0), FluidAttributes.BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }

    @Override
    protected void resolveInputs() {

        // Input Items
        inputSlot.modify(-itemInputCounts.get(0));

        if (itemInputCounts.size() > 1 && !dieSlot.getItemStack().getItem().isIn(MACHINE_DIES)) {
            dieSlot.modify(-itemInputCounts.get(1));
        }
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachinePressContainer(i, world, pos, inventory, player);
    }

}