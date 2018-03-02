package com.black_dog20.gadgetron.integration.te;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class ThermalExpansionIntegration {
	public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
		if (input == null || primaryOutput == null) {
			return;
		}
		NBTTagCompound toSend = new NBTTagCompound();
		toSend.setInteger("energy", energy);
		toSend.setTag("input", new NBTTagCompound());
		toSend.setTag("primaryOutput", new NBTTagCompound());
		if (secondaryOutput != null) {
			toSend.setTag("secondaryOutput", new NBTTagCompound());
		}
		input.writeToNBT(toSend.getCompoundTag("input"));
		primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));
		if (secondaryOutput != null) {
			secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
			toSend.setInteger("secondaryChance", secondaryChance);
		}
		if(Loader.isModLoaded("thermalexpansion"))
			FMLInterModComms.sendMessage("thermalexpansion", "addpulverizerrecipe", toSend);
	}
}
