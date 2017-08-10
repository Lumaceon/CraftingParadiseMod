package lumaceon.mods.craftingparadise.config;

import lumaceon.mods.craftingparadise.lib.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.*;
import java.nio.file.*;

public class ConfigurationHandler
{
    public static void init(File directory)
    {
        directory = new File(directory, Reference.MOD_ID + ".cfg");
        try {
            if(!Files.exists(Paths.get(directory.toURI()).getParent()))
                Files.createDirectories(Paths.get(directory.toURI()).getParent());
            if(!Files.exists(Paths.get(directory.toURI())))
                Files.createFile(Paths.get(directory.toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration config = new Configuration(directory);

        try
        {
            config.load();
            ConfigValues.DEVELOPING = config.get(Configuration.CATEGORY_GENERAL, "DeveloperMode", ConfigValues.DEVELOPING, "For developers. Currently does nothing but confirm that configurations are working.").getBoolean();
        }
        catch(Exception ex)
        {
            System.err.println("[" + Reference.MOD_NAME + "] Exception caught in ConfigurationHandler:");
            ex.printStackTrace();
        }
        finally
        {
            config.save();
        }
    }
}
