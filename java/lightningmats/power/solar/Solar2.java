package lightningmats.power.solar;


import java.util.List;
import java.util.Random;

import javax.swing.Icon;

import lightningmats.LightningMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Solar2 extends BlockContainer{


				private final Random maceratorRand = new Random();

		private final boolean isActive;

		private static boolean keepMaceratorInventory;
		@SideOnly(Side.CLIENT)
		private IIcon maceratorIconTop;
		private IIcon Hi;
		private IIcon Solarpaneltop;
		public Solar2(int id, boolean isActive) {
			super(Material.rock);
			this.setBlockBounds(0, 0, 0, 1, 0.375F, 1);
			this.isActive = isActive;
			
			
		}
		
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister iconRegister){
			this.Hi = iconRegister.registerIcon("lightningmats:macerator_side");
			this.blockIcon = iconRegister.registerIcon("lightningmats:macerator_side2");
			this.maceratorIconTop =  iconRegister.registerIcon((this.isActive ? "lightningmats:macerator_side2" : "lightningmats:macerator_side2"));
			this.Solarpaneltop = iconRegister.registerIcon("lightningmats:SolarPanel");
		}
		
		@SideOnly(Side.CLIENT)

		/**
		 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
		 */
		public IIcon getIcon(int par1, int par2)
		{
			return  (IIcon)(par1 == 1 ? this.Solarpaneltop : (par1 == 0 ? this.Hi : (par1 != par2 ? this.blockIcon : this.maceratorIconTop)));
		}
		public boolean isOpaqueCube(){return false;}
		public boolean renderAsNormalBlock(){return false;}
		/**
		 * Returns the ID of the items to drop on destruction.
		 */
		public Block idDropped(int par1, Random par2Random, int par3)
		{
			return LightningMaterials.Solar2Idle;
		}   

		/**
		 * Called whenever the block is added into the world. Args: world, x, y, z
		 */
		public void onBlockAdded(World par1World, int par2, int par3, int par4)
		{
			super.onBlockAdded(par1World, par2, par3, par4);
			this.func_149930_e(par1World, par2, par3, par4);
		}
		private void func_149930_e(World p_149930_1_, int p_149930_2_, int p_149930_3_, int p_149930_4_)
	    {
	        if (!p_149930_1_.isRemote)
	        {
	            Block block = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ - 1);
	            Block block1 = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ + 1);
	            Block block2 = p_149930_1_.getBlock(p_149930_2_ - 1, p_149930_3_, p_149930_4_);
	            Block block3 = p_149930_1_.getBlock(p_149930_2_ + 1, p_149930_3_, p_149930_4_);
	            byte b0 = 3;

	            if (block.func_149730_j() && !block1.func_149730_j())
	            {
	                b0 = 3;
	            }

	            if (block1.func_149730_j() && !block.func_149730_j())
	            {
	                b0 = 2;
	            }

	            if (block2.func_149730_j() && !block3.func_149730_j())
	            {
	                b0 = 5;
	            }

	            if (block3.func_149730_j() && !block2.func_149730_j())
	            {
	                b0 = 4;
	            }

	            p_149930_1_.setBlockMetadataWithNotify(p_149930_2_, p_149930_3_, p_149930_4_, b0, 2);
	        }
	    }

		/**
		 * set a blocks direction
		 */
		
		

		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
			if(!world.isRemote) {
				FMLNetworkHandler.openGui(player, LightningMaterials.instance, LightningMaterials.guiIdSolar, world, x, y, z);
			}
			
			return true;
		}

		/**
		 * Update which block ID the furnace is using depending on whether or not it is burning
		 */
		public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
		{
			int l = par1World.getBlockMetadata(par2, par3, par4);
			TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
			keepMaceratorInventory = true;
			
			if (par0)
			{
				par1World.setBlock(par2, par3, par4, LightningMaterials.Solar2Active);
			}
			else
			{
				par1World.setBlock(par2, par3, par4, LightningMaterials.Solar2Idle);
			}

			keepMaceratorInventory = false;
			par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

			if (tileentity != null)
			{
				tileentity.validate();
				par1World.setTileEntity(par2, par3, par4, tileentity);
			}
		

		}

		/**
		 * Returns a new instance of a block's tile entity class. Called on placing the block.
		 */
		public TileEntity createNewTileEntity(World par1World)
		{
			return new TileEntitySolar2();
		}

		/**
		 * Called when the block is placed in the world.
		 */
		public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
		{
			int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

			if (l == 0)
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
			}

			if (l == 1)
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
			}

			if (l == 2)
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
			}

			if (l == 3)
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
			}

			if (par6ItemStack.hasDisplayName())
			{
				((TileEntitySolar2)par1World.getTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
			}
		}

		/**
		 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
		 */
		public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
		{
			if (!keepMaceratorInventory)
			{
				TileEntitySolar2 TileEntitySolar = (TileEntitySolar2)par1World.getTileEntity(par2, par3, par4);

				if (TileEntitySolar != null)
				{
					for (int j1 = 0; j1 < TileEntitySolar.getSizeInventory(); ++j1)
					{
						ItemStack itemstack = TileEntitySolar.getStackInSlot(j1);

						if (itemstack != null)
						{
							float f = this.maceratorRand.nextFloat() * 0.8F + 0.1F;
							float f1 = this.maceratorRand.nextFloat() * 0.8F + 0.1F;
							float f2 = this.maceratorRand.nextFloat() * 0.8F + 0.1F;

							while (itemstack.stackSize > 0)
							{
								int k1 = this.maceratorRand.nextInt(21) + 10;

								if (k1 > itemstack.stackSize)
								{
									k1 = itemstack.stackSize;
								}

								itemstack.stackSize -= k1;
								EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

								if (itemstack.hasTagCompound())
								{
									entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
								}

								float f3 = 0.05F;
								entityitem.motionX = (double)((float)this.maceratorRand.nextGaussian() * f3);
								entityitem.motionY = (double)((float)this.maceratorRand.nextGaussian() * f3 + 0.2F);
								entityitem.motionZ = (double)((float)this.maceratorRand.nextGaussian() * f3);
								par1World.spawnEntityInWorld(entityitem);
							}
						}
					}

					par1World.func_147453_f(par2, par3, par4, par5);
				}
			}

			super.breakBlock(par1World, par2, par3, par4, par5, par6);
		}

		/**
		 * If this returns true, then comparators facing away from this block will use the value from
		 * getComparatorInputOverride instead of the actual redstone signal strength.
		 */
		public boolean hasComparatorInputOverride()
		{
			return true;
		}

		/**
		 * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
		 * strength when this block inputs to a comparator.
		 */
		public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
		{
			return Container.calcRedstoneFromInventory((IInventory)par1World.getTileEntity(par2, par3, par4));
		}

		@SideOnly(Side.CLIENT)

		/**
		 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
		 */
		public Block idPicked(World par1World, int par2, int par3, int par4)
		{
			return LightningMaterials.Solar2Idle;
		}

		@Override
		public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
			// TODO Auto-generated method stub
			return new TileEntitySolar2();
		}
		@SideOnly(Side.CLIENT)
	     public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4){
	    	 list.add("Generates 10 RF/t");
	     }
	}
