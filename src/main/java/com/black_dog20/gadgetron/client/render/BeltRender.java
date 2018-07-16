package com.black_dog20.gadgetron.client.render;


import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.client.model.Belt;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BeltRender implements LayerRenderer<EntityPlayer>{

	@Override
	public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		IBeltHandler mh = player.getCapability(BeltHandler.CAP, null);
		if(mh != null && mh.getHasBelt() && mh.isVisible()) {
			Belt model = new Belt();
			float Myscale = 1F / 2.2F;
			GlStateManager.pushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("gadgetron:textures/models/belt.png"));
			if(player.isSneaking()){
				GlStateManager.translate(0.03F, 0.6F, 0.25F);
				GlStateManager.rotate(90F / (float) Math.PI, 1.0F, 0.0F, 0.0F);
			}else {
				GlStateManager.translate(0.03F, 0.5F, 0.032F);
			}
			GlStateManager.scale(Myscale, Myscale, Myscale);
			model.render(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.1, 0.1, 0.1);
			if(player.isSneaking()){
				GlStateManager.translate(-0.1F, 7F, 1.1F);
				GlStateManager.rotate(90F / (float) Math.PI, 1.0F, 0.0F, 0.0F);
			}
			else {
				GlStateManager.translate(-0.1F, 5.3F, -1.4F);
			}
			ItemStack stack = mh.getInventory().getStackInSlot(27);
			if(stack!= null && !stack.isEmpty())
				Minecraft.getMinecraft().getRenderItem().renderItem(stack , ItemCameraTransforms.TransformType.FIXED);
			GlStateManager.popMatrix();
		}
		
	}

	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}

}
