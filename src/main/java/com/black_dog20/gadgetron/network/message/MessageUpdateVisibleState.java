package com.black_dog20.gadgetron.network.message;


import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateVisibleState implements IMessage, IMessageHandler<MessageUpdateVisibleState, IMessage>{


	public MessageUpdateVisibleState() {}
	
	@Override
	public IMessage onMessage(MessageUpdateVisibleState message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> {
			IBeltHandler mh = BeltHandler.instanceFor(player);
			mh.setVisible(!mh.isVisible());
			
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

}
