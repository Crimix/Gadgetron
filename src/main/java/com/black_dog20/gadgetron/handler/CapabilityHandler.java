package com.black_dog20.gadgetron.handler;

import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.network.PacketHandler;
import com.black_dog20.gadgetron.network.message.MessageSyncMagnetCapabilityTracking;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {

	@SubscribeEvent 
	public void addPlayerCapabilities(AttachCapabilitiesEvent<Entity> entity) {
		if(entity.getObject() instanceof EntityPlayer){
			entity.addCapability(new ResourceLocation(Reference.MOD_ID, "BeltHandler"), new BeltHandler());
		}
	}

	@SubscribeEvent 
	public void persistPlayerCapabilities(PlayerEvent.Clone e) {
			IBeltHandler newCap = e.getEntityPlayer().getCapability(BeltHandler.CAP, null);
			IBeltHandler oldCap = e.getOriginal().getCapability(BeltHandler.CAP, null);

			if(oldCap != null)
				oldCap.copyTo(newCap);
	}
	
	@SubscribeEvent 
	public void OnPlayerStartTrackingPlayer(PlayerEvent.StartTracking event){
		if(event.getTarget().world.isRemote) return;
		if(event.getTarget() instanceof EntityPlayer){
			EntityPlayer trackedPlayer = (EntityPlayer) event.getTarget();
			EntityPlayer trackingPlayer = event.getEntityPlayer();
			PacketHandler.network.sendTo(new MessageSyncMagnetCapabilityTracking(BeltHandler.instanceFor(trackedPlayer),trackedPlayer), (EntityPlayerMP) trackingPlayer);
		}
	}
	
	@SubscribeEvent
	public void OnPlayerCapabilityUpdate(LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if(!player.world.isRemote) {
				IBeltHandler mh = player.getCapability(BeltHandler.CAP, null);
				if(mh != null){
					mh.updateClient(player);
				}
			}
		}
	}
}
