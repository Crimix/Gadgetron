package com.black_dog20.gadgetron.client.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.black_dog20.gadgetron.api.GadgetronAPI;
import com.black_dog20.gadgetron.api.ISpecialEquipment.SpecialEquipmentType;
import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.client.gui.utils.GuiLeftCheckBox;
import com.black_dog20.gadgetron.container.ContainerBelt;
import com.black_dog20.gadgetron.network.PacketHandler;
import com.black_dog20.gadgetron.network.message.MessageUpdateVisibleState;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBelt extends GuiContainer {
	private static final ResourceLocation gui = new ResourceLocation("gadgetron:textures/gui/belt.png");
	protected final int xSizeOfTexture = 192 , ySizeOfTexture = 135;
	private final EntityPlayer player;
	private GuiLeftCheckBox checkbox;
	private int currentTick = 1;
	private int tickBeforeSwitch = 400;
	private int currentItem = 0;
	private int currentItemMED = 0;
	
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
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
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
	
	private void renderItemIntoGui(List<Item> list, int slot, int currentItem, int xPos, int yPos) {
		
		if(list.size() == 0 || list.size() <= currentItem)
			return;
		
		EntityPlayer player = Minecraft.getMinecraft().player;
		
		IBeltHandler bh = BeltHandler.instanceFor(player);
		if(bh != null) {
			if(!bh.getInventory().getStackInSlot(slot).isEmpty())
				return;
		}
		
		ItemStack stack = new ItemStack(list.get(currentItem));		

		GlStateManager.pushMatrix();
        RenderItem renderitem = Minecraft.getMinecraft().getRenderItem();
        renderitem.renderItemAndEffectIntoGUI(stack, xPos, yPos);
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        this.mc.getTextureManager().bindTexture(gui);
        GlStateManager.depthFunc(516);
        this.drawTexturedModalRect(xPos, yPos, 176, 18, 16, 16);
        GlStateManager.depthFunc(515);	
        GlStateManager.popMatrix();
        currentTick++;
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		if(!GadgetronAPI.doesEquipmentListContainType(SpecialEquipmentType.MAGNET)) {
			this.drawTexturedModalRect(k + 67, l + 4, 176, 0, 18, 18);
		}
		if(!GadgetronAPI.doesEquipmentListContainType(SpecialEquipmentType.MED)) {
			this.drawTexturedModalRect(k + 91, l + 4, 176, 0, 18, 18);
		}
		
		if(GadgetronAPI.doesEquipmentListContainType(SpecialEquipmentType.MAGNET)) {
			renderItemIntoGui(GadgetronAPI.getEquipmentList(SpecialEquipmentType.MAGNET), 27, currentItem, k + 68, l+5);
		}
		if(GadgetronAPI.doesEquipmentListContainType(SpecialEquipmentType.MED)) {
			renderItemIntoGui(GadgetronAPI.getEquipmentList(SpecialEquipmentType.MED), 28, currentItemMED, k+92, l+5);
		}
		
		if(currentTick % tickBeforeSwitch == 0) {
			if(GadgetronAPI.getEquipmentList(SpecialEquipmentType.MAGNET).size() == (currentItem+1))
				currentItem = 0;
			else
				currentItem++;
			
			if(GadgetronAPI.getEquipmentList(SpecialEquipmentType.MED).size() == (currentItemMED+1))
				currentItemMED = 0;
			else
				currentItemMED++;
			
			currentTick = 0;
		}
		
		currentTick++;
		
	}	
}