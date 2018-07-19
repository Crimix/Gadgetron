package com.black_dog20.gadgetron.fluid;

import com.black_dog20.gadgetron.creativetab.CreativeTabGT;
import com.black_dog20.gadgetron.init.ModFluids;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluidTrillium extends BlockFluidClassic {
	
	public BlockFluidTrillium() {
		super(ModFluids.fluidTrillium, Material.WATER);
		setRegistryName("trillium");
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(CreativeTabGT.TAB);
	}

	@SideOnly(Side.CLIENT)
	public void render() {
		ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(LEVEL).build());
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, "trillium"), "inventory"));
	}
}
