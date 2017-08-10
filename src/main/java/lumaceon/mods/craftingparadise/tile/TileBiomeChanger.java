package lumaceon.mods.craftingparadise.tile;

import lumaceon.mods.craftingparadise.network.PacketHandler;
import lumaceon.mods.craftingparadise.network.message.MessageBiomeChange;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

public class TileBiomeChanger extends TileMod implements ITickable
{
    private Biome targetBiome;
    private int worldId;

    public byte[] currentArray;
    private long updateCount = 0;

    public void setTargetBiome(Biome targetBiome) {
        this.targetBiome = targetBiome;
        markDirty();
    }

    public Biome getTargetBiome() {
        return targetBiome;
    }

    public void setTargetWorld(int targetWorld) {
        this.worldId = targetWorld;
        markDirty();
    }

    public int getTargetWorld() {
        return this.worldId;
    }

    public void changeBiomeArea(World world, BlockPos thisPosition)
    {
        if(world == null) { return; }
        if(targetBiome == null) { return; }
        if(world.provider.getDimension() != worldId) { return; } //Do nothing unless this is the specified world.

        for(byte nX = -5; nX <= 5; nX++)
        {
            for(byte nZ = -5; nZ <= 5; nZ++)
            {
                Chunk c = world.getChunkFromBlockCoords(new BlockPos(thisPosition.getX() + nX*16, 0, thisPosition.getZ() + nZ*16));
                this.currentArray = c.getBiomeArray();
                for(int n = 0; n < this.currentArray.length; n++)
                {
                    this.currentArray[n] = (byte) Biome.getIdForBiome(targetBiome);
                }
                c.setBiomeArray(this.currentArray);
                c.setModified(true);
            }
        }
        PacketHandler.INSTANCE.sendToDimension(new MessageBiomeChange((byte) Biome.getIdForBiome(targetBiome), this.getPos()), world.provider.getDimension());
    }

    /*public void sendBiomeChangePacket(int dimId, int x, int y, int z, byte biomeId)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(13);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try
        {
            outputStream.writeInt(x);
            outputStream.writeInt(y);
            outputStream.writeInt(z);
            outputStream.writeByte(biomeId);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload(Reference.CHANNEL_BIOME_CHANGE, bos.toByteArray());
        PacketDispatcher.sendPacketToAllAround(x, y, z, 256, dimId, packet);
    }*/

    @Override
    public void update()
    {
        updateCount++;

        if(updateCount % 50 == 0)
        {
            World world = this.getWorld();
            if(world != null && !world.isRemote && world.provider.getDimension() == this.worldId)
            {
                if(!world.getBiome(this.getPos()).equals(targetBiome))
                {
                    changeBiomeArea(world, this.getPos());
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        if(nbt.hasKey("biome_id"))
            this.targetBiome = Biome.getBiome(nbt.getByte("biome_id"), Biomes.OCEAN);
        if(nbt.hasKey("world_id"))
            this.worldId = nbt.getInteger("world_id");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        if(targetBiome != null)
            nbt.setByte("biome_id", (byte) Biome.getIdForBiome(targetBiome));
        nbt.setInteger("world_id", this.worldId);
        return nbt;
    }
}
