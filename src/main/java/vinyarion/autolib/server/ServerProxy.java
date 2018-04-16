package vinyarion.autolib.server;

import java.io.File;
import java.util.UUID;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import vinyarion.autolib.common.ALMessageHandler;
import vinyarion.autolib.common.ALMod;
import vinyarion.autolib.common.IProxy;
import vinyarion.autolib.common.ALMessageHandler.ALMessage;

public class ServerProxy implements IProxy {
	
	public void onJoining(EntityPlayer player, String serveruuid, String asseturl) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("action", "joinpacket");
		nbt.setString("server-uuid", serveruuid);
		nbt.setString("server-asset-url", asseturl);
		ALMod.wrapper.sendTo(new ALMessage(nbt, true), (EntityPlayerMP) player);
	}
	
	public File getRunDirectory() {
		return MinecraftServer.getServer().getFile(ALMod.MODID);
	}
	
	public ALMessage onMessage(ALMessage message, MessageContext ctx) {
		return null;
	}
	
}
