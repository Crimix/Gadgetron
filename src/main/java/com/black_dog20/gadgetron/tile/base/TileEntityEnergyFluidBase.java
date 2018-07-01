package com.black_dog20.gadgetron.tile.base;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.utility.CustomEnergyStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public abstract class TileEntityEnergyFluidBase extends TileEntityEnergyBase{

	protected FluidTank tank;
	private boolean exposeTank = true;
	
	public TileEntityEnergyFluidBase(CustomEnergyStorage storage, int sizeMB, boolean exposeTank) {
		super(storage);
		tank = new FluidTank(sizeMB);
		this.exposeTank = exposeTank;
	}
	
	public TileEntityEnergyFluidBase(CustomEnergyStorage storage, FluidTank tank, boolean exposeTank) {
		super(storage);
		this.tank = tank;
		this.exposeTank = exposeTank;
	}
	
    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && exposeTank) {
        	return (T) tank;
        }
        return super.getCapability(capability, facing);
    }
	
	
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
    	tank.writeToNBT(nbt);
    	nbt.setBoolean("exposeTank", exposeTank);
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        tank.readFromNBT(nbt);
        exposeTank = nbt.getBoolean("exposeTank");
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
}
