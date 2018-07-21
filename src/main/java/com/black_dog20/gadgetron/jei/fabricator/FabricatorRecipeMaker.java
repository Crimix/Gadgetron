package com.black_dog20.gadgetron.jei.fabricator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.black_dog20.gadgetron.recipehandler.FabricatorRecipes;
import com.black_dog20.gadgetron.recipehandler.OreInput;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.oredict.OreDictionary;

public final class FabricatorRecipeMaker {

	private FabricatorRecipeMaker() {
	}

	public static List<FabricatorRecipeWrapper> getRecipes(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		FabricatorRecipes machineRecipes = FabricatorRecipes.instance();
		Map<Tuple<ItemStack, ItemStack>, ItemStack> map = machineRecipes.getRecipeList();
		Map<Tuple<OreInput, ItemStack>, ItemStack> mapOre = machineRecipes.getRecipeOreList();
		Map<Tuple<OreInput, OreInput>, ItemStack> mapOre2 = machineRecipes.getRecipeOre2List();

		List<FabricatorRecipeWrapper> recipes = new ArrayList<>();

		for (Entry<Tuple<ItemStack, ItemStack>, ItemStack> entry : map.entrySet()) {
			Tuple<ItemStack, ItemStack> input = entry.getKey();
			ItemStack output = entry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input.getFirst());
			List<ItemStack> inputsTwo = stackHelper.getSubtypes(input.getSecond());
			FabricatorRecipeWrapper recipe = new FabricatorRecipeWrapper(inputs, inputsTwo, output);
			recipes.add(recipe);
		}
		
		for (Entry<Tuple<OreInput, ItemStack>, ItemStack> entry : mapOre.entrySet()) {
			ItemStack output = entry.getValue();
			int amount1 = entry.getKey().getFirst().getAmount();
			
			List<ItemStack> temp = OreDictionary.getOres(entry.getKey().getFirst().getInput(), true);
			List<ItemStack> inputs = new ArrayList<ItemStack>();
			for(ItemStack s : temp) {
				ItemStack t = s.copy();
				t.setCount(amount1);
				inputs.add(t);
			}	
			
			List<ItemStack> inputsTwo = stackHelper.getSubtypes(entry.getKey().getSecond());

			if(!inputs.isEmpty()) {
				FabricatorRecipeWrapper recipe = new FabricatorRecipeWrapper(inputs, inputsTwo, output);
				recipes.add(recipe);
			}
			
		}
		
		for (Entry<Tuple<OreInput, OreInput>, ItemStack> entry : mapOre2.entrySet()) {
			ItemStack output = entry.getValue();
			int amount1 = entry.getKey().getFirst().getAmount();
			int amount2 = entry.getKey().getSecond().getAmount();
			
			List<ItemStack> temp = OreDictionary.getOres(entry.getKey().getFirst().getInput(), true);
			List<ItemStack> inputs = new ArrayList<ItemStack>();
			for(ItemStack s : temp) {
				ItemStack t = s.copy();
				t.setCount(amount1);
				inputs.add(t);
			}
			
			List<ItemStack> temp2 = OreDictionary.getOres(entry.getKey().getSecond().getInput(), true);
			List<ItemStack> inputsTwo = new ArrayList<ItemStack>();
			for(ItemStack s : temp2) {
				ItemStack t = s.copy();
				t.setCount(amount2);
				inputsTwo.add(t);
			}

			if(!inputs.isEmpty()) {
				FabricatorRecipeWrapper recipe = new FabricatorRecipeWrapper(inputs, inputsTwo, output);
				recipes.add(recipe);
			}
			
		}

		return recipes;
	}

}
