package lumaceon.mods.craftingparadise.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageClientPlanetChange implements IMessage
{
    public int core, mantle, crust, land, worldType, atmosphere = -1;

    public MessageClientPlanetChange() {}

    public MessageClientPlanetChange(int core, int mantle, int crust, int land, int worldType, int atmosphere) {
        this.core = core;
        this.mantle = mantle;
        this.crust = crust;
        this.land = land;
        this.worldType = worldType;
        this.atmosphere = atmosphere;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(core);
        buf.writeInt(mantle);
        buf.writeInt(crust);
        buf.writeInt(land);
        buf.writeInt(worldType);
        buf.writeInt(atmosphere);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.core = buf.readInt();
        this.mantle = buf.readInt();
        this.crust = buf.readInt();
        this.land = buf.readInt();
        this.worldType = buf.readInt();
        this.atmosphere = buf.readInt();
    }
}
