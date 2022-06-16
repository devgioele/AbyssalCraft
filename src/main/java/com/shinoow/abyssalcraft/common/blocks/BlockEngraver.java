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
import com.shinoow.abyssalcraft.common.blocks.tile.TileEntityEngraver;
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
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEngraver extends BlockContainer {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	private static boolean keepInventory;

	public BlockEngraver() {
		super(Material.ROCK);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setCreativeTab(ACTabs.tabDecoration);
		setSoundType(SoundType.STONE);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getIndex();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random par1Random, int par3)
	{
		return Item.getItemFromBlock(ACBlocks.getInstance().engraver.getBlock());
	}

	@Override
	public boolean onBlockActivated(World par1World, BlockPos pos, IBlockState state, EntityPlayer par5EntityPlayer, EnumHand hand, EnumFacing side, float par7, float par8, float par9) {
		if(!par1World.isRemote)
			FMLNetworkHandler.openGui(par5EntityPlayer, AbyssalCraft.instance, ACLib.engraverGuiID, par1World, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	public static void updateEngraverBlockState(World par1World, BlockPos pos) {

	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityEngraver();
	}

	@Override
	public void onBlockPlacedBy(World par1World, BlockPos pos, IBlockState state, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {

		if (par6ItemStack.hasDisplayName())
			((TileEntityEngraver) par1World.getTileEntity(pos)).setCustomName(par6ItemStack.getDisplayName());
	}

	@Override
	public void breakBlock(World par1World, BlockPos pos, IBlockState state) {
		if (!keepInventory){
			TileEntity tileEntity = par1World.getTileEntity(pos);

			if (tileEntity instanceof TileEntityEngraver){
				InventoryHelper.dropInventoryItems(par1World, pos, (TileEntityEngraver)tileEntity);
				par1World.updateComparatorOutputLevel(pos, this);
			}
		}

		super.breakBlock(par1World, pos, state);
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World par1World, BlockPos pos)
	{
		return Container.calcRedstoneFromInventory((IInventory)par1World.getTileEntity(pos));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getItem(World par1World, BlockPos pos, IBlockState state)
	{
		return new ItemStack(ACBlocks.getInstance().engraver.getBlock());
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockStateContainer createBlockState()
	{
		return new BlockStateContainer.Builder(this).add(FACING).add(OBJModel.OBJProperty.INSTANCE).build();
	}
}
