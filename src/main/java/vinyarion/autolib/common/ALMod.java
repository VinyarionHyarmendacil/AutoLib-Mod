package vinyarion.autolib.common;

import java.util.logging.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = ALMod.MODID, version = ALMod.VERSION)
public class ALMod {
	
	public static final String MODID = "autolib";
	public static final String VERSION = "1.0";
	
	@Mod.Instance(MODID)
	public static ALMod instance;
	
	public static final SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("autolib_");
	
	public static ALDelagatingItem theItem = new ALDelagatingItem();
	
	public final Logger log = Logger.getLogger("Autolib");
	
	@SidedProxy(modId = MODID, serverSide = "vinyarion.autolib.server.ServerProxy", clientSide = "vinyarion.autolib.client.ClientProxy")
	public IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.onPreInit(e);
		int disc = 0;
		wrapper.registerMessage(ALMessageHandler.class, ALMessageHandler.ALMessage.class, disc++, Side.CLIENT);
		wrapper.registerMessage(ALMessageHandler.class, ALMessageHandler.ALMessage.class, disc++, Side.SERVER);
		GameRegistry.registerItem(theItem, theItem.getUnlocalizedName());
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.onInit(e);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.onPostInit(e);
	}
	
}
