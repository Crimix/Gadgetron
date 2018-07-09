package com.black_dog20.gadgetron.storage;

import com.black_dog20.gadgetron.config.ModConfig;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class CustomEnergyStorage extends EnergyStorage {
	
	public CustomEnergyStorage(int capacity)
    {
        super(capacity, capacity, capacity, 0);
    }

    public CustomEnergyStorage(int capacity, int maxTransfer)
    {
    	super(capacity, maxTransfer, maxTransfer, 0);
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract)
    {
    	super(capacity, maxReceive, maxExtract, 0);
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy)
    {
    	super(capacity,maxReceive,maxExtract,energy);
    }
    
    public void readFromNBT(NBTTagCompound nbt) {
		this.energy = nbt.getInteger("Energy");
		this.capacity = nbt.getInteger("Capacity");
		this.maxReceive = nbt.getInteger("MaxReceive");
		this.maxExtract = nbt.getInteger("MaxExtract");
	}

	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("Energy", this.energy);
		nbt.setInteger("Capacity", this.capacity);
		nbt.setInteger("MaxReceive", this.maxReceive);
		nbt.setInteger("MaxExtract", this.maxExtract);
	}
	
	public int extractEnergyInternal(int maxExtract, boolean simulate) {
		int before = this.maxExtract;
		this.maxExtract = Integer.MAX_VALUE;

		int toReturn = this.extractEnergy(maxExtract, simulate);

		this.maxExtract = before;
		return toReturn;
	}
	
	public int receiveEnergyInternal(int maxReceive, boolean simulate) {
		int before = this.maxReceive;
		this.maxReceive = Integer.MAX_VALUE;

		int toReturn = this.receiveEnergy(maxReceive, simulate);

		this.maxReceive = before;
		return toReturn;
	}

	public boolean isEmpty() {
		return 0 == getEnergyStored();
	}
	
	public boolean isFull() {
		return getMaxEnergyStored() == getEnergyStored();
	}
	
	public void transferEnergy(IEnergyStorage target) {
		int toExtract = this.extractEnergy(maxExtract, true);
		int inserted = target.receiveEnergy(toExtract, false);
		this.extractEnergy(inserted, false);
	}
	
	public void transferEnergy(IEnergyStorage target, int maxExtract) {
		int toExtract = this.extractEnergy(maxExtract, true);
		int inserted = target.receiveEnergy(toExtract, false);
		this.extractEnergy(inserted, false);
	}
	
	public int getMaxReceive() {
		return maxReceive;
	}
	
	public int getMaxExtract() {
		return maxExtract;
	}
}
