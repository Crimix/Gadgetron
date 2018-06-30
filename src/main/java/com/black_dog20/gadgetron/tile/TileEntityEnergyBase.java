package com.black_dog20.gadgetron.tile;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.utility.CustomEnergyStorage;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public abstract class TileEntityEnergyBase extends TileEntityBase {

	public TileEntityEnergyBase(CustomEnergyStorage storage) {
		this.energyContainer = storage;
	}
	
	protected CustomEnergyStorage energyContainer = null;

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY) 
			return (T) this.energyContainer;
		return super.getCapability(capability, facing);
	}

	@Override
	public abstract void update();

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.energyContainer.readFromNBT(nbt);
		super.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		this.energyContainer.writeToNBT(nbt);
		return super.writeToNBT(nbt);
	}
}