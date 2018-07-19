package com.black_dog20.gadgetron.tile;

import java.util.HashSet;
import java.util.Set;

import com.black_dog20.gadgetron.client.gui.GuiBattery;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.container.ContainerBattery;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyBase;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityBattery extends TileEntityEnergyBase {

	private Set<BlockPos> coordinates = new HashSet<BlockPos>();
	
	
	public TileEntityBattery(int energyCapacity, int inputRF, int outputRF) {
		super(new CustomEnergyStorage(energyCapacity, inputRF, outputRF));
	}
	
	public TileEntityBattery() {
		this(ModConfig.machines.battery_t1.capacity, ModConfig.machines.battery_t1.rfInputPerTick, ModConfig.machines.battery_t1.rfOutputPerTick);
	}

	@Override
	public void update() {
		if(!world.isRemote) {
			if (energyContainer.isEmpty()) {
				return;
			}
			
			this.coordinates.forEach((pos) -> {
				for (EnumFacing f : EnumFacing.VALUES) {
					TileEntity te = world.getTileEntity(pos);
					if (te != null && !te.isInvalid()) {
						IEnergyStorage energyStorage = te.getCapability(CapabilityEnergy.ENERGY, f);
						if (energyStorage != null) {
							energyContainer.transferEnergy(energyStorage);
							te.markDirty();
							this.markDirty();
							break;
						}
					} 
			}
			});
			super.update();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGUI(EntityPlayer player) {
		return new GuiBattery(player, this);
	}

	@Override
	public Container getContainer(EntityPlayer player) {
		return new ContainerBattery(player.inventory, this);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagCompound tag = nbt.getCompoundTag("coordinates");
		int size = tag.getInteger("size");
		coordinates = new HashSet<BlockPos>();
		for(int i = 0; i < size; i++) {
			NBTTagCompound temp = tag.getCompoundTag(Integer.toString(i));
			int x = temp.getInteger("x");
			int y = temp.getInteger("y");
			int z = temp.getInteger("z");
			coordinates.add( new BlockPos(x, y, z));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = new NBTTagCompound();
		int size = coordinates.size();
		tag.setInteger("size", size);
		BlockPos[] arr = coordinates.toArray(new BlockPos[coordinates.size()]);
		for(int i = 0; i < size; i++) {
			BlockPos p = arr[i];
			NBTTagCompound temp = new NBTTagCompound();
			temp.setInteger("x",p.getX());
			temp.setInteger("y",p.getY());
			temp.setInteger("z",p.getZ());
			tag.setTag(Integer.toString(i), temp);
		}
		nbt.setTag("coordinates", tag);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
		NBTTagCompound tag = new NBTTagCompound();
		int size = coordinates.size();
		tag.setInteger("size", size);
		BlockPos[] arr = coordinates.toArray(new BlockPos[coordinates.size()]);
		for(int i = 0; i < size; i++) {
			BlockPos p = arr[i];
			NBTTagCompound temp = new NBTTagCompound();
			temp.setInteger("x",p.getX());
			temp.setInteger("y",p.getY());
			temp.setInteger("z",p.getZ());
			tag.setTag(Integer.toString(i), temp);
		}
		nbt.setTag("coordinates", tag);
		return super.writeCustomInfoToNBT(nbt);
	}
	
	@Override
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
			NBTTagCompound tag = nbt.getCompoundTag("coordinates");
			int size = tag.getInteger("size");
			coordinates = new HashSet<BlockPos>();
			for(int i = 0; i < size; i++) {
				NBTTagCompound temp = tag.getCompoundTag(Integer.toString(i));
				int x = temp.getInteger("x");
				int y = temp.getInteger("y");
				int z = temp.getInteger("z");
				coordinates.add( new BlockPos(x, y, z));
			}
			super.readFromCustomInfoNBT(nbt);
		}
	}
	
	public Set<BlockPos> getCoordinatesForPower(){
		return coordinates;
	}
	
	public void addCoordinateForPower(BlockPos pos){
		if(coordinates.contains(pos))
			coordinates.remove(pos);
		else
			coordinates.add(pos);
		this.markDirty();
		this.sendUpdates();
	}
}
