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
package com.shinoow.abyssalcraft.common.items.armor;

import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.lib.ACConfig;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemDreadiumArmor extends ItemACArmor {
	public ItemDreadiumArmor(ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4, String name){
		super(par2EnumArmorMaterial, par3, par4, name);
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack) {
		return TextFormatting.DARK_RED + super.getItemStackDisplayName(par1ItemStack);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String layer) {
		if(stack.getItem() == ACItems.getInstance().dreadium_helmet || stack.getItem() == ACItems.getInstance().dreadium_chestplate || stack.getItem() == ACItems.getInstance().dreadium_boots)
			return "abyssalcraft:textures/armor/dreadium_1.png";

		if(stack.getItem() == ACItems.getInstance().dreadium_leggings)
			return "abyssalcraft:textures/armor/dreadium_2.png";
		else return null;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack) {
		if(world.isRemote || !ACConfig.armorPotionEffects) return;
		if (itemstack.getItem() == ACItems.getInstance().dreadium_chestplate)
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 0, false, false));
		if (itemstack.getItem() == ACItems.getInstance().dreadium_boots)
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 0, false, false));
	}
}
