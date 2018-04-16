package vinyarion.autolib.common;

import java.io.File;
import java.util.UUID;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import vinyarion.autolib.common.ALMessageHandler.ALMessage;

public interface IProxy {
	
	public void onJoining(EntityPlayer player, String serveruuid, String asseturl);
	
	public File getRunDirectory();
	
	public ALMessage onMessage(ALMessage message, MessageContext ctx);
	
}
