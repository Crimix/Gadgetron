package com.black_dog20.gadgetron.jei.smelter;

import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class SmelterRecipeWrapper implements IRecipeWrapper
{

    private final List<List<ItemStack>> inputs;
	private final FluidStack output;

	public SmelterRecipeWrapper(List<ItemStack> inputs, FluidStack output) {
		this.inputs = Collections.singletonList(inputs);
		this.output = output;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutput(FluidStack.class, output);
	}
}
