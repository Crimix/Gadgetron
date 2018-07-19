package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.tile.TileEntityProcessor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockProcessorT2 extends BlockProcessor {
	
	public BlockProcessorT2(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityProcessor(ModConfig.machines.processor_t2.capacity, ModConfig.machines.extractor_t2.consumeRfPertick, ModConfig.machines.processor_t2.speed);
	}
}
