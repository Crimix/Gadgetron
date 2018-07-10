package com.black_dog20.gadgetron.tile;

import java.util.HashSet;
import java.util.Set;

import com.black_dog20.gadgetron.client.gui.GuiBattery;
import com.black_dog20.gadgetron.client.gui.GuiSmelter;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.container.ContainerBattery;
import com.black_dog20.gadgetron.container.ContainerSmelter;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.storage.CustomFluidTank;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryFluidBase;

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

public class TileEntitySmelter extends TileEntityEnergyInventoryFluidBase {
	
	public TileEntitySmelter() {
		super(new CustomEnergyStorage(ModConfig.machines.smelter.capacity, Integer.MAX_VALUE, 0),1, 1, new CustomFluidTank(ModConfig.machines.smelter.capacityTank, (f) -> false));
	}

	public TileEntitySmelter(String name) {
		super(new CustomEnergyStorage(ModConfig.machines.smelter.capacity, Integer.MAX_VALUE, 0),1, 1, new CustomFluidTank(ModConfig.machines.smelter.capacityTank, (f) -> false));
		this.name = name;
	}


	@Override
	public void update() {
		if(!world.isRemote) {
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGUI(EntityPlayer player) {
		return new GuiSmelter(player, this);
	}

	@Override
	public Container getContainer(EntityPlayer player) {
		return new ContainerSmelter(player.inventory, this);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return super.writeToNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
		return super.writeCustomInfoToNBT(nbt);
	}
	
	@Override
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
			super.readFromCustomInfoNBT(nbt);
		}
	}
}
