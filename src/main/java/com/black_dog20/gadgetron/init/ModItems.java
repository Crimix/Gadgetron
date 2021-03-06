package com.black_dog20.gadgetron.init;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.item.ItemBase;
import com.black_dog20.gadgetron.item.ItemBelt;
import com.black_dog20.gadgetron.item.tools.ItemAxeBase;
import com.black_dog20.gadgetron.item.tools.ItemHammer;
import com.black_dog20.gadgetron.item.tools.ItemHoeBase;
import com.black_dog20.gadgetron.item.tools.ItemPDA;
import com.black_dog20.gadgetron.item.tools.ItemPickaxeBase;
import com.black_dog20.gadgetron.item.tools.ItemShovelBase;
import com.black_dog20.gadgetron.item.tools.ItemSwordBase;
import com.black_dog20.gadgetron.item.tools.trillium.ItemTrilliumAxe;
import com.black_dog20.gadgetron.item.tools.trillium.ItemTrilliumHoe;
import com.black_dog20.gadgetron.item.tools.trillium.ItemTrilliumPickaxe;
import com.black_dog20.gadgetron.item.tools.trillium.ItemTrilliumShovel;
import com.black_dog20.gadgetron.item.tools.trillium.ItemTrilliumSword;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModItems {
	
	public static ToolMaterial Carbonox = EnumHelper.addToolMaterial("carbonox", 3, 3000, 10.0F, 6.0F, 22).setRepairItem(new ItemStack(ModItems.CarbonoxIngot));
	public static ToolMaterial Trillium = EnumHelper.addToolMaterial("trillium", 3, 2500, 8.0F, 4.0F, 14).setRepairItem(new ItemStack(ModItems.TrilliumIngot));
	public static ToolMaterial Raritanium = EnumHelper.addToolMaterial("raritanium", 3, 2000, 12.0F, 5.0F, 22).setRepairItem(new ItemStack(ModBlocks.RaritaniumCrystal));
	public static ToolMaterial Adamantine = EnumHelper.addToolMaterial("adamantine", 3, 2000, 8.0F, 4.0F, 15).setRepairItem(new ItemStack(ModItems.AdamantineIngot));
	public static ToolMaterial Titanium = EnumHelper.addToolMaterial("titanium", 3, 750, 6.0F, 3.0F, 14).setRepairItem(new ItemStack(ModItems.TitaniumIngot));
	

	public static ItemBase AdamantineIngot = new ItemBase("adamantineIngot");
	public static ItemBase CarbonoxIngot = new ItemBase("carbonoxIngot");
	public static ItemBase TrilliumIngot = new ItemBase("trilliumIngot");
	public static ItemBase TitaniumIngot = new ItemBase("titaniumIngot");
	public static ItemBase AdamantineDust = new ItemBase("adamantineDust");
	public static ItemBase CarbonoxDust = new ItemBase("carbonoxDust");
	public static ItemBase TrilliumDust = new ItemBase("trilliumDust");
	public static ItemBase TitaniumDust = new ItemBase("titaniumDust");
	public static ItemBase RaritaniumRod = new ItemBase("raritaniumRod");
	
	public static ItemBase RedGlowstoneDust = new ItemBase("redglowstone_dust");
	public static ItemBase Silicon = new ItemBase("silicon");
	public static ItemBase AdamantinePlate = new ItemBase("adamantine_plate");
	public static ItemBase CarbonoxPlate = new ItemBase("carbonox_plate");
	public static ItemBase TrilliumPlate = new ItemBase("trillium_plate");
	public static ItemBase TitaniumPlate = new ItemBase("titanium_plate");
	public static ItemBase RaritaniumShard = new ItemBase("raritanium_shard");
	public static ItemBase ConductingIngot = new ItemBase("conducting_ingot");
	public static ItemBase RaritaniumEnhancedPlate = new ItemBase("raritanium_enhanced_plate");
	public static ItemBase ConductingWire = new ItemBase("conducting_wire");
	public static ItemBase Circuit = new ItemBase("circuit");
	public static ItemHammer RaritaniumHammer = new ItemHammer("raritanium_hammer");
	
	public static ItemBase LeadPlatinumMixDust = new ItemBase("lead_platinum_mix_dust");
	public static ItemBase TinSilverMixDust = new ItemBase("tin_silver_mix_dust");
	
	public static ItemAxeBase RaritaniumAxe = new ItemAxeBase("raritaniumAxe", Raritanium, Raritanium.getAttackDamage(), -2.5F);
	public static ItemHoeBase RaritaniumHoe = new ItemHoeBase("raritaniumHoe", Raritanium);
	public static ItemPickaxeBase RaritaniumPickaxe = new ItemPickaxeBase("raritaniumPickaxe", Raritanium);
	public static ItemShovelBase RaritaniumShovel = new ItemShovelBase("raritaniumShovel", Raritanium);
	public static ItemSwordBase RaritaniumSword = new ItemSwordBase("raritaniumSword", Raritanium);
	public static ItemAxeBase AdamantineAxe = new ItemAxeBase("adamantineAxe", Adamantine, Adamantine.getAttackDamage(), -3.1F);
	public static ItemHoeBase AdamantineHoe = new ItemHoeBase("adamantineHoe", Adamantine);
	public static ItemPickaxeBase AdamantinePickaxe = new ItemPickaxeBase("adamantinePickaxe", Adamantine);
	public static ItemShovelBase AdamantineShovel = new ItemShovelBase("adamantineShovel", Adamantine);
	public static ItemSwordBase AdamantineSword = new ItemSwordBase("adamantineSword", Adamantine);
	public static ItemAxeBase CarbonoxAxe = new ItemAxeBase("carbonoxAxe", Carbonox, Carbonox.getAttackDamage(), -2.0F);
	public static ItemHoeBase CarbonoxHoe = new ItemHoeBase("carbonoxHoe", Carbonox);
	public static ItemPickaxeBase CarbonoxPickaxe = new ItemPickaxeBase("carbonoxPickaxe", Carbonox);
	public static ItemShovelBase CarbonoxShovel = new ItemShovelBase("carbonoxShovel", Carbonox);
	public static ItemSwordBase CarbonoxSword = new ItemSwordBase("carbonoxSword", Carbonox);
	public static ItemTrilliumAxe TrilliumAxe = new ItemTrilliumAxe("trilliumAxe", Trillium, Trillium.getAttackDamage(), -3.0F);
	public static ItemTrilliumHoe TrilliumHoe = new ItemTrilliumHoe("trilliumHoe", Trillium);
	public static ItemTrilliumPickaxe TrilliumPickaxe = new ItemTrilliumPickaxe("trilliumPickaxe", Trillium);
	public static ItemTrilliumShovel TrilliumShovel = new ItemTrilliumShovel("trilliumShovel", Trillium);
	public static ItemTrilliumSword TrilliumSword = new ItemTrilliumSword("trilliumSword", Trillium);
	public static ItemAxeBase TitaniumAxe = new ItemAxeBase("titaniumAxe", Titanium, Titanium.getAttackDamage(), -3.2F);
	public static ItemHoeBase TitaniumHoe = new ItemHoeBase("titaniumHoe", Titanium);
	public static ItemPickaxeBase TitaniumPickaxe = new ItemPickaxeBase("titaniumPickaxe", Titanium);
	public static ItemShovelBase TitaniumShovel = new ItemShovelBase("titaniumShovel", Titanium);
	public static ItemSwordBase TitaniumSword = new ItemSwordBase("titaniumSword", Titanium);
	
	public static ItemPDA pda = new ItemPDA();
	public static ItemBelt belt = new ItemBelt("belt");
	public static ItemBase blackLeather = new ItemBase("black_leather");
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();
		
		r.register(AdamantineIngot);
		r.register(CarbonoxIngot);
		r.register(TrilliumIngot);
		r.register(TitaniumIngot);
		r.register(AdamantineDust);
		r.register(CarbonoxDust);
		r.register(TrilliumDust);
		r.register(TitaniumDust);
		r.register(RaritaniumRod);
		
		r.register(RedGlowstoneDust);
		r.register(TinSilverMixDust);
		r.register(LeadPlatinumMixDust);
		r.register(Silicon);
		r.register(AdamantinePlate);
		r.register(CarbonoxPlate);
		r.register(TrilliumPlate);
		r.register(TitaniumPlate);
		r.register(RaritaniumShard);
		r.register(ConductingIngot);
		r.register(RaritaniumEnhancedPlate );
		r.register(ConductingWire);
		r.register(Circuit);
		r.register(RaritaniumHammer);
		
		r.register(RaritaniumAxe);
		r.register(RaritaniumHoe);
		r.register(RaritaniumPickaxe);
		r.register(RaritaniumShovel);
		r.register(RaritaniumSword);
		r.register(AdamantineAxe);
		r.register(AdamantineHoe);
		r.register(AdamantinePickaxe);
		r.register(AdamantineShovel);
		r.register(AdamantineSword);
		r.register(CarbonoxAxe);
		r.register(CarbonoxHoe);
		r.register(CarbonoxPickaxe);
		r.register(CarbonoxShovel);
		r.register(CarbonoxSword);
		r.register(TrilliumAxe);
		r.register(TrilliumHoe);
		r.register(TrilliumPickaxe);
		r.register(TrilliumShovel);
		r.register(TrilliumSword);
		r.register(TitaniumAxe);
		r.register(TitaniumHoe);
		r.register(TitaniumPickaxe);
		r.register(TitaniumShovel);
		r.register(TitaniumSword);
		
		r.register(pda);
		r.register(belt);
		r.register(blackLeather);
		
		registerOreDict();
	}
	
	private static void registerOreDict() {
		OreDictionary.registerOre("dustAdamantine", ModItems.AdamantineDust);
		OreDictionary.registerOre("dustCarbonox", ModItems.CarbonoxDust);
		OreDictionary.registerOre("dustTitanium", ModItems.TitaniumDust);
		OreDictionary.registerOre("dustTrillium", ModItems.TrilliumDust);
		OreDictionary.registerOre("dustRedstoneGlowstoneMix", ModItems.RedGlowstoneDust);
		OreDictionary.registerOre("dustLeadPlatinumMix", ModItems.LeadPlatinumMixDust);
		OreDictionary.registerOre("dustTinSilverMix", ModItems.TinSilverMixDust);
		
		OreDictionary.registerOre("ingotAdamantine", ModItems.AdamantineIngot);
		OreDictionary.registerOre("ingotCarbonox", ModItems.CarbonoxIngot);
		OreDictionary.registerOre("ingotTitanium", ModItems.TitaniumIngot);
		OreDictionary.registerOre("ingotTrillium", ModItems.TrilliumIngot);
		OreDictionary.registerOre("ingotConductingMetal", ModItems.ConductingIngot);
		OreDictionary.registerOre("wireConductingMetal", ModItems.ConductingWire);
		
		OreDictionary.registerOre("plateAdamantine", ModItems.AdamantinePlate);
		OreDictionary.registerOre("plateCarbonox", ModItems.CarbonoxPlate);
		OreDictionary.registerOre("plateTitanium", ModItems.TitaniumPlate);
		OreDictionary.registerOre("plateTrillium", ModItems.TrilliumPlate);
		
		OreDictionary.registerOre("shardRaritanium", ModItems.RaritaniumShard);
		OreDictionary.registerOre("silicon", ModItems.Silicon);
		OreDictionary.registerOre("reinforcedLeather", ModItems.blackLeather);
		
		Gadgetron.logger.info("OreDictionary items register complete!");
	}
}
