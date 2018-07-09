package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergyGenerator extends BlockMachineBase {

	private int capacity;
	private int capacityTank;
	private int speed;
	private int generate;
	private int consume;
	
	public BlockEnergyGenerator(String name, int capacity, int capacityTank, int speed, int generate, int consume) {
		super(name);
		this.capacity = capacity;
		this.capacityTank = capacityTank;
		this.speed = speed;
		this.generate = generate;
		this.consume = consume;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityEnergyGenerator(this.getLocalizedName(), capacity, capacityTank, speed, generate, consume);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}
