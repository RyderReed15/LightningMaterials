package lightningmats.power;

import java.util.HashMap;
import java.util.Map;

import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Reference implementation of {@link IEnergyStorage}. Use/extend this or implement your own.
 * 
 * @author King Lemming
 * 
 */
public class LMEnergyStorage implements IEnergyStorage {

	public int energy;
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	private TileEntity teInstance;
	private int transferRate;
	private Map<ForgeDirection, TileEntity> teNeighborMap;

	public LMEnergyStorage(TileEntity teInstance, int capacity, int transferRate, boolean useSync) {
		this.teInstance = teInstance;
		
		this.teNeighborMap = new HashMap<ForgeDirection, TileEntity>();

	}

	public LMEnergyStorage(int capacity, int maxTransfer) {

		this(capacity, maxTransfer, maxTransfer);
	}

	public LMEnergyStorage(int capacity, int maxReceive, int maxExtract) {

		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}

	public LMEnergyStorage readFromNBT(NBTTagCompound nbt) {

		this.energy = nbt.getInteger("Energy");

		if (energy > capacity) {
			energy = capacity;
		}
		return this;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		if (energy < 0) {
			energy = 0;
		}
		nbt.setInteger("Energy", energy);
		return nbt;
	}

	public void setCapacity(int capacity) {

		this.capacity = capacity;

		if (energy > capacity) {
			energy = capacity;
		}
	}

	public void setMaxTransfer(int maxTransfer) {

		setMaxReceive(maxTransfer);
		setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(int maxReceive) {

		this.maxReceive = maxReceive;
	}

	public void setMaxExtract(int maxExtract) {

		this.maxExtract = maxExtract;
	}

	public int getMaxReceive() {

		return maxReceive;
	}

	public int getMaxExtract() {

		return maxExtract;
	}

	/**
	 * This function is included to allow for server -&gt; client sync. Do not call this externally to the containing Tile Entity, as not all IEnergyHandlers
	 * are guaranteed to have it.
	 * 
	 * @param energy
	 */
	public void setEnergyStored(int energy) {

		this.energy = energy;

		if (this.energy > capacity) {
			this.energy = capacity;
		} else if (this.energy < 0) {
			this.energy = 0;
		}
	}

	/**
	 * This function is included to allow the containing tile to directly and efficiently modify the energy contained in the EnergyStorage. Do not rely on this
	 * externally, as not all IEnergyHandlers are guaranteed to have it.
	 * 
	 * @param energy
	 */
	public void modifyEnergyStored(int energy) {

		this.energy += energy;

		if (this.energy > capacity) {
			this.energy = capacity;
		} else if (this.energy < 0) {
			this.energy = 0;
		}
	}

	/* IEnergyStorage */
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {

		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate) {
			energy += energyReceived;
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {

		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		if (!simulate) {
			energy -= energyExtracted;
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored() {

		return energy;
	}

	@Override
	public int getMaxEnergyStored() {

		return capacity;
	}
	public void shareEnergyWithNeighbors() { 
		 		if(teNeighborMap.isEmpty()) return; 
		 
		 
		 		for (Map.Entry<ForgeDirection, TileEntity> mapEntry : teNeighborMap.entrySet()) { 
		 		    if(mapEntry.getValue() instanceof IEnergyReceiver) pushEnergy(mapEntry.getValue(), mapEntry.getKey(), getTransferRate()); 
		 		} 
		 	} 

	public int getTransferRate() { 
		 		return transferRate; 
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

}

