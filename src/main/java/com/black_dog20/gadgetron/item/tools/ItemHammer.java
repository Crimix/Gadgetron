package com.black_dog20.gadgetron.item.tools;

import com.black_dog20.gadgetron.item.ItemBase;

import net.minecraft.item.ItemStack;

public class ItemHammer extends ItemBase {

	public ItemHammer(String name) {
		super(name);
		this.setMaxStackSize(1);
		this.setMaxDamage(256);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {		
		return true;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack copy = itemStack.copy();
		copy.setItemDamage(copy.getItemDamage() + 1);
		return copy;
	}
	
}
