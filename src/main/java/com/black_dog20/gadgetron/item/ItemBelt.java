package com.black_dog20.gadgetron.item;

import java.util.List;

import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.client.settings.Keybindings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemBelt extends ItemBase {
	
	
	public ItemBelt(String name){
		super(name);
		this.setMaxStackSize(1);
	}
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		TextComponentTranslation unequip = new TextComponentTranslation("tooltip.gadgetron:open_gui");
		TextComponentTranslation rightclick = new TextComponentTranslation("tooltip.gadgetron:right_click");
		tooltip.add(unequip.getFormattedText() + " §9" + Keybindings.OPEN_GUI.getDisplayName() + "§r");
		tooltip.add(rightclick.getFormattedText());
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			IBeltHandler mh = BeltHandler.instanceFor(player);
			if(mh != null){
				if(!mh.getHasBelt()) {
					mh.setHasBelt(true);
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, ItemStack.EMPTY);
				} else {
					player.sendMessage(new TextComponentTranslation("msg.already_have"));
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItem(hand));
				}
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));

	}
}
