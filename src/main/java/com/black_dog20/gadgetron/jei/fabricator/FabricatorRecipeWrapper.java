package com.black_dog20.gadgetron.jei.fabricator;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

public class FabricatorRecipeWrapper extends BlankRecipeWrapper
{

    private final List<List<ItemStack>> inputs = new ArrayList<>();
	private final ItemStack output;

	public FabricatorRecipeWrapper(List<ItemStack> inputs, List<ItemStack> inputsTwo, ItemStack output) {
		this.inputs.add(inputs);
		this.inputs.add(inputsTwo);
		this.output = output;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, output);
	}
}
