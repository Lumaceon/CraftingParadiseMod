package lumaceon.mods.craftingparadise.network;

import lumaceon.mods.craftingparadise.network.message.MessageBiomeChange;
import lumaceon.mods.craftingparadise.network.message.MessageClientPlanetChange;
import lumaceon.mods.craftingparadise.network.message.handler.HandlerBiomeChange;
import lumaceon.mods.craftingparadise.network.message.handler.HandlerClientPlanetChange;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandlerClient
{
    public static void init()
    {
        PacketHandler.INSTANCE.registerMessage(HandlerBiomeChange.class, MessageBiomeChange.class, PacketHandler.BIOME_CHANGE, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(HandlerClientPlanetChange.class, MessageClientPlanetChange.class, PacketHandler.WORLD_CLIENT_CHANGE, Side.CLIENT);
    }
}
