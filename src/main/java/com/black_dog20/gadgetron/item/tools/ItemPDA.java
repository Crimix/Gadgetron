package com.black_dog20.gadgetron.item.tools;

import java.util.HashSet;
import java.util.Set;

import com.black_dog20.gadgetron.client.render.BlockRenderHelper;
import com.black_dog20.gadgetron.item.ItemBase;
import com.black_dog20.gadgetron.network.PacketHandler;
import com.black_dog20.gadgetron.network.message.MessageAddPowerOutputToBattery;
import com.black_dog20.gadgetron.tile.TileEntityBattery;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPDA extends ItemBase {

	public ItemPDA() {
		super("power_distubution_atuner");
		// TODO Auto-generated constructor stub
	}


	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			
			ItemStack tool = player.getHeldItem(hand);
			if(tool == null || tool == ItemStack.EMPTY)
				return EnumActionResult.FAIL;

			if(!player.canPlayerEdit(pos, facing, player.getHeldItem(hand)))
				return EnumActionResult.FAIL;
			
			if(worldIn.getTileEntity(pos) instanceof TileEntityBattery) {
				player.sendMessage(new TextComponentString("bound"));
				NBTTagCompound nbt = tool.getTagCompound();
				if(nbt == null)
					nbt = new NBTTagCompound();
				nbt.setInteger("dim", worldIn.provider.getDimension());
				nbt.setInteger("x", pos.getX());
				nbt.setInteger("y", pos.getY());
				nbt.setInteger("z", pos.getZ());
				tool.setTagCompound(nbt);
			}
			else {
				NBTTagCompound nbt = tool.getTagCompound();
				if(nbt == null)
					return EnumActionResult.FAIL;
				int dim = nbt.getInteger("dim");
				int x = nbt.getInteger("x");
				int y = nbt.getInteger("y");
				int z = nbt.getInteger("z");

				if(dim == worldIn.provider.getDimension()) {
					TileEntity te = worldIn.getTileEntity(new BlockPos(x,y,z));
					if(te instanceof TileEntityBattery) {
						TileEntityBattery battery = (TileEntityBattery) te;
						battery.addCoordinateForPower(pos);
					}
				}
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}


	@SideOnly(Side.CLIENT)
	public void renderOverlay(RenderWorldLastEvent evt, EntityPlayerSP player, ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null)
			return;
		
		int id = nbt.getInteger("id");
		int dim = nbt.getInteger("dim");
		int x = nbt.getInteger("x");
		int y = nbt.getInteger("y");
		int z = nbt.getInteger("z");

		if(player.getEntityWorld().provider.getDimension() == dim) {
			TileEntityBattery battery = (TileEntityBattery) player.getEntityWorld().getTileEntity(new BlockPos(x,y,z));
			if(battery != null && battery instanceof TileEntityBattery) {
				BlockRenderHelper.renderOutlines(evt, player, battery.getPos(), 0, 0, 218 , 4);
				BlockRenderHelper.renderOutlines(evt, player, battery.getCoordinatesForPower(), 218, 0, 218 , 4);
			}
		}
	}

}
