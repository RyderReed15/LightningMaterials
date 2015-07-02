package lightningmats.items;


import java.util.List;

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


public class LightningSword extends ItemSword {
    
    private String texturePath = "lightningmats:";
    
    public LightningSword(int ItemID, ToolMaterial material, String textureName)
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

       
     public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	 par1ItemStack.damageItem(2, par3EntityPlayer);
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        
           
          
        

        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote)
        {
            par2World.spawnEntityInWorld(new LightningEntity(par2World, par3EntityPlayer));
        }

        return par1ItemStack;


}
     @SideOnly(Side.CLIENT)
     public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4){
    	 list.add("Shoots A Ball Of Lightning When You Block");
     }
}