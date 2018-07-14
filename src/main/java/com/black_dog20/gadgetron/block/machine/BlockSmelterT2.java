package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.tile.TileEntitySmelter;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSmelterT2 extends BlockSmelter {
	
	public BlockSmelterT2(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntitySmelter(ModConfig.machines.smelter_t2.capacity, ModConfig.machines.smelter_t2.capacityTank, ModConfig.machines.smelter_t2.consumeRfPertick, ModConfig.machines.smelter_t2.speed);
	}
}
