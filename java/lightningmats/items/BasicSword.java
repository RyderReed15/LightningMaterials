package lightningmats.items;

import lightningmats.LightningMaterials;
import lightningmats.projectile.GrenadeEntity;
import lightningmats.projectile.LightningEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;


public class BasicSword extends ItemSword {
    
    private String texturePath = "lightningmats:";
    
    public BasicSword(int ItemID, ToolMaterial material, String textureName)
    {
    	
        super(material);
        this.setUnlocalizedName(textureName);
        texturePath += textureName;
        
    }
   
@SideOnly(Side.CLIENT)

    public void registerIcons(IIconRegister iconRegister)
    {
	if (LightningMaterials.HDTextures==true){
		this.itemIcon = iconRegister.registerIcon(texturePath);
	}
if (LightningMaterials.HDTextures==false){
		this.itemIcon = iconRegister.registerIcon(texturePath + "2");
	}
    }   


/** Adds Lightning Power to your Sword */

       
     }