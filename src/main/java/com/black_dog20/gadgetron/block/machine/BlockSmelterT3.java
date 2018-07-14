package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.tile.TileEntitySmelter;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSmelterT3 extends BlockSmelter {
	
	public BlockSmelterT3(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntitySmelter(ModConfig.machines.smelter_t3.capacity, ModConfig.machines.smelter_t3.capacityTank, ModConfig.machines.smelter_t3.consumeRfPertick, ModConfig.machines.smelter_t3.speed);
	}
}
