package com.black_dog20.gadgetron.storage;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class InputItemHandlerWrapper extends ItemStackHandler{
	private final ItemStackHandler internalSlot;

	public InputItemHandlerWrapper(ItemStackHandler hidden) {
		super();
		internalSlot = hidden;
	}

	@Override
	public void setSize(int size) {
		stacks = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		internalSlot.setStackInSlot(slot, stack);
	}

	@Override
	public int getSlots() {
		return internalSlot.getSlots();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return internalSlot.getStackInSlot(slot);
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		return internalSlot.insertItem(slot, stack, simulate);
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return ItemStack.EMPTY;
	}
}
