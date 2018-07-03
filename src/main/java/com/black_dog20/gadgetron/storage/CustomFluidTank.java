package com.black_dog20.gadgetron.storage;

import java.util.function.Function;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

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

}
