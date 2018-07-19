package com.black_dog20.gadgetron.container;

import com.black_dog20.gadgetron.api.GadgetronAPI;
import com.black_dog20.gadgetron.api.ISpecialEquipment.SpecialEquipmentType;
import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.container.slot.SpecicalEquipmentSlot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBelt extends Container{


	public ContainerBelt(InventoryPlayer playerInventory, EntityPlayer player){
		
		IBeltHandler mh = BeltHandler.instanceFor(player);
		if(mh != null && mh.getHasBelt()){
			
			if(GadgetronAPI.doesEquipmentListContainType(SpecialEquipmentType.MAGNET)) {
				this.addSlotToContainer(new SpecicalEquipmentSlot(mh.getInventory(), 27, 68, 5, SpecialEquipmentType.MAGNET));
			}
			if(GadgetronAPI.doesEquipmentListContainType(SpecialEquipmentType.MED)) {
				this.addSlotToContainer(new SpecicalEquipmentSlot(mh.getInventory(), 28, 92, 5, SpecialEquipmentType.MED));
			}

			for(int i = 0; i < 3; ++i){
				for(int j = 0; j < 9; ++j){
					this.addSlotToContainer(new SlotItemHandler(mh.getInventory(), j + i * 9, 8 + j * 18, 26 + i * 18));
				}
			}
		
		}
		
		
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
