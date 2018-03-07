package com.black_dog20.gadgetron.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.black_dog20.gadgetron.api.IElementType;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.init.ModBlocks;


public class EventHandler {


	@SubscribeEvent
	public void Tool(ItemTooltipEvent event) {
		if(event.getItemStack().getItem() instanceof IElementType){
			switch (((IElementType) event.getItemStack().getItem()).getElementType()) {
			case POSION:
				if(ModConfig.doesTrilliumWeaponsCausePoison){
					TextComponentTranslation component = new TextComponentTranslation("tooltip.gadgetron:poisonous");
					component.getStyle().setColor(TextFormatting.GREEN);
					event.getToolTip().add(1,component.getFormattedText());
				}
				break;

			default:
				break;
			}
		}
	}

	@SubscribeEvent
	public void onHurt(LivingAttackEvent event){
		if(ModConfig.doesTrilliumWeaponsCausePoison){
			if(event.getSource().getTrueSource() instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
				if(player.getHeldItemMainhand().getItem() instanceof IElementType){
					switch (((IElementType) player.getHeldItemMainhand().getItem()).getElementType()) {
					case POSION:
						event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.POISON, 600));
						break;

					default:
						break;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event){
		if(ModConfig.doesTrilliumCausePoison){
			if(event.getEntity() instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) event.getEntity();
				if(player.inventory.hasItemStack(new ItemStack(ModBlocks.TrilliumOre))){
					player.addPotionEffect(new PotionEffect(MobEffects.POISON, 40,1));
				}
			}
		}
	}


}