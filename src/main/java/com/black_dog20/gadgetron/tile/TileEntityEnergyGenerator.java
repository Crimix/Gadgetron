package com.black_dog20.gadgetron.tile;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.black_dog20.gadgetron.client.gui.GuiEnergyGenerator;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.container.ContainerEnergyGenerator;
import com.black_dog20.gadgetron.recipehandler.FuelGeneratorHandler;
import com.black_dog20.gadgetron.recipehandler.FuelGeneratorHandler.FuelObject;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.storage.CustomFluidTank;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryFluidBase;
import com.black_dog20.gadgetron.utility.MachineFaces;
import com.black_dog20.gadgetron.utility.Varient;

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

	protected int fuelUse = 100; 

	public TileEntityEnergyGenerator(int capacity, int capacityTank, double speed) {
		super(new CustomEnergyStorage(capacity, 0, Integer.MAX_VALUE), 1, 1, new CustomFluidTank(capacityTank, (f) -> isFuel(f)));
		tankFaces = new MachineFaces(this, Varient.TANK, true, false);
		inventoryFaces = new MachineFaces(this, Varient.IVENTORY, false, false);
		this.speed = speed;
	}
	
	
	public TileEntityEnergyGenerator() {
		this(ModConfig.machines.fuelGenerator_t1.capacity ,ModConfig.machines.fuelGenerator_t1.capacityTank, ModConfig.machines.fuelGenerator_t1.speed);
	}

	
	private static boolean isFuel(FluidStack fluid) {
		return fluid != null && FuelGeneratorHandler.instance().getFuel(fluid.getFluid()) != null;
	}

	@Override
	public void update() {
		if(!world.isRemote) {
			if(!this.energyContainer.isFull()) {
				if(burnTime == 0) {
					on = false;
					FluidStack fluid = this.tank.drain(fuelUse, false);
					if(isFuel(fluid)) {
						FuelObject fuel = FuelGeneratorHandler.instance().getFuel(fluid.getFluid());
						timeToBurn = (int)Math.ceil(fuel.getTotalBurningTime()*((double)(fluid.amount / (double)fuelUse)*speed));
						energyPerTick = fuel.getPowerPerCycle();
						this.tank.drain(fuelUse, true);
						on = true;
						burnTime++;
						addEnergy();
					}
				}else {
					if(timeToBurn == 0 )
						return;
					if(burnTime % timeToBurn == 0) {
						burnTime = 0;
						on = false;
					}else {
						addEnergy();
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

	private void addEnergy() {
		int energy = getEnergyPerTick();
		this.energyContainer.receiveEnergyInternal(energy, false);
	}
	
	public String getGeneratePerTick() {
		return Integer.toString(getEnergyPerTick());
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

	@Override
	public int getProgress() {
		double t = ((double)burnTime / timeToBurn) *100;
		return (int) Math.ceil(t);
	}

	@Override
	public String getRemainingTime() {
		if(burnTime != 0) {
			double ticksLeft = timeToBurn - burnTime;
			double secs = ticksLeft / 20;
			int secI = (int) Math.ceil(secs);
			return Integer.toString(secI) + "s";
		}else {
			return null;
		}
	}

	@Override
	public int getEnergyPerTick() {
		return Math.max(10,(int)Math.ceil(((double)getStoredFluidPercentage() /100) * energyPerTick));
	}

	public double getFuelUsePerTick() {
		return new BigDecimal((double)fuelUse / timeToBurn).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		speed = nbt.getDouble("speed");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setDouble("speed", speed);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public boolean hasInventory(){
		return false;
	}

}
