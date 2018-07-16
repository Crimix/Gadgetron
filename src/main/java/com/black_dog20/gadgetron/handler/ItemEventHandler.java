package com.black_dog20.gadgetron.handler;

import com.black_dog20.gadgetron.api.ISpecialEquipment;
import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.storage.CustomItemHandlerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
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
	
	@SubscribeEvent
	public void onItemToss(ItemTossEvent event){
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

							((ISpecialEquipment)item).onTossEvent(event.getEntityItem(), stack, player);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityPickUpEvent(EntityItemPickupEvent event){
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

							((ISpecialEquipment)item).onPickUpEvent(player, event.getItem());
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onAttack(LivingAttackEvent event){
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

							((ISpecialEquipment)item).onAttackEvent(event.getEntityLiving(), event.getSource(), event.getAmount());
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event){
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

							((ISpecialEquipment)item).onDeathEvent(event.getEntityLiving(), event.getSource());
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onHeal(LivingHealEvent event){
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

							((ISpecialEquipment)item).onHealEvent(event.getEntityLiving(), event.getAmount());
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event){
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

							((ISpecialEquipment)item).onHurtEvent(event.getEntityLiving(), event.getSource(), event.getAmount());
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onFall(LivingFallEvent event){
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

							((ISpecialEquipment)item).onFallEvent(event.getEntityLiving(), event.getDistance(), event.getDamageMultiplier());
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onFallFly(PlayerFlyableFallEvent event){
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

							((ISpecialEquipment)item).onFallFlyEvent(event.getEntityPlayer(), event.getDistance(), event.getMultiplier());
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onStruckByLighting(EntityStruckByLightningEvent event){
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

							((ISpecialEquipment)item).onStruckByLightningevent(event.getEntity(), event.getLightning());
						}
					}
				}
			}
		}
	}
	
}
