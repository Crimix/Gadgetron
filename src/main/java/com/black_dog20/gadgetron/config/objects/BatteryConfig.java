package com.black_dog20.gadgetron.config.objects;

import net.minecraftforge.common.config.Config;

public class BatteryConfig {

	public BatteryConfig(int rfOutputPerTick, int rfInputPerTick, int capacityRF) {
		this.rfOutputPerTick = rfOutputPerTick;
		this.capacity = capacityRF;
		this.rfInputPerTick = rfInputPerTick;
	}
	
	@Config.LangKey("gadgetron.config.machine.outputRF")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int rfOutputPerTick = 30;

	@Config.LangKey("gadgetron.config.machine.capacityRf")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int capacity = 100000;
	
	@Config.LangKey("gadgetron.config.machine.inputRF")
	@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
	public int rfInputPerTick = 60;
}
