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
package com.shinoow.abyssalcraft.lib;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.item.ACItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * Creative Tab references
 *
 * @author shinoow
 */
public class ACTabs {

	public static void init() { /* Nothing special here, just initializing all the static things
	 */}

	public static final CreativeTabs tabBlock = new CreativeTabs("acblocks") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ACBlocks.getInstance().darkstone.getBlock());
		}
	};
	public static final CreativeTabs tabItems = new CreativeTabs("acitems") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ACItems.getInstance().necronomicon);
		}
	};
	public static final CreativeTabs tabTools = new CreativeTabs("actools") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ACItems.getInstance().darkstone_axe);
		}
	};
	public static final CreativeTabs tabCombat = new CreativeTabs("acctools") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ACItems.getInstance().darkstone_sword);
		}
	};
	public static final CreativeTabs tabFood = new CreativeTabs("acfood") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ACItems.getInstance().coralium_plagued_flesh_on_a_bone);
		}
	};
	public static final CreativeTabs tabDecoration = new CreativeTabs("acdblocks") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ACBlocks.getInstance().wooden_crate.getBlock());
		}
	};
	public static final CreativeTabs tabCrystals = new CreativeTabs("accrystals") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ACBlocks.getInstance().crystallizer_idle.getBlock());
		}
	};
	public static final CreativeTabs tabCoins = new CreativeTabs("accoins") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ACItems.getInstance().coin);
		}
	};
	public static final CreativeTabs tabSpells = new CreativeTabs("acspells") {

		@Override
		public ItemStack createIcon() {

			return new ItemStack(ACItems.getInstance().scroll, 1, 3);
		}
	};

}
