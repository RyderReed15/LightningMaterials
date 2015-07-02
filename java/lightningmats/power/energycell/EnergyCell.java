package lightningmats.power.energycell;

import java.util.Random;

import lightningmats.LightningMaterials;
import lightningmats.power.energycell.TileEntityEnergyCell;
import lightningmats.power.macerator.TileEntityMacerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class EnergyCell extends BlockContainer {

    private String texturePath = "mymod:";  
    private int thisBlockID;
    
    public EnergyCell (int par1, Material blockMaterial, String textureName) {
        
        super(blockMaterial);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockName(textureName);
        texturePath += textureName;
        thisBlockID = par1;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return thisBlockID;
    }
    
    public int quantityDropped(Random random)
    {
        return 1;
    }
public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityEnergyCell();
	}

@Override
public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
	// TODO Auto-generated method stub
	return new TileEntityEnergyCell();
}


    
}

