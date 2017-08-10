package lumaceon.mods.craftingparadise.block;

import lumaceon.mods.craftingparadise.tile.TileBiomeChanger;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;

public class BlockBiomeChanger extends BlockMod implements ITileEntityProvider
{
    public BlockBiomeChanger(Material blockMaterial, String name) {
        super(blockMaterial, name);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(player.isSneaking())
        {
            TileEntity te = world.getTileEntity(pos);
            if(te != null && te instanceof TileBiomeChanger)
            {
                TileBiomeChanger biomeChanger = (TileBiomeChanger) te;
                biomeChanger.setTargetWorld(world.provider.getDimension());
                if(!world.isRemote)
                    player.sendMessage(new TextComponentString("Now changing world: \"" + world.provider.getDimensionType().getName() + "\""));
            }
        }
        else
        {
            TileEntity te = world.getTileEntity(pos);
            if(te != null && te instanceof TileBiomeChanger)
            {
                TileBiomeChanger biomeChanger = (TileBiomeChanger) te;

                int currentID;
                if(biomeChanger.getTargetBiome() == null)
                {
                    currentID = 0;
                }
                else
                {
                    currentID = Biome.getIdForBiome(biomeChanger.getTargetBiome());
                }

                Biome newBiome = null;
                int attempts = 0;
                while(attempts < 255) //Loop forward one id number until you find a biome, then break out of the loop.
                {
                    ++currentID;
                    if(currentID > Byte.MAX_VALUE)
                    {
                        currentID = Byte.MIN_VALUE;
                    }

                    newBiome = Biome.getBiomeForId(currentID);
                    if(newBiome != null)
                    {
                        break;
                    }

                    ++attempts;
                }

                if(newBiome != null)
                {
                    biomeChanger.setTargetBiome(newBiome);
                    if(!world.isRemote)
                        player.sendMessage(new TextComponentString("Biome: \""+ newBiome.getBiomeName() + "\""));
                }
            }
        }

        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileBiomeChanger();
    }
}
