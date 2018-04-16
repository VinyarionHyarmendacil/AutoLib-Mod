package vinyarion.autolib.server;

import java.net.URL;
import java.util.UUID;

import vinyarion.autolib.common.ALMod;
import vinyarion.autolib.common.DataManager;
import vinyarion.autolib.common.DataManagerFileEntry;

public class ServerSettings {
	
	public static final DataManagerFileEntry alServerConfig = DataManager.getOrNew(ALMod.MODID + "/config.dat");
	
	public static void setServerUUID(UUID uuid) {
		alServerConfig.getTag().setString("server-uuid", uuid.toString());
	}
	
	public static UUID getServerUUID() {
		return UUID.fromString(alServerConfig.getTag().getString("server-uuid"));
	}
	
	public static void setAssetURL(String url) {
		try {
			new URL(url);
		} catch(Exception e) {
			e.printStackTrace();
			ALMod.instance.log.warning("Url " + url + " is not valid!");
			return;
		}
		alServerConfig.getTag().setString("server-asset-url", url);
	}
	
	public static String getAssetURL() {
		return alServerConfig.getTag().getString("server-asset-url");
	}
	
}
