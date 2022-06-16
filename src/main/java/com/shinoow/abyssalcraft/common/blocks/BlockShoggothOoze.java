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

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.common.entity.EntityShoggothBase;
import com.shinoow.abyssalcraft.lib.ACConfig;
import com.shinoow.abyssalcraft.lib.ACLib;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockShoggothOoze extends BlockACBasic {

	public static final PropertyInteger LAYERS = PropertyInteger.create("layers", 1, 8);
	protected static final AxisAlignedBB[] OOZE_AABB = new AxisAlignedBB[]{
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)
	};

	public BlockShoggothOoze() {
		super(Material.GROUND, 1.0F, 1.0F, SoundType.SLIME);
		setDefaultState(blockState.getBaseState().withProperty(LAYERS, 1));
		setTickRandomly(ACConfig.oozeExpire);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return OOZE_AABB[state.getValue(LAYERS)];
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getValue(LAYERS).intValue() < 5;
	}

	@Override
	public boolean isTopSolid(IBlockState state) {
		return state.getValue(LAYERS).intValue() == 8;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn,
												 BlockPos pos) {
		int i = blockState.getValue(LAYERS).intValue() - 1;
		AxisAlignedBB axisalignedbb = blockState.getBoundingBox(worldIn, pos);
		return new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ,
				axisalignedbb.maxX, i * 0.125F, axisalignedbb.maxZ);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (!(entity instanceof EntityShoggothBase)) {
			entity.motionX *= 0.4D;
			entity.motionZ *= 0.4D;
			if (entity instanceof EntityLivingBase && !world.isRemote) {
				EntityLivingBase e = (EntityLivingBase) entity;
				ItemStack legs = e.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
				ItemStack feet = e.getItemStackFromSlot(EntityEquipmentSlot.FEET);
				if (e.ticksExisted % 60 == 0 && !legs.isEmpty() &&
						legs.getItem() != ACItems.getInstance().ethaxium_leggings)
					legs.damageItem(1, e);
				if (e.ticksExisted % 40 == 0 && !feet.isEmpty() &&
						feet.getItem() != ACItems.getInstance().ethaxium_boots)
					feet.damageItem(1, e);
			}
		}
	}

	@Override
	public void updateTick(World par1World, BlockPos pos, IBlockState state, Random par5Random) {
		if (ACConfig.oozeExpire) if (!par1World.isRemote && par5Random.nextInt(10) == 0 &&
				par1World.getLightFromNeighbors(pos.up()) >= 13)
			if (state.getValue(LAYERS).intValue() == 8)
				par1World.setBlockState(pos, getState(par1World));
			else if (state.getValue(LAYERS).intValue() > 1) par1World.setBlockState(pos,
					state.withProperty(LAYERS, state.getValue(LAYERS).intValue() - 1));
			else par1World.setBlockToAir(pos);
	}

	private IBlockState getState(World world) {
		if (world.provider.getDimension() == ACLib.abyssal_wasteland_id)
			return ACBlocks.getInstance().abyssal_sand.getBlock().getDefaultState();
		if (world.provider.getDimension() == ACLib.dreadlands_id)
			return ACBlocks.getInstance().dreadlands_dirt.getBlock().getDefaultState();
		if (world.provider.getDimension() == ACLib.omothol_id)
			return ACBlocks.getInstance().omothol_stone.getBlock().getDefaultState();
		if (world.provider.getDimension() == ACLib.dark_realm_id)
			return ACBlocks.getInstance().darkstone.getBlock().getDefaultState();
		if (world.provider.getDimension() == -1) return Blocks.NETHERRACK.getDefaultState();
		if (world.provider.getDimension() == 1) return Blocks.END_STONE.getDefaultState();
		return Blocks.DIRT.getDefaultState();
	}

	@Override
	public int tickRate(World worldIn) {
		return ACConfig.oozeExpire ? 200 : super.tickRate(worldIn);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos.down());
		Block block = iblockstate.getBlock();
		return block != ACBlocks.getInstance().shoggoth_biomass.getBlock() &&
				iblockstate != ACBlocks.getInstance().monolith_stone.getBlock().getDefaultState() &&
				(iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.down()) ||
						(block == this && iblockstate.getValue(LAYERS) == 8 ||
								iblockstate.isOpaqueCube() &&
										iblockstate.getMaterial().blocksMovement()));
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn,
								BlockPos fromPos) {
		if (state.getValue(LAYERS) < 8) checkAndDropBlock(worldIn, pos, state);
	}

	private boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!canPlaceBlockAt(worldIn, pos)) {
			worldIn.setBlockToAir(pos);
			return false;
		} else return true;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state,
							 @Nullable TileEntity te, @Nullable ItemStack stack) {
		super.harvestBlock(worldIn, player, pos, state, te, stack);
		worldIn.setBlockToAir(pos);
	}

	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess,
										BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.UP) return true;
		else {
			IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
			return iblockstate.getBlock() == this && iblockstate.getValue(LAYERS).intValue() >=
					blockState.getValue(LAYERS).intValue() ? true :
					super.shouldSideBeRendered(blockState, blockAccess, pos, side);
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LAYERS, (meta & 7) + 1);
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getValue(LAYERS).intValue() == 1;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(LAYERS).intValue() - 1;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer.Builder(this).add(LAYERS).build();
	}

}
