package com.black_dog20.gadgetron.jei.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.black_dog20.gadgetron.recipehandler.ProcessorRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public final class ProcessorRecipeMaker {

	private ProcessorRecipeMaker() {
	}

	public static List<ProcessorRecipeWrapper> getRecipes(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		ProcessorRecipes machineRecipes = ProcessorRecipes.instance();
		Map<ItemStack, ItemStack> map = machineRecipes.getRecipeList();

		List<ProcessorRecipeWrapper> recipes = new ArrayList<>();

		for (Map.Entry<ItemStack, ItemStack> entry : map.entrySet()) {
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			ProcessorRecipeWrapper recipe = new ProcessorRecipeWrapper(inputs, output);
			recipes.add(recipe);
		}

		return recipes;
	}

}
