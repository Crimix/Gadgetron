package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.tile.TileEntityCoalGenerator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCoalGeneratorT2 extends BlockCoalGenerator {

	public BlockCoalGeneratorT2(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityCoalGenerator(ModConfig.machines.coalGenerator_t2.capacity, ModConfig.machines.coalGenerator_t2.generateRfPerTick);
	}
}
