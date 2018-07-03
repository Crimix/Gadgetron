package com.black_dog20.gadgetron.container.slot;

import com.black_dog20.gadgetron.init.ModFluids;
import com.black_dog20.gadgetron.storage.CustomItemHandler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BucketSlot extends CustomSlotItemHandler {

	private boolean input;
	
	public BucketSlot(boolean input, CustomItemHandler inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.input = input;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if(!input)
			return false;
		else
			return ItemStack.areItemsEqual(stack, UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.fluidTrillium));
	}
	
	public FluidStack getFluid() {
		UniversalBucket bucket = new UniversalBucket();
		if(getStack().getItem() == Items.LAVA_BUCKET)
			return new FluidStack(FluidRegistry.LAVA, Fluid.BUCKET_VOLUME);
		else if(getStack().getItem() == Items.WATER_BUCKET)
			return new FluidStack(FluidRegistry.WATER, Fluid.BUCKET_VOLUME);
		else
			return bucket.getFluid(getStack());
	}

}
