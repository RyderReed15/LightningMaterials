package lightningmats.armor;

import lightningmats.LightningMaterials;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RubyArmor extends ItemArmor {

	private String texturePath = "lightningmats:textures/armor/";
	private String iconPath = "lightningmats:";
	private String name = "";
	private String integer;

	public RubyArmor(int par1, ArmorMaterial par2EnumArmorMaterial, int par3,
			int par4, String myArmorName) {
		super(par2EnumArmorMaterial, par3, par4);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.SetArmorType(myArmorName, par4);
		
		
	}

	private void SetArmorType(String myArmorName, int par4) {
		switch (par4) {
		case 0:
			if (LightningMaterials.HDTextures == false){
				this.integer = "2";
			}else{
				this.integer = "";
			}
			
			this.name = "LightningHelm";
			this.setUnlocalizedName("LightningHelm");
			this.texturePath += myArmorName + "_1.png";
			this.iconPath += (this.name + this.integer);
			break;
		case 1:
			if (LightningMaterials.HDTextures == false){
				this.integer = "2";
			}else{
				this.integer = "";
			}
			this.name = "LightningChestplate";
			this.setUnlocalizedName("LightningChestplate");
			this.texturePath += myArmorName + "_1.png";
			this.iconPath += (this.name + this.integer);
			break;
		case 2:
			if (LightningMaterials.HDTextures == false){
				this.integer = "2";
			}else{
				this.integer = "";
			}
			this.name = "LightningLeggings";
			this.setUnlocalizedName("LightningLeggings");
			this.texturePath += myArmorName + "_2.png";
			this.iconPath += (this.name + this.integer);
			break;
		case 3:
			if (LightningMaterials.HDTextures == false){
				this.integer = "2";
			}else{
				this.integer = "";
			}
			this.name = "LightningBoots";
			this.setUnlocalizedName("LightningBoots");
			this.texturePath += myArmorName + "_1.png";
			this.iconPath += (name + integer);
			break;
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon(this.iconPath);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack)
	{
		if(player.getCurrentArmor(0) !=null && player.getCurrentArmor(0).getItem().equals(LightningMaterials.MyBoots_1))
		{
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 1, 1));
		}

		if(player.getCurrentArmor(1) !=null && player.getCurrentArmor(1).getItem().equals(LightningMaterials.MyLeggings_1))
		{
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1, 2));
		}
		if(player.getCurrentArmor(3) !=null && player.getCurrentArmor(3).getItem().equals(LightningMaterials.MyHelmet_1))
		{
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 4500, 2));
		}
	}
@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
	
		return this.texturePath;
	}}
