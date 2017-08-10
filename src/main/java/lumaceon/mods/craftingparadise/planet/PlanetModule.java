package lumaceon.mods.craftingparadise.planet;

import net.minecraft.util.ResourceLocation;

public class PlanetModule
{
    public ResourceLocation textureLocation = null;
    public float textureSize = 0.0f;
    public int moduleId = 0;
    public String moduleName = "";

    public PlanetModule(int id, String name)
    {
        moduleId = id;
        moduleName = name;
    }

    public void setTextureLocation(String modId, String location)
    {
        textureLocation = new ResourceLocation(modId, location);
    }

    public void setTextureSize(float size)
    {
        textureSize = size;
    }
}
