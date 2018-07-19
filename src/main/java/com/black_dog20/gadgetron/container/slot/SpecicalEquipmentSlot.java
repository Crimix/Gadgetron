package com.black_dog20.gadgetron.container.slot;

import com.black_dog20.gadgetron.api.ISpecialEquipment;
import com.black_dog20.gadgetron.api.ISpecialEquipment.SpecialEquipmentType;
import com.black_dog20.gadgetron.storage.CustomItemHandlerBase;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class SpecicalEquipmentSlot extends SlotItemHandler {

	private SpecialEquipmentType type;
	
	public SpecicalEquipmentSlot(CustomItemHandlerBase inventoryIn, int index, int xPosition, int yPosition, SpecialEquipmentType type) {
		super(inventoryIn, index, xPosition, yPosition);
		this.type = type;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if(stack.getItem() instanceof ISpecialEquipment)
			return ((ISpecialEquipment)stack.getItem()).getType() == type;
		else
			return false;
	}

}
