package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.tile.TileEntityBattery;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBattery extends BlockMachineBase {
	
	private int capacity;
	private int inputRate;
	private int outputRate
	;
	public BlockBattery(String name, int capacity, int inputRate, int outputRate) {
		super(name);
		this.capacity = capacity;
		this.inputRate = inputRate;
		this.outputRate = outputRate;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityBattery(this.getLocalizedName(), capacity, inputRate, outputRate);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}
