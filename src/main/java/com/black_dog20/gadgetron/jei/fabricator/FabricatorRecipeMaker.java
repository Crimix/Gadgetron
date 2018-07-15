package com.black_dog20.gadgetron.jei.fabricator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.black_dog20.gadgetron.recipehandler.FabricatorRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

public final class FabricatorRecipeMaker {

	private FabricatorRecipeMaker() {
	}

	public static List<FabricatorRecipeWrapper> getRecipes(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		FabricatorRecipes machineRecipes = FabricatorRecipes.instance();
		Map<Tuple<ItemStack, ItemStack>, ItemStack> map = machineRecipes.getRecipeList();

		List<FabricatorRecipeWrapper> recipes = new ArrayList<>();

		for (Entry<Tuple<ItemStack, ItemStack>, ItemStack> entry : map.entrySet()) {
			Tuple<ItemStack, ItemStack> input = entry.getKey();
			ItemStack output = entry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input.getFirst());
			List<ItemStack> inputsTwo = stackHelper.getSubtypes(input.getSecond());
			FabricatorRecipeWrapper recipe = new FabricatorRecipeWrapper(inputs, inputsTwo, output);
			recipes.add(recipe);
		}

		return recipes;
	}

}
