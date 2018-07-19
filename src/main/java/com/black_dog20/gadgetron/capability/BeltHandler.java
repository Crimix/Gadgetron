package com.black_dog20.gadgetron.capability;

import java.util.concurrent.Callable;

import com.black_dog20.gadgetron.network.PacketHandler;
import com.black_dog20.gadgetron.network.message.MessageSyncMagnetCapability;
import com.black_dog20.gadgetron.network.message.MessageSyncMagnetCapabilityTracking;
import com.black_dog20.gadgetron.storage.CustomItemHandlerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BeltHandler implements IBeltHandler, ICapabilitySerializable<NBTTagCompound> {

	public static final int SIZE = 29;
	
	@CapabilityInject(IBeltHandler.class) 
	public static final Capability<IBeltHandler> CAP = null;
	public CustomItemHandlerBase inventory = new CustomItemHandlerBase(SIZE);
	private boolean magnetOn = false;
	private boolean sneakDeactivate = false;
	private boolean belt = false;
	private boolean dirty = false;
	private boolean tempOff = false;
	private boolean visible = true;
	
	@Override
	public void markDirty() {
		dirty = true;
	}
	
	@Override
	public void setHasMagnetOn(boolean hasMagnetOn) {
		magnetOn = hasMagnetOn;
		dirty = true;
	}

	@Override
	public boolean getHasMagnetOn() {
		return magnetOn;
	}
	
	@Override
	public void setSneakDeactivate(boolean sneakDeactivate) {
		this.sneakDeactivate = sneakDeactivate;
		dirty = true;
	}

	@Override
	public boolean getSneakDeactivate() {
		return sneakDeactivate;
	}

	@Override
	public void setHasBelt(boolean hasBelt) {
		belt = hasBelt;
		dirty = true;
	}

	@Override
	public boolean getHasBelt() {
		return belt;
	}
	
	@Override
	public void setTempOff(boolean tempOff) {
		this.tempOff = tempOff;
		dirty = true;
	}

	@Override
	public boolean getTempOff() {
		return tempOff;
	}
	
	
	@Override
	public void setInventory(CustomItemHandlerBase inventory) {
		this.inventory = inventory;
		dirty = true;
	}

	@Override
	public CustomItemHandlerBase getInventory() {
		return inventory;
	}
	
	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
		dirty = true;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}


	@Override
	public void copyTo(IBeltHandler other) {
		other.setHasBelt(belt);
		other.setHasMagnetOn(magnetOn);
		other.setSneakDeactivate(sneakDeactivate);
		other.setTempOff(tempOff);
		other.setVisible(visible);
		other.setInventory(inventory);
	}
	
	@Override
	public void updateClient(EntityPlayer player){
		if(!player.world.isRemote && dirty){
			PacketHandler.network.sendTo(new MessageSyncMagnetCapability(this), (EntityPlayerMP) player);
			((WorldServer) player.world).getEntityTracker().sendToTracking(player, PacketHandler.network.getPacketFrom(new MessageSyncMagnetCapabilityTracking(this, player)));
			dirty = false;
		}
	}
	
	public static IBeltHandler instanceFor(EntityPlayer player) { 
		return player.getCapability(CAP, null); 
	}

	
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CAP ? CAP.<T> cast(this) : null;

	}

	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) CAP.getStorage().writeNBT(CAP, this, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		CAP.getStorage().readNBT(CAP, this, null, nbt);
		this.dirty = true;
	}
	
	public static final class Factory implements Callable<BeltHandler>
	{
		  @Override
		  public BeltHandler call() throws Exception
		  {
		    return new BeltHandler();
		  }
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		magnetOn = nbt.getBoolean("magnetOn");
		belt = nbt.getBoolean("belt");
		sneakDeactivate = nbt.getBoolean("sneakDeactivate");
		tempOff = nbt.getBoolean("tempOff");
		magnetOn = nbt.getBoolean("magnetOn");
		visible = nbt.getBoolean("visible");
		CustomItemHandlerBase temp = new CustomItemHandlerBase();
		temp.deserializeNBT(nbt.getCompoundTag("inventory"));
		if(temp.getSlots() != BeltHandler.SIZE) {
			temp.setSize(BeltHandler.SIZE);
		}
		inventory = temp;
	}

	@Override
	public NBTTagCompound writeToNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("magnetOn", magnetOn);
		nbt.setBoolean("belt", belt);
		nbt.setBoolean("sneakDeactivate", sneakDeactivate);
		nbt.setBoolean("tempOff", tempOff);
		nbt.setBoolean("visible", visible);
		nbt.setTag("inventory", inventory.serializeNBT());
		
		return nbt;
	}


}
