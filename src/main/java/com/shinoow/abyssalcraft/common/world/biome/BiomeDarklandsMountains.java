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
package com.shinoow.abyssalcraft.common.world.biome;

import java.util.Random;

import com.shinoow.abyssalcraft.api.biome.IDarklandsBiome;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.common.entity.*;
import com.shinoow.abyssalcraft.lib.ACClientVars;
import com.shinoow.abyssalcraft.lib.ACConfig;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeDarklandsMountains extends Biome implements IDarklandsBiome {

	public BiomeDarklandsMountains(BiomeProperties par1)
	{
		super(par1);
		topBlock = ACBlocks.getInstance().darkstone.getBlock().getDefaultState();
		fillerBlock = ACBlocks.getInstance().darkstone.getBlock().getDefaultState();
		decorator.treesPerChunk = 0;
		spawnableMonsterList.add(new SpawnListEntry(EntityDepthsGhoul.class, 45, 1, 2));
		spawnableMonsterList.add(new SpawnListEntry(EntityAbyssalZombie.class, 45, 1, 2));
		spawnableMonsterList.add(new SpawnListEntry(EntityShadowCreature.class, 100, 3, 3));
		spawnableMonsterList.add(new SpawnListEntry(EntityShadowMonster.class, 80, 2, 2));
		spawnableMonsterList.add(new SpawnListEntry(EntityShadowBeast.class, 40, 1, 1));
	}
	@Override
	public void decorate(World par1World, Random par2Random, BlockPos pos)
	{
		super.decorate(par1World, par2Random, pos);
		int var5 = 3 + par2Random.nextInt(6);

		if(ACConfig.generateAbyssalniteOre)
			for (int rarity = 0; rarity < var5; ++rarity)
			{
				int veinSize = 1 + par2Random.nextInt(3);
				int x = par2Random.nextInt(16);
				int y = par2Random.nextInt(28) + 4;
				int z = par2Random.nextInt(16);

				new WorldGenMinable(ACBlocks.getInstance().abyssalnite_ore.getBlock().getDefaultState(), veinSize).generate(par1World, par2Random, pos.add(x, y, z));
			}
	}

	@Override
	@SideOnly(Side.CLIENT)

	public int getSkyColorByTemp(float par1)
	{
		return ACClientVars.getDarklandsMountainsSkyColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		return ACClientVars.getDarklandsMountainsGrassColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos)
	{
		return ACClientVars.getDarklandsMountainsFoliageColor();
	}
}
