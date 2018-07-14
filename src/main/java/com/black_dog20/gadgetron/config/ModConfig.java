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
		
		@Config.LangKey("gadgetron.config.machine.automationticks")
		@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
		public int automation_ticks = 20;

		@Config.LangKey("gadgetron.config.machine.automationmb")
		@Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
		public int automation_mb = 250;
		
		@Config.LangKey("gadgetron.config.machines.coalGenerator_t1")
		public GeneratorMachineConfig coalGenerator_t1 = new GeneratorMachineConfig(30, 100000);
		
		@Config.LangKey("gadgetron.config.machines.coalGenerator_t2")
		public GeneratorMachineConfig coalGenerator_t2 = new GeneratorMachineConfig(60, 500000);
		
		@Config.LangKey("gadgetron.config.machines.coalGenerator_t3")
		public GeneratorMachineConfig coalGenerator_t3 = new GeneratorMachineConfig(90, 1000000);
		
		@Config.LangKey("gadgetron.config.machines.fuelGenerator_t1")
		public GeneratorFluidMachineConfig fuelGenerator_t1 = new GeneratorFluidMachineConfig(300, 100000, 1, 10, 10000);
		
		@Config.LangKey("gadgetron.config.machines.fuelGenerator_t2")
		public GeneratorFluidMachineConfig fuelGenerator_t2 = new GeneratorFluidMachineConfig(600, 500000, 1.5, 10, 20000);
		
		@Config.LangKey("gadgetron.config.machines.fuelGenerator_t3")
		public GeneratorFluidMachineConfig fuelGenerator_t3 = new GeneratorFluidMachineConfig(1000, 1000000, 2, 10, 30000);
		
		@Config.LangKey("gadgetron.config.machines.battery_t1")
		public BatteryConfig battery_t1 = new BatteryConfig(1500, Integer.MAX_VALUE, 1000000);
		
		@Config.LangKey("gadgetron.config.machines.battery_t2")
		public BatteryConfig battery_t2 = new BatteryConfig(20000, Integer.MAX_VALUE, 20000000);
		
		@Config.LangKey("gadgetron.config.machines.battery_t3")
		public BatteryConfig battery_t3 = new BatteryConfig(200000, Integer.MAX_VALUE, 200000000);
		
		@Config.LangKey("gadgetron.config.machines.smelter_t1")
		public EnergyFluidGeneratorMachineConfig smelter_t1 = new EnergyFluidGeneratorMachineConfig(60, 100000, 1, 10000);
		
		@Config.LangKey("gadgetron.config.machines.smelter_t2")
		public EnergyFluidGeneratorMachineConfig smelter_t2 = new EnergyFluidGeneratorMachineConfig(90, 200000, 0.6, 20000);
		
		@Config.LangKey("gadgetron.config.machines.smelter_t3")
		public EnergyFluidGeneratorMachineConfig smelter_t3 = new EnergyFluidGeneratorMachineConfig(100, 300000, 0.4, 40000);
		
		@Config.LangKey("gadgetron.config.machines.fabricator_t1")
		public EnergyMachineConfig fabricator_t1 = new EnergyMachineConfig(60, 100000, 1);
		
		@Config.LangKey("gadgetron.config.machines.fabricator_t2")
		public EnergyMachineConfig fabricator_t2 = new EnergyMachineConfig(90, 200000, 0.6);
		
		@Config.LangKey("gadgetron.config.machines.fabricator_t3")
		public EnergyMachineConfig fabricator_t3 = new EnergyMachineConfig(100, 300000, 0.4);
		
		@Config.LangKey("gadgetron.config.machines.extractor_t1")
		public EnergyMachineConfig extractor_t1 = new EnergyMachineConfig(60, 100000, 1);
		
		@Config.LangKey("gadgetron.config.machines.extractor_t2")
		public EnergyMachineConfig extractor_t2 = new EnergyMachineConfig(90, 200000, 0.6);
		
		@Config.LangKey("gadgetron.config.machines.extractor_t3")
		public EnergyMachineConfig extractor_t3 = new EnergyMachineConfig(100, 300000, 0.4);
		
		@Config.LangKey("gadgetron.config.machines.processor_t1")
		public EnergyMachineConfig processor_t1 = new EnergyMachineConfig(60, 100000, 1);
		
		@Config.LangKey("gadgetron.config.machines.processor_t2")
		public EnergyMachineConfig processor_t2 = new EnergyMachineConfig(90, 200000, 0.6);
		
		@Config.LangKey("gadgetron.config.machines.processor_t3")
		public EnergyMachineConfig processor_t3 = new EnergyMachineConfig(100, 300000, 0.4);
	
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
