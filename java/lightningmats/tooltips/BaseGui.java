package lightningmats.tooltips;





import lightningmats.tooltips.ITooltip;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public abstract class BaseGui extends GuiContainer {

	public BaseGui(Container p_i1072_1_) {
		super(p_i1072_1_);
		// TODO Auto-generated constructor stub
	}
	@Override
	final protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		int ox = guiLeft; // (width - xSize) / 2;
		int oy = guiTop; // (height - ySize) / 2;
		GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
		drawBG( ox, oy, x, y );

		final List<Slot> slots = this.getInventorySlots();
		for (Slot slot : slots)
		
					{
						GL11.glPushAttrib( GL11.GL_ALL_ATTRIB_BITS );
						GL11.glColor4f( 1.0F, 1.0F, 1.0F, 0.4F );
						GL11.glEnable( GL11.GL_BLEND );
						
						GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
						GL11.glPopAttrib();
					}
				}
			
		
	public void bindTexture(String base, String file)
	{
		ResourceLocation loc = new ResourceLocation( base, "textures/" + file );
		this.mc.getTextureManager().bindTexture( loc );
	}

	@Override
	final protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		int ox = guiLeft; // (width - xSize) / 2;
		int oy = guiTop; // (height - ySize) / 2;
		GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );

	

		drawFG( ox, oy, x, y );
	}
	public abstract void drawBG(int offsetX, int offsetY, int mouseX, int mouseY);

	public abstract void drawFG(int offsetX, int offsetY, int mouseX, int mouseY);
	
	public void drawTooltip(int par2, int par3, int forceWidth, String Msg)
	{
		GL11.glPushAttrib( GL11.GL_ALL_ATTRIB_BITS );
		GL11.glDisable( GL12.GL_RESCALE_NORMAL );
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable( GL11.GL_LIGHTING );
		GL11.glDisable( GL11.GL_DEPTH_TEST );
		String[] var4 = Msg.split( "\n" );

		if ( var4.length > 0 )
		{
			int var5 = 0;
			int var6;
			int var7;

			for (var6 = 0; var6 < var4.length; ++var6)
			{
				var7 = fontRendererObj.getStringWidth( var4[var6] );

				if ( var7 > var5 )
				{
					var5 = var7;
				}
			}

			var6 = par2 + 12;
			var7 = par3 - 12;
			int var9 = 8;

			if ( var4.length > 1 )
			{
				var9 += 2 + (var4.length - 1) * 10;
			}

			if ( this.guiTop + var7 + var9 + 6 > this.height )
			{
				var7 = this.height - var9 - this.guiTop - 6;
			}

			if ( forceWidth > 0 )
				var5 = forceWidth;

			this.zLevel = 300.0F;
			itemRender.zLevel = 300.0F;
			int var10 = -267386864;
			this.drawGradientRect( var6 - 3, var7 - 4, var6 + var5 + 3, var7 - 3, var10, var10 );
			this.drawGradientRect( var6 - 3, var7 + var9 + 3, var6 + var5 + 3, var7 + var9 + 4, var10, var10 );
			this.drawGradientRect( var6 - 3, var7 - 3, var6 + var5 + 3, var7 + var9 + 3, var10, var10 );
			this.drawGradientRect( var6 - 4, var7 - 3, var6 - 3, var7 + var9 + 3, var10, var10 );
			this.drawGradientRect( var6 + var5 + 3, var7 - 3, var6 + var5 + 4, var7 + var9 + 3, var10, var10 );
			int var11 = 1347420415;
			int var12 = (var11 & 16711422) >> 1 | var11 & -16777216;
			this.drawGradientRect( var6 - 3, var7 - 3 + 1, var6 - 3 + 1, var7 + var9 + 3 - 1, var11, var12 );
			this.drawGradientRect( var6 + var5 + 2, var7 - 3 + 1, var6 + var5 + 3, var7 + var9 + 3 - 1, var11, var12 );
			this.drawGradientRect( var6 - 3, var7 - 3, var6 + var5 + 3, var7 - 3 + 1, var11, var11 );
			this.drawGradientRect( var6 - 3, var7 + var9 + 2, var6 + var5 + 3, var7 + var9 + 3, var12, var12 );

			for (int var13 = 0; var13 < var4.length; ++var13)
			{
				String var14 = var4[var13];

				if ( var13 == 0 )
				{
					var14 = "\u00a7" + Integer.toHexString( 15 ) + var14;
				}
				else
				{
					var14 = "\u00a77" + var14;
				}

				this.fontRendererObj.drawStringWithShadow( var14, var6, var7, -1 );

				if ( var13 == 0 )
				{
					var7 += 2;
				}

				var7 += 10;
			}

			this.zLevel = 0.0F;
			itemRender.zLevel = 0.0F;
		}
		GL11.glPopAttrib();
	}
	@SuppressWarnings( "unchecked" )
	private List<Slot> getInventorySlots()
	{
		return this.inventorySlots.inventorySlots;
	}
	@Override
	public void initGui()
	{
		super.initGui();

		final List<Slot> slots = this.getInventorySlots();
		Iterator<Slot> i = slots.iterator();
		
	}
	@Override
	public void drawScreen(int mouse_x, int mouse_y, float btn)
	{
		super.drawScreen( mouse_x, mouse_y, btn );

		boolean hasClicked = Mouse.isButtonDown( 0 );
		

		for (Object c : buttonList)
		{
			if ( c instanceof ITooltip )
			{
				ITooltip tooltip = (ITooltip) c;
				int x = tooltip.xPos(); // ((GuiImgButton) c).xPosition;
				int y = tooltip.yPos(); // ((GuiImgButton) c).yPosition;

				if ( x < mouse_x && x + tooltip.getWidth() > mouse_x && tooltip.isVisible() )
				{
					if ( y < mouse_y && y + tooltip.getHeight() > mouse_y )
					{
						if ( y < 15 )
							y = 15;

						String msg = tooltip.getMsg();
						if ( msg != null )
							drawTooltip( x + 11, y + 4, 0, msg );
					}
				}
			}
		}
	}
	
}
