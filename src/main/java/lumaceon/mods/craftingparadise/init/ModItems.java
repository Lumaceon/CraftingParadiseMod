package lumaceon.mods.craftingparadise.init;

import lumaceon.mods.craftingparadise.CraftingParadise;
import lumaceon.mods.craftingparadise.item.modules.*;
import lumaceon.mods.craftingparadise.util.ISimpleNamed;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;

public class ModItems
{
    public static ArrayList<Item> itemsForModel = new ArrayList<>(20);

    public static Item planetModuleIronCore;

    public static Item planetModuleLavaMantle;

    public static Item planetModuleStoneCrust;

    public static Item planetModuleDirtLandscape;

    public static Item planetModuleHomeWorld;
    public static Item planetModuleTropicalWorld;
    public static Item planetModuleSwampWorld;
    public static Item planetModuleSeasonalWorld;
    public static Item planetModuleLushWorld;
    public static Item planetModuleLavaWorld;
    public static Item planetModulePollutedWorld;
    public static Item planetModuleFrozenWorld;
    public static Item planetModuleFlatWorld;
    public static Item planetModuleMagicWorld;
    public static Item planetModuleBloodWorld;
    public static Item planetModuleAridWorld;

    public static Item planetModuleBlueAtmosphere;
    public static Item planetModuleThickBlueAtmosphere;
    public static Item planetModuleThickRedAtmosphere;
    public static Item planetModulePollutedAtmosphere;
    public static Item planetModuleGoldenAtmosphere;
    public static Item planetModuleSwampyAtmosphere;
    public static Item planetModuleMagicalAtmosphere;
    public static Item planetModuleIcyAtmosphere;

    public static void init()
    {
        planetModuleIronCore = new ItemPlanetModuleIronCore(1, 100, "planet_module_iron_core");
        register(planetModuleIronCore);

        planetModuleLavaMantle = new ItemPlanetModuleMantleLava(1, 100, "planet_module_lava_mantle");
        register(planetModuleLavaMantle);

        planetModuleStoneCrust = new ItemPlanetModuleCrustStone(1, 100, "planet_module_stone_crust");
        register(planetModuleStoneCrust);

        planetModuleDirtLandscape = new ItemPlanetModuleLandDirt(1, 100, "planet_module_dirt_landscape");
        register(planetModuleDirtLandscape);

        planetModuleHomeWorld = new ItemPlanetModuleWorldStandard(1, 100, "planet_module_standard_world");
        register(planetModuleHomeWorld);

        planetModuleTropicalWorld = new ItemPlanetModuleWorldTropical(1, 100, "planet_module_tropical_world");
        register(planetModuleTropicalWorld);

        planetModuleSwampWorld = new ItemPlanetModuleWorldSwamp(1, 100, "planet_module_swamp_world");
        register(planetModuleSwampWorld);

        planetModuleSeasonalWorld = new ItemPlanetModuleWorldSeasonal(1, 100, "planet_module_seasonal_world");
        register(planetModuleSeasonalWorld);

        planetModuleLushWorld = new ItemPlanetModuleWorldLush(1, 100, "planet_module_lush_world");
        register(planetModuleLushWorld);

        planetModuleLavaWorld = new ItemPlanetModuleWorldMagmatic(1, 100, "planet_module_magma_world");
        register(planetModuleLavaWorld);

        planetModulePollutedWorld = new ItemPlanetModuleWorldDead(1, 100, "planet_module_polluted_world");
        register(planetModulePollutedWorld);

        planetModuleFrozenWorld = new ItemPlanetModuleWorldIcy(1, 100, "planet_module_frozen_world");
        register(planetModuleFrozenWorld);

        planetModuleFlatWorld = new ItemPlanetModuleWorldFlat(1, 100, "planet_module_flat_world");
        register(planetModuleFlatWorld);

        planetModuleMagicWorld = new ItemPlanetModuleWorldMagical(1, 100, "planet_module_magic_world");
        register(planetModuleMagicWorld);

        planetModuleBloodWorld = new ItemPlanetModuleWorldBloody(1, 100, "planet_module_blood_world");
        register(planetModuleBloodWorld);

        planetModuleAridWorld = new ItemPlanetModuleWorldArid(1, 100, "planet_module_arid_world");
        register(planetModuleAridWorld);

        planetModuleBlueAtmosphere = new ItemPlanetModuleAtmosphereBlue(1, 100, "planet_module_blue_atmosphere");
        register(planetModuleBlueAtmosphere);

        planetModuleThickBlueAtmosphere = new ItemPlanetModuleAtmosphereThickBlue(1, 100, "planet_module_thick_blue_atmosphere");
        register(planetModuleThickBlueAtmosphere);

        planetModuleThickRedAtmosphere = new ItemPlanetModuleAtmosphereThickRed(1, 100, "planet_module_thick_red_atmosphere");
        register(planetModuleThickRedAtmosphere);

        planetModulePollutedAtmosphere = new ItemPlanetModuleAtmosphereSickly(1, 100, "planet_module_polluted_atmosphere");
        register(planetModulePollutedAtmosphere);

        planetModuleGoldenAtmosphere = new ItemPlanetModuleAtmosphereGolden(1, 100, "planet_module_gold_atmosphere");
        register(planetModuleGoldenAtmosphere);

        planetModuleSwampyAtmosphere = new ItemPlanetModuleAtmosphereSwampy(1, 100, "planet_module_swamp_atmosphere");
        register(planetModuleSwampyAtmosphere);

        planetModuleMagicalAtmosphere = new ItemPlanetModuleAtmosphereMagic(1, 100, "planet_module_magical_atmosphere");
        register(planetModuleMagicalAtmosphere);

        planetModuleIcyAtmosphere = new ItemPlanetModuleAtmosphereCold(1, 100, "planet_module_cold_atmosphere");
        register(planetModuleIcyAtmosphere);
    }

    private static void register(Item item)
    {
        ForgeRegistries.ITEMS.register(item);
        itemsForModel.add(item);
    }

    public static void initModels() {
        if(itemsForModel != null)
        {
            for(Item item : itemsForModel)
                if(item != null && item instanceof ISimpleNamed)
                    CraftingParadise.proxy.registerItemModel(item, ((ISimpleNamed) item).getSimpleName());
            itemsForModel.clear();
        }
        itemsForModel = null;
    }
}
