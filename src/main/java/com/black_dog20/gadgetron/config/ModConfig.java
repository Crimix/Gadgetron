package com.black_dog20.gadgetron.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.reference.Reference;

@Config(modid = Reference.MOD_ID)
@Config.LangKey("gadgetron.config.title")
public class ModConfig {
	
	@Config.LangKey("gadgetron.config.trilliumCausePoison")
	public static boolean doesTrilliumCausePoison = true;
	
	@Config.LangKey("gadgetron.config.trilliumWeaponsCausePoison")
	public static boolean doesTrilliumWeaponsCausePoison = true;
	
	@Config.LangKey("gadgetron.config.worldgen")
	public static Worldgen worldgen = new Worldgen();
	
	public static class Worldgen {
	
		@Config.LangKey("gadgetron.config.worldgen.raritanium")
		public OreGenConfig raritanium = new OreGenConfig(true, 10, 3, 1, 20);
		
		@Config.LangKey("gadgetron.config.worldgen.adamantine")
		public OreGenConfig adamantine = new OreGenConfig(true, 10, 8, 1, 60);
		
		@Config.LangKey("gadgetron.config.worldgen.carbonox")
		public OreGenConfig carbonox = new OreGenConfig(true, 8, 6, 1, 30);
		
		@Config.LangKey("gadgetron.config.worldgen.trillium")
		public OreGenConfig trillium = new OreGenConfig(true, 10, 6, 1, 40);
		
		@Config.LangKey("gadgetron.config.worldgen.titanium")
		public OreGenConfig titanium = new OreGenConfig(true, 14, 10, 20, 60);
		
		@Config.LangKey("gadgetron.config.worldgen.retrogen")
		public boolean retrogen = false;
		
		@Config.LangKey("gadgetron.config.worldgen.logretrogen")
		public boolean logretrogen = false;
	}
	
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
				Gadgetron.instance.CheckOreReplaceBlocksAreCorrect();
			}
		}
	}

	

}
