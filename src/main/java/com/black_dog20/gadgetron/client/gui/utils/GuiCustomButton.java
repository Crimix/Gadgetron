package com.black_dog20.gadgetron.client.gui.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiCustomButton extends GuiButton {

	private double scale;
	private int myX;
	private int myY;
	private Runnable onClick;
	private List<String> tips = new ArrayList<String>();
	
	public GuiCustomButton(int buttonId, String buttonText, int x, int y, int widthIn, double scale, Runnable onClick, String... info) {
		super(buttonId, x, y, widthIn, 20, buttonText);
		this.scale = scale;
		myX = x;
		myY = y;
		this.onClick = onClick;
		for(String s : info)
			tips.add(s);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY,float partialTicks)
    {
		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);
		super.x = (int) Math.ceil((double)myX / scale);
		super.y = (int) Math.ceil((double)myY / scale);
		int mouseXC = (int) Math.ceil((double)mouseX / scale);
		int mouseYC = (int) Math.ceil((double)mouseY / scale);
		super.drawButton(mc,mouseXC, mouseYC,partialTicks);
		GlStateManager.popMatrix();
    }
	
	@Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
		int mouseXC = (int) Math.ceil((double)mouseX / scale);
		int mouseYC = (int) Math.ceil((double)mouseY / scale);
		return super.mousePressed(mc,mouseXC, mouseYC);
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
