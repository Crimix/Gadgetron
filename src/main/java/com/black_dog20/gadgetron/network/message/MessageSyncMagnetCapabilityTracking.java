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

public class MessageSyncMagnetCapabilityTracking implements IMessage, IMessageHandler<MessageSyncMagnetCapabilityTracking, IMessage>{

	private NBTTagCompound nbt;
	private int id;
	
	public MessageSyncMagnetCapabilityTracking() {}
	
	public MessageSyncMagnetCapabilityTracking(IBeltHandler mh, EntityPlayer player) {
		nbt = mh.writeToNBT();
		id = player.getEntityId();
	}
	
	
	@Override
	public IMessage onMessage(MessageSyncMagnetCapabilityTracking message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			EntityPlayer playerTrack = Gadgetron.Proxy.getPlayerByIDFromMessageContext(message.id, ctx);
			IBeltHandler mh = BeltHandler.instanceFor(playerTrack);
			if(mh != null){
				mh.readFromNBT(message.nbt);
			}
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		ByteBufUtils.writeTag(buf, nbt);
	}

}
