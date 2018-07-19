package com.black_dog20.gadgetron.container;

import javax.annotation.Nonnull;

import com.black_dog20.gadgetron.container.slot.CustomSlotItemHandler;
import com.black_dog20.gadgetron.storage.CustomItemHandler;
import com.black_dog20.gadgetron.tile.TileEntityFabricator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFabricator extends Container{

	private CustomSlotItemHandler output;
	private CustomSlotItemHandler inputA;
	private CustomSlotItemHandler inputB;

	public ContainerFabricator(InventoryPlayer playerInventory, TileEntityFabricator tile){
		
		inputA = new CustomSlotItemHandler((CustomItemHandler) tile.getInventory(), 0, 33, 35);
		inputB = new CustomSlotItemHandler((CustomItemHandler) tile.getInventory(), 1, 63, 35);
		output = new CustomSlotItemHandler((CustomItemHandler) tile.getInventory(), 2, 123, 35) {
		    @Override
		    public boolean isItemValid(@Nonnull ItemStack stack)
		    {
		        	return false;
		    }
		};
		
		this.addSlotToContainer(inputA);
		this.addSlotToContainer(inputB);
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
