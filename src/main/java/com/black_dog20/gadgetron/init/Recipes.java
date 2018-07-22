package com.black_dog20.gadgetron.init;

import com.black_dog20.gadgetron.integration.enderio.EnderIOIntegration;
import com.black_dog20.gadgetron.integration.mekanism.MekanismIntegration;
import com.black_dog20.gadgetron.integration.te.ThermalExpansionIntegration;
import com.black_dog20.gadgetron.recipehandler.ExtractorRecipes;
import com.black_dog20.gadgetron.recipehandler.FabricatorRecipes;
import com.black_dog20.gadgetron.recipehandler.ProcessorRecipes;
import com.black_dog20.gadgetron.recipehandler.SmelterRecipes;
import com.black_dog20.gadgetron.reference.Reference;
import com.black_dog20.gadgetron.utility.RecipeToJSON;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
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
		

		//generateJSON();
		
		RegisterSmelterRecipes();
		RegisterExtractorRecipes();
		RegisterProcessorRecipes();
		RegisterFabricatorRecipes();
	}
	
	@SuppressWarnings("unused")
	private static void generateJSON() {
		Boolean devEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		if(!devEnv) return;
		
		RecipeToJSON toJson = new RecipeToJSON(Reference.MOD_NAME, true);
	
		toJson.addShapedRecipe(ModItems.RaritaniumRod, new Object[]{"c","c", 'c', "crystalRaritanium"});
		toJson.addShapedRecipe(ModItems.RaritaniumAxe, new Object[]{"ii ","is "," s ", 'i', "crystalRaritanium", 's', ModItems.RaritaniumRod});
		toJson.addShapedRecipe(ModItems.RaritaniumHoe, new Object[]{"ii "," s "," s ", 'i', "crystalRaritanium", 's', ModItems.RaritaniumRod});
		toJson.addShapedRecipe(ModItems.RaritaniumPickaxe, new Object[]{"iii"," s "," s ", 'i', "crystalRaritanium", 's', ModItems.RaritaniumRod});
		toJson.addShapedRecipe(ModItems.RaritaniumShovel, new Object[]{" i "," s "," s ", 'i', "crystalRaritanium", 's', ModItems.RaritaniumRod});
		toJson.addShapedRecipe(ModItems.RaritaniumSword, new Object[]{" i "," i "," s ", 'i', "crystalRaritanium", 's', ModItems.RaritaniumRod});
		toJson.addShapedRecipe(ModItems.AdamantineAxe, new Object[]{"ii ","is "," s ", 'i', "ingotAdamantine", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.AdamantineHoe, new Object[]{"ii "," s "," s ", 'i', "ingotAdamantine", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.AdamantinePickaxe, new Object[]{"iii"," s "," s ", 'i', "ingotAdamantine", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.AdamantineShovel, new Object[]{" i "," s "," s ", 'i', "ingotAdamantine", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.AdamantineSword, new Object[]{" i "," i "," s ", 'i', "ingotAdamantine", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.CarbonoxAxe, new Object[]{"ii ","is "," s ", 'i', "ingotCarbonox", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.CarbonoxHoe, new Object[]{"ii "," s "," s ", 'i', "ingotCarbonox", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.CarbonoxPickaxe, new Object[]{"iii"," s "," s ", 'i', "ingotCarbonox", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.CarbonoxShovel, new Object[]{" i "," s "," s ", 'i', "ingotCarbonox", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.CarbonoxSword, new Object[]{" i "," i "," s ", 'i', "ingotCarbonox", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.TitaniumAxe, new Object[]{"ii ","is "," s ", 'i', "ingotTitanium", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.TitaniumHoe, new Object[]{"ii "," s "," s ", 'i', "ingotTitanium", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.TitaniumPickaxe, new Object[]{"iii"," s "," s ", 'i', "ingotTitanium", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.TitaniumShovel, new Object[]{" i "," s "," s ", 'i', "ingotTitanium", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.TitaniumSword, new Object[]{" i "," i "," s ", 'i', "ingotTitanium", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.TrilliumAxe, new Object[]{"ii ","is "," s ", 'i', "ingotTrillium", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.TrilliumHoe, new Object[]{"ii "," s "," s ", 'i', "ingotTrillium", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.TrilliumPickaxe, new Object[]{"iii"," s "," s ", 'i', "ingotTrillium", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.TrilliumShovel, new Object[]{" i "," s "," s ", 'i', "ingotTrillium", 's', "stickWood"});
		toJson.addShapedRecipe(ModItems.TrilliumSword, new Object[]{" i "," i "," s ", 'i', "ingotTrillium", 's', "stickWood"});
		
		
		toJson.addShapedRecipe(ModBlocks.Machine_block, new Object[]{"ccc","csc","tft", 'c', "plateCarbonox", 's', "shardRaritanium", 't', "plateTitanium", 'f', Blocks.FURNACE});
		toJson.addShapedRecipe(ModItems.RaritaniumHammer, new Object[]{" ii"," si","s  ", 'i', "crystalRaritanium", 's', "stickWood"});
		toJson.addShapelessRecipe(ModItems.RaritaniumShard, "crystalRaritanium", ModItems.RaritaniumHammer);
		toJson.addShapelessRecipe(ModItems.blackLeather, Items.LEATHER, new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()), "ingotTitanium" );
		toJson.addShapedRecipe(new ItemStack(ModItems.blackLeather, 6), new Object[] { "ltl", "ldl", "ltl", 'l', Items.LEATHER , 'd', new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()), 't', "ingotTitanium" });
		toJson.addShapedRecipe(ModItems.belt, new Object[] { "lll", "tct", "lll", 'c', "plateCarbonox", 't', "ingotTitanium", 'l', ModItems.blackLeather });
		toJson.addShapedRecipe(ModItems.pda, new Object[] { "csc", "ctc", "csc", 'c', "plateCarbonox", 't', "paneGlass", 's', "shardRaritanium"});
		
		toJson.addShapedRecipe(ModBlocks.AdamantineBlock, new Object[]{"iii","iii","iii", 'i', "ingotAdamantine"});
		toJson.addShapelessRecipe(new ItemStack(ModItems.AdamantineIngot, 9), ModBlocks.AdamantineBlock);
		toJson.addShapedRecipe(ModBlocks.CarbonoxBlock, new Object[]{"iii","iii","iii", 'i', "ingotCarbonox"});
		toJson.addShapelessRecipe(new ItemStack(ModItems.CarbonoxIngot, 9), ModBlocks.CarbonoxBlock);
		toJson.addShapedRecipe(ModBlocks.TitaniumBlock, new Object[]{"iii","iii","iii", 'i', "ingotTitanium"});
		toJson.addShapelessRecipe(new ItemStack(ModItems.TitaniumIngot, 9), ModBlocks.TitaniumBlock);
		toJson.addShapedRecipe(ModBlocks.TrilliumBlock, new Object[]{"iii","iii","iii", 'i', "ingotTrillium"});
		toJson.addShapelessRecipe(new ItemStack(ModItems.TrilliumIngot, 9), ModBlocks.TrilliumBlock);
		toJson.addShapelessRecipe(ModItems.AdamantinePlate, ModItems.AdamantineIngot, ModItems.RaritaniumHammer);
		toJson.addShapelessRecipe(ModItems.CarbonoxPlate, ModItems.CarbonoxIngot, ModItems.RaritaniumHammer);
		toJson.addShapelessRecipe(ModItems.TitaniumPlate, ModItems.TitaniumIngot, ModItems.RaritaniumHammer);
		toJson.addShapelessRecipe(ModItems.TrilliumPlate, ModItems.TrilliumIngot, ModItems.RaritaniumHammer);
		
		toJson.addShapedRecipe(ModBlocks.CoalGenerator_T1, new Object[]{"ici","imi","tft", 'i', "plateCarbonox", 'c', Blocks.COAL_BLOCK, 't',"plateTitanium" , 'f', Items.FLINT_AND_STEEL, 'm' , ModBlocks.Machine_block });
		toJson.addShapedRecipe(ModBlocks.CoalGenerator_T2, new Object[]{"ici","tmt","ifi", 'i', "plateAdamantine", 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.CoalGenerator_T1});
		toJson.addShapedRecipe(ModBlocks.CoalGenerator_T3, new Object[]{"ici","tmt","ifi", 'i', ModItems.RaritaniumEnhancedPlate, 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.CoalGenerator_T2});
		
		toJson.addShapedRecipe(ModBlocks.Generator_T1, new Object[]{"ifi","imi","tct", 'i', "plateCarbonox", 'c', Blocks.OBSIDIAN, 't',"plateTitanium" , 'f', Items.LAVA_BUCKET, 'm' , ModBlocks.Machine_block });
		toJson.addShapedRecipe(ModBlocks.Generator_T2, new Object[]{"ici","tmt","ifi", 'i', "plateAdamantine", 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.Generator_T1});
		toJson.addShapedRecipe(ModBlocks.Generator_T3, new Object[]{"ici","tmt","ifi", 'i', ModItems.RaritaniumEnhancedPlate, 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.Generator_T2});
		
		toJson.addShapedRecipe(ModBlocks.Smelter_T1, new Object[]{"ifi","imi","tit", 'i', "plateCarbonox", 't',"plateTitanium" , 'f', Items.BUCKET, 'm' , ModBlocks.Machine_block });
		toJson.addShapedRecipe(ModBlocks.Smelter_T2, new Object[]{"ici","tmt","ifi", 'i', "plateAdamantine", 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.Smelter_T1});
		toJson.addShapedRecipe(ModBlocks.Smelter_T3, new Object[]{"ici","tmt","ifi", 'i', ModItems.RaritaniumEnhancedPlate, 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.Smelter_T2});
		
		toJson.addShapedRecipe(ModBlocks.Battery_T1, new Object[]{"iri","imi","trt", 'i', "plateCarbonox", 'r', ModBlocks.RaritaniumCrystal, 't',"plateTitanium" , 'm' , ModBlocks.Machine_block });
		toJson.addShapedRecipe(ModBlocks.Battery_T2, new Object[]{"iri","tmt","iri", 'i', "plateAdamantine", 'r', ModBlocks.RaritaniumCrystal, 't',"plateTitanium" , 'm' , ModBlocks.Battery_T1 });
		toJson.addShapedRecipe(ModBlocks.Battery_T3, new Object[]{"iri","tmt","iri", 'i', ModItems.RaritaniumEnhancedPlate, 'r', ModBlocks.RaritaniumCrystal, 't',"plateCarbonox" , 'm' , ModBlocks.Battery_T2});
		
		toJson.addShapedRecipe(ModBlocks.Fabricator_T1, new Object[]{"ifi","imi","tlt", 'i', "plateCarbonox", 't',"plateTitanium" , 'f', Blocks.CRAFTING_TABLE, 'l', Blocks.FURNACE, 'm' , ModBlocks.Machine_block });
		toJson.addShapedRecipe(ModBlocks.Fabricator_T2, new Object[]{"ici","tmt","ifi", 'i', "plateAdamantine", 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.Fabricator_T1});
		toJson.addShapedRecipe(ModBlocks.Fabricator_T3, new Object[]{"ici","tmt","ifi", 'i', ModItems.RaritaniumEnhancedPlate, 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.Fabricator_T2});
		
		toJson.addShapedRecipe(ModBlocks.Extractor_T1, new Object[]{"ifi","imi","tlt", 'i', "plateCarbonox", 't',"plateTitanium" , 'f', Blocks.STICKY_PISTON, 'l', Blocks.FURNACE, 'm' , ModBlocks.Machine_block });
		toJson.addShapedRecipe(ModBlocks.Extractor_T2, new Object[]{"ici","tmt","ifi", 'i', "plateAdamantine", 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.Extractor_T1});
		toJson.addShapedRecipe(ModBlocks.Extractor_T3, new Object[]{"ici","tmt","ifi", 'i', ModItems.RaritaniumEnhancedPlate, 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.Extractor_T2});
		
		toJson.addShapedRecipe(ModBlocks.Processor_T1, new Object[]{"ili","imi","tlt", 'i', "plateCarbonox", 't',"plateTitanium", 'l', Blocks.FURNACE, 'm' , ModBlocks.Machine_block });
		toJson.addShapedRecipe(ModBlocks.Processor_T2, new Object[]{"ici","tmt","ifi", 'i', "plateAdamantine", 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.Processor_T1});
		toJson.addShapedRecipe(ModBlocks.Processor_T3, new Object[]{"ici","tmt","ifi", 'i', ModItems.RaritaniumEnhancedPlate, 'c', ModItems.Circuit, 't',"plateCarbonox" , 'f', "ingotConductingMetal", 'm' , ModBlocks.Processor_T2});
		
	}
	
	
	private static void RegisterSmelterRecipes() {
		SmelterRecipes.instance().addRecipe("oreTrillium", 500, new FluidStack(ModFluids.fluidTrillium, 1000));
		SmelterRecipes.instance().addRecipe("ingotTrillium", 250, new FluidStack(ModFluids.fluidTrillium, 500));
		SmelterRecipes.instance().addRecipeForBlock(ModBlocks.TrilliumBlock, 1000, new FluidStack(ModFluids.fluidTrillium, 4500));
		SmelterRecipes.instance().addRecipe("cobblestone", 250, new FluidStack(FluidRegistry.LAVA, 100));
		SmelterRecipes.instance().addRecipe("stone", 250, new FluidStack(FluidRegistry.LAVA, 250));
		SmelterRecipes.instance().addRecipe("netherrack", 250, new FluidStack(FluidRegistry.LAVA, 500));
		SmelterRecipes.instance().addRecipe(new ItemStack(Blocks.OBSIDIAN), 500, new FluidStack(FluidRegistry.LAVA, 1000));
		SmelterRecipes.instance().addRecipe(new ItemStack(Blocks.MAGMA), 500, new FluidStack(FluidRegistry.LAVA, 1000));
		
		SmelterRecipes.instance().addRecipe("dustGlowstone", 250, "glowstone", 250);
		SmelterRecipes.instance().addRecipe(new ItemStack(Blocks.GLOWSTONE), 250, "glowstone", 1000);
		SmelterRecipes.instance().addRecipe("clathrateGlowstone", 250, "glowstone", 250);
		SmelterRecipes.instance().addRecipe("oreClathrateGlowstone", 250, "glowstone", 1000);
		SmelterRecipes.instance().addRecipe("dustRedstone", 250, "redstone", 250);
		SmelterRecipes.instance().addRecipe("clathrateRedstone", 250, "redstone", 250);
		SmelterRecipes.instance().addRecipe("oreClathrateRedstone", 250, "redstone", 1000);
		SmelterRecipes.instance().addRecipe("blockRedstone", 250, "redstone", 900);
		SmelterRecipes.instance().addRecipe(new ItemStack(Items.ENDER_PEARL), 250, "ender", 250);
		SmelterRecipes.instance().addRecipe("clathrateEnder", 250, "ender", 250);
		SmelterRecipes.instance().addRecipe("oreClathrateEnder", 250, "ender", 1000);
		SmelterRecipes.instance().addRecipe("dustPyrotheum", 250, "pyrotheum", 250);
		SmelterRecipes.instance().addRecipe("dustCryotheum", 250, "cryotheum", 250);
		SmelterRecipes.instance().addRecipe("dustAerotheum", 250, "aerotheum", 250);
		SmelterRecipes.instance().addRecipe("dustPetrotheum", 250, "petrotheum", 250);
		SmelterRecipes.instance().addRecipe("seed", 250, "seed_oil", 50);
		SmelterRecipes.instance().addRecipe("dustCoal", 250, "coal", 100);
		SmelterRecipes.instance().addRecipe("clathrateOil", 250, "crude_oil", 250);
		SmelterRecipes.instance().addRecipe("oreClathrateOilSand", 250, "crude_oil", 1000);
		SmelterRecipes.instance().addRecipe("oreClathrateOilShale", 250, "crude_oil", 1000);
	}
	
	private static void RegisterExtractorRecipes() {
		ExtractorRecipes.instance().addRecipe("stone", 200, new ItemStack(Blocks.COBBLESTONE));
		ExtractorRecipes.instance().addRecipe("cobblestone", 200, new ItemStack(Blocks.GRAVEL));
		ExtractorRecipes.instance().addRecipe("gravel", 200, new ItemStack(Blocks.SAND));
		ExtractorRecipes.instance().addRecipe("sand", 200, new ItemStack(ModItems.Silicon));
		ExtractorRecipes.instance().addRecipe("blockWool", 200, new ItemStack(Items.STRING, 4));
		ExtractorRecipes.instance().addRecipe("netherrack", 200, new ItemStack(Blocks.GRAVEL));
		ExtractorRecipes.instance().addRecipe("flowerYellow", 200, new ItemStack(Items.DYE,1,11));
		ExtractorRecipes.instance().addRecipeForBlock(Blocks.RED_SANDSTONE, 200, new ItemStack(Blocks.SAND,1,1));
		ExtractorRecipes.instance().addRecipe("blockStainedHardenedClay", 200, new ItemStack(Items.CLAY_BALL, 4));
		
		ExtractorRecipes.instance().addRecipe("blockGlowstone", 200, "dustGlowstone",4);
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
		ExtractorRecipes.instance().addRecipe("oreUranium", 200, "dustUranium", 2);
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
		ExtractorRecipes.instance().addRecipe("oreMithril", 200, "dustMithril", 2);
		ExtractorRecipes.instance().addRecipe("oreRedstone", 200, "dustRedstone", 8);
		ExtractorRecipes.instance().addRecipe("oreLapis", 200, new ItemStack(Items.DYE, 8, 4));
		
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
		ExtractorRecipes.instance().addRecipe("ingotBronze", 200, "dustBronze", 1);
		ExtractorRecipes.instance().addRecipe("ingotInvar", 200, "dustInvar", 1);
		ExtractorRecipes.instance().addRecipe("ingotSteel", 200, "dustSteel", 1);
		ExtractorRecipes.instance().addRecipe("ingotElectrum", 200, "dustElectrum", 1);
		ExtractorRecipes.instance().addRecipe("ingotConstantan", 200, "dustConstantan", 1);
		ExtractorRecipes.instance().addRecipe("ingotSignalum", 200, "dustSignalum", 1);
		ExtractorRecipes.instance().addRecipe("ingotDraconium", 200, "dustDraconium", 1);
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
		ExtractorRecipes.instance().addRecipe("ingotMithril", 200, "dustMithril", 1);
		ExtractorRecipes.instance().addRecipe("ingotEnderium", 200, "dustEnderium", 1);
		ExtractorRecipes.instance().addRecipe("ingotElectrumFlux", 200, "dustElectrumFlux", 1);
		ExtractorRecipes.instance().addRecipe("ingotBrass", 200, "dustBras", 1);
		ExtractorRecipes.instance().addRecipe("ingotThaumium", 200, "dustThaumium", 1);
		ExtractorRecipes.instance().addRecipe("ingotVoid", 200, "dustVoid", 1);
		ExtractorRecipes.instance().addRecipe(new ItemStack(Items.DYE, 1, 4), 200, "dustLapis", 1);
		
		ExtractorRecipes.instance().addRecipeForBlock(ModBlocks.RaritaniumCrystal, 200, new ItemStack(ModItems.RaritaniumShard, 4));
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
		ProcessorRecipes.instance().addRecipe("dustBronze", 200, "ingotBronze", 1);
		ProcessorRecipes.instance().addRecipe("dustInvar", 200, "ingotInvar", 1);
		ProcessorRecipes.instance().addRecipe("dustSteel", 200, "ingotSteel", 1);
		ProcessorRecipes.instance().addRecipe("dustElectrum", 200, "ingotElectrum", 1);
		ProcessorRecipes.instance().addRecipe("dustConstantan", 200, "ingotConstantan", 1);
		ProcessorRecipes.instance().addRecipe("dustSignalum", 200, "ingotSignalum", 1);
		ProcessorRecipes.instance().addRecipe("dustDraconium", 200, "ingotDraconium", 1);
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
		ProcessorRecipes.instance().addRecipe("dustMithril", 200, "ingotMithril", 1);
		ProcessorRecipes.instance().addRecipe("dustEnderium", 200, "ingotEnderium", 1);
		ProcessorRecipes.instance().addRecipe("dustElectrumFlux", 200, "ingotElectrumFlux", 1);
		ProcessorRecipes.instance().addRecipe("dustBrass", 200, "ingotBras", 1);
		ProcessorRecipes.instance().addRecipe("dustThaumium", 200, "ingotThaumium", 1);
		ProcessorRecipes.instance().addRecipe("dustVoid", 200, "ingotVoid", 1);
		
		ProcessorRecipes.instance().addRecipe("ingotAdamantine", 200, "plateAdamantine", 1);
		ProcessorRecipes.instance().addRecipe("ingotCarbonox", 200, "plateCarbonox", 1);
		ProcessorRecipes.instance().addRecipe("ingotTitanium", 200, "plateTitanium", 1);
		ProcessorRecipes.instance().addRecipe("ingotTrillium", 200, "plateTrillium", 1);
		ProcessorRecipes.instance().addRecipe("ingotIron", 200, "plateIron", 1);
		ProcessorRecipes.instance().addRecipe("ingotGold", 200, "plateGold", 1);
		ProcessorRecipes.instance().addRecipe("ingotCopper", 200, "plateCopper", 1);
		ProcessorRecipes.instance().addRecipe("ingotLead", 200, "plateLead", 1);
		ProcessorRecipes.instance().addRecipe("ingotTin", 200, "plateTin", 1);
		ProcessorRecipes.instance().addRecipe("ingotSilver", 200, "plateSilver", 1);
		ProcessorRecipes.instance().addRecipe("ingotUranium", 200, "plateUranium", 1);
		ProcessorRecipes.instance().addRecipe("ingotAluminium", 200, "plateAluminium", 1);
		ProcessorRecipes.instance().addRecipe("ingotAluminum", 200, "plateAluminum", 1);
		ProcessorRecipes.instance().addRecipe("ingotZinc", 200, "plateZinc", 1);
		ProcessorRecipes.instance().addRecipe("ingotPlatinum", 200, "platePlatinum", 1);
		ProcessorRecipes.instance().addRecipe("ingotShiny", 200, "plateShiny", 1);
		ProcessorRecipes.instance().addRecipe("ingotOsmium", 200, "plateOsmium", 1);
		ProcessorRecipes.instance().addRecipe("ingotIridium", 200, "plateIridium", 1);
		ProcessorRecipes.instance().addRecipe("ingotNickel", 200, "plateNickel", 1);
		ProcessorRecipes.instance().addRecipe("ingotManaInfused", 200, "plateManaInfused", 1);
		ProcessorRecipes.instance().addRecipe("ingotMithril", 200, "plateMithril", 1);
		ProcessorRecipes.instance().addRecipe("ingotBronze", 200, "plateBronze", 1);
		ProcessorRecipes.instance().addRecipe("ingotInvar", 200, "plateInvar", 1);
		ProcessorRecipes.instance().addRecipe("ingotSteel", 200, "plateSteel", 1);
		ProcessorRecipes.instance().addRecipe("ingotElectrum", 200, "plateElectrum", 1);
		ProcessorRecipes.instance().addRecipe("ingotConstantan", 200, "plateConstantan", 1);
		ProcessorRecipes.instance().addRecipe("ingotSignalum", 200, "plateSignalum", 1);
		ProcessorRecipes.instance().addRecipe("ingotConstantan", 200, "plateConstantan", 1);
		ProcessorRecipes.instance().addRecipe("ingotLumium", 200, "plateLumium", 1);
		ProcessorRecipes.instance().addRecipe("ingotEnderium", 200, "plateEnderium", 1);
		ProcessorRecipes.instance().addRecipe("ingotElectrumFlux", 200, "plateElectrumFlux", 1);
		ProcessorRecipes.instance().addRecipe("ingotBrass", 200, "plateBras", 1);
		ProcessorRecipes.instance().addRecipe("ingotThaumium", 200, "plateThaumium", 1);
		ProcessorRecipes.instance().addRecipe("ingotVoid", 200, "plateVoid", 1);
		
		ProcessorRecipes.instance().addRecipe("ingotConductingMetal", 200, new ItemStack(ModItems.ConductingWire, 4));
	}
	
	private static void RegisterFabricatorRecipes() {
		FabricatorRecipes.instance().addRecipe("dustRedstone", "dustGlowstone", 400, new ItemStack(ModItems.RedGlowstoneDust,2));
		FabricatorRecipes.instance().addRecipe("plateCarbonox",5 , new ItemStack(ModBlocks.RaritaniumCrystal,2), 400, new ItemStack(ModItems.RaritaniumEnhancedPlate));
		FabricatorRecipes.instance().add(ModItems.RedGlowstoneDust, ModItems.AdamantineIngot, 400, new ItemStack(ModItems.ConductingIngot));
		FabricatorRecipes.instance().addRecipe("ingotCopper", "ingotTin", 400, "ingotBronze",1);
		FabricatorRecipes.instance().addRecipe("itemSilicon", "wireConductingMetal", 400, new ItemStack(ModItems.Circuit));
		FabricatorRecipes.instance().addRecipe("ingotNickel", 1, "ingotIron", 2, 400, "ingotInvar",3);
		FabricatorRecipes.instance().addRecipe("ingotNickel", 1, "ingotCopper", 1, 400, "ingotConstantan",2);
		FabricatorRecipes.instance().addRecipe("ingotIron", 1, "dustCoal", 2, 400, "ingotSteel",1);
		FabricatorRecipes.instance().addRecipe("ingotSilver", 1, "ingotGold", 1, 400, "ingotElectrum",1);
		FabricatorRecipes.instance().addRecipe("ingotBronze", 4, "dustRedstone", 10, 400, "ingotSignalum",4);
		FabricatorRecipes.instance().addRecipe("ingotGold", 1, "dustRedstoneGlowstoneMix", 1, 400, "ingotEnergeticAlloy",1);
		FabricatorRecipes.instance().addRecipe(new ItemStack(Items.ENDER_PEARL), "ingotEnergeticAlloy", 1, 400, "ingotVibrantAlloy",1);
		FabricatorRecipes.instance().addRecipe("dustRedstone", 1, "itemSilicon", 1, 400, "ingotRedstoneAlloy",1);
		FabricatorRecipes.instance().addRecipe("dustRedstone", 1, "ingotIron", 1, 400, "ingotConductiveIron",1);
		FabricatorRecipes.instance().addRecipe(new ItemStack(Items.ENDER_PEARL), "ingotIron", 1, 400, "ingotPulsatingIron",1);
		FabricatorRecipes.instance().addRecipe("soulSand", 1, "ingotGold", 1, 400, "ingotSoularium",1);
		FabricatorRecipes.instance().addRecipe("ingotCobalt", 1, "ingotArdite", 1, 400, "ingotManyullyn",1);
		FabricatorRecipes.instance().addRecipe("ingotElectrum", 1, "dustRedstone", 10, 400, "dustElectrumFlux",1);
		FabricatorRecipes.instance().addRecipe("dustLead", 3, "dustPlatinum", 1, 400, "dustLeadPlatinumMix",4);
		FabricatorRecipes.instance().addRecipe(new ItemStack(Items.ENDER_PEARL, 4), "dustLeadPlatinumMix", 4, 400, "ingotEnderium",4);
		FabricatorRecipes.instance().addRecipe("dustTin", 3, "dustSilver", 1, 400, "dustTinSilverMix",4);
		FabricatorRecipes.instance().addRecipe(new ItemStack(Items.GLOWSTONE_DUST, 4), "dustTinSilverMix", 4, 400, "ingotLumium",4);
	}
	
	private static void RegisterIntegrationRecipes(ItemStack in, ItemStack out){
		ThermalExpansionIntegration.addPulverizerRecipe(4000, in, out, null, 0);
		if(in.getItem() instanceof ItemBlock){
			MekanismIntegration.AddRecipeToEnrichmentChamber(in, out);
		}
		else{
			MekanismIntegration.AddRecipeToCrusher(in, out);
		}
		EnderIOIntegration.AddRecipeToSagmill(in, out);
	}

}
