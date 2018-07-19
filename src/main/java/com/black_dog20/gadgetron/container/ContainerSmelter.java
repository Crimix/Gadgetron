package com.black_dog20.gadgetron.container;

import com.black_dog20.gadgetron.container.slot.BucketSlot;
import com.black_dog20.gadgetron.container.slot.CustomSlotItemHandler;
import com.black_dog20.gadgetron.storage.CustomFluidTank;
import com.black_dog20.gadgetron.storage.CustomItemHandler;
import com.black_dog20.gadgetron.tile.TileEntitySmelter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class ContainerSmelter extends Container{

	private BucketSlot bucketInput;
	private CustomSlotItemHandler bucketoutput;
	private CustomSlotItemHandler input;
	
	public ContainerSmelter(InventoryPlayer playerInventory, TileEntitySmelter tile){
		
		
		input = new CustomSlotItemHandler((CustomItemHandler) tile.getInventory(), 0, 42, 34);
		bucketoutput = new CustomSlotItemHandler((CustomItemHandler) tile.getInventory(), 2, 106, 53)
		{
			@Override
			public void onSlotChanged() {
				bucketInput.onSlotChanged();
				super.onSlotChanged();
			}
		};
		
		bucketInput = new BucketSlot(true, (CustomItemHandler) tile.getInventory(), 1, 106, 17, (CustomFluidTank) tile.getTank()) {
			@Override
			public void onSlotChanged() {
				if(isItemValid(getStack()) && !bucketoutput.getHasStack() && isItemStackValid(getStack())) {
					ItemStack copy = getStack().copy();
					ItemStack s = decrStackSize(1);
					IFluidHandlerItem handler = FluidUtil.getFluidHandler(s);
					if(handler != null)
						if(handler.fill(tile.getFluid(), false) > 0) {
							int drain = handler.fill(tile.getFluid(), false);
							FluidStack fluid = tile.getTank().drain(drain, false);
							if(fluid.amount == drain) {
								handler.fill(fluid, true);
								tile.getTank().drain(drain, true);
								bucketoutput.putStack(handler.getContainer());
							}
						}
						else
							putStackNoNotify(copy);
				}
				super.onSlotChanged();
			}
		};
		
		this.addSlotToContainer(input);
		this.addSlotToContainer(bucketInput);
		this.addSlotToContainer(bucketoutput);
		
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
	
	@Override
	public void detectAndSendChanges()
    {
		super.detectAndSendChanges();
		bucketInput.onSlotChanged();
    }

}
