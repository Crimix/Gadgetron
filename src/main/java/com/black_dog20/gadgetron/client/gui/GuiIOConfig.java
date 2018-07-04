package com.black_dog20.gadgetron.client.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.client.gui.utils.GuiCustomButton;
import com.black_dog20.gadgetron.client.gui.utils.GuiElement;
import com.black_dog20.gadgetron.container.ContainerIOConfig;
import com.black_dog20.gadgetron.network.PacketHandler;
import com.black_dog20.gadgetron.network.message.MessageOpenGuiOnServer;
import com.black_dog20.gadgetron.tile.base.TileEntityBase;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIOConfig extends GuiContainerBase {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/empty.png");
	private final EntityPlayer player;
	private GuiCustomButton inventoryTop = new GuiCustomButton(1, "", 150, 0,null);
	private GuiCustomButton inventoryFront = new GuiCustomButton(2, "", 150, 0,null);
	private GuiCustomButton inventoryLeft = new GuiCustomButton(3, "", 150, 0,null);
	private GuiCustomButton inventoryRight = new GuiCustomButton(4, "", 150, 0,null);
	private GuiCustomButton inventoryBack = new GuiCustomButton(5, "", 150, 0,null);
	private GuiCustomButton inventoryAutoI = new GuiCustomButton(4, "", 150, 0,null);
	private GuiCustomButton inventoryAutoO = new GuiCustomButton(5, "", 150, 0,null);
	
	private GuiCustomButton tankTop = new GuiCustomButton(6, "", 150, 0,null);
	private GuiCustomButton tankFront = new GuiCustomButton(7, "", 150, 0,null);
	private GuiCustomButton tankLeft = new GuiCustomButton(8, "", 150, 0,null);
	private GuiCustomButton tankRight = new GuiCustomButton(9, "", 150, 0,null);
	private GuiCustomButton tankBack = new GuiCustomButton(10, "", 150, 0,null);
	private GuiCustomButton tankAutoI = new GuiCustomButton(11, "", 150, 0,null);
	private GuiCustomButton tankAutoO = new GuiCustomButton(12, "", 150, 0,null);
	
	
	public GuiIOConfig(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerIOConfig(player.inventory, tileEntity), tileEntity, player);
		overrideTile = true;
		this.player = player;
	}

	@Override
	public void initGui()
	{
		super.initGui();
			int x = tile.getPos().getX();
			int y = tile.getPos().getY();
			int z = tile.getPos().getZ();
			elements.add(new GuiCustomButton(0, "x", 150, 0, ()-> PacketHandler.network.sendToServer(new MessageOpenGuiOnServer(0,x,y,z))));
			elements.add(inventoryTop);
			elements.add(inventoryFront);
			elements.add(inventoryLeft);
			elements.add(inventoryRight);
			elements.add(inventoryBack);
			elements.add(inventoryAutoI);
			elements.add(inventoryAutoO);
			elements.add(tankTop);
			elements.add(tankFront);
			elements.add(tankLeft);
			elements.add(tankRight);
			elements.add(tankBack);
			elements.add(tankAutoI);
			elements.add(tankAutoO);

		int k = (this.width - xSizeOfTexture) / 2;
		int l = (this.height - ySizeOfTexture) / 2;

		for(GuiElement e : elements) {
			if(e instanceof GuiCustomButton) {
				GuiCustomButton b = (GuiCustomButton)e;
				this.buttonList.add(new GuiButton(b.getId(), k + b.x, l + b.y, b.width, b.height, b.getName()));
			}
		}
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
		super.drawScreen(mouseX, mouseY, par3);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
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
}
