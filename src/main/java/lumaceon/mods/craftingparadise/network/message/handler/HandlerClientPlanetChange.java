package lumaceon.mods.craftingparadise.network.message.handler;

import lumaceon.mods.craftingparadise.planet.ActivePlanet;
import lumaceon.mods.craftingparadise.init.ModPlanetModules;
import lumaceon.mods.craftingparadise.network.message.MessageClientPlanetChange;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class HandlerClientPlanetChange implements IMessageHandler<MessageClientPlanetChange, IMessage>
{
    @Override
    public IMessage onMessage(final MessageClientPlanetChange message, final MessageContext ctx)
    {
        if(ctx.side != Side.CLIENT)
        {
            System.err.println("MessageClientPlanetChange received on wrong side:" + ctx.side);
            return null;
        }

        Minecraft minecraft = Minecraft.getMinecraft();
        final WorldClient worldClient = minecraft.world;
        minecraft.addScheduledTask(() -> processMessage(message, ctx, worldClient));
        return null;
    }

    private void processMessage(MessageClientPlanetChange message, MessageContext ctx, WorldClient world)
    {
        ActivePlanet.coreModule = ModPlanetModules.planetCoreModules.get(message.core);
        ActivePlanet.mantleModule = ModPlanetModules.planetMantleModules.get(message.mantle);
        ActivePlanet.crustModule = ModPlanetModules.planetCrustModules.get(message.crust);
        ActivePlanet.landscapeModule = ModPlanetModules.planetLandscapeModules.get(message.land);
        ActivePlanet.worldModule = ModPlanetModules.planetOceansModules.get(message.worldType);
        ActivePlanet.atmosphereModule = ModPlanetModules.planetAtmosphereModules.get(message.atmosphere);
    }
}
