package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergyGenerator extends BlockMachineBase {

	public BlockEnergyGenerator(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityEnergyGenerator();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}
