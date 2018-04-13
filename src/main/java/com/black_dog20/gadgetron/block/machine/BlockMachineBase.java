package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.block.BlockBase;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineBase extends BlockBase implements ITileEntityProvider{

	public BlockMachineBase(String name) {
		super(Material.IRON, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return null;
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
