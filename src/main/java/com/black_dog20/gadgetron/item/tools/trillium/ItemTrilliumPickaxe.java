package com.black_dog20.gadgetron.item.tools.trillium;

import com.black_dog20.gadgetron.api.IElementType;
import com.black_dog20.gadgetron.client.render.IItemModelRegister;
import com.black_dog20.gadgetron.creativetab.CreativeTabGT;
import com.black_dog20.gadgetron.item.tools.ItemPickaxeBase;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTrilliumPickaxe extends ItemPickaxeBase implements IItemModelRegister, IElementType{

	public ItemTrilliumPickaxe(String name, ToolMaterial material) {
		super(material);
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(CreativeTabGT.TAB);
	}
	
	
    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }


	@Override
	public ItemElementType getElementType() {
		return ItemElementType.POSION;
	}

}
