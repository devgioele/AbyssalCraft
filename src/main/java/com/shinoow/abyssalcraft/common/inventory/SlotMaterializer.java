/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2018 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.inventory;

import com.shinoow.abyssalcraft.api.event.ACEvents;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class SlotMaterializer extends Slot
{
	/** The player that is using the GUI where this slot resides. */
	private EntityPlayer thePlayer;
	private int stackSize;

	public SlotMaterializer(EntityPlayer par1EntityPlayer, IInventory par2IInventory, int par3, int par4, int par5)
	{
		super(par2IInventory, par3, par4, par5);
		thePlayer = par1EntityPlayer;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return false;
	}

	@Override
	public ItemStack decrStackSize(int par1)
	{
		getStack().onCrafting(thePlayer.world, thePlayer, stackSize);

		MinecraftForge.EVENT_BUS.post(new ACEvents.ItemMaterializedEvent(thePlayer, getStack()));
		if (getHasStack())
			stackSize += Math.min(par1, getStack().stackSize);

		return super.decrStackSize(par1);
	}

	@Override
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
	{
		this.onCrafting(par2ItemStack);
		super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
	}

	@Override
	protected void onCrafting(ItemStack par1ItemStack, int par2)
	{
		stackSize += par2;
	}

	@Override
	protected void onCrafting(ItemStack par1ItemStack)
	{
		//		if(inventory instanceof TileEntityMaterializer){
		//			par1ItemStack.onCrafting(thePlayer.worldObj, thePlayer, stackSize);
		//
		//			MinecraftForge.EVENT_BUS.post(new ACEvents.ItemMaterializedEvent(thePlayer, par1ItemStack));
		//
		//			MaterializerRecipes.instance().processMaterialization(par1ItemStack, inventory.getStackInSlot(0));
		//		}
	}
}
