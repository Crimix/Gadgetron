package com.black_dog20.gadgetron.client.gui.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.client.config.GuiCheckBox;
import net.minecraftforge.fml.client.config.GuiUtils;

public class GuiCustomCheckBox extends GuiCheckBox {

	private double scale;
	private int myX;
	private int myY;
	private Runnable onClick;
	private List<String> tips = new ArrayList<String>();
	private boolean input;
	private int boxwidth = 11;
	
	public GuiCustomCheckBox(int buttonId, String buttonText, int x, int y, double scale, Runnable onClick, boolean input, String... info) {
		super(buttonId, x, y, buttonText, false);
		this.scale = scale;
		myX = x;
		myY = y;
		this.input = input;
		this.onClick = onClick;
		for(String s : info)
			tips.add(s);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);
		super.x = (int) Math.ceil((double)myX / scale);
		super.y = (int) Math.ceil((double)myY / scale);
		int mouseXC = (int) Math.ceil((double)mouseX / scale);
		int mouseYC = (int) Math.ceil((double)mouseY / scale);
		this.cDraw(mc, mouseXC, mouseYC);
		GlStateManager.popMatrix();
    }
	
	private void cDraw(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible)
        {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + 11 && mouseY < this.y + this.height;
            GuiUtils.drawContinuousTexturedBox(BUTTON_TEXTURES, this.x, this.y, 0, 46, 11, this.height, 200, 20, 2, 3, 2, 2, this.zLevel);
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
                this.drawCenteredString(mc.fontRenderer, "x", this.x + 11 / 2 + 1, this.y + 1, 14737632);

            if(!input)
            	this.drawString(mc.fontRenderer, displayString, x-9, y + 11 + 2, color);
            else
            	this.drawString(mc.fontRenderer, displayString, x-9, y - 10, color);
        }
	}
	
	@Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
		int mouseXC = (int) Math.ceil((double)mouseX / scale);
		int mouseYC = (int) Math.ceil((double)mouseY / scale);
        if (this.enabled && this.visible && mouseXC >= this.x && mouseYC >= this.y && mouseXC < this.x + this.boxwidth  && mouseYC < this.y + this.height)
        {
            setIsChecked(!isChecked());
            return true;
        }

        return false;
    }
	
	public void execute(int id) {
		if(this.id == id)
			if(onClick != null)
				onClick.run();
	}
	
	public List<String> getHoverText(){
		return tips;
	}

}
