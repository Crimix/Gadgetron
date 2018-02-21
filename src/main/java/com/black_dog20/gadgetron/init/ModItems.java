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
	public static ItemBase AdamantineIngot = new ItemBase("ItemAdamantineIngot");
	public static ItemBase CarbonoxIngot = new ItemBase("ItemCarbonoxIngot");
	public static ItemBase TrilliumIngot = new ItemBase("ItemTrilliumIngot");
	public static ItemBase TitaniumIngot = new ItemBase("ItemTitaniumIngot");
	public static ItemBase AdamantineDust = new ItemBase("ItemAdamantineDust");
	public static ItemBase CarbonoxDust = new ItemBase("ItemCarbonoxDust");
	public static ItemBase TrilliumDust = new ItemBase("ItemTrilliumDust");
	public static ItemBase TitaniumDust = new ItemBase("ItemTitaniumDust");
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();
		
		r.register(RaritaniumCrystal);
		r.register(AdamantineIngot);
		r.register(CarbonoxIngot);
		r.register(TrilliumIngot);
		r.register(TitaniumIngot);
		r.register(AdamantineDust);
		r.register(CarbonoxDust);
		r.register(TrilliumDust);
		r.register(TitaniumDust);
	}
}
