package lumaceon.mods.craftingparadise.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageBiomeChange implements IMessage
{
    public byte biomeID;
    public BlockPos changeCenter;

    public MessageBiomeChange() {}

    public MessageBiomeChange(byte biomeID, BlockPos changeCenter) {
        this.biomeID = biomeID;
        this.changeCenter = changeCenter;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(biomeID);
        buf.writeInt(changeCenter.getX());
        buf.writeInt(changeCenter.getY());
        buf.writeInt(changeCenter.getZ());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        biomeID = buf.readByte();
        changeCenter = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }
}
