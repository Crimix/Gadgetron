package com.black_dog20.gadgetron.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.client.gui.utils.GuiElement;
import com.black_dog20.gadgetron.container.ContainerSmelter;
import com.black_dog20.gadgetron.tile.TileEntitySmelter;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSmelter extends GuiContainerBase {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/smelter.png");
	private TileEntitySmelter te;
	private final EntityPlayer player;
	private GuiElement power = new GuiElement("powerbar", 6, 10, 62, 19, 176, 95, I18n.format("gadgetron.container.energystored"));
	private GuiElement arrow = new GuiElement("arrow", 68, 34, 17, 24, 176, 14);
	private GuiElement tank = new GuiElement("fluid", 131, 9, 63, 16, 0, 0);
	private String empty = I18n.format("gadgetron.tank.empty");
	
	public GuiSmelter(EntityPlayer player, TileEntitySmelter tileEntity) {
		super(new ContainerSmelter(player.inventory, tileEntity), tileEntity, player);
		elements.add(power);
		elements.add(tank);
		elements.add(arrow);
		this.te = (TileEntitySmelter) tile;
		this.player = player;
	}

	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		power.updateDynamicList(getPowerTipList());	
		tank.updateDynamicList(getTankTipList());
		String t = te.getRemainingTime();
		if(t != null)
			arrow.updateDynamicList(Integer.toString(te.getProgress()) + "%", t);
		else
			arrow.updateDynamicList(Integer.toString(te.getProgress()) + "%");
		super.drawScreen(mouseX, mouseY, par3);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		String s = I18n.format("gadgetron.container.smelter");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.player.inventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96+4, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		drawProgressVertical(te.getStoredEnergyPercentage(), power); //Powerbar
		drawProgressHorizontal(te.getProgress(), arrow);
		drawFluid(te.getFluid(), te.getStoredFluidPercentage(), tank);
	}	
	
	private List<String> getPowerTipList(){
		List<String> powerList = new ArrayList<String>();
		powerList.add(getFormattedInt(te.getStoredEnergy()) + "RF");
		powerList.add(Integer.toString(te.getStoredEnergyPercentage()) + "%");
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
		return tankList;
	}
}