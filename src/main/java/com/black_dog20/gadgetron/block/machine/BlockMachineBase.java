package com.black_dog20.gadgetron.block.machine;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.block.BlockBase;
import com.black_dog20.gadgetron.client.render.IItemModelRegister;
import com.black_dog20.gadgetron.creativetab.CreativeTabGT;
import com.black_dog20.gadgetron.reference.Reference;
import com.black_dog20.gadgetron.tile.base.TileEntityBase;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachineBase extends BlockBase implements IItemModelRegister {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockMachineBase(String name) {
		super(Material.IRON, name);
		this.setHardness(1.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setCreativeTab(CreativeTabGT.TAB_MACHINES);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(playerIn.isSneaking()) {
		}
		else {
			if(te instanceof TileEntityBase) {
				playerIn.openGui(Gadgetron.instance, Gadgetron.guiAutoTileEntityID, worldIn, pos.getX(), pos.getY(), pos.getZ());
				return true;
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
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
		EnumFacing front = EnumFacing.NORTH;

		if(!world.isRemote) {
			if(entityFacing == EnumFacing.NORTH) {
				entityFacing = EnumFacing.SOUTH;
				front = EnumFacing.SOUTH;
			} else if(entityFacing == EnumFacing.EAST) {
				entityFacing = EnumFacing.WEST;
				front = EnumFacing.WEST;
			} else if(entityFacing == EnumFacing.SOUTH) {
				entityFacing = EnumFacing.NORTH;
				front = EnumFacing.NORTH;
			} else if(entityFacing == EnumFacing.WEST) {
				entityFacing = EnumFacing.EAST;
				front = EnumFacing.EAST;
			}

			world.setBlockState(pos, state.withProperty(FACING, entityFacing), 2);
			TileEntityBase te = (TileEntityBase) world.getTileEntity(pos);

			if (te!= null && te instanceof TileEntityBase )
			{
				if(stack.getTagCompound() != null)
					te.readFromCustomInfoNBT(stack.getTagCompound());
				te.setFront(front.getName());
			}
		}
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player){
		if(!world.isRemote)   {
			if(!player.isCreative()) {
				this.dropBlockAsItem(world, pos, state, 0);

			}
			//dirty workaround because of Forge calling Item.onBlockStartBreak() twice
			world.setBlockToAir(pos);
		}

	}

	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ItemStack stack = new ItemStack(world.getBlockState(pos).getBlock());

		TileEntityBase te = (TileEntityBase) world.getTileEntity(pos);
		if (te != null && te instanceof TileEntityBase)
		{

			NBTTagCompound tag = new NBTTagCompound();
			te.writeCustomInfoToNBT(tag);
			if (!stack.hasTagCompound())
			{
				stack.setTagCompound(tag);
			}else
			{
				stack.getTagCompound().merge(tag);
			}
			
			ret.add(stack);
		}
		return ret;
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
	{
		if (willHarvest)
		{
			return true; // If it will harvest, delay deletion of the block
							// until after getDrops
		}
		return super.removedByPlayer(state, world, pos, player, willHarvest);
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		super.harvestBlock(worldIn, player, pos, state, te, stack);
		worldIn.setBlockToAir(pos);
	}

}
