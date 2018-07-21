package com.black_dog20.gadgetron.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.black_dog20.gadgetron.api.ISpecialEquipment.SpecialEquipmentType;
import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.storage.CustomItemHandlerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GadgetronAPI {
	
	private static Map<SpecialEquipmentType, ArrayList<Item>> specialEquimentList = new HashMap<SpecialEquipmentType, ArrayList<Item>>();
	
	/**
	 * Checks if the stack is either in the belt inventory or in the players inventory
	 * @param player The player
	 * @param stack The ItemStack
	 * @return True if stack is in either inventory
	 */
	public static boolean isItemStackInInventory(EntityPlayer player, ItemStack stack){
		IBeltHandler bh = BeltHandler.instanceFor(player);
		if(bh != null) {
			CustomItemHandlerBase inventory = bh.getInventory();
			return inventory.hasItemStack(stack) || player.inventory.hasItemStack(stack);
		}
		return player.inventory.hasItemStack(stack);
	}
	
	
	public static void registerEquipment(ISpecialEquipment item) {
		if(item instanceof Item) {
			Item i = (Item) item;
			if(specialEquimentList.containsKey(item.getType()))
				specialEquimentList.get(item.getType()).add(i);
			else {
				ArrayList<Item> temp = new ArrayList<>();
				temp.add(i);
				specialEquimentList.put(item.getType(), temp);
			}
		}
	}
	
	public static ArrayList<Item> getEquipmentList(){
		ArrayList<Item> temp = new ArrayList<>();
		for(ArrayList<Item> list : specialEquimentList.values())
			temp.addAll(list);
		
		return temp;
	}
	
	public static ArrayList<Item> getEquipmentList(SpecialEquipmentType type){
		if(doesEquipmentListContainType(type)) {
			return specialEquimentList.get(type);
		}else
			return new ArrayList<Item>();
	}
	
	public static boolean doesEquipmentListContainType(SpecialEquipmentType type) {
		return specialEquimentList.containsKey(type);
	}
}