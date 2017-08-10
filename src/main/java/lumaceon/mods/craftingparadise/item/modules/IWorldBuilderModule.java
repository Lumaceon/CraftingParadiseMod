package lumaceon.mods.craftingparadise.item.modules;

import lumaceon.mods.craftingparadise.planet.PlanetModule;
import net.minecraft.item.ItemStack;

public interface IWorldBuilderModule
{
    public ModuleType getModuleType(ItemStack stack);

    public PlanetModule getModule(ItemStack stack);

    public enum ModuleType
    {
        CORE, MANTLE, CRUST, LANDSCAPE, WORLD, ATMOSPHERE
    }
}
