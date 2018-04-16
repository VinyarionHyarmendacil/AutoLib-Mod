package vinyarion.autolib.common;

import java.io.File;

public abstract class DataManagerEntry {

	protected File file;
	protected String name;
	
	public final void destroy() {
		DataManager.remove(name);
	}
	
}
