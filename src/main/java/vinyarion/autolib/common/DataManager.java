package vinyarion.autolib.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;

public class DataManager {
	
	static final Map<String, DataManagerEntry> theMap = new HashMap<String, DataManagerEntry>();
	
	public static void remove(String name) {
		theMap.remove(name);
	}
	
	public static DataManagerFileEntry getOrNewFile(String name) {
		DataManagerEntry entry = theMap.get(name);
		if(entry == null) {
			theMap.put(name, entry = new DataManagerFileEntry(name, getFileForName(name)));
		}
		if(!(entry instanceof DataManagerFileEntry)) {
			return null;
		}
		return (DataManagerFileEntry) entry;
	}
	
	public static DataManagerFolderEntry getOrNewFolder(String name) {
		DataManagerEntry entry = theMap.get(name);
		if(entry == null) {
			theMap.put(name, entry = new DataManagerFolderEntry(name, getFileForName(name)));
		}
		if(!(entry instanceof DataManagerFolderEntry)) {
			return null;
		}
		return (DataManagerFolderEntry) entry;
	}
	
	public static File getFileForName(String name) {
		File ret = ALMod.instance.proxy.getRunDirectory();
		for(String file : name.split("\\/")) {
			ret = new File(ret, file);
		}
		return ret;
	}
	
}
