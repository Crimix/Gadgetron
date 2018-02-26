package com.black_dog20.gadgetron.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.black_dog20.gadgetron.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Recipes {

	public static void init() {
		
		GameRegistry.addSmelting(ModItems.AdamantineDust, new ItemStack(ModItems.AdamantineIngot), 3F);
		GameRegistry.addSmelting(ModItems.CarbonoxDust, new ItemStack(ModItems.CarbonoxIngot), 3F);
		GameRegistry.addSmelting(ModItems.TitaniumDust, new ItemStack(ModItems.TitaniumIngot), 3F);
		GameRegistry.addSmelting(ModItems.TrilliumDust, new ItemStack(ModItems.TrilliumIngot), 3F);
		GameRegistry.addSmelting(ModBlocks.blockAdamantineOre, new ItemStack(ModItems.AdamantineIngot), 3F);
		GameRegistry.addSmelting(ModBlocks.blockCarbonoxOre, new ItemStack(ModItems.CarbonoxIngot), 3F);
		GameRegistry.addSmelting(ModBlocks.blockTitaniumOre, new ItemStack(ModItems.TitaniumIngot), 3F);
		GameRegistry.addSmelting(ModBlocks.blockTrilliumOre, new ItemStack(ModItems.TrilliumIngot), 3F);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.RaritaniumRod, new Object[]{"c","c", 'c', "crystalRaritanium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.RaritaniumAxe, new Object[]{"ii ","is "," s ", 'i', "crystalRaritanium", 's', ModItems.RaritaniumRod}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.RaritaniumHoe, new Object[]{"ii "," s "," s ", 'i', "crystalRaritanium", 's', ModItems.RaritaniumRod}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.RaritaniumPickaxe, new Object[]{"iii"," s "," s ", 'i', "crystalRaritanium", 's', ModItems.RaritaniumRod}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.RaritaniumShovel, new Object[]{" i "," s "," s ", 'i', "crystalRaritanium", 's', ModItems.RaritaniumRod}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.RaritaniumSword, new Object[]{" i "," i "," s ", 'i', "crystalRaritanium", 's', ModItems.RaritaniumRod}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.AdamantineAxe, new Object[]{"ii ","is "," s ", 'i', "ingotAdamantine", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.AdamantineHoe, new Object[]{"ii "," s "," s ", 'i', "ingotAdamantine", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.AdamantinePickaxe, new Object[]{"iii"," s "," s ", 'i', "ingotAdamantine", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.AdamantineShovel, new Object[]{" i "," s "," s ", 'i', "ingotAdamantine", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.AdamantineSword, new Object[]{" i "," i "," s ", 'i', "ingotAdamantine", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.CarbonoxAxe, new Object[]{"ii ","is "," s ", 'i', "ingotCarbonox", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.CarbonoxHoe, new Object[]{"ii "," s "," s ", 'i', "ingotCarbonox", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.CarbonoxPickaxe, new Object[]{"iii"," s "," s ", 'i', "ingotCarbonox", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.CarbonoxShovel, new Object[]{" i "," s "," s ", 'i', "ingotCarbonox", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.CarbonoxSword, new Object[]{" i "," i "," s ", 'i', "ingotCarbonox", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.TitaniumAxe, new Object[]{"ii ","is "," s ", 'i', "ingotTitanium", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.TitaniumHoe, new Object[]{"ii "," s "," s ", 'i', "ingotTitanium", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.TitaniumPickaxe, new Object[]{"iii"," s "," s ", 'i', "ingotTitanium", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.TitaniumShovel, new Object[]{" i "," s "," s ", 'i', "ingotTitanium", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.TitaniumSword, new Object[]{" i "," i "," s ", 'i', "ingotTitanium", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.TrilliumAxe, new Object[]{"ii ","is "," s ", 'i', "ingotTrillium", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.TrilliumHoe, new Object[]{"ii "," s "," s ", 'i', "ingotTrillium", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.TrilliumPickaxe, new Object[]{"iii"," s "," s ", 'i', "ingotTrillium", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.TrilliumShovel, new Object[]{" i "," s "," s ", 'i', "ingotTrillium", 's', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.TrilliumSword, new Object[]{" i "," i "," s ", 'i', "ingotTrillium", 's', "stickWood"}));
	}

}
