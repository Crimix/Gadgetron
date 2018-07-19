package com.black_dog20.gadgetron.utility;

import net.minecraft.nbt.NBTTagCompound;

public class Face {

	private FaceId id;
	private boolean input = false;
	private boolean output = false;
	private boolean hasInput = false;
	private boolean hasOutput = false;
	
	public Face (FaceId id, boolean hasInput, boolean hasOutput) {
		this.id = id;
		this.hasInput = hasInput;
		this.hasOutput = hasOutput;
	}
	
	public FaceId getFace() {
		return id;
	}
	
	public void update() {
		if(hasInput && hasOutput) {
			if(!input && !output)
				input = true;
			else if(input && !output) {
				input = false;
				output = true;
			}
			else if(!input && output) {
				input = true;
				output = true;
			}
			else {
				input = false;
				output = false;
			}
		}
		else if(hasInput && !hasOutput) {
			if(!input)
				input = true;
			else
				input = false;
		}
		else if(!hasInput && hasOutput) {
			if(!output)
				output = true;
			else
				output = false;
		}
	}
	
	public boolean isInput() {
		return input;
	}
	
	public boolean isOutput() {
		return output;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("input", input);
		tag.setBoolean("output", output);
		nbt.setTag(id.toString(), tag);
		return nbt;
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = nbt.getCompoundTag(id.toString());
		input = tag.getBoolean("input");
		output = tag.getBoolean("output");
	}
}
