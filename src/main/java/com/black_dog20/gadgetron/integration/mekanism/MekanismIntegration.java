package com.black_dog20.gadgetron.integration.mekanism;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class MekanismIntegration {

	
	public static void AddRecipeToEnrichmentChamber(ItemStack in, ItemStack out) {
		NBTTagCompound recipeTag = new NBTTagCompound();
		recipeTag.setTag("input", in.writeToNBT(new NBTTagCompound()));

		recipeTag.setTag("output", out.writeToNBT(new NBTTagCompound()));

		if(Loader.isModLoaded("mekanism"))
			FMLInterModComms.sendMessage("mekanism", "EnrichmentChamberRecipe", recipeTag);
	}
	
	public static void AddRecipeToCrusher(ItemStack in, ItemStack out) {
		NBTTagCompound recipeTag = new NBTTagCompound();
		recipeTag.setTag("input", in.writeToNBT(new NBTTagCompound()));

		recipeTag.setTag("output", out.writeToNBT(new NBTTagCompound()));

		if(Loader.isModLoaded("mekanism"))
			FMLInterModComms.sendMessage("mekanism", "CrusherRecipe", recipeTag);
	}
	
}
