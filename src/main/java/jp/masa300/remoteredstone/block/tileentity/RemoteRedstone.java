package jp.masa300.remoteredstone.block.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public abstract class RemoteRedstone extends TileEntityCustom {
    protected String remoteRSId;
    protected RemoteRSType type;

    public RemoteRedstone(RemoteRSType type) {
        this.remoteRSId = "";
        this.type = type;
    }

    public String getRemoteRSId() {
        return this.remoteRSId;
    }

    public void setRemoteRSId(String id) {
        this.remoteRSId = id;
    }

    public RemoteRSType getRemoteRSType() {
        return this.type;
    }

    public enum RemoteRSType {
        SENDER("Redstone Sender"),
        RECEIVER("Redstone Receiver");

        public final String name;

        private RemoteRSType(String name) {
            this.name = name;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.remoteRSId = nbt.getString("remoteRSId");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        System.out.println("RemoteRedstone#writeToNBT: " + this.remoteRSId);
        nbt.setString("remoteRSId", this.remoteRSId);
    }
}

