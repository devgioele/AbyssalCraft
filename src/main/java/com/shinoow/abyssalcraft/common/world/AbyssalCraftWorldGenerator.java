/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2022 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.world;

import java.util.Random;

import com.shinoow.abyssalcraft.api.biome.IDarklandsBiome;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.common.structures.StructureShoggothPit;
import com.shinoow.abyssalcraft.init.InitHandler;
import com.shinoow.abyssalcraft.lib.ACConfig;

import net.minecraft.block.material.Material;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

public class AbyssalCraftWorldGenerator implements IWorldGenerator {

	private StructureShoggothPit shoggothLair = new StructureShoggothPit();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator
			chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider instanceof WorldProviderSurface)
			generateSurface(world, random, chunkX*16, chunkZ*16);
	}

	public void generateSurface(World world, Random random, int chunkX, int chunkZ) {

		boolean blacklisted = InitHandler.INSTANCE.isDimBlacklistedFromStructureGen(world.provider.getDimension());
		if(ACConfig.generateDarklandsStructures && !blacklisted && world.getBiome(new BlockPos(chunkX, 0, chunkZ)) instanceof IDarklandsBiome){

			int x = chunkX + random.nextInt(16) + 8;
			int z = chunkZ + random.nextInt(16) + 8;
			DarklandsStructureGenerator.generate(1, world, random, world.getHeight(new BlockPos(x, 0, z)));

			x = chunkX + random.nextInt(16) + 8;
			z = chunkZ + random.nextInt(16) + 8;
			DarklandsStructureGenerator.generate(2, world, random, world.getHeight(new BlockPos(x, 0, z)));

			x = chunkX + random.nextInt(16) + 8;
			z = chunkZ + random.nextInt(16) + 8;
			DarklandsStructureGenerator.generate(3, world, random, world.getHeight(new BlockPos(x, 0, z)));

			x = chunkX + random.nextInt(16) + 8;
			z = chunkZ + random.nextInt(16) + 8;
			DarklandsStructureGenerator.generate(4, world, random, world.getHeight(new BlockPos(x, 0, z)),
					Blocks.GRASS.getDefaultState(), ACBlocks.getInstance().darkstone.getBlock().getDefaultState());

			x = chunkX + random.nextInt(16) + 8;
			z = chunkZ + random.nextInt(16) + 8;
			DarklandsStructureGenerator.generate(0, world, random, world.getHeight(new BlockPos(x, 0, z)));
		}

		if(!InitHandler.INSTANCE.isDimBlacklistedFromOreGen(world.provider.getDimension())) {
			if(ACConfig.generateCoraliumOre){
				for(int rarity = 0; rarity < InitHandler.coraliumOreGeneration[0]/2; rarity++) {
					int veinSize = InitHandler.coraliumOreGeneration[1];
					int x = chunkX + random.nextInt(16);
					int y = random.nextInt(InitHandler.coraliumOreGeneration[2]);
					int z = chunkZ + random.nextInt(16);
					if(BiomeDictionary.hasType(world.getBiome(new BlockPos(x, 0, z)), Type.SWAMP))
						new WorldGenMinable(ACBlocks.getInstance().coralium_ore.getBlock().getDefaultState(), veinSize).generate(world, random, new BlockPos(x, y, z));
				}

				for(int rarity = 0; rarity < InitHandler.coraliumOreGeneration[0]; rarity++) {
					int veinSize = InitHandler.coraliumOreGeneration[1];
					int x = chunkX + random.nextInt(16);
					int y = random.nextInt(InitHandler.coraliumOreGeneration[2]);
					int z = chunkZ + random.nextInt(16);
					if(BiomeDictionary.hasType(world.getBiome(new BlockPos(x, 0, z)), Type.OCEAN) &&
							world.getBiome(new BlockPos(x, 0, z))!=Biomes.DEEP_OCEAN)
						new WorldGenMinable(ACBlocks.getInstance().coralium_ore.getBlock().getDefaultState(), veinSize).generate(world, random, new BlockPos(x, y, z));
					if(world.getBiome(new BlockPos(x, 0, z))==Biomes.DEEP_OCEAN)
						new WorldGenMinable(ACBlocks.getInstance().coralium_ore.getBlock().getDefaultState(), veinSize).generate(world, random, new BlockPos(x, y-20, z));
				}
			}

			if(ACConfig.generateNitreOre)
				for(int rarity = 0; rarity < 3; rarity++) {
					int veinSize = 4 + random.nextInt(2);
					int x = chunkX + random.nextInt(16);
					int y = random.nextInt(30);
					int z = chunkZ + random.nextInt(16);

					new WorldGenMinable(ACBlocks.getInstance().nitre_ore.getBlock().getDefaultState(), veinSize).generate(world, random, new BlockPos(x, y, z));
				}
		}

		if(ACConfig.generateShoggothLairs && !blacklisted)
			for(int i = 0; i < 1; i++){
				int x = chunkX + random.nextInt(16) + 8;
				int z = chunkZ + random.nextInt(2) + 28;
				BlockPos pos1 = world.getHeight(new BlockPos(x, 0, z));
				boolean swamp = BiomeDictionary.hasType(world.getBiome(pos1), Type.SWAMP);
				if(world.getBlockState(pos1).getMaterial() == Material.PLANTS) pos1 = pos1.down();
				if(swamp || BiomeDictionary.hasType(world.getBiome(pos1), Type.RIVER) &&
						!BiomeDictionary.hasType(world.getBiome(pos1), Type.OCEAN))
					if(swamp ? ACConfig.shoggothLairSpawnRate > 0 && random.nextInt(ACConfig.shoggothLairSpawnRate) == 0 :
						ACConfig.shoggothLairSpawnRateRivers > 0 && random.nextInt(ACConfig.shoggothLairSpawnRateRivers) == 0)
						if(!world.isAirBlock(pos1.north(13)) && !world.isAirBlock(pos1.north(20)) && !world.isAirBlock(pos1.north(27)))
							shoggothLair.generate(world, random, pos1);
			}
	}
}
