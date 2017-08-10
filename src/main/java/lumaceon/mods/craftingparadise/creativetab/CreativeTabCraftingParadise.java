package lumaceon.mods.craftingparadise.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTabCraftingParadise extends CreativeTabs
{
    public CreativeTabCraftingParadise(String label)
    {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(Items.NETHER_STAR);
    }
}
