package com.black_dog20.gadgetron.client.gui.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.client.config.GuiCheckBox;
import net.minecraftforge.fml.client.config.GuiUtils;

public class GuiLeftCheckBox extends GuiCheckBox {
	
	private int boxWidth = 11;

	public GuiLeftCheckBox(int buttonId, int x, int y, String buttonText, boolean checked) {
		super(buttonId, x, y, buttonText, checked);
	}

	@Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.boxWidth  && mouseY < this.y + this.height;
            GuiUtils.drawContinuousTexturedBox(BUTTON_TEXTURES, this.x, this.y, 0, 46, this.boxWidth, this.height, 200, 20, 2, 3, 2, 2, this.zLevel);
            this.mouseDragged(mc, mouseX, mouseY);
            int color = 14737632;

            if (packedFGColour != 0)
            {
                color = packedFGColour;
            }
            else if (!this.enabled)
            {
                color = 10526880;
            }

            if (this.isChecked())
                this.drawCenteredString(mc.fontRenderer, "x", this.x + this.boxWidth / 2 + 1, this.y + 1, 14737632);

            GlStateManager.pushMatrix();
            double scale = 0.75;
            GlStateManager.scale(scale, scale, scale);
            int lenght = Minecraft.getMinecraft().fontRenderer.getStringWidth(displayString);
    		int sX = (int) Math.ceil((double)x / scale);
    		int sY = (int) Math.ceil((double)y / scale);
            this.drawString(mc.fontRenderer, displayString, sX - lenght - (this.boxWidth / 2)+3, sY + 3, color);
            GlStateManager.popMatrix();
        }
    }

}
