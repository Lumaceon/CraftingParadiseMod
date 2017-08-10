package lumaceon.mods.craftingparadise.datastorage;

import lumaceon.mods.craftingparadise.planet.ActivePlanet;
import lumaceon.mods.craftingparadise.lib.Reference;
import lumaceon.mods.craftingparadise.init.ModPlanetModules;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class CraftingParadiseSaveData extends WorldSavedData
{
    public static final String KEY = Reference.MOD_ID + "_save_data";

    public CraftingParadiseSaveData() {
        super(KEY);
    }

    public CraftingParadiseSaveData(String name) {
        super(name);
    }

    public static CraftingParadiseSaveData get(World world)
    {
        MapStorage storage = world.getMapStorage();
        CraftingParadiseSaveData instance = (CraftingParadiseSaveData) storage.getOrLoadData(CraftingParadiseSaveData.class, KEY);

        if(instance == null)
        {
            instance = new CraftingParadiseSaveData();
            storage.setData(KEY, instance);
        }

        return instance;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        int core = nbt.getInteger("core_id");
        int mantle = nbt.getInteger("mantle_id");
        int crust = nbt.getInteger("crust_id");
        int landscape = nbt.getInteger("landscape_id");
        int oceans = nbt.getInteger("ocean_id");
        int atmos = nbt.getInteger("atmos_id");

        if(core != -1)
        {
            ActivePlanet.coreModule = ModPlanetModules.planetCoreModules.get(core);
        }

        if(mantle != -1)
        {
            ActivePlanet.mantleModule = ModPlanetModules.planetMantleModules.get(mantle);
        }

        if(crust != -1)
        {
            ActivePlanet.crustModule = ModPlanetModules.planetCrustModules.get(crust);
        }

        if(landscape != -1)
        {
            ActivePlanet.landscapeModule = ModPlanetModules.planetLandscapeModules.get(landscape);
        }

        if(oceans != -1)
        {
            ActivePlanet.worldModule = ModPlanetModules.planetOceansModules.get(oceans);
        }

        if(atmos != -1)
        {
            ActivePlanet.atmosphereModule = ModPlanetModules.planetAtmosphereModules.get(atmos);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        if(ActivePlanet.coreModule != null)
        {
            nbt.setInteger("core_id", ActivePlanet.coreModule.moduleId);
        }
        else
        {
            nbt.setInteger("core_id", -1);
        }

        if(ActivePlanet.mantleModule != null)
        {
            nbt.setInteger("mantle_id", ActivePlanet.mantleModule.moduleId);
        }
        else
        {
            nbt.setInteger("mantle_id", -1);
        }

        if(ActivePlanet.crustModule != null)
        {
            nbt.setInteger("crust_id", ActivePlanet.crustModule.moduleId);
        }
        else
        {
            nbt.setInteger("crust_id", -1);
        }

        if(ActivePlanet.landscapeModule != null)
        {
            nbt.setInteger("landscape_id", ActivePlanet.landscapeModule.moduleId);
        }
        else
        {
            nbt.setInteger("landscape_id", -1);
        }

        if(ActivePlanet.worldModule != null)
        {
            nbt.setInteger("ocean_id", ActivePlanet.worldModule.moduleId);
        }
        else
        {
            nbt.setInteger("ocean_id", -1);
        }

        if(ActivePlanet.atmosphereModule != null)
        {
            nbt.setInteger("atmos_id", ActivePlanet.atmosphereModule.moduleId);
        }
        else
        {
            nbt.setInteger("atmos_id", -1);
        }

        return nbt;
    }
}
