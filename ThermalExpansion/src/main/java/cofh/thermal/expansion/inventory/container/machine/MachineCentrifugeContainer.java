package cofh.thermal.expansion.inventory.container.machine;

import cofh.core.inventory.InvWrapperCoFH;
import cofh.core.inventory.container.TileContainer;
import cofh.core.inventory.container.slot.SlotCoFH;
import cofh.core.inventory.container.slot.SlotRemoveOnly;
import cofh.thermal.core.tileentity.ReconfigurableTile4Way;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CENTRIFUGE_CONTAINER;

public class MachineCentrifugeContainer extends TileContainer {

    public final ReconfigurableTile4Way tile;

    public MachineCentrifugeContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(MACHINE_CENTRIFUGE_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (ReconfigurableTile4Way) world.getTileEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 44, 26));

        addSlot(new SlotRemoveOnly(tileInv, 1, 107, 26));
        addSlot(new SlotRemoveOnly(tileInv, 2, 125, 26));
        addSlot(new SlotRemoveOnly(tileInv, 3, 107, 44));
        addSlot(new SlotRemoveOnly(tileInv, 4, 125, 44));

        addSlot(new SlotCoFH(tileInv, 5, 8, 53));

        bindAugmentSlots(tileInv, 6, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
