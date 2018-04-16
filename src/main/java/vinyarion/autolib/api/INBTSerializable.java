package vinyarion.autolib.api;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTSerializable {
	
	public void readFrom(NBTTagCompound nbt);
	
	public void writeTo(NBTTagCompound nbt);
	
}
