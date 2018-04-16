package vinyarion.autolib.client;

import vinyarion.autolib.common.ALMod;
import vinyarion.autolib.common.DataManager;
import vinyarion.autolib.common.DataManagerFileEntry;
import vinyarion.autolib.common.DataManagerFolderEntry;

public class ClientSettings {
	
	public static final DataManagerFileEntry alClientConfig = DataManager.getOrNewFile(ALMod.MODID + "/config.dat");
	
	public static final DataManagerFolderEntry serverModDir = DataManager.getOrNewFolder(ALMod.MODID + "/servermods");
	
	public static final DataManagerFolderEntry alWorkingUpdater = DataManager.getOrNewFolder(ALMod.MODID + "/working");
	
	private static ClientSyncedSettings syncedSettings;
	
	public static ClientSyncedSettings getSyncedSettings() {
		return syncedSettings;
	}
	
	public static void setSyncedSettings(ClientSyncedSettings newSettings) {
		syncedSettings = newSettings;
	}
	
}
