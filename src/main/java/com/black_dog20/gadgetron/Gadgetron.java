package com.black_dog20.gadgetron;

import org.apache.logging.log4j.Logger;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.handler.EventHandler;
import com.black_dog20.gadgetron.handler.GuiHandler;
import com.black_dog20.gadgetron.init.ModBlocks;
import com.black_dog20.gadgetron.init.ModFluids;
import com.black_dog20.gadgetron.init.ModItems;
import com.black_dog20.gadgetron.init.Recipes;
import com.black_dog20.gadgetron.proxies.IProxy;
import com.black_dog20.gadgetron.reference.Reference;
import com.black_dog20.gadgetron.worldgen.OreGenerator;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MC_VERSIONS, dependencies = Reference.DEPENDENCIES)
public class Gadgetron {

	@Mod.Instance(Reference.MOD_ID)
	public static Gadgetron instance = new Gadgetron();
	public static Logger logger;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy Proxy;
	
	static {
	    FluidRegistry.enableUniversalBucket();
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		registerOreDict();
		ModFluids.registerFluids();
		Proxy.preInit(event);
		logger.info("Pre Initialization Complete!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		Recipes.init();
		OreGenerator oreGen = new OreGenerator();
		GameRegistry.registerWorldGenerator(oreGen, 0);
		MinecraftForge.EVENT_BUS.register(oreGen);
		logger.info("Initialization Complete!");
}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		CheckOreReplaceBlocksAreCorrect();
		logger.info("Post Initialization Complete!");
	}
	
	
	public void CheckOreReplaceBlocksAreCorrect(){
		boolean result = true;
		if((Block.getBlockFromName(ModConfig.worldgen.raritanium.replaceBlock) == null)){
			result = false;
			logger.error("Replace block (" + ModConfig.worldgen.raritanium.replaceBlock + ") for raritanium ore does not exist");
		}
		if((Block.getBlockFromName(ModConfig.worldgen.adamantine.replaceBlock) == null)){
			result = false;
			logger.error("Replace block (" + ModConfig.worldgen.adamantine.replaceBlock + ") for adamantine ore does not exist");
		}
		if((Block.getBlockFromName(ModConfig.worldgen.carbonox.replaceBlock) == null)){
			result = false;
			logger.error("Replace block (" + ModConfig.worldgen.carbonox.replaceBlock + ") for carbonox ore does not exist");
		}
		if((Block.getBlockFromName(ModConfig.worldgen.trillium.replaceBlock) == null)){
			result = false;
			logger.error("Replace block (" + ModConfig.worldgen.trillium.replaceBlock + ") for trillium ore does not exist");
		}
		if((Block.getBlockFromName(ModConfig.worldgen.titanium.replaceBlock) == null)){
			result = false;
			logger.error("Replace block (" + ModConfig.worldgen.titanium.replaceBlock + ") for titanium ore does not exist");
		}
		if(!result){
			logger.error("One or more replace blocks are invaild, worldgen will not be done for Gadgetron");
		}
		OreGenerator.correctReplaceBlocks = result;
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
		
		OreDictionary.registerOre("crystalRaritanium", ModBlocks.RaritaniumCrystal);
		
		OreDictionary.registerOre("oreAdamantine", ModBlocks.AdamantineOre);
		OreDictionary.registerOre("oreCarbonox", ModBlocks.CarbonoxOre);
		OreDictionary.registerOre("oreTitanium", ModBlocks.TitaniumOre);
		OreDictionary.registerOre("oreTrillium", ModBlocks.TrilliumOre);
		logger.info("OreDictionary register complete!");
	}
}
