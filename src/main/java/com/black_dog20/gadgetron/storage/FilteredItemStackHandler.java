package com.black_dog20.gadgetron.storage;

import java.util.function.Function;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemHandlerHelper;

public class FilteredItemStackHandler extends CustomItemHandler{
	
	private Function<ItemStack,Boolean> validator;
	

    public FilteredItemStackHandler(int input, int output, Function<ItemStack,Boolean> validator)
    {
        super(input, output);
        this.validator = validator;
    }
    
    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
    	if(isSlotInput(slot)) {
    		if (stack.isEmpty())
    			return ItemStack.EMPTY;

    		if(!validator.apply(stack)) {
    			return stack;
    		}
    		return super.insertItem(slot, stack, simulate);
    	}
    	return ItemStack.EMPTY;
    }   
}
