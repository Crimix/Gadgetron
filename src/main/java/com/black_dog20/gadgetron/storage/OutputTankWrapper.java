package com.black_dog20.gadgetron.storage;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class OutputTankWrapper extends FluidTank {

	private FluidTank internalTank;
	
	public OutputTankWrapper(int capacity, FluidTank hidden) {
		super(capacity);
		internalTank = hidden;
	}
	
	@Override
    public FluidStack getFluid()
    {
        return internalTank.getFluid();
    }

	@Override
    public void setFluid(@Nullable FluidStack fluid)
    {
        internalTank.setFluid(fluid);
    }

    @Override
    public int getFluidAmount()
    {
    	return internalTank.getFluidAmount();
    }

    @Override
    public int getCapacity()
    {
        return internalTank.getCapacity();
    }

    @Override
    public void setCapacity(int capacity)
    {
        internalTank.setCapacity(capacity);
    }

    @Override
    public void setTileEntity(TileEntity tile)
    {
        internalTank.setTileEntity(tile);
    }

    @Override
    public FluidTankInfo getInfo()
    {
        return internalTank.getInfo();
    }

    @Override
    public IFluidTankProperties[] getTankProperties()
    {
    	return internalTank.getTankProperties();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill)
    {
    	return 0;
    }

    public int fillInternal(FluidStack resource, boolean doFill)
    {
        return 0;
    }

    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain)
    {
    	return internalTank.drain(resource, doDrain);
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain)
    {
    	return internalTank.drain(maxDrain, doDrain);
    }

    @Override
    @Nullable
    public FluidStack drainInternal(FluidStack resource, boolean doDrain)
    {
    	return null;
    }

    @Override
    @Nullable
    public FluidStack drainInternal(int maxDrain, boolean doDrain)
    {
    	return null;
    }

    @Override
    public boolean canFill()
    {
        return false;
    }

    @Override
    public boolean canDrain()
    {
        return internalTank.canDrain();
    }

    @Override
    public void setCanFill(boolean canFill)
    {
    }

    @Override
    public void setCanDrain(boolean canDrain)
    {
    	internalTank.setCanDrain(canDrain);
    }

    @Override
    public boolean canFillFluidType(FluidStack fluid)
    {
    	return false;
    }

    @Override
    public boolean canDrainFluidType(@Nullable FluidStack fluid)
    {
        return internalTank.canDrainFluidType(fluid);
    }

}
