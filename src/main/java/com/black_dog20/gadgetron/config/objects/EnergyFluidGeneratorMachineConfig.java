package com.black_dog20.gadgetron.config.objects;

import net.minecraftforge.common.config.Config;

public class EnergyFluidGeneratorMachineConfig {

	public EnergyFluidGeneratorMachineConfig(int consumeRfPertick, int capacityRF, int speed, int createMbPerOperation, int capacityTank) {
		this.consumeRfPertick = consumeRfPertick;
		this.capacity = capacityRF;
		this.speed=speed;
		this.createMbPerOperation = createMbPerOperation;
		this.capacityTank = capacityTank;
	}
	
	@Config.LangKey("gadgetron.config.machine.consumeRf")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int consumeRfPertick = 30;

	@Config.LangKey("gadgetron.config.machine.capacityRf")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int capacity = 100000;
	
	@Config.LangKey("gadgetron.config.machine.speed")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int speed = 60;
	
	@Config.LangKey("gadgetron.config.machine.createMb")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int createMbPerOperation = 10;

	@Config.LangKey("gadgetron.config.machine.capacityTank")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int capacityTank = 10000;
}