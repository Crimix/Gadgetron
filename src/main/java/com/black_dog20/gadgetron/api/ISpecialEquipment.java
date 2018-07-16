package com.black_dog20.gadgetron.api;

import net.minecraft.entity.player.EntityPlayer;

public interface ISpecialEquipment {

	SpecialEquipmentType getype();

	public enum SpecialEquipmentType{
		MAGNET(1),
		MED(2);
		
		private final int value;

	    private SpecialEquipmentType(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	void onWornTick(EntityPlayer player);
}
