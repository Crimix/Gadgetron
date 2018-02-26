package com.black_dog20.gadgetron.item.tools.trillium;

import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.black_dog20.gadgetron.api.IElementType;
import com.black_dog20.gadgetron.client.render.IItemModelRegister;
import com.black_dog20.gadgetron.creativetab.CreativeTabGT;
import com.black_dog20.gadgetron.item.tools.ItemSwordBase;
import com.black_dog20.gadgetron.reference.Reference;

public class ItemTrilliumSword extends ItemSwordBase implements IItemModelRegister, IElementType{

	public ItemTrilliumSword(String name, ToolMaterial material) {
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

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool, String text) {
		super.addInformation(item, player, list, bool);
		list.add(text);
	}


	@Override
	public ItemElementType getElementType() {
		return ItemElementType.POSION;
	}

}
