package com.black_dog20.gadgetron.config;

import net.minecraftforge.common.config.Config;

public class OreGenConfig {

	public OreGenConfig(boolean generate, int perChunk, int veinMin, int veinMax, int hightMin, int hightMax) {
		this.generate = generate;
		this.perChunk = perChunk;
		this.veinMin = veinMin;
		this.veinMax = veinMax;
		this.heightMin = hightMin;
		this.heightMax = hightMax;
	}
	
	@Config.LangKey("gadgetron.config.worldgen.generate")
	public boolean generate = true;

	@Config.LangKey("gadgetron.config.worldgen.perChunk")
	@Config.RangeInt(min = 1, max = 16)
	public int perChunk = 6;

	@Config.LangKey("gadgetron.config.worldgen.veinMin")
	@Config.RangeInt(min = 2, max = 10)
	public int veinMin = 2;

	@Config.LangKey("gadgetron.config.worldgen.veinMax")
	@Config.RangeInt(min = 2, max = 10)
	public int veinMax = 4;

	@Config.LangKey("gadgetron.config.worldgen.heightMin")
	@Config.RangeInt(min = 1, max = 150)
	public int heightMin = 1;

	@Config.LangKey("gadgetron.config.worldgen.heightMax")
	@Config.RangeInt(min = 1, max = 150)
	public int heightMax = 20;
}
