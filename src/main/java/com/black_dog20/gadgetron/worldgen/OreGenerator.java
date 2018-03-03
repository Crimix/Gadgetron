package com.black_dog20.gadgetron.worldgen;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.init.ModBlocks;
import com.black_dog20.gadgetron.utility.Helper;

public class OreGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(!(chunkGenerator instanceof ChunkProviderHell) && !(chunkGenerator instanceof ChunkProviderEnd))
		{
			if(ModConfig.worldgen.raritanium.generate)
				for(int i = 0; i < ModConfig.worldgen.raritanium.perChunk; i++)
				{
					BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, ModConfig.worldgen.raritanium.heightMin, ModConfig.worldgen.raritanium.heightMax), (chunkZ*16) + random.nextInt(16));
					new WorldGenMinable(ModBlocks.RaritaniumCrystal.getDefaultState(), Helper.GetRandonBetween(random, ModConfig.worldgen.raritanium.veinMin, ModConfig.worldgen.raritanium.veinMax), BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
				}

			if(ModConfig.worldgen.adamantine.generate)
				for(int i = 0; i < ModConfig.worldgen.adamantine.perChunk; i++)
				{
					BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, ModConfig.worldgen.adamantine.heightMin, ModConfig.worldgen.adamantine.heightMax), (chunkZ*16) + random.nextInt(16));
					new WorldGenMinable(ModBlocks.AdamantineOre.getDefaultState(), Helper.GetRandonBetween(random, ModConfig.worldgen.adamantine.veinMin, ModConfig.worldgen.adamantine.veinMax), BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
				}

			if(ModConfig.worldgen.carbonox.generate)
				for(int i = 0; i < ModConfig.worldgen.carbonox.perChunk; i++)
				{
					BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, ModConfig.worldgen.carbonox.heightMin, ModConfig.worldgen.carbonox.heightMax), (chunkZ*16) + random.nextInt(16));
					new WorldGenMinable(ModBlocks.CarbonoxOre.getDefaultState(), Helper.GetRandonBetween(random, ModConfig.worldgen.carbonox.veinMin, ModConfig.worldgen.carbonox.veinMax), BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
				}

			if(ModConfig.worldgen.trillium.generate)
				for(int i = 0; i < ModConfig.worldgen.trillium.perChunk; i++)
				{
					BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, ModConfig.worldgen.trillium.heightMin, ModConfig.worldgen.trillium.heightMax), (chunkZ*16) + random.nextInt(16));
					new WorldGenMinable(ModBlocks.TrilliumOre.getDefaultState(), Helper.GetRandonBetween(random, ModConfig.worldgen.trillium.veinMin, ModConfig.worldgen.trillium.veinMax), BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
				}

			if(ModConfig.worldgen.titanium.generate)
				for(int i = 0; i < ModConfig.worldgen.titanium.perChunk; i++)
				{
					BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, ModConfig.worldgen.titanium.heightMin, ModConfig.worldgen.titanium.heightMax), (chunkZ*16) + random.nextInt(16));
					new WorldGenMinable(ModBlocks.TitaniumOre.getDefaultState(), Helper.GetRandonBetween(random, ModConfig.worldgen.titanium.veinMin, ModConfig.worldgen.titanium.veinMax), BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
				}
		}

	}

}
