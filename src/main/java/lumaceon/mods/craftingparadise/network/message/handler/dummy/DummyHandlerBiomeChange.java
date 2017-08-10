package lumaceon.mods.craftingparadise.network.message.handler.dummy;

import lumaceon.mods.craftingparadise.network.message.MessageBiomeChange;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class DummyHandlerBiomeChange implements IMessageHandler<MessageBiomeChange, IMessage>
{
    @Override
    public IMessage onMessage(MessageBiomeChange message, net.minecraftforge.fml.common.network.simpleimpl.MessageContext ctx) {
        return null;
    }
}
