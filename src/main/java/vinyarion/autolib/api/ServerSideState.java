package vinyarion.autolib.api;

import vinyarion.autolib.common.ALMessageHandler;
import vinyarion.autolib.common.ServerSideDefaultState;
import vinyarion.autolib.common.ALMessageHandler.ALMessage;

public abstract class ServerSideState {
	
	private static ServerSideState currentState = ServerSideDefaultState.INSTANCE;
	
	public static ServerSideState getState() {
		return currentState;
	}
	
	public static void setState(ServerSideState newState) {
		currentState = newState != null ? newState : ServerSideDefaultState.INSTANCE;
	}
	
	public abstract Object provideEventListenerObject();
	
	public abstract void initialize();
	
}
