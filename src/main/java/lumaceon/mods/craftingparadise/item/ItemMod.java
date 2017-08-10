package lumaceon.mods.craftingparadise.item;

import lumaceon.mods.craftingparadise.CraftingParadise;
import lumaceon.mods.craftingparadise.util.ISimpleNamed;
import net.minecraft.item.Item;

public class ItemMod extends Item implements ISimpleNamed
{
    String simpleName;

    public ItemMod(int maxStack, int maxDamage, String name)
    {
        super();
        this.setMaxStackSize(maxStack);
        this.setMaxDamage(maxDamage);
        this.setNoRepair();
        this.setCreativeTab(CraftingParadise.instance.CREATIVE_TAB);
        this.simpleName = name;
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getSimpleName() {
        return simpleName;
    }
}
