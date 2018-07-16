package com.black_dog20.gadgetron.capability;

import com.black_dog20.gadgetron.storage.CustomItemHandlerBase;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class BeltStorage implements IStorage<IBeltHandler>{

	public NBTTagCompound writeNBT(Capability<IBeltHandler> capability, IBeltHandler instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("magnetOn", instance.getHasMagnetOn());
		nbt.setBoolean("belt", instance.getHasBelt());
		nbt.setBoolean("sneakDeactivate", instance.getSneakDeactivate());
		nbt.setBoolean("tempOff", instance.getTempOff());
		nbt.setBoolean("visible", instance.isVisible());
		nbt.setTag("inventory", instance.getInventory().serializeNBT());
		
		return nbt;
	}

	public void readNBT(Capability<IBeltHandler> capability, IBeltHandler instance, EnumFacing side, NBTBase nbt) {
		instance.setHasMagnetOn(((NBTTagCompound) nbt).getBoolean("magnetOn"));
		instance.setHasBelt(((NBTTagCompound) nbt).getBoolean("belt"));
		instance.setSneakDeactivate(((NBTTagCompound) nbt).getBoolean("sneakDeactivate"));
		instance.setTempOff(((NBTTagCompound) nbt).getBoolean("tempOff"));
		instance.setVisible(((NBTTagCompound) nbt).getBoolean("visible"));
		CustomItemHandlerBase temp = instance.getInventory();
		temp.deserializeNBT(((NBTTagCompound) nbt).getCompoundTag("inventory"));
		if(temp.getSlots() != BeltHandler.SIZE) {
			temp.setSize(BeltHandler.SIZE);
		}
		instance.setInventory(temp);
		
	}

}
