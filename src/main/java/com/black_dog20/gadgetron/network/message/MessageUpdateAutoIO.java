package com.black_dog20.gadgetron.network.message;

import com.black_dog20.gadgetron.tile.base.TileEntityBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyFluidBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryFluidBase;
import com.black_dog20.gadgetron.utility.Automation;
import com.black_dog20.gadgetron.utility.Varient;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateAutoIO implements IMessage, IMessageHandler<MessageUpdateAutoIO, IMessage>{

	private String id;
	private String varient;
	private int x;
	private int y; 
	private int z;

	public MessageUpdateAutoIO() {}

	public MessageUpdateAutoIO(Automation id, Varient varient, BlockPos pos) {
		this.id = id.toString();
		this.varient = varient.toString();
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}


	@Override
	public IMessage onMessage(MessageUpdateAutoIO message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> {
			TileEntityBase te = (TileEntityBase) player.world.getTileEntity(new BlockPos(message.x, message.y, message.z));
			if((te instanceof TileEntityEnergyInventoryBase) && Varient.IVENTORY == Varient.valueOf(message.varient)) {
				TileEntityEnergyInventoryBase tile = (TileEntityEnergyInventoryBase)te;
				tile.inventoryFaces.changeIO(Automation.valueOf(message.id));
			}
			else if((te instanceof TileEntityEnergyInventoryFluidBase || te instanceof TileEntityEnergyFluidBase) && Varient.TANK == Varient.valueOf(message.varient)) {
				if(te instanceof TileEntityEnergyInventoryFluidBase) {
					TileEntityEnergyInventoryFluidBase tile = (TileEntityEnergyInventoryFluidBase)te;
					tile.tankFaces.changeIO(Automation.valueOf(message.id));
				} else {
					TileEntityEnergyFluidBase tile = (TileEntityEnergyFluidBase)te;
					tile.tankFaces.changeIO(Automation.valueOf(message.id));
				}
			}
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = ByteBufUtils.readUTF8String(buf);
		this.varient = ByteBufUtils.readUTF8String(buf);
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, id);
		ByteBufUtils.writeUTF8String(buf, varient);
		buf.writeInt(x);
		buf.writeInt(y);;
		buf.writeInt(z);
	}

}
