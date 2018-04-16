package vinyarion.autolib.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModClassLoader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import vinyarion.autolib.api.ServerSideState;
import vinyarion.autolib.common.ALMod;
import vinyarion.autolib.common.IProxy;
import vinyarion.autolib.common.ALMessageHandler.ALMessage;

public class ClientProxy implements IProxy {
	
	public void onJoining(EntityPlayer player, String serveruuid, String asseturl) {
		try {
			URL url = new URL(asseturl);
			File serverConfig = ClientSettings.alWorkingUpdater.fileIn(serveruuid);
			ClientSyncedSettings oldsettings = new ClientSyncedSettings(serverConfig);
			FileUtils.copyURLToFile(url, serverConfig);
			ClientSyncedSettings newsettings = new ClientSyncedSettings(serverConfig);
			File serverMod = ClientSettings.serverModDir.fileIn(serveruuid);
			boolean update = false;
			if(newsettings.isLaterThan(oldsettings)) {
				update = true;
			} else if(oldsettings.isLaterThan(newsettings)) {
				if(oldsettings.isBackdateEnabled()) {
					update = true;
				} else if(newsettings.isBackdateForced()) {
					update = true;
				}
			}
			if(update) {
				FileUtils.copyURLToFile(url, serverMod, 10000, 10000);
			}
			ClientSyncedSettings settings = update ? newsettings : oldsettings;
			ModClassLoader mcl = (ModClassLoader) Loader.instance().getModClassLoader();
			mcl.addFile(serverMod);
			Class entrypoint = Class.forName(settings.getStateClass());
			if(!entrypoint.isAssignableFrom(ServerSideState.class)) {
				throw new ClassCastException("Cannot cast " + entrypoint.getName() + " to " + ServerSideState.class.getName());
			}
			ServerSideState state = (ServerSideState) entrypoint.newInstance();
			ServerSideState.setState(state);
			state.initialize();
		} catch(Exception e) {
			ServerSideState.setState(null);
			ALMod.instance.log.warning("Unable to load server's mod!");
			e.printStackTrace();
			if(Minecraft.getMinecraft().theWorld != null) {
				Minecraft.getMinecraft().theWorld.sendQuittingDisconnectingPacket();
			}
			Minecraft.getMinecraft().loadWorld(null);
			Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
		}
	}
	
	public File getRunDirectory() {
		return new File(Minecraft.getMinecraft().mcDataDir, ALMod.MODID);
	}
	
	public ALMessage onMessage(ALMessage message, MessageContext ctx) {
		NBTTagCompound nbt = message.getTag();
		String action = nbt.getString("action");
		if(action.equals("joinpacket")) {
			String serveruuid = nbt.getString("server-uuid");
			String asseturl = nbt.getString("server-asset-url");
			this.onJoining(Minecraft.getMinecraft().thePlayer, serveruuid, asseturl);
		}
		return null;
	}
	
}
