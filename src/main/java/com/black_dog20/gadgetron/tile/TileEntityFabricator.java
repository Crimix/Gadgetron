package com.black_dog20.gadgetron.tile;

import com.black_dog20.gadgetron.client.gui.GuiFabricator;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.container.ContainerFabricator;
import com.black_dog20.gadgetron.recipehandler.FabricatorRecipes;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityFabricator extends TileEntityEnergyInventoryBase {

	private ItemStack result;
	
	public TileEntityFabricator() {
		super(new CustomEnergyStorage(ModConfig.machines.fabricator.capacity, Integer.MAX_VALUE, 0),2,1);
		inventoryFaces.SetCanAutoInput(false);
	}

	public TileEntityFabricator(String name) {
		super(new CustomEnergyStorage(ModConfig.machines.fabricator.capacity, Integer.MAX_VALUE, 0),2,1);
		this.name = name;
		inventoryFaces.SetCanAutoInput(false);
	}


	@Override
	public void update() {
		if(!world.isRemote) {
			energyPerTick = ModConfig.machines.extractor.consumeRfPertick;
			if(!this.energyContainer.isEmpty()) {
				if(burnTime == 0) {
					on = false;
					ItemStack s = inventory.extractItemInternal(0, 1, true);
					ItemStack s2 = inventory.extractItemInternal(1, 1, true);
					Tuple<ItemStack, ItemStack> temp = new Tuple<ItemStack, ItemStack>(s, s2);
					//System.out.println(FabricatorRecipes.instance().containsRecipe(temp));
					if(s != null && !s.isEmpty() && FabricatorRecipes.instance().containsRecipe(temp)) {
						result = FabricatorRecipes.instance().getResult(temp);
						if(result != null && !result.isEmpty() && inventory.canMerge(2, result)) {
							inventory.extractItemInternal(0, s.getCount(), false);
							inventory.extractItemInternal(1, s2.getCount(), false);
							currentUsedTime = (int) Math.ceil(FabricatorRecipes.instance().getTime(temp) * ModConfig.machines.fabricator.speed);
							burnTime++;
							on = true;
						}
					}
				} else if(burnTime % currentUsedTime == 0 && on) {
					if(result != null && !result.isEmpty() && inventory.canMerge(2, result)) {
						inventory.insertItemInternal(2, result.copy(), false);
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
		return new GuiFabricator(player, this);
	}

	@Override
	public Container getContainer(EntityPlayer player) {
		return new ContainerFabricator(player.inventory, this);
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
	
}
