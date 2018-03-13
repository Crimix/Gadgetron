package com.black_dog20.gadgetron.init;

import com.black_dog20.gadgetron.integration.mekanism.MekanismIntegration;
import com.black_dog20.gadgetron.integration.te.ThermalExpansionIntegration;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Recipes {

	public static void init() {
		
		GameRegistry.addSmelting(ModItems.AdamantineDust, new ItemStack(ModItems.AdamantineIngot), 3F);
		GameRegistry.addSmelting(ModItems.CarbonoxDust, new ItemStack(ModItems.CarbonoxIngot), 3F);
		GameRegistry.addSmelting(ModItems.TitaniumDust, new ItemStack(ModItems.TitaniumIngot), 3F);
		GameRegistry.addSmelting(ModItems.TrilliumDust, new ItemStack(ModItems.TrilliumIngot), 3F);
		GameRegistry.addSmelting(ModBlocks.AdamantineOre, new ItemStack(ModItems.AdamantineIngot), 3F);
		GameRegistry.addSmelting(ModBlocks.CarbonoxOre, new ItemStack(ModItems.CarbonoxIngot), 3F);
		GameRegistry.addSmelting(ModBlocks.TitaniumOre, new ItemStack(ModItems.TitaniumIngot), 3F);
		GameRegistry.addSmelting(ModBlocks.TrilliumOre, new ItemStack(ModItems.TrilliumIngot), 3F);
		
		RegisterIntegrationRecipes(new ItemStack(ModBlocks.AdamantineOre), new ItemStack(ModItems.AdamantineDust,2));
		RegisterIntegrationRecipes(new ItemStack(ModBlocks.CarbonoxOre), new ItemStack(ModItems.CarbonoxDust,2));
		RegisterIntegrationRecipes(new ItemStack(ModBlocks.TitaniumOre), new ItemStack(ModItems.TitaniumDust,2));
		RegisterIntegrationRecipes(new ItemStack(ModBlocks.TrilliumOre), new ItemStack(ModItems.TrilliumDust,2));
		RegisterIntegrationRecipes(new ItemStack(ModItems.AdamantineIngot), new ItemStack(ModItems.AdamantineDust));
		RegisterIntegrationRecipes(new ItemStack(ModItems.CarbonoxIngot), new ItemStack(ModItems.CarbonoxDust));
		RegisterIntegrationRecipes(new ItemStack(ModItems.TitaniumIngot), new ItemStack(ModItems.TitaniumDust));
		RegisterIntegrationRecipes(new ItemStack(ModItems.TrilliumIngot), new ItemStack(ModItems.TrilliumDust));
	}
	
	
	private static void RegisterIntegrationRecipes(ItemStack in, ItemStack out){
		ThermalExpansionIntegration.addPulverizerRecipe(4000, in, out, null, 0);
		if(in.getItem() instanceof ItemBlock){
			MekanismIntegration.AddRecipeToEnrichmentChamber(in, out);
		}
		else{
			MekanismIntegration.AddRecipeToCrusher(in, out);
		}
		
	}

}
