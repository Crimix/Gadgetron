package com.black_dog20.gadgetron.network;

import com.black_dog20.gadgetron.network.message.MessageOpenIOConfig;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


public class PacketHandler {

	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

	public static void init() {
		network.registerMessage(MessageOpenIOConfig.class, MessageOpenIOConfig.class, 0, Side.SERVER);
	}

}
