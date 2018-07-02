package com.black_dog20.gadgetron.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.client.gui.utils.GuiElement;
import com.black_dog20.gadgetron.container.ContainerEnergyGenerator;
import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnergyGenerator extends GuiContainerBase {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/generator.png");
	private TileEntityEnergyGenerator tile;
	private ArrayList<GuiElement> elements = new ArrayList<GuiElement>();
	private final InventoryPlayer playerInventory;
	private GuiElement flame = new GuiElement("flame", 81, 36, 12, 14, 176, 12,"Progress");
	private GuiElement power = new GuiElement("powerbar", 6, 10, 62, 19, 176, 95, "Energy Stored");
	private GuiElement tank = new GuiElement("fluid", 30, 10, 62, 16, 0, 0);
	private TextComponentTranslation empty = new TextComponentTranslation("gadgetron.tank.empty");
	
	public GuiEnergyGenerator(InventoryPlayer IPlayer, TileEntityEnergyGenerator tileEntity) {
		super(new ContainerEnergyGenerator(IPlayer, tileEntity));
		tile = tileEntity;
		elements.add(flame);
		elements.add(power);
		elements.add(tank);
		playerInventory = IPlayer;
	}

	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		super.drawScreen(mouseX, mouseY, par3);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		power.updateDynamicList(getPowerTipList());
		tank.updateDynamicList(getTankTipList());
		String t = tile.getRemainingTime();
		if(t != null)
			flame.updateDynamicList(Integer.toString(tile.getProgress()) + "%", t);
		else
			flame.updateDynamicList(Integer.toString(tile.getProgress()) + "%");
		
		for(GuiElement e : elements) {
			if(mouseX >= k+e.x && mouseX <= k+e.x+e.width && mouseY >= l+e.y && mouseY <= l+e.y + e.height) {
				drawHoveringText(e.getHoverText(), mouseX, mouseY);
			}
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = tile.getName();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96+4, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		drawProgressVertical(tile.getProgress(), flame); //Flame
		drawProgressVertical(tile.getStoredEnergyPercentage(), power); //Powerbar
		drawFluid(tile.getFluid(), tile.getStoredFluidPercentage(), tank);
	}	
	
	private List<String> getPowerTipList(){
		List<String> powerList = new ArrayList<String>();
		powerList.add(Integer.toString(tile.getStoredEnergy()) + "RF");
		powerList.add(Integer.toString(tile.getStoredEnergyPercentage()) + "%");
		if(tile.isOn()) {
			TextComponentString text = new TextComponentString("+" + Integer.toString(tile.getEnergyPerTick())+"RF/t");
			text.getStyle().setColor(TextFormatting.GREEN);
			powerList.add(text.getFormattedText());
		}else {
			TextComponentString text = new TextComponentString("+0RF/t");
			text.getStyle().setColor(TextFormatting.RED);
			powerList.add(text.getFormattedText());
		}
		return powerList;
	}
	
	private List<String> getTankTipList(){
		List<String> tankList = new ArrayList<String>();
		if(tile.getTank().getFluid() == null) {
			tankList.add(empty.getFormattedText());
		}
		else {
			tankList.add(tile.getTank().getFluid().getLocalizedName());
		}
		tankList.add(Integer.toString(tile.getStoredFluid())+"mB");
		tankList.add(Integer.toString(tile.getStoredFluidPercentage()) + "%");
		if(tile.isOn())
			tankList.add("-" + Double.toString(tile.getFuelUsePerTick())+"mB/t");
		else
			tankList.add("-0mB/t");
		return tankList;
	}
}