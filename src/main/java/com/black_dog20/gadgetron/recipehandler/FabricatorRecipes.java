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
import net.minecraft.util.Tuple;
import net.minecraftforge.oredict.OreDictionary;

public class FabricatorRecipes {
	private static final FabricatorRecipes recipes = new FabricatorRecipes();
	private final Map<Tuple<ItemStack,ItemStack>, ItemStack> recipeList = Maps.<Tuple<ItemStack,ItemStack>, ItemStack>newHashMap();
	private final Map<Tuple<ItemStack, ItemStack>, Integer> timeList = Maps.<Tuple<ItemStack, ItemStack>, Integer>newHashMap();

	public static FabricatorRecipes instance()
	{
		return recipes;
	}

	private FabricatorRecipes()
	{
	}

	public void addRecipeForBlock(Block inputA, Block inputB, int time, ItemStack stack)
	{
		this.add(Item.getItemFromBlock(inputA),Item.getItemFromBlock(inputB), time, stack);
	}

	public void add(Item inputA, Item inputB, int time, ItemStack stack)
	{
		this.addRecipe(new ItemStack(inputA, 1, 32767),new ItemStack(inputB, 1, 32767), time, stack);
	}

	public void addRecipe(ItemStack inputA, ItemStack inputB, int time, ItemStack stack)
	{
		Tuple<ItemStack, ItemStack> temp = new Tuple<ItemStack, ItemStack>(inputA, inputB);
		if (getResult(temp) != ItemStack.EMPTY) { 
			Gadgetron.logger.log(Level.INFO, "Ignored Fabricator recipe with conflicting input: {} + {} = {}", inputA, inputB, stack); 
			return; }
		this.recipeList.put(temp, stack);
		this.timeList.put(temp, time);
	}
    
	public void addRecipe(String oreA, String oreB, int time,  ItemStack out) {
    	NonNullList<ItemStack> tList = OreDictionary.getOres(oreA);
    	NonNullList<ItemStack> tList2 = OreDictionary.getOres(oreB);
    		for (int i = 0; i < tList.size(); i++) {
    			ItemStack tStack = tList.get(i);
    			tStack = tStack.copy();
    			tStack.setCount(1);
    			for (int j = 0; j < tList2.size(); j++) {
        			ItemStack tStack2 = tList.get(j);
        			tStack2 = tStack2.copy();
        			tStack2.setCount(1);
        			this.addRecipe(OreDictionary.getOres(oreA).get(i), OreDictionary.getOres(oreB).get(i), time, out);
        		}
    		}
	}
    

	public ItemStack getResult(Tuple<ItemStack, ItemStack> stack)
	{
		for (Entry<Tuple<ItemStack, ItemStack>, ItemStack> entry : this.recipeList.entrySet())
		{
			if (this.compareItemStacks(stack, (Tuple<ItemStack, ItemStack>)entry.getKey()))
			{
				return (ItemStack)entry.getValue();
			}
		}

		return ItemStack.EMPTY;
	}
	
	public int getTime(Tuple<ItemStack, ItemStack> stack)
	{
		for (Entry<Tuple<ItemStack, ItemStack>, Integer> entry : this.timeList.entrySet())
		{
			if (this.compareItemStacks(stack, (Tuple<ItemStack, ItemStack>)entry.getKey()))
			{
				return (int)entry.getValue();
			}
		}

		return 1;
	}

	private boolean compareItemStacks(Tuple<ItemStack, ItemStack> stack1, Tuple<ItemStack, ItemStack> stack2)
	{
		return (compareItemStacks(stack1.getFirst(), stack2.getFirst()) && compareItemStacks(stack1.getSecond(), stack2.getSecond())) || (compareItemStacks(stack1.getFirst(), stack2.getSecond()) && compareItemStacks(stack1.getSecond(), stack2.getFirst()));
		
	}
	
	private boolean compareItemStacks(ItemStack stack1,ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public boolean containsRecipe(Tuple<ItemStack, ItemStack> stack) {
		for (Entry<Tuple<ItemStack, ItemStack>, Integer> entry : this.timeList.entrySet())
		{
			if (this.compareItemStacks(stack, (Tuple<ItemStack, ItemStack>)entry.getKey()))
			{
				return true;
			}
		}
		return false;
	}
	

	public Map<Tuple<ItemStack, ItemStack>, ItemStack> getRecipeList()
	{
		return this.recipeList;
	}
	
	public Map<Tuple<ItemStack, ItemStack>, Integer> getTimeList()
	{
		return this.timeList;
	}
}
