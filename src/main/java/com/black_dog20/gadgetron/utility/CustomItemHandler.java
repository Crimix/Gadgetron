package com.black_dog20.gadgetron.utility;

import com.black_dog20.gadgetron.tile.base.TileEntityBase;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class CustomItemHandler extends ItemStackHandler{

	private TileEntityBase base;
	
	
    public CustomItemHandler()
    {
        this(1);
    }

    public CustomItemHandler(int size)
    {
    	super(size);
    }

    public CustomItemHandler(NonNullList<ItemStack> stacks)
    {
       super(stacks);
    }
	
	public void setTileEntityBase(TileEntityBase base) {
		this.base = base;
	}
	
	@Override
	protected void onContentsChanged(int slot) {
		super.onContentsChanged(slot);
		if(base != null)
			base.sendUpdates();
	}
	
}
