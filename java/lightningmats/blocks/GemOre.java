package lightningmats.blocks;

import java.util.Random;

import lightningmats.LightningMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class GemOre extends Block {

    private String texturePath = "lightningmats:";  
    private int thisBlockID;
    private Item dropID;
    private int least_quantity;
    private int most_quantity;
    public GemOre (int par1, Material blockMaterial, String textureName, Item par4, int par5, int par6) {
        
        super(blockMaterial);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockName(textureName);
        this.setHarvestLevel("pickaxe", 2);
        texturePath += textureName;
        thisBlockID = par1;
        dropID = par4;
        least_quantity = par5;
        most_quantity = par6;
    }

    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return dropID;
    }
    @Override
    public int quantityDropped(int meta , int fortune, Random random)
    {
        if (this.least_quantity >= this.most_quantity){
        	return this.least_quantity;
        }
        if (fortune !=0){
        return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune - 1);}
        return this.least_quantity;
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

