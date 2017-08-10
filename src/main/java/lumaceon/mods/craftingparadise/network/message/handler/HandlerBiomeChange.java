package lumaceon.mods.craftingparadise.network.message.handler;

import lumaceon.mods.craftingparadise.network.message.MessageBiomeChange;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class HandlerBiomeChange implements IMessageHandler<MessageBiomeChange, IMessage>
{
    @Override
    public IMessage onMessage(final MessageBiomeChange message, final MessageContext ctx)
    {
        if(ctx.side != Side.CLIENT)
        {
            System.err.println("MessageBiomeChange received on wrong side:" + ctx.side);
            return null;
        }

        Minecraft minecraft = Minecraft.getMinecraft();
        final WorldClient worldClient = minecraft.world;
        minecraft.addScheduledTask(() -> processMessage(message, ctx, worldClient));
        return null;
    }

    private void processMessage(MessageBiomeChange message, MessageContext ctx, WorldClient world)
    {
        Biome targetBiome = Biome.getBiomeForId(message.biomeID);
        if(targetBiome == null)
            return;

        byte[] currentArray;
        for(byte nX = -5; nX <= 5; nX++)
        {
            for(byte nZ = -5; nZ <= 5; nZ++)
            {
                Chunk c = world.getChunkFromBlockCoords(new BlockPos(message.changeCenter.getX() + nX*16, 0, message.changeCenter.getZ() + nZ*16));
                currentArray = c.getBiomeArray();
                for(int n = 0; n < currentArray.length; n++)
                {
                    currentArray[n] = (byte) Biome.getIdForBiome(targetBiome);
                }
                c.setBiomeArray(currentArray);
                c.setModified(true);
            }
        }
    }
}
