package lumaceon.mods.craftingparadise.proxy;

import lumaceon.mods.craftingparadise.client.CustomSkyRenderer;
import lumaceon.mods.craftingparadise.client.ModelRegistry;
import lumaceon.mods.craftingparadise.client.gui.GuiHandler;
import lumaceon.mods.craftingparadise.network.PacketHandlerClient;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{
    private CustomSkyRenderer customSkyRenderer = new CustomSkyRenderer();

    @Override
    public void preInit() {
        new GuiHandler();
    }

    @Override
    public void init() {
        PacketHandlerClient.init();
    }

    @Override
    public void registerBlockModel(Block block, String unlocalizedName) {
        ModelRegistry.registerItemBlockModel(block, unlocalizedName);
    }

    @Override
    public void registerItemModel(Item item, String unlocalizedName) {
        ModelRegistry.registerItemModel(item, unlocalizedName);
    }

    @Override
    public void initSideHandlers()
    {

    }

    @Override
    public void registerCustomSky()
    {
        World world = Minecraft.getMinecraft().world;
        if(world != null)
        {
            world.provider.setSkyRenderer(customSkyRenderer);
        }
    }
}
