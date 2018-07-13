package com.black_dog20.gadgetron.tile.base;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public abstract class TileEntityEnergyBase extends TileEntityBase {

	protected boolean on = false;
	protected CustomEnergyStorage energyContainer = null;
	protected int ticksBetweenAutoIO = ModConfig.machines.automation_ticks;
	protected int currentTickBewteen = 0;
	protected int energyPerTick = 1;
	
	public TileEntityEnergyBase(CustomEnergyStorage storage) {
		this.energyContainer = storage;
	}
	
	public TileEntityEnergyBase() {
	}

	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY) 
			return (T) this.energyContainer;
		return super.getCapability(capability, facing);
	}

	@Override
	public void update() {
		if(!world.isRemote) {
			if(currentTickBewteen % ticksBetweenAutoIO == 0)
				currentTickBewteen = 0;
			currentTickBewteen++;
			sendUpdates();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.energyContainer.readFromNBT(nbt);
		on = nbt.getBoolean("on");
		energyPerTick = nbt.getInteger("energyPerTick");
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		this.energyContainer.writeToNBT(nbt);
		nbt.setBoolean("on",on);
		nbt.setInteger("energyPerTick", energyPerTick);
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
	
	public CustomEnergyStorage getEnergyStorage() {
		return this.energyContainer;
	}
	
	public boolean isOn() {
		return on;
	}
	
	public int getEnergyPerTick() {
		return energyPerTick;
	}
	
	@Override
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
		this.energyContainer.writeToNBT(nbt);
		on = nbt.getBoolean("on");
		return super.writeCustomInfoToNBT(nbt);
	}
	
	@Override
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
			this.energyContainer.readFromNBT(nbt);
			nbt.setBoolean("on", on);
			super.readFromCustomInfoNBT(nbt);
		}
	}
	
	public void notifyContainers() {
		
	}
}
