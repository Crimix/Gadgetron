package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.tile.TileEntitySmelter;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSmelter extends BlockMachineBase {
	
	public BlockSmelter(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntitySmelter(this.getLocalizedName());
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}
