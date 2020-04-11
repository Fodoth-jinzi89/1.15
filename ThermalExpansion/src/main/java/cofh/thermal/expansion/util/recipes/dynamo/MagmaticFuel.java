package cofh.thermal.expansion.util.recipes.dynamo;

import cofh.thermal.core.util.recipes.ThermalFuel;
import cofh.thermal.expansion.init.TExpRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_MAGMATIC_BLOCK;
import static cofh.thermal.expansion.init.TExpReferences.ID_FUEL_MAGMATIC;

public class MagmaticFuel extends ThermalFuel {

    public MagmaticFuel(ResourceLocation recipeId, int energy, @Nullable List<Ingredient> inputItems, @Nullable List<FluidStack> inputFluids) {

        super(recipeId, energy, inputItems, inputFluids);
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_FUEL_MAGMATIC);
    }

    @Nonnull
    @Override
    public IRecipeType<?> getType() {

        return TExpRecipeTypes.FUEL_MAGMATIC;
    }

    @Nonnull
    @Override
    public String getGroup() {

        return DYNAMO_MAGMATIC_BLOCK.getTranslationKey();
    }

    @Nonnull
    @Override
    public ItemStack getIcon() {

        return new ItemStack(DYNAMO_MAGMATIC_BLOCK);
    }

}
