package lightningmats.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lightningmats.LightningMaterials;
import lightningmats.projectile.GrenadeEntity;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;


public class Gun extends Item {
	private boolean isZoomed = false;
 	private String texturePath = "lightningmats:";
	private int delay = 0;
	public Gun(int ItemID, String textureName)
	{
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName(textureName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setMaxDamage(2000);
		texturePath += textureName;

		// TODO Auto-generated constructor stub
	}
	
	
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
	{
		return EnumAction.bow;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
	{
		list.add("Fires 1.5 Times A Second, Uses Bullets ");
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
		
		float f =  1.0F;
		f = (f * f + f * 2.0F)/  3.0F;

		if(!par3EntityPlayer.capabilities.isCreativeMode && this.delay == 0 && par3EntityPlayer.inventory.hasItem(LightningMaterials.Bullet))
		{



			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!par2World.isRemote)



			{
				par3EntityPlayer.inventory.consumeInventoryItem(LightningMaterials.Bullet);
				par1ItemStack.damageItem(1, par3EntityPlayer);
				par2World.spawnEntityInWorld(new EntityArrow(par2World, par3EntityPlayer, f * 4.0F));
				this.delay = 20;
			}

			return par1ItemStack;

		}
		if(par3EntityPlayer.capabilities.isCreativeMode  && this.delay == 0)
		{



			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!par2World.isRemote)



			{
				EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 4.0F);
				entityarrow.canBePickedUp = 2;
				par2World.spawnEntityInWorld(entityarrow);
				this.delay = 20;
			}
			
			return par1ItemStack;
			
		}
		return par1ItemStack;}
	@Override
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_){
		if (this.delay > 0)this.delay--;
	}
	public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
	{
		this.delay = 0;
	}
	
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		entityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 3));
		this.isZoomed=true;
		return false;
    }
}