package com.black_dog20.gadgetron.jei;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import com.black_dog20.gadgetron.init.ModBlocks;
import com.black_dog20.gadgetron.init.ModItems;

@mezz.jei.api.JEIPlugin
public class JEIPlugin extends BlankModPlugin{

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerIngredients(IModIngredientRegistration registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(IModRegistry registry) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(ModBlocks.TrilliumOre));
		list.add(new ItemStack(ModItems.TrilliumAxe));
		list.add(new ItemStack(ModItems.TrilliumHoe));
		list.add(new ItemStack(ModItems.TrilliumPickaxe));
		list.add(new ItemStack(ModItems.TrilliumShovel));
		list.add(new ItemStack(ModItems.TrilliumSword));
		list.add(new ItemStack(ModItems.RaritaniumCrystal));
		list.add(new ItemStack(ModItems.AdamantineIngot));
		list.add(new ItemStack(ModItems.CarbonoxIngot));
		list.add(new ItemStack(ModItems.TrilliumIngot));
		
		for(ItemStack stack : list){
			String info = getFormattedString(stack);
			if(info != null){
				registry.addIngredientInfo(stack, ItemStack.class, info);
			}
		}
	}
	
	private String getFormattedString(ItemStack stack){
		String res = I18n.format(stack.getItem().getUnlocalizedName()+".info");
		if(res.contains(stack.getItem().getUnlocalizedName())){
			return null;
		}
		else{
			return res;
		}
	}
		

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		// TODO Auto-generated method stub
		
	}

}
