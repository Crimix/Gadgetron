package com.black_dog20.gadgetron.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import com.black_dog20.gadgetron.block.BlockModOre;
import com.black_dog20.gadgetron.reference.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModBlocks {

	public static BlockModOre RaritaniumOre = (BlockModOre) new BlockModOre("raritaniumOre",3).setHardness(4.0F);
	public static BlockModOre AdamantineOre = (BlockModOre) new BlockModOre("adamantineOre").setHardness(3.0F);
	public static BlockModOre CarbonoxOre = (BlockModOre) new BlockModOre("carbonoxOre").setHardness(3.0F);
	public static BlockModOre TrilliumOre = (BlockModOre) new BlockModOre("trilliumOre").setHardness(3.0F);
	public static BlockModOre TitaniumOre = (BlockModOre) new BlockModOre("titaniumOre").setHardness(3.0F);
	
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> evt) {
		IForgeRegistry<Block> r = evt.getRegistry();
		r.register(RaritaniumOre);
		r.register(AdamantineOre);
		r.register(CarbonoxOre);
		r.register(TrilliumOre);
		r.register(TitaniumOre);
		

	}
}
