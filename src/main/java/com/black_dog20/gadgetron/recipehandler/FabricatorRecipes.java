package com.black_dog20.gadgetron.recipehandler;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;

import com.black_dog20.gadgetron.Gadgetron;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FabricatorRecipes {
	private static final FabricatorRecipes recipes = new FabricatorRecipes();
	private final Map<ItemStack, ItemStack> recipeList = Maps.<ItemStack, ItemStack>newHashMap();

	public static FabricatorRecipes instance()
	{
		return recipes;
	}

	private FabricatorRecipes()
	{
	}

	public void addRecipeForBlock(Block input, ItemStack stack)
	{
		this.add(Item.getItemFromBlock(input), stack);
	}

	public void add(Item input, ItemStack stack)
	{
		this.addRecipe(new ItemStack(input, 1, 32767), stack);
	}

	public void addRecipe(ItemStack input, ItemStack stack)
	{
		if (getResult(input) != ItemStack.EMPTY) { 
			Gadgetron.logger.log(Level.INFO, "Ignored fabricator recipe with conflicting input: {} = {}", input, stack); 
			return; }
		this.recipeList.put(input, stack);
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

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Map<ItemStack, ItemStack> getRecipeList()
	{
		return this.recipeList;
	}
}
