package vinyarion.autolib.common;

import vinyarion.autolib.api.ServerSideState;

public class ServerSideDefaultState extends ServerSideState {
	
	public static final ServerSideDefaultState INSTANCE = new ServerSideDefaultState();
	
	private ServerSideDefaultState() { }
	
	public Object provideEventListenerObject() {
		return null;
	}
	
	public void initialize() {
		
	}
	
}
