package lightningmats.items.render;

import lightningmats.LightningMaterials;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class GunRenderer implements IItemRenderer {

	private GunModel gunmodel;
	
	public static ResourceLocation location = new ResourceLocation("lightningmats:textures/items/models/Gun2.png");
	
	public GunRenderer(){
		gunmodel = new GunModel();
	}
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if (LightningMaterials.config.get("Client Side", "Render The Gun In 3D", true).getBoolean()==true){
		switch(type)
		{
		
		case EQUIPPED: return true;
		case EQUIPPED_FIRST_PERSON: return true;
		default: return false;
		}}
	return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {

		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (LightningMaterials.config.get("Client Side", "Render The Gun In 3D", true).getBoolean()==true){
		switch(type)
		{
		case EQUIPPED:
		{
			GL11.glPushMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(location);
			GL11.glRotatef(-78, 0, 1, 0);
			GL11.glRotatef(-180, 0, 0, 1);
			GL11.glRotatef(-15, 1, 0, 0);
			GL11.glScalef(1.8F, 1.8F, 1.8F);
			GL11.glTranslatef(-.65F, .67F, -.3F);
			
			
			this.gunmodel.renderModel(0.0625F);
			
			GL11.glPopMatrix();
			
			
		}
		case EQUIPPED_FIRST_PERSON:
		{
			
GL11.glPushMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(location);
			GL11.glRotatef(-78, 0, 1, 0);
			GL11.glRotatef(-180, 0, 0, 1);
			GL11.glRotatef(-15, 1, 0, 0);
			GL11.glScalef(1.8F, 1.8F, 1.8F);
			
boolean isFirstPerson = false;
			
			if(data[1] != null && data[1] instanceof EntityPlayer)
				{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glTranslatef(-.65F, .67F, -.3F);
				}
				else
				{
					isFirstPerson = true;
					
					GL11.glTranslatef(0, .35F, -1F);
					GL11.glRotatef(-80, 0, 1, 0);
					GL11.glRotatef(12, 0, 0, 1);
					GL11.glRotatef(8, 1, 0, 0);
					GL11.glScalef(.95F, .95F, .95F);
					
				}
				}
				else	
				{
					GL11.glTranslatef(-.65F, .67F, -.3F);
				}
			

			
			
			
			this.gunmodel.renderModel(0.0625F);
			
			GL11.glPopMatrix();
			
			
			

		}
		default:
			break;
		}
	}
}
}


