package com.black_dog20.gadgetron.init;

import com.black_dog20.gadgetron.fluid.BlockFluidTrillium;
import com.black_dog20.gadgetron.fluid.FluidTrillium;

public class ModFluids {

	public static FluidTrillium fluidTrillium;
	public static BlockFluidTrillium blockFluidTrillium;

	public static void registerFluids() {
		fluidTrillium = new FluidTrillium();
		blockFluidTrillium = new BlockFluidTrillium();
	}
	
	public static void renderFluids() {
		blockFluidTrillium.render();
	}
}
