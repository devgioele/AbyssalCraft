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
package com.shinoow.abyssalcraft.common.structures;

import java.util.*;
import java.util.Map.Entry;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.common.blocks.BlockDecorativeStatue;
import com.shinoow.abyssalcraft.common.blocks.BlockStatue;
import com.shinoow.abyssalcraft.lib.ACConfig;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class StructureShoggothPit extends WorldGenerator {

	public StructureShoggothPit() {}

	private Map<Integer, Set<BlockPos>> positions = new HashMap<>();

	private boolean tooClose(int dim, BlockPos pos) {
		positions.putIfAbsent(dim, new HashSet<BlockPos>());
		return positions.get(dim).stream().anyMatch(b -> b.getDistance(pos.getX(), b.getY(), pos.getZ()) <= ACConfig.shoggothLairGenerationDistance);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {

		while(world.isAirBlock(pos) && pos.getY() > 2)
			pos = pos.down();
		if(pos.getY() <= 1) return false;

		if(world.getBlockState(pos).getMaterial() == Material.LEAVES ||
				world.getBlockState(pos).getMaterial() == Material.WOOD ||
				world.getBlockState(pos).getMaterial() == Material.VINE ||
				world.getBlockState(pos).getMaterial() == Material.CACTUS)
			return false;
		if(tooClose(world.provider.getDimension(), pos))
			return false;
		else {

			PlacementSettings placeSettings = new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID);

			if(world.getBlockState(pos).getMaterial().isLiquid())
				while(!world.isAirBlock(pos.up()))
					pos = pos.up();

			int num = rand.nextInt(3);
			num++;
			switch(num) {
			case 1:
				pos = pos.add(-6, -9, -27);
				break;
			case 2:
				pos = pos.add(-6, -9, -21);
				break;
			case 3:
				pos = pos.add(-6, -9, -19);
				break;
			}

			MinecraftServer server = world.getMinecraftServer();
			TemplateManager templateManager = world.getSaveHandler().getStructureTemplateManager();

			Template template = templateManager.getTemplate(server, new ResourceLocation(
					AbyssalCraft.modid, "shoggothlair/shoggothlair_"+num));

			template.addBlocksToWorld(world, pos, placeSettings);

			positions.get(world.provider.getDimension()).add(pos);

			Map<BlockPos, String> map = template.getDataBlocks(pos, placeSettings);

			IBlockState monolith_stone = ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState();

			switch(num) {
			case 1:
				for (Entry<BlockPos, String> entry : map.entrySet())
					if("statue1".equals(entry.getValue()) || "statue2".equals(entry.getValue()) || "statue3".equals(entry.getValue()) || "statue4".equals(entry.getValue())) {
						if(rand.nextInt(10) < 6)
							world.setBlockState(entry.getKey(), monolith_stone);
						else
							world.setBlockState(entry.getKey(), getRandomStatue(rand, EnumFacing.EAST));
					} else if("statue5".equals(entry.getValue()) || "statue6".equals(entry.getValue()) || "statue7".equals(entry.getValue()) || "statue8".equals(entry.getValue()))
						if(rand.nextInt(10) < 6)
							world.setBlockState(entry.getKey(), monolith_stone);
						else
							world.setBlockState(entry.getKey(), getRandomStatue(rand, EnumFacing.WEST));
				break;
			case 2:
				for (Entry<BlockPos, String> entry : map.entrySet())
					if("statue1".equals(entry.getValue())) {
						if(rand.nextInt(10) < 6)
							world.setBlockState(entry.getKey(), monolith_stone);
						else
							world.setBlockState(entry.getKey(), getRandomStatue(rand, EnumFacing.EAST));
					} else if("statue2".equals(entry.getValue())) {
						if(rand.nextInt(10) < 6)
							world.setBlockState(entry.getKey(), monolith_stone);
						else
							world.setBlockState(entry.getKey(), getRandomStatue(rand, EnumFacing.SOUTH));
					} else if("statue3".equals(entry.getValue()))
						if(rand.nextInt(10) < 6)
							world.setBlockState(entry.getKey(), monolith_stone);
						else
							world.setBlockState(entry.getKey(), getRandomStatue(rand, EnumFacing.WEST));
				break;
			case 3:
				for (Entry<BlockPos, String> entry : map.entrySet())
					if("statue1".equals(entry.getValue()))
						if(rand.nextInt(10) < 6)
							world.setBlockState(entry.getKey(), monolith_stone);
						else
							world.setBlockState(entry.getKey(), getRandomStatue(rand, EnumFacing.SOUTH));
				break;
			}

			return true;
		}
	}

	private IBlockState getRandomStatue(Random rand, EnumFacing facing){
		if(rand.nextInt(5) == 0 && ACConfig.generateStatuesInLairs)
			switch(rand.nextInt(7)){
			case 0:
				return ACBlocks.getInstance().cthulhu_statue.getBlock().getDefaultState().withProperty(BlockStatue.FACING, facing);
			case 1:
				return ACBlocks.getInstance().hastur_statue.getBlock().getDefaultState().withProperty(BlockStatue.FACING, facing);
			case 2:
				return ACBlocks.getInstance().jzahar_statue.getBlock().getDefaultState().withProperty(BlockStatue.FACING, facing);
			case 3:
				return ACBlocks.getInstance().azathoth_statue.getBlock().getDefaultState().withProperty(BlockStatue.FACING, facing);
			case 4:
				return ACBlocks.getInstance().nyarlathotep_statue.getBlock().getDefaultState().withProperty(BlockStatue.FACING, facing);
			case 5:
				return ACBlocks.getInstance().yog_sothoth_statue.getBlock().getDefaultState().withProperty(BlockStatue.FACING, facing);
			case 6:
				return ACBlocks.getInstance().shub_niggurath_statue.getBlock().getDefaultState().withProperty(BlockStatue.FACING, facing);
			default:
				return getRandomStatue(rand, facing);
			}
		switch(rand.nextInt(7)){
		case 0:
			return ACBlocks.getInstance().decorative_cthulhu_statue.getBlock().getDefaultState().withProperty(BlockDecorativeStatue.FACING, facing);
		case 1:
			return ACBlocks.getInstance().decorative_hastur_statue.getBlock().getDefaultState().withProperty(BlockDecorativeStatue.FACING, facing);
		case 2:
			return ACBlocks.getInstance().decorative_jzahar_statue.getBlock().getDefaultState().withProperty(BlockDecorativeStatue.FACING, facing);
		case 3:
			return ACBlocks.getInstance().decorative_azathoth_statue.getBlock().getDefaultState().withProperty(BlockDecorativeStatue.FACING, facing);
		case 4:
			return ACBlocks.getInstance().decorative_nyarlathotep_statue.getBlock().getDefaultState().withProperty(BlockDecorativeStatue.FACING, facing);
		case 5:
			return ACBlocks.getInstance().decorative_yog_sothoth_statue.getBlock().getDefaultState().withProperty(BlockDecorativeStatue.FACING, facing);
		case 6:
			return ACBlocks.getInstance().decorative_shub_niggurath_statue.getBlock().getDefaultState().withProperty(BlockDecorativeStatue.FACING, facing);
		default:
			return getRandomStatue(rand, facing);
		}
	}
}
