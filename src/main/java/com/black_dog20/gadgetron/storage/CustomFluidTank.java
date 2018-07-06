package com.black_dog20.gadgetron.storage;

import java.util.function.Function;

import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class CustomFluidTank extends FluidTank{

	private Function<FluidStack,Boolean> validator;
	
	public CustomFluidTank(int capacity, Function<FluidStack,Boolean> validator) {
		super(capacity);
		this.validator = validator;
	}
	
	public CustomFluidTank(int capacity) {
		super(capacity);
	}
	
	@Override
    public boolean canFillFluidType(FluidStack fluid)
    {
		if(validator != null && validator.apply(fluid)) {
			return true && canFill();
		}
        return super.canFillFluidType(fluid);
    }
	
	public void transfer(IFluidHandler target) {
		FluidStack toExtract = this.drain(20, true);
		int inserted = target.fill(toExtract, false);
		this.drain(inserted, false);
	}
	
	public void tryExtract(IFluidHandler target) {
		FluidStack toExtract = target.drain(20, true);
		int inserted = this.fill(toExtract, false);
		target.drain(inserted, false);
	}

}
