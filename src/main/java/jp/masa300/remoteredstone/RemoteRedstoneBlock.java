package jp.masa300.remoteredstone;

import cpw.mods.fml.common.registry.GameRegistry;
import jp.masa300.remoteredstone.block.RSReceiver;
import jp.masa300.remoteredstone.block.RSSender;
import net.minecraft.block.Block;

public class RemoteRedstoneBlock {
    public static Block blockRSSender;
    public static Block blockRSReceiver;

    public void preInit() {
        GameRegistry.registerBlock(blockRSSender = new RSSender(), "RSSender");
        GameRegistry.registerBlock(blockRSReceiver = new RSReceiver(), "RSReceiver");
    }
}
