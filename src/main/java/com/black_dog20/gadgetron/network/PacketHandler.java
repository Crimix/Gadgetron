package com.black_dog20.gadgetron.network;

import com.black_dog20.gadgetron.network.message.MessageOpenGuiOnServer;
import com.black_dog20.gadgetron.network.message.MessageSyncMagnetCapability;
import com.black_dog20.gadgetron.network.message.MessageSyncMagnetCapabilityTracking;
import com.black_dog20.gadgetron.network.message.MessageUpdateAutoIO;
import com.black_dog20.gadgetron.network.message.MessageUpdateFaceConfig;
import com.black_dog20.gadgetron.network.message.MessageUpdateVisibleState;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


public class PacketHandler {

	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

	public static void init() {
		network.registerMessage(MessageOpenGuiOnServer.class, MessageOpenGuiOnServer.class, 0, Side.SERVER);
		network.registerMessage(MessageUpdateFaceConfig.class, MessageUpdateFaceConfig.class, 1, Side.SERVER);
		network.registerMessage(MessageUpdateAutoIO.class, MessageUpdateAutoIO.class, 2, Side.SERVER);
		network.registerMessage(MessageSyncMagnetCapability.class, MessageSyncMagnetCapability.class, 3, Side.CLIENT);
		network.registerMessage(MessageSyncMagnetCapabilityTracking.class, MessageSyncMagnetCapabilityTracking.class, 4, Side.CLIENT);
		network.registerMessage(MessageUpdateVisibleState.class, MessageUpdateVisibleState.class, 5, Side.SERVER);
	}

}
