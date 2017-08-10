package lumaceon.mods.craftingparadise.planet;

import lumaceon.mods.craftingparadise.planet.PlanetModule;
import lumaceon.mods.craftingparadise.planet.PlanetModuleAtmosphere;
import lumaceon.mods.craftingparadise.planet.PlanetModuleWorld;

public class ActivePlanet
{
    public static PlanetModule coreModule;

    public static PlanetModule mantleModule;

    public static PlanetModule crustModule;

    public static PlanetModule landscapeModule;

    public static PlanetModuleWorld worldModule;

    public static PlanetModuleAtmosphere atmosphereModule;

    public static boolean isCoreVisible()
    {
        if(coreModule == null || mantleModule != null || crustModule != null || landscapeModule != null || worldModule != null || atmosphereModule != null)
        {
            return false;
        }
        return true;
    }

    public static boolean isMantleVisible()
    {
        if(mantleModule == null || crustModule != null || landscapeModule != null || worldModule != null || atmosphereModule != null)
        {
            return false;
        }
        return true;
    }

    public static boolean isCrustVisible()
    {
        if(crustModule == null || landscapeModule != null || worldModule != null || atmosphereModule != null)
        {
            return false;
        }
        return true;
    }

    public static boolean isLandVisible()
    {
        if(landscapeModule == null || worldModule != null || atmosphereModule != null)
        {
            return false;
        }
        return true;
    }

    public static boolean isWorldVisible()
    {
        if(worldModule == null)
        {
            return false;
        }
        return true;
    }

    public static boolean isAtmosphereVisible()
    {
        if(atmosphereModule == null)
        {
            return false;
        }
        return true;
    }
}
