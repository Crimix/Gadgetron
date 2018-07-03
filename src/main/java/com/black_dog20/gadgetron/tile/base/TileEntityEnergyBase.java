package com.black_dog20.gadgetron.tile.base;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.storage.CustomEnergyStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public abstract class TileEntityEnergyBase extends TileEntityBase {

	protected boolean on = false;
	protected CustomEnergyStorage energyContainer = null;
	
	public TileEntityEnergyBase(CustomEnergyStorage storage) {
		this.energyContainer = storage;
	}
	
	

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY) 
			return (T) this.energyContainer;
		return super.getCapability(capability, facing);
	}

	@Override
	public abstract void update();

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.energyContainer.readFromNBT(nbt);
		on = nbt.getBoolean("on");
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		this.energyContainer.writeToNBT(nbt);
		on = nbt.getBoolean("on");
		return super.writeToNBT(nbt);
	}
	
	public int getStoredEnergy() {
		return energyContainer.getEnergyStored();
	}
	
	public int getStoredEnergyPercentage() {
		double t = (((double) energyContainer.getEnergyStored()) / energyContainer.getMaxEnergyStored()) *100;
		return (int) Math.floor(t);
	}
	
	public int getEnergyCapacity() {
		return energyContainer.getMaxEnergyStored();
	}
	
	public boolean isOn() {
		return on;
	}
	
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
		this.energyContainer.writeToNBT(nbt);
		on = nbt.getBoolean("on");
		return super.writeCustomInfoToNBT(nbt);
	}
	
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
			super.readFromCustomInfoNBT(nbt);
			this.energyContainer.readFromNBT(nbt);
			nbt.setBoolean("on", on);
			sendUpdates();
		}
	}
}
