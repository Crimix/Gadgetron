package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.tile.TileEntityExtractor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockExtractorT3 extends BlockExtractor {
	
	public BlockExtractorT3(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityExtractor(ModConfig.machines.extractor_t3.capacity, ModConfig.machines.extractor_t3.consumeRfPertick, ModConfig.machines.extractor_t3.speed);
	}
}
