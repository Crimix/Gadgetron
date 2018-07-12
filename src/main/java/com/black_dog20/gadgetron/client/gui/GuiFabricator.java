package com.black_dog20.gadgetron.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.client.gui.utils.GuiElement;
import com.black_dog20.gadgetron.container.ContainerFabricator;
import com.black_dog20.gadgetron.tile.TileEntityFabricator;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiFabricator extends GuiContainerBase {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/battery.png");
	private TileEntityFabricator te;
	private GuiElement power = new GuiElement("powerbar", 6, 10, 62, 19, 176, 95, I18n.format("gadgetron.container.energystored"));
	
	public GuiFabricator(EntityPlayer player, TileEntityFabricator tileEntity) {
		super(new ContainerFabricator(player.inventory, tileEntity), tileEntity, player);
		elements.add(power);
		this.te = (TileEntityFabricator) tile;
	}

	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		power.updateDynamicList(getPowerTipList());	
		super.drawScreen(mouseX, mouseY, par3);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		drawProgressVertical(te.getStoredEnergyPercentage(), power); //Powerbar
	}	
	
	private List<String> getPowerTipList(){
		List<String> powerList = new ArrayList<String>();
		powerList.add(getFormattedInt(te.getStoredEnergy()) + "RF");
		powerList.add(Integer.toString(te.getStoredEnergyPercentage()) + "%");
		return powerList;
	}
}