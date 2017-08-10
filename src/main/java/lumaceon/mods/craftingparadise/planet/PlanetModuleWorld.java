package lumaceon.mods.craftingparadise.planet;

public class PlanetModuleWorld extends PlanetModule
{
    public int landscapeIdReq = -1;

    public PlanetModuleWorld(int id, String mainTextureName, int landscapeIdRequired) {
        super(id, mainTextureName);
        this.landscapeIdReq = landscapeIdRequired;
    }
}
