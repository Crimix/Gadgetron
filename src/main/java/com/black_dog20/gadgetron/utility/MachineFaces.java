package com.black_dog20.gadgetron.utility;

import java.util.ArrayList;
import java.util.List;

import com.black_dog20.gadgetron.tile.base.TileEntityBase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class MachineFaces {
	
	private String facing = "north";
	private List<Face> faces = new ArrayList<Face>();
	
	private boolean autoInput = false;
	private boolean autoOutput = false;
	private boolean hasInput = false;
	private boolean hasOutput = false;
	
	private TileEntityBase tile;
	private Varient varient;
	
	public MachineFaces(TileEntityBase tile, Varient varient, boolean hasInput, boolean hasOutput) {
		this.tile = tile;
		this.varient = varient;
		this.hasInput = hasInput;
		this.hasOutput = hasOutput;
		faces.add(new Face(FaceId.FRONT, hasInput, hasOutput));
		faces.add(new Face(FaceId.TOP, hasInput, hasOutput));
		faces.add(new Face(FaceId.LEFT, hasInput, hasOutput));
		faces.add(new Face(FaceId.RIGHT, hasInput, hasOutput));
		faces.add(new Face(FaceId.BOTTOM, hasInput, hasOutput));
		faces.add(new Face(FaceId.BACK, hasInput, hasOutput));
	}
	
	public void setFaceing(String facing) {
		this.facing = facing;
	}
	
	
	public void changeIO(Automation id) {
		if(id == Automation.INPUT) {
			if(autoInput)
				autoInput = false;
			else
				autoInput = true;
		}else if(id == Automation.OUTPUT) {
			if(autoOutput)
				autoOutput = false;
			else
				autoOutput = true;
		}
		
		tile.sendUpdates();
	}
	
	public void update(FaceId id) {
		for(Face s : faces) {
			if(s.getFace() == id)
				s.update();	
		}
		tile.sendUpdates();
	}
	
	public String getButtonState(FaceId id) {
		for(Face s : faces) {
			if(s.getFace() == id) {
				if(s.isInput() && s.isOutput())
					return "I/O";
				else if(s.isInput())
					return "I";
				else if(s.isOutput())
					return "O";
			}		
		}
		return "";
	}
	
	public boolean isFaceInput(EnumFacing facing) {
		for(Face s : faces) {
			if(converFromFaceId(s.getFace()) != null && converFromFaceId(s.getFace()) == facing) {
				return s.isInput();
			}
		}
		return false;
	}
	
	public boolean isFaceOutput(EnumFacing facing) {
		for(Face s : faces) {
			if(converFromFaceId(s.getFace()) != null && converFromFaceId(s.getFace()) == facing) {
				return s.isOutput();
			}
		}
		return false;
	}
	
	private EnumFacing converFromFaceId(FaceId id) {
		EnumFacing front = EnumFacing.byName(this.facing);
		switch (id) {
		case FRONT:
			return front.getOpposite();
		case TOP:
			return EnumFacing.UP;
		case BOTTOM:
			return EnumFacing.DOWN;
		case LEFT:
			return front.rotateY().getOpposite();
		case RIGHT:
			return front.rotateY();
		case BACK:
			return front;
		}
		return null;
	}
	
	public boolean isAutoOutput() {
		return autoOutput;
	}
	
	public boolean isAutoInput() {
		return autoInput;
	}
	
	public boolean hasInputSlots() {
		return hasInput;
	}
	
	public boolean hasOutputSlots() {
		return hasOutput;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("facing", facing);
		tag.setBoolean("autoInput", autoInput);
		tag.setBoolean("autoOutput", autoOutput);
		for(Face f : faces)
			f.writeToNBT(tag);
		
		nbt.setTag(varient.toString(), tag);
		return nbt;
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = nbt.getCompoundTag(varient.toString());
		facing = tag.getString("facing");
		autoInput = tag.getBoolean("autoInput");
		autoOutput = tag.getBoolean("autoOutput");
		for(Face f : faces)
			f.readFromNBT(tag);
	}
	
	
}
