package cofh.thermal.expansion;

import cofh.thermal.core.init.ThermalRecipeManager;
import cofh.thermal.expansion.gui.client.*;
import cofh.thermal.expansion.init.TExpBlocks;
import cofh.thermal.expansion.init.TExpItems;
import cofh.thermal.expansion.init.TExpRecipes;
import cofh.thermal.expansion.util.managers.machine.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_EXPANSION;
import static cofh.thermal.expansion.init.TExpReferences.*;

@Mod(ID_THERMAL_EXPANSION)
public class ThermalExpansion {

    public ThermalExpansion() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);

        TExpBlocks.register();
        TExpItems.register();
        TExpRecipes.register();

        ThermalRecipeManager.register(FurnaceRecipeManager.instance());
        ThermalRecipeManager.register(SawmillRecipeManager.instance());
        ThermalRecipeManager.register(PulverizerRecipeManager.instance());
        ThermalRecipeManager.register(InsolatorRecipeManager.instance());
        ThermalRecipeManager.register(CentrifugeRecipeManager.instance());
        ThermalRecipeManager.register(PressRecipeManager.instance());
        ThermalRecipeManager.register(CrucibleRecipeManager.instance());
        ThermalRecipeManager.register(ChillerRecipeManager.instance());
        ThermalRecipeManager.register(RefineryRecipeManager.instance());
        ThermalRecipeManager.register(BrewerRecipeManager.instance());

        // TODO: TESTING ONLY
        PotionBrewing.addMix(Potions.WATER, Items.APPLE, Potions.LEAPING);
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ScreenManager.registerFactory(MACHINE_FURNACE_CONTAINER, MachineFurnaceScreen::new);
        ScreenManager.registerFactory(MACHINE_SAWMILL_CONTAINER, MachineSawmillScreen::new);
        ScreenManager.registerFactory(MACHINE_PULVERIZER_CONTAINER, MachinePulverizerScreen::new);
        ScreenManager.registerFactory(MACHINE_INSOLATOR_CONTAINER, MachineInsolatorScreen::new);
        ScreenManager.registerFactory(MACHINE_CENTRIFUGE_CONTAINER, MachineCentrifugeScreen::new);
        ScreenManager.registerFactory(MACHINE_PRESS_CONTAINER, MachinePressScreen::new);
        ScreenManager.registerFactory(MACHINE_CRUCIBLE_CONTAINER, MachineCrucibleScreen::new);
        ScreenManager.registerFactory(MACHINE_CHILLER_CONTAINER, MachineChillerScreen::new);
        ScreenManager.registerFactory(MACHINE_REFINERY_CONTAINER, MachineRefineryScreen::new);
        ScreenManager.registerFactory(MACHINE_BREWER_CONTAINER, MachineBrewerScreen::new);
    }
    // endregion

    // region DATA
    private void gatherData(final GatherDataEvent event) {

        if (event.includeServer()) {
            registerServerProviders(event.getGenerator());
        }
        if (event.includeClient()) {
            registerClientProviders(event.getGenerator(), event);
        }
    }

    private void registerServerProviders(DataGenerator generator) {

        //        generator.addProvider(new TCulTags.Block(generator));
        //        generator.addProvider(new TCulTags.Item(generator));
        //        generator.addProvider(new TCulTags.Fluid(generator));
        //
        //        generator.addProvider(new TCulLootTables(generator));
        //        generator.addProvider(new TCulRecipes(generator));
    }

    private void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

    }
    // endregion
}
