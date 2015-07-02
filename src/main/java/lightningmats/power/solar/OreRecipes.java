package lightningmats.power.solar;

import lightningmats.LightningMaterials;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class OreRecipes{

		private static final FurnaceRecipes oreBase = FurnaceRecipes.smelting();
		
		public static FurnaceRecipes ores(){
			return oreBase;
		}
		
		static{
			OreDictionary.registerOre("oreCoal", Blocks.coal_ore);
		
			
		}
		
	}
	
	
	
