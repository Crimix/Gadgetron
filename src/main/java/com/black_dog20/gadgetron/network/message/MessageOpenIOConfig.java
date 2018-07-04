package com.black_dog20.gadgetron.network.message;

import com.black_dog20.gadgetron.Gadgetron;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageOpenIOConfig implements IMessage, IMessageHandler<MessageOpenIOConfig, IMessage>{

	private int x;
	private int y; 
	private int z;
	
	public MessageOpenIOConfig() {}
	
	public MessageOpenIOConfig(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	@Override
	public IMessage onMessage(MessageOpenIOConfig message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> {
			player.openGui(Gadgetron.instance, 1, player.world, message.x, message.y, message.z);
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);;
		buf.writeInt(z);
	}

}
