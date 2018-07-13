package com.black_dog20.gadgetron.tile;

import com.black_dog20.gadgetron.client.gui.GuiMachine;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.container.ContainerMachine;
import com.black_dog20.gadgetron.recipehandler.ProcessorRecipes;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityProcessor extends TileEntityEnergyInventoryBase {
	
	
	private ItemStack result;
	
	public TileEntityProcessor() {
		super(new CustomEnergyStorage(ModConfig.machines.processor.capacity, Integer.MAX_VALUE, 0),1,1);
		validatorItemInput = (i) -> ProcessorRecipes.instance().containsRecipe(i);
	}

	public TileEntityProcessor(String name) {
		super(new CustomEnergyStorage(ModConfig.machines.processor.capacity, Integer.MAX_VALUE, 0),1,1);
		this.name = name;
		validatorItemInput = (i) -> ProcessorRecipes.instance().containsRecipe(i);
	}


	@Override
	public void update() {
		if(!world.isRemote) {
			energyPerTick = ModConfig.machines.extractor.consumeRfPertick;
			if(!this.energyContainer.isEmpty()) {
				if(burnTime == 0) {
					on = false;
					ItemStack s = inventory.extractItemInternal(0, 1, true);
					if(s != null && !s.isEmpty() && ProcessorRecipes.instance().containsRecipe(s)) {
						result = ProcessorRecipes.instance().getResult(s);
						if(result != null && !result.isEmpty() && inventory.canMerge(1, result)) {
							inventory.extractItemInternal(0, 1, false);
							currentUsedTime = (int) Math.ceil(ProcessorRecipes.instance().getTime(s) * ModConfig.machines.processor.speed);
							burnTime++;
							on = true;
						}
					}
				} else if(burnTime % currentUsedTime == 0 && on) {
					if(result != null && !result.isEmpty() && inventory.canMerge(1, result)) {
						inventory.insertItemInternal(1, result.copy(), false);
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
		return new GuiMachine(player, this);
	}

	@Override
	public Container getContainer(EntityPlayer player) {
		return new ContainerMachine(player.inventory, this);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if(nbt.hasKey("resultItem")) {
			NBTTagCompound resultItemNBT = nbt.getCompoundTag("resultItem");
			result = new ItemStack(resultItemNBT);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		if(result != null) {
			NBTTagCompound resultItemNBT = new NBTTagCompound();
			result.writeToNBT(resultItemNBT);
			nbt.setTag("resultItem", resultItemNBT);
		}
		return super.writeToNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
		if(result != null) {
			NBTTagCompound resultItemNBT = new NBTTagCompound();
			result.writeToNBT(resultItemNBT);
			nbt.setTag("resultItem", resultItemNBT);
		}
		return super.writeCustomInfoToNBT(nbt);
	}
	
	@Override
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
			if(nbt.hasKey("resultItem")) {
				NBTTagCompound resultItemNBT = nbt.getCompoundTag("resultItem");
				result = new ItemStack(resultItemNBT);
			}
			super.readFromCustomInfoNBT(nbt);
		}
	}
	
	@Override
	public String getName() {
		return I18n.format("gadgetron.container.processor");
	}
}
