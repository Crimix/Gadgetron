package com.black_dog20.gadgetron.container.slot;

import com.black_dog20.gadgetron.storage.CustomFluidTank;
import com.black_dog20.gadgetron.storage.CustomItemHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class BucketSlot extends CustomSlotItemHandler {

	private boolean input;
	private Fluid fluid;
	private CustomFluidTank tank;
	
	public BucketSlot(boolean input, Fluid fluid, CustomItemHandler inventoryIn, int index, int xPosition, int yPosition, CustomFluidTank tank) {
		super(inventoryIn, index, xPosition, yPosition);
		this.input = input;
		this.fluid = fluid;
		this.tank = tank;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if(!input)
			return false;
		else
			if(fluid != null) {
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack);
				FluidStack f = FluidUtil.getFluidContained(stack);
				return handler != null && f != null && f.getFluid() == fluid;
			}
			else{
				IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack);
				FluidStack f = FluidUtil.getFluidContained(stack);
				return handler != null && (f == null || f.amount == 0 || f.isFluidEqual(tank.getFluid()));
			}
	}

}
