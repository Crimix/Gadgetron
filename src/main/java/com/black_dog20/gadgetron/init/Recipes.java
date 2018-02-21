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
		
		//GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.AdamantineIngot, new Object[] { "c ", " s", 'c', ModItems.soulcystal, 's', "stickWood" }));
		//GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.soulgem.GetItemForCraftingResult(), ModItems.soulcystal,ModItems.soulManipulator));
		
	}

}
