package com.black_dog20.gadgetron.tile.base;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.storage.CustomFluidTank;
import com.black_dog20.gadgetron.storage.CustomItemHandler;
import com.black_dog20.gadgetron.storage.InputTankWrapper;
import com.black_dog20.gadgetron.storage.OutputTankWrapper;
import com.black_dog20.gadgetron.utility.MachineFaces;
import com.black_dog20.gadgetron.utility.Varient;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public abstract class TileEntityEnergyInventoryFluidBase extends TileEntityEnergyInventoryBase{
	
	protected CustomFluidTank tank;
	public MachineFaces tankFaces;

	
	public TileEntityEnergyInventoryFluidBase() {
		super();
	}
	
	public TileEntityEnergyInventoryFluidBase(CustomEnergyStorage storage, int intputSlots, int outputSlots, int sizeMB) {
		super(storage, intputSlots,outputSlots);
		tank = new CustomFluidTank(sizeMB);
		tankFaces = new MachineFaces(this, Varient.TANK,true,true);
		tankFaces.setFaceing(faceing);
	}
	
	public TileEntityEnergyInventoryFluidBase(CustomEnergyStorage storage, int intputSlots, int outputSlots, CustomFluidTank tank) {
		super(storage,intputSlots,outputSlots);
		this.tank = tank;
		tankFaces = new MachineFaces(this, Varient.TANK,true,true);
		tankFaces.setFaceing(faceing);
	}
	
	public TileEntityEnergyInventoryFluidBase(CustomEnergyStorage storage, CustomItemHandler inventory, int sizeMB) {
		super(storage, inventory);
		tank = new CustomFluidTank(sizeMB);
		tankFaces = new MachineFaces(this, Varient.TANK,true,true);
		tankFaces.setFaceing(faceing);
	}
	
	public TileEntityEnergyInventoryFluidBase(CustomEnergyStorage storage, CustomItemHandler inventory, CustomFluidTank tank) {
		super(storage,inventory);
		this.tank = tank;
		tankFaces = new MachineFaces(this, Varient.TANK,true,true);
		tankFaces.setFaceing(faceing);
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
	
	@Override
	public boolean hasConfig() {
		return true;
	}
	
	@Override
	public boolean hasInventory() {
		return true;
	}
	
	@Override
	public boolean hasTank() {
		return true;
	}
	
	@Override
	public void update() {
		if(!world.isRemote) {
			if(currentTickBewteen % ticksBetweenAutoIO == 0) {
			if(tankFaces.isAutoOutput()) {
				for (EnumFacing f : EnumFacing.VALUES) {
					if(tankFaces.isFaceOutput(f)) {
						TileEntity te = world.getTileEntity(pos.offset(f));
						if (te != null && !te.isInvalid()) {
							IFluidHandler fluidStorage = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, f.getOpposite());
							if (fluidStorage != null) {
								tank.transfer(fluidStorage, ModConfig.machines.automation_mb);
								te.markDirty();
								this.markDirty();
							}
						}
					}

					continue;
				}
			}
			if(tankFaces.isAutoInput()) {
				for (EnumFacing f : EnumFacing.VALUES) {
					if(tankFaces.isFaceInput(f)) {
						TileEntity te = world.getTileEntity(pos.offset(f));
						if (te != null && !te.isInvalid()) {
							IFluidHandler fluidStorage = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, f.getOpposite());
							if (fluidStorage != null) {
								tank.tryExtract(fluidStorage, ModConfig.machines.automation_mb);
								te.markDirty();
								this.markDirty();
							}
						}
					}

					continue;
				}
			}
			}
			super.update();
		}
	}
	
	@Override
	public void setFront(String facing) {
		super.setFront(facing);
		tankFaces.setFaceing(facing);
	}
}
