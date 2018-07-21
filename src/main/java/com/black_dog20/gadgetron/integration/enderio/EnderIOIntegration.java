package com.black_dog20.gadgetron.integration.enderio;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class EnderIOIntegration {
	
	public static void AddRecipeToSagmill(ItemStack in, ItemStack out) {
		String recipe = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
				"<enderio:recipes xmlns:enderio=\"http://enderio.com/recipes\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://enderio.com/recipes recipes.xsd \">";
		recipe += String.format("<recipe name=\"Sagmill: %s\" required=\"false\">", in.getItem().getRegistryName());
		recipe += "<sagmilling energy=\"3200\">";
		recipe += String.format("<input name=\"%s\" />", in.getItem().getRegistryName());
		recipe += String.format("<output name=\"%s\" amount=\"%d\" />", out.getItem().getRegistryName(), out.getCount());
		recipe += "</sagmilling>";
		recipe += "</recipe>";
		recipe += "</enderio:recipes>";

		if(Loader.isModLoaded("enderio"))
			FMLInterModComms.sendMessage("enderio", "recipe:xml", recipe);
	}

}
