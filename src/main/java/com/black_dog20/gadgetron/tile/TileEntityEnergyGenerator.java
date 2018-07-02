package com.black_dog20.gadgetron.tile;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.black_dog20.gadgetron.client.gui.GuiEnergyGenerator;
import com.black_dog20.gadgetron.container.ContainerEnergyGenerator;
import com.black_dog20.gadgetron.init.ModFluids;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryFluidBase;
import com.black_dog20.gadgetron.utility.CustomEnergyStorage;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityEnergyGenerator extends TileEntityEnergyInventoryFluidBase {

	private int burnTime = 0;
	private double ticksToBurnfuel = 60.0;
	private int energyPerTick = 100;
	private int fuelUse = 10;
	
	public TileEntityEnergyGenerator() {
		super(new CustomEnergyStorage(100000, 0, Integer.MAX_VALUE), 2, false, new FluidTank(10000) {
		    
			@Override
			public boolean canFillFluidType(FluidStack fluid)
		    {
		        return fluid.getFluid() == ModFluids.fluidTrillium;
		    }
		} , true);
	}
	
	@Override
	public void update() {
		if(!this.energyContainer.isFull()) {
			if(burnTime == 0) {
				FluidStack fluid = this.tank.drain(fuelUse, false);
				if(fluid != null && fluid.amount == fuelUse) {
					this.tank.drain(fuelUse, true);
					burnTime++;
					this.energyContainer.receiveEnergyInternal(energyPerTick, false);
				}
			}else {
				if(burnTime % ticksToBurnfuel  == 0) {
					burnTime = 0;
				}else {
					this.energyContainer.receiveEnergyInternal(energyPerTick, false);
					burnTime++;
				}
			}
			
		}
		else {
			burnTime=0;
		}
		
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
	
	public int getProgress() {
		double t = (burnTime / ticksToBurnfuel) *100;
		return (int) Math.ceil(t);
	}
	
	public String getRemainingTime() {
		if(burnTime != 0) {
			double ticksLeft = ticksToBurnfuel - burnTime;
			double secs = ticksLeft / 20;
			int secI = (int) Math.ceil(secs);
			return Integer.toString(secI) + "s";
		}else {
			return null;
		}
	}

	public int getEnergyPerTick() {
		return energyPerTick;
	}
	
	public double getFuelUsePerTick() {
		return new BigDecimal(fuelUse / ticksToBurnfuel).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		burnTime = nbt.getInteger("burnTime");
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("burnTime", burnTime);
		return super.writeToNBT(nbt);
	}
	
}
