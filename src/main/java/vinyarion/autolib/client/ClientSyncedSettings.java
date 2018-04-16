package vinyarion.autolib.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ClientSyncedSettings {
	
	private Properties props;
	
	public ClientSyncedSettings(File file) throws FileNotFoundException, IOException {
		this.props = new Properties();
		this.props.load(new FileInputStream(file));
	}
	
	public int[] getVersion() {
		String version = this.props.getProperty("version");
		String[] sub = version.split("\\.");
		int[] ret = new int[sub.length];
		for(int i = 0; i < sub.length; i++) {
			int v = 0;
			try {
				v = Integer.parseInt(sub[i]);
			} catch(Exception e) { }
			ret[i] = v;
		}
		return ret;
	}
	
	public boolean isLaterThan(ClientSyncedSettings other) {
		int[] thisv = this.getVersion();
		int[] thatv = other.getVersion();
		for(int i = 0; i < Math.min(thisv.length, thatv.length); i++) {
			if(thisv[i] > thatv[i]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isBackdateEnabled() {
		return "true".equalsIgnoreCase(this.props.getProperty("backdate-enabled"));
	}
	
	public boolean isBackdateForced() {
		return "true".equalsIgnoreCase(this.props.getProperty("backdate-forced"));
	}
	
	public String getStateClass() {
		return this.props.getProperty("autolib-state-class");
	}
	
}
