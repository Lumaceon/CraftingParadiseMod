package lumaceon.mods.craftingparadise.client.gui;

import lumaceon.mods.craftingparadise.CraftingParadise;
import lumaceon.mods.craftingparadise.inventory.ContainerWorldMachine;
import lumaceon.mods.craftingparadise.tile.TileWorldMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler
{
    public GuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(CraftingParadise.instance, this);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 0)
        {
            TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
            if(te != null && te instanceof TileWorldMachine)
                return new ContainerWorldMachine(player, (TileWorldMachine) te);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 0)
        {
            TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
            if(te != null && te instanceof TileWorldMachine)
                return new GuiWorldMachine(player, (TileWorldMachine) te);
        }
        return null;
    }
}
