package com.black_dog20.gadgetron.item.tools;

import com.black_dog20.gadgetron.client.render.IItemModelRegister;
import com.black_dog20.gadgetron.creativetab.CreativeTabGT;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPickaxeBase extends ItemPickaxe implements IItemModelRegister{

	public ItemPickaxeBase(String name, ToolMaterial material) {
		super(material);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(CreativeTabGT.TAB);
	}
	
	public ItemPickaxeBase(ToolMaterial material) {
		super(material);
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
