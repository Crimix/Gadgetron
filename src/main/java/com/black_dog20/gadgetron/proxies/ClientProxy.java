package com.black_dog20.gadgetron.proxies;

import com.black_dog20.gadgetron.init.ModFluids;
import com.black_dog20.gadgetron.client.handler.KeyInputEventHandler;
import com.black_dog20.gadgetron.client.render.BeltRender;
import com.black_dog20.gadgetron.client.settings.Keybindings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
	}
	
	@Override
	public void registerKeyBindings() {
		ClientRegistry.registerKeyBinding(Keybindings.OPEN_GUI);
	}

	@Override
	public void registerKeyInputHandler() {
		MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
	}
	
	@Override
	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx) {
		switch (ctx.side) {
		case CLIENT:
			EntityPlayer entityClientPlayerMP = Minecraft.getMinecraft().player;
			return entityClientPlayerMP;
		case SERVER:
			EntityPlayer entityPlayerMP = ctx.getServerHandler().player;
			return entityPlayerMP;
		}
		return null;
	}

	@Override
	public EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx) {
		if (ctx.side == Side.CLIENT) {
			EntityPlayer entityClientPlayer = (EntityPlayer) Minecraft.getMinecraft().world.getEntityByID(id);
			return entityClientPlayer;
		}
		return null;
	}
	
	@Override
	public void registerRendersPreInit() {
	}
	
	@Override
	public void registerRendersInit() {
		((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default")).addLayer(new BeltRender());
		((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim")).addLayer(new BeltRender());
	}

}
