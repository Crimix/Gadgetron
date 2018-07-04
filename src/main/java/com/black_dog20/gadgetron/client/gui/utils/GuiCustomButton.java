package com.black_dog20.gadgetron.client.gui.utils;

public class GuiCustomButton extends GuiElement{

	private Runnable onClick;
	private int id;
	
	public GuiCustomButton(int id, String name, int x, int y, Runnable onClick, String... args) {
		super(name, x, y, 20, 20, 0, 0, args);
		this.id = id; 
		this.onClick = onClick;
	}
	
	public int getId() {
		return id;
	}
	
	public void execute(int id) {
		if(this.id == id)
			onClick.run();
	}

}
