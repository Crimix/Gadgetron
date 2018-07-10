package com.black_dog20.gadgetron.config;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.config.objects.BatteryConfig;
import com.black_dog20.gadgetron.config.objects.EnergyFluidGeneratorMachineConfig;
import com.black_dog20.gadgetron.config.objects.EnergyMachineConfig;
import com.black_dog20.gadgetron.config.objects.GeneratorFluidMachineConfig;
import com.black_dog20.gadgetron.config.objects.GeneratorMachineConfig;
import com.black_dog20.gadgetron.config.objects.OreGenConfig;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID)
@Config.LangKey("gadgetron.config.title")
public class ModConfig {
	
	@Config.LangKey("gadgetron.config.trilliumCausePoison")
	public static boolean doesTrilliumCausePoison = true;
	
	@Config.LangKey("gadgetron.config.trilliumWeaponsCausePoison")
	public static boolean doesTrilliumWeaponsCausePoison = true;
	
	@Config.LangKey("gadgetron.config.worldgen")
	public static Worldgen worldgen = new Worldgen();
	
	@Config.LangKey("gadgetron.config.machines")
	public static Machines machines = new Machines();
	
	public static class Worldgen {
	
		@Config.LangKey("gadgetron.config.worldgen.raritanium")
		public OreGenConfig raritanium = new OreGenConfig(true, 5, 3, 1, 20);
		
		@Config.LangKey("gadgetron.config.worldgen.adamantine")
		public OreGenConfig adamantine = new OreGenConfig(true, 5, 4, 1, 60);
		
		@Config.LangKey("gadgetron.config.worldgen.carbonox")
		public OreGenConfig carbonox = new OreGenConfig(true, 5, 4, 1, 30);
		
		@Config.LangKey("gadgetron.config.worldgen.trillium")
		public OreGenConfig trillium = new OreGenConfig(true, 5, 5, 1, 40);
		
		@Config.LangKey("gadgetron.config.worldgen.titanium")
		public OreGenConfig titanium = new OreGenConfig(true, 7, 6, 20, 60);
		
		@Config.LangKey("gadgetron.config.worldgen.retrogen")
		public boolean retrogen = false;
		
		@Config.LangKey("gadgetron.config.worldgen.logretrogen")
		public boolean logretrogen = false;
	}
	
	public static class Machines {
		
		@Config.LangKey("gadgetron.config.machines.coalGenerator")
		public GeneratorMachineConfig coalGenerator = new GeneratorMachineConfig(30, 100000);
		
		@Config.LangKey("gadgetron.config.machines.fuelGenerator")
		public GeneratorFluidMachineConfig fuelGenerator = new GeneratorFluidMachineConfig(100, 100000, 60, 10, 10000);
		
		@Config.LangKey("gadgetron.config.machines.battery_t1")
		public BatteryConfig battery_t1 = new BatteryConfig(20000, 1500, 1000000);
		
		@Config.LangKey("gadgetron.config.machines.smelter")
		public EnergyFluidGeneratorMachineConfig smelter = new EnergyFluidGeneratorMachineConfig(1000, 100000, 60, 1000, 10000);
		
		@Config.LangKey("gadgetron.config.machines.fabricator")
		public EnergyMachineConfig fabricator = new EnergyMachineConfig(100,100000,60);
		
		@Config.LangKey("gadgetron.config.machines.extractor")
		public EnergyMachineConfig extractor = new EnergyMachineConfig(100,100000,60);
		
		@Config.LangKey("gadgetron.config.machines.processor")
		public EnergyMachineConfig processor = new EnergyMachineConfig(100,100000,60);
	
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
