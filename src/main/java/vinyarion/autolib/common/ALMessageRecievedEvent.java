package vinyarion.autolib.common;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

@Cancelable
public class ALMessageRecievedEvent extends Event {
	
	public ALMessageHandler.ALMessage returnMessage = null;
	
	public final ALMessageHandler.ALMessage recievedMessage;
	
	public final MessageContext context;
	
	public ALMessageRecievedEvent(ALMessageHandler.ALMessage message, MessageContext context) {
		this.recievedMessage = message;
		this.context = context;
	}
	
}
