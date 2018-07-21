package com.black_dog20.gadgetron.recipehandler;

public class OreInput {

	private String input = "";
	private int amount = 1;
	
	public OreInput(String input, int amount) {
		this.input = input;
		this.amount = amount;
	}
	
	public OreInput(String input) {
		this.input = input;
		this.amount = 1;
	}
	
	public String getInput() {
		return input;
	}
	
	public int getAmount() {
		return amount;
	}
	
	@Override
	public String toString() {
		return String.format("%s %d", input, amount);
	}
}