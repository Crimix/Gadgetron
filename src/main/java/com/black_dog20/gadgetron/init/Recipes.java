package com.black_dog20.gadgetron.init;

import com.black_dog20.gadgetron.integration.mekanism.MekanismIntegration;
import com.black_dog20.gadgetron.integration.te.ThermalExpansionIntegration;
import com.black_dog20.gadgetron.recipehandler.ExtractorRecipes;
import com.black_dog20.gadgetron.recipehandler.ProcessorRecipes;
import com.black_dog20.gadgetron.recipehandler.SmelterRecipes;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
		
		RegisterSmelterRecipes();
		RegisterExtractorRecipes();
		RegisterProcessorRecipes();
	}
	
	
	private static void RegisterSmelterRecipes() {
		SmelterRecipes.instance().addRecipeForBlock(ModBlocks.TrilliumOre, 500, new FluidStack(ModFluids.fluidTrillium, 1000));
		SmelterRecipes.instance().addRecipe(new ItemStack(ModItems.TrilliumIngot), 250, new FluidStack(ModFluids.fluidTrillium, 500));
		SmelterRecipes.instance().addRecipeForBlock(ModBlocks.TrilliumBlock, 1000, new FluidStack(ModFluids.fluidTrillium, 4500));
		SmelterRecipes.instance().addRecipeForBlock(Blocks.COBBLESTONE, 100, new FluidStack(FluidRegistry.LAVA, 50));
		SmelterRecipes.instance().addRecipeForBlock(Blocks.STONE, 100, new FluidStack(FluidRegistry.LAVA, 100));
	}
	
	private static void RegisterExtractorRecipes() {
		ExtractorRecipes.instance().addRecipe("stone", 200, new ItemStack(Blocks.COBBLESTONE));
		ExtractorRecipes.instance().addRecipe("cobblestone", 200, new ItemStack(Blocks.GRAVEL));
		ExtractorRecipes.instance().addRecipe("gravel", 200, new ItemStack(Blocks.SAND));
		
		ExtractorRecipes.instance().addRecipe("oreAdamantine", 200, "dustAdamantine", 3);
		ExtractorRecipes.instance().addRecipe("oreCarbonox", 200, "dustCarbonox", 3);
		ExtractorRecipes.instance().addRecipe("oreTitanium", 200, "dustTitanium", 2);
		ExtractorRecipes.instance().addRecipe("oreTrillium", 200, "dustTrillium", 3);
		ExtractorRecipes.instance().addRecipe("oreIron", 200, "dustIron", 2);
		ExtractorRecipes.instance().addRecipe("oreGold", 200, "dustGold", 2);
		ExtractorRecipes.instance().addRecipe("oreCopper", 200, "dustCopper", 2);
		ExtractorRecipes.instance().addRecipe("oreLead", 200, "dustLead", 2);
		ExtractorRecipes.instance().addRecipe("oreTin", 200, "dustTin", 2);
		ExtractorRecipes.instance().addRecipe("oreSilver", 200, "dustSilver", 2);
		ExtractorRecipes.instance().addRecipe("oreYellorite", 200, "dustUranium", 2);
		ExtractorRecipes.instance().addRecipe("oreYellorium", 200, "dustUranium", 2);
		ExtractorRecipes.instance().addRecipe("oreAluminium", 200, "dustAluminium", 2);
		ExtractorRecipes.instance().addRecipe("oreAluminum", 200, "dustAluminum", 2);
		ExtractorRecipes.instance().addRecipe("oreZinc", 200, "dustZinc", 2);
		ExtractorRecipes.instance().addRecipe("orePlatinum", 200, "dustPlatinum", 2);
		ExtractorRecipes.instance().addRecipe("oreShiny", 200, "dustShiny", 2);
		ExtractorRecipes.instance().addRecipe("oreOsmium", 200, "dustOsmium", 2);
		ExtractorRecipes.instance().addRecipe("oreIridium", 200, "dustIridium", 2);
		ExtractorRecipes.instance().addRecipe("oreSulphur", 200, "dustSulphur", 2);
		ExtractorRecipes.instance().addRecipe("oreSulfur", 200, "dustSulfur", 2);
		ExtractorRecipes.instance().addRecipe("oreSaltpeter", 200, "dustSaltpeter", 2);
		ExtractorRecipes.instance().addRecipe("oreNickel", 200, "dustNickel", 2);
		ExtractorRecipes.instance().addRecipe("oreManaInfused", 200, "dustManaInfused", 2);
		
		ExtractorRecipes.instance().addRecipe("ingotAdamantine", 200, "dustAdamantine", 1);
		ExtractorRecipes.instance().addRecipe("ingotCarbonox", 200, "dustCarbonox", 1);
		ExtractorRecipes.instance().addRecipe("ingotTitanium", 200, "dustTitanium", 1);
		ExtractorRecipes.instance().addRecipe("ingotTrillium", 200, "dustTrillium", 1);
		ExtractorRecipes.instance().addRecipe("ingotIron", 200, "dustIron", 1);
		ExtractorRecipes.instance().addRecipe("ingotGold", 200, "dustGold", 1);
		ExtractorRecipes.instance().addRecipe("ingotCopper", 200, "dustCopper", 1);
		ExtractorRecipes.instance().addRecipe("ingotLead", 200, "dustLead", 1);
		ExtractorRecipes.instance().addRecipe("ingotTin", 200, "dustTin", 1);
		ExtractorRecipes.instance().addRecipe("ingotSilver", 200, "dustSilver", 1);
		ExtractorRecipes.instance().addRecipe("ingotUranium", 200, "dustUranium", 1);
		ExtractorRecipes.instance().addRecipe("ingotAluminium", 200, "dustAluminium", 1);
		ExtractorRecipes.instance().addRecipe("ingotAluminum", 200, "dustAluminum", 1);
		ExtractorRecipes.instance().addRecipe("ingotZinc", 200, "dustZinc", 1);
		ExtractorRecipes.instance().addRecipe("ingotPlatinum", 200, "dustPlatinum", 1);
		ExtractorRecipes.instance().addRecipe("ingotShiny", 200, "dustShiny", 1);
		ExtractorRecipes.instance().addRecipe("ingotOsmium", 200, "dustOsmium", 1);
		ExtractorRecipes.instance().addRecipe("ingotIridium", 200, "dustIridium", 1);
		ExtractorRecipes.instance().addRecipe("ingotNickel", 200, "dustNickel", 1);
		ExtractorRecipes.instance().addRecipe("ingotManaInfused", 200, "dustManaInfused", 1);
	}
	
	private static void RegisterProcessorRecipes() {
		ProcessorRecipes.instance().addRecipe("cobblestone", 200, new ItemStack(Blocks.STONE));
		ProcessorRecipes.instance().addRecipe("sand", 200, new ItemStack(Blocks.GLASS));
		ProcessorRecipes.instance().addRecipe("gravel", 200, new ItemStack(Items.FLINT,2));
		
		ProcessorRecipes.instance().addRecipe("dustAdamantine", 200, "ingotAdamantine", 1);
		ProcessorRecipes.instance().addRecipe("dustCarbonox", 200, "ingotCarbonox", 1);
		ProcessorRecipes.instance().addRecipe("dustTitanium", 200, "ingotTitanium", 1);
		ProcessorRecipes.instance().addRecipe("dustTrillium", 200, "ingotTrillium", 1);
		ProcessorRecipes.instance().addRecipe("dustIron", 200, "ingotIron", 1);
		ProcessorRecipes.instance().addRecipe("dustGold", 200, "ingotGold", 1);
		ProcessorRecipes.instance().addRecipe("dustCopper", 200, "ingotCopper", 1);
		ProcessorRecipes.instance().addRecipe("dustLead", 200, "ingotLead", 1);
		ProcessorRecipes.instance().addRecipe("dustTin", 200, "ingotTin", 1);
		ProcessorRecipes.instance().addRecipe("dustSilver", 200, "ingotSilver", 1);
		ProcessorRecipes.instance().addRecipe("dustUranium", 200, "ingotUranium", 1);
		ProcessorRecipes.instance().addRecipe("dustAluminium", 200, "ingotAluminium", 1);
		ProcessorRecipes.instance().addRecipe("dustAluminum", 200, "ingotAluminum", 1);
		ProcessorRecipes.instance().addRecipe("dustZinc", 200, "ingotZinc", 1);
		ProcessorRecipes.instance().addRecipe("dustPlatinum", 200, "ingotPlatinum", 1);
		ProcessorRecipes.instance().addRecipe("dustShiny", 200, "ingotShiny", 1);
		ProcessorRecipes.instance().addRecipe("dustOsmium", 200, "ingotOsmium", 1);
		ProcessorRecipes.instance().addRecipe("dustIridium", 200, "ingotIridium", 1);
		ProcessorRecipes.instance().addRecipe("dustNickel", 200, "ingotNickel", 1);
		ProcessorRecipes.instance().addRecipe("dustManaInfused", 200, "ingotManaInfused", 1);
		
		ExtractorRecipes.instance().addRecipe("ingotAdamantine", 200, "plateAdamantine", 1);
		ExtractorRecipes.instance().addRecipe("ingotCarbonox", 200, "plateCarbonox", 1);
		ExtractorRecipes.instance().addRecipe("ingotTitanium", 200, "plateTitanium", 1);
		ExtractorRecipes.instance().addRecipe("ingotTrillium", 200, "plateTrillium", 1);
		ExtractorRecipes.instance().addRecipe("ingotIron", 200, "plateIron", 1);
		ExtractorRecipes.instance().addRecipe("ingotGold", 200, "plateGold", 1);
		ExtractorRecipes.instance().addRecipe("ingotCopper", 200, "plateCopper", 1);
		ExtractorRecipes.instance().addRecipe("ingotLead", 200, "plateLead", 1);
		ExtractorRecipes.instance().addRecipe("ingotTin", 200, "plateTin", 1);
		ExtractorRecipes.instance().addRecipe("ingotSilver", 200, "plateSilver", 1);
		ExtractorRecipes.instance().addRecipe("ingotUranium", 200, "plateUranium", 1);
		ExtractorRecipes.instance().addRecipe("ingotAluminium", 200, "plateAluminium", 1);
		ExtractorRecipes.instance().addRecipe("ingotAluminum", 200, "plateAluminum", 1);
		ExtractorRecipes.instance().addRecipe("ingotZinc", 200, "plateZinc", 1);
		ExtractorRecipes.instance().addRecipe("ingotPlatinum", 200, "platePlatinum", 1);
		ExtractorRecipes.instance().addRecipe("ingotShiny", 200, "plateShiny", 1);
		ExtractorRecipes.instance().addRecipe("ingotOsmium", 200, "plateOsmium", 1);
		ExtractorRecipes.instance().addRecipe("ingotIridium", 200, "plateIridium", 1);
		ExtractorRecipes.instance().addRecipe("ingotNickel", 200, "plateNickel", 1);
		ExtractorRecipes.instance().addRecipe("ingotManaInfused", 200, "plateManaInfused", 1);
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
