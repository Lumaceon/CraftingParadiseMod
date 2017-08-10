package lumaceon.mods.craftingparadise.item.modules;

import lumaceon.mods.craftingparadise.init.ModPlanetModules;
import lumaceon.mods.craftingparadise.item.ItemMod;
import lumaceon.mods.craftingparadise.planet.PlanetModule;
import net.minecraft.item.ItemStack;

public class ItemPlanetModuleWorldBloody extends ItemMod implements IWorldBuilderModule
{
    public ItemPlanetModuleWorldBloody(int maxStack, int maxDamage, String name) {
        super(maxStack, maxDamage, name);
    }

    @Override
    public ModuleType getModuleType(ItemStack stack) {
        return ModuleType.WORLD;
    }

    @Override
    public PlanetModule getModule(ItemStack stack) {
        return ModPlanetModules.bloodWorld;
    }
}
