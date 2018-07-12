package com.black_dog20.gadgetron.init;

import com.black_dog20.gadgetron.block.BlockBase;
import com.black_dog20.gadgetron.block.BlockModOre;
import com.black_dog20.gadgetron.block.BlockRaritaniumCrystal;
import com.black_dog20.gadgetron.block.machine.BlockBattery;
import com.black_dog20.gadgetron.block.machine.BlockCoalGenerator;
import com.black_dog20.gadgetron.block.machine.BlockEnergyGenerator;
import com.black_dog20.gadgetron.block.machine.BlockExtractor;
import com.black_dog20.gadgetron.block.machine.BlockFabricator;
import com.black_dog20.gadgetron.block.machine.BlockMachineBase;
import com.black_dog20.gadgetron.block.machine.BlockProcessor;
import com.black_dog20.gadgetron.block.machine.BlockSmelter;
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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModBlocks {
	
	public static BlockModOre RaritaniumCrystal = (BlockModOre) new BlockRaritaniumCrystal().setHardness(4.0F);
	public static BlockModOre AdamantineOre = (BlockModOre) new BlockModOre("adamantineOre",2).setHardness(3.0F);
	public static BlockModOre CarbonoxOre = (BlockModOre) new BlockModOre("carbonoxOre",2).setHardness(3.0F);
	public static BlockModOre TrilliumOre = (BlockModOre) new BlockModOre("trilliumOre",2).setHardness(3.0F);
	public static BlockModOre TitaniumOre = (BlockModOre) new BlockModOre("titaniumOre",2).setHardness(3.0F);
	
	public static BlockBase AdamantineBlock = (BlockBase) new BlockBase(Material.ROCK, "adamantineBlock").setHardness(1.0F);
	public static BlockBase CarbonoxBlock = (BlockBase) new BlockBase(Material.ROCK, "carbonoxBlock").setHardness(1.0F);
	public static BlockBase TrilliumBlock = (BlockBase) new BlockBase(Material.ROCK, "trilliumBlock").setHardness(1.0F);
	public static BlockBase TitaniumBlock = (BlockBase) new BlockBase(Material.ROCK, "titaniumBlock").setHardness(1.0F);
	public static BlockBase Machine_block = (BlockBase) new BlockBase(Material.IRON, "machine_block").setHardness(1.0F);
	
	public static BlockMachineBase Generator = new BlockEnergyGenerator("energy_generator_t1");
	public static BlockMachineBase CoalGenerator = new BlockCoalGenerator("coal_generator_t1");
	public static BlockMachineBase Battery_T1 = new BlockBattery("battery_t1");
	
	public static BlockMachineBase Fabricator = new BlockFabricator("fabricator_t1");
	public static BlockMachineBase Extractor = new BlockExtractor("extractor_t1");
	public static BlockMachineBase Processor = new BlockProcessor("processor_t1");
	public static BlockMachineBase Smelter = new BlockSmelter("smelter_t1");
	
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
		
		r.register(Generator);
		GameRegistry.registerTileEntity(TileEntityEnergyGenerator.class, Reference.MOD_ID+":"+"generator");
		r.register(CoalGenerator);
		GameRegistry.registerTileEntity(TileEntityCoalGenerator.class, Reference.MOD_ID+":"+"coalGenerator");
		r.register(Battery_T1);
		GameRegistry.registerTileEntity(TileEntityBattery.class, Reference.MOD_ID+":"+"batteryT1");
		
		r.register(Fabricator);
		GameRegistry.registerTileEntity(TileEntityFabricator.class, Reference.MOD_ID+":"+"fabricator");
		r.register(Extractor);
		GameRegistry.registerTileEntity(TileEntityExtractor.class, Reference.MOD_ID+":"+"extractor");
		r.register(Processor);
		GameRegistry.registerTileEntity(TileEntityProcessor.class, Reference.MOD_ID+":"+"processor");
		r.register(Smelter);
		GameRegistry.registerTileEntity(TileEntitySmelter.class, Reference.MOD_ID+":"+"smelter");
	}
}
