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
package com.shinoow.abyssalcraft.common.ritual;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.ritual.EnumRitualParticle;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.common.util.BiomeUtil;
import com.shinoow.abyssalcraft.lib.ACConfig;
import com.shinoow.abyssalcraft.lib.util.ScheduledProcess;
import com.shinoow.abyssalcraft.lib.util.Scheduler;
import com.shinoow.abyssalcraft.lib.util.SpecialTextUtil;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.biome.BiomePlains;

public class NecronomiconCorruptionRitual extends NecronomiconRitual {

	public NecronomiconCorruptionRitual() {
		super("corruption", 4, 0, 10000F, true, new Object[]{
				new ItemStack[]{
						new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())
				}, ACBlocks.getInstance().darkstone.getBlock(), new ItemStack[]{
				new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())
		}, ACBlocks.getInstance().darkstone.getBlock(), new ItemStack[]{
				new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())
		}, ACBlocks.getInstance().darkstone.getBlock(), new ItemStack[]{
				new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
				new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())
		}, ACBlocks.getInstance().darkstone.getBlock()
		});
		setRitualParticle(EnumRitualParticle.PE_STREAM);
	}

	@Override
	public boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player) {
		for (int x = pos.getX() - 24; x < pos.getX() + 25; x++)
			for (int z = pos.getZ() - 24; z < pos.getZ() + 25; z++) {
				BlockPos pos1 = new BlockPos(x, 0, z);
				Biome b = world.getBiome(pos1);
				if (b instanceof BiomePlains || b instanceof BiomeHills ||
						b instanceof BiomeForest || b == Biomes.ICE_MOUNTAINS) return true;
			}
		return false;
	}

	@Override
	protected void completeRitualClient(World world, BlockPos pos, EntityPlayer player) {
		SpecialTextUtil.JzaharText(I18n.format("message.jzahar.corrupting"));
	}

	@Override
	protected void completeRitualServer(World world, BlockPos pos, EntityPlayer player) {
		int num = 1, num2 = 0, range = ACConfig.corruptionRitualRange * 8;
		boolean b1 = world.rand.nextBoolean();
		for (int x = pos.getX() - range; x < pos.getX() + range + 1; x++)
			for (int z = pos.getZ() - range; z < pos.getZ() + range + 1; z++) {

				BlockPos pos1 = new BlockPos(x, 0, z);

				if (!isApplicable(world, pos1)) continue;

				Scheduler.schedule(new ScheduledProcess(num * 2) {

					@Override
					public void execute() {
						Biome b = getDarklandsBiome(world.getBiome(pos1), b1);

						for (int y = 0; y < 256; y++) {
							if (world.isAirBlock(pos1.up(y))) continue;
							IBlockState state = world.getBlockState(pos1.up(y));
							if (state.getBlock() == Blocks.STONE &&
									state.getValue(BlockStone.VARIANT) != BlockStone.EnumType.STONE)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darkstone.getBlock()
												.getDefaultState(), 2);
							else if (state.getBlock() == Blocks.LEAVES)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darklands_oak_leaves.getBlock()
												.getDefaultState()
												.withProperty(BlockLeaves.CHECK_DECAY,
														state.getValue(BlockLeaves.CHECK_DECAY))
												.withProperty(BlockLeaves.DECAYABLE,
														state.getValue(BlockLeaves.DECAYABLE)), 2);
							else if (state.getBlock() == Blocks.LOG) world.setBlockState(pos1.up(y),
									(world.rand.nextInt(10) == 0 ?
											ACBlocks.getInstance().darklands_oak_wood_2.getBlock() :
											ACBlocks.getInstance().darklands_oak_wood.getBlock()).getDefaultState()
											.withProperty(BlockLog.LOG_AXIS,
													state.getValue(BlockLog.LOG_AXIS)), 2);
							else if (state.getBlock() == Blocks.COBBLESTONE)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darkstone_cobblestone.getBlock()
												.getDefaultState(), 2);
							else if (state.getBlock() == Blocks.STONEBRICK)
								switch (state.getValue(BlockStoneBrick.VARIANT)) {
									case CHISELED:
										world.setBlockState(pos1.up(y),
												ACBlocks.getInstance().chiseled_darkstone_brick.getBlock()
														.getDefaultState(), 2);
										break;
									case CRACKED:
										world.setBlockState(pos1.up(y),
												ACBlocks.getInstance().cracked_darkstone_brick.getBlock()
														.getDefaultState(), 2);
										break;
									default:
										world.setBlockState(pos1.up(y),
												ACBlocks.getInstance().darkstone_brick.getBlock()
														.getDefaultState(), 2);
										break;
								}
							else if (state.getBlock() == Blocks.COBBLESTONE_WALL)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darkstone_cobblestone_wall.getBlock()
												.getDefaultState(), 2);
							else if (state == ACBlocks.getInstance().ritual_altar_stone.getBlock()
									.getDefaultState()) world.setBlockState(pos1.up(y),
									ACBlocks.getInstance().ritual_altar_darkstone.getBlock()
											.getDefaultState(), 2);
							else if (state ==
									ACBlocks.getInstance().ritual_pedestal_stone.getBlock()
											.getDefaultState()) world.setBlockState(pos1.up(y),
									ACBlocks.getInstance().ritual_pedestal_darkstone.getBlock()
											.getDefaultState(), 2);
							else if (state.getBlock() == Blocks.STONE_SLAB &&
									state.getValue(BlockStoneSlab.VARIANT) ==
											BlockStoneSlab.EnumType.SMOOTHBRICK)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darkstone_brick_slab.getBlock()
												.getDefaultState().withProperty(BlockSlab.HALF,
														state.getValue(BlockSlab.HALF)), 2);
							else if (state.getBlock() == Blocks.STONE_SLAB &&
									state.getValue(BlockStoneSlab.VARIANT) ==
											BlockStoneSlab.EnumType.COBBLESTONE)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darkstone_cobblestone_slab.getBlock()
												.getDefaultState().withProperty(BlockSlab.HALF,
														state.getValue(BlockSlab.HALF)), 2);
							else if (state.getBlock() == Blocks.STONE_SLAB &&
									state.getValue(BlockStoneSlab.VARIANT) ==
											BlockStoneSlab.EnumType.STONE)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darkstone_slab.getBlock()
												.getDefaultState().withProperty(BlockSlab.HALF,
														state.getValue(BlockSlab.HALF)), 2);
							else if (state.getBlock() == Blocks.DOUBLE_STONE_SLAB &&
									state.getValue(BlockStoneSlab.VARIANT) ==
											BlockStoneSlab.EnumType.STONE)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().Darkstoneslab2.getBlock()
												.getDefaultState(), 2);
							else if (state.getBlock() == Blocks.STONE_BRICK_STAIRS)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darkstone_brick_stairs.getBlock()
												.getDefaultState().withProperty(BlockStairs.FACING,
														state.getValue(BlockStairs.FACING))
												.withProperty(BlockStairs.HALF,
														state.getValue(BlockStairs.HALF)), 2);
							else if (state.getBlock() == Blocks.STONE_STAIRS)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darkstone_cobblestone_stairs.getBlock()
												.getDefaultState().withProperty(BlockStairs.FACING,
														state.getValue(BlockStairs.FACING))
												.withProperty(BlockStairs.HALF,
														state.getValue(BlockStairs.HALF)), 2);
							else if (state.getBlock() == Blocks.PLANKS)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darklands_oak_planks.getBlock()
												.getDefaultState(), 2);
							else if (state.getBlock() == Blocks.OAK_STAIRS)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darklands_oak_stairs.getBlock()
												.getDefaultState().withProperty(BlockStairs.FACING,
														state.getValue(BlockStairs.FACING))
												.withProperty(BlockStairs.HALF,
														state.getValue(BlockStairs.HALF)), 2);
							else if (state.getBlock() == Blocks.WOODEN_SLAB)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darklands_oak_slab.getBlock()
												.getDefaultState().withProperty(BlockSlab.HALF,
														state.getValue(BlockSlab.HALF)), 2);
							else if (state.getBlock() == Blocks.OAK_FENCE)
								world.setBlockState(pos1.up(y),
										ACBlocks.getInstance().darklands_oak_fence.getBlock()
												.getDefaultState(), 2);
						}

						BiomeUtil.updateBiome(world, pos1, b, true);
					}

				});
				num2++;
				if (num2 % 256 == 0) num++;
			}
	}

	private boolean isApplicable(World world, BlockPos pos) {
		Biome b = world.getBiome(pos);
		return b instanceof BiomePlains || b instanceof BiomeHills || b instanceof BiomeForest ||
				b == Biomes.ICE_MOUNTAINS;
	}

	private Biome getDarklandsBiome(Biome b, boolean b1) {
		if (b instanceof BiomePlains) return b1 ? ACBiomes.darklands_plains : ACBiomes.darklands;
		else if (b instanceof BiomeForest) return ACBiomes.darklands_forest;
		else if (b == Biomes.ICE_MOUNTAINS || b instanceof BiomeHills && b1)
			return ACBiomes.darklands_mountains;
		else if (b instanceof BiomeHills) return ACBiomes.darklands_hills;
		return ACBiomes.darklands;
	}

}
