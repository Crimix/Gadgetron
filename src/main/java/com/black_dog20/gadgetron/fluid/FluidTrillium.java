package com.black_dog20.gadgetron.fluid;

import com.black_dog20.gadgetron.reference.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidTrillium extends Fluid {
	
	public FluidTrillium() {
		super("trillium", new ResourceLocation(Reference.MOD_ID, "blocks/trillium_still"), new ResourceLocation(Reference.MOD_ID, "blocks/trillium_flow"));
		FluidRegistry.registerFluid(this);
		FluidRegistry.addBucketForFluid(this);
	}
}