package com.black_dog20.gadgetron.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import com.black_dog20.gadgetron.item.ItemBase;
import com.black_dog20.gadgetron.reference.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModItems {
	
	public static ItemBase RaritaniumCrystal = new ItemBase("ItemRaritaniumCrystal");
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();
		
		r.register(RaritaniumCrystal);
	}
}
