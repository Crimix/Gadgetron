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
	
	public TileEntityFabricator(int capacity, int energyPerTick, double speed) {
		super(new CustomEnergyStorage(capacity, Integer.MAX_VALUE, 0),2,1);
		this.energyPerTick = energyPerTick;
		this.speed = speed;
		inventoryFaces.SetCanAutoInput(false);
	}

	public TileEntityFabricator() {
		this(ModConfig.machines.fabricator_t1.capacity, ModConfig.machines.extractor_t1.consumeRfPertick, ModConfig.machines.fabricator_t1.speed);
	}


	@Override
	public void update() {
		if(!world.isRemote) {
			if(!this.energyContainer.isEmpty()) {
				if(burnTime == 0) {
					on = false;
					ItemStack s = null;
					ItemStack s2 = null;
					Tuple<ItemStack, ItemStack> temp = null;
					for(int i = 1; i <= 64; i++) {
						for(int j = 1; j <= 64; j++) {
							s = inventory.extractItemInternal(0, i, true);
							s2 = inventory.extractItemInternal(1, j, true);
							
							temp = new Tuple<ItemStack, ItemStack>(s, s2);
							if(s != null && !s.isEmpty() && s2 != null && !s2.isEmpty() && temp != null && FabricatorRecipes.instance().containsRecipe(temp))
								break;
						}
						if(s != null && !s.isEmpty() && s2 != null && !s2.isEmpty() && temp != null && FabricatorRecipes.instance().containsRecipe(temp))
							break;
					}
					if(s != null && !s.isEmpty() && s2 != null && !s2.isEmpty() && temp != null && FabricatorRecipes.instance().containsRecipe(temp)) {
						result = FabricatorRecipes.instance().getResult(temp);
						if(result != null && !result.isEmpty() && inventory.canMerge(2, result)) {
							inventory.extractItemInternal(0, s.getCount(), false);
							inventory.extractItemInternal(1, s2.getCount(), false);
							currentUsedTime = (int) Math.ceil(FabricatorRecipes.instance().getTime(temp) * speed);
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
