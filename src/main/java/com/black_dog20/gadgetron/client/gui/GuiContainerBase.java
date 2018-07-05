package com.black_dog20.gadgetron.client.gui;

import java.text.NumberFormat;
import java.util.ArrayList;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.client.gui.utils.GuiCustomButton;
import com.black_dog20.gadgetron.client.gui.utils.GuiElement;
import com.black_dog20.gadgetron.network.PacketHandler;
import com.black_dog20.gadgetron.network.message.MessageOpenGuiOnServer;
import com.black_dog20.gadgetron.tile.base.TileEntityBase;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiContainerBase extends GuiContainer{

	protected ArrayList<GuiElement> elements = new ArrayList<GuiElement>();
	protected final int xSizeOfTexture = 192 , ySizeOfTexture = 135;
	protected TileEntityBase tile;
	protected boolean overrideTile = false;
	
	public GuiContainerBase(Container inventorySlotsIn, TileEntityBase tile, EntityPlayer player) {
		super(inventorySlotsIn);
		this.tile = tile;
	}

	@Override
	public void initGui()
	{
		super.initGui();

		this.buttonList.clear();

		int k = (this.width - xSizeOfTexture) / 2;
		int l = (this.height - ySizeOfTexture) / 2;

		if(!overrideTile && tile.hasConfig()) {
			int x = tile.getPos().getX();
			int y = tile.getPos().getY();
			int z = tile.getPos().getZ();
			this.buttonList.add(new GuiCustomButton(0, "I/O", k+150, l+0, 20, 0.80, ()-> PacketHandler.network.sendToServer(new MessageOpenGuiOnServer(Gadgetron.guiIOConfig,x,y,z))));
			
		}
		
	}
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		for(GuiButton e : buttonList) {
			if(e instanceof GuiCustomButton) {
				GuiCustomButton b = (GuiCustomButton)e;
				b.execute(button.id);
			}
		}
	}

	
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		for(GuiElement e : elements) {
			if(mouseX >= k+e.x && mouseX <= k+e.x+e.width && mouseY >= l+e.y && mouseY <= l+e.y + e.height) {
				drawHoveringText(e.getHoverText(), mouseX, mouseY);
			}
		}
		super.drawScreen(mouseX, mouseY, par3);
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
	
	protected String getFormattedInt(int number) {
		return NumberFormat.getIntegerInstance().format(number);
	}
}
