package com.black_dog20.gadgetron.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IProxy {

	void preInit(FMLPreInitializationEvent event);
	
	EntityPlayer getPlayerFromMessageContext(MessageContext ctx);
	
	EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx);

	void registerRendersInit();

	void registerKeyBindings();

	void registerKeyInputHandler();
}
