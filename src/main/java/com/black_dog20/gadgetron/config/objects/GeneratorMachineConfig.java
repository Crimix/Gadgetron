package com.black_dog20.gadgetron.config.objects;

import net.minecraftforge.common.config.Config;

public class GeneratorMachineConfig {

	public GeneratorMachineConfig(int generateRfPertick, int capacity) {
		this.generateRfPerTick = generateRfPertick;
		this.capacity = capacity;
	}
	
	@Config.LangKey("gadgetron.config.machine.generateRf")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int generateRfPerTick = 30;

	@Config.LangKey("gadgetron.config.machine.capacityRf")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int capacity = 100000;
}
