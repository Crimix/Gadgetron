package com.black_dog20.gadgetron.jei.extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.black_dog20.gadgetron.recipehandler.ExtractorRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public final class ExtractorRecipeMaker {

	private ExtractorRecipeMaker() {
	}

	public static List<ExtractorRecipeWrapper> getRecipes(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		ExtractorRecipes machineRecipes = ExtractorRecipes.instance();
		Map<ItemStack, ItemStack> map = machineRecipes.getRecipeList();

		List<ExtractorRecipeWrapper> recipes = new ArrayList<>();

		for (Map.Entry<ItemStack, ItemStack> entry : map.entrySet()) {
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			ExtractorRecipeWrapper recipe = new ExtractorRecipeWrapper(inputs, output);
			recipes.add(recipe);
		}

		return recipes;
	}

}
