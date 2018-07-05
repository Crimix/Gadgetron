package com.black_dog20.gadgetron.utility;

import com.black_dog20.gadgetron.config.ModConfig.Machines;
import com.black_dog20.gadgetron.tile.base.TileEntityBase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class MachineFaces {

	public enum Id{
		FRONT, TOP, LEFT, RIGHT, BACK, BOTTOM, AUTO;
	}
	
	public enum Varient{
		IVENTORY, TANK;
	}
	
	private String facing = "north";
	private boolean frontInput = false;
	private boolean frontOutput = false;
	private boolean topInput = false;
	private boolean topOutput = false;
	private boolean bottomInput = false;
	private boolean bottomOutput = false;
	private boolean backInput = false;
	private boolean backOutput = false;
	private boolean leftInput = false;
	private boolean leftOutput = false;
	private boolean rightInput = false;
	private boolean rightOutput = false;
	
	private boolean autoInput = false;
	private boolean autoOutput = false;
	
	private TileEntityBase tile;
	private Varient varient;
	
	public MachineFaces(TileEntityBase tile, MachineFaces.Varient varient) {
		this.tile = tile;
		this.varient = varient;
	}
	
	public void setFaceing(String facing) {
		this.facing = facing;
	}
	
	public void update(MachineFaces.Id id, boolean input, boolean output) {
		switch (id) {
		case FRONT:
			frontInput = input;
			frontOutput = output;
			break;
		case TOP:
			topInput = input;
			topOutput = output;
			break;
		case LEFT:
			leftInput = input;
			leftOutput = output;
			break;
		case RIGHT:
			rightInput = input;
			rightOutput = output;
			break;
		case BACK:
			backInput = input;
			backOutput = output;
			break;
		case BOTTOM:
			bottomInput = input;
			bottomOutput = output;
			break;
		case AUTO:
			autoInput = input;
			autoOutput = output;
			break;

		default:
			break;
		}
		tile.sendUpdates();
	}
	
	public String getButtonState(MachineFaces.Id id) {
		switch (id) {
		case FRONT:
			if(frontInput && frontOutput)
				return "I/O";
			else if(frontInput)
				return "I";
			else if(frontOutput)
				return "O";
		case TOP:
			if(topInput && topOutput)
				return "I/O";
			else if(topInput)
				return "I";
			else if(topOutput)
				return "O";
		case LEFT:
			if(leftInput && leftOutput)
				return "I/O";
			else if(leftInput)
				return "I";
			else if(leftOutput)
				return "O";
		case RIGHT:
			if(rightInput && rightOutput)
				return "I/O";
			else if(rightInput)
				return "I";
			else if(rightOutput)
				return "O";
		case BACK:
			if(backInput && backOutput)
				return "I/O";
			else if(backInput)
				return "I";
			else if(backOutput)
				return "O";
		case BOTTOM:
			if(bottomInput && bottomOutput)
				return "I/O";
			else if(bottomInput)
				return "I";
			else if(bottomOutput)
				return "O";
		case AUTO:
			if(autoInput && autoOutput)
				return "I/O";
			else if(autoInput)
				return "I";
			else if(autoOutput)
				return "O";
		default:
			return "";
		}
	}
	
	public boolean isFaceInput(EnumFacing facing) {
		EnumFacing front = EnumFacing.byName(this.facing);
		if(facing == EnumFacing.UP) 
			return frontInput;
		else if(facing == EnumFacing.DOWN)
			return bottomInput;
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
			return bottomOutput;
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
	
	public boolean isAutoOutput() {
		return autoOutput;
	}
	
	public boolean isAutoInput() {
		return autoInput;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setString(varient.toString()+"facing", facing);
		nbt.setBoolean(varient.toString()+"frontInput", frontInput);
		nbt.setBoolean(varient.toString()+"frontOutput", frontOutput);
		nbt.setBoolean(varient.toString()+"topInput", topInput);
		nbt.setBoolean(varient.toString()+"topOutput", topOutput);
		nbt.setBoolean(varient.toString()+"bottomInput", bottomInput);
		nbt.setBoolean(varient.toString()+"bottomOutput", bottomOutput);
		nbt.setBoolean(varient.toString()+"backInput", backInput);
		nbt.setBoolean(varient.toString()+"backOutput", backOutput);
		nbt.setBoolean(varient.toString()+"leftInput", leftInput);
		nbt.setBoolean(varient.toString()+"leftOutput", leftOutput);
		nbt.setBoolean(varient.toString()+"rightInput", rightInput);
		nbt.setBoolean(varient.toString()+"rightOutput", rightOutput);
		nbt.setBoolean(varient.toString()+"autoInput", autoInput);
		nbt.setBoolean(varient.toString()+"autoOutput", autoOutput);
		return nbt;
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		facing = nbt.getString(varient.toString()+"facing");
		frontInput = nbt.getBoolean(varient.toString()+"frontInput");
		frontOutput = nbt.getBoolean(varient.toString()+"frontOutput");
		topInput= nbt.getBoolean(varient.toString()+"topInput");
		topOutput = nbt.getBoolean(varient.toString()+"topOutput");
		bottomInput = nbt.getBoolean(varient.toString()+"bottomInput");
		bottomOutput = nbt.getBoolean(varient.toString()+"bottomOutput");
		backInput = nbt.getBoolean(varient.toString()+"backInput");
		backOutput = nbt.getBoolean(varient.toString()+"backOutput");
		leftInput = nbt.getBoolean(varient.toString()+"leftInput");
		leftOutput = nbt.getBoolean(varient.toString()+"leftOutput");
		rightInput = nbt.getBoolean(varient.toString()+"rightInput");
		rightOutput = nbt.getBoolean(varient.toString()+"rightOutput");
		autoInput = nbt.getBoolean(varient.toString()+"autoInput");
		autoOutput = nbt.getBoolean(varient.toString()+"autoOutput");
	}
	
	
}
