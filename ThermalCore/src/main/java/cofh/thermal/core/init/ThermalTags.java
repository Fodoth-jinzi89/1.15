package cofh.thermal.core.init;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.constants.Constants.ID_FORGE;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class ThermalTags {

    public static class Blocks {

        public static final Tag<Block> ORES_COPPER = forgeTag("ores/copper");
        public static final Tag<Block> ORES_TIN = forgeTag("ores/tin");
        public static final Tag<Block> ORES_SILVER = forgeTag("ores/silver");
        public static final Tag<Block> ORES_LEAD = forgeTag("ores/lead");
        public static final Tag<Block> ORES_NICKEL = forgeTag("ores/nickel");
        public static final Tag<Block> ORES_PLATINUM = forgeTag("ores/platinum");

        public static final Tag<Block> ORES_RUBY = forgeTag("ores/ruby");
        public static final Tag<Block> ORES_SAPPHIRE = forgeTag("ores/sapphire");

        public static final Tag<Block> STORAGE_BLOCKS_SIGNALUM = forgeTag("storage_blocks/signalum");
        public static final Tag<Block> STORAGE_BLOCKS_LUMIUM = forgeTag("storage_blocks/lumium");
        public static final Tag<Block> STORAGE_BLOCKS_ENDERIUM = forgeTag("storage_blocks/enderium");

        public static final Tag<Block> STORAGE_BLOCKS_COPPER = forgeTag("storage_blocks/copper");
        public static final Tag<Block> STORAGE_BLOCKS_TIN = forgeTag("storage_blocks/tin");
        public static final Tag<Block> STORAGE_BLOCKS_SILVER = forgeTag("storage_blocks/silver");
        public static final Tag<Block> STORAGE_BLOCKS_LEAD = forgeTag("storage_blocks/lead");
        public static final Tag<Block> STORAGE_BLOCKS_NICKEL = forgeTag("storage_blocks/nickel");
        public static final Tag<Block> STORAGE_BLOCKS_PLATINUM = forgeTag("storage_blocks/platinum");

        public static final Tag<Block> STORAGE_BLOCKS_BRONZE = forgeTag("storage_blocks/bronze");
        public static final Tag<Block> STORAGE_BLOCKS_ELECTRUM = forgeTag("storage_blocks/electrum");
        public static final Tag<Block> STORAGE_BLOCKS_INVAR = forgeTag("storage_blocks/invar");
        public static final Tag<Block> STORAGE_BLOCKS_CONSTANTAN = forgeTag("storage_blocks/constantan");

        public static final Tag<Block> STORAGE_BLOCKS_RUBY = forgeTag("storage_blocks/ruby");
        public static final Tag<Block> STORAGE_BLOCKS_SAPPHIRE = forgeTag("storage_blocks/sapphire");

        // region HELPERS
        private static Tag<Block> tag(String name) {

            return new BlockTags.Wrapper(new ResourceLocation(ID_THERMAL, name));
        }

        private static Tag<Block> forgeTag(String name) {

            return new BlockTags.Wrapper(new ResourceLocation(ID_FORGE, name));
        }
        // endregion
    }

    public static class Items {

        public static final Tag<Item> ORES_COPPER = forgeTag("ores/copper");
        public static final Tag<Item> ORES_TIN = forgeTag("ores/tin");
        public static final Tag<Item> ORES_SILVER = forgeTag("ores/silver");
        public static final Tag<Item> ORES_LEAD = forgeTag("ores/lead");
        public static final Tag<Item> ORES_NICKEL = forgeTag("ores/nickel");
        public static final Tag<Item> ORES_PLATINUM = forgeTag("ores/platinum");

        public static final Tag<Item> ORES_RUBY = forgeTag("ores/ruby");
        public static final Tag<Item> ORES_SAPPHIRE = forgeTag("ores/sapphire");

        public static final Tag<Item> STORAGE_BLOCKS_SIGNALUM = forgeTag("storage_blocks/signalum");
        public static final Tag<Item> STORAGE_BLOCKS_LUMIUM = forgeTag("storage_blocks/lumium");
        public static final Tag<Item> STORAGE_BLOCKS_ENDERIUM = forgeTag("storage_blocks/enderium");

        public static final Tag<Item> STORAGE_BLOCKS_COPPER = forgeTag("storage_blocks/copper");
        public static final Tag<Item> STORAGE_BLOCKS_TIN = forgeTag("storage_blocks/tin");
        public static final Tag<Item> STORAGE_BLOCKS_SILVER = forgeTag("storage_blocks/silver");
        public static final Tag<Item> STORAGE_BLOCKS_LEAD = forgeTag("storage_blocks/lead");
        public static final Tag<Item> STORAGE_BLOCKS_NICKEL = forgeTag("storage_blocks/nickel");
        public static final Tag<Item> STORAGE_BLOCKS_PLATINUM = forgeTag("storage_blocks/platinum");

        public static final Tag<Item> STORAGE_BLOCKS_BRONZE = forgeTag("storage_blocks/bronze");
        public static final Tag<Item> STORAGE_BLOCKS_ELECTRUM = forgeTag("storage_blocks/electrum");
        public static final Tag<Item> STORAGE_BLOCKS_INVAR = forgeTag("storage_blocks/invar");
        public static final Tag<Item> STORAGE_BLOCKS_CONSTANTAN = forgeTag("storage_blocks/constantan");

        public static final Tag<Item> STORAGE_BLOCKS_RUBY = forgeTag("storage_blocks/ruby");
        public static final Tag<Item> STORAGE_BLOCKS_SAPPHIRE = forgeTag("storage_blocks/sapphire");

        public static final Tag<Item> COINS_SIGNALUM = forgeTag("coins/signalum");
        public static final Tag<Item> COINS_LUMIUM = forgeTag("coins/lumium");
        public static final Tag<Item> COINS_ENDERIUM = forgeTag("coins/enderium");

        public static final Tag<Item> GEARS_SIGNALUM = forgeTag("gears/signalum");
        public static final Tag<Item> GEARS_LUMIUM = forgeTag("gears/lumium");
        public static final Tag<Item> GEARS_ENDERIUM = forgeTag("gears/enderium");

        public static final Tag<Item> INGOTS_SIGNALUM = forgeTag("ingots/signalum");
        public static final Tag<Item> INGOTS_LUMIUM = forgeTag("ingots/lumium");
        public static final Tag<Item> INGOTS_ENDERIUM = forgeTag("ingots/enderium");

        public static final Tag<Item> NUGGETS_SIGNALUM = forgeTag("nuggets/signalum");
        public static final Tag<Item> NUGGETS_LUMIUM = forgeTag("nuggets/lumium");
        public static final Tag<Item> NUGGETS_ENDERIUM = forgeTag("nuggets/enderium");

        public static final Tag<Item> PLATES_SIGNALUM = forgeTag("plates/signalum");
        public static final Tag<Item> PLATES_LUMIUM = forgeTag("plates/lumium");
        public static final Tag<Item> PLATES_ENDERIUM = forgeTag("plates/enderium");

        public static final Tag<Item> COINS_COPPER = forgeTag("coins/copper");
        public static final Tag<Item> COINS_TIN = forgeTag("coins/tin");
        public static final Tag<Item> COINS_SILVER = forgeTag("coins/silver");
        public static final Tag<Item> COINS_LEAD = forgeTag("coins/lead");
        public static final Tag<Item> COINS_NICKEL = forgeTag("coins/nickel");
        public static final Tag<Item> COINS_PLATINUM = forgeTag("coins/platinum");

        public static final Tag<Item> COINS_BRONZE = forgeTag("coins/bronze");
        public static final Tag<Item> COINS_ELECTRUM = forgeTag("coins/electrum");
        public static final Tag<Item> COINS_INVAR = forgeTag("coins/invar");
        public static final Tag<Item> COINS_CONSTANTAN = forgeTag("coins/constantan");

        public static final Tag<Item> GEARS_COPPER = forgeTag("gears/copper");
        public static final Tag<Item> GEARS_TIN = forgeTag("gears/tin");
        public static final Tag<Item> GEARS_SILVER = forgeTag("gears/silver");
        public static final Tag<Item> GEARS_LEAD = forgeTag("gears/lead");
        public static final Tag<Item> GEARS_NICKEL = forgeTag("gears/nickel");
        public static final Tag<Item> GEARS_PLATINUM = forgeTag("gears/platinum");

        public static final Tag<Item> GEARS_BRONZE = forgeTag("gears/bronze");
        public static final Tag<Item> GEARS_ELECTRUM = forgeTag("gears/electrum");
        public static final Tag<Item> GEARS_INVAR = forgeTag("gears/invar");
        public static final Tag<Item> GEARS_CONSTANTAN = forgeTag("gears/constantan");

        public static final Tag<Item> INGOTS_COPPER = forgeTag("ingots/copper");
        public static final Tag<Item> INGOTS_TIN = forgeTag("ingots/tin");
        public static final Tag<Item> INGOTS_SILVER = forgeTag("ingots/silver");
        public static final Tag<Item> INGOTS_LEAD = forgeTag("ingots/lead");
        public static final Tag<Item> INGOTS_NICKEL = forgeTag("ingots/nickel");
        public static final Tag<Item> INGOTS_PLATINUM = forgeTag("ingots/platinum");

        public static final Tag<Item> INGOTS_BRONZE = forgeTag("ingots/bronze");
        public static final Tag<Item> INGOTS_ELECTRUM = forgeTag("ingots/electrum");
        public static final Tag<Item> INGOTS_INVAR = forgeTag("ingots/invar");
        public static final Tag<Item> INGOTS_CONSTANTAN = forgeTag("ingots/constantan");

        public static final Tag<Item> NUGGETS_COPPER = forgeTag("nuggets/copper");
        public static final Tag<Item> NUGGETS_TIN = forgeTag("nuggets/tin");
        public static final Tag<Item> NUGGETS_SILVER = forgeTag("nuggets/silver");
        public static final Tag<Item> NUGGETS_LEAD = forgeTag("nuggets/lead");
        public static final Tag<Item> NUGGETS_NICKEL = forgeTag("nuggets/nickel");
        public static final Tag<Item> NUGGETS_PLATINUM = forgeTag("nuggets/platinum");

        public static final Tag<Item> NUGGETS_BRONZE = forgeTag("nuggets/bronze");
        public static final Tag<Item> NUGGETS_ELECTRUM = forgeTag("nuggets/electrum");
        public static final Tag<Item> NUGGETS_INVAR = forgeTag("nuggets/invar");
        public static final Tag<Item> NUGGETS_CONSTANTAN = forgeTag("nuggets/constantan");

        public static final Tag<Item> PLATES_COPPER = forgeTag("plates/copper");
        public static final Tag<Item> PLATES_TIN = forgeTag("plates/tin");
        public static final Tag<Item> PLATES_SILVER = forgeTag("plates/silver");
        public static final Tag<Item> PLATES_LEAD = forgeTag("plates/lead");
        public static final Tag<Item> PLATES_NICKEL = forgeTag("plates/nickel");
        public static final Tag<Item> PLATES_PLATINUM = forgeTag("plates/platinum");

        public static final Tag<Item> PLATES_BRONZE = forgeTag("plates/bronze");
        public static final Tag<Item> PLATES_ELECTRUM = forgeTag("plates/electrum");
        public static final Tag<Item> PLATES_INVAR = forgeTag("plates/invar");
        public static final Tag<Item> PLATES_CONSTANTAN = forgeTag("plates/constantan");

        public static final Tag<Item> GEARS_RUBY = forgeTag("gears/ruby");
        public static final Tag<Item> GEARS_SAPPHIRE = forgeTag("gears/sapphire");

        public static final Tag<Item> GEMS_RUBY = forgeTag("gems/ruby");
        public static final Tag<Item> GEMS_SAPPHIRE = forgeTag("gems/sapphire");

        public static final Tag<Item> NUGGETS_RUBY = forgeTag("nuggets/ruby");
        public static final Tag<Item> NUGGETS_SAPPHIRE = forgeTag("nuggets/sapphire");

        public static final Tag<Item> PLATES_RUBY = forgeTag("plates/ruby");
        public static final Tag<Item> PLATES_SAPPHIRE = forgeTag("plates/sapphire");

        public static final Tag<Item> MACHINE_DIES = tag("crafting/dies");
        public static final Tag<Item> MACHINE_MOLDS = tag("crafting/molds");

        // region HELPERS
        private static Tag<Item> tag(String name) {

            return new ItemTags.Wrapper(new ResourceLocation(ID_THERMAL, name));
        }

        private static Tag<Item> forgeTag(String name) {

            return new ItemTags.Wrapper(new ResourceLocation(ID_FORGE, name));
        }
        // endregion
    }

    public static class Fluids {

        public static final Tag<Fluid> REDSTONE = forgeTag("redstone");
        public static final Tag<Fluid> GLOWSTONE = forgeTag("glowstone");
        public static final Tag<Fluid> ENDER = forgeTag("ender");

        // region HELPERS
        private static Tag<Fluid> tag(String name) {

            return new FluidTags.Wrapper(new ResourceLocation(ID_THERMAL, name));
        }

        private static Tag<Fluid> forgeTag(String name) {

            return new FluidTags.Wrapper(new ResourceLocation(ID_FORGE, name));
        }
        // endregion
    }

}
