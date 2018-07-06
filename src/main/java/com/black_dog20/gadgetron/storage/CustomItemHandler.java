package com.black_dog20.gadgetron.storage;

import javax.annotation.Nonnull;

import com.black_dog20.gadgetron.tile.base.TileEntityBase;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CustomItemHandler extends ItemStackHandler{

	private TileEntityBase base;
	private int inputSlots;
	private int outputSlots;

    public CustomItemHandler(int input, int output)
    {
    	super(input+output);
    	this.inputSlots = input;
    	this.outputSlots = output;
    }
	
	public void setTileEntityBase(TileEntityBase base) {
		this.base = base;
	}
	
	@Override
	protected void onContentsChanged(int slot) {
		super.onContentsChanged(slot);
		if(base != null)
			base.sendUpdates();
	}
	
    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
    	if(isSlotInput(slot)) {
    		return super.insertItem(slot, stack, simulate);
    	}
    	return stack;
    }
    
    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
    	if(isSlotOutput(slot)) {
    		return super.extractItem(slot, amount, simulate);
    	}
    	return ItemStack.EMPTY;
    }
	
    @Nonnull
    public ItemStack insertItemInternal(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
    	return super.insertItem(slot, stack, simulate);
    }
    
    @Nonnull
    public ItemStack extractItemInternal(int slot, int amount, boolean simulate)
    {
    	return super.extractItem(slot, amount, simulate);
    }
	
	public int getNumberOfOutputSlots() {
		return outputSlots;
	}
	
	public int getNumberOfInputSlots() {
		return inputSlots;
	}
	
	protected boolean isSlotOutput(int slot) {
		if(slot+1 > inputSlots)
			return true;
		else 
			return false;
	}
	
	protected boolean isSlotInput(int slot) {
		if(slot+1 <= inputSlots)
			return true;
		else 
			return false;
	}
	
	public void transfer(IItemHandler target) {
		for(int i = 0; i < outputSlots; i++) {
			ItemStack stack = this.extractItem(i, 1, true);
			if(stack != null && !stack.isEmpty()) {
				for(int k = 0; k < target.getSlots(); k++) {
					ItemStack returnedStack = target.insertItem(k, stack, false);
					if(returnedStack.isEmpty()) {
						this.extractItem(i, 1, false);
					}
				}
			}
		}
	}
	
	public void tryExtract(IItemHandler target) {
		for(int k = 0; k < target.getSlots(); k++) {
			ItemStack stack = target.extractItem(k, 1, true);
			if(stack != null && !stack.isEmpty()) {
				for(int i = 0; i < inputSlots; i++) {
					ItemStack returnedStack = this.insertItem(i, stack, false);
					if(returnedStack.isEmpty()) {
						target.extractItem(k, 1, false);
					}
				}
			}
		}
	}
	
}
