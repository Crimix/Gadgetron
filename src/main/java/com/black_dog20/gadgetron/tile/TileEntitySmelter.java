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
	
	private FluidStack result;
	
	public TileEntitySmelter(int energyCapcity, int capacityTank, int energyPerTick, double speed ) {
		super(new CustomEnergyStorage(energyCapcity, Integer.MAX_VALUE, 0), new CustomItemHandler(2, 1, new boolean[] {true,false,false}), new CustomFluidTank(capacityTank, (f) -> false));
		this.energyPerTick = energyPerTick;
		this.speed = speed;
		tankFaces = new MachineFaces(this, Varient.TANK, false, true);
		inventoryFaces = new MachineFaces(this, Varient.IVENTORY, true, false);
	}

	public TileEntitySmelter() {
		this(ModConfig.machines.smelter_t1.capacity, ModConfig.machines.smelter_t1.capacityTank, ModConfig.machines.smelter_t1.consumeRfPertick, ModConfig.machines.smelter_t1.speed);
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
							currentUsedTime = (int) Math.ceil(SmelterRecipes.instance().getSmeltingTime(s) * speed);
							burnTime++;
							on = true;
						}
					}
				} else if(burnTime % currentUsedTime == 0 && on) {
					if(result != null && tank.hasSpacefor(result)) {
						tank.fillInternal(result, true);
						result = null;
						burnTime = 0;
						currentUsedTime = 1;
						on = false;
					}
				} else {
					if(on && energyContainer.extractEnergyInternal(energyPerTick, true) == energyPerTick) {
						energyContainer.extractEnergyInternal(energyPerTick, false);
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
		result = FluidStack.loadFluidStackFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		if(result != null)
			result.writeToNBT(nbt);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
		if(result != null)
			result.writeToNBT(nbt);
		return super.writeCustomInfoToNBT(nbt);
	}
	
	@Override
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
			result = FluidStack.loadFluidStackFromNBT(nbt);
			super.readFromCustomInfoNBT(nbt);
		}
	}
}
