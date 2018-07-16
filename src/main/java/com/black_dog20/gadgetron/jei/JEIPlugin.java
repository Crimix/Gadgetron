package com.black_dog20.gadgetron.jei;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.init.ModBlocks;
import com.black_dog20.gadgetron.init.ModItems;
import com.black_dog20.gadgetron.jei.extractor.ExtractorRecipeCategory;
import com.black_dog20.gadgetron.jei.extractor.ExtractorRecipeMaker;
import com.black_dog20.gadgetron.jei.fabricator.FabricatorRecipeCategory;
import com.black_dog20.gadgetron.jei.fabricator.FabricatorRecipeMaker;
import com.black_dog20.gadgetron.jei.fuelgenerator.FuelGeneratorRecipeCategory;
import com.black_dog20.gadgetron.jei.fuelgenerator.FuelGeneratorRecipeMaker;
import com.black_dog20.gadgetron.jei.processor.ProcessorRecipeCategory;
import com.black_dog20.gadgetron.jei.processor.ProcessorRecipeMaker;
import com.black_dog20.gadgetron.jei.smelter.SmelterRecipeCategory;
import com.black_dog20.gadgetron.jei.smelter.SmelterRecipeMaker;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

@mezz.jei.api.JEIPlugin
public class JEIPlugin extends BlankModPlugin{
	
	 public static IJeiHelpers jeiHelper;

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerIngredients(IModIngredientRegistration registry) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		final IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registry.addRecipeCategories(
				new ExtractorRecipeCategory(guiHelper),
				new ProcessorRecipeCategory(guiHelper),
				new SmelterRecipeCategory(guiHelper),
				new FuelGeneratorRecipeCategory(guiHelper),
				new FabricatorRecipeCategory(guiHelper)
		);
	}
	

	@Override
	public void register(IModRegistry registry) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(ModBlocks.TrilliumOre));
		list.add(new ItemStack(ModItems.TrilliumAxe));
		list.add(new ItemStack(ModItems.TrilliumHoe));
		list.add(new ItemStack(ModItems.TrilliumPickaxe));
		list.add(new ItemStack(ModItems.TrilliumShovel));
		list.add(new ItemStack(ModItems.TrilliumSword));
		list.add(new ItemStack(ModBlocks.RaritaniumCrystal));
		list.add(new ItemStack(ModItems.AdamantineIngot));
		list.add(new ItemStack(ModItems.CarbonoxIngot));
		list.add(new ItemStack(ModItems.TrilliumIngot));
		
		RegisterInfo(registry, list, ".info");
		if(ModConfig.doesTrilliumWeaponsCausePoison){
			RegisterInfo(registry, list, ".poison");
		}
		if(ModConfig.doesTrilliumCausePoison){
			RegisterInfo(registry, list, ".poisonous");
		}
		
		jeiHelper = registry.getJeiHelpers();
		
		registry.addRecipes(ExtractorRecipeMaker.getRecipes(jeiHelper), RecipeCategoryUid.EXTRACTOR);
		//registry.addRecipeClickArea(GuiExtractor.class, 78, 32, 28, 23, RecipeCategoryUid.EXTRACTOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Extractor_T1), RecipeCategoryUid.EXTRACTOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Extractor_T2), RecipeCategoryUid.EXTRACTOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Extractor_T3), RecipeCategoryUid.EXTRACTOR);
		
		registry.addRecipes(ProcessorRecipeMaker.getRecipes(jeiHelper), RecipeCategoryUid.PROCESSOR);
		//registry.addRecipeClickArea(GuiProcessor.class, 78, 32, 28, 23, RecipeCategoryUid.PROCESSOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Processor_T1), RecipeCategoryUid.PROCESSOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Processor_T2), RecipeCategoryUid.PROCESSOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Processor_T2), RecipeCategoryUid.PROCESSOR);
		
		registry.addRecipes(SmelterRecipeMaker.getRecipes(jeiHelper), RecipeCategoryUid.SMELTER);
		//registry.addRecipeClickArea(GuiSmelter.class, 68, 34, 28, 23, RecipeCategoryUid.SMELTER);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Smelter_T1), RecipeCategoryUid.SMELTER);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Smelter_T2), RecipeCategoryUid.SMELTER);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Smelter_T3), RecipeCategoryUid.SMELTER);
		
		registry.addRecipes(FuelGeneratorRecipeMaker.getRecipes(jeiHelper), RecipeCategoryUid.FUEL_GENERATOR);
		//registry.addRecipeClickArea(GuiEnergyGenerator.class, 79, 34, 15, 15, RecipeCategoryUid.FUEL_GENERATOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Generator_T1), RecipeCategoryUid.FUEL_GENERATOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Generator_T2), RecipeCategoryUid.FUEL_GENERATOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Generator_T3), RecipeCategoryUid.FUEL_GENERATOR);
		
		registry.addRecipes(FabricatorRecipeMaker.getRecipes(jeiHelper), RecipeCategoryUid.FABRICATOR);
		//registry.addRecipeClickArea(GuiFabricator.class, 86, 34, 34, 17, RecipeCategoryUid.FABRICATOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Fabricator_T1), RecipeCategoryUid.FABRICATOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Fabricator_T2), RecipeCategoryUid.FABRICATOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.Fabricator_T3), RecipeCategoryUid.FABRICATOR);
	}
	
	private void RegisterInfo(IModRegistry registry, List<ItemStack> list, String postfix){
		for(ItemStack stack : list){
			String info = getFormattedString(stack, postfix);
			if(info != null){
				registry.addIngredientInfo(stack, ItemStack.class, info);
			}
		}
	}
	
	private String getFormattedString(ItemStack stack, String postfix){
		String res = I18n.format(stack.getItem().getUnlocalizedName()+ postfix);
		if(res.contains(stack.getItem().getUnlocalizedName())){
			return null;
		}
		else{
			return res;
		}
	}
		

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		// TODO Auto-generated method stub
		
	}

}
