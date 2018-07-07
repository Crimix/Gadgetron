package com.black_dog20.gadgetron.item.tools;

import java.util.HashSet;
import java.util.Set;

import com.black_dog20.gadgetron.client.render.BlockRenderHelper;
import com.black_dog20.gadgetron.item.ItemBase;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPDA extends ItemBase {

	public ItemPDA() {
		super("power_distubution_atuner");
		// TODO Auto-generated constructor stub
	}
	
	@SideOnly(Side.CLIENT)
    public void renderOverlay(RenderWorldLastEvent evt, EntityPlayerSP player, ItemStack stack) {
        Set<BlockPos> coordinates = new HashSet<BlockPos>();
        coordinates.add(new BlockPos(11,68,7));
       	BlockRenderHelper.renderOutlines(evt, player, coordinates, 218, 0, 218 , 4);
    }

}
