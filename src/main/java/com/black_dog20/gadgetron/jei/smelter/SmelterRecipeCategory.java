package com.black_dog20.gadgetron.jei.smelter;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.gadgetron.jei.RecipeCategoryUid;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class SmelterRecipeCategory implements IRecipeCategory<SmelterRecipeWrapper> {
	
	protected static final int inputSlot = 0;
	protected static final int outputSlot = 1;

	protected final IDrawableStatic staticArrow;
	protected final IDrawableAnimated arrow;
	private final String localizedName;
	private final IDrawable background;

	public SmelterRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/smelter.png");
		staticArrow = guiHelper.createDrawable(gui, 176, 14, 24, 17);
		arrow = guiHelper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.LEFT, false);
		
		background = guiHelper.createDrawable(gui, 36, 8, 125, 70);
		localizedName = I18n.format("gadgetron.container.smelter");
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
		arrow.draw(minecraft, 32, 26);		
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SmelterRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

		guiItemStacks.init(inputSlot, true, 5, 25);
		guiFluidStacks.init(outputSlot, false, 96, 2, 16, 63, 10000, false, null);

		guiItemStacks.set(ingredients);
		guiFluidStacks.set(ingredients);
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return new ArrayList<String>();
	}

	@Override
	public String getUid() {
		return RecipeCategoryUid.SMELTER;
	}

	@Override
	public String getTitle() {
		return localizedName;
	}
}