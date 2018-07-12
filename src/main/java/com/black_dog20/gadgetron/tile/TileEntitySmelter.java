package com.black_dog20.gadgetron.tile;

import com.black_dog20.gadgetron.client.gui.GuiSmelter;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.container.ContainerSmelter;
import com.black_dog20.gadgetron.recipehandler.SmelterRecipes;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.storage.CustomFluidTank;
import com.black_dog20.gadgetron.storage.CustomItemHandler;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryFluidBase;
import com.black_dog20.gadgetron.utility.MachineFaces;
import com.black_dog20.gadgetron.utility.Varient;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySmelter extends TileEntityEnergyInventoryFluidBase {
	
	private int burnTime = 0;
	private int currentItemBurnTime = 1;
	private FluidStack result;
	
	public TileEntitySmelter() {
		super(new CustomEnergyStorage(ModConfig.machines.smelter.capacity, Integer.MAX_VALUE, 0), new CustomItemHandler(2, 1, new boolean[] {true,false,false}), new CustomFluidTank(ModConfig.machines.smelter.capacityTank, (f) -> false));
	}

	public TileEntitySmelter(String name) {
		super(new CustomEnergyStorage(ModConfig.machines.smelter.capacity, Integer.MAX_VALUE, 0), new CustomItemHandler(2, 1, new boolean[] {true,false,false}), new CustomFluidTank(ModConfig.machines.smelter.capacityTank, (f) -> false));
		this.name = name;
		tankFaces = new MachineFaces(this, Varient.TANK, false, true);
		inventoryFaces = new MachineFaces(this, Varient.IVENTORY, true, false);
	}


	@Override
	public void update() {
		if(!world.isRemote) {
			if(!this.energyContainer.isEmpty() && !this.tank.isFull()) {
				if(burnTime == 0) {
					on = false;
					ItemStack s = inventory.extractItemInternal(0, 1, true);
					if(s != null && !s.isEmpty()) {
						result = SmelterRecipes.instance().getResult(s);
						if(result != null && tank.hasSpacefor(result)) {
							inventory.extractItemInternal(0, 1, false);
							currentItemBurnTime = (int) Math.ceil(SmelterRecipes.instance().getSmeltingTime(s) * ModConfig.machines.smelter.speed);
							burnTime++;
							on = true;
						}
					}
				} else if(burnTime % currentItemBurnTime == 0 && on) {
					if(result != null && tank.hasSpacefor(result)) {
						tank.fillInternal(result, true);
						result = null;
						burnTime = 0;
						currentItemBurnTime = 1;
						on = false;
					}
				} else {
					if(on && energyContainer.extractEnergyInternal(ModConfig.machines.smelter.consumeRfPertick, true) == ModConfig.machines.smelter.consumeRfPertick) {
						energyContainer.extractEnergyInternal(ModConfig.machines.smelter.consumeRfPertick, false);
						burnTime++;
					}
				}
			}
			super.update();
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
		burnTime = nbt.getInteger("burnTime");
		currentItemBurnTime = nbt.getInteger("currentItemBurnTime");
		result = FluidStack.loadFluidStackFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("burnTime", burnTime);
		nbt.setInteger("currentItemBurnTime", currentItemBurnTime);
		if(result != null)
			result.writeToNBT(nbt);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
		nbt.setInteger("burnTime", burnTime);
		nbt.setInteger("currentItemBurnTime", currentItemBurnTime);
		return super.writeCustomInfoToNBT(nbt);
	}
	
	@Override
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
			burnTime = nbt.getInteger("burnTime");
			currentItemBurnTime = nbt.getInteger("currentItemBurnTime");
			super.readFromCustomInfoNBT(nbt);
		}
	}

	public int getProgress() {
		if(currentItemBurnTime == 0) {
			return 0;
		}
		double t = ((double)burnTime / currentItemBurnTime) *100;
		return (int) Math.ceil(t);
	}

	public String getRemainingTime() {
		if(burnTime != 0) {
			double ticksLeft = currentItemBurnTime - burnTime;
			double secs = ticksLeft / 20;
			int secI = (int) Math.ceil(secs);
			return Integer.toString(secI) + "s";
		}else {
			return null;
		}
	}
}
