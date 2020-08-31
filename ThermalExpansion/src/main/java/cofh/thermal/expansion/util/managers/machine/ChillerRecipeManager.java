package cofh.thermal.expansion.util.managers.machine;

import cofh.core.fluid.IFluidStackAccess;
import cofh.core.inventory.FalseIInventory;
import cofh.core.inventory.IItemStackAccess;
import cofh.core.util.ComparableItemStack;
import cofh.core.util.helpers.FluidHelper;
import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.managers.AbstractManager;
import cofh.thermal.core.util.managers.IRecipeManager;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.core.util.recipes.internal.SimpleMachineRecipe;
import cofh.thermal.expansion.init.TExpRecipeTypes;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;

import static java.util.Arrays.asList;

public class ChillerRecipeManager extends AbstractManager implements IRecipeManager {

    private static final ChillerRecipeManager INSTANCE = new ChillerRecipeManager();
    protected static final int DEFAULT_ENERGY = 4000;

    protected Map<List<Integer>, IMachineRecipe> recipeMap = new Object2ObjectOpenHashMap<>();
    protected Set<Fluid> validFluids = new ObjectOpenHashSet<>();
    protected Set<ComparableItemStack> validItems = new ObjectOpenHashSet<>();

    protected int maxOutputItems;
    protected int maxOutputFluids;

    public static ChillerRecipeManager instance() {

        return INSTANCE;
    }

    private ChillerRecipeManager() {

        super(DEFAULT_ENERGY);
        this.maxOutputItems = 1;
        this.maxOutputFluids = 0;
    }

    public void addRecipe(ThermalRecipe recipe) {

        if (!recipe.getInputItems().isEmpty()) {
            for (ItemStack recipeInput : recipe.getInputItems().get(0).getMatchingStacks()) {
                addRecipe(recipe.getEnergy(), recipe.getExperience(), recipe.getMinTicks(), Collections.singletonList(recipeInput), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
            }
        } else {
            addRecipe(recipe.getEnergy(), recipe.getExperience(), recipe.getMinTicks(), Collections.emptyList(), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
        }
    }

    public boolean validItem(ItemStack item) {

        return validItems.contains(convert(item));
    }

    public boolean validFluid(FluidStack fluid) {

        return validFluids.contains(fluid.getFluid());
    }

    protected void clear() {

        recipeMap.clear();
        validFluids.clear();
        validItems.clear();
    }

    // region RECIPES
    protected IMachineRecipe getRecipe(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks) {

        if (inputSlots.isEmpty() && inputTanks.isEmpty() || inputSlots.get(0).isEmpty() && inputTanks.get(0).isEmpty()) {
            return null;
        }
        if (inputTanks.isEmpty() || inputTanks.get(0).isEmpty()) {
            ItemStack inputItem = inputSlots.get(0).getItemStack();
            return recipeMap.get(Collections.singletonList(convert(inputItem).hashCode()));
        }
        if (inputSlots.isEmpty() || inputSlots.get(0).isEmpty()) {
            FluidStack inputFluid = inputTanks.get(0).getFluidStack();
            return recipeMap.get(Collections.singletonList(FluidHelper.fluidHashcode(inputFluid)));
        }
        ItemStack inputItem = inputSlots.get(0).getItemStack();
        FluidStack inputFluid = inputTanks.get(0).getFluidStack();
        return recipeMap.get(asList(convert(inputItem).hashCode(), FluidHelper.fluidHashcode(inputFluid)));
    }

    protected IMachineRecipe addRecipe(int energy, float experience, int minTicks, List<ItemStack> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> chance, List<FluidStack> outputFluids) {

        if (inputItems.isEmpty() && inputFluids.isEmpty() || outputItems.size() > maxOutputItems || outputFluids.size() > maxOutputFluids || energy <= 0) {
            return null;
        }
        List<Integer> key;
        if (inputFluids.isEmpty()) {
            ItemStack inputItem = inputItems.get(0);
            if (inputItem.isEmpty()) {
                return null;
            }
            validItems.add(convert(inputItem));
            key = Collections.singletonList(convert(inputItem).hashCode());
        } else if (inputItems.isEmpty()) {
            FluidStack inputFluid = inputFluids.get(0);
            if (inputFluid.isEmpty()) {
                return null;
            }
            validFluids.add(inputFluid.getFluid());
            key = Collections.singletonList(FluidHelper.fluidHashcode(inputFluid));
        } else {
            ItemStack inputItem = inputItems.get(0);
            if (inputItem.isEmpty()) {
                return null;
            }
            FluidStack inputFluid = inputFluids.get(0);
            if (inputFluid.isEmpty()) {
                return null;
            }
            validItems.add(convert(inputItem));
            validFluids.add(inputFluid.getFluid());
            key = asList(convert(inputItem).hashCode(), FluidHelper.fluidHashcode(inputFluid));
        }
        for (ItemStack stack : outputItems) {
            if (stack.isEmpty()) {
                return null;
            }
        }
        energy = (energy * getDefaultScale()) / 100;

        SimpleMachineRecipe recipe = new SimpleMachineRecipe(energy, experience, minTicks, inputItems, inputFluids, outputItems, chance, outputFluids);
        recipeMap.put(key, recipe);
        return recipe;
    }
    // endregion

    // region IRecipeManager
    @Override
    public IMachineRecipe getRecipe(IThermalInventory inventory) {

        return getRecipe(inventory.inputSlots(), inventory.inputTanks());
    }

    @Override
    public List<IMachineRecipe> getRecipeList() {

        return new ArrayList<>(recipeMap.values());
    }
    // endregion

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipeTypes.RECIPE_CHILLER);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addRecipe((ThermalRecipe) entry.getValue());
        }
    }
    // endregion
}
