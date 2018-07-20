package com.black_dog20.gadgetron.jei.extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.black_dog20.gadgetron.recipehandler.ExtractorRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class ExtractorRecipeMaker {

	private ExtractorRecipeMaker() {
	}

	public static List<ExtractorRecipeWrapper> getRecipes(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		ExtractorRecipes machineRecipes = ExtractorRecipes.instance();
		Map<ItemStack, ItemStack> map = machineRecipes.getRecipeList();
		Map<String, ItemStack> mapOre = machineRecipes.getRecipeOreList();

		List<ExtractorRecipeWrapper> recipes = new ArrayList<>();

		for (Map.Entry<ItemStack, ItemStack> entry : map.entrySet()) {
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			ExtractorRecipeWrapper recipe = new ExtractorRecipeWrapper(inputs, output);
			recipes.add(recipe);
		}
		
		for (Map.Entry<String, ItemStack> entry : mapOre.entrySet()) {
			ItemStack output = entry.getValue();

			List<ItemStack> inputs = OreDictionary.getOres(entry.getKey(), true);
			if(!inputs.isEmpty()) {
				ExtractorRecipeWrapper recipe = new ExtractorRecipeWrapper(inputs, output);
				recipes.add(recipe);
			}
		}

		return recipes;
	}

}
