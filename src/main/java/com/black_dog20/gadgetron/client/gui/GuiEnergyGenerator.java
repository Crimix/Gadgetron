package com.black_dog20.gadgetron.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.client.gui.utils.GuiElement;
import com.black_dog20.gadgetron.container.ContainerEnergyGenerator;
import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnergyGenerator extends GuiContainerBase {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/generator.png");
	private TileEntityEnergyGenerator te;
	private final EntityPlayer player;
	private GuiElement flame = new GuiElement("flame", 80, 36, 12, 14, 176, 12, I18n.format("gadgetron.container.progress"));
	private GuiElement power = new GuiElement("powerbar", 6, 10, 62, 19, 176, 95, I18n.format("gadgetron.container.energystored"));
	private GuiElement tank = new GuiElement("fluid", 30, 9, 63, 16, 0, 0);
	private String empty = I18n.format("gadgetron.tank.empty");
	
	public GuiEnergyGenerator(EntityPlayer player, TileEntityEnergyGenerator tileEntity) {
		super(new ContainerEnergyGenerator(player.inventory, tileEntity), tileEntity, player);
		te = (TileEntityEnergyGenerator) tile;
		elements.add(flame);
		elements.add(power);
		elements.add(tank);
		this.player = player;
	}

	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		power.updateDynamicList(getPowerTipList());
		tank.updateDynamicList(getTankTipList());
		String t = te.getRemainingTime();
		if(t != null)
			flame.updateDynamicList(Integer.toString(te.getProgress()) + "%", t);
		else
			flame.updateDynamicList(Integer.toString(te.getProgress()) + "%");
		super.drawScreen(mouseX, mouseY, par3);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		String s = I18n.format("gadgetron.container.energy_generator");
		String generate = String.format("%s%s", te.getGeneratePerTick(), "RF/t");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.player.inventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96+4, 4210752);
        this.fontRenderer.drawString(generate, 100, 40, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		if(te.isOn())
			drawProgressVertical(100-te.getProgress(), flame); //Flame
		drawProgressVertical(te.getStoredEnergyPercentage(), power); //Powerbar
		drawFluid(te.getFluid(), te.getStoredFluidPercentage(), tank);
	}	
	
	private List<String> getPowerTipList(){
		List<String> powerList = new ArrayList<String>();
		powerList.add(getFormattedInt(te.getStoredEnergy()) + "RF");
		powerList.add(Integer.toString(te.getStoredEnergyPercentage()) + "%");
		if(te.isOn()) {
			TextComponentString text = new TextComponentString("+" + getFormattedInt(te.getEnergyPerTick())+"RF/t");
			text.getStyle().setColor(TextFormatting.GREEN);
			powerList.add(text.getFormattedText());
		}
		return powerList;
	}
	
	private List<String> getTankTipList(){
		List<String> tankList = new ArrayList<String>();
		if(te.getTank().getFluid() == null) {
			tankList.add(empty);
		}
		else {
			tankList.add(te.getTank().getFluid().getLocalizedName());
		}
		tankList.add(getFormattedInt(te.getStoredFluid())+"mB");
		tankList.add(Integer.toString(te.getStoredFluidPercentage()) + "%");
		tankList.add("-" + Double.toString(te.getFuelUsePerTick())+"mB/t");
		return tankList;
	}
}