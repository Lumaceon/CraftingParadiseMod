package lumaceon.mods.craftingparadise.network.message.handler.dummy;

import lumaceon.mods.craftingparadise.network.message.MessageClientPlanetChange;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class DummyHandlerClientPlanetChange implements IMessageHandler<MessageClientPlanetChange, IMessage>
{
    @Override
    public IMessage onMessage(MessageClientPlanetChange message, net.minecraftforge.fml.common.network.simpleimpl.MessageContext ctx) {
        return null;
    }
}
