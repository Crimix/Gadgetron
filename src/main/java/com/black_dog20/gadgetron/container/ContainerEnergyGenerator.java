package com.black_dog20.gadgetron.container;

import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerEnergyGenerator extends Container{


	public ContainerEnergyGenerator(InventoryPlayer playerInventory, TileEntityEnergyGenerator tile){

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

}
