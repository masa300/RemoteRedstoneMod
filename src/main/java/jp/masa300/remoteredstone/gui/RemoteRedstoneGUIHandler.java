package jp.masa300.remoteredstone.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import jp.masa300.remoteredstone.RemoteRedstoneCore;
import jp.masa300.remoteredstone.block.tileentity.RemoteRedstone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class RemoteRedstoneGUIHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == RemoteRedstoneCore.guiId_RemoteRedstone) {
            return new GUIRemoteRedstone((RemoteRedstone) player.worldObj.getTileEntity(x, y, z));
        }
        return null;
    }
}
