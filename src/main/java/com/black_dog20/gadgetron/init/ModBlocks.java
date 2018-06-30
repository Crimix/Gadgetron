package com.black_dog20.gadgetron.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import com.black_dog20.gadgetron.block.BlockBase;
import com.black_dog20.gadgetron.block.BlockModOre;
import com.black_dog20.gadgetron.block.BlockRaritaniumCrystal;
import com.black_dog20.gadgetron.block.machine.BlockEnergyGenerator;
import com.black_dog20.gadgetron.block.machine.BlockMachineBase;
import com.black_dog20.gadgetron.reference.Reference;
import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModBlocks {

	public static BlockModOre RaritaniumCrystal = (BlockModOre) new BlockRaritaniumCrystal().setHardness(4.0F);
	public static BlockModOre AdamantineOre = (BlockModOre) new BlockModOre("adamantineOre",2).setHardness(3.0F);
	public static BlockModOre CarbonoxOre = (BlockModOre) new BlockModOre("carbonoxOre",2).setHardness(3.0F);
	public static BlockModOre TrilliumOre = (BlockModOre) new BlockModOre("trilliumOre",2).setHardness(3.0F);
	public static BlockModOre TitaniumOre = (BlockModOre) new BlockModOre("titaniumOre",2).setHardness(3.0F);
	
	public static BlockBase AdamantineBlock = new BlockBase(Material.ROCK, "adamantineBlock");
	public static BlockBase CarbonoxBlock = new BlockBase(Material.ROCK, "carbonoxBlock");
	public static BlockBase TrilliumBlock = new BlockBase(Material.ROCK, "trilliumBlock");
	public static BlockBase TitaniumBlock = new BlockBase(Material.ROCK, "titaniumBlock");
	
	public static BlockMachineBase Generator = new BlockEnergyGenerator();
	
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
		
		r.register(Generator);
		GameRegistry.registerTileEntity(TileEntityEnergyGenerator.class, Reference.MOD_ID+":"+"generator");
	}
}
