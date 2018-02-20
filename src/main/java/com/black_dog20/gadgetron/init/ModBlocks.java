package com.black_dog20.gadgetron.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.black_dog20.gadgetron.block.BlockModOre;
import com.black_dog20.gadgetron.reference.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModBlocks {

	public static BlockModOre blockRaritaniumOre = (BlockModOre) new BlockModOre("blockRaritaniumOre").setHardness(4.0F);
	public static BlockModOre blockAdamantineOre = (BlockModOre) new BlockModOre("blockAdamantineOre").setHardness(3.0F);
	public static BlockModOre blockCarbonoxOre = (BlockModOre) new BlockModOre("blockCarbonoxOre").setHardness(3.0F);
	public static BlockModOre blockTrilliumOre = (BlockModOre) new BlockModOre("blockTrilliumOre").setHardness(3.0F);
	public static BlockModOre blockTitaniumOre = (BlockModOre) new BlockModOre("blockTitaniumOre").setHardness(3.0F);
	
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> evt) {
		IForgeRegistry<Block> r = evt.getRegistry();
		r.register(blockRaritaniumOre);
		r.register(blockAdamantineOre);
		r.register(blockCarbonoxOre);
		r.register(blockTrilliumOre);
		r.register(blockTitaniumOre);
		

	}
}
