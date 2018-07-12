package com.black_dog20.gadgetron.tile.base;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.storage.CustomEnergyStorage;
import com.black_dog20.gadgetron.storage.CustomItemHandler;
import com.black_dog20.gadgetron.storage.InputItemHandlerWrapper;
import com.black_dog20.gadgetron.storage.OutputItemHandlerWrapper;
import com.black_dog20.gadgetron.utility.MachineFaces;
import com.black_dog20.gadgetron.utility.Varient;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileEntityEnergyInventoryBase extends TileEntityEnergyBase {
	
	protected CustomItemHandler inventory = null;
	public MachineFaces inventoryFaces;
	
	protected int burnTime = 0;
	protected int currentUsedTime = 1;
	protected int energyPerTick = 1;

	public TileEntityEnergyInventoryBase() {
		super();
	}
	
	public TileEntityEnergyInventoryBase(CustomEnergyStorage storage, int inputSlots, int outputSlots) {
		super(storage);
		inventory = new CustomItemHandler(inputSlots,outputSlots);
		boolean hasInput = false;
		boolean hasOutput = false;
		
		if(inputSlots > 0)
			hasInput = true;
		if(outputSlots > 0)
			hasOutput = true;
		
		inventoryFaces = new MachineFaces(this, Varient.IVENTORY,hasInput,hasOutput);
		inventoryFaces.setFaceing(faceing);
	}
	
	public TileEntityEnergyInventoryBase(CustomEnergyStorage storage, CustomItemHandler inventory) {
		super(storage);
		this.inventory = inventory;
		boolean hasInput = false;
		boolean hasOutput = false;
		
		if(inventory.getNumberOfInputSlots() > 0)
			hasInput = true;
		if(inventory.getNumberOfOutputSlots() > 0)
			hasOutput = true;
		inventoryFaces = new MachineFaces(this, Varient.IVENTORY,hasInput,hasOutput);
		inventoryFaces.setFaceing(faceing);
	}

	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventoryFaces.isFaceInput(facing) && inventoryFaces.isFaceOutput(facing)) 
			return (T) this.inventory;
		else if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventoryFaces.isFaceInput(facing) && !inventoryFaces.isFaceOutput(facing))
			return (T) new InputItemHandlerWrapper(inventory);
		else if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && !inventoryFaces.isFaceInput(facing) && inventoryFaces.isFaceOutput(facing))
			return (T) new OutputItemHandlerWrapper(inventory);
		return super.getCapability(capability, facing);
	}
	
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
    	nbt.setTag("inventory", inventory.serializeNBT());
    	inventoryFaces.writeToNBT(nbt);
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        inventory.deserializeNBT((NBTTagCompound) nbt.getTag("inventory"));
        inventoryFaces.readFromNBT(nbt);
    }
    
    public ItemStackHandler getInventory() {
    	return this.inventory;
    }
    
    @Override
	public NBTTagCompound writeCustomInfoToNBT(NBTTagCompound nbt) {
		if(nbt == null)
			nbt = new NBTTagCompound();
		nbt.setTag("inventory", inventory.serializeNBT());
		inventoryFaces.writeToNBT(nbt);
		return super.writeCustomInfoToNBT(nbt);
	}
	
	@Override
	public void readFromCustomInfoNBT(NBTTagCompound nbt) {
		if(nbt != null) {
	        inventory.deserializeNBT((NBTTagCompound) nbt.getTag("inventory"));
	        inventoryFaces.readFromNBT(nbt);
	        super.readFromCustomInfoNBT(nbt);
		}
	}
	
	@Override
	public boolean hasConfig() {
		return true;
	}
	
	@Override
	public boolean hasInventory() {
		return true;
	}
	
	@Override
	public boolean hasTank() {
		return false;
	}

	@Override
	public void update() {
		if(!world.isRemote) {
			if(currentTickBewteen % ticksBetweenAutoIO == 0) {
			if(inventoryFaces.isAutoOutput()) {
				for (EnumFacing f : EnumFacing.VALUES) {
					if(inventoryFaces.isFaceOutput(f)) {
						TileEntity te = world.getTileEntity(pos.offset(f));
						if (te != null && !te.isInvalid()) {
							IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, f.getOpposite());
							if (handler != null) {
								inventory.transfer(handler);
								te.markDirty();
								this.markDirty();
							}
						}
					}

					continue;
				}
			}

			if(inventoryFaces.isAutoInput()) {
				for (EnumFacing f : EnumFacing.VALUES) {
					if(inventoryFaces.isFaceInput(f)) {
						TileEntity te = world.getTileEntity(pos.offset(f));
						if (te != null && !te.isInvalid()) {
							IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, f.getOpposite());
							if (handler != null) {
								inventory.tryExtract(handler);
								te.markDirty();
								this.markDirty();
							}
						}
					}

					continue;
				}
			}
			}
			super.update();
		}
	}
	
	@Override
	public void setFront(String facing) {
		super.setFront(facing);
		inventoryFaces.setFaceing(facing);
	}
	
	public int getProgress() {
		if(currentUsedTime == 0) {
			return 0;
		}
		double t = ((double)burnTime / currentUsedTime) *100;
		return (int) Math.ceil(t);
	}

	public String getRemainingTime() {
		if(burnTime != 0) {
			double ticksLeft = currentUsedTime - burnTime;
			double secs = ticksLeft / 20;
			int secI = (int) Math.ceil(secs);
			return Integer.toString(secI) + "s";
		}else {
			return null;
		}
	}
	
	public String getName() {
		return "";
	}
}
