/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2017 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional.Interface;
import net.minecraftforge.fml.common.Optional.Method;
import thaumcraft.api.crafting.IInfusionStabiliser;

import com.shinoow.abyssalcraft.lib.ACTabs;
import com.shinoow.abyssalcraft.lib.tileentity.TEDirectional;

@Interface(modid = "Thaumcraft", iface = "thaumcraft.api.crafting.IInfusionStabiliser", striprefs = true)
public abstract class BlockDGhead extends BlockContainer implements IInfusionStabiliser {

	public BlockDGhead() {
		super(Material.cloth);
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.8F, 0.7F, 0.8F);
		setHardness(1.0F);
		setResistance(6.0F);
		setStepSound(Block.soundTypeCloth);
		setCreativeTab(ACTabs.tabDecoration);
	}

	@Override
	public int getRenderType() {
		return 2;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World par1World, BlockPos pos, IBlockState state)
	{
		setBlockBoundsBasedOnState(par1World, pos);
		return super.getCollisionBoundingBox(par1World, pos, state);
	}

	@Override
	public void onBlockPlacedBy(World par1World, BlockPos pos, IBlockState state, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		if (par5EntityLivingBase == null)
			return;

		TEDirectional tile = (TEDirectional) par1World.getTileEntity(pos);
		tile.direction = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
	}

	@Override
	@Method(modid = "Thaumcraft")
	public boolean canStabaliseInfusion(World world, BlockPos pos) {

		return true;
	}
}
