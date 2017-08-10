package lumaceon.mods.craftingparadise.init;

import lumaceon.mods.craftingparadise.CraftingParadise;
import lumaceon.mods.craftingparadise.block.*;
import lumaceon.mods.craftingparadise.tile.*;
import lumaceon.mods.craftingparadise.util.ISimpleNamed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class ModBlocks
{
    public static ArrayList<Block> blocksForModel = new ArrayList<>(2);

    public static Block worldMachine;
    public static Block biomeChanger;
    public static void init()
    {
        //ORES
        worldMachine = new BlockWorldMachine(Material.IRON, "world_machine");
        register(worldMachine);

        biomeChanger = new BlockBiomeChanger(Material.IRON, "BiomeShifter"); //Unlocalized name should be consistent with the old mod version.
        register(biomeChanger);
    }

    public static void initTE() {
        GameRegistry.registerTileEntity(TileWorldMachine.class, worldMachine.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileBiomeChanger.class, biomeChanger.getUnlocalizedName());
    }

    //************ START HELPER METHODS ************\\

    private static void register(Block block)
    {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        blocksForModel.add(block);
    }

    /**
     * By default, register also includes an ItemBlock (blocks are held in the inventory as ItemBlocks, which extend
     * Item). This will skip the ItemBlock and only register the Block, making it unable to be held in the inventory.
     *
     * Generally you would want to use this when you want to register your own custom ItemBlock for the block.
     */
    private static void registerWithoutItemBlock(Block block)
    {
        ForgeRegistries.BLOCKS.register(block);
        blocksForModel.add(block);
    }

    public static void initModels()
    {
        if(blocksForModel != null)
        {
            for(Block block : blocksForModel)
                if(block != null && block instanceof ISimpleNamed)
                    CraftingParadise.proxy.registerBlockModel(block, ((ISimpleNamed) block).getSimpleName());
            blocksForModel.clear();
        }
        blocksForModel = null;
    }
}
