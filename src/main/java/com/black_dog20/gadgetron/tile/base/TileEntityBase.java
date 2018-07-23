package com.black_dog20.gadgetron.tile.base;


import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityBase extends TileEntity implements ITickable{

	protected String faceing = "north";
	protected int tier = 1;
	
	public void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
		markDirty();
	}
	
	private IBlockState getState() {
		return world.getBlockState(pos);
	}
	
	@SideOnly(Side.CLIENT)
	public abstract GuiContainer getGUI(EntityPlayer player);
	
	public abstract Container getContainer(EntityPlayer player);
	
    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return this.getCapability(capability, facing) != null;
    }
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound data = new NBTTagCompound();
		return writeToNBT(data);
	}
	
    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound data = new NBTTagCompound();
        writeToNBT(data);
        return new SPacketUpdateTileEntity(this.pos, 1, data);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager networkManager, SPacketUpdateTileEntity s35PacketUpdateTileEntity) {
        readFromNBT(s35PacketUpdateTileEntity.getNbtCompound());
        world.markBlockRangeForRenderUpdate(this.pos, this.pos);
        this.world.notifyBlockUpdate(this.pos, world.getBlockState(this.pos), world.getBlockState(this.pos), 3);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }
    
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.faceing = nbt.getString("faceing");
		this.tier = nbt.getInteger("tier");
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setString("faceing", faceing);
		nbt.setInteger("tier", tier);
		return super.writeToNBT(nbt);
	}
	
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
		return nbt;
	}
	
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
		}
		sendUpdates();
	}

	public void setFront(String facing) {
		this.faceing = facing;
		sendUpdates();
	}
	
	public boolean hasConfig() {
		return false;
	}
	
	public boolean hasInventory() {
		return false;
	}
	
	public boolean hasTank() {
		return false;
	}
}