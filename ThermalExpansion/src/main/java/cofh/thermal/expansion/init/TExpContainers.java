package cofh.thermal.expansion.init;

import cofh.core.util.ProxyUtils;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoLapidaryContainer;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoNumismaticContainer;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoStirlingContainer;
import cofh.thermal.expansion.inventory.container.machine.*;
import net.minecraftforge.common.extensions.IForgeContainerType;

import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.expansion.init.TExpReferences.*;

public class TExpContainers {

    private TExpContainers() {

    }

    public static void register() {

        CONTAINERS.register(ID_MACHINE_FURNACE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineFurnaceContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_SAWMILL, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineSawmillContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PULVERIZER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachinePulverizerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_INSOLATOR, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineInsolatorContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CENTRIFUGE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineCentrifugeContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PRESS, () -> IForgeContainerType.create((windowId, inv, data) -> new MachinePressContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CRUCIBLE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineCrucibleContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CHILLER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineChillerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_REFINERY, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineRefineryContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_BREWER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineBrewerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));

        CONTAINERS.register(ID_DYNAMO_STIRLING, () -> IForgeContainerType.create((windowId, inv, data) -> new DynamoStirlingContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_NUMISMATIC, () -> IForgeContainerType.create((windowId, inv, data) -> new DynamoNumismaticContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_LAPIDARY, () -> IForgeContainerType.create((windowId, inv, data) -> new DynamoLapidaryContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
    }

}
