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
package com.shinoow.abyssalcraft.common.inventory;

import com.shinoow.abyssalcraft.api.event.ACEvents;
import com.shinoow.abyssalcraft.api.recipe.TransmutatorRecipes;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;

public class SlotTransmutator extends Slot
{
	/** The player that is using the GUI where this slot resides. */
	private EntityPlayer thePlayer;
	private int stackSize;

	public SlotTransmutator(EntityPlayer par1EntityPlayer, IInventory par2IInventory, int par3, int par4, int par5)
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
		if (getHasStack())
			stackSize += Math.min(par1, getStack().getCount());

		return super.decrStackSize(par1);
	}

	@Override
	public ItemStack onTake(EntityPlayer player, ItemStack stack)
	{
		this.onCrafting(stack);
		super.onTake(player, stack);
		return stack;
	}

	@Override
	protected void onCrafting(ItemStack par1ItemStack, int par2)
	{
		stackSize += par2;
		this.onCrafting(par1ItemStack);
	}

	@Override
	protected void onCrafting(ItemStack par1ItemStack)
	{
		par1ItemStack.onCrafting(thePlayer.world, thePlayer, stackSize);

		if (!thePlayer.world.isRemote)
		{
			int i = stackSize;
			float f = TransmutatorRecipes.instance().getExperience(par1ItemStack);
			int j;

			if (f == 0.0F)
				i = 0;
			else if (f < 1.0F)
			{
				j = MathHelper.floor(i * f);

				if (j < MathHelper.ceil(i * f) && (float)Math.random() < i * f - j)
					++j;

				i = j;
			}

			while (i > 0)
			{
				j = EntityXPOrb.getXPSplit(i);
				i -= j;
				thePlayer.world.spawnEntity(new EntityXPOrb(thePlayer.world, thePlayer.posX, thePlayer.posY + 0.5D, thePlayer.posZ + 0.5D, j));
			}
		}

		stackSize = 0;

		MinecraftForge.EVENT_BUS.post(new ACEvents.ItemTransmutedEvent(thePlayer, par1ItemStack));

		//		if (par1ItemStack.getItem() == ACItems.getInstance().rlyehian_gateway_key)
		//			thePlayer.addStat(ACAchievements.rlyehian_gateway_key, 1);
		//		if(par1ItemStack.getItem() == ACItems.getInstance().dreadium_ingot)
		//			thePlayer.addStat(ACAchievements.dreadium, 1);
	}
}
