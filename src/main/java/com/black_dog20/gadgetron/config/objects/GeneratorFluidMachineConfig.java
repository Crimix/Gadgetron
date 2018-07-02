package com.black_dog20.gadgetron.config.objects;

import net.minecraftforge.common.config.Config;

public class GeneratorFluidMachineConfig {
	
	public GeneratorFluidMachineConfig(int generateRfPertick, int capacityRF, int speed, int cosumeMbPerOperation, int capacityTank) {
		this.generateRfPerTick = generateRfPertick;
		this.capacity = capacityRF;
		this.speed = speed;
		this.cosumeMbPerOperation = cosumeMbPerOperation;
		this.capacityTank = capacityTank;
	}
	
	@Config.LangKey("gadgetron.config.machine.generateRf")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int generateRfPerTick = 30;

	@Config.LangKey("gadgetron.config.machine.capacityRf")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int capacity = 100000;
	
	@Config.LangKey("gadgetron.config.machine.speed")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int speed = 60;
	
	@Config.LangKey("gadgetron.config.machine.consumeMbOperation")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int cosumeMbPerOperation = 10;

	@Config.LangKey("gadgetron.config.machine.capacityTank")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int capacityTank = 10000;
}
