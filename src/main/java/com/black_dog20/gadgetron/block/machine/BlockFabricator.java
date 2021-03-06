package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.tile.TileEntityFabricator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFabricator extends BlockMachineBase {
	
	public BlockFabricator(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityFabricator();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}
