package com.black_dog20.gadgetron.tile;

import com.black_dog20.gadgetron.client.gui.GuiCoalGenerator;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.container.ContainerCoalGenerator;
import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.storage.FilteredItemStackHandler;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;

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

public class TileEntityCoalGenerator extends TileEntityEnergyInventoryBase {

	private int burnTime = 0;
	private int energyPerTick = ModConfig.machines.coalGenerator.generateRfPerTick;
	private int currentItemBurnTime = 1;

	public TileEntityCoalGenerator() {
		super(new CustomEnergyStorage(ModConfig.machines.coalGenerator.capacity, 0, Integer.MAX_VALUE), new FilteredItemStackHandler(1,0, (i) -> isItemFuel(i)));
	}

	public TileEntityCoalGenerator(String name) {
		super(new CustomEnergyStorage(ModConfig.machines.coalGenerator.capacity, 0, Integer.MAX_VALUE), new FilteredItemStackHandler(1,0, (i) -> isItemFuel(i)));
		this.name = name;
	}

	public static int getItemBurnTime(ItemStack stack)
	{
		if (stack.isEmpty())
		{
			return 0;
		}
		else
		{
			Item item = stack.getItem();
			if (!item.getRegistryName().getResourceDomain().equals("minecraft"))
			{
				int burnTime = net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
				if (burnTime != 0) return burnTime;
			}
			return item == Item.getItemFromBlock(Blocks.WOODEN_SLAB) ? 150 : (item == Item.getItemFromBlock(Blocks.WOOL) ? 100 : (item == Item.getItemFromBlock(Blocks.CARPET) ? 67 : (item == Item.getItemFromBlock(Blocks.LADDER) ? 300 : (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON) ? 100 : (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD ? 300 : (item == Item.getItemFromBlock(Blocks.COAL_BLOCK) ? 16000 : (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()) ? 200 : (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()) ? 200 : (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()) ? 200 : (item == Items.STICK ? 100 : (item != Items.BOW && item != Items.FISHING_ROD ? (item == Items.SIGN ? 200 : (item == Items.COAL ? 1600 : (item == Items.LAVA_BUCKET ? 20000 : (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL ? (item == Items.BLAZE_ROD ? 2400 : (item instanceof ItemDoor && item != Items.IRON_DOOR ? 200 : (item instanceof ItemBoat ? 400 : 0))) : 100)))) : 300)))))))))));
		}
	}

	public static boolean isItemFuel(ItemStack stack)
	{
		return getItemBurnTime(stack) > 0;
	}

	@Override
	public void update() {
		if(!world.isRemote) {
			sendUpdates();
			if(!this.energyContainer.isFull()) {
				if(burnTime == 0) {
					on = false;
					ItemStack stack = inventory.getStackInSlot(0);
					if(isItemFuel(stack)) {
						inventory.extractItemInternal(0, 1, false);
						currentItemBurnTime = getItemBurnTime(stack);
						on = true;
						burnTime++;
						this.energyContainer.receiveEnergyInternal(energyPerTick, false);
					}
				}else {
					if(burnTime % currentItemBurnTime  == 0) {
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
					}
				}
			}
			sendUpdates();
			this.markDirty();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGUI(InventoryPlayer inventory) {
		return new GuiCoalGenerator(inventory, this);
	}

	@Override
	public Container getContainer(InventoryPlayer inventory) {
		return new ContainerCoalGenerator(inventory, this);
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

	public int getEnergyPerTick() {
		return energyPerTick;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		burnTime = nbt.getInteger("burnTime");
		currentItemBurnTime = nbt.getInteger("currentItemBurnTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("burnTime", burnTime);
		nbt.setInteger("currentItemBurnTime", currentItemBurnTime);
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

}
