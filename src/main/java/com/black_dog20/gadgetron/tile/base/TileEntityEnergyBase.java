package com.black_dog20.gadgetron.tile.base;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.utility.CustomEnergyStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public abstract class TileEntityEnergyBase extends TileEntityBase {

	public TileEntityEnergyBase(CustomEnergyStorage storage) {
		this.energyContainer = storage;
	}
	
	protected CustomEnergyStorage energyContainer = null;

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
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		this.energyContainer.writeToNBT(nbt);
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
}
