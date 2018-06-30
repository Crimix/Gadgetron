package com.black_dog20.gadgetron.block.machine;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.block.BlockBase;
import com.black_dog20.gadgetron.tile.TileEntityBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class BlockMachineBase extends BlockBase {

	public BlockMachineBase(String name) {
		super(Material.IRON, name);
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
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

}
