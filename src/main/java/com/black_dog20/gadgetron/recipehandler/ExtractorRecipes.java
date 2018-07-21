package com.black_dog20.gadgetron.recipehandler;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.utility.OreDicHelper;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ExtractorRecipes {
	private static final ExtractorRecipes recipes = new ExtractorRecipes();
	private final Map<ItemStack, ItemStack> recipeList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<String, ItemStack> recipeOreList = Maps.<String, ItemStack>newHashMap();
	private final Map<ItemStack, Integer> timeList = Maps.<ItemStack, Integer>newHashMap();
	private final Map<String, Integer> timeOreList = Maps.<String, Integer>newHashMap();

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
		this.addRecipe(new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE), time, stack);
	}

	private void addRecipe(ItemStack input, int time, ItemStack stack)
	{
		if (getResult(input) != ItemStack.EMPTY) { 
			Gadgetron.logger.log(Level.INFO, "Ignored Extractor recipe with conflicting input: {} = {}", input, stack); 
			return; }
		this.recipeList.put(input, stack);
		this.timeList.put(input, time);
	}
	
	public void addRecipe(String ore, int time, ItemStack out) {
		if(!OreDictionary.doesOreNameExist(ore)) return;
		recipeOreList.put(ore,out);
		timeOreList.put(ore, time);
    }
    
	public void addRecipe(String ore, int time, String out, int amount) {
		if(!OreDictionary.doesOreNameExist(ore)) return;
		ItemStack stack = OreDicHelper.getDefaultOreDic(out);
		if(stack.isEmpty()) return;
    	stack = stack.copy();
    	stack.setCount(amount);
    	this.addRecipe(ore, time, stack);
	}
	
	public void addRecipe(ItemStack input, int time, String out, int amount) {
    	ItemStack stack = OreDicHelper.getDefaultOreDic(out);
    	if(stack.isEmpty()) return;
    	stack = stack.copy();
    	stack.setCount(amount);
    	this.addRecipe(input, time, stack);
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
		
		for (Entry<String, ItemStack> entry : this.recipeOreList.entrySet())
		{
			if (OreDicHelper.stackBelongsTo(entry.getKey(), stack))
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
		
		for (Entry<String, Integer> entry : this.timeOreList.entrySet())
		{
			if (OreDicHelper.stackBelongsTo(entry.getKey(), stack))
			{
				return (int)entry.getValue();
			}
		}

		return 1;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == OreDictionary.WILDCARD_VALUE || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public boolean containsRecipe(ItemStack stack) {
		return getResult(stack) != ItemStack.EMPTY;
	}
	

	public Map<ItemStack, ItemStack> getRecipeList()
	{
		return this.recipeList;
	}
	
	public Map<ItemStack, Integer> getTimeList()
	{
		return this.timeList;
	}
	
	public Map<String, ItemStack> getRecipeOreList()
	{
		return this.recipeOreList;
	}
	
	public Map<String, Integer> getTimeOreList()
	{
		return this.timeOreList;
	}
	
	

}
