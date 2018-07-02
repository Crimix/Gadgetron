package com.black_dog20.gadgetron.config.objects;

import net.minecraftforge.common.config.Config;

public class OreGenConfig {

	public OreGenConfig(boolean generate, int perChunk, int veinMax, int hightMin, int hightMax) {
		this.generate = generate;
		this.perChunk = perChunk;
		this.veinMax = veinMax;
		this.heightMin = hightMin;
		this.heightMax = hightMax;
	}
	
	@Config.LangKey("gadgetron.config.worldgen.generate")
	public boolean generate = true;

	@Config.LangKey("gadgetron.config.worldgen.perChunk")
	@Config.RangeInt(min = 1, max = 30)
	public int perChunk = 6;

	@Config.LangKey("gadgetron.config.worldgen.veinMax")
	@Config.RangeInt(min = 3, max = 20)
	public int veinMax = 4;

	@Config.LangKey("gadgetron.config.worldgen.heightMin")
	@Config.RangeInt(min = 1, max = 150)
	public int heightMin = 1;

	@Config.LangKey("gadgetron.config.worldgen.heightMax")
	@Config.RangeInt(min = 1, max = 150)
	public int heightMax = 20;
	
	@Config.LangKey("gadgetron.config.worldgen.blacklist")
	public int[] dimBlacklist = new int[]{-1,1};
	
	@Config.LangKey("gadgetron.config.worldgen.replaceBlock")
	public String replaceBlock = "minecraft:stone";
}
