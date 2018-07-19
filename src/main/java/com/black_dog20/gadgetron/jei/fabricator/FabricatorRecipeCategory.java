package com.black_dog20.gadgetron.jei.fabricator;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.gadgetron.jei.RecipeCategoryUid;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class FabricatorRecipeCategory implements IRecipeCategory<FabricatorRecipeWrapper> {
	
	protected static final int inputSlot = 0;
	protected static final int inputSlotTwo = 1;
	protected static final int outputSlot = 2;

	protected final IDrawableStatic staticArrow;
	protected final IDrawableAnimated arrow;
	private final String localizedName;
	private final IDrawable background;

	public FabricatorRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/fabricator.png");
		staticArrow = guiHelper.createDrawable(gui, 176, 14, 24, 17);
		arrow = guiHelper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.LEFT, false);
		
		background = guiHelper.createDrawable(gui, 29, 27, 117, 31);
		localizedName = I18n.format("gadgetron.container.fabricator");
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
		arrow.draw(minecraft, 57, 8);		
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, FabricatorRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(inputSlot, true, 3, 7);
		guiItemStacks.init(inputSlotTwo, true, 33, 7);
		guiItemStacks.init(outputSlot, false, 93, 7);

		guiItemStacks.set(ingredients);
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return new ArrayList<String>();
	}

	@Override
	public String getUid() {
		return RecipeCategoryUid.FABRICATOR;
	}

	@Override
	public String getTitle() {
		return localizedName;
	}
}