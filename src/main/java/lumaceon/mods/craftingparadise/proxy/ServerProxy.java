package lumaceon.mods.craftingparadise.proxy;

import lumaceon.mods.craftingparadise.client.gui.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ServerProxy extends CommonProxy
{
    @Override
    public void preInit() {
        new GuiHandler();
    }

    @Override
    public void init() {}
    @Override
    public void registerBlockModel(Block block, String unlocalizedName) {}
    @Override
    public void registerItemModel(Item item, String unlocalizedName) {}
    @Override
    public void initSideHandlers() {}
    @Override
    public void registerCustomSky() {}
}
