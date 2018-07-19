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
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCoalGenerator extends TileEntityEnergyInventoryBase {

	public TileEntityCoalGenerator(int energyCapcaity, int energyPerTick) {
		super(new CustomEnergyStorage(energyCapcaity, 0, Integer.MAX_VALUE), new FilteredItemStackHandler(1, 0, (i) -> isItemFuel(i)));
		this.energyPerTick = energyPerTick;
	}

	public TileEntityCoalGenerator() {
		this(ModConfig.machines.coalGenerator_t1.capacity, ModConfig.machines.coalGenerator_t1.generateRfPerTick);
	}

	public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            int burnTime = ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(Blocks.WOODEN_SLAB))
            {
                return 150;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOOL))
            {
                return 100;
            }
            else if (item == Item.getItemFromBlock(Blocks.CARPET))
            {
                return 67;
            }
            else if (item == Item.getItemFromBlock(Blocks.LADDER))
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON))
            {
                return 100;
            }
            else if (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD)
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK))
            {
                return 16000;
            }
            else if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()))
            {
                return 200;
            }
            else if (item == Items.STICK)
            {
                return 100;
            }
            else if (item != Items.BOW && item != Items.FISHING_ROD)
            {
                if (item == Items.SIGN)
                {
                    return 200;
                }
                else if (item == Items.COAL)
                {
                    return 1600;
                }
                else if (item == Items.LAVA_BUCKET)
                {
                    return 20000;
                }
                else if (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL)
                {
                    if (item == Items.BLAZE_ROD)
                    {
                        return 2400;
                    }
                    else if (item instanceof ItemDoor && item != Items.IRON_DOOR)
                    {
                        return 200;
                    }
                    else
                    {
                        return item instanceof ItemBoat ? 400 : 0;
                    }
                }
                else
                {
                    return 100;
                }
            }
            else
            {
                return 300;
            }
        }
    }

	public static boolean isItemFuel(ItemStack stack)
	{
		return getItemBurnTime(stack) > 0;
	}

	@Override
	public void update() {
		if(!world.isRemote) {
			super.update();
			if(!this.energyContainer.isFull()) {
				if(burnTime == 0) {
					on = false;
					ItemStack stack = inventory.getStackInSlot(0);
					if(isItemFuel(stack)) {
						inventory.extractItemInternal(0, 1, false);
						timeToBurn = getItemBurnTime(stack);
						on = true;
						burnTime++;
						this.energyContainer.receiveEnergyInternal(energyPerTick, false);
					}
				}else {
					if(burnTime % timeToBurn  == 0) {
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
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGUI(EntityPlayer player) {
		return new GuiCoalGenerator(player, this);
	}

	@Override
	public Container getContainer(EntityPlayer player) {
		return new ContainerCoalGenerator(player.inventory, this);
	}

}
