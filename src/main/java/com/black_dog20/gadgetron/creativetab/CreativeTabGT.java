package com.black_dog20.gadgetron.creativetab;

import com.black_dog20.gadgetron.init.ModBlocks;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabGT{

	public static final CreativeTabs TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModBlocks.AdamantineOre);
		}

		@Override
		public String getTranslatedTabLabel() {
			return Reference.MOD_NAME;
		}
	};

}
