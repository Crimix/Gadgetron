package com.black_dog20.gadgetron.block.machine;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.gadgetron.tile.TileEntityEnergyGenerator;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEnergyGenerator extends BlockMachineBase {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockEnergyGenerator() {
		super("energy_generator");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityEnergyGenerator(this.getLocalizedName());
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

	@Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
        EnumFacing entityFacing = entity.getHorizontalFacing();

        if(!world.isRemote) {
            if(entityFacing == EnumFacing.NORTH) {
                entityFacing = EnumFacing.SOUTH;
            } else if(entityFacing == EnumFacing.EAST) {
                entityFacing = EnumFacing.WEST;
            } else if(entityFacing == EnumFacing.SOUTH) {
                entityFacing = EnumFacing.NORTH;
            } else if(entityFacing == EnumFacing.WEST) {
                entityFacing = EnumFacing.EAST;
            }

            world.setBlockState(pos, state.withProperty(FACING, entityFacing), 2);
        }
    }
}
