package lightningmats.power;

import java.util.List;

import scala.reflect.internal.Trees.This;
import lightningmats.LightningMaterials;
import lightningmats.items.ItemPowerStorage;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class Battery extends ItemPowerStorage {


	private String texturePath = "lightningmats:";

	public Battery(int ItemID, String textureName)
	{
		super(ItemID);
		this.setMaxDamage(5000);
		this.setUnlocalizedName(textureName);
		this.setCreativeTab(CreativeTabs.tabRedstone);
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4){
		if (this.getDamage(item) >= 3500){
			list.add("§4" + ((this.getMaxDamage(item)-this.getDamage(item))*50) + " §2/ 250000 RF");}
		if (this.getDamage(item) >= 1500 && this.getDamage(item) < 3500){
			list.add("§6" + ((this.getMaxDamage(item)-this.getDamage(item))*50) + " §2/ 250000 RF");}
		if (this.getDamage(item) < 1500){
			list.add("§2" + ((this.getMaxDamage(item)-this.getDamage(item))*50) + " §2/ 250000 RF");}
		}
			
	}


