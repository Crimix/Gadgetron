package com.black_dog20.gadgetron.tile.base;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.utility.CustomEnergyStorage;
import com.black_dog20.gadgetron.utility.CustomItemHandler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileEntityEnergyInventoryBase extends TileEntityEnergyBase {
	
	protected ItemStackHandler inventory = null;
	private boolean exposeInventory = true;

	public TileEntityEnergyInventoryBase(CustomEnergyStorage storage, int size, boolean exposeInventory) {
		super(storage);
		inventory = new CustomItemHandler(size);
		this.exposeInventory = exposeInventory;
	}
	
	public TileEntityEnergyInventoryBase(CustomEnergyStorage storage, ItemStackHandler inventory, boolean exposeInventory) {
		super(storage);
		this.inventory = inventory;
		this.exposeInventory = exposeInventory;
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && exposeInventory) 
			return (T) this.inventory;
		return super.getCapability(capability, facing);
	}
	
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
    	nbt.setTag("inventory", inventory.serializeNBT());
    	nbt.setBoolean("exposeInventory", exposeInventory);
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        inventory.deserializeNBT((NBTTagCompound) nbt.getTag("inventory"));
        exposeInventory = nbt.getBoolean("exposeInventory");
    }
    
    public ItemStackHandler getInventory() {
    	return this.inventory;
    }
}
