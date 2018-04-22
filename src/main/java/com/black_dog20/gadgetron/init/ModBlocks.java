package com.black_dog20.gadgetron.init;

import com.black_dog20.gadgetron.block.BlockBase;
import com.black_dog20.gadgetron.block.BlockModOre;
import com.black_dog20.gadgetron.block.BlockRaritaniumCrystal;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModBlocks {

	public static BlockModOre RaritaniumCrystal = (BlockModOre) new BlockRaritaniumCrystal().setHardness(4.0F);
	public static BlockModOre AdamantineOre = (BlockModOre) new BlockModOre("adamantineOre",2).setHardness(3.0F);
	public static BlockModOre CarbonoxOre = (BlockModOre) new BlockModOre("carbonoxOre",2).setHardness(3.0F);
	public static BlockModOre TrilliumOre = (BlockModOre) new BlockModOre("trilliumOre",2).setHardness(3.0F);
	public static BlockModOre TitaniumOre = (BlockModOre) new BlockModOre("titaniumOre",2).setHardness(3.0F);
	
	public static BlockBase AdamantineBlock = (BlockBase) new BlockBase(Material.IRON, "adamantineBlock").setHardness(1.0F);
	public static BlockBase CarbonoxBlock = (BlockBase) new BlockBase(Material.IRON,"carbonoxBlock").setHardness(1.0F);
	public static BlockBase TrilliumBlock = (BlockBase) new BlockBase(Material.IRON,"trilliumBlock").setHardness(1.0F);
	public static BlockBase TitaniumBlock = (BlockBase) new BlockBase(Material.IRON,"titaniumBlock").setHardness(1.0F);
	
	
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
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();
		r.register(new ItemBlock(RaritaniumCrystal).setRegistryName(RaritaniumCrystal.getRegistryName()));
		r.register(new ItemBlock(AdamantineOre).setRegistryName(AdamantineOre.getRegistryName()));
		r.register(new ItemBlock(CarbonoxOre).setRegistryName(CarbonoxOre.getRegistryName()));
		r.register(new ItemBlock(TrilliumOre).setRegistryName(TrilliumOre.getRegistryName()));
		r.register(new ItemBlock(TitaniumOre).setRegistryName(TitaniumOre.getRegistryName()));
		
		r.register(new ItemBlock(AdamantineBlock).setRegistryName(AdamantineBlock.getRegistryName()));
		r.register(new ItemBlock(CarbonoxBlock).setRegistryName(CarbonoxBlock.getRegistryName()));
		r.register(new ItemBlock(TrilliumBlock).setRegistryName(TrilliumBlock.getRegistryName()));
		r.register(new ItemBlock(TitaniumBlock).setRegistryName(TitaniumBlock.getRegistryName()));
		
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
	}
}
