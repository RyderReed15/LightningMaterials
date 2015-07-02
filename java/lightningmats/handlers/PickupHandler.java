package lightningmats.handlers;


import lightningmats.LightningMaterials;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class PickupHandler {
	@SubscribeEvent
	public void onPickup(PlayerEvent.ItemPickupEvent event)
		{
		if (event.pickedUp.getEntityItem().getItem() == Item.getItemFromBlock(LightningMaterials.NickelOre)){
			event.player.addStat(LightningMaterials.NickelAch, 1);
		}
		if (event.pickedUp.getEntityItem().getItem() == Item.getItemFromBlock(LightningMaterials.PlatinumOre)){
			event.player.addStat(LightningMaterials.PlatinumAch, 1);
		}
		if (event.pickedUp.getEntityItem().getItem() == Item.getItemFromBlock(LightningMaterials.MyBlock_1)){
			event.player.addStat(LightningMaterials.LightningAch, 1);
		}
	}
	@SubscribeEvent
	public void OnCrafted(PlayerEvent.ItemCraftedEvent event)
	{
	if (event.crafting.getItem() == LightningMaterials.MySword_1){
		event.player.addStat(LightningMaterials.SwordAch, 1);
	}
	if (event.crafting.getItem() == Item.getItemFromBlock(LightningMaterials.SolarIdle)){
		event.player.addStat(LightningMaterials.SolarAch, 1);
	}
	if (event.crafting.getItem() == LightningMaterials.MultiTool){
		event.player.addStat(LightningMaterials.AllInOne, 1);
	}
}}
