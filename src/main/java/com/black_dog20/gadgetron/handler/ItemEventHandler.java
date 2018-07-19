package com.black_dog20.gadgetron.handler;

import com.black_dog20.gadgetron.api.ISpecialEquipment;
import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.storage.CustomItemHandlerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemEventHandler {

	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event){
		if(event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			IBeltHandler bh = BeltHandler.instanceFor(player);
			if(bh != null) {
				CustomItemHandlerBase iventory = bh.getInventory();
				NonNullList<ItemStack> temp =  iventory.getStacks();
				for(ItemStack stack : temp) {
					if(stack != null && !stack.isEmpty()) {
						Item item = stack.getItem();
						if(stack.getItem() instanceof ISpecialEquipment) {

							((ISpecialEquipment)item).onWornTick(stack,player);
						}
					}
				}
			}
		}
	}
	
}
