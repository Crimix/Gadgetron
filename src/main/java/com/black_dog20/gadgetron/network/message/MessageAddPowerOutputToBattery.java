package com.black_dog20.gadgetron.network.message;

import com.black_dog20.gadgetron.tile.TileEntityBattery;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageAddPowerOutputToBattery implements IMessage, IMessageHandler<MessageAddPowerOutputToBattery, IMessage>{

	private int x;
	private int y; 
	private int z;
	private int addX;
	private int addY; 
	private int addZ;
	
	
	public MessageAddPowerOutputToBattery() {}
	
	public MessageAddPowerOutputToBattery(int x, int y, int z, int addX, int addY, int addZ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.addX = addX;
		this.addY = addY;
		this.addZ = addZ;
	}
	
	
	@Override
	public IMessage onMessage(MessageAddPowerOutputToBattery message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> {
			TileEntity te = player.world.getTileEntity(new BlockPos(message.x,message.y, message.z));
			if(te instanceof TileEntityBattery) {
				TileEntityBattery battery = (TileEntityBattery) te;
				battery.addCoordinateForPower(new BlockPos(message.addX, message.addY, message.addZ));
			}
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.addX = buf.readInt();
		this.addY = buf.readInt();
		this.addZ = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(addX);
		buf.writeInt(addY);;
		buf.writeInt(addZ);
	}

}
