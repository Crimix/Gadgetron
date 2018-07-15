package com.black_dog20.gadgetron.jei.fuelgenerator;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.gadgetron.jei.RecipeCategoryUid;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class FuelGeneratorRecipeCategory implements IRecipeCategory<FuelGeneratorRecipeWrapper> {
	
	protected static final int inputSlot = 0;

	protected final IDrawableStatic staticArrow;
	protected final IDrawableAnimated arrow;
	private final String localizedName;
	private final IDrawable background;
	
	public FuelGeneratorRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/generator.png");
		staticArrow = guiHelper.createDrawable(gui, 176, 0, 13, 13);
		arrow = guiHelper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.TOP, true);
		
		background = guiHelper.createDrawable(gui, 27, 5, 145, 71);
		localizedName = I18n.format("gadgetron.container.energy_generator");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return null;
	}

	@Override
	public String getModName() {
		return I18n.format("itemGroup.gadgetron");
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		arrow.draw(minecraft, 53, 31);	
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, FuelGeneratorRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

		guiFluidStacks.init(inputSlot, true, 4, 5, 16, 63, 1000, false, null);
		guiFluidStacks.set(ingredients);
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return new ArrayList<String>();
	}

	@Override
	public String getUid() {
		return RecipeCategoryUid.FUEL_GENERATOR;
	}

	@Override
	public String getTitle() {
		return localizedName;
	}
}