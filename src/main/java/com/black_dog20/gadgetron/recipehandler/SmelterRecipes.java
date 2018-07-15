package com.black_dog20.gadgetron.recipehandler;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;

import com.black_dog20.gadgetron.Gadgetron;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class SmelterRecipes {
	private static final SmelterRecipes recipes = new SmelterRecipes();
	private final Map<ItemStack, FluidStack> recipeList = Maps.<ItemStack, FluidStack>newHashMap();
	private final Map<ItemStack, Integer> smeltingTime = Maps.<ItemStack, Integer>newHashMap();

	public static SmelterRecipes instance()
	{
		return recipes;
	}

	private SmelterRecipes()
	{
	}

	public void addRecipeForBlock(Block input, int smeltingTime, FluidStack stack)
	{
		this.add(Item.getItemFromBlock(input), smeltingTime, stack);
	}

	public void add(Item input, int smeltingTime, FluidStack stack)
	{
		this.addRecipe(new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE), smeltingTime, stack);
	}

	public void addRecipe(ItemStack input, int smeltingTime, FluidStack stack)
	{
		if (getResult(input) != null) { 
			Gadgetron.logger.log(Level.INFO, "Ignored Smelter recipe with conflicting input: {} = {}", input, stack); 
			return; }
		
		this.recipeList.put(input, stack);
		this.smeltingTime.put(input, smeltingTime);
	}
	
	public void addRecipe(String ore, int time, FluidStack out) {
    	NonNullList<ItemStack> tList = OreDictionary.getOres(ore);
    	for (int i = 0; i < tList.size(); i++) {
    	    ItemStack tStack = tList.get(i);
    	    tStack = tStack.copy();
    	    tStack.setCount(1);
    	    tStack.setItemDamage(OreDictionary.WILDCARD_VALUE);
    	    this.addRecipe(tStack, time, out);
    	}
    }
	
	public void addRecipe(String ore, int time, String out, int mb) {
		if(FluidRegistry.isFluidRegistered(out)) {
			this.addRecipe(ore, time, new FluidStack(FluidRegistry.getFluid(out), mb));
		}
    }
	
	public void addRecipe(ItemStack input, int time, String out, int mb) {
		if(FluidRegistry.isFluidRegistered(out)) {
			this.addRecipe(input, time, new FluidStack(FluidRegistry.getFluid(out), mb));
		}
    }

	public FluidStack getResult(ItemStack stack)
	{
		for (Entry<ItemStack, FluidStack> entry : this.recipeList.entrySet())
		{
			if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
			{
				return (FluidStack)entry.getValue();
			}
		}

		return null;
	}
	
	public int getSmeltingTime(ItemStack stack)
	{
		for (Entry<ItemStack, Integer> entry : this.smeltingTime.entrySet())
		{
			if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
			{
				return (int)entry.getValue();
			}
		}

		return 0;
	}


	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == OreDictionary.WILDCARD_VALUE || stack2.getMetadata() == stack1.getMetadata());
	}

	public Map<ItemStack, FluidStack> getRecipeList()
	{
		return this.recipeList;
	}
	
	public Map<ItemStack, Integer> getSmeltingTimeList()
	{
		return this.smeltingTime;
	}
}
