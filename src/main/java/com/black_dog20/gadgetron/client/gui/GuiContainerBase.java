package com.black_dog20.gadgetron.client.gui;

import com.black_dog20.gadgetron.client.gui.Utils.GuiElement;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public abstract class GuiContainerBase extends GuiContainer{

	public GuiContainerBase(Container inventorySlotsIn) {
		super(inventorySlotsIn);
	}
	
	@Override
	protected abstract void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_);
	
	@Override
	protected abstract void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_);

	protected void drawProgressVertical(int progress, GuiElement e) {
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		double oneP = e.height / 100.0;
		int p = (int) Math.ceil(oneP * progress);
		if(p!= 0)
			this.drawTexturedModalRect(k + e.x, l + e.y + e.height - p, e.textureX, e.textureY - p, e.width, p+1);
	}
	
	protected void drawProgressHorizontal(int progress, GuiElement e) {
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		double oneP = e.height / 100.0;
		int p = (int) Math.ceil(oneP * progress);
		if(p!= 0)
			this.drawTexturedModalRect(k + e.x, l + e.y, e.textureX, e.textureY, p + 1, e.height);
	}
	
	protected void drawFluid(FluidStack fluid, int percentage, GuiElement e) {
		if( fluid != null && fluid.amount != 0 ) {
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
	    	ResourceLocation location = fluid.getFluid().getStill();
	        TextureAtlasSprite sprite = mc.getTextureMapBlocks().getAtlasSprite( location.toString() );
			mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			double oneP = e.height / 100.0;
			int p = (int) Math.ceil(oneP * percentage);
			drawTexturedModalRect(k + e.x+1, l + e.y+e.height+1 - p, sprite, 16, p);
		}
	}
}
