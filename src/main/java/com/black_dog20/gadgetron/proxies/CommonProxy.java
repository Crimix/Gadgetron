package com.black_dog20.gadgetron.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public abstract class CommonProxy implements IProxy {
	
	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx) {
		switch (ctx.side) {
		case CLIENT: {
			assert false : "Message for CLIENT received on dedicated server";
		}
		case SERVER: {
			EntityPlayer entityPlayerMP = ctx.getServerHandler().player;
			return entityPlayerMP;
		}
		default:
			assert false : "Invalid side in TestMsgHandler: " + ctx.side;
		}
		return null;

	}
}
