package lightningmats.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lightningmats.LightningMaterials;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class MyItem2 extends Item {
    
    private String texturePath = "lightningmats:";
    
    public MyItem2(int ItemID, String textureName)
    {
        super();
        this.setUnlocalizedName(textureName);
        this.setCreativeTab(CreativeTabs.tabMaterials);
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
	}}


}