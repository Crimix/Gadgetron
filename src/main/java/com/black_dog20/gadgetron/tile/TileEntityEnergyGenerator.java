package com.black_dog20.gadgetron.tile;

import com.black_dog20.gadgetron.client.gui.GuiEnergyGenerator;
import com.black_dog20.gadgetron.container.ContainerEnergyGenerator;
import com.black_dog20.gadgetron.utility.CustomEnergyStorage;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityEnergyGenerator extends TileEntityEnergyFluidBase {

	
	public TileEntityEnergyGenerator() {
		super(new CustomEnergyStorage(100000, 0, Integer.MAX_VALUE), new FluidTank(new FluidStack(FluidRegistry.LAVA, 1000) ,10000), true);
	}
	
	@Override
	public void update() {
		this.energyContainer.receiveEnergyInternal(300, false);
		if (energyContainer.isEmpty()) {
			return;
		}
		for (EnumFacing f : EnumFacing.VALUES) {
			TileEntity te = world.getTileEntity(pos.offset(f));
			if (te != null) {
				IEnergyStorage energyStorage = te.getCapability(CapabilityEnergy.ENERGY, f.getOpposite());
				if (energyStorage != null) {
					energyContainer.transferEnergy(energyStorage);
					te.markDirty();
					this.markDirty();
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGUI(InventoryPlayer inventory) {
		return new GuiEnergyGenerator(inventory, this);
	}

	@Override
	public Container getContainer(InventoryPlayer inventory) {
		return new ContainerEnergyGenerator(inventory, this);
	}
	


}
