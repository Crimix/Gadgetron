package com.black_dog20.gadgetron.block;

import net.minecraft.block.state.IBlockState;

import com.black_dog20.gadgetron.client.render.ISpecialModel;

public class BlockRaritaniumCrystal extends BlockModOre implements ISpecialModel {

	public BlockRaritaniumCrystal() {
		super("raritaniumCrystal");
		this.setHarvestLevel("pickaxe", 3);
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean causesSuffocation(IBlockState state)
    {
        return false;
    }
}
