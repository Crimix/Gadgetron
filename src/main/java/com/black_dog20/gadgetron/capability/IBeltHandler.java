package com.black_dog20.gadgetron.capability;

import com.black_dog20.gadgetron.storage.CustomItemHandlerBase;

public interface IBeltHandler extends IBaseCapability<IBeltHandler> {

	void setHasMagnetOn(boolean hasMagnetOn);
	boolean getHasMagnetOn();
	void setHasBelt(boolean hasBelt);
	boolean getHasBelt();
	void setSneakDeactivate(boolean sneakDeactivate);
	boolean getSneakDeactivate();
	void setTempOff(boolean tempOff);
	boolean getTempOff();
	void setInventory(CustomItemHandlerBase inventory);
	CustomItemHandlerBase getInventory();
	void setVisible(boolean visible);
	boolean isVisible();
	
	void markDirty();
}
