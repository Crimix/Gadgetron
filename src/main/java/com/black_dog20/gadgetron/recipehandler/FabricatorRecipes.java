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
		this.addRecipe(new ItemStack(inputA, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(inputB, 1, OreDictionary.WILDCARD_VALUE), time, stack);
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
    
	public void addRecipe(String oreA, String oreB, int time,  String out, int amount) {
    	NonNullList<ItemStack> tList2 = OreDictionary.getOres(out);
    	if (tList2.size() > 0) {
    		ItemStack tStack2 = tList2.get(0);
    		tStack2 = tStack2.copy();
    		tStack2.setCount(amount);
    		this.addRecipe(oreA, oreB, time, tStack2);
    	}
	}
	
	public void addRecipe(String oreA, String oreB, int time,  ItemStack out) {
    	NonNullList<ItemStack> tList = OreDictionary.getOres(oreA);
    	NonNullList<ItemStack> tList2 = OreDictionary.getOres(oreB);
    		for (int i = 0; i < tList.size(); i++) {
    			ItemStack tStack = tList.get(i);
    			tStack = tStack.copy();
    			tStack.setCount(1);
    			tStack.setItemDamage(OreDictionary.WILDCARD_VALUE);
    			for (int j = 0; j < tList2.size(); j++) {
        			ItemStack tStack2 = tList2.get(j);
        			tStack2 = tStack2.copy();
        			tStack2.setCount(1);
        			tStack2.setItemDamage(OreDictionary.WILDCARD_VALUE);
        			this.addRecipe(tStack, tStack2, time, out);
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
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == OreDictionary.WILDCARD_VALUE || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public boolean containsRecipe(Tuple<ItemStack, ItemStack> stack) {
		return getResult(stack) != ItemStack.EMPTY;
	}
	
	public boolean containsRecipe(ItemStack stack, ItemStack stack2) {
		return containsRecipe(new Tuple<ItemStack,ItemStack>(stack,stack2));
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
