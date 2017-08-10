package lumaceon.mods.craftingparadise.init;

import lumaceon.mods.craftingparadise.lib.Reference;
import lumaceon.mods.craftingparadise.planet.PlanetModule;
import lumaceon.mods.craftingparadise.planet.PlanetModuleAtmosphere;
import lumaceon.mods.craftingparadise.planet.PlanetModuleWorld;

import java.util.ArrayList;
import java.util.HashMap;

public class ModPlanetModules
{
    public static HashMap<Integer, PlanetModule> planetCoreModules = new HashMap<>(1);
    public static HashMap<Integer, PlanetModule> planetMantleModules = new HashMap<>(1);
    public static HashMap<Integer, PlanetModule> planetCrustModules = new HashMap<>(1);
    public static HashMap<Integer, PlanetModule> planetLandscapeModules = new HashMap<>(1);
    public static HashMap<Integer, PlanetModuleWorld> planetOceansModules = new HashMap<>(12);
    public static HashMap<Integer, PlanetModuleAtmosphere> planetAtmosphereModules = new HashMap<>(8);

    public static PlanetModule ironCore;

    public static PlanetModule lavaMantle;

    public static PlanetModule stoneCrust;

    public static PlanetModule dirtLandscape;

    public static PlanetModuleWorld standardWorld;
    public static PlanetModuleWorld tropicalWorld;
    public static PlanetModuleWorld swampyWorld;
    public static PlanetModuleWorld seasonalWorld;
    public static PlanetModuleWorld lushWorld;
    public static PlanetModuleWorld lavaWorld;
    public static PlanetModuleWorld industrialWorld;
    public static PlanetModuleWorld frozenWorld;
    public static PlanetModuleWorld flatWorld;
    public static PlanetModuleWorld essentialWorld;
    public static PlanetModuleWorld bloodWorld;
    public static PlanetModuleWorld aridWorld;

    public static PlanetModuleAtmosphere blueAtmosphere;
    public static PlanetModuleAtmosphere thickBlueAtmosphere;
    public static PlanetModuleAtmosphere thickRedAtmosphere;
    public static PlanetModuleAtmosphere sicklyAtmosphere;
    public static PlanetModuleAtmosphere goldenAtmosphere;
    public static PlanetModuleAtmosphere swampyAtmosphere;
    public static PlanetModuleAtmosphere magicInfusedAtmosphere;
    public static PlanetModuleAtmosphere icyAtmosphere;

