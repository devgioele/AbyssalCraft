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
package com.shinoow.abyssalcraft.common.blocks;

import java.util.Random;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.lib.ACTabs;

import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockDreadGrass extends BlockGrass {

	public BlockDreadGrass() {
		setCreativeTab(ACTabs.tabBlock);
		setSoundType(SoundType.PLANT);
		setHarvestLevel("shovel", 0);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return MapColor.RED;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote) {
			if (!worldIn.isAreaLoaded(pos, 3)) return;
			if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getBlock().getLightOpacity(worldIn.getBlockState(pos.up()), worldIn, pos.up()) > 2)
				worldIn.setBlockState(pos, ACBlocks.getInstance().dreadlands_dirt.getBlock().getDefaultState());
			else
				for (int i = 0; i < 4; ++i)
				{
					BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
					Block block = worldIn.getBlockState(blockpos.up()).getBlock();
					IBlockState iblockstate = worldIn.getBlockState(blockpos);

					if (iblockstate.getBlock() == ACBlocks.getInstance().dreadlands_dirt.getBlock() && block.getLightOpacity(worldIn.getBlockState(blockpos.up()), worldIn, blockpos.up()) <= 2)
						worldIn.setBlockState(blockpos, ACBlocks.getInstance().dreadlands_grass.getBlock().getDefaultState());
				}
		}
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
	{
		boolean hasWater = world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
				world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
				world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
				world.getBlockState(pos.south()).getMaterial() == Material.WATER;
		return plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Plains ||
				plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Beach && hasWater;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random par2Random, int par3)
	{
		return ACBlocks.getInstance().dreadlands_dirt.getBlock().getItemDropped(state, par2Random, par3);
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		BlockPos blockpos = pos.up();

		for (int i = 0; i < 128; ++i)
		{
			BlockPos blockpos1 = blockpos;
			int j = 0;

			while (true)
			{
				if (j >= i / 16)
				{
					if (worldIn.isAirBlock(blockpos1))
						if (rand.nextInt(8) == 0)
							worldIn.getBiome(blockpos1).plantFlower(worldIn, rand, blockpos1);
						else
						{
							IBlockState iblockstate1 = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);

							if (Blocks.TALLGRASS.canBlockStay(worldIn, blockpos1, iblockstate1))
								worldIn.setBlockState(blockpos1, iblockstate1, 3);
						}

					break;
				}

				blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

				if (worldIn.getBlockState(blockpos1.down()).getBlock() != ACBlocks.getInstance().dreadlands_grass.getBlock() || worldIn.getBlockState(blockpos1).isNormalCube())
					break;

				++j;
			}
		}
	}
}
