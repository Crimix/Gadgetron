package com.black_dog20.gadgetron.tile.base;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.storage.CustomItemHandler;
import com.black_dog20.gadgetron.storage.InputItemHandlerWrapper;
import com.black_dog20.gadgetron.storage.OutputItemHandlerWrapper;
import com.black_dog20.gadgetron.utility.MachineFaces;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileEntityEnergyInventoryBase extends TileEntityEnergyBase {
	
	protected CustomItemHandler inventory = null;
	protected MachineFaces inventoryFaces;

	public TileEntityEnergyInventoryBase(CustomEnergyStorage storage, int inputSlots, int outputSlots) {
		super(storage);
		inventory = new CustomItemHandler(inputSlots,outputSlots);
		inventoryFaces = new MachineFaces();
	}
	
	public TileEntityEnergyInventoryBase(CustomEnergyStorage storage, CustomItemHandler inventory) {
		super(storage);
		this.inventory = inventory;
		inventoryFaces = new MachineFaces();
	}

	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventoryFaces.isFaceInput(facing) && inventoryFaces.isFaceOutput(facing)) 
			return (T) this.inventory;
		else if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventoryFaces.isFaceInput(facing) && !inventoryFaces.isFaceOutput(facing))
			return (T) new InputItemHandlerWrapper(inventory);
		else if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && !inventoryFaces.isFaceInput(facing) && inventoryFaces.isFaceOutput(facing))
			return (T) new OutputItemHandlerWrapper(inventory);
		return super.getCapability(capability, facing);
	}
	
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
    	nbt.setTag("inventory", inventory.serializeNBT());
    	inventoryFaces.writeToNBT(nbt);
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        inventory.deserializeNBT((NBTTagCompound) nbt.getTag("inventory"));
        inventoryFaces.readFromNBT(nbt);
    }
    
    public ItemStackHandler getInventory() {
    	return this.inventory;
    }
    
    @Override
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
		nbt.setTag("inventory", inventory.serializeNBT());
		inventoryFaces.writeToNBT(nbt);
		return super.writeCustomInfoToNBT(nbt);
	}
	
	@Override
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
	        inventory.deserializeNBT((NBTTagCompound) nbt.getTag("inventory"));
	        inventoryFaces.readFromNBT(nbt);
	        super.readFromCustomInfoNBT(nbt);
		}
	}
}
