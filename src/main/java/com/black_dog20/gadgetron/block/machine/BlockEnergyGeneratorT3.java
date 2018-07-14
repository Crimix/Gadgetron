package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergyGeneratorT3 extends BlockEnergyGenerator {

	public BlockEnergyGeneratorT3(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityEnergyGenerator(ModConfig.machines.fuelGenerator_t3.capacity, ModConfig.machines.fuelGenerator_t3.capacityTank, ModConfig.machines.fuelGenerator_t3.generateRfPerTick, ModConfig.machines.fuelGenerator_t3.speed, ModConfig.machines.fuelGenerator_t3.cosumeMbPerOperation);
	}
}
