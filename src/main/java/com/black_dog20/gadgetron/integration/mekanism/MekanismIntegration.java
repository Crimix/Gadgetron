package com.black_dog20.gadgetron.integration.mekanism;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class MekanismIntegration {

	
	public static void AddRecipeToEnrichmentChamber(ItemStack in, ItemStack out) {
		NBTTagCompound recipeTag = new NBTTagCompound();
		recipeTag.setTag("itemInput", in.writeToNBT(new NBTTagCompound()));

		recipeTag.setTag("itemOutput", out.writeToNBT(new NBTTagCompound()));

		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", recipeTag);
	}
	
	public static void AddRecipeToCrusher(ItemStack in, ItemStack out) {
		NBTTagCompound recipeTag = new NBTTagCompound();
		recipeTag.setTag("itemInput", in.writeToNBT(new NBTTagCompound()));

		recipeTag.setTag("itemOutput", out.writeToNBT(new NBTTagCompound()));

		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", recipeTag);
	}
	
}
