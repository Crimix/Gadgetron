package com.black_dog20.gadgetron.tile;

import com.black_dog20.gadgetron.client.gui.GuiBattery;
import com.black_dog20.gadgetron.container.ContainerBattery;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyBase;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityBattery extends TileEntityEnergyBase {

	public TileEntityBattery() {
		super();
	}

	public TileEntityBattery(String name, int capacity, int inputRate, int outputRate) {
		super(new CustomEnergyStorage(capacity, inputRate, outputRate));
		this.name = name;
	}


	@Override
	public void update() {
		if(!world.isRemote) {
			if (energyContainer.isEmpty()) {
				return;
			}
			for (EnumFacing f : EnumFacing.VALUES) {
				TileEntity te = world.getTileEntity(pos.offset(f));
				if (te != null && !te.isInvalid()) {
					IEnergyStorage energyStorage = te.getCapability(CapabilityEnergy.ENERGY, f.getOpposite());
					if (energyStorage != null) {
						energyContainer.transferEnergy(energyStorage);
						te.markDirty();
						this.markDirty();
					}
				} 
				continue;
			}
			sendUpdates();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGUI(InventoryPlayer inventory) {
		return new GuiBattery(inventory, this);
	}

	@Override
	public Container getContainer(InventoryPlayer inventory) {
		return new ContainerBattery(inventory, this);
	}

}
