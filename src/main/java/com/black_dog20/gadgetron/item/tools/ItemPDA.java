package com.black_dog20.gadgetron.item.tools;

import java.util.List;

import com.black_dog20.gadgetron.client.render.BlockRenderHelper;
import com.black_dog20.gadgetron.item.ItemBase;
import com.black_dog20.gadgetron.tile.TileEntityBattery;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPDA extends ItemBase {

	public ItemPDA() {
		super("power_distribution_atuner");
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
		ItemStack tool = playerIn.getHeldItem(handIn);
		NBTTagCompound nbt = tool.getTagCompound();
		if(nbt == null)
			nbt = new NBTTagCompound();
		int dim = nbt.getInteger("dim");
		int x = nbt.getInteger("x");
		int y = nbt.getInteger("y");
		int z = nbt.getInteger("z");

			TileEntity te = DimensionManager.getWorld(dim).getTileEntity(new BlockPos(x,y,z));
			if(te != null && te instanceof TileEntityBattery) {
				TileEntityBattery battery = (TileEntityBattery) te;
				playerIn.sendMessage(new TextComponentString(String.format("%s %d %s",I18n.format("msg.gadgetron:deliver"), battery.getCoordinatesForPower().size(), I18n.format("msg.gadgetron:blocks"))));
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			
			ItemStack tool = player.getHeldItem(hand);
			if(tool == null || tool == ItemStack.EMPTY)
				return EnumActionResult.FAIL;
			if(!player.canPlayerEdit(pos, facing, player.getHeldItem(hand)))
				return EnumActionResult.FAIL;
			NBTTagCompound nbt = tool.getTagCompound();
			if(nbt == null)
				nbt = new NBTTagCompound();
			if(worldIn.getTileEntity(pos) instanceof TileEntityBattery && !nbt.hasKey("dim")) {
				player.sendMessage(new TextComponentString(String.format("%s (%d,%d,%d)", I18n.format("msg.gadgetron:boundto"), pos.getX(), pos.getY(), pos.getZ())));

				nbt.setInteger("dim", worldIn.provider.getDimension());
				nbt.setInteger("x", pos.getX());
				nbt.setInteger("y", pos.getY());
				nbt.setInteger("z", pos.getZ());
				tool.setTagCompound(nbt);
			} else if(checkIfSameBattery(worldIn, tool, pos)){
				nbt.removeTag("dim");
				nbt.removeTag("x");
				nbt.removeTag("y");
				nbt.removeTag("z");
				player.sendMessage(new TextComponentTranslation("msg.gadgetron:unbound"));
			} else {
				int dim = nbt.getInteger("dim");
				int x = nbt.getInteger("x");
				int y = nbt.getInteger("y");
				int z = nbt.getInteger("z");
				
				if(dim == worldIn.provider.getDimension()) {
					TileEntity te = worldIn.getTileEntity(new BlockPos(x,y,z));
					if(te != null && te instanceof TileEntityBattery) {
						TileEntityBattery battery = (TileEntityBattery) te;
						battery.addCoordinateForPower(pos);
					} else {
						player.sendMessage(new TextComponentTranslation("msg.gadgetron:failedtoadd"));
						
					}
				} else {
					player.sendMessage(new TextComponentTranslation("msg.gadgetron:failednotsamedim"));
				}
			}
		}
		return EnumActionResult.SUCCESS;
	}
	
	private boolean checkIfSameBattery(World world, ItemStack tool, BlockPos input) {
		NBTTagCompound nbt = tool.getTagCompound();
		if(nbt == null)
			nbt = new NBTTagCompound();
		int dim = nbt.getInteger("dim");
		int x = nbt.getInteger("x");
		int y = nbt.getInteger("y");
		int z = nbt.getInteger("z");
		return(world.getTileEntity(input) instanceof TileEntityBattery && world.provider.getDimension() == dim && input.getX() == x && input.getY() == y && input.getZ() == z);
	}


	@SideOnly(Side.CLIENT)
	public void renderOverlay(RenderWorldLastEvent evt, EntityPlayerSP player, ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null)
			return;
		
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
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(I18n.format("tooltip.gadgetron:power_distribution_atuner"));
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			int dim = nbt.getInteger("dim");
			int x = nbt.getInteger("x");
			int y = nbt.getInteger("y");
			int z = nbt.getInteger("z");
			if(nbt.hasKey("dim"))
				tooltip.add(String.format("%s (%d,%d,%d) %s %s", I18n.format("tooltip.gadgetron:bound"), x, y, z, I18n.format("tooltip.gadgetron:in"), DimensionManager.getProvider(dim).getDimensionType().getName()));
			else
				tooltip.add(I18n.format("tooltip.gadgetron:notbound"));
		}
		else
			tooltip.add(I18n.format("tooltip.gadgetron:notbound"));
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

}
