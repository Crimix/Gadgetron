package com.black_dog20.gadgetron.storage;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class CustomItemHandlerBase extends ItemStackHandler {

	public CustomItemHandlerBase(NonNullList<ItemStack> withSize) {
		super(withSize);
	}
	
	public CustomItemHandlerBase(int size) {
		super(size);
	}
	
	public CustomItemHandlerBase() {
		super();
	}
	
	public boolean hasItemStack(ItemStack stack) {
		for(ItemStack item : stacks) {
			if(ItemStack.areItemsEqual(item, stack))
				return true;
		}
		return false;
	}

}
