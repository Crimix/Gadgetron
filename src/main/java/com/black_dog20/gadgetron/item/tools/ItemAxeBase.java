package com.black_dog20.gadgetron.item.tools;

import com.black_dog20.gadgetron.client.render.IItemModelRegister;
import com.black_dog20.gadgetron.creativetab.CreativeTabGT;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAxeBase extends ItemAxe implements IItemModelRegister{

	public ItemAxeBase(String name, ToolMaterial material, float damage, float speed) {
		super(material,damage,speed);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(CreativeTabGT.TAB);
	}
	
	public ItemAxeBase(ToolMaterial material, float damage, float speed) {
		super(material,damage,speed);
	}
	
	
    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
