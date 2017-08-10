package lumaceon.mods.craftingparadise.planet;

import net.minecraft.util.ResourceLocation;

public class PlanetModuleAtmosphere extends PlanetModule
{
    public ResourceLocation atmosphereTextureLocation;
    public float atmosphereTextureSize;

    public PlanetModuleAtmosphere(int id, String mainTextureName) {
        super(id, mainTextureName);
    }

    public void setAtmosphereTextureSize(float size)
    {
        this.atmosphereTextureSize = size;
    }

    public void setAtmosphereTextureLocation(String modId, String location)
    {
        this.atmosphereTextureLocation = new ResourceLocation(modId, location);
    }
}
