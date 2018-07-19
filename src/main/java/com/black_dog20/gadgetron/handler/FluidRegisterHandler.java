package com.black_dog20.gadgetron.handler;

import com.black_dog20.gadgetron.recipehandler.FuelGeneratorHandler;
import com.black_dog20.gadgetron.reference.Reference;

import net.minecraftforge.fluids.FluidRegistry.FluidRegisterEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class FluidRegisterHandler {

	  @SubscribeEvent
	  public static void onFluidRegisterEvent(FluidRegisterEvent event) {
	    switch (event.getFluidName()) {
	    case "creosote":
	      FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 20, 5000);
	      break;
	    case "coal":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 40, 10000);
	      break;
	    case "crude_oil":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 50, 8000);
	      break;
	    case "tree_oil":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 50, 20000);
	      break;
	    case "refined_oil":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 100, 12500);
	      break;
	    case "refined_fuel":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 200, 10000);
	      break;
	    case "seed_oil":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 20, 4000);
	      break;
	    case "refined_biofuel":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 125, 6400);
	      break;
	    case "canolaoil":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 20, 4000);
	      break;
	    case "refinedcanolaoil":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 40, 5000);
	      break;
	    case "crystaloil":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 80, 5000);
	      break;
	    case "empoweredoil":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 140, 5000);
	      break;
	    case "bio.ethanol":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 160, 3125);
	      break;
	    case "biodiesel":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 125, 4000);
	      break;
	    case "oil":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 50, 8000);
	      break;
	    case "diesel":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 125, 6400);
	      break;
	    case "gasoline":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 160, 7500);
	      break;
	    case "ic2biogas":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 50, 2000);
	      break;
	    case "biofuel":
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 125, 4000);
	      break;
	    case "if.protein": // TODO: This is better suited for the zombie gen
	    	FuelGeneratorHandler.instance().addFuel(event.getFluidName(), 25, 40000);
	      break;
	    default:
	      return;
	    }
	  }
}
