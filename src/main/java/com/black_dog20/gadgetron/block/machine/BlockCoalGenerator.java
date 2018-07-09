package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.tile.TileEntityCoalGenerator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCoalGenerator extends BlockMachineBase {

	public BlockCoalGenerator() {
		super("coal_generator");
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityCoalGenerator(this.getLocalizedName());
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}
