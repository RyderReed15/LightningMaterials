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

public class NickelArmor extends ItemArmor {

	private String texturePath = "lightningmats:textures/armor/";
	private String iconPath = "lightningmats:";
	private String name = "";

	public NickelArmor(int par1, ArmorMaterial par2EnumArmorMaterial, int par3,
			int par4, String myArmorName) {
		super(par2EnumArmorMaterial, par3, par4);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.SetArmorType(myArmorName, par4);
		
		
	}

	private void SetArmorType(String myArmorName, int par4) {
		switch (par4) {
		case 0:		
			this.name = "NickelHelmet";
			this.setUnlocalizedName("NickelHelmet");
			this.texturePath += myArmorName + "_1.png";
			this.iconPath += (this.name);
			break;
		case 1:
			this.name = "NickelChestplate";
			this.setUnlocalizedName("NickelChestplate");
			this.texturePath += myArmorName + "_1.png";
			this.iconPath += (this.name);
			break;
		case 2:
			this.name = "NickelLeggings";
			this.setUnlocalizedName("NickelLeggings");
			this.texturePath += myArmorName + "_2.png";
			this.iconPath += (this.name);
			break;
		case 3:
			this.name = "NickelBoots";
			this.setUnlocalizedName("NickelBoots");
			this.texturePath += myArmorName + "_1.png";
			this.iconPath += (name);
			break;
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		if (LightningMaterials.HDTextures==true){
			this.itemIcon = reg.registerIcon(this.iconPath + "2");
		}else{
			this.itemIcon = reg.registerIcon(this.iconPath);
		}
	}
		
	
	
@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
	if (LightningMaterials.HDTextures==true){
			return this.texturePath + "2.png";
		}
	return this.texturePath + ".png";
	}
}
