package lumaceon.mods.craftingparadise.item.modules;

import lumaceon.mods.craftingparadise.init.ModPlanetModules;
import lumaceon.mods.craftingparadise.item.ItemMod;
import lumaceon.mods.craftingparadise.planet.PlanetModule;
import net.minecraft.item.ItemStack;

public class ItemPlanetModuleAtmosphereThickRed extends ItemMod implements IWorldBuilderModule
{
    public ItemPlanetModuleAtmosphereThickRed(int maxStack, int maxDamage, String name) {
        super(maxStack, maxDamage, name);
    }

    @Override
    public ModuleType getModuleType(ItemStack stack) {
        return ModuleType.ATMOSPHERE;
    }

    @Override
    public PlanetModule getModule(ItemStack stack) {
        return ModPlanetModules.thickRedAtmosphere;
    }
}
