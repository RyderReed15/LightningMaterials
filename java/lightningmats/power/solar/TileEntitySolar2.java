package lightningmats.power.solar;

import lightningmats.LightningMaterials;
import lightningmats.items.ItemPowerStorage;
import lightningmats.power.Battery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySolar2 extends TileEntity implements ISidedInventory{
		private static final int[] slots_top = new int[] {0};
		private static final int[] slots_bottom = new int[] {2, 1};
		private static final int[] slots_sides = new int[] {1};

		/**
		* The ItemStacks that hold the items currently being used in the furnace
		*/
		private ItemStack[] slots = new ItemStack[4];

		/** the speed of this furnace, 200 is normal / how many ticks it takes : 30 ticks = 1 second */
		public int maceratingSpeed = 30;
	
		/** The number of ticks that the furnace will keep burning */
		public int power;
		public int maxPower = 150000;

		/** The number of ticks that the current item has been cooking for */
		public int cookTime;
	
		private String field_94130_e;

    	/**
     	* Returns the number of slots in the inventory.
     	*/
    	public int getSizeInventory()
    	{
        	return this.slots.length;
    	}

    	/**
     	* Returns the stack in slot i
     	*/
    	public ItemStack getStackInSlot(int par1)
    	{
        	return this.slots[par1];
    	}

    	/**
     	* Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     	* new stack.
     	*/
    	public ItemStack decrStackSize(int par1, int par2)
    	{
        	if (this.slots[par1] != null)
        	{
            		ItemStack itemstack;

            		if (this.slots[par1].stackSize <= par2)
           		{
                		itemstack = this.slots[par1];
                		this.slots[par1] = null;
               			return itemstack;
            		}
            		else
            		{
                		itemstack = this.slots[par1].splitStack(par2);

                		if (this.slots[par1].stackSize == 0)
                		{
                    			this.slots[par1] = null;
               			}
	
                		return itemstack;
            		}
        	}
        	else
        	{
            		return null;
        	}
    	}

    	/**
     	* When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     	* like when you close a workbench GUI.
     	*/
    	public ItemStack getStackInSlotOnClosing(int par1)
    	{
        	if (this.slots[par1] != null)
        	{
            		ItemStack itemstack = this.slots[par1];
            		this.slots[par1] = null;
            		return itemstack;
        	}else{
            		return null;
        	}
    	}

    	/**
     	* Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     	*/
    	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    	{
        	this.slots[par1] = par2ItemStack;

        	if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        	{
        		par2ItemStack.stackSize = this.getInventoryStackLimit();
        	}
    	}

    	/**
     	* Returns the name of the inventory.
     	*/
    	public String getInvName()
    	{
        	return this.isInvNameLocalized() ? this.field_94130_e : "Solar Generator";
    	}

    	/**
     	* If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     	* language. Otherwise it will be used directly.
     	*/
    	public boolean isInvNameLocalized()
    	{
        	return this.field_94130_e != null && this.field_94130_e.length() > 0;
    	}

    	/**
     	* Sets the custom display name to use when opening a GUI linked to this tile entity.
     	*/
    	public void setGuiDisplayName(String par1Str)
    	{
        	this.field_94130_e = par1Str;
    	}

    	/**
     	* Reads a tile entity from NBT.
     	*/
    	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    	{
        	super.readFromNBT(par1NBTTagCompound);
        	NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 0);
        	this.slots = new ItemStack[this.getSizeInventory()];

        	for (int i = 0; i < nbttaglist.tagCount(); ++i)
        	{
            		NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            		byte b0 = nbttagcompound1.getByte("Slot");

            		if (b0 >= 0 && b0 < this.slots.length)
            		{
                		this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            		}
        	}

        	this.power = par1NBTTagCompound.getShort("power");
        	this.cookTime = par1NBTTagCompound.getShort("CookTime");

        	if (par1NBTTagCompound.hasKey("CustomName"))
        	{
            	this.field_94130_e = par1NBTTagCompound.getString("CustomName");
        	}
    	}

    	/**
     	* Writes a tile entity to NBT.
     	*/
    	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    	{
        	super.writeToNBT(par1NBTTagCompound);
        	par1NBTTagCompound.setShort("power", (short)this.power);
        	par1NBTTagCompound.setShort("CookTime", (short)this.cookTime);
        	NBTTagList nbttaglist = new NBTTagList();

        	for (int i = 0; i < this.slots.length; ++i)
        	{	
            		if (this.slots[i] != null)
            		{
                		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                		nbttagcompound1.setByte("Slot", (byte)i);
                		this.slots[i].writeToNBT(nbttagcompound1);
                		nbttaglist.appendTag(nbttagcompound1);
            		}
        	}

        	par1NBTTagCompound.setTag("Items", nbttaglist);

        	if (this.isInvNameLocalized())
        	{
            		par1NBTTagCompound.setString("CustomName", this.field_94130_e);
        	}
    	}

    	/**
     	* Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     	* this more of a set than a get?*
     	*/
    	public int getInventoryStackLimit()
    	{
        	return 64;
    	}

    	@SideOnly(Side.CLIENT)

    	/**
     	* Returns an integer between 0 and the passed value representing how close the current item is to being completely
     	* cooked
     	*/
    	public int getCookProgressScaled(int par1)
    	{
      		return this.cookTime * par1 / this.maceratingSpeed;
    	}
    
    	public int getPowerRemainingScaled(int par1){
        	return this.power * par1 / this.maxPower;
    	}

    	/**
     	* Returns true if the furnace is currently burning
     	*/
    	public boolean hasPower()
    	{
        	return this.power > 0;
    	}
    
    	public boolean isMacerating(){
    		return this.cookTime > 0;
    	}

    	/**
     	* Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     	* ticks and creates a new spawn inside its implementation.
     	*/
    	public void updateEntity(){
    		boolean flag = this.power > 0;
        	boolean flag1 = false;
    	
        	if (hasPower() && isMacerating()){
            		this.power--;this.power--;
        	}

        	if (!this.worldObj.isRemote){
        	if(isItemPowerStorage() && this.slots[3].getItemDamage() > 0 && this.power > 0){
        	this.slots[3].setItemDamage(this.slots[3].getItemDamage()-10);
        	this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;
        	this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;
        	this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;
        	this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;
        	this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;
        	this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;
        	this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;
        	this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;
        	this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;
        	this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;this.power--;
        	}
        	if(isPowerProducable()){this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;this.power++;}
        	
        	
            	if (this.power < this.maxPower && this.getItemPower(this.slots[1]) > 0 && this.canPower()){
            	if(!this.slots[1].isItemStackDamageable()){
            		this.power += getItemPower(this.slots[1]);

            		flag1 = true;
            	
            		if (this.slots[1] != null){
                    		this.slots[1].stackSize--;

                    		if (this.slots[1].stackSize == 0){
                        		this.slots[1] = this.slots[1].getItem().getContainerItem(slots[1]);
                    		}
                	}                
            	}else{
            	if(this.slots[1].getItemDamage() < this.slots[1].getMaxDamage()){
            	this.power += getItemPower(this.slots[1]);
            	this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getItemDamage()+1 );
            	}}}

            	
        	if (flag1){
            		this.closeInventory();}}
        	
    	}
    
    	public boolean isOre(ItemStack itemstack){
    		String[] oreNames = OreDictionary.getOreNames();
    	
    		for(int i = 0; i < oreNames.length; i++){
    			if(oreNames[i].contains("ore")){
	    			if(OreDictionary.getOres(oreNames[i]) != null){
	    				if(OreDictionary.getOres(oreNames[i]).get(0).getItem() == itemstack.getItem()){
	    					return true;        			
	    				}
	    			}
    			}
    		}
    	
    		return false;
    	}
    	
    	private boolean isItemPowerStorage(){
    	 
    	 if(this.slots[3] != null) return this.slots[3].getItem() instanceof ItemPowerStorage;
    	 return false;
    	 
    	 
    	 
    	 }
    	

    	/**
     	* Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     	*/
    	
        	
    	

    	/**
     	* Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     	*/
    	


    	/**
     	* Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     	* fuel
     	*/
    	public static int getItemPower(ItemStack par0ItemStack){
        	if (par0ItemStack == null){
            	return 0;
        	}else{
        		Item i = par0ItemStack.getItem();
        	
        		if (i == Items.redstone) return 1500;
        		if (i == LightningMaterials.Battery) return 20;
        		if (i == Items.coal) return 1000;
        		if (i == Items.lava_bucket) return 12500;
            	return 0;
        	}
    	}

    	/**
     	* Return true if item is a fuel source (getItempower() > 0).
     	*/
    	public static boolean isItemFuel(ItemStack par0ItemStack)
    	{
        	return getItemPower(par0ItemStack) > 0;
    	}

    	/**
     	* Do not make give this method the name canInteractWith because it clashes with Container
     	*/
    	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    	{
        	return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    	}	

    	public void openChest() {}

    	public void closeChest() {}

    	/**
     	* Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
		*/
		public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
		{
				return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
		}

    	/**
     	* Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     	* block.
     	*/
    	public int[] getAccessibleSlotsFromSide(int par1)
    	{
        	return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    	}

    	/**
     	* Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     	* side
     	*/
    	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
		{
  	      return this.isItemValidForSlot(par1, par2ItemStack);
    	}

    	/**
    	* Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     	* side
     	*/
    	

		@Override
		public String getInventoryName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasCustomInventoryName() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void openInventory() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void closeInventory() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_,
				int p_102008_3_) {
			// TODO Auto-generated method stub
			return false;
		}
	
		public boolean canPower(){
			if((this.maxPower-this.power) >= this.getItemPower(this.slots[1]))return true;
			return false;
		}
private boolean isPowerProducable(){
    	 if(this.power < this.maxPower) return true;
    	 return false;
    	 
    	 }}