package com.black_dog20.gadgetron.tile;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.black_dog20.gadgetron.client.gui.GuiEnergyGenerator;
import com.black_dog20.gadgetron.container.ContainerEnergyGenerator;
import com.black_dog20.gadgetron.init.ModFluids;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.storage.CustomFluidTank;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryFluidBase;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityEnergyGenerator extends TileEntityEnergyInventoryFluidBase {

	private int burnTime = 0;
	private int ticksToBurnfuel;
	private int energyPerTick;
	private int fuelUse;

	public TileEntityEnergyGenerator() {
		super();
	}

	public TileEntityEnergyGenerator(String name, int capacity, int capacityTank, int speed, int generate, int consume) {
		super(new CustomEnergyStorage(capacity, 0, Integer.MAX_VALUE), 1, 1, new CustomFluidTank(capacityTank, (f) -> isFuel(f)));
		this.name = name;
		this.ticksToBurnfuel = speed;
		this.energyPerTick = generate;
		this.fuelUse = consume;
	}
	
	private static boolean isFuel(FluidStack fluid) {
		return fluid.getFluid() == ModFluids.fluidTrillium;
	}

	@Override
	public void update() {
		if(!world.isRemote) {
			if(!this.energyContainer.isFull()) {
				if(burnTime == 0) {
					on = false;
					FluidStack fluid = this.tank.drain(fuelUse, false);
					if(fluid != null && fluid.amount == fuelUse) {
						this.tank.drain(fuelUse, true);
						on = true;
						burnTime++;
						this.energyContainer.receiveEnergyInternal(energyPerTick, false);
					}
				}else {
					if(burnTime % ticksToBurnfuel  == 0) {
						burnTime = 0;
						on = false;
					}else {
						this.energyContainer.receiveEnergyInternal(energyPerTick, false);
						burnTime++;
						on = true;
					}
				}

			}
			else {
				burnTime=0;
				on = false;
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
			super.update();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGUI(EntityPlayer player) {
		return new GuiEnergyGenerator(player, this);
	}

	@Override
	public Container getContainer(EntityPlayer player) {
		return new ContainerEnergyGenerator(player.inventory, this);
	}

	public int getProgress() {
		double t = ((double)burnTime / ticksToBurnfuel) *100;
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
		return new BigDecimal((double)fuelUse / ticksToBurnfuel).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		burnTime = nbt.getInteger("burnTime");
		ticksToBurnfuel = nbt.getInteger("ticksToBurnfuel");
		energyPerTick = nbt.getInteger("energyPerTick");
		ticksToBurnfuel = nbt.getInteger("ticksToBurnfuel");
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("burnTime", burnTime);
		nbt.setInteger("ticksToBurnfuel", ticksToBurnfuel);
		nbt.setInteger("energyPerTick", energyPerTick);
		nbt.setInteger("fuelUse", fuelUse);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public boolean hasInventory(){
		return false;
	}

}
