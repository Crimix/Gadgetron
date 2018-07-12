package com.black_dog20.gadgetron.recipehandler;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;

import com.black_dog20.gadgetron.Gadgetron;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExtractorRecipes {
	private static final ExtractorRecipes recipes = new ExtractorRecipes();
	private final Map<ItemStack, ItemStack> recipeList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Integer> timeList = Maps.<ItemStack, Integer>newHashMap();

	public static ExtractorRecipes instance()
	{
		return recipes;
	}

	private ExtractorRecipes()
	{
	}

	public void addRecipeForBlock(Block input, int time, ItemStack stack)
	{
		this.add(Item.getItemFromBlock(input), time, stack);
	}

	public void add(Item input, int time, ItemStack stack)
	{
		this.addRecipe(new ItemStack(input, 1, 32767), time, stack);
	}

	public void addRecipe(ItemStack input, int time, ItemStack stack)
	{
		if (getResult(input) != ItemStack.EMPTY) { 
			Gadgetron.logger.log(Level.INFO, "Ignored Extractor recipe with conflicting input: {} = {}", input, stack); 
			return; }
		this.recipeList.put(input, stack);
		this.timeList.put(stack, time);
	}

	public ItemStack getResult(ItemStack stack)
	{
		for (Entry<ItemStack, ItemStack> entry : this.recipeList.entrySet())
		{
			if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
			{
				return (ItemStack)entry.getValue();
			}
		}

		return ItemStack.EMPTY;
	}
	
	public int getTime(ItemStack stack)
	{
		for (Entry<ItemStack, Integer> entry : this.timeList.entrySet())
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
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Map<ItemStack, ItemStack> getRecipeList()
	{
		return this.recipeList;
	}
	
	public Map<ItemStack, Integer> getTimeList()
	{
		return this.timeList;
	}
}
