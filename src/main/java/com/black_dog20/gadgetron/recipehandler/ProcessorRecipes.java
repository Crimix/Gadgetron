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
import net.minecraftforge.oredict.OreDictionary;

public class ProcessorRecipes {
	private static final ProcessorRecipes recipes = new ProcessorRecipes();
	private final Map<ItemStack, ItemStack> recipeList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Integer> timeList = Maps.<ItemStack, Integer>newHashMap();


	public static ProcessorRecipes instance()
	{
		return recipes;
	}

	private ProcessorRecipes()
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

	public void addRecipe(ItemStack input, int time, ItemStack stack)
	{
		if (getResult(input) != ItemStack.EMPTY) { 
			Gadgetron.logger.log(Level.INFO, "Ignored Processor recipe with conflicting input: {} = {}", input, stack); 
			return; }
		this.recipeList.put(input, stack);
		this.timeList.put(input, time);
	}
	
	public void addRecipe(String ore, int time, ItemStack out) {
    	NonNullList<ItemStack> tList = OreDictionary.getOres(ore);
    	for (int i = 0; i < tList.size(); i++) {
    	    ItemStack tStack = tList.get(i);
    	    tStack = tStack.copy();
    	    tStack.setCount(1);
    	    tStack.setItemDamage(OreDictionary.WILDCARD_VALUE);
    	    this.addRecipe(tStack, time, out);
    	}
    }
    
	public void addRecipe(String ore, int time, String out, int amount) {
    	NonNullList<ItemStack> tList = OreDictionary.getOres(ore);
    	NonNullList<ItemStack> tList2 = OreDictionary.getOres(out);
    	if (tList2.size() > 0) {
    		ItemStack tStack2 = tList2.get(0);
    		tStack2 = tStack2.copy();
    		tStack2.setCount(amount);
    		tStack2.setItemDamage(OreDictionary.WILDCARD_VALUE);
    		for (int i = 0; i < tList.size(); i++) {
    			ItemStack tStack = tList.get(i);
    			tStack = tStack.copy();
    			tStack.setCount(1);
    			tStack.setItemDamage(OreDictionary.WILDCARD_VALUE);
    			this.addRecipe(tStack, time, tStack2);
    		}
    	}
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
}
