package lumaceon.mods.craftingparadise.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;


public interface IProxy
{
    public void preInit();
    public void init();
    public void registerBlockModel(Block block, String unlocalizedName);
    public void registerItemModel(Item item, String unlocalizedName);
    public void initSideHandlers();
    public void registerCustomSky();
}
