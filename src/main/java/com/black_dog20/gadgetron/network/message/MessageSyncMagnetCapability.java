package com.black_dog20.gadgetron.network.message;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.capability.BeltHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncMagnetCapability implements IMessage, IMessageHandler<MessageSyncMagnetCapability, IMessage>{

	private NBTTagCompound nbt;
	
	public MessageSyncMagnetCapability() {}
	
	public MessageSyncMagnetCapability(IBeltHandler mh) {
		nbt = mh.writeToNBT();
	}
	
	
	@Override
	public IMessage onMessage(MessageSyncMagnetCapability message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			EntityPlayer player = Gadgetron.Proxy.getPlayerFromMessageContext(ctx);
			IBeltHandler mh = BeltHandler.instanceFor(player);
			if(mh != null){
				mh.readFromNBT(message.nbt);
			}
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

}
