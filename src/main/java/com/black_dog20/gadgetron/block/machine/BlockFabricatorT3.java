package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.tile.TileEntityFabricator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFabricatorT3 extends BlockFabricator {
	
	public BlockFabricatorT3(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityFabricator(ModConfig.machines.fabricator_t3.capacity, ModConfig.machines.extractor_t3.consumeRfPertick, ModConfig.machines.fabricator_t3.speed);
	}
}
