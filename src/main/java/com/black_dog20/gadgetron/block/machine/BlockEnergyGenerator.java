package com.black_dog20.gadgetron.block.machine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergyGenerator extends BlockMachineBase {

	public BlockEnergyGenerator() {
		super("energyGenerator");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return null;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

}
