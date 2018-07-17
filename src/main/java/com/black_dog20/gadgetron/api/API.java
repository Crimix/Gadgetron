package com.black_dog20.gadgetron.api;

import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.storage.CustomItemHandlerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class API {
	
	/**
	 * Checks if the stack is either in the belt inventory or in the players inventory
	 * @param player The player
	 * @param stack The ItemStack
	 * @return True if stack is in either inventory
	 */
	public static boolean isItemStackInInventory(EntityPlayer player, ItemStack stack){
		IBeltHandler bh = BeltHandler.instanceFor(player);
		if(bh != null) {
			CustomItemHandlerBase inventory = bh.getInventory();
			return inventory.hasItemStack(stack) || player.inventory.hasItemStack(stack);
		}
		return player.inventory.hasItemStack(stack);
	}
}
