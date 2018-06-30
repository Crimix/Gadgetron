package com.black_dog20.gadgetron.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.client.gui.Utils.GuiElement;
import com.black_dog20.gadgetron.container.ContainerEnergyGenerator;
import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnergyGenerator extends GuiContainerBase {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/generator.png");
	private TileEntityEnergyGenerator tile;
	private ArrayList<GuiElement> elements = new ArrayList<GuiElement>();
	private GuiElement flame = new GuiElement("flame", 81, 36, 12, 14, 176, 12);
	private GuiElement power = new GuiElement("powerbar", 6, 10, 62, 19, 176, 95, "Energy Stored");
	private GuiElement tank = new GuiElement("fluid", 30, 10, 62, 16, 0, 0);
	
	public GuiEnergyGenerator(InventoryPlayer IPlayer, TileEntityEnergyGenerator tileEntity) {
		super(new ContainerEnergyGenerator(IPlayer, tileEntity));
		tile = tileEntity;
		elements.add(flame);
		elements.add(power);
		elements.add(tank);
	}

	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		super.drawScreen(mouseX, mouseY, par3);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		power.updateDynamicList(Integer.toString(tile.getStoredEnergy()) + "RF", Integer.toString(tile.getStoredEnergyPercentage()) + "%");
		tank.updateDynamicList(tile.getFluid().getLocalizedName(), Integer.toString(tile.getStoredFluid())+"mB", Integer.toString(tile.getStoredFluidPercentage()) + "%" );
		
		for(GuiElement e : elements) {
			if(mouseX >= k+e.x && mouseX <= k+e.x+e.width && mouseY >= l+e.y && mouseY <= l+e.y + e.height) {
				drawHoveringText(e.getHoverText(), mouseX, mouseY);
			}
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		// String s = this.tileAirMaker.hasCustomInventoryName() ?
		// this.tileAirMaker.getInventoryName() :
		// I18n.format(this.tileAirMaker.getInventoryName(), new Object[0]);
		// this.fontRendererObj.drawString(s, this.xSize / 2 -
		// this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		// this.fontRendererObj.drawString(I18n.format("Inventory", new
		// Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		drawProgressVertical(4, flame); //Flame
		drawProgressVertical(tile.getStoredEnergyPercentage(), power); //Powerbar
		FluidStack fluid = tile.getFluid();
	    if( fluid != null && fluid.amount != 0 ) {
	    	ResourceLocation location = tile.getFluid().getFluid().getStill();
	        TextureAtlasSprite sprite = mc.getTextureMapBlocks().getAtlasSprite( location.toString() );
			drawFluid(fluid.amount, tile.getFluidCapacity(), sprite, tank);
	    }
	}	
}