package com.black_dog20.gadgetron.tile;

import java.util.HashSet;
import java.util.Set;

import com.black_dog20.gadgetron.client.gui.GuiBattery;
import com.black_dog20.gadgetron.client.gui.GuiFabricator;
import com.black_dog20.gadgetron.client.gui.GuiMachine;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.container.ContainerBattery;
import com.black_dog20.gadgetron.container.ContainerMachine;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;

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

public class TileEntityExtractor extends TileEntityEnergyInventoryBase {

	
	public TileEntityExtractor() {
		super(new CustomEnergyStorage(ModConfig.machines.extractor.capacity, Integer.MAX_VALUE, 0),1,1);
	}

	public TileEntityExtractor(String name) {
		super(new CustomEnergyStorage(ModConfig.machines.extractor.capacity, Integer.MAX_VALUE, 0),1,1);
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
		return new GuiMachine(player, this);
	}

	@Override
	public Container getContainer(EntityPlayer player) {
		return new ContainerMachine(player.inventory, this);
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
