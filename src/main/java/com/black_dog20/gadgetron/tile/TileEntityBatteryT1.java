package com.black_dog20.gadgetron.tile;

import com.black_dog20.gadgetron.client.gui.GuiBattery;
import com.black_dog20.gadgetron.client.gui.GuiCoalGenerator;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.container.ContainerBattery;
import com.black_dog20.gadgetron.container.ContainerCoalGenerator;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;
import com.black_dog20.gadgetron.utility.CustomEnergyStorage;
import com.black_dog20.gadgetron.utility.FilteredItemStackHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityBatteryT1 extends TileEntityEnergyBase {

	public TileEntityBatteryT1() {
		super(new CustomEnergyStorage(ModConfig.machines.battery_t1.capacity, ModConfig.machines.battery_t1.rfInputPerTick, ModConfig.machines.battery_t1.rfOutputPerTick));
	}

	public TileEntityBatteryT1(String name) {
		super(new CustomEnergyStorage(ModConfig.machines.battery_t1.capacity, ModConfig.machines.battery_t1.rfInputPerTick, ModConfig.machines.battery_t1.rfOutputPerTick));
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
