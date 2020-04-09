package cofh.thermal.locomotion.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.init.ThermalReferences.*;

public class TLocLootTables extends LootTableProviderCoFH {

    public TLocLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "Thermal Locomotion: Loot Tables";
    }

    @Override
    protected void addTables() {

        lootTables.put(BLOCKS.get(ID_CROSSOVER_RAIL), createSelfDropTable(BLOCKS.get(ID_CROSSOVER_RAIL)));

        lootTables.put(BLOCKS.get(ID_PRISMARINE_RAIL), createSelfDropTable(BLOCKS.get(ID_PRISMARINE_RAIL)));
        lootTables.put(BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL), createSelfDropTable(BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL)));
        lootTables.put(BLOCKS.get(ID_PRISMARINE_POWERED_RAIL), createSelfDropTable(BLOCKS.get(ID_PRISMARINE_POWERED_RAIL)));
        lootTables.put(BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL), createSelfDropTable(BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL)));
        lootTables.put(BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL), createSelfDropTable(BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL)));

        lootTables.put(BLOCKS.get(ID_LUMIUM_RAIL), createSelfDropTable(BLOCKS.get(ID_LUMIUM_RAIL)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL), createSelfDropTable(BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_POWERED_RAIL), createSelfDropTable(BLOCKS.get(ID_LUMIUM_POWERED_RAIL)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL), createSelfDropTable(BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL), createSelfDropTable(BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL)));
    }

}
