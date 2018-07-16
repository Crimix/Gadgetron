package com.black_dog20.gadgetron.client.gui;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.client.gui.utils.GuiLeftCheckBox;
import com.black_dog20.gadgetron.container.ContainerBelt;
import com.black_dog20.gadgetron.network.PacketHandler;
import com.black_dog20.gadgetron.network.message.MessageUpdateVisibleState;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBelt extends GuiContainer {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/belt.png");
	protected final int xSizeOfTexture = 192 , ySizeOfTexture = 135;
	private final EntityPlayer player;
	private GuiLeftCheckBox checkbox;
	
	public GuiBelt(EntityPlayer player) {
		super(new ContainerBelt(player.inventory,player));
		this.player = player;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		int k = (this.width - xSizeOfTexture) / 2;
		int l = (this.height - ySizeOfTexture) / 2;
		IBeltHandler mh = BeltHandler.instanceFor(player);
		if(mh != null){
			checkbox = new GuiLeftCheckBox(0, k+168, l-10, I18n.format("gadgetron.container.visible"), mh.isVisible());
			this.buttonList.add(checkbox);
		}
	}
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		switch (button.id) {
		case 0:
			IBeltHandler mh = BeltHandler.instanceFor(player);
			if(mh != null){
				PacketHandler.network.sendToServer(new MessageUpdateVisibleState());
			}
			break;

		default:
			break;
		}
	}

	
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float par3) {
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
		if(!Loader.isModLoaded("gadgetronig")) {
			this.drawTexturedModalRect(k + 67, l + 4, 176, 0, 18, 18);
		}
		if(!Loader.isModLoaded("gadgetronmt")) {
			this.drawTexturedModalRect(k + 91, l + 4, 176, 0, 18, 18);
		}
	}	
}