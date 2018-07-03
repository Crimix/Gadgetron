package com.black_dog20.gadgetron.tile.base;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.storage.CustomFluidTank;
import com.black_dog20.gadgetron.storage.CustomItemHandler;
import com.black_dog20.gadgetron.storage.InputTankWrapper;
import com.black_dog20.gadgetron.storage.OutputTankWrapper;
import com.black_dog20.gadgetron.utility.MachineFaces;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public abstract class TileEntityEnergyInventoryFluidBase extends TileEntityEnergyInventoryBase{
	
	protected CustomFluidTank tank;
	protected MachineFaces tankFaces;

	public TileEntityEnergyInventoryFluidBase(CustomEnergyStorage storage, int intputSlots, int outputSlots, int sizeMB) {
		super(storage, intputSlots,outputSlots);
		tank = new CustomFluidTank(sizeMB);
		tankFaces = new MachineFaces();
	}
	
	public TileEntityEnergyInventoryFluidBase(CustomEnergyStorage storage, int intputSlots, int outputSlots, CustomFluidTank tank) {
		super(storage,intputSlots,outputSlots);
		this.tank = tank;
		tankFaces = new MachineFaces();
	}
	
	public TileEntityEnergyInventoryFluidBase(CustomEnergyStorage storage, CustomItemHandler inventory, int sizeMB) {
		super(storage, inventory);
		tank = new CustomFluidTank(sizeMB);
		tankFaces = new MachineFaces();
	}
	
	public TileEntityEnergyInventoryFluidBase(CustomEnergyStorage storage, CustomItemHandler inventory, CustomFluidTank tank) {
		super(storage,inventory);
		this.tank = tank;
		tankFaces = new MachineFaces();
	}

    @SuppressWarnings("unchecked")
	@Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && tankFaces.isFaceInput(facing) && tankFaces.isFaceOutput(facing))
        	return (T) tank;
        else if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && tankFaces.isFaceInput(facing) && !tankFaces.isFaceOutput(facing))
        	return (T) new InputTankWrapper(0, tank);
        else if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && !tankFaces.isFaceInput(facing) && tankFaces.isFaceOutput(facing))
        	return (T) new OutputTankWrapper(0, tank);
        return super.getCapability(capability, facing);
    }
	
	
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
    	tank.writeToNBT(nbt);
    	tankFaces.writeToNBT(nbt);
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        tank.readFromNBT(nbt);
        tankFaces.readFromNBT(nbt);
    }

	public FluidStack getFluid() {
		return tank.getFluid();
	}
	
	public int getStoredFluid() {
		return tank.getFluidAmount();
	}
	
	public int getStoredFluidPercentage() {
		double t = (((double) tank.getFluidAmount()) / tank.getCapacity()) *100;
		return (int) Math.floor(t);
	}
	
	public int getFluidCapacity() {
		return tank.getCapacity();
	}
	
	public FluidTank getTank() {
		return tank;
	}
	
	@Override
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
    	tank.writeToNBT(nbt);
    	tankFaces.writeToNBT(nbt);
		return super.writeCustomInfoToNBT(nbt);
	}
	
	@Override
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
	        tank.readFromNBT(nbt);
	        tankFaces.readFromNBT(nbt);
	        super.readFromCustomInfoNBT(nbt);
		}
	}
}
