package lumaceon.mods.craftingparadise;

import lumaceon.mods.craftingparadise.config.ConfigurationHandler;
import lumaceon.mods.craftingparadise.creativetab.CreativeTabCraftingParadise;
import lumaceon.mods.craftingparadise.handler.EntityHandler;
import lumaceon.mods.craftingparadise.init.*;
import lumaceon.mods.craftingparadise.lib.Reference;
import lumaceon.mods.craftingparadise.network.PacketHandler;
import lumaceon.mods.craftingparadise.init.ModPlanetModules;
import lumaceon.mods.craftingparadise.proxy.IProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class CraftingParadise
{
    @Mod.Instance(Reference.MOD_ID)
    public static CraftingParadise instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static IProxy proxy;

    public final CreativeTabs CREATIVE_TAB = new CreativeTabCraftingParadise("CraftingParadise");

    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getModConfigurationDirectory());

        ModPlanetModules.init();

        ModItems.init();
        ModBlocks.init();
        ModBlocks.initTE();

        proxy.preInit();
    }

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event)
    {
        ModItems.initModels();
        ModBlocks.initModels();

        MinecraftForge.EVENT_BUS.register(new EntityHandler());
        proxy.initSideHandlers();

        PacketHandler.init();
        proxy.init();
    }

    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event) {}
}