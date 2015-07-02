package lightningmats.power.energycell;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import cofh.api.energy.TileEnergyHandler;
import lightningmats.power.LMEnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyCell extends TileEnergyHandler implements IInventory, IEnergyHandler {

    protected LMEnergyStorage storage;
    public static int MAX_RECEIVE = 32000;
    public static int CAPACITY = 160000000;

    public TileEntityEnergyCell(){
        storage = new LMEnergyStorage(CAPACITY, MAX_RECEIVE);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }

    public int extractEnergy(int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }
    
    @Override
    public int getEnergyStored(ForgeDirection from){
        return storage.getEnergyStored();
    }


	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		// TODO Auto-generated method stub
		return 0;
	}
	private void pushEnergy(TileEntity tileEntity, ForgeDirection direction, int transferRate) { 
 		//TileEntity check 
 		if(tileEntity == null) return; 
 		if(!(tileEntity instanceof IEnergyReceiver)) return; 
 		 
 		//Instances 
 		IEnergyReceiver teReceiver = ((IEnergyReceiver)tileEntity); 
		ForgeDirection directionRecieve = direction.getOpposite(); 
 		 
 		//Energy push 
 		if(teReceiver.canConnectEnergy(directionRecieve) && teReceiver.getEnergyStored(directionRecieve) < teReceiver.getMaxEnergyStored(directionRecieve)) { 
 			if(teReceiver.receiveEnergy(directionRecieve, transferRate, true) > 0 && this.extractEnergy(transferRate, true) > 0) { 
 				this.extractEnergy(teReceiver.receiveEnergy(directionRecieve, transferRate, false), false); 
 			} 
 		} 
 	} 
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		
	}

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
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
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
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		return false;
	}
	
}