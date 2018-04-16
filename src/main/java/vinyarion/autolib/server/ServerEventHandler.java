package vinyarion.autolib.server;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import vinyarion.autolib.common.ALMod;

public class ServerEventHandler {
	
	@SubscribeEvent
	public void login(PlayerLoggedInEvent e) {
		ALMod.instance.proxy.onJoining(e.player, ServerSettings.getServerUUID().toString(), ServerSettings.getAssetURL());
	}
	
	@SubscribeEvent
	public void logout(PlayerLoggedOutEvent e) {
	}
	
}
