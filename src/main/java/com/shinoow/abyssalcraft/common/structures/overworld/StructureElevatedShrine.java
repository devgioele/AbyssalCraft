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
import com.shinoow.abyssalcraft.common.blocks.BlockShoggothOoze;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StructureElevatedShrine extends StructureDarklandsBase {

	@Override
	@SuppressWarnings("deprecation")
	public boolean generate(World worldIn, Random rand, BlockPos pos) {

		IBlockState brick_slab = ACBlocks.getInstance().darkstone_brick_slab.getBlock().getDefaultState();
		IBlockState cobble = ACBlocks.getInstance().darkstone_cobblestone.getBlock().getDefaultState();
		IBlockState cobble_wall = ACBlocks.getInstance().darkstone_cobblestone_wall.getBlock().getDefaultState();


		for(int i = -2; i < 3; i++)
			for(int j = -4; j < 5; j++){

				boolean flag = i > -2 && i < 2;

				if(j > -3 && j < 3 && flag)
					for(int k = 1; k < 7; k++)
						if(k != 2){
							worldIn.setBlockToAir(pos.add(i, k, j));
							worldIn.setBlockToAir(pos.add(j, k, i));
						}

				if((j == -4 || j == 4) && flag){
					setBlockAndNotifyAdequately(worldIn, pos.add(j, 1, i), ACBlocks.getInstance().darkstone_cobblestone_stairs.getBlock().getStateFromMeta(j > 0 ? 1 : 0));
					setBlockAndNotifyAdequately(worldIn, pos.add(i, 1, j), ACBlocks.getInstance().darkstone_cobblestone_stairs.getBlock().getStateFromMeta(j > 0 ? 3 : 2));
				}
				if((j == -3 || j == 3) && flag){
					setBlockAndNotifyAdequately(worldIn, pos.add(j, 1, i), cobble);
					setBlockAndNotifyAdequately(worldIn, pos.add(i, 1, j), cobble);
					setBlockAndNotifyAdequately(worldIn, pos.add(j, 2, i), ACBlocks.getInstance().darkstone_cobblestone_stairs.getBlock().getStateFromMeta(j > 0 ? 1 : 0));
					setBlockAndNotifyAdequately(worldIn, pos.add(i, 2, j), ACBlocks.getInstance().darkstone_cobblestone_stairs.getBlock().getStateFromMeta(j > 0 ? 3 : 2));
				}
				if(j == -2 || j == 2)
					for(int k = 1; k < 7; k++){
						if(i == -2 || i == 2){
							setBlockAndNotifyAdequately(worldIn, pos.add(j, k, i), cobble_wall);
							setBlockAndNotifyAdequately(worldIn, pos.add(i, k, j), cobble_wall);
						}
						if(k == 2 && flag){
							setBlockAndNotifyAdequately(worldIn, pos.add(i, k, j), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
							setBlockAndNotifyAdequately(worldIn, pos.add(j, k, i), ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState());
						}
						if(k == 6){
							setBlockAndNotifyAdequately(worldIn, pos.add(i, k, j), brick_slab);
							setBlockAndNotifyAdequately(worldIn, pos.add(j, k, i), brick_slab);
						}
					}
				if(j > -2 && j < 2 && flag)
					setBlockAndNotifyAdequately(worldIn, pos.add(i, 2, j), ACBlocks.getInstance().shoggoth_ooze.getBlock().getDefaultState().withProperty(BlockShoggothOoze.LAYERS, 8));
			}

		placeStatue(worldIn, rand, pos.up(3));

		return true;
	}
}
