package jp.masa300.remoteredstone.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import jp.masa300.remoteredstone.RemoteRedstoneCore;
import jp.masa300.remoteredstone.block.tileentity.RemoteRedstone;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketRemoteRedstone extends PacketTileEntity implements IMessageHandler<PacketRemoteRedstone, IMessage> {
    private String remoteRSId;

    public PacketRemoteRedstone() {

    }

    public PacketRemoteRedstone(TileEntity tileEntity, String remoteRSId) {
        super(tileEntity);
        this.remoteRSId = remoteRSId;
    }

    @Override
    protected void write(ByteBuf buffer) {
        ByteBufUtils.writeUTF8String(buffer, this.remoteRSId);
    }

    @Override
    protected void read(ByteBuf buffer) {
        this.remoteRSId = ByteBufUtils.readUTF8String(buffer);
    }


    @Override
    public IMessage onMessage(PacketRemoteRedstone message, MessageContext ctx) {
        World world = ctx.getServerHandler().playerEntity.worldObj;
        RemoteRedstone tile = (RemoteRedstone) message.getTileEntity(world);
        tile.setRemoteRSId(message.remoteRSId);
        RemoteRedstoneCore.NETWORK_WRAPPER.sendToAll(new PacketNBT(tile, true));
        tile.markDirty();
        return null;
    }
}
