package com.black_dog20.gadgetron.client.gui;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.client.gui.utils.GuiCustomButton;
import com.black_dog20.gadgetron.client.gui.utils.GuiCustomCheckBox;
import com.black_dog20.gadgetron.container.ContainerIOConfig;
import com.black_dog20.gadgetron.network.PacketHandler;
import com.black_dog20.gadgetron.network.message.MessageOpenGuiOnServer;
import com.black_dog20.gadgetron.network.message.MessageUpdateAutoIO;
import com.black_dog20.gadgetron.network.message.MessageUpdateFaceConfig;
import com.black_dog20.gadgetron.tile.base.TileEntityBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyFluidBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryFluidBase;
import com.black_dog20.gadgetron.utility.Automation;
import com.black_dog20.gadgetron.utility.FaceId;
import com.black_dog20.gadgetron.utility.Varient;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
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
	private GuiButton inventoryBottom;
	private GuiButton inventoryAutoI;
	private GuiButton inventoryAutoO;
	
	private GuiButton tankTop; 
	private GuiButton tankFront;
	private GuiButton tankLeft;
	private GuiButton tankRight; 
	private GuiButton tankBack; 
	private GuiButton tankBottom;
	private GuiButton tankAutoI;
	private GuiButton tankAutoO;
	
	
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
		this.buttonList.add(new GuiCustomButton(0, "x", k+160, l-10, 20,0.8, ()-> PacketHandler.network.sendToServer(new MessageOpenGuiOnServer(0,x,y,z)), I18n.format("gadgetron.container.close")));

		int standardX = 50;
		int standardY = 0;
		int heightButton = 20;
		int widthButton = 20;
		int buttonOffset = 4;
		double scale = 0.75;
		
		inventoryTop = new GuiCustomButton(1, "", k+standardX, l+standardY, widthButton, scale, sendUpdate(FaceId.TOP, Varient.IVENTORY), I18n.format("gadgetron.container.top"));
		inventoryFront = new GuiCustomButton(2, "", k+standardX, l+standardY+(heightButton-buttonOffset), widthButton, scale, sendUpdate(FaceId.FRONT, Varient.IVENTORY), I18n.format("gadgetron.container.front"));
		inventoryLeft = new GuiCustomButton(3, "", k+standardX-(widthButton-buttonOffset), l+standardY+(heightButton-buttonOffset), widthButton, scale, sendUpdate(FaceId.LEFT, Varient.IVENTORY), I18n.format("gadgetron.container.left"));
		inventoryRight = new GuiCustomButton(4, "", k+standardX+(widthButton-buttonOffset), l+standardY+(heightButton-buttonOffset), widthButton, scale, sendUpdate(FaceId.RIGHT, Varient.IVENTORY), I18n.format("gadgetron.container.right"));
		inventoryBack = new GuiCustomButton(5, "", k+standardX+(widthButton-buttonOffset), l+standardY+(2*(heightButton-buttonOffset)), widthButton, scale, sendUpdate(FaceId.BACK, Varient.IVENTORY), I18n.format("gadgetron.container.back"));
		inventoryBottom = new GuiCustomButton(6, "", k+standardX, l+standardY+(2*(heightButton-buttonOffset)), widthButton, scale, sendUpdate(FaceId.BOTTOM, Varient.IVENTORY), I18n.format("gadgetron.container.bottom")); 
		inventoryAutoI = new GuiCustomCheckBox(7, I18n.format("gadgetron.container.input"), k+standardX-(int)Math.ceil(widthButton*1.5), l+standardY+((int)Math.ceil(heightButton*1.4)-buttonOffset)-(heightButton/2), scale, sendAuto(Automation.INPUT, Varient.IVENTORY), true, I18n.format("gadgetron.container.autoinput"));
		inventoryAutoO = new GuiCustomCheckBox(8, I18n.format("gadgetron.container.output"), k+standardX-(int)Math.ceil(widthButton*1.5), l+standardY+(heightButton-buttonOffset)+(heightButton/2), scale, sendAuto(Automation.OUTPUT, Varient.IVENTORY), false, I18n.format("gadgetron.container.autooutput"));
		
		standardX = 130;
		tankTop = new GuiCustomButton(8, "", k+standardX, l+standardY, widthButton, scale, sendUpdate(FaceId.TOP, Varient.TANK), I18n.format("gadgetron.container.top"));
		tankFront = new GuiCustomButton(9, "", k+standardX, l+standardY+(heightButton-buttonOffset), widthButton, scale, sendUpdate(FaceId.FRONT, Varient.TANK), I18n.format("gadgetron.container.front"));
		tankLeft = new GuiCustomButton(10, "", k+standardX-(widthButton-buttonOffset), l+standardY+(heightButton-buttonOffset), widthButton, scale, sendUpdate(FaceId.LEFT, Varient.TANK), I18n.format("gadgetron.container.left"));
		tankRight = new GuiCustomButton(11, "", k+standardX+(widthButton-buttonOffset), l+standardY+(heightButton-buttonOffset), widthButton, scale, sendUpdate(FaceId.RIGHT, Varient.TANK), I18n.format("gadgetron.container.right"));
		tankBack = new GuiCustomButton(12, "", k+standardX+(widthButton-buttonOffset), l+standardY+(2*(heightButton-buttonOffset)), widthButton, scale, sendUpdate(FaceId.BACK, Varient.TANK), I18n.format("gadgetron.container.back"));
		tankBottom = new GuiCustomButton(13, "", k+standardX, l+standardY+(2*(heightButton-buttonOffset)), widthButton, scale, sendUpdate(FaceId.BOTTOM, Varient.TANK), I18n.format("gadgetron.container.bottom")); 
		tankAutoI = new GuiCustomCheckBox(14, I18n.format("gadgetron.container.input"), k+standardX-(int)Math.ceil(widthButton*1.5), l+standardY+((int)Math.ceil(heightButton*1.4)-buttonOffset)-(heightButton/2), scale, sendAuto(Automation.INPUT, Varient.TANK), true, I18n.format("gadgetron.container.autoinput"));
		tankAutoO = new GuiCustomCheckBox(15, I18n.format("gadgetron.container.output"), k+standardX-(int)Math.ceil(widthButton*1.5), l+standardY+(heightButton-buttonOffset)+(heightButton/2), scale,sendAuto(Automation.OUTPUT, Varient.TANK), false, I18n.format("gadgetron.container.autooutput"));
		
		if(tile.hasInventory()) {
			this.buttonList.add(inventoryTop);
			this.buttonList.add(inventoryFront);
			this.buttonList.add(inventoryLeft);
			this.buttonList.add(inventoryRight);
			this.buttonList.add(inventoryBack);
			this.buttonList.add(inventoryBottom);
			this.buttonList.add(inventoryAutoI);
			this.buttonList.add(inventoryAutoO);
		}
		
		if(tile.hasTank()) {
			this.buttonList.add(tankTop);
			this.buttonList.add(tankFront);
			this.buttonList.add(tankLeft);
			this.buttonList.add(tankRight);
			this.buttonList.add(tankBack);
			this.buttonList.add(tankBottom);
			this.buttonList.add(tankAutoI);
			this.buttonList.add(tankAutoO);
		}
		
	}
	
	private Runnable sendUpdate(FaceId id, Varient varient) {
		return ()-> PacketHandler.network.sendToServer(new MessageUpdateFaceConfig(id, varient, tile.getPos()));
	}
	
	private Runnable sendAuto(Automation id, Varient varient) {
		return ()-> PacketHandler.network.sendToServer(new MessageUpdateAutoIO(id, varient, tile.getPos()));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		updateTextOnButtons();
		super.drawScreen(mouseX, mouseY, par3);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = I18n.format("gadgetron.container.items");
        String ss = I18n.format("gadgetron.container.fluid");
        if(tile.hasInventory())
        	this.fontRenderer.drawString(s, (this.xSize / 3)-10- this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        if(tile.hasTank())
        	this.fontRenderer.drawString(ss, (2*(this.xSize / 3))+12- this.fontRenderer.getStringWidth(ss) / 2, 6, 4210752);
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
		if(tile instanceof TileEntityEnergyInventoryBase) {
			TileEntityEnergyInventoryBase te = (TileEntityEnergyInventoryBase) tile;
			inventoryTop.displayString = te.inventoryFaces.getButtonState(FaceId.TOP);
			inventoryFront.displayString = te.inventoryFaces.getButtonState(FaceId.FRONT);
			inventoryLeft.displayString = te.inventoryFaces.getButtonState(FaceId.LEFT);
			inventoryRight.displayString = te.inventoryFaces.getButtonState(FaceId.RIGHT);
			inventoryBack.displayString = te.inventoryFaces.getButtonState(FaceId.BACK);
			inventoryBottom.displayString = te.inventoryFaces.getButtonState(FaceId.BOTTOM);
			((GuiCustomCheckBox)inventoryAutoI).visible = te.inventoryFaces.hasInputSlots() && te.inventoryFaces.canAutoInput();
			((GuiCustomCheckBox)inventoryAutoI).setIsChecked(te.inventoryFaces.isAutoInput());
			((GuiCustomCheckBox)inventoryAutoO).visible = te.inventoryFaces.hasOutputSlots() && te.inventoryFaces.canAutoOutput();
			((GuiCustomCheckBox)inventoryAutoO).setIsChecked(te.inventoryFaces.isAutoOutput());
		}
		if(tile instanceof TileEntityEnergyFluidBase) {
			TileEntityEnergyFluidBase te = (TileEntityEnergyFluidBase) tile;
			tankTop.displayString = te.tankFaces.getButtonState(FaceId.TOP);
			tankFront.displayString = te.tankFaces.getButtonState(FaceId.FRONT);
			tankLeft.displayString = te.tankFaces.getButtonState(FaceId.LEFT);
			tankRight.displayString = te.tankFaces.getButtonState(FaceId.RIGHT);
			tankBack.displayString = te.tankFaces.getButtonState(FaceId.BACK);
			tankBottom.displayString = te.tankFaces.getButtonState(FaceId.BOTTOM);
			((GuiCustomCheckBox)tankAutoI).setIsChecked(te.tankFaces.isAutoInput());
			((GuiCustomCheckBox)tankAutoI).visible = te.tankFaces.hasInputSlots() && te.tankFaces.canAutoInput();
			((GuiCustomCheckBox)tankAutoO).setIsChecked(te.tankFaces.isAutoOutput());
			((GuiCustomCheckBox)tankAutoO).visible = te.tankFaces.hasOutputSlots() && te.tankFaces.canAutoOutput();
		}else if(tile instanceof TileEntityEnergyInventoryFluidBase) {
			TileEntityEnergyInventoryFluidBase te = (TileEntityEnergyInventoryFluidBase) tile;
			tankTop.displayString = te.tankFaces.getButtonState(FaceId.TOP);
			tankFront.displayString = te.tankFaces.getButtonState(FaceId.FRONT);
			tankLeft.displayString = te.tankFaces.getButtonState(FaceId.LEFT);
			tankRight.displayString = te.tankFaces.getButtonState(FaceId.RIGHT);
			tankBack.displayString = te.tankFaces.getButtonState(FaceId.BACK);
			tankBottom.displayString = te.tankFaces.getButtonState(FaceId.BOTTOM);
			((GuiCustomCheckBox)tankAutoI).setIsChecked(te.tankFaces.isAutoInput());
			((GuiCustomCheckBox)tankAutoI).visible = te.tankFaces.hasInputSlots() && te.tankFaces.canAutoInput();
			((GuiCustomCheckBox)tankAutoO).setIsChecked(te.tankFaces.isAutoOutput());
			((GuiCustomCheckBox)tankAutoO).visible = te.tankFaces.hasOutputSlots() && te.tankFaces.canAutoOutput();
		}
	}
}
