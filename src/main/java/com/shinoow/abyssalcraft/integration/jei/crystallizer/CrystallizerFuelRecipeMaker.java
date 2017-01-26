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
package com.shinoow.abyssalcraft.integration.jei.crystallizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.shinoow.abyssalcraft.common.blocks.tile.TileEntityCrystallizer;
import com.shinoow.abyssalcraft.integration.jei.JEIUtils;

public class CrystallizerFuelRecipeMaker {

	@Nonnull
	public static List<CrystallizerFuelRecipe> getFuelRecipes(JEIUtils itemRegistry, IJeiHelpers helpers) {
		IGuiHelper guiHelper = helpers.getGuiHelper();
		IStackHelper stackHelper = helpers.getStackHelper();
		List<ItemStack> fuelStacks = itemRegistry.getCrystallizerFuels();
		Set<String> oreDictNames = new HashSet<>();
		List<CrystallizerFuelRecipe> fuelRecipes = new ArrayList<>(fuelStacks.size());
		for (ItemStack fuelStack : fuelStacks) {
			if (fuelStack == null)
				continue;

			int[] oreIDs = OreDictionary.getOreIDs(fuelStack);
			if (oreIDs.length > 0)
				for (int oreID : oreIDs) {
					String name = OreDictionary.getOreName(oreID);
					if (oreDictNames.contains(name))
						continue;

					oreDictNames.add(name);
					List<ItemStack> oreDictFuels = OreDictionary.getOres(name);
					Collection<ItemStack> oreDictFuelsSet = stackHelper.getAllSubtypes(oreDictFuels);
					removeNoBurnTime(oreDictFuelsSet);
					if (oreDictFuels.isEmpty())
						continue;
					int burnTime = getBurnTime(oreDictFuels.get(0));

					fuelRecipes.add(new CrystallizerFuelRecipe(guiHelper, oreDictFuelsSet, burnTime));
				}
			else {
				List<ItemStack> fuels = stackHelper.getSubtypes(fuelStack);
				removeNoBurnTime(fuels);
				if (fuels.isEmpty())
					continue;
				int burnTime = getBurnTime(fuels.get(0));
				fuelRecipes.add(new CrystallizerFuelRecipe(guiHelper, fuels, burnTime));
			}
		}
		return fuelRecipes;
	}

	private static void removeNoBurnTime(Collection<ItemStack> itemStacks) {
		Iterator<ItemStack> iterator = itemStacks.iterator();
		while (iterator.hasNext()) {
			ItemStack itemStack = iterator.next();
			if (getBurnTime(itemStack) == 0)
				iterator.remove();
		}
	}

	private static int getBurnTime(ItemStack itemStack) {
		return TileEntityCrystallizer.getCrystallizationTime(itemStack);
	}
}
