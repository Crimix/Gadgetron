package com.black_dog20.gadgetron.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.black_dog20.gadgetron.init.ModItems;
import com.black_dog20.gadgetron.reference.Reference;

public class CreativeTabGT{

	public static final CreativeTabs MOT_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.ACACIA_BOAT);
		}

		@Override
		public String getTranslatedTabLabel() {
			return Reference.MOD_NAME;
		}
	};

}
