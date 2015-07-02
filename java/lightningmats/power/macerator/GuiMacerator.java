package lightningmats.power.macerator;

import lightningmats.tooltips.BaseGui;
import lightningmats.tooltips.GuiProgressBar;
import lightningmats.tooltips.GuiProgressBar.Direction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiMacerator extends BaseGui{
		public static final ResourceLocation texture = new ResourceLocation("lightningmats:textures/gui/macerator.png");
		
		final ContainerMacerator m;
		public TileEntityMacerator macerator;
		GuiProgressBar pb;
		
		public GuiMacerator(InventoryPlayer invPlayer, TileEntityMacerator entity) {
			super(new ContainerMacerator(invPlayer, entity));
			m = (ContainerMacerator) inventorySlots;
			
			this.macerator = entity;

			this.xSize = 176;
			this.ySize = 165;
		}
		@Override
		public void initGui(){
			super.initGui();
			
			pb = new GuiProgressBar(m, "gui/macerator.png", 8, 8, 176, 176, 17, 45, Direction.VERTICAL );
			this.buttonList.add(pb);
		}
		
		@Override
		public void drawBG(int offsetX, int offsetY, int mouseX, int mouseY) {
			GL11.glColor4f(1F, 1F, 1F, 1F);
			
			
			bindTexture("lightningmats", "gui/macerator.png");
			pb.xPosition = 8 + guiLeft;
			pb.yPosition = 8 + guiTop;
			drawTexturedModalRect(offsetX, offsetY, 0, 0, xSize, ySize);
			
			int i1;

			if(this.macerator.hasPower()){
				i1 = this.macerator.getPowerRemainingScaled(45);
				this.drawTexturedModalRect(guiLeft + 8, guiTop + 53 - i1, 176, 62 - i1, 17, i1);
			}
			
			i1 = this.macerator.getCookProgressScaled(24);
			this.drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 0, i1 + 1, 16);
		}
		@Override
		public void drawFG(int offsetX, int offsetY, int mouseX, int mouseY){
			
			String s = this.macerator.isInvNameLocalized() ? this.macerator.getInvName() : I18n.format(this.macerator.getInvName());
			this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
			this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 5, 4210752);
			
		pb.setFullMsg(macerator.storage.energy + " / " + macerator.CAPACITY + " RF");
		}
	}

