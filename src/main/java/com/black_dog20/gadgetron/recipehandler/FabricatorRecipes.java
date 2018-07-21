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
import net.minecraft.util.Tuple;
import net.minecraftforge.oredict.OreDictionary;

public class FabricatorRecipes {
	private static final FabricatorRecipes recipes = new FabricatorRecipes();
	
	private final Map<Tuple<ItemStack,ItemStack>, ItemStack> recipeList = Maps.<Tuple<ItemStack,ItemStack>, ItemStack>newHashMap();
	private final Map<Tuple<ItemStack, ItemStack>, Integer> timeList = Maps.<Tuple<ItemStack, ItemStack>, Integer>newHashMap();

	
	private final Map<Tuple<OreInput,ItemStack>, ItemStack> recipeOreList = Maps.<Tuple<OreInput,ItemStack>, ItemStack>newHashMap();
	private final Map<Tuple<OreInput, ItemStack>, Integer> timeOreList = Maps.<Tuple<OreInput, ItemStack>, Integer>newHashMap();

	
	private final Map<Tuple<OreInput,OreInput>, ItemStack> recipeOre2List = Maps.<Tuple<OreInput,OreInput>, ItemStack>newHashMap();
	private final Map<Tuple<OreInput, OreInput>, Integer> timeOre2List = Maps.<Tuple<OreInput, OreInput>, Integer>newHashMap();

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
    
	public void addRecipe(String oreA, String oreB, int time, String out, int amount) {
		this.addRecipe(oreA, 1, oreB, 1, time, out, amount);
	}
	
	public void addRecipe(String oreA, String oreB, int time, ItemStack out) {
		this.addRecipe(oreA, 1, oreB, 1, time, out);
	}
	
	public void addRecipe(String oreA, int amountA, String oreB, int amountB, int time, String out, int amount) {
		ItemStack stack = OreDicHelper.getDefaultOreDic(out);
		if(stack.isEmpty()) return;
    	stack = stack.copy();
    	stack.setCount(amount);
    	this.addRecipe(oreA, amountA, oreB, amountB, time, stack);
	}
	
	public void addRecipe(String oreA, int amountA, String oreB, int amountB, int time, ItemStack out) {
		if(!OreDictionary.doesOreNameExist(oreA)) return;
		if(!OreDictionary.doesOreNameExist(oreB)) return;
    	Tuple<OreInput, OreInput> temp = new Tuple<OreInput, OreInput>(new OreInput(oreA, amountA), new OreInput(oreB, amountB));
    	this.recipeOre2List.put(temp, out);
    	this.timeOre2List.put(temp, time);
	}
	
	public void addRecipe(ItemStack inputA, String oreB, int amountB, int time, ItemStack out) {
		if(!OreDictionary.doesOreNameExist(oreB)) return;
    	Tuple<OreInput, ItemStack> temp = new Tuple<OreInput, ItemStack>(new OreInput(oreB, amountB), inputA);
    	this.recipeOreList.put(temp, out);
    	this.timeOreList.put(temp, time);
	}
	
	public void addRecipe(String oreA, int amountA, ItemStack inputB, int time, ItemStack out) {
		this.addRecipe(inputB, oreA, amountA, time, out);
	}
    
	
	public void addRecipe(String oreA, int amountA, ItemStack inputB, int time, String out, int amount) {
		this.addRecipe(inputB, oreA, amountA, time, out, amount);
	}
	
	public void addRecipe(ItemStack inputA, String oreB, int amountB, int time, String out, int amount) {
		ItemStack stack = OreDicHelper.getDefaultOreDic(out);
		if(stack.isEmpty()) return;
    	stack = stack.copy();
    	stack.setCount(amount);
    	this.addRecipe(inputA, oreB, amountB, time, stack);
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
		
		for (Entry<Tuple<OreInput, ItemStack>, ItemStack> entry : this.recipeOreList.entrySet())
		{
			if (compareItemStacksOre(entry.getKey(), stack))
			{
				return (ItemStack)entry.getValue();
			}
		}
		
		for (Entry<Tuple<OreInput, OreInput>, ItemStack> entry : this.recipeOre2List.entrySet())
		{
			if (compareItemStacksOre2(entry.getKey(), stack))
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
		
		for (Entry<Tuple<OreInput, ItemStack>, Integer> entry : this.timeOreList.entrySet())
		{
			if (compareItemStacksOre(entry.getKey(), stack))
			{
				return (int)entry.getValue();
			}
		}
		
		for (Entry<Tuple<OreInput, OreInput>, Integer> entry : this.timeOre2List.entrySet())
		{
			if (compareItemStacksOre2(entry.getKey(), stack))
			{
				return (int)entry.getValue();
			}
		}

		return 1;
	}
	
	
	private boolean compareItemStacksOre2(Tuple<OreInput, OreInput> stack1, Tuple<ItemStack, ItemStack> stack2)
	{
		if(OreDicHelper.stackBelongsToWithAmount(stack1.getFirst().getInput(), stack1.getFirst().getAmount(), stack2.getFirst()))			
			if(OreDicHelper.stackBelongsToWithAmount(stack1.getSecond().getInput(), stack1.getSecond().getAmount(), stack2.getSecond()))			
				return true;
		if(OreDicHelper.stackBelongsToWithAmount(stack1.getFirst().getInput(), stack1.getFirst().getAmount(), stack2.getSecond()))		
			if(OreDicHelper.stackBelongsToWithAmount(stack1.getSecond().getInput(), stack1.getSecond().getAmount(), stack2.getFirst()))			
				return true;
		
		return false;
				
	}
	
	
	private boolean compareItemStacksOre(Tuple<OreInput, ItemStack> stack1, Tuple<ItemStack, ItemStack> stack2)
	{
		if(OreDicHelper.stackBelongsToWithAmount(stack1.getFirst().getInput(), stack1.getFirst().getAmount(), stack2.getFirst()))			
			if(compareItemStacks(stack1.getSecond(), stack2.getSecond()))
				return true;
		if(OreDicHelper.stackBelongsToWithAmount(stack1.getFirst().getInput(), stack1.getFirst().getAmount(), stack2.getSecond()))			
			if(compareItemStacks(stack1.getSecond(), stack2.getFirst()))
				return true;
		
		return false;
				
	}

	private boolean compareItemStacks(Tuple<ItemStack, ItemStack> stack1, Tuple<ItemStack, ItemStack> stack2)
	{
		return (compareItemStacks(stack1.getFirst(), stack2.getFirst()) && compareItemStacks(stack1.getSecond(), stack2.getSecond())) || (compareItemStacks(stack1.getFirst(), stack2.getSecond()) && compareItemStacks(stack1.getSecond(), stack2.getFirst()));
		
	}
	
	private boolean compareItemStacks(ItemStack stack1,ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == OreDictionary.WILDCARD_VALUE || stack2.getMetadata() == stack1.getMetadata()) && stack2.getCount() == stack1.getCount();
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
	
	public Map<Tuple<OreInput, ItemStack>, ItemStack> getRecipeOreList()
	{
		return this.recipeOreList;
	}
	
	public Map<Tuple<OreInput, ItemStack>, Integer> getTimeOreList()
	{
		return this.timeOreList;
	}
	
	public Map<Tuple<OreInput, OreInput>, ItemStack> getRecipeOre2List()
	{
		return this.recipeOre2List;
	}
	
	public Map<Tuple<OreInput, OreInput>, Integer> getTimeOre2List()
	{
		return this.timeOre2List;
	}
}
