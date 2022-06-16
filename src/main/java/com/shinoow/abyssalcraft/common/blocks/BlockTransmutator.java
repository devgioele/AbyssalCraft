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

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.common.blocks.tile.TileEntityTransmutator;
import com.shinoow.abyssalcraft.lib.ACConfig;
import com.shinoow.abyssalcraft.lib.ACLib;
import com.shinoow.abyssalcraft.lib.ACTabs;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTransmutator extends BlockContainer {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	private final boolean isLit;
	private static boolean keepInventory;

	public BlockTransmutator(boolean par1) {
		super(Material.ROCK);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setSoundType(SoundType.STONE);
		isLit = par1;
		if(!isLit)
			setCreativeTab(ACTabs.tabDecoration);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random par1Random, int par3)
	{
		return Item.getItemFromBlock(ACBlocks.getInstance().transmutator_idle.getBlock());
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		setDefaultFacing(worldIn, pos, state);
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			IBlockState block = worldIn.getBlockState(pos.north());
			IBlockState block1 = worldIn.getBlockState(pos.south());
			IBlockState block2 = worldIn.getBlockState(pos.west());
			IBlockState block3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
				enumfacing = EnumFacing.SOUTH;
			else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
				enumfacing = EnumFacing.NORTH;
			else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
				enumfacing = EnumFacing.EAST;
			else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
				enumfacing = EnumFacing.WEST;

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	@Override
	public boolean onBlockActivated(World par1World, BlockPos pos, IBlockState state, EntityPlayer par5EntityPlayer, EnumHand hand, EnumFacing side, float par7, float par8, float par9) {
		if(!par1World.isRemote)
			FMLNetworkHandler.openGui(par5EntityPlayer, AbyssalCraft.instance, ACLib.transmutatorGuiID, par1World, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	public static void updateTransmutatorBlockState(boolean par0, World par1World, BlockPos pos) {
		IBlockState state = par1World.getBlockState(pos);
		TileEntity tileentity = par1World.getTileEntity(pos);
		keepInventory = true;

		if (par0)
			par1World.setBlockState(pos, ACBlocks.getInstance().transmutator_active.getBlock().getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		else
			par1World.setBlockState(pos, ACBlocks.getInstance().transmutator_idle.getBlock().getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);

		keepInventory = false;

		if (tileentity != null){
			tileentity.validate();
			par1World.setTileEntity(pos, tileentity);
		}
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World par1World, int par2)
	{
		return new TileEntityTransmutator();
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World par1World, BlockPos pos, IBlockState state, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {

		par1World.setBlockState(pos, state.withProperty(FACING, par5EntityLivingBase.getHorizontalFacing().getOpposite()), 2);

		if (par6ItemStack.hasDisplayName())
			((TileEntityTransmutator)par1World.getTileEntity(pos)).setCustomName(par6ItemStack.getDisplayName());
	}

	@Override
	public void breakBlock(World par1World, BlockPos pos, IBlockState state) {
		if (!keepInventory){
			TileEntity tileEntity = par1World.getTileEntity(pos);

			if (tileEntity instanceof TileEntityTransmutator){
				InventoryHelper.dropInventoryItems(par1World, pos, (TileEntityTransmutator)tileEntity);
				par1World.updateComparatorOutputLevel(pos, this);
			}
		}

		super.breakBlock(par1World, pos, state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (isLit && ACConfig.particleBlock){
			EnumFacing enumfacing = state.getValue(FACING);
			double d0 = pos.getX() + 0.5D;
			double d1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double d2 = pos.getZ() + 0.5D;
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;

			switch (enumfacing)
			{
			case WEST:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				AbyssalCraft.proxy.spawnParticle("CorBlood", d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				break;
			case EAST:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				AbyssalCraft.proxy.spawnParticle("CorBlood", d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				break;
			case NORTH:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
				AbyssalCraft.proxy.spawnParticle("CorBlood", d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D);
				break;
			case SOUTH:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
				AbyssalCraft.proxy.spawnParticle("CorBlood", d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D);
			default:
				break;
			}
		}
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World par1World, BlockPos pos)
	{
		return Container.calcRedstone(par1World.getTileEntity(pos));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getItem(World par1World, BlockPos pos, IBlockState state)
	{
		return new ItemStack(ACBlocks.getInstance().transmutator_idle.getBlock());
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing = EnumFacing.byIndex(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y)
			enumfacing = EnumFacing.NORTH;

		return getDefaultState().withProperty(FACING, enumfacing);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer.Builder(this).add(FACING).build();
	}
}
