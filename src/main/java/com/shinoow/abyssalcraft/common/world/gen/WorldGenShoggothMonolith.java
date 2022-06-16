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
package com.shinoow.abyssalcraft.common.world.gen;

import java.util.Random;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.common.blocks.BlockStatue;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenShoggothMonolith extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {

		while(world.isAirBlock(pos) && pos.getY() > 2)
			pos = pos.down();

		if(world.getBlockState(pos).getBlock() != ACBlocks.getInstance().shoggoth_ooze.getBlock())
			return false;
		else {

			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			int max = rand.nextInt(8) + 5;
			for(int i = 0; i < max; i++){
				setBlockAndNotifyAdequately(world, new BlockPos(x, y + i, z), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + i, z), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x -1, y + i, z), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x, y + i, z + 1), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x, y + i, z -1), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + i, z + 1), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x -1, y + i, z -1), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + i, z -1), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x -1, y + i, z + 1), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
			}
			setBlockAndNotifyAdequately(world, pos, ACBlocks.getInstance().shoggoth_biomass.getBlock().getDefaultState());
			setBlockAndNotifyAdequately(world, new BlockPos(x, y + max, z), getStatue(rand).withProperty(BlockStatue.FACING, EnumFacing.byIndex(rand.nextInt(3))));

			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 2, world.rand.nextFloat() * 0.1F * 0.9F);

			return true;
		}
	}

	private IBlockState getStatue(Random rand){
		switch(rand.nextInt(7)){
		case 0:
			return ACBlocks.getInstance().cthulhu_statue.getBlock().getDefaultState();
		case 1:
			return ACBlocks.getInstance().hastur_statue.getBlock().getDefaultState();
		case 2:
			return ACBlocks.getInstance().jzahar_statue.getBlock().getDefaultState();
		case 3:
			return ACBlocks.getInstance().azathoth_statue.getBlock().getDefaultState();
		case 4:
			return ACBlocks.getInstance().nyarlathotep_statue.getBlock().getDefaultState();
		case 5:
			return ACBlocks.getInstance().yog_sothoth_statue.getBlock().getDefaultState();
		case 6:
			return ACBlocks.getInstance().shub_niggurath_statue.getBlock().getDefaultState();
		default:
			return getStatue(rand);
		}
	}
}
