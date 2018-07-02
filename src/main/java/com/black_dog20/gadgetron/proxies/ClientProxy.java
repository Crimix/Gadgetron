package com.black_dog20.gadgetron.proxies;

import com.black_dog20.gadgetron.init.ModFluids;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ModFluids.renderFluids();
	}

}
