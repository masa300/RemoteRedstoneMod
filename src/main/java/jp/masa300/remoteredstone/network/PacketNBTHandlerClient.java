package jp.masa300.remoteredstone.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class PacketNBTHandlerClient implements IMessageHandler<PacketNBT, IMessage> {

    @Override
    public IMessage onMessage(PacketNBT message, MessageContext ctx) {
        if(message.nbtData == null) {
            return null;
        }
        if(message.nbtData.getBoolean("ToClient")) {
            World world = Minecraft.getMinecraft().theWorld;
            message.onGetPacket(world);
        }
        return null;
    }
}
