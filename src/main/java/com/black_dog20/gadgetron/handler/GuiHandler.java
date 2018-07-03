package com.black_dog20.gadgetron.handler;

import com.black_dog20.gadgetron.client.gui.GuiIOConfig;
import com.black_dog20.gadgetron.container.ContainerIOConfig;
import com.black_dog20.gadgetron.tile.base.TileEntityBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntityBase te = (TileEntityBase) world.getTileEntity(new BlockPos(x, y, z));
		if(ID != 0) 
			return te.getContainer(player.inventory);
		else
			return new ContainerIOConfig(player.inventory, te);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntityBase te = (TileEntityBase) world.getTileEntity(new BlockPos(x, y, z));
		if(ID!=0)
			return te.getGUI(player.inventory);
		else
			return new GuiIOConfig(player.inventory, te);

	}
}