package com.black_dog20.gadgetron.utility;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class MachineFaces {

	private String facing = "north";
	private boolean frontInput = false;
	private boolean frontOutput = false;
	private boolean topInput = false;
	private boolean topOutput = false;
	private boolean buttomInput = false;
	private boolean buttomOutput = false;
	private boolean backInput = false;
	private boolean backOutput = false;
	private boolean leftInput = false;
	private boolean leftOutput = false;
	private boolean rightInput = false;
	private boolean rightOutput = false;
	
	public void setFaceing(String facing) {
		this.facing = facing;
	}
	
	public void setFrontIO(boolean input, boolean output) {
		frontInput = input;
		frontOutput = output;
	}
	
	public void setTopIO(boolean input, boolean output) {
		topInput = input;
		topOutput = output;
	}
	
	public void setButtomIO(boolean input, boolean output) {
		buttomInput = input;
		buttomOutput = output;
	}
	
	public void setBackIO(boolean input, boolean output) {
		backInput = input;
		backOutput = output;
	}
	
	public void setLeftIO(boolean input, boolean output) {
		leftInput = input;
		leftOutput = output;
	}
	
	public void setRightIO(boolean input, boolean output) {
		rightInput = input;
		rightOutput = output;
	}
	
	public boolean isFaceInput(EnumFacing facing) {
		EnumFacing front = EnumFacing.byName(this.facing);
		if(facing == EnumFacing.UP) 
			return frontInput;
		else if(facing == EnumFacing.DOWN)
			return buttomInput;
		else if(facing == front)
			return frontInput;
		else if(facing == front.getOpposite())
			return backInput;
		else if(facing == front.rotateY())
			return leftInput;
		else if(facing == front.rotateY().getOpposite())
			return rightInput;
		
		return false;
	}
	
	public boolean isFaceOutput(EnumFacing facing) {
		EnumFacing front = EnumFacing.byName(this.facing);
		if(facing == EnumFacing.UP) 
			return frontOutput;
		else if(facing == EnumFacing.DOWN)
			return buttomOutput;
		else if(facing == front)
			return frontOutput;
		else if(facing == front.getOpposite())
			return backOutput;
		else if(facing == front.rotateY())
			return leftOutput;
		else if(facing == front.rotateY().getOpposite())
			return rightOutput;
		
		return false;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setString("facing", facing);
		nbt.setBoolean("frontInput", frontInput);
		nbt.setBoolean("frontOutput", frontOutput);
		nbt.setBoolean("topInput", topInput);
		nbt.setBoolean("topOutput", topOutput);
		nbt.setBoolean("buttomInput", buttomInput);
		nbt.setBoolean("buttomOutput", buttomOutput);
		nbt.setBoolean("backInput", backInput);
		nbt.setBoolean("backOutput", backOutput);
		nbt.setBoolean("leftInput", leftInput);
		nbt.setBoolean("leftOutput", leftOutput);
		nbt.setBoolean("rightInput", rightInput);
		nbt.setBoolean("rightOutput", rightOutput);
		return nbt;
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		facing = nbt.getString("facing");
		frontInput = nbt.getBoolean("frontInput");
		frontOutput = nbt.getBoolean("frontOutput");
		topInput= nbt.getBoolean("topInput");
		topOutput = nbt.getBoolean("topOutput");
		buttomInput = nbt.getBoolean("buttomInput");
		buttomOutput = nbt.getBoolean("buttomOutput");
		backInput = nbt.getBoolean("backInput");
		backOutput = nbt.getBoolean("backOutput");
		leftInput = nbt.getBoolean("leftInput");
		leftOutput = nbt.getBoolean("leftOutput");
		rightInput = nbt.getBoolean("rightInput");
		rightOutput = nbt.getBoolean("rightOutput");
	}
	
	
}
