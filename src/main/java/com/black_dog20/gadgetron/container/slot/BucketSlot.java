package com.black_dog20.gadgetron.container.slot;

import com.black_dog20.gadgetron.storage.CustomFluidTank;
import com.black_dog20.gadgetron.storage.CustomItemHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class BucketSlot extends CustomSlotItemHandler {

	private boolean input;
	private CustomFluidTank tank;
	
	public BucketSlot(boolean input, CustomItemHandler inventoryIn, int index, int xPosition, int yPosition, CustomFluidTank tank) {
		super(inventoryIn, index, xPosition, yPosition);
		this.input = input;
		this.tank = tank;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if(!input)
			return false;
		else
			return true;
	}
		
	public boolean isItemStackValid(ItemStack stack) {
		IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack);
		FluidStack f = FluidUtil.getFluidContained(stack);
		if(handler != null && f != null)
			return tank.canFillFluidType(f) && tank.hasSpacefor(f);
		else if(handler != null && f != null && f.amount == 0)
			return true;
		else
			return false;
	}

}
