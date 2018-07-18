package com.black_dog20.gadgetron.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ISpecialEquipment {

	SpecialEquipmentType getType();

	public enum SpecialEquipmentType{
		GENERAL_ITEM(0),
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
	
	public default void onWornTick(ItemStack stack, EntityPlayer player) {}
	
	public default void registerEquipment(ISpecialEquipment item) {
		
	}
}
