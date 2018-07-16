package com.black_dog20.gadgetron.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface ISpecialEquipment {

	SpecialEquipmentType getype();

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
	
	public default void onTossEvent(EntityItem tossed, ItemStack calledOn, EntityPlayer player) {}
	
	public default void onPickUpEvent(EntityPlayer player, EntityItem item) {}
	
	public default void onAttackEvent(EntityLivingBase entity, DamageSource source, float amount) {}
	
	public default void onDeathEvent(EntityLivingBase entity, DamageSource source) {}
	
	public default void onHealEvent(EntityLivingBase entity, float amount) {}
	
	public default void onHurtEvent(EntityLivingBase entity, DamageSource source, float amount) {}
	
	public default void onFallEvent(EntityLivingBase entity, float distance, float damageMultiplier) {}
	
	public default void onFallFlyEvent(EntityPlayer player, float distance, float multiplier) {}
	
	public default void onStruckByLightningevent(Entity entity, EntityLightningBolt lightning) {}
	
}
