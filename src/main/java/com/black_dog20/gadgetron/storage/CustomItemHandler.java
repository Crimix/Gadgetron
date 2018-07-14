package com.black_dog20.gadgetron.storage;

import java.util.function.Function;

import javax.annotation.Nonnull;

import com.black_dog20.gadgetron.tile.base.TileEntityBase;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CustomItemHandler extends ItemStackHandler {

	private TileEntityBase base;
	private int inputSlots;
	private int outputSlots;
	private boolean[] auto = new boolean[0];

    public CustomItemHandler(int input, int output)
    {
    	super(NonNullList.withSize((input+output), ItemStack.EMPTY));
    	this.inputSlots = input;
    	this.outputSlots = output;
    }
    
    public CustomItemHandler(int input, int output, boolean[] auto)
    {
    	super(NonNullList.withSize((input+output), ItemStack.EMPTY));
    	this.inputSlots = input;
    	this.outputSlots = output;
    	this.auto = auto;
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
		if(slot+1 > inputSlots && (!isArrayValid() || isAutomation(slot)))
			return true;
		else 
			return false;
	}
	
	protected boolean isSlotInput(int slot) {
		if(slot+1 <= inputSlots && (!isArrayValid() || isAutomation(slot)))
			return true;
		else 
			return false;
	}
	
	protected boolean isArrayValid() {
		return inputSlots+outputSlots == auto.length;
	}
	
	protected boolean isAutomation(int slot) {
		return isArrayValid() && auto[slot];
			
	}
	
	public void transfer(IItemHandler target) {
		for(int i = inputSlots; i < inputSlots+outputSlots; i++) {
			ItemStack stack = this.extractItemInternal(i, 1, true);
			if(stack != null && !stack.isEmpty()) {
				for(int k = 0; k < target.getSlots(); k++) {
					ItemStack returnedStack = target.insertItem(k, stack, false);
					if(returnedStack.isEmpty()) {
						this.extractItemInternal(i, 1, false);
						return;
					}
				}
			}
		}
	}
	
	public void tryExtract(IItemHandler target, Function<ItemStack,Boolean> validator) {
		for(int k = 0; k < target.getSlots(); k++) {
			ItemStack stack = target.extractItem(k, 1, true);
			if(stack != null && !stack.isEmpty() && (validator == null || validator.apply(stack))) {
				for(int i = 0; i < inputSlots; i++) {
					ItemStack returnedStack = this.insertItemInternal(i, stack, false);
					if(returnedStack.isEmpty()) {
						target.extractItem(k, 1, false);
						break;
					}
				}
			}
		}
	}
	
	public void tryExtract(IItemHandler target) {
		tryExtract(target, null);
	}
	
	@Override
    protected void validateSlotIndex(int slot)
    {
        if (slot < 0 || slot >= stacks.size())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + stacks.size() + ")");
    }
	
	
	public boolean canMerge(int slot, ItemStack stack) {
		return (slot >= 0 || slot < stacks.size()) && (stacks.get(slot).isEmpty() || (ItemStack.areItemsEqual(stacks.get(slot), stack) && stacks.get(slot).getMaxStackSize() >= (stacks.get(slot).getCount()+stack.getCount())));
	}
	
}
