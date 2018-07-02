package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.block.BlockBase;
import com.black_dog20.gadgetron.client.render.IItemModelRegister;
import com.black_dog20.gadgetron.creativetab.CreativeTabGT;
import com.black_dog20.gadgetron.tile.base.TileEntityBase;
import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

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
			if(!worldIn.isRemote) {
				if(te.hasCapability(CapabilityEnergy.ENERGY, null)) {
					IEnergyStorage es = te.getCapability(CapabilityEnergy.ENERGY, null);
					playerIn.sendMessage(new TextComponentString(Integer.toString(es.getEnergyStored())));
				}
			}
		}
		else {
			if(te instanceof TileEntityBase) {
				playerIn.openGui(Gadgetron.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
	public void breakBlock(World world, BlockPos pos, IBlockState state){
		if(this.shouldDropInventory(world, pos)){
			this.dropInventory(world, pos);
		}

		super.breakBlock(world, pos, state);
	}

	public boolean shouldDropInventory(World world, BlockPos pos){
		return true;
	}

	private void dropInventory(World world, BlockPos pos){
		if(!world.isRemote){
			TileEntity tile = world.getTileEntity(pos);
			if(tile instanceof TileEntityEnergyInventoryBase){
				TileEntityEnergyInventoryBase base = (TileEntityEnergyInventoryBase)tile;
				if(base.getInventory().getSlots()>0){
					for(int i = 0; i < base.getInventory().getSlots(); i++){
						ItemStack stack = base.getInventory().getStackInSlot(i);
						if(!stack.isEmpty()){
							float dX = world.rand.nextFloat()*0.8F+0.1F;
							float dY = world.rand.nextFloat()*0.8F+0.1F;
							float dZ = world.rand.nextFloat()*0.8F+0.1F;
							EntityItem entityItem = new EntityItem(world, pos.getX()+dX, pos.getY()+dY, pos.getZ()+dZ, stack.copy());
							float factor = 0.05F;
							entityItem.motionX = world.rand.nextGaussian()*factor;
							entityItem.motionY = world.rand.nextGaussian()*factor+0.2F;
							entityItem.motionZ = world.rand.nextGaussian()*factor;
							world.spawnEntity(entityItem);
						}
					}
				}
			}
		}
	}

}
