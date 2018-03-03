package com.black_dog20.gadgetron.worldgen;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.init.ModBlocks;
import com.black_dog20.gadgetron.utility.Helper;

public class OreGenerator implements IWorldGenerator{
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == 0)
		{
			for(int i = 0; i < ModConfig.RaritaniumPerChunk; i++)
			{
				BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, 1, 20), (chunkZ*16) + random.nextInt(16));
				new WorldGenMinable(ModBlocks.RaritaniumCrystal.getDefaultState(), Helper.GetRandonBetween(random, 1, 4), BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
			}

			for(int i = 0; i < ModConfig.AdamantinePerChunk; i++)
			{
				BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, 1, 60), (chunkZ*16) + random.nextInt(16));
				new WorldGenMinable(ModBlocks.AdamantineOre.getDefaultState(), Helper.GetRandonBetween(random, 1, 8), BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
			}

			for(int i = 0; i < ModConfig.CarbonoxPerChunk; i++)
			{
				BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, 1, 60), (chunkZ*16) + random.nextInt(16));
				new WorldGenMinable(ModBlocks.CarbonoxOre.getDefaultState(), Helper.GetRandonBetween(random, 1, 8), BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
			}
			
			for(int i = 0; i < ModConfig.TrilliumPerChunk; i++)
			{
				BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, 1, 60), (chunkZ*16) + random.nextInt(16));
				new WorldGenMinable(ModBlocks.TrilliumOre.getDefaultState(), Helper.GetRandonBetween(random, 1, 8), BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
			}
		}
		
	}

}
