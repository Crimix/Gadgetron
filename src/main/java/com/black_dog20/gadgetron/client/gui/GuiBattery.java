package com.black_dog20.gadgetron.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.client.gui.utils.GuiElement;
import com.black_dog20.gadgetron.container.ContainerBattery;
import com.black_dog20.gadgetron.tile.TileEntityBattery;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBattery extends GuiContainerBase {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/battery.png");
	private TileEntityBattery tile;
	private ArrayList<GuiElement> elements = new ArrayList<GuiElement>();
	private final EntityPlayer player;
	private GuiElement power = new GuiElement("powerbar", 6, 10, 62, 19, 176, 95, I18n.format("gadgetron.container.energystored"));
	
	public GuiBattery(EntityPlayer player, TileEntityBattery tileEntity) {
		super(new ContainerBattery(player.inventory, tileEntity));
		tile = tileEntity;
		elements.add(power);
		this.player = player;
	}

	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		super.drawScreen(mouseX, mouseY, par3);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		power.updateDynamicList(getPowerTipList());
		
		for(GuiElement e : elements) {
			if(mouseX >= k+e.x && mouseX <= k+e.x+e.width && mouseY >= l+e.y && mouseY <= l+e.y + e.height) {
				drawHoveringText(e.getHoverText(), mouseX, mouseY);
			}
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = tile.getName();
        int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.player.inventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96+4, 4210752);
        String capacity = I18n.format("%s: %s RF",I18n.format("gadgetron.container.capacity"), getFormattedInt(tile.getEnergyCapacity()));
        String input = I18n.format("%s: %s RF/t",I18n.format("gadgetron.container.input"), getFormattedInt(tile.getEnergyStorage().getMaxReceive()));
        String output = I18n.format("%s: %s RF/t",I18n.format("gadgetron.container.output"), getFormattedInt(tile.getEnergyStorage().getMaxExtract()));
        l-=10;
        k-=80;
        this.fontRenderer.drawString(capacity, k, l, 4210752);
        l+=fontRenderer.FONT_HEIGHT;
        this.fontRenderer.drawString(input, k, l, 4210752);
        l+=fontRenderer.FONT_HEIGHT;
        this.fontRenderer.drawString(output, k, l, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		drawProgressVertical(tile.getStoredEnergyPercentage(), power); //Powerbar
	}	
	
	private List<String> getPowerTipList(){
		List<String> powerList = new ArrayList<String>();
		powerList.add(getFormattedInt(tile.getStoredEnergy()) + "RF");
		powerList.add(Integer.toString(tile.getStoredEnergyPercentage()) + "%");
		return powerList;
	}
}