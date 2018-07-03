package com.black_dog20.gadgetron.container;

import com.black_dog20.gadgetron.container.slot.BucketSlot;
import com.black_dog20.gadgetron.storage.CustomItemHandler;
import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerEnergyGenerator extends Container{

	private BucketSlot input;
	private SlotItemHandler output;

	public ContainerEnergyGenerator(InventoryPlayer playerInventory, TileEntityEnergyGenerator tile){
		output = new SlotItemHandler(tile.getInventory(), 1, 54, 53);
		input = new BucketSlot(true, (CustomItemHandler) tile.getInventory(), 0, 54, 17) {
			@Override
			public void onSlotChanged() {
				if(isItemValid(getStack())) {
					if(tile.getTank().fill(getFluid(), false) >= Fluid.BUCKET_VOLUME) {
						if(tile.getTank().getFluidAmount() != 0) {
							FluidStack t = new FluidStack(tile.getFluid(), tile.getFluid().amount+getFluid().amount);
							tile.getTank().setFluid(t);
						}
						else {
							tile.getTank().setFluid(getFluid());
						}
						if(output.getHasStack()) {
							ItemStack temp = output.getStack();
							temp.setCount(output.getStack().getCount()+1);
							output.putStack(temp);
						}else {
							output.putStack(new ItemStack(Items.BUCKET));
						}
						putStack(ItemStack.EMPTY);
					}
				}
				super.onSlotChanged();
			}
		};
		
		this.addSlotToContainer(input);
		this.addSlotToContainer(output);
		for(int i = 0; i < 3; ++i){
			for(int j = 0; j < 9; ++j){
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int i = 0; i < 9; ++i){
			this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
		}
	}

	

	@Override
	public boolean canInteractWith(EntityPlayer playerIn){
		return true;
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            
            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

}
