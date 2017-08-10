package lumaceon.mods.craftingparadise.network.message.handler;

import lumaceon.mods.craftingparadise.datastorage.CraftingParadiseSaveData;
import lumaceon.mods.craftingparadise.planet.ActivePlanet;
import lumaceon.mods.craftingparadise.item.modules.IWorldBuilderModule;
import lumaceon.mods.craftingparadise.network.PacketHandler;
import lumaceon.mods.craftingparadise.network.message.MessageClientPlanetChange;
import lumaceon.mods.craftingparadise.network.message.MessageWorldCraft;
import lumaceon.mods.craftingparadise.planet.PlanetModule;
import lumaceon.mods.craftingparadise.planet.PlanetModuleAtmosphere;
import lumaceon.mods.craftingparadise.planet.PlanetModuleWorld;
import lumaceon.mods.craftingparadise.tile.TileWorldMachine;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class HandlerWorldCraft implements IMessageHandler<MessageWorldCraft, IMessage>
{
    @Override
    public IMessage onMessage(final MessageWorldCraft message, final MessageContext ctx)
    {
        if(ctx.side != Side.SERVER)
        {
            System.err.println("MessageWorldCraft received on wrong side:" + ctx.side);
            return null;
        }

        final EntityPlayerMP sendingPlayer = ctx.getServerHandler().player;
        if(sendingPlayer == null)
        {
            System.err.println("MessageWorldCraft received with null player.");
            return null;
        }

        final WorldServer playerWorldServer = sendingPlayer.getServerWorld();
        playerWorldServer.addScheduledTask(() -> processMessage(message, ctx, playerWorldServer));
        return null;
    }

    private void processMessage(MessageWorldCraft message, MessageContext ctx, WorldServer world)
    {
        TileEntity te = world.getTileEntity(message.pos);
        if(te != null && te instanceof TileWorldMachine)
        {
            PlanetModule core = null;
            PlanetModule mantle = null;
            PlanetModule crust = null;
            PlanetModule land = null;
            PlanetModule worldType = null;
            PlanetModule atmos = null;
            TileWorldMachine worldMachine = (TileWorldMachine) te;

            ItemStack coreStack = worldMachine.getStackInSlot(0);
            if(coreStack != null && !coreStack.isEmpty() && coreStack.getItem() instanceof IWorldBuilderModule)
            {
                IWorldBuilderModule wbm = (IWorldBuilderModule) coreStack.getItem();
                PlanetModule mod = wbm.getModule(coreStack);
                if(wbm.getModuleType(coreStack).equals(IWorldBuilderModule.ModuleType.CORE) && mod != null)
                {
                    core = mod;
                }
            }

            ItemStack mantleStack = worldMachine.getStackInSlot(1);
            if(mantleStack != null && !mantleStack.isEmpty() && mantleStack.getItem() instanceof IWorldBuilderModule)
            {
                IWorldBuilderModule wbm = (IWorldBuilderModule) mantleStack.getItem();
                PlanetModule mod = wbm.getModule(mantleStack);
                if(wbm.getModuleType(mantleStack).equals(IWorldBuilderModule.ModuleType.MANTLE) && mod != null)
                {
                    mantle = mod;
                }
            }

            ItemStack crustStack = worldMachine.getStackInSlot(2);
            if(crustStack != null && !crustStack.isEmpty() && crustStack.getItem() instanceof IWorldBuilderModule)
            {
                IWorldBuilderModule wbm = (IWorldBuilderModule) crustStack.getItem();
                PlanetModule mod = wbm.getModule(crustStack);
                if(wbm.getModuleType(crustStack).equals(IWorldBuilderModule.ModuleType.CORE) && mod != null)
                {
                    crust = mod;
                }
            }

            ItemStack landStack = worldMachine.getStackInSlot(3);
            if(landStack != null && !landStack.isEmpty() && landStack.getItem() instanceof IWorldBuilderModule)
            {
                IWorldBuilderModule wbm = (IWorldBuilderModule) landStack.getItem();
                PlanetModule mod = wbm.getModule(landStack);
                if(wbm.getModuleType(landStack).equals(IWorldBuilderModule.ModuleType.LANDSCAPE) && mod != null)
                {
                    land = mod;
                }
            }

            ItemStack worldStack = worldMachine.getStackInSlot(4);
            if(worldStack != null && !worldStack.isEmpty() && worldStack.getItem() instanceof IWorldBuilderModule)
            {
                IWorldBuilderModule wbm = (IWorldBuilderModule) worldStack.getItem();
                PlanetModule mod = wbm.getModule(worldStack);
                if(wbm.getModuleType(worldStack).equals(IWorldBuilderModule.ModuleType.WORLD) && mod != null)
                {
                    worldType = mod;
                }
            }

            ItemStack atmosStack = worldMachine.getStackInSlot(5);
            if(atmosStack != null && !atmosStack.isEmpty() && atmosStack.getItem() instanceof IWorldBuilderModule)
            {
                IWorldBuilderModule wbm = (IWorldBuilderModule) atmosStack.getItem();
                PlanetModule mod = wbm.getModule(atmosStack);
                if(wbm.getModuleType(atmosStack).equals(IWorldBuilderModule.ModuleType.ATMOSPHERE) && mod != null)
                {
                    atmos = mod;
                }
            }

            boolean hasChanged = core != ActivePlanet.coreModule ||
                    mantle != ActivePlanet.mantleModule ||
                    crust != ActivePlanet.crustModule ||
                    land != ActivePlanet.landscapeModule ||
                    worldType != ActivePlanet.worldModule ||
                    atmos != ActivePlanet.atmosphereModule;

            if(!hasChanged)
                return;

            if(core != null)
            {
                ActivePlanet.coreModule = core;
                if(mantle != null)
                {
                    ActivePlanet.mantleModule = mantle;
                    if(crust != null)
                    {
                        ActivePlanet.crustModule = crust;
                        if(land != null)
                        {
                            ActivePlanet.landscapeModule = land;
                            if(worldType != null)
                            {
                                ActivePlanet.worldModule = (PlanetModuleWorld) worldType;
                                if(atmos != null)
                                {
                                    ActivePlanet.atmosphereModule = (PlanetModuleAtmosphere) atmos;
                                }
                                else
                                {
                                    ActivePlanet.atmosphereModule = null;
                                }
                            }
                            else
                            {
                                ActivePlanet.worldModule = null;
                                ActivePlanet.atmosphereModule = null;
                            }
                        }
                        else
                        {
                            ActivePlanet.landscapeModule = null;
                            ActivePlanet.worldModule = null;
                            ActivePlanet.atmosphereModule = null;
                        }
                    }
                    else
                    {
                        ActivePlanet.crustModule = null;
                        ActivePlanet.landscapeModule = null;
                        ActivePlanet.worldModule = null;
                        ActivePlanet.atmosphereModule = null;
                    }
                }
                else
                {
                    ActivePlanet.mantleModule = null;
                    ActivePlanet.crustModule = null;
                    ActivePlanet.landscapeModule = null;
                    ActivePlanet.worldModule = null;
                    ActivePlanet.atmosphereModule = null;
                }
            }
            else
            {
                ActivePlanet.coreModule = null;
                ActivePlanet.mantleModule = null;
                ActivePlanet.crustModule = null;
                ActivePlanet.landscapeModule = null;
                ActivePlanet.worldModule = null;
                ActivePlanet.atmosphereModule = null;
            }

            CraftingParadiseSaveData.get(world).markDirty();

            int coreID = core == null ? -1 : core.moduleId;
            int mantleID = mantle == null ? -1 : mantle.moduleId;
            int crustID = crust == null ? -1 : crust.moduleId;
            int landID = land == null ? -1 : land.moduleId;
            int worldTypeID = worldType == null ? -1 : worldType.moduleId;
            int atmosID = atmos == null ? -1 : atmos.moduleId;
            PacketHandler.INSTANCE.sendToAll(new MessageClientPlanetChange(coreID, mantleID, crustID, landID, worldTypeID, atmosID));
        }
    }
}
