package lumaceon.mods.craftingparadise.network;

import lumaceon.mods.craftingparadise.lib.Reference;
import lumaceon.mods.craftingparadise.network.message.*;
import lumaceon.mods.craftingparadise.network.message.handler.HandlerWorldCraft;
import lumaceon.mods.craftingparadise.network.message.handler.dummy.DummyHandlerBiomeChange;
import lumaceon.mods.craftingparadise.network.message.handler.dummy.DummyHandlerClientPlanetChange;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
    public static final int BIOME_CHANGE = 1;
    public static final int WORLD_CRAFT = 2;
    public static final int WORLD_CLIENT_CHANGE = 3;

    public static void init()
    {
        //Note: the side passed in is the RECEIVING side.
        INSTANCE.registerMessage(DummyHandlerBiomeChange.class, MessageBiomeChange.class, BIOME_CHANGE, Side.SERVER); //CLIENT
        INSTANCE.registerMessage(HandlerWorldCraft.class, MessageWorldCraft.class, WORLD_CRAFT, Side.SERVER);
        INSTANCE.registerMessage(DummyHandlerClientPlanetChange.class, MessageClientPlanetChange.class, WORLD_CLIENT_CHANGE, Side.SERVER); //CLIENT
    }
}
