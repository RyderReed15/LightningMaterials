package lightningmats.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;


public class LightningLoaf extends ItemFood 
{
   
    private String texturePath = "lightningmats:";
    
    public LightningLoaf(int itemID, int healAmount, Float saturationModifier, boolean isWolfsFavoriteMeat, String textureName) 
    {
        super(itemID, healAmount, isWolfsFavoriteMeat);
        this.setUnlocalizedName(textureName);
        texturePath += textureName;
    }

@Override
@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(texturePath);
    }
    protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote)
        {
        	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 450, 3));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2400, 0));
        }

        if (par1ItemStack.getItemDamage() > 0)
        {
            if (!par2World.isRemote)
            {
                par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 6000, 5));
                par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 2));
                par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 6000, 4));
            }
        }
        else
        {
            super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        }
    }
}