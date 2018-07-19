package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.tile.TileEntityBattery;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBatteryT3 extends BlockBattery {
	
	public BlockBatteryT3(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityBattery(ModConfig.machines.battery_t3.capacity, ModConfig.machines.battery_t3.rfInputPerTick, ModConfig.machines.battery_t3.rfOutputPerTick);
	}
}
