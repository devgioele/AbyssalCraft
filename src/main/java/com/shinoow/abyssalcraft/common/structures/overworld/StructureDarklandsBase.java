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
package com.shinoow.abyssalcraft.common.structures.overworld;

import java.util.Random;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.common.blocks.BlockStatue;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class StructureDarklandsBase extends WorldGenerator {

	protected void placeStatue(World world, Random rand, BlockPos pos){
		if(rand.nextFloat() < 0.3F){
			setBlockAndNotifyAdequately(world, pos, ACBlocks.getInstance().monolith_pillar.getBlock().getDefaultState());
			setBlockAndNotifyAdequately(world, pos.up(), getStatue(rand).withProperty(BlockStatue.FACING, EnumFacing.byHorizontalIndex(rand.nextInt(3))));
		}
	}

	protected IBlockState getBrick(Random rand){
		IBlockState brick = ACBlocks.getInstance().darkstone_brick.getBlock().getDefaultState();
		IBlockState cracked_brick = ACBlocks.getInstance().cracked_darkstone_brick.getBlock().getDefaultState();
		return rand.nextFloat() < 0.2 ? cracked_brick : brick;
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
