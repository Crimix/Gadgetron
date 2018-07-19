package com.black_dog20.gadgetron.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
	}
	
	@Override
	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx) {
		return ctx.getServerHandler().player;
	}

	@Override
	public EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerRendersInit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void registerKeyBindings() {
		// NOOP

	}

	@Override
	public void registerKeyInputHandler() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerRendersPreInit() {
		// TODO Auto-generated method stub
		
	}
}
