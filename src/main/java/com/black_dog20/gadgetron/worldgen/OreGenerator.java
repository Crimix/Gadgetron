package com.black_dog20.gadgetron.worldgen;

import java.util.List;
import java.util.Random;

import com.black_dog20.gadgetron.Gadgetron;
import com.black_dog20.gadgetron.config.ModConfig;
import com.black_dog20.gadgetron.config.objects.OreGenConfig;
import com.black_dog20.gadgetron.init.ModBlocks;
import com.black_dog20.gadgetron.reference.Reference;
import com.black_dog20.gadgetron.utility.Helper;
import com.google.common.collect.ArrayListMultimap;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class OreGenerator implements IWorldGenerator{

	private OreGenConfig raritanium = ModConfig.worldgen.raritanium;
	private OreGenConfig adamantine = ModConfig.worldgen.adamantine;
	private OreGenConfig carbonox = ModConfig.worldgen.carbonox;
	private OreGenConfig trillium = ModConfig.worldgen.trillium;
	private OreGenConfig titanium = ModConfig.worldgen.titanium;
	public static boolean correctReplaceBlocks = true;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(!correctReplaceBlocks){
			return;
		}
		
		if(raritanium.generate && !Helper.IntArrayContains(raritanium.dimBlacklist,world.provider.getDimension()))
			for(int i = 0; i < raritanium.perChunk; i++)
			{
				BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, raritanium.heightMin, raritanium.heightMax), (chunkZ*16) + random.nextInt(16));
				new WorldGenMinable(ModBlocks.RaritaniumCrystal.getDefaultState(),raritanium.veinMax, BlockMatcher.forBlock(Block.getBlockFromName(raritanium.replaceBlock))).generate(world, random, pos);
			}

		if(adamantine.generate && !Helper.IntArrayContains(adamantine.dimBlacklist,world.provider.getDimension()))
			for(int i = 0; i < adamantine.perChunk; i++)
			{
				BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, adamantine.heightMin, adamantine.heightMax), (chunkZ*16) + random.nextInt(16));
				new WorldGenMinable(ModBlocks.AdamantineOre.getDefaultState(), adamantine.veinMax, BlockMatcher.forBlock(Block.getBlockFromName(adamantine.replaceBlock))).generate(world, random, pos);
			}

		if(carbonox.generate && !Helper.IntArrayContains(carbonox.dimBlacklist,world.provider.getDimension()))
			for(int i = 0; i < carbonox.perChunk; i++)
			{
				BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, carbonox.heightMin, carbonox.heightMax), (chunkZ*16) + random.nextInt(16));
				new WorldGenMinable(ModBlocks.CarbonoxOre.getDefaultState(), carbonox.veinMax, BlockMatcher.forBlock(Block.getBlockFromName(carbonox.replaceBlock))).generate(world, random, pos);
			}

		if(trillium.generate && !Helper.IntArrayContains(trillium.dimBlacklist,world.provider.getDimension()))
			for(int i = 0; i < trillium.perChunk; i++)
			{
				BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, trillium.heightMin, trillium.heightMax), (chunkZ*16) + random.nextInt(16));
				new WorldGenMinable(ModBlocks.TrilliumOre.getDefaultState(), trillium.veinMax, BlockMatcher.forBlock(Block.getBlockFromName(trillium.replaceBlock))).generate(world, random, pos);
			}

		if(titanium.generate && !Helper.IntArrayContains(titanium.dimBlacklist,world.provider.getDimension()))
			for(int i = 0; i < titanium.perChunk; i++)
			{
				BlockPos pos = new BlockPos(chunkX*16 + random.nextInt(16), Helper.GetRandonBetween(random, titanium.heightMin, titanium.heightMax), (chunkZ*16) + random.nextInt(16));
				new WorldGenMinable(ModBlocks.TitaniumOre.getDefaultState(), titanium.veinMax, BlockMatcher.forBlock(Block.getBlockFromName(titanium.replaceBlock))).generate(world, random, pos);
			}

	}
	


	public static ArrayListMultimap<Integer, ChunkPos> retrogenChunks = ArrayListMultimap.create();

	@SubscribeEvent
	public void onChunkSave(ChunkDataEvent.Save event){
		if(correctReplaceBlocks){
			NBTTagCompound nbt = new NBTTagCompound();
			event.getData().setTag(Reference.MOD_NAME, nbt);
			nbt.setBoolean("Gen", true);
		}
	}

	@SubscribeEvent
	public void onChunkLoad(ChunkDataEvent.Load event)
	{
		int dimension = event.getWorld().provider.getDimension();
		if((!event.getData().getCompoundTag(Reference.MOD_NAME).hasKey("Gen")) && ModConfig.worldgen.retrogen){
			if(ModConfig.worldgen.logretrogen)
				Gadgetron.logger.info("Chunk "+event.getChunk().getPos()+" has been flagged for retrogen by " + Reference.MOD_ID);
			retrogenChunks.put(dimension, event.getChunk().getPos());
		}
	}

	@SubscribeEvent
	public void onServerWorldTick(TickEvent.WorldTickEvent event)
	{
		if(event.side==Side.CLIENT || event.phase==TickEvent.Phase.START)
			return;
		int dimension = event.world.provider.getDimension();
		int processedChunks = 0;
		List<ChunkPos> chunks = retrogenChunks.get(dimension);
		if(chunks != null && chunks.size() > 0)
			for(int i = 0; i < 2; i++)
			{
				chunks = retrogenChunks.get(dimension);
				if(chunks == null || chunks.size() <= 0)
					break;
				processedChunks++;
				ChunkPos pos = chunks.get(0);
				Random FMLRandom = Helper.CreateFMLRandom(pos, event.world.getSeed());
				generate(FMLRandom, pos.x, pos.z, event.world, null, null);
				chunks.remove(0);
			}
		if(processedChunks > 0 && ModConfig.worldgen.logretrogen)
			Gadgetron.logger.info("Retrogen is being done, " +Math.max(0,chunks.size())+" chunks remaining");
	}



}
