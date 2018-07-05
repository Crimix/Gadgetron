package com.black_dog20.gadgetron.client.gui;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.client.gui.utils.GuiCustomButton;
import com.black_dog20.gadgetron.client.gui.utils.GuiElement;
import com.black_dog20.gadgetron.container.ContainerIOConfig;
import com.black_dog20.gadgetron.network.PacketHandler;
import com.black_dog20.gadgetron.network.message.MessageOpenGuiOnServer;
import com.black_dog20.gadgetron.tile.base.TileEntityBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryFluidBase;
import com.black_dog20.gadgetron.utility.MachineFaces;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIOConfig extends GuiContainerBase {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/empty.png");
	private final EntityPlayer player;
	
	private GuiButton inventoryTop; 
	private GuiButton inventoryFront;
	private GuiButton inventoryLeft;
	private GuiButton inventoryRight; 
	private GuiButton inventoryBack; 
	private GuiButton inventoryAutoI;
	private GuiButton inventoryAutoO;
	
/*	private final GuiButton tankTop = new GuiCustomButton(6, "", 150, 0,null);
	private final GuiButton tankFront = new GuiCustomButtonElement(7, "", 150, 0,null);
	private final GuiButton tankLeft = new GuiCustomButtonElement(8, "", 150, 0,null);
	private final GuiButton tankRight = new GuiCustomButtonElement(9, "", 150, 0,null);
	private final GuiButton tankBack = new GuiCustomButtonElement(10, "", 150, 0,null);
	private final GuiButton tankAutoI = new GuiCustomButtonElement(11, "", 150, 0,null);
	private final GuiButton tankAutoO = new GuiCustomButtonElement(12, "", 150, 0,null);*/
	
	
	public GuiIOConfig(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerIOConfig(player.inventory, tileEntity), tileEntity, player);
		overrideTile = true;
		this.player = player;
	}

	@Override
	public void initGui()
	{
		super.initGui();

		this.buttonList.clear();

		int k = (this.width - xSizeOfTexture) / 2;
		int l = (this.height - ySizeOfTexture) / 2;

		int x = tile.getPos().getX();
		int y = tile.getPos().getY();
		int z = tile.getPos().getZ();
		this.buttonList.add(new GuiCustomButton(0, "x", k+150, l+0, 20,0.8, ()-> PacketHandler.network.sendToServer(new MessageOpenGuiOnServer(0,x,y,z))));

		int standardX = 50;
		int standardY = 0;
		int heightButton = 20;
		int widthButton = 20;
		int buttonOffset = 4;
		double scale = 0.75;
		
		inventoryTop = new GuiCustomButton(1, "", k+standardX, l+standardY, widthButton, scale, null);
		inventoryFront = new GuiCustomButton(2, "", k+standardX, l+standardY+(heightButton-buttonOffset), widthButton,  scale,null);
		inventoryLeft = new GuiCustomButton(3, "", k+standardX-(widthButton-buttonOffset), l+standardY+(heightButton-buttonOffset), widthButton, scale,null);
		inventoryRight = new GuiCustomButton(4, "", k+standardX+(widthButton-buttonOffset), l+standardY+(heightButton-buttonOffset), widthButton, scale,null);
		inventoryBack = new GuiCustomButton(5, "", k+standardX+(widthButton-buttonOffset), l+standardY+(2*(heightButton-buttonOffset)), widthButton, scale,null);
		inventoryAutoI = new GuiCustomButton(6, "", k+standardX-(int)Math.ceil(widthButton*1.7), l+standardY+(heightButton-buttonOffset)-(heightButton/2), widthButton, scale,null);
		inventoryAutoO = new GuiCustomButton(7, "", k+standardX-(int)Math.ceil(widthButton*1.7), l+standardY+(heightButton-buttonOffset)+(heightButton/2), widthButton, scale,null);
		
		this.buttonList.add(inventoryTop);
		this.buttonList.add(inventoryFront);
		this.buttonList.add(inventoryLeft);
		this.buttonList.add(inventoryRight);
		this.buttonList.add(inventoryBack);
		this.buttonList.add(inventoryAutoI);
		this.buttonList.add(inventoryAutoO);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		updateTextOnButtons();
		super.drawScreen(mouseX, mouseY, par3);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = tile.getName();
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
	}
	
	private void updateTextOnButtons() {
		if(tile instanceof TileEntityEnergyInventoryBase || tile instanceof TileEntityEnergyInventoryFluidBase) {
			TileEntityEnergyInventoryBase te = (TileEntityEnergyInventoryBase) tile;
			inventoryTop.displayString = te.inventoryFaces.getButtonState(MachineFaces.Id.TOP);
			inventoryFront.displayString = te.inventoryFaces.getButtonState(MachineFaces.Id.FRONT);
			inventoryLeft.displayString = te.inventoryFaces.getButtonState(MachineFaces.Id.LEFT);
			inventoryRight.displayString = te.inventoryFaces.getButtonState(MachineFaces.Id.RIGHT);
			inventoryBack.displayString = te.inventoryFaces.getButtonState(MachineFaces.Id.BACK);
		}
	}
}
