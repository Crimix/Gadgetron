package com.black_dog20.gadgetron.client.handler;

import com.black_dog20.gadgetron.capability.BeltHandler;
import com.black_dog20.gadgetron.capability.IBeltHandler;
import com.black_dog20.gadgetron.client.settings.Keybindings;
import com.black_dog20.gadgetron.network.PacketHandler;
import com.black_dog20.gadgetron.network.message.MessageOpenGuiOnServer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		if(Keybindings.OPEN_GUI.isPressed()){
			EntityPlayer player = Minecraft.getMinecraft().player;
			IBeltHandler bh = BeltHandler.instanceFor(player);
			if(bh != null && bh.getHasBelt())
				PacketHandler.network.sendToServer(new MessageOpenGuiOnServer(2, (int)player.posX, (int)player.posY, (int)player.posZ));
			else
				player.sendMessage(new TextComponentTranslation("msg.gadgetron.no_belt"));
		}
	}
}
