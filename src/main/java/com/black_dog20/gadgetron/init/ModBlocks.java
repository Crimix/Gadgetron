package com.black_dog20.gadgetron.init;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.block.BlockBase;
import com.black_dog20.gadgetron.block.BlockModOre;
import com.black_dog20.gadgetron.block.BlockRaritaniumCrystal;
import com.black_dog20.gadgetron.block.machine.BlockBattery;
import com.black_dog20.gadgetron.block.machine.BlockBatteryT2;
import com.black_dog20.gadgetron.block.machine.BlockBatteryT3;
import com.black_dog20.gadgetron.block.machine.BlockCoalGenerator;
import com.black_dog20.gadgetron.block.machine.BlockCoalGeneratorT2;
import com.black_dog20.gadgetron.block.machine.BlockCoalGeneratorT3;
import com.black_dog20.gadgetron.block.machine.BlockEnergyGenerator;
import com.black_dog20.gadgetron.block.machine.BlockEnergyGeneratorT2;
import com.black_dog20.gadgetron.block.machine.BlockEnergyGeneratorT3;
import com.black_dog20.gadgetron.block.machine.BlockExtractor;
import com.black_dog20.gadgetron.block.machine.BlockExtractorT2;
import com.black_dog20.gadgetron.block.machine.BlockExtractorT3;
import com.black_dog20.gadgetron.block.machine.BlockFabricator;
import com.black_dog20.gadgetron.block.machine.BlockFabricatorT2;
import com.black_dog20.gadgetron.block.machine.BlockFabricatorT3;
import com.black_dog20.gadgetron.block.machine.BlockMachineBase;
import com.black_dog20.gadgetron.block.machine.BlockProcessor;
import com.black_dog20.gadgetron.block.machine.BlockProcessorT2;
import com.black_dog20.gadgetron.block.machine.BlockProcessorT3;
import com.black_dog20.gadgetron.block.machine.BlockSmelter;
import com.black_dog20.gadgetron.block.machine.BlockSmelterT2;
import com.black_dog20.gadgetron.block.machine.BlockSmelterT3;
import com.black_dog20.gadgetron.fluid.BlockFluidTrillium;
import com.black_dog20.gadgetron.reference.Reference;
import com.black_dog20.gadgetron.tile.TileEntityBattery;
import com.black_dog20.gadgetron.tile.TileEntityCoalGenerator;
import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;
import com.black_dog20.gadgetron.tile.TileEntityExtractor;
import com.black_dog20.gadgetron.tile.TileEntityFabricator;
import com.black_dog20.gadgetron.tile.TileEntityProcessor;
import com.black_dog20.gadgetron.tile.TileEntitySmelter;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModBlocks {
	
	public static BlockModOre RaritaniumCrystal = (BlockModOre) new BlockRaritaniumCrystal().setHardness(4.0F);
	public static BlockModOre AdamantineOre = (BlockModOre) new BlockModOre("adamantineOre",2).setHardness(3.0F);
	public static BlockModOre CarbonoxOre = (BlockModOre) new BlockModOre("carbonoxOre",2).setHardness(3.0F);
	public static BlockModOre TrilliumOre = (BlockModOre) new BlockModOre("trilliumOre",2).setHardness(3.0F);
	public static BlockModOre TitaniumOre = (BlockModOre) new BlockModOre("titaniumOre",2).setHardness(3.0F);
	
	public static BlockFluidTrillium blockFluidTrillium = new BlockFluidTrillium();
	
	public static BlockBase AdamantineBlock = (BlockBase) new BlockBase(Material.ROCK, "adamantineBlock").setHardness(1.0F);
	public static BlockBase CarbonoxBlock = (BlockBase) new BlockBase(Material.ROCK, "carbonoxBlock").setHardness(1.0F);
	public static BlockBase TrilliumBlock = (BlockBase) new BlockBase(Material.ROCK, "trilliumBlock").setHardness(1.0F);
	public static BlockBase TitaniumBlock = (BlockBase) new BlockBase(Material.ROCK, "titaniumBlock").setHardness(1.0F);
	public static BlockBase Machine_block = (BlockBase) new BlockBase(Material.IRON, "machine_block").setHardness(1.0F);
	
	public static BlockMachineBase Generator_T1 = new BlockEnergyGenerator("energy_generator_t1");
	public static BlockMachineBase Generator_T2 = new BlockEnergyGeneratorT2("energy_generator_t2");
	public static BlockMachineBase Generator_T3 = new BlockEnergyGeneratorT3("energy_generator_t3");
	public static BlockMachineBase CoalGenerator_T1 = new BlockCoalGenerator("coal_generator_t1");
	public static BlockMachineBase CoalGenerator_T2 = new BlockCoalGeneratorT2("coal_generator_t2");
	public static BlockMachineBase CoalGenerator_T3 = new BlockCoalGeneratorT3("coal_generator_t3");
	public static BlockMachineBase Battery_T1 = new BlockBattery("battery_t1");
	public static BlockMachineBase Battery_T2 = new BlockBatteryT2("battery_t2");
	public static BlockMachineBase Battery_T3 = new BlockBatteryT3("battery_t3");
	
	public static BlockMachineBase Fabricator_T1 = new BlockFabricator("fabricator_t1");
	public static BlockMachineBase Fabricator_T2 = new BlockFabricatorT2("fabricator_t2");
	public static BlockMachineBase Fabricator_T3 = new BlockFabricatorT3("fabricator_t3");
	public static BlockMachineBase Extractor_T1 = new BlockExtractor("extractor_t1");
	public static BlockMachineBase Extractor_T2 = new BlockExtractorT2("extractor_t2");
	public static BlockMachineBase Extractor_T3 = new BlockExtractorT3("extractor_t3");
	public static BlockMachineBase Processor_T1 = new BlockProcessor("processor_t1");
	public static BlockMachineBase Processor_T2 = new BlockProcessorT2("processor_t2");
	public static BlockMachineBase Processor_T3 = new BlockProcessorT3("processor_t3");
	public static BlockMachineBase Smelter_T1 = new BlockSmelter("smelter_t1");
	public static BlockMachineBase Smelter_T2 = new BlockSmelterT2("smelter_t2");
	public static BlockMachineBase Smelter_T3 = new BlockSmelterT3("smelter_t3");
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> evt) {
		IForgeRegistry<Block> r = evt.getRegistry();
		r.register(RaritaniumCrystal);
		r.register(AdamantineOre);
		r.register(CarbonoxOre);
		r.register(TrilliumOre);
		r.register(TitaniumOre);
		r.register(AdamantineBlock);
		r.register(CarbonoxBlock);
		r.register(TrilliumBlock);
		r.register(TitaniumBlock);
		r.register(Machine_block);
		
		r.register(blockFluidTrillium);
		
		r.register(Generator_T1);
		r.register(Generator_T2);
		r.register(Generator_T3);
		GameRegistry.registerTileEntity(TileEntityEnergyGenerator.class, Reference.MOD_ID+":"+"generator");
		r.register(CoalGenerator_T1);
		r.register(CoalGenerator_T2);
		r.register(CoalGenerator_T3);
		GameRegistry.registerTileEntity(TileEntityCoalGenerator.class, Reference.MOD_ID+":"+"coalGenerator");
		r.register(Battery_T1);
		r.register(Battery_T2);
		r.register(Battery_T3);
		GameRegistry.registerTileEntity(TileEntityBattery.class, Reference.MOD_ID+":"+"battery");
		
		r.register(Fabricator_T1);
		r.register(Fabricator_T2);
		r.register(Fabricator_T3);
		GameRegistry.registerTileEntity(TileEntityFabricator.class, Reference.MOD_ID+":"+"fabricator");
		r.register(Extractor_T1);
		r.register(Extractor_T2);
		r.register(Extractor_T3);
		GameRegistry.registerTileEntity(TileEntityExtractor.class, Reference.MOD_ID+":"+"extractor");
		r.register(Processor_T1);
		r.register(Processor_T2);
		r.register(Processor_T3);
		GameRegistry.registerTileEntity(TileEntityProcessor.class, Reference.MOD_ID+":"+"processor");
		r.register(Smelter_T1);
		r.register(Smelter_T2);
		r.register(Smelter_T3);
		GameRegistry.registerTileEntity(TileEntitySmelter.class, Reference.MOD_ID+":"+"smelter");
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();
		r.register(new ItemBlock(RaritaniumCrystal).setRegistryName(RaritaniumCrystal.getRegistryName()));
		r.register(new ItemBlock(AdamantineOre).setRegistryName(AdamantineOre.getRegistryName()));
		r.register(new ItemBlock(CarbonoxOre).setRegistryName(CarbonoxOre.getRegistryName()));
		r.register(new ItemBlock(TrilliumOre).setRegistryName(TrilliumOre.getRegistryName()));
		r.register(new ItemBlock(TitaniumOre).setRegistryName(TitaniumOre.getRegistryName()));
		
		r.register(new ItemBlock(blockFluidTrillium).setRegistryName(blockFluidTrillium.getRegistryName()));
		
		r.register(new ItemBlock(AdamantineBlock).setRegistryName(AdamantineBlock.getRegistryName()));
		r.register(new ItemBlock(CarbonoxBlock).setRegistryName(CarbonoxBlock.getRegistryName()));
		r.register(new ItemBlock(TrilliumBlock).setRegistryName(TrilliumBlock.getRegistryName()));
		r.register(new ItemBlock(TitaniumBlock).setRegistryName(TitaniumBlock.getRegistryName()));
		
		r.register(new ItemBlock(Machine_block).setRegistryName(Machine_block.getRegistryName()));
		
		
		r.register(new ItemBlock(Generator_T1).setRegistryName(Generator_T1.getRegistryName()));
		r.register(new ItemBlock(Generator_T2).setRegistryName(Generator_T2.getRegistryName()));
		r.register(new ItemBlock(Generator_T3).setRegistryName(Generator_T3.getRegistryName()));
		
		r.register(new ItemBlock(CoalGenerator_T1).setRegistryName(CoalGenerator_T1.getRegistryName()));
		r.register(new ItemBlock(CoalGenerator_T2).setRegistryName(CoalGenerator_T2.getRegistryName()));
		r.register(new ItemBlock(CoalGenerator_T3).setRegistryName(CoalGenerator_T3.getRegistryName()));
		
		r.register(new ItemBlock(Battery_T1).setRegistryName(Battery_T1.getRegistryName()));
		r.register(new ItemBlock(Battery_T2).setRegistryName(Battery_T2.getRegistryName()));
		r.register(new ItemBlock(Battery_T3).setRegistryName(Battery_T3.getRegistryName()));
		
		r.register(new ItemBlock(Fabricator_T1).setRegistryName(Fabricator_T1.getRegistryName()));
		r.register(new ItemBlock(Fabricator_T2).setRegistryName(Fabricator_T2.getRegistryName()));
		r.register(new ItemBlock(Fabricator_T3).setRegistryName(Fabricator_T3.getRegistryName()));
		
		r.register(new ItemBlock(Extractor_T1).setRegistryName(Extractor_T1.getRegistryName()));
		r.register(new ItemBlock(Extractor_T2).setRegistryName(Extractor_T2.getRegistryName()));
		r.register(new ItemBlock(Extractor_T3).setRegistryName(Extractor_T3.getRegistryName()));
		
		r.register(new ItemBlock(Processor_T1).setRegistryName(Processor_T1.getRegistryName()));
		r.register(new ItemBlock(Processor_T2).setRegistryName(Processor_T2.getRegistryName()));
		r.register(new ItemBlock(Processor_T3).setRegistryName(Processor_T3.getRegistryName()));
		
		r.register(new ItemBlock(Smelter_T1).setRegistryName(Smelter_T1.getRegistryName()));
		r.register(new ItemBlock(Smelter_T2).setRegistryName(Smelter_T2.getRegistryName()));
		r.register(new ItemBlock(Smelter_T3).setRegistryName(Smelter_T3.getRegistryName()));
		
		registerOreDict();
	}

	private static void registerOreDict() {
		OreDictionary.registerOre("crystalRaritanium", ModBlocks.RaritaniumCrystal);
		
		OreDictionary.registerOre("oreAdamantine", ModBlocks.AdamantineOre);
		OreDictionary.registerOre("oreCarbonox", ModBlocks.CarbonoxOre);
		OreDictionary.registerOre("oreTitanium", ModBlocks.TitaniumOre);
		OreDictionary.registerOre("oreTrillium", ModBlocks.TrilliumOre);
		
		OreDictionary.registerOre("blockAdamantine", ModBlocks.AdamantineBlock);
		OreDictionary.registerOre("blockCarbonox", ModBlocks.CarbonoxBlock);
		OreDictionary.registerOre("blockTitanium", ModBlocks.TrilliumBlock);
		OreDictionary.registerOre("blockTrillium", ModBlocks.TitaniumBlock);
		
		Gadgetron.logger.info("OreDictionary blocks register complete!");
	}
}
