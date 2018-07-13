package com.black_dog20.gadgetron.tile;

import com.black_dog20.gadgetron.client.gui.GuiFabricator;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.container.ContainerFabricator;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityFabricator extends TileEntityEnergyInventoryBase {

	
	public TileEntityFabricator() {
		super(new CustomEnergyStorage(ModConfig.machines.fabricator.capacity, Integer.MAX_VALUE, 0),2,1);
	}

	public TileEntityFabricator(String name) {
		super(new CustomEnergyStorage(ModConfig.machines.fabricator.capacity, Integer.MAX_VALUE, 0),2,1);
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
		return new GuiFabricator(player, this);
	}

	@Override
	public Container getContainer(EntityPlayer player) {
		return new ContainerFabricator(player.inventory, this);
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
