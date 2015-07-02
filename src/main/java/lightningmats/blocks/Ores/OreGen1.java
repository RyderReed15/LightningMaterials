package lightningmats.blocks.Ores;

import java.util.Random;

import lightningmats.LightningMaterials;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class OreGen1 implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.dimensionId)
        {
            case -1: 
                generateHell(world, random,chunkX*16,chunkZ*16);            
        }              
    }



    private void generateHell(World world, Random random, int BlockX, int BlockZ) {
        for(int i =0; i<LightningMaterials.netherlightningPerChunk; i++){ 
            int Xcoord = BlockX + random.nextInt(16);
            int Zcoord = BlockZ + random.nextInt(16);
            int Ycoord = random.nextInt(LightningMaterials.netherlightningHeight); 
            (new WorldGenMinable(LightningMaterials.NetherLightning, LightningMaterials.netherlightningPerGroup, Blocks.netherrack)).generate(world, random, Xcoord, Ycoord, Zcoord);            
            
        }
        for(int i =0; i<LightningMaterials.netherredstonePerChunk; i++){ 
            int Xcoord = BlockX + random.nextInt(16);
            int Zcoord = BlockZ + random.nextInt(16);
            int Ycoord = random.nextInt(LightningMaterials.netherredstoneHeight);
            (new WorldGenMinable(LightningMaterials.NetherRedstone, LightningMaterials.netherredstonePerGroup, Blocks.netherrack)).generate(world, random, Xcoord, Ycoord, Zcoord);
}
        for(int i =0; i<LightningMaterials.netheremeraldPerChunk; i++){ 
            int Xcoord = BlockX + random.nextInt(16);
            int Zcoord = BlockZ + random.nextInt(16);
            int Ycoord = random.nextInt(LightningMaterials.netheremeraldHeight);
            (new WorldGenMinable(LightningMaterials.NetherEmerald, LightningMaterials.netheremeraldPerGroup, Blocks.netherrack)).generate(world, random, Xcoord, Ycoord, Zcoord);
}
        for(int i =0; i<LightningMaterials.netherdiamondPerChunk; i++){ 
            int Xcoord = BlockX + random.nextInt(16);
            int Zcoord = BlockZ + random.nextInt(16);
            int Ycoord = random.nextInt(LightningMaterials.netherdiamondHeight);
            (new WorldGenMinable(LightningMaterials.NetherDiamond, LightningMaterials.netherdiamondPerGroup, Blocks.netherrack)).generate(world, random, Xcoord, Ycoord, Zcoord);
}
        for(int i =0; i<LightningMaterials.nethercoalPerChunk; i++){ 
            int Xcoord = BlockX + random.nextInt(16);
            int Zcoord = BlockZ + random.nextInt(16);
            int Ycoord = random.nextInt(LightningMaterials.nethercoalHeight);
            (new WorldGenMinable(LightningMaterials.NetherCoal, LightningMaterials.nethercoalPerGroup, Blocks.netherrack)).generate(world, random, Xcoord, Ycoord, Zcoord);
}
        for(int i =0; i<LightningMaterials.nethergoldPerChunk; i++){ 
            int Xcoord = BlockX + random.nextInt(16);
            int Zcoord = BlockZ + random.nextInt(16);
            int Ycoord = random.nextInt(LightningMaterials.nethergoldHeight);
            (new WorldGenMinable(LightningMaterials.NetherGold, LightningMaterials.nethergoldPerGroup, Blocks.netherrack)).generate(world, random, Xcoord, Ycoord, Zcoord);
}
        for(int i =0; i<LightningMaterials.netherironPerChunk; i++){ 
            int Xcoord = BlockX + random.nextInt(16);
            int Zcoord = BlockZ + random.nextInt(16);
            int Ycoord = random.nextInt(LightningMaterials.netherironHeight);
            (new WorldGenMinable(LightningMaterials.NetherIron, LightningMaterials.netherironPerGroup, Blocks.netherrack)).generate(world, random, Xcoord, Ycoord, Zcoord);
}
    }}
    
    


    

