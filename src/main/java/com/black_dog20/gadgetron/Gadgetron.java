package com.black_dog20.gadgetron;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Logger;

import com.black_dog20.gadgetron.handler.EventHandler;
import com.black_dog20.gadgetron.handler.PlayerEventHandler;
import com.black_dog20.gadgetron.init.ModBlocks;
import com.black_dog20.gadgetron.init.ModItems;
import com.black_dog20.gadgetron.init.Recipes;
import com.black_dog20.gadgetron.proxies.IProxy;
import com.black_dog20.gadgetron.reference.Reference;
import com.black_dog20.gadgetron.worldgen.OreGenerator;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MC_VERSIONS)
public class Gadgetron {

	@Mod.Instance(Reference.MOD_ID)
	public static Gadgetron instance = new Gadgetron();
	public static Logger logger;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy Proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		registerOreDict();
		
		logger.info("Pre Initialization Complete!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

		Recipes.init();
		GameRegistry.registerWorldGenerator(new OreGenerator(), 0);
		logger.info("Initialization Complete!");
}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		logger.info("Post Initialization Complete!");
	}
	
	
	private void registerOreDict(){
		OreDictionary.registerOre("dustAdamantine", ModItems.AdamantineDust);
		OreDictionary.registerOre("dustCarbonox", ModItems.CarbonoxDust);
		OreDictionary.registerOre("dustTitanium", ModItems.TitaniumDust);
		OreDictionary.registerOre("dustTrillium", ModItems.TrilliumDust);
		
		OreDictionary.registerOre("ingotAdamantine", ModItems.AdamantineIngot);
		OreDictionary.registerOre("ingotCarbonox", ModItems.CarbonoxIngot);
		OreDictionary.registerOre("ingotTitanium", ModItems.TitaniumIngot);
		OreDictionary.registerOre("ingotTrillium", ModItems.TrilliumIngot);
		
		OreDictionary.registerOre("crystalRaritanium", ModItems.RaritaniumCrystal);
		
		OreDictionary.registerOre("oreAdamantine", ModBlocks.blockAdamantineOre);
		OreDictionary.registerOre("oreCarbonox", ModBlocks.blockCarbonoxOre);
		OreDictionary.registerOre("oreRaritanium", ModBlocks.blockRaritaniumOre);
		OreDictionary.registerOre("oreTitanium", ModBlocks.blockTitaniumOre);
		OreDictionary.registerOre("oreTrillium", ModBlocks.blockTrilliumOre);
		logger.info("OreDictionary register complete!");
	}
}
