package cofh.thermal.expansion.gui.client;

import cofh.core.gui.element.ElementScaled;
import cofh.core.gui.element.ElementScaled.StartDirection;
import cofh.core.gui.element.ElementScaledFluid;
import cofh.core.util.GuiHelper;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.gui.client.MachineScreenBase;
import cofh.thermal.expansion.inventory.container.MachinePressContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachinePressScreen extends MachineScreenBase<MachinePressContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/press.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachinePressScreen(MachinePressContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_press"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_press");
    }

    @Override
    public void init() {

        super.init();

        addElement(GuiHelper.createLargeFluidStorage(this, 151, 8, tile.getTank(0)));

        progressOverlay = (ElementScaledFluid) addElement(new ElementScaledFluid(this, 79, 34).setFluid(tile.getRenderFluid()).setDirection(StartDirection.LEFT).setSize(PROGRESS, 16).setTexture(PROG_ARROW_FLUID_RIGHT, 64, 16).setVisible(() -> !tile.getRenderFluid().isEmpty()));
        progress = (ElementScaled) addElement(new ElementScaled(this, 79, 34).setDirection(StartDirection.LEFT).setSize(PROGRESS, 16).setTexture(PROG_ARROW_RIGHT, 64, 16).setVisible(() -> tile.getRenderFluid().isEmpty()));
        speed = (ElementScaled) addElement(new ElementScaled(this, 53, 35).setSize(16, SPEED).setTexture(SCALE_COMPACT, 32, 16));
    }

}
