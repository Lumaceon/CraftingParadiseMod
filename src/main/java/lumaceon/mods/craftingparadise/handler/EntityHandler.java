package lumaceon.mods.craftingparadise.handler;

import lumaceon.mods.craftingparadise.CraftingParadise;
import lumaceon.mods.craftingparadise.datastorage.CraftingParadiseSaveData;
import lumaceon.mods.craftingparadise.planet.ActivePlanet;
import lumaceon.mods.craftingparadise.network.PacketHandler;
import lumaceon.mods.craftingparadise.network.message.MessageClientPlanetChange;
import lumaceon.mods.craftingparadise.planet.PlanetModule;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityHandler
{
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onEntityConstruction(EntityEvent.EntityConstructing event)
    {
        Entity entity = event.getEntity();
        if(entity instanceof EntityPlayer)
        {
            if(entity.world != null)
            {
                if(event.getEntity().world.isRemote)
                {
                    CraftingParadise.proxy.registerCustomSky();
                }
            }
        }
    }

    @SubscribeEvent()
    public void onJoinWorld(EntityJoinWorldEvent event)
    {
        Entity entity = event.getEntity();
        if(entity instanceof EntityPlayer)
        {
            if(event.getWorld() != null)
            {
                if(!event.getWorld().isRemote) //Server-side
                {
                    CraftingParadiseSaveData.get(event.getWorld());
                    PlanetModule core = ActivePlanet.coreModule;
                    PlanetModule mantle = ActivePlanet.mantleModule;
                    PlanetModule crust = ActivePlanet.crustModule;
                    PlanetModule land = ActivePlanet.landscapeModule;
                    PlanetModule worldType = ActivePlanet.worldModule;
                    PlanetModule atmos = ActivePlanet.atmosphereModule;

                    int coreID = core == null ? -1 : core.moduleId;
                    int mantleID = mantle == null ? -1 : mantle.moduleId;
                    int crustID = crust == null ? -1 : crust.moduleId;
                    int landID = land == null ? -1 : land.moduleId;
                    int worldTypeID = worldType == null ? -1 : worldType.moduleId;
                    int atmosID = atmos == null ? -1 : atmos.moduleId;

                    PacketHandler.INSTANCE.sendTo(new MessageClientPlanetChange(coreID, mantleID, crustID, landID, worldTypeID, atmosID), (EntityPlayerMP) entity);
                }
            }
        }
    }
}
