package com.black_dog20.gadgetron.client.gui;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.container.ContainerEnergyGenerator;
import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnergyGenerator extends GuiContainer {
	private static final ResourceLocation gui = new ResourceLocation("tucs:textures/gui/airCompressorGui.png");
	private TileEntityEnergyGenerator tile;

	public GuiEnergyGenerator(InventoryPlayer IPlayer, TileEntityEnergyGenerator tileEntity) {
		super(new ContainerEnergyGenerator(IPlayer, tileEntity));
		tile = tileEntity;
	}

	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		// String s = this.tileAirMaker.hasCustomInventoryName() ?
		// this.tileAirMaker.getInventoryName() :
		// I18n.format(this.tileAirMaker.getInventoryName(), new Object[0]);
		// this.fontRendererObj.drawString(s, this.xSize / 2 -
		// this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		// this.fontRendererObj.drawString(I18n.format("Inventory", new
		// Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

	}
}