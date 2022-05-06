package jp.masa300.remoteredstone.block;

import jp.masa300.remoteredstone.RemoteRedstoneCore;
import jp.masa300.remoteredstone.block.tileentity.TileEntityRSSender;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class RSSender extends BlockContainer {

    public RSSender() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.setBlockName(RemoteRedstoneCore.MODID + ":" + "RSSender");
        this.setBlockTextureName(RemoteRedstoneCore.MODID + ":" + "RSSender");
        this.setStepSound(Block.soundTypeStone);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float posX, float posY, float posZ) {
        player.openGui(RemoteRedstoneCore.INSTANCE, RemoteRedstoneCore.guiId_RemoteRedstone, player.worldObj, x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityRSSender();
    }
}
