package lightningmats.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lightningmats.LightningMaterials;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;


public class BasicAxe extends ItemAxe {
    
    private String texturePath = "lightningmats:";
    public int damage;
    public BasicAxe(int ItemID, ToolMaterial material, String textureName)
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
}