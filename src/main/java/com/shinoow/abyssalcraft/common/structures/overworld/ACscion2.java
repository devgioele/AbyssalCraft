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

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ACscion2 extends StructureDarklandsBase {

	@Override
	@SuppressWarnings("deprecation")
	public boolean generate(World world, Random random, BlockPos pos) {

		pos = pos.add(-3, 1, -3);

		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();

		world.setBlockState(new BlockPos(i + 0, j + 0, k + 0), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 6), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 0, j + 1, k + 0), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 0, j + 1, k + 6), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 0, j + 2, k + 0), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 0, j + 2, k + 6), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 0, j + 3, k + 0), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 0, j + 3, k + 6), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 2), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 4), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 3), ACBlocks.getInstance().darkstone_brick_stairs.getBlock().getStateFromMeta(0), 2);
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 3, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 3, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 3, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 1), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 2), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(13), 2);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 3), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 4), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(13), 2);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 5), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 2), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(13), 2);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 3), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 4), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(13), 2);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 3), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 3, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 3, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 3, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 3, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 3, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 1), ACBlocks.getInstance().darkstone_brick_stairs.getBlock().getStateFromMeta(2), 2);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 2), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 3), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 4), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 5), ACBlocks.getInstance().darkstone_brick_stairs.getBlock().getStateFromMeta(3), 2);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 2), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 3), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 4), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 2), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 3), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 4), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 3, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 3, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 3, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 3, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 3, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 1), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 2), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(13), 2);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 3), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 4), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(13), 2);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 5), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 2), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(13), 2);
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 3), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 4), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(13), 2);
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 3), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 3, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 3, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 3, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 3, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 3, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 4, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 4, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 4, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 4, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 4, j + 4, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 2), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 4), ACBlocks.getInstance().darkstone_brick_slab.getBlock().getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 3), ACBlocks.getInstance().darkstone_brick_stairs.getBlock().getStateFromMeta(1), 2);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 2, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 2, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 2, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 2, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 2, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 3, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 3, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 3, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 3, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 3, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 4, k + 1), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 4, k + 2), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 4, k + 3), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 4, k + 4), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 5, j + 4, k + 5), Blocks.AIR.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 0), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 6), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 6, j + 1, k + 0), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 6, j + 1, k + 6), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 6, j + 2, k + 0), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 6, j + 2, k + 6), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 6, j + 3, k + 0), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 6, j + 3, k + 6), getBrick(random), 2);
		world.setBlockState(new BlockPos(i + 0, j + 4, k + 0), Blocks.REDSTONE_TORCH.getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 0, j + 4, k + 6), Blocks.REDSTONE_TORCH.getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 6, j + 4, k + 0), Blocks.REDSTONE_TORCH.getStateFromMeta(5), 2);
		world.setBlockState(new BlockPos(i + 6, j + 4, k + 6), Blocks.REDSTONE_TORCH.getStateFromMeta(5), 2);

		return true;
	}
}
