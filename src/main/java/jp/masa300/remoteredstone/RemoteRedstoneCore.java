package jp.masa300.remoteredstone;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import jp.masa300.remoteredstone.block.tileentity.TileEntityRSReceiver;
import jp.masa300.remoteredstone.block.tileentity.TileEntityRSSender;
import jp.masa300.remoteredstone.gui.RemoteRedstoneGUIHandler;
import jp.masa300.remoteredstone.network.PacketRemoteRedstone;

@Mod(modid = RemoteRedstoneCore.MODID, version = RemoteRedstoneCore.VERSION, name = RemoteRedstoneCore.MODID)
public class RemoteRedstoneCore {
    public static final String MODID = "RemoteRedstoneMod";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MODID)
    public static RemoteRedstoneCore INSTANCE;
    public static int guiId_RemoteRedstone = 0;

    public static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new RemoteRedstoneGUIHandler());
        NETWORK_WRAPPER.registerMessage(PacketRemoteRedstone.class, PacketRemoteRedstone.class, 0, Side.SERVER);
        GameRegistry.registerTileEntity(TileEntityRSSender.class, "TE_RSSender");
        GameRegistry.registerTileEntity(TileEntityRSReceiver.class, "TE_RSReceiver");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        new RemoteRedstoneBlock().preInit();
    }
}
