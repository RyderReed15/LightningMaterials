package lightningmats.blocks.Ores;

import java.util.Random;

import lightningmats.LightningMaterials;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class GemOreGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId)
		{
		case 0: 
			generatesurface(world, random,chunkX*16,chunkZ*16);            
		}              
	}



	private void generatesurface(World world, Random random, int BlockX, int BlockZ) {
		for(int i =0; i<3; i++){ 
			int Xcoord = BlockX + random.nextInt(16);
			int Zcoord = BlockZ + random.nextInt(16);
			int Ycoord = random.nextInt(31); 
			(new WorldGenMinable(LightningMaterials.RubyOre, 3, Blocks.stone)).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		for(int i =0; i<2; i++){ 
			int Xcoord = BlockX + random.nextInt(16);
			int Zcoord = BlockZ + random.nextInt(16);
			int Ycoord = random.nextInt(21);{
				(new WorldGenMinable(LightningMaterials.PlatinumOre, 3, Blocks.stone)).generate(world, random, Xcoord, Ycoord, Zcoord);
			}}
			for(int i =0; i<3; i++){ 
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(31);{
					(new WorldGenMinable(LightningMaterials.AmethystOre, 3, Blocks.stone)).generate(world, random, Xcoord, Ycoord, Zcoord);
				}}
	for(int i =0; i<3; i++){ 
		int Xcoord = BlockX + random.nextInt(16);
		int Zcoord = BlockZ + random.nextInt(16);
		int Ycoord = random.nextInt(31);{
			(new WorldGenMinable(LightningMaterials.SapphireOre, 3, Blocks.stone)).generate(world, random, Xcoord, Ycoord, Zcoord);

		}}
	for(int i =0; i<LightningMaterials.nickelPerChunk; i++){ 
		int Xcoord = BlockX + random.nextInt(16);
		int Zcoord = BlockZ + random.nextInt(16);
		int Ycoord = random.nextInt(LightningMaterials.nickelHeight);{
			(new WorldGenMinable(LightningMaterials.NickelOre, LightningMaterials.nickelPerGroup, Blocks.stone)).generate(world, random, Xcoord, Ycoord, Zcoord);

		}}
	for(int i =0; i<LightningMaterials.zincPerChunk; i++){ 
		int Xcoord = BlockX + random.nextInt(16);
		int Zcoord = BlockZ + random.nextInt(16);
		int Ycoord = random.nextInt(LightningMaterials.zincHeight);{
			(new WorldGenMinable(LightningMaterials.ZincOre, LightningMaterials.zincPerGroup, Blocks.stone)).generate(world, random, Xcoord, Ycoord, Zcoord);

		}}
	
	}}






