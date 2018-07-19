package com.black_dog20.gadgetron.block;

import com.black_dog20.gadgetron.client.render.IItemModelRegister;
import com.black_dog20.gadgetron.client.render.ModelHandler;
import com.black_dog20.gadgetron.creativetab.CreativeTabGT;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockModOre extends BlockOre implements IItemModelRegister{

	
	public BlockModOre(String name, int toolLevel) {
		this(name);
		this.setHarvestLevel("pickaxe", toolLevel);
	}
	
	public BlockModOre(String name) {
		super();
		setDefaultState(pickDefaultState());
		setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(CreativeTabGT.TAB);
		setSoundType(SoundType.STONE);
		setResistance(5.0F);
	}

	protected IBlockState pickDefaultState() {
		return blockState.getBaseState();
	}

	
	@Override
	public void initModel() {
		if(Item.getItemFromBlock(this) != Items.AIR){
			ModelHandler.registerBlockToState(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
		}
	}
	

}
