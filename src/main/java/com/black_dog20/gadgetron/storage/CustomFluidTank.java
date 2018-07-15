package com.black_dog20.gadgetron.storage;

import java.util.function.Function;

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
	
	public void transfer(IFluidHandler target, int amount) {
		FluidStack toExtract = this.drain(amount, false);
		int inserted = target.fill(toExtract, true);
		this.drain(inserted, true);
	}
	
	public void tryExtract(IFluidHandler target, int amount) {
		FluidStack toExtract = target.drain(amount, false);
		int inserted = this.fill(toExtract, true);
		target.drain(inserted, true);
	}
	
	public boolean isFull() {
		return this.getFluidAmount() == this.capacity;
	}
	
	public boolean isEmpty() {
		return this.getFluidAmount() == 0;
	}
	
	public boolean hasSpacefor(FluidStack stack) {
		return this.canFillFluidType(stack) && (this.fluid == null || this.fluid.isFluidEqual(stack)) && this.fillInternal(stack, false) == stack.amount;
	}

}
