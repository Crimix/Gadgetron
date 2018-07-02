package com.black_dog20.gadgetron.utility;

import java.util.function.Function;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class FilteredItemStackHandler extends CustomItemHandler{
	
	private Function<ItemStack,Boolean> validator;
	
    public FilteredItemStackHandler(Function<ItemStack,Boolean> validator)
    {
        this(1, validator);
    }
	

    public FilteredItemStackHandler(int size, Function<ItemStack,Boolean> validator)
    {
        super(size);
        this.validator = validator;
    }

    public FilteredItemStackHandler(NonNullList<ItemStack> stacks, Function<ItemStack,Boolean> validator)
    {
       	super(stacks);
       	this.validator = validator;
    }
    
    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
        if (stack.isEmpty())
            return ItemStack.EMPTY;
    	
    	if(!validator.apply(stack)) {
    		return stack;
    	}
       return super.insertItem(slot, stack, simulate);
    }
    
    
   
}
