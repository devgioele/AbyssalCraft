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
package com.shinoow.abyssalcraft.common.items;

import com.shinoow.abyssalcraft.api.block.ACBlock;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemEthaxiumPickaxe extends ItemACPickaxe {

	private final Set<Block> effectiveBlocks;

	public ItemEthaxiumPickaxe(ToolMaterial mat, String name) {
		super(mat, name, 8, TextFormatting.AQUA);
		ACBlocks blocks = ACBlocks.getInstance();
		ACBlock[] effectiveBlocks = {
				blocks.ethaxium_brick,
				blocks.ethaxium_pillar,
				blocks.ethaxium_brick_fence,
				blocks.ethaxium_brick_slab,
				blocks.block_of_ethaxium,
				blocks.ethaxium,
				blocks.ethaxiumslab2,
				blocks.ethaxium_brick_stairs,
				blocks.materializer,
				blocks.dark_ethaxium_brick,
				blocks.dark_ethaxium_pillar,
				blocks.dark_ethaxium_brick_stairs,
				blocks.dark_ethaxium_brick_slab,
				blocks.darkethaxiumslab2,
				blocks.dark_ethaxium_brick_fence,
				blocks.cracked_ethaxium_brick,
				blocks.chiseled_ethaxium_brick,
				blocks.cracked_dark_ethaxium_brick,
				blocks.chiseled_dark_ethaxium_brick
		};
		this.effectiveBlocks =
				Arrays.stream(effectiveBlocks).map(ACBlock::getBlock).collect(Collectors.toSet());
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if (effectiveBlocks.contains(state)) return efficiency * 10;
		if (state.getBlock().isToolEffective("pickaxe", state)) return efficiency;
		return super.getDestroySpeed(stack, state);
	}

}
