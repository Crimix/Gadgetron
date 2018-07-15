package com.black_dog20.gadgetron.client.gui;

import com.black_dog20.gadgetron.tile.base.TileEntityEnergyInventoryBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiExtractor extends GuiMachine {

	public GuiExtractor(EntityPlayer player, TileEntityEnergyInventoryBase tileEntity) {
		super(player, tileEntity);
	}
	
}