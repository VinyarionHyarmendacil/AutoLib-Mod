package vinyarion.autolib.common;

import java.io.File;

public class DataManagerFolderEntry extends DataManagerEntry {
	
	public DataManagerFolderEntry(String n, File f) {
		this.name = n;
		this.file = f;
	}
	
	public File fileIn(String name) {
		return new File(this.file, name);
	}
	
}
