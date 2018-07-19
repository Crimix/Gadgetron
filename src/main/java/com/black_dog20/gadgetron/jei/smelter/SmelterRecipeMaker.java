package com.black_dog20.gadgetron.jei.smelter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.black_dog20.gadgetron.recipehandler.SmelterRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public final class SmelterRecipeMaker {

	private SmelterRecipeMaker() {
	}

	public static List<SmelterRecipeWrapper> getRecipes(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		SmelterRecipes smelter = SmelterRecipes.instance();
		Map<ItemStack, FluidStack> map = smelter.getRecipeList();

		List<SmelterRecipeWrapper> recipes = new ArrayList<>();

		for (Map.Entry<ItemStack, FluidStack> entry : map.entrySet()) {
			ItemStack input = entry.getKey();
			FluidStack output = entry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			SmelterRecipeWrapper recipe = new SmelterRecipeWrapper(inputs, output);
			recipes.add(recipe);
		}

		return recipes;
	}

}
