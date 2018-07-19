package com.black_dog20.gadgetron.jei.fuelgenerator;

import java.awt.Color;

import com.black_dog20.gadgetron.recipehandler.FuelGeneratorHandler.FuelObject;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;

public class FuelGeneratorRecipeWrapper extends BlankRecipeWrapper
{

    private final FluidStack inputs;
	private final FuelObject output;

	public FuelGeneratorRecipeWrapper(FluidStack inputs, FuelObject output) {
		this.inputs = inputs;
		this.output = output;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(FluidStack.class, inputs);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		if (output != null) {
			String burn = I18n.format("gadgetron.jei.burn", (output.getTotalBurningTime()/20));
			String rf = I18n.format("gadgetron.jei.generate", output.getPowerPerCycle());
			FontRenderer fontRenderer = minecraft.fontRenderer;
			fontRenderer.drawString(burn, 55, 20, Color.gray.getRGB());
			fontRenderer.drawString(rf, 55, 50, Color.gray.getRGB());
		}
	}
}
