package lightningmats.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lightningmats.LightningMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.creativetab.CreativeTabs;


public class BasicBlock extends Block {

    private String texturePath = "lightningmats:";  
    private int thisBlockID;
    
    public BasicBlock (int par1, Material blockMaterial, String textureName) {
        
        super(blockMaterial);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockName(textureName);
        this.setHarvestLevel("pickaxe", 2);
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
    @SideOnly(Side.CLIENT)

    public void registerBlockIcons(IIconRegister iconRegister)
    {
	if (LightningMaterials.HDTextures==true){
		this.blockIcon = iconRegister.registerIcon(texturePath);
	}
if (LightningMaterials.HDTextures==false){
		this.blockIcon = iconRegister.registerIcon(texturePath + "2");
	}
    }   
    
}

