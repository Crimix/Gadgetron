package com.black_dog20.gadgetron.utility;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import mezz.jei.util.Log;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class OreDicHelper {

	public static ItemStack getDefaultOreDic(String ore) {
		NonNullList<ItemStack> tList2 = OreDictionary.getOres(ore);
    	if (tList2.size() > 0) {
    		for(ItemStack stack : tList2) {
    			if(stack.getItem().getUnlocalizedName().toString().contains(".thermalfoundation.")) {
    				ItemStack temp = stack.copy();
    				return temp;
    			}
    		}
    		
    		ItemStack temp = tList2.get(0).copy();
			return temp;
    	} else
    		return ItemStack.EMPTY;
	}
	
	public static boolean stackBelongsTo(String ore, ItemStack stack) {
		return stackBelongsTo(ore, 1, stack);
	}
	
	public static boolean stackBelongsTo(String ore, int amount, ItemStack stack) {
		int[] ids = OreDictionary.getOreIDs(stack);
		for(int id : ids) {
			if(OreDictionary.getOreName(id).equals(ore) && amount == stack.getCount()) {
				return true;
			}
		}
		return false;
	}
	
}
