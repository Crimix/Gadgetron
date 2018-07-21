package com.black_dog20.gadgetron.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.client.gui.utils.GuiElement;
import com.black_dog20.gadgetron.container.ContainerBattery;
import com.black_dog20.gadgetron.tile.TileEntityBattery;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBattery extends GuiContainerBase {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/battery.png");
	private TileEntityBattery te;
	private final EntityPlayer player;
	private GuiElement power = new GuiElement("powerbar", 6, 10, 62, 19, 176, 95, I18n.format("gadgetron.container.energystored"));
	
	public GuiBattery(EntityPlayer player, TileEntityBattery tileEntity) {
		super(new ContainerBattery(player.inventory, tileEntity), tileEntity, player);
		elements.add(power);
		this.te = (TileEntityBattery) tile;
		this.player = player;
	}

	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		power.updateDynamicList(getPowerTipList());	
		super.drawScreen(mouseX, mouseY, par3);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = I18n.format("gadgetron.container.battery");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.player.inventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96+4, 4210752);
        String capacity = I18n.format("%s: %s RF",I18n.format("gadgetron.container.capacity"), getFormattedInt(te.getEnergyCapacity()));
        String input = I18n.format("%s: %s RF/t",I18n.format("gadgetron.container.inputRf"), getFormattedInt(te.getEnergyStorage().getMaxReceive()));
        String output = I18n.format("%s: %s RF/t",I18n.format("gadgetron.container.outputperblock"), getFormattedInt(te.getEnergyStorage().getMaxExtract()));
        this.fontRenderer.drawString(capacity, 35, 27, 4210752);
        this.fontRenderer.drawString(input, 35, 37, 4210752);
        this.fontRenderer.drawString(output, 35, 47, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		super.drawDefaultBackground();
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