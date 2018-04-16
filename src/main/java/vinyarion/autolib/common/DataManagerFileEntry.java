package vinyarion.autolib.common;

import java.io.File;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class DataManagerFileEntry extends DataManagerEntry {
	
	private NBTTagCompound tag;
	
	DataManagerFileEntry(String n, File f) {
		this(n, f, new NBTTagCompound());
	}
	
	DataManagerFileEntry(String n, File f, NBTTagCompound defaultTag) {
		this.name = n;
		this.file = f;
		if(!file.exists()) {
			this.tag = defaultTag;
			this.save();
		} else {
			try {
				this.tag = CompressedStreamTools.read(file);
			} catch (IOException e) {
				this.tag = defaultTag;
			}
		}
	}
	
	public NBTTagCompound getTag() {
		return this.tag;
	}
	
	void save() {
		try {
			CompressedStreamTools.safeWrite(tag, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
