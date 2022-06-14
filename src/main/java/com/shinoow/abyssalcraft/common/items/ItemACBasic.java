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

import javax.annotation.Nullable;

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.api.item.IUnlockableItem;
import com.shinoow.abyssalcraft.api.necronomicon.condition.DefaultCondition;
import com.shinoow.abyssalcraft.api.necronomicon.condition.IUnlockCondition;
import com.shinoow.abyssalcraft.lib.ACTabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemACBasic extends Item implements IUnlockableItem {

	private IUnlockCondition condition = new DefaultCondition();

	public ItemACBasic(String name) {
		super();
		setTranslationKey(name);
		setCreativeTab(ACTabs.tabItems);
	}

	@Override
	public Item setUnlockCondition(IUnlockCondition condition){
		this.condition = condition;
		return this;
	}

	@Override
	public IUnlockCondition getUnlockCondition(ItemStack stack){
		return condition;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@Nullable
	public net.minecraft.client.gui.FontRenderer getFontRenderer(ItemStack stack)
	{
		return APIUtils.getFontRenderer(stack);
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		if(this.getTranslationKey().contains("dreadshard") || this.getTranslationKey().contains("dreadchunk") ||
				this.getTranslationKey().contains("dreadiumingot") || this.getTranslationKey().contains("dreadfragment"))
			return TextFormatting.DARK_RED + super.getItemStackDisplayName(itemStack);
		else if(this.getTranslationKey().contains("abyingot"))
			return TextFormatting.DARK_AQUA + super.getItemStackDisplayName(itemStack);
		else if(this.getTranslationKey().contains("cpearl") || this.getTranslationKey().contains("cingot")
				|| this.getTranslationKey().contains("ethaxiumingot"))
			return TextFormatting.AQUA + super.getItemStackDisplayName(itemStack);

		return super.getItemStackDisplayName(itemStack);
	}

	@Override
	public int getItemBurnTime(ItemStack itemStack)
	{
		return itemStack.getItem() == ACItems.methane ? 10000 : itemStack.getItem() == ACItems.charcoal ? 1600 : -1;
	}
}
