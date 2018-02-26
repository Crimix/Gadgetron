package com.black_dog20.gadgetron.api;

public interface IElementType {

	ItemElementType getElementType();

	public enum ItemElementType{
		POSION(1);
		
		private final int value;

	    private ItemElementType(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
}
