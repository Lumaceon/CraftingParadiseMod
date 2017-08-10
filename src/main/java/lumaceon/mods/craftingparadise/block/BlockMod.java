package lumaceon.mods.craftingparadise.block;

import lumaceon.mods.craftingparadise.CraftingParadise;
import lumaceon.mods.craftingparadise.util.ISimpleNamed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMod extends Block implements ISimpleNamed
{
    String simpleName;

    public BlockMod(Material blockMaterial, String name) {
        super(blockMaterial);
        this.setCreativeTab(CraftingParadise.instance.CREATIVE_TAB);
        this.setHardness(3.0F);
        this.simpleName = name;
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getSimpleName() {
        return this.simpleName;
    }
}
