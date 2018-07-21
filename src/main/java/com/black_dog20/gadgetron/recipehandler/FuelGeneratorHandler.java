package com.black_dog20.gadgetron.recipehandler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FuelGeneratorHandler {

	private static final FuelGeneratorHandler instance = new FuelGeneratorHandler();
	
	public static FuelGeneratorHandler instance() {
		return instance;
	}

	private final Map<String, FuelObject> fuels = new HashMap<String, FuelObject>();

	private FuelGeneratorHandler() {
		//this.addFuel(FluidRegistry.LAVA, 10, 100);
	}

	public void addFuel(String fluidName, int powerPerCycleRF, int totalBurnTime) {
		if(FluidRegistry.getFluid(fluidName) != null)
			addFuel(FluidRegistry.getFluid(fluidName), powerPerCycleRF, totalBurnTime);
	}

	public void addFuel(@Nonnull Fluid fluid, int powerPerCycleRF, int totalBurnTime) {
		fuels.put(fluid.getName(), new FuelObject(fluid, powerPerCycleRF, totalBurnTime));
	}


	public FuelObject getFuel(Fluid fluid) {
		if(fluid != null)
			return getFuel(fluid.getName());
		else
			return null;
	}

	public FuelObject getFuel(String fluidName) {
		return fuels.get(fluidName);
	}

	public Map<String, FuelObject> getFuels(){
		return fuels;
	}


	public static class FuelObject  {

		private final @Nonnull Fluid fluid;
		private final int powerPerCycle;
		private final int totalBurningTime;

		public FuelObject(@Nonnull Fluid fluid, int power, int totalBurningTime) {
			this.fluid = fluid;
			this.powerPerCycle = power;
			this.totalBurningTime = totalBurningTime;
		}

		public @Nonnull Fluid getFluid() {
			return fluid;
		}

		public int getTotalBurningTime() {
			return totalBurningTime;
		}

		public int getPowerPerCycle() {
			return powerPerCycle;
		}

	}

}