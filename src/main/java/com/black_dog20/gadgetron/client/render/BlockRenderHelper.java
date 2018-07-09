package com.black_dog20.gadgetron.client.render;

import java.util.Set;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class BlockRenderHelper {

	 public static void renderOutlines(RenderWorldLastEvent evt, EntityPlayerSP p, Set<BlockPos> coordinates, int r, int g, int b, int thickness) {
	        double doubleX = p.lastTickPosX + (p.posX - p.lastTickPosX) * evt.getPartialTicks();
	        double doubleY = p.lastTickPosY + (p.posY - p.lastTickPosY) * evt.getPartialTicks();
	        double doubleZ = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) * evt.getPartialTicks();

	        RenderHelper.disableStandardItemLighting();
	        Minecraft.getMinecraft().entityRenderer.disableLightmap();
	        GlStateManager.disableDepth();
	        GlStateManager.disableTexture2D();
	        GlStateManager.disableLighting();
	        GlStateManager.disableAlpha();
	        GlStateManager.depthMask(false);

	        GlStateManager.pushMatrix();
	        GlStateManager.translate(-doubleX, -doubleY, -doubleZ);

	        renderOutlines(p,coordinates, r, g, b, thickness);

	        GlStateManager.popMatrix();

	        Minecraft.getMinecraft().entityRenderer.enableLightmap();
	        GlStateManager.enableTexture2D();
	    }

	    private static void renderOutlines(EntityPlayerSP p, Set<BlockPos> coordinates, int r, int g, int b, int thickness) {
	        Tessellator tessellator = Tessellator.getInstance();

	        VertexBuffer buffer = tessellator.getBuffer();
	        buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
	        GL11.glLineWidth(thickness);

	        for (BlockPos coordinate : coordinates) {
	            float x = coordinate.getX();
	            float y = coordinate.getY();
	            float z = coordinate.getZ();

	            if(p.getPosition().getDistance((int)x, (int)y, (int)z) <= 50)
	            	renderBlock(buffer, x, y, z, r / 255.0f, g / 255.0f, b / 255.0f, 1.0f); // .02f
	        }
	        tessellator.draw();
	    }

	    private static void renderBlock(VertexBuffer buffer, float mx, float my, float mz, float r, float g, float b, float a) {
	        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my, mz).color(r, g, b, a).endVertex();
	        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
	        buffer.pos(mx, my+1, mz).color(r, g, b, a).endVertex();
	        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
	        buffer.pos(mx, my, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my+1, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx, my+1, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my+1, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my+1, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my+1, mz).color(r, g, b, a).endVertex();

	        buffer.pos(mx, my+1, mz).color(r, g, b, a).endVertex();
	        buffer.pos(mx, my+1, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx, my+1, mz).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my+1, mz).color(r, g, b, a).endVertex();

	        buffer.pos(mx+1, my, mz).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my, mz).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my+1, mz).color(r, g, b, a).endVertex();

	        buffer.pos(mx, my, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx+1, my, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx, my, mz+1).color(r, g, b, a).endVertex();
	        buffer.pos(mx, my+1, mz+1).color(r, g, b, a).endVertex();
	    }


}
