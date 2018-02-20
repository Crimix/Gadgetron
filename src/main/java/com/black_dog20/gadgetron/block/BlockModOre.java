package com.black_dog20.gadgetron.block;

import java.util.Random;

import com.black_dog20.gadgetron.client.render.IItemModelRegister;
import com.black_dog20.gadgetron.client.render.ModelHandler;
import com.black_dog20.gadgetron.creativetab.CreativeTabGT;
import com.black_dog20.gadgetron.init.ModBlocks;
import com.black_dog20.gadgetron.init.ModItems;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockModOre extends BlockOre implements IItemModelRegister{

	public BlockModOre(String name) {
		super();
		setDefaultState(pickDefaultState());
		setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		setUnlocalizedName(getRegistryName().toString());
		registerItemForm();
		setCreativeTab(CreativeTabGT.TAB);
		setSoundType(SoundType.STONE);
		setResistance(5.0F);
	}

	protected IBlockState pickDefaultState() {
		return blockState.getBaseState();
	}
	
	public void registerItemForm() {
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}

	
	@Override
	public void initModel() {
		if(Item.getItemFromBlock(this) != Items.AIR){
			ModelHandler.registerBlockToState(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
		}
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this == ModBlocks.blockRaritaniumOre ? ModItems.RaritaniumCrystal : Item.getItemFromBlock(this);
    }
	
	   @Override
	    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
	    {
	        Random rand = world instanceof World ? ((World)world).rand : new Random();
	        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
	        {
	            int i = 0;

	            if (this == ModBlocks.blockRaritaniumOre)
	            {
	                i = MathHelper.getInt(rand, 3, 7);
	            }

	            return i;
	        }
	        return 0;
	    }
	
	
	

}
