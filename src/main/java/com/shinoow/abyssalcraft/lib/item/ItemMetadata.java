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
package com.shinoow.abyssalcraft.lib.item;

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.item.IUnlockableItem;
import com.shinoow.abyssalcraft.api.necronomicon.condition.DefaultCondition;
import com.shinoow.abyssalcraft.api.necronomicon.condition.IUnlockCondition;
import com.shinoow.abyssalcraft.lib.ACTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * Simple implementation of an Item with metadata subtypes.
 *
 * @author shinoow
 */
public class ItemMetadata extends Item implements IUnlockableItem {

    private final String[] variations;
    private IUnlockCondition condition = new DefaultCondition();

    public ItemMetadata(String name, String... variations) {
        setTranslationKey(name);
        setCreativeTab(ACTabs.tabItems);
        setMaxDamage(0);
        setHasSubtypes(true);
        this.variations = variations;
    }

    public String[] getVariationNames() {
        String name = getTranslationKey();
        return Arrays.stream(variations).map(variation -> String.join("_", name, variation))
                .toArray(String[]::new);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> itemStacks) {
        if (isInCreativeTab(creativeTabs)) for (int i = 0; i < variations.length; ++i)
            itemStacks.add(new ItemStack(this, 1, i));
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return getTranslationKey() + "." + variations[stack.getItemDamage()];
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
    public net.minecraft.client.gui.FontRenderer getFontRenderer(ItemStack stack) {
        return APIUtils.getFontRenderer(stack);
    }

}
