package com.black_dog20.gadgetron.client.gui.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiElement {

	public int x;
	public int y;
	public int height;
	public int width;
	public int textureX;
	public int textureY;
	private List<String> staticText = new ArrayList<String>();
	private List<String> dynamicText = new ArrayList<String>();
	private String name;
	
	public GuiElement(String name, int x, int y, int height, int width, int textureX, int textureY, String... args) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.textureX = textureX;
		this.textureY = textureY;
		for(String arg : args) {
			staticText.add(I18n.format(arg));
		}
	}
	
	public void updateDynamicList(String... args) {
		dynamicText = new ArrayList<String>();
		for(String arg : args) {
			dynamicText.add(arg);
		}
	}
	
	public List<String> getHoverText(){
		ArrayList<String> temp = new ArrayList<String>(staticText);
		temp.addAll(dynamicText);
		return temp;
	}
	
	public String getName() {
		return name;
	}
}
