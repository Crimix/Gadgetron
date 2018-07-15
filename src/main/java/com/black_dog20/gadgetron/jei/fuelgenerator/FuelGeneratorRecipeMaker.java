package com.black_dog20.gadgetron.jei.fuelgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.black_dog20.gadgetron.recipehandler.FuelGeneratorHandler;
import com.black_dog20.gadgetron.recipehandler.FuelGeneratorHandler.FuelObject;

import mezz.jei.api.IJeiHelpers;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public final class FuelGeneratorRecipeMaker {

	private FuelGeneratorRecipeMaker() {
	}

	public static List<FuelGeneratorRecipeWrapper> getRecipes(IJeiHelpers helpers) {
		FuelGeneratorHandler generator = FuelGeneratorHandler.instance();
		Map<String, FuelObject> map = generator.getFuels();

		List<FuelGeneratorRecipeWrapper> recipes = new ArrayList<>();

		for (Map.Entry<String, FuelObject> entry : map.entrySet()) {
			FluidStack input = new FluidStack(FluidRegistry.getFluid(entry.getKey()), 100);
			FuelObject output = entry.getValue();
			if(input != null) {
				FuelGeneratorRecipeWrapper recipe = new FuelGeneratorRecipeWrapper(input, output);
				recipes.add(recipe);
			}
		}

		return recipes;
	}

}
