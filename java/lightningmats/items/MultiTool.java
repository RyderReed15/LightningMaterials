package lightningmats.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import lightningmats.LightningMaterials;
import lightningmats.items.ItemPickaxe;
import lightningmats.projectile.LightningEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class MultiTool extends ItemPickaxe{
    
    private String texturePath = "lightningmats:";
    
    public MultiTool(int ItemID, ToolMaterial material, String textureName)
    {
        super(material);
        this.setUnlocalizedName(textureName);
        texturePath += textureName;
    }

@Override   
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

}