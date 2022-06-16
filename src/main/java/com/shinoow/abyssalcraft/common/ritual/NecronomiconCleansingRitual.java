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
import com.shinoow.abyssalcraft.api.biome.IDarklandsBiome;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
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

public class NecronomiconCleansingRitual extends NecronomiconRitual {

	public NecronomiconCleansingRitual() {
		super("cleansing", 4, 0, 100000F, true,
				new ItemStack[]{new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()), new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()), new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()), new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()), new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())},
				new ItemStack[]{new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()), new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()), new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()), new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()), new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())},
				new ItemStack[]{new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()), new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()), new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()), new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()), new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())},
				new ItemStack[]{new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()), new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()), new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()), new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()), new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())},
				new ItemStack[]{new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()), new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()), new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()), new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()), new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())},
				new ItemStack[]{new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()), new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()), new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()), new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()), new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())},
				new ItemStack[]{new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()), new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()), new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()), new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()), new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())},
				new ItemStack[]{new ItemStack(ACBlocks.getInstance().jzahar_statue.getBlock()), new ItemStack(ACBlocks.getInstance().cthulhu_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().hastur_statue.getBlock()), new ItemStack(ACBlocks.getInstance().azathoth_statue.getBlock()), new ItemStack(ACBlocks.getInstance().nyarlathotep_statue.getBlock()), new ItemStack(ACBlocks.getInstance().yog_sothoth_statue.getBlock()),
						new ItemStack(ACBlocks.getInstance().shub_niggurath_statue.getBlock())});
	}

	@Override
	public boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player) {
		for(int x = pos.getX() - 24; x < pos.getX() + 25; x++)
			for(int z = pos.getZ() - 24; z < pos.getZ() + 25; z++){
				BlockPos pos1 = new BlockPos(x, 0, z);
				if(world.getBiome(pos1) instanceof IDarklandsBiome)
					return true;
			}
		return false;
	}

	@Override
	protected void completeRitualClient(World world, BlockPos pos, EntityPlayer player) {
		SpecialTextUtil.JzaharText(I18n.format("message.jzahar.cleansing"));
	}

	@Override
	protected void completeRitualServer(World world, BlockPos pos, EntityPlayer player) {
		int num = 1, num2 = 0, range = ACConfig.cleansingRitualRange * 8;
		for(int x = pos.getX() - range; x < pos.getX() + range + 1; x++)
			for(int z = pos.getZ() - range; z < pos.getZ() + range + 1; z++){

				BlockPos pos1 = new BlockPos(x, 0, z);

				if(!(world.getBiome(pos1) instanceof IDarklandsBiome)) continue;

				Scheduler.schedule(new ScheduledProcess(num * 2) {

					@Override
					public void execute() {
						Biome b = getRealBiome(world.getBiome(pos1));

						for(int y = 0; y < 256; y++){
							if(world.isAirBlock(pos1.up(y))) continue;
							IBlockState state = world.getBlockState(pos1.up(y));
							if(state == ACBlocks.getInstance().darkstone.getBlock().getDefaultState())
								world.setBlockState(pos1.up(y), Blocks.STONE.getDefaultState(), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darklands_oak_leaves.getBlock())
								world.setBlockState(pos1.up(y), Blocks.LEAVES.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, state.getValue(BlockLeaves.CHECK_DECAY)).withProperty(BlockLeaves.DECAYABLE, state.getValue(BlockLeaves.DECAYABLE)), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darklands_oak_wood.getBlock() || state.getBlock() == ACBlocks.getInstance().darklands_oak_wood_2.getBlock())
								world.setBlockState(pos1.up(y), Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, state.getValue(BlockLog.LOG_AXIS)), 2);
							else if(state.getBlock() == ACBlocks.getInstance().abyssalnite_ore.getBlock())
								world.setBlockState(pos1.up(y), Blocks.IRON_ORE.getDefaultState(), 2);
							else if(state == ACBlocks.getInstance().darkstone_cobblestone.getBlock().getDefaultState())
								world.setBlockState(pos1.up(y), Blocks.COBBLESTONE.getDefaultState(), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darkstone_brick.getBlock())
								world.setBlockState(pos1.up(y), Blocks.STONEBRICK.getDefaultState(), 2);
							else if(state.getBlock() == ACBlocks.getInstance().chiseled_darkstone_brick.getBlock())
								world.setBlockState(pos1.up(y), Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED), 2);
							else if(state.getBlock() == ACBlocks.getInstance().cracked_darkstone_brick.getBlock())
								world.setBlockState(pos1.up(y), Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darkstone_cobblestone_wall.getBlock())
								world.setBlockState(pos1.up(y), Blocks.COBBLESTONE_WALL.getDefaultState(), 2);
							else if(state == ACBlocks.getInstance().ritual_altar_darkstone.getBlock().getDefaultState())
								world.setBlockState(pos1.up(y), ACBlocks.getInstance().ritual_altar_stone.getBlock().getDefaultState(), 2);
							else if(state == ACBlocks.getInstance().ritual_pedestal_darkstone.getBlock().getDefaultState())
								world.setBlockState(pos1.up(y), ACBlocks.getInstance().ritual_pedestal_stone.getBlock().getDefaultState(), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darkstone_brick_slab.getBlock())
								world.setBlockState(pos1.up(y), Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, state.getValue(BlockSlab.HALF)).withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SMOOTHBRICK), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darkstone_cobblestone_slab.getBlock())
								world.setBlockState(pos1.up(y), Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, state.getValue(BlockSlab.HALF)).withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.COBBLESTONE), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darkstone_slab.getBlock())
								world.setBlockState(pos1.up(y), Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, state.getValue(BlockSlab.HALF)), 2);
							else if(state.getBlock() == ACBlocks.getInstance().Darkstoneslab2.getBlock())
								world.setBlockState(pos1.up(y), Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2);
							else if(state.getBlock() == ACBlocks.getInstance().glowing_darkstone_bricks.getBlock())
								world.setBlockState(pos1.up(y), Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darkstone_brick_stairs.getBlock())
								world.setBlockState(pos1.up(y), Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING)).withProperty(BlockStairs.HALF, state.getValue(BlockStairs.HALF)), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darkstone_cobblestone_stairs.getBlock())
								world.setBlockState(pos1.up(y), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING)).withProperty(BlockStairs.HALF, state.getValue(BlockStairs.HALF)), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darklands_oak_planks.getBlock())
								world.setBlockState(pos1.up(y), Blocks.PLANKS.getDefaultState(), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darklands_oak_stairs.getBlock())
								world.setBlockState(pos1.up(y), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING)).withProperty(BlockStairs.HALF, state.getValue(BlockStairs.HALF)), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darklands_oak_slab.getBlock())
								world.setBlockState(pos1.up(y), Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockSlab.HALF, state.getValue(BlockSlab.HALF)), 2);
							else if(state.getBlock() == ACBlocks.getInstance().darklands_oak_fence.getBlock())
								world.setBlockState(pos1.up(y), Blocks.OAK_FENCE.getDefaultState(), 2);
						}

						BiomeUtil.updateBiome(world, pos1, b, true);
					}
				});
				num2++;
				if(num2 % 256 == 0)
					num++;
			}
	}

	private Biome getRealBiome(Biome b){
		if(b == ACBiomes.darklands || b == ACBiomes.darklands_plains)
			return Biomes.PLAINS;
		else if(b == ACBiomes.darklands_forest)
			return Biomes.FOREST;
		else if(b == ACBiomes.darklands_hills)
			return Biomes.EXTREME_HILLS;
		else if(b == ACBiomes.darklands_mountains)
			return Biomes.ICE_MOUNTAINS;
		return Biomes.PLAINS;
	}
}
