package jp.masa300.remoteredstone.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketNBT implements IMessage {
//    private static final byte Type_Entity = 0;
//    private static final byte Type_TileEntity = 1;
//    private static final byte Type_PlayerItem = 2;
    public NBTTagCompound nbtData;

    public PacketNBT() {
    }

    public PacketNBT(TileEntity tileEntity, boolean toClient) {
        this.nbtData = new NBTTagCompound();
        tileEntity.writeToNBT(this.nbtData);
        this.nbtData.setBoolean("ToClient", toClient);
//        this.nbtData.setByte("DataType", Type_TileEntity);
        this.nbtData.setInteger("XPos", tileEntity.xCoord);
        this.nbtData.setInteger("YPos", tileEntity.yCoord);
        this.nbtData.setInteger("ZPos", tileEntity.zCoord);
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        this.nbtData = ByteBufUtils.readTag(buffer);
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        ByteBufUtils.writeTag(buffer, this.nbtData);
    }

    protected void onGetPacket(World world) {
        if(world == null) {
            return;
        }

        int x = this.nbtData.getInteger("XPos");
        int y = this.nbtData.getInteger("YPos");
        int z = this.nbtData.getInteger("ZPos");
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity != null) {
            tileEntity.readFromNBT(this.nbtData);
            if(!world.isRemote) {
                tileEntity.markDirty();
            }
        }
    }
}
