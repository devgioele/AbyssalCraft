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
package com.shinoow.abyssalcraft.common.blocks.itemblock;

import javax.annotation.Nullable;

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.item.IUnlockableItem;
import com.shinoow.abyssalcraft.api.necronomicon.condition.DefaultCondition;
import com.shinoow.abyssalcraft.api.necronomicon.condition.IUnlockCondition;

import com.shinoow.abyssalcraft.common.blocks.IngotBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockAC extends ItemBlock implements IUnlockableItem {

	private IUnlockCondition condition = new DefaultCondition();

	public ItemBlockAC(Block block) {
		super(block);
	}

	@Override
	public Item setUnlockCondition(IUnlockCondition condition) {
		this.condition = condition;
		return this;
	}

	@Override
	public IUnlockCondition getUnlockCondition(ItemStack stack) {

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
		String key = getTranslationKey();
		if (block instanceof IngotBlock) return ((IngotBlock) block).getType().getFormat() +
				super.getItemStackDisplayName(itemStack);
		else if (ACBlocks.oblivion_deathbomb.hasTranslationKey(key) ||
				key.contains("darkethaxium") || key.contains("dark_ethaxium"))
			return TextFormatting.DARK_RED + super.getItemStackDisplayName(itemStack);
		else if (key.contains("ethaxium"))
			return TextFormatting.AQUA + super.getItemStackDisplayName(itemStack);
		else if (key.contains("aby"))
			return TextFormatting.BLUE + super.getItemStackDisplayName(itemStack);
		return super.getItemStackDisplayName(itemStack);
	}
}
