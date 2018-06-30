package com.black_dog20.gadgetron.tile;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.utility.CustomEnergyStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
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

}