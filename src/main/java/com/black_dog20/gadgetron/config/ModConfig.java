package com.black_dog20.gadgetron.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.black_dog20.gadgetron.reference.Reference;

@Config(modid = Reference.MOD_ID)
@Config.LangKey("gadgetron.config.title")
public class ModConfig {
	
	@Config.LangKey("gadgetron.config.RaritaniumPerChunk")
	public static int RaritaniumPerChunk = 2;
	
	@Config.LangKey("gadgetron.config.AdamantinePerChunk")
	public static int AdamantinePerChunk = 4;
	
	@Config.LangKey("gadgetron.config.CarbonoxPerChunk")
	public static int CarbonoxPerChunk = 3;
	
	@Config.LangKey("gadgetron.config.TrilliumPerChunk")
	public static int TrilliumPerChunk = 5;
	
	@Config.LangKey("gadgetron.config.TitaniumPerChunk")
	public static int TitaniumPerChunk = 8;
	
	
	@Config.LangKey("gadgetron.config.TrilliumCausePoison")
	public static boolean doesTrilliumCausePoison = true;
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	private static class EventHandler {

		/**
		 * Inject the new values and save to the config file when the config has been changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MOD_ID)) {
				ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}

	

}