    public static void init()
    {
        ironCore = new PlanetModule(0, "Iron Core");
        ironCore.setTextureLocation(Reference.MOD_ID, "textures/environment/iron_core.png");
        ironCore.setTextureSize(20.0F);
        planetCoreModules.put(ironCore.moduleId, ironCore);

        lavaMantle = new PlanetModule(0, "Lava Mantle");
        lavaMantle.setTextureLocation(Reference.MOD_ID, "textures/environment/lava_mantle.png");
        lavaMantle.setTextureSize(60.0F);
        planetMantleModules.put(lavaMantle.moduleId, lavaMantle);

        stoneCrust = new PlanetModule(0, "Stone Crust");
        stoneCrust.setTextureLocation(Reference.MOD_ID, "textures/environment/stone_crust.png");
        stoneCrust.setTextureSize(70.0F);
        planetCrustModules.put(stoneCrust.moduleId, stoneCrust);

        dirtLandscape = new PlanetModule(0, "Dirt Landscape");
        dirtLandscape.setTextureLocation(Reference.MOD_ID, "textures/environment/dirt_landscape.png");
        dirtLandscape.setTextureSize(80.0F);
        planetLandscapeModules.put(dirtLandscape.moduleId, dirtLandscape);

        // ### START WORLDS ### //

        //Requires Dirt Landscape.
        standardWorld = new PlanetModuleWorld(0, "Standard Base", 0);
        standardWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/standard_world.png");
        standardWorld.setTextureSize(90.0F);
        planetOceansModules.put(standardWorld.moduleId, standardWorld);

        tropicalWorld = new PlanetModuleWorld(1, "Tropical Base", 0);
        tropicalWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/tropical_world.png");
        tropicalWorld.setTextureSize(90.0F);
        planetOceansModules.put(tropicalWorld.moduleId, tropicalWorld);

        swampyWorld = new PlanetModuleWorld(2, "Swampy Base", 0);
        swampyWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/swampy_world.png");
        swampyWorld.setTextureSize(90.0F);
        planetOceansModules.put(swampyWorld.moduleId, swampyWorld);

        seasonalWorld = new PlanetModuleWorld(3, "Seasonal Base", 0);
        seasonalWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/seasonal_world.png");
        seasonalWorld.setTextureSize(90.0F);
        planetOceansModules.put(seasonalWorld.moduleId, seasonalWorld);

        lushWorld = new PlanetModuleWorld(4, "Lush Base", 0);
        lushWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/lush_world.png");
        lushWorld.setTextureSize(90.0F);
        planetOceansModules.put(lushWorld.moduleId, lushWorld);

        lavaWorld = new PlanetModuleWorld(5, "Lava Base", 0);
        lavaWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/lava_world.png");
        lavaWorld.setTextureSize(90.0F);
        planetOceansModules.put(lavaWorld.moduleId, lavaWorld);

        industrialWorld = new PlanetModuleWorld(6, "Industrial Base", 0);
        industrialWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/industrial_world.png");
        industrialWorld.setTextureSize(90.0F);
        planetOceansModules.put(industrialWorld.moduleId, industrialWorld);

        frozenWorld = new PlanetModuleWorld(7, "Frozen Base", 0);
        frozenWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/frozen_world.png");
        frozenWorld.setTextureSize(90.0F);
        planetOceansModules.put(frozenWorld.moduleId, frozenWorld);

        flatWorld = new PlanetModuleWorld(8, "Flat Base", 0);
        flatWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/flat_world.png");
        flatWorld.setTextureSize(90.0F);
        planetOceansModules.put(flatWorld.moduleId, flatWorld);

        essentialWorld = new PlanetModuleWorld(9, "Magic Base", 0);
        essentialWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/magic_world.png");
        essentialWorld.setTextureSize(90.0F);
        planetOceansModules.put(essentialWorld.moduleId, essentialWorld);

        bloodWorld = new PlanetModuleWorld(10, "Bloodstained Base", 0);
        bloodWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/blood_world.png");
        bloodWorld.setTextureSize(90.0F);
        planetOceansModules.put(bloodWorld.moduleId, bloodWorld);

        aridWorld = new PlanetModuleWorld(11, "Arid Base", 0);
        aridWorld.setTextureLocation(Reference.MOD_ID, "textures/environment/arid_world.png");
        aridWorld.setTextureSize(90.0F);
        planetOceansModules.put(aridWorld.moduleId, aridWorld);

        // ### START ATMOSPHERES ### //

        blueAtmosphere = new PlanetModuleAtmosphere(0, "Standard Atmosphere");
        blueAtmosphere.setTextureLocation(Reference.MOD_ID, "textures/environment/white_clouds.png");
        blueAtmosphere.setTextureSize(90.0F);
        blueAtmosphere.setAtmosphereTextureLocation(Reference.MOD_ID, "textures/environment/blue_atmosphere.png");
        blueAtmosphere.setAtmosphereTextureSize(90.0F);
        planetAtmosphereModules.put(blueAtmosphere.moduleId, blueAtmosphere);

        thickBlueAtmosphere = new PlanetModuleAtmosphere(1, "Thick Atmosphere");
        thickBlueAtmosphere.setTextureLocation(Reference.MOD_ID, "textures/environment/white_clouds.png");
        thickBlueAtmosphere.setTextureSize(90.0F);
        thickBlueAtmosphere.setAtmosphereTextureLocation(Reference.MOD_ID, "textures/environment/thick_blue_atmosphere.png");
        thickBlueAtmosphere.setAtmosphereTextureSize(90.0F);
        planetAtmosphereModules.put(thickBlueAtmosphere.moduleId, thickBlueAtmosphere);

        thickRedAtmosphere = new PlanetModuleAtmosphere(2, "Hot Atmosphere");
        thickRedAtmosphere.setTextureLocation(Reference.MOD_ID, "textures/environment/white_clouds.png");
        thickRedAtmosphere.setTextureSize(90.0F);
        thickRedAtmosphere.setAtmosphereTextureLocation(Reference.MOD_ID, "textures/environment/thick_red_atmosphere.png");
        thickRedAtmosphere.setAtmosphereTextureSize(90.0F);
        planetAtmosphereModules.put(thickRedAtmosphere.moduleId, thickRedAtmosphere);

        sicklyAtmosphere = new PlanetModuleAtmosphere(3, "Sickly Atmosphere");
        sicklyAtmosphere.setTextureLocation(Reference.MOD_ID, "textures/environment/grey_clouds.png");
        sicklyAtmosphere.setTextureSize(90.0F);
        sicklyAtmosphere.setAtmosphereTextureLocation(Reference.MOD_ID, "textures/environment/sickly_atmosphere.png");
        sicklyAtmosphere.setAtmosphereTextureSize(90.0F);
        planetAtmosphereModules.put(sicklyAtmosphere.moduleId, sicklyAtmosphere);

        goldenAtmosphere = new PlanetModuleAtmosphere(4, "Golden Atmosphere");
        goldenAtmosphere.setTextureLocation(Reference.MOD_ID, "textures/environment/white_clouds.png");
        goldenAtmosphere.setTextureSize(90.0F);
        goldenAtmosphere.setAtmosphereTextureLocation(Reference.MOD_ID, "textures/environment/sandy_atmosphere.png");
        goldenAtmosphere.setAtmosphereTextureSize(90.0F);
        planetAtmosphereModules.put(goldenAtmosphere.moduleId, goldenAtmosphere);

        swampyAtmosphere = new PlanetModuleAtmosphere(5, "Muggy Atmosphere");
        swampyAtmosphere.setTextureLocation(Reference.MOD_ID, "textures/environment/white_clouds.png");
        swampyAtmosphere.setTextureSize(90.0F);
        swampyAtmosphere.setAtmosphereTextureLocation(Reference.MOD_ID, "textures/environment/muggy_atmosphere.png");
        swampyAtmosphere.setAtmosphereTextureSize(90.0F);
        planetAtmosphereModules.put(swampyAtmosphere.moduleId, swampyAtmosphere);

        magicInfusedAtmosphere = new PlanetModuleAtmosphere(6, "Magic Infused Atmosphere");
        magicInfusedAtmosphere.setTextureLocation(Reference.MOD_ID, "textures/environment/white_clouds.png");
        magicInfusedAtmosphere.setTextureSize(90.0F);
        magicInfusedAtmosphere.setAtmosphereTextureLocation(Reference.MOD_ID, "textures/environment/magic_infused_atmosphere.png");
        magicInfusedAtmosphere.setAtmosphereTextureSize(90.0F);
        planetAtmosphereModules.put(magicInfusedAtmosphere.moduleId, magicInfusedAtmosphere);

        icyAtmosphere = new PlanetModuleAtmosphere(7, "Icy Atmosphere");
        icyAtmosphere.setTextureLocation(Reference.MOD_ID, "textures/environment/white_clouds.png");
        icyAtmosphere.setTextureSize(90.0F);
        icyAtmosphere.setAtmosphereTextureLocation(Reference.MOD_ID, "textures/environment/icy_atmosphere.png");
        icyAtmosphere.setAtmosphereTextureSize(90.0F);
        planetAtmosphereModules.put(icyAtmosphere.moduleId, icyAtmosphere);
    }
}
