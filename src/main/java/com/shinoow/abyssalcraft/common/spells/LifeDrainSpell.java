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
package com.shinoow.abyssalcraft.common.spells;

import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.api.spell.Spell;
import com.shinoow.abyssalcraft.api.spell.SpellUtils;
import com.shinoow.abyssalcraft.client.handlers.GeneralHooks;
import com.shinoow.abyssalcraft.client.handlers.MouseHooks;
import com.shinoow.abyssalcraft.common.network.PacketDispatcher;
import com.shinoow.abyssalcraft.common.network.server.MobSpellMessage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class LifeDrainSpell extends Spell {

	public LifeDrainSpell() {
		super("lifedrain", 100, Items.APPLE);
		setParchment(new ItemStack(ACItems.getInstance().scroll, 1, 0));
		setRequiresCharging();
		setColor(0xa00404);
	}

	@Override
	public boolean canCastSpell(World world, BlockPos pos, EntityPlayer player) {

		if(world.isRemote){
			RayTraceResult r = MouseHooks.getMouseOverExtended(15);
			if(r != null && r.entityHit instanceof EntityLivingBase)
				return SpellUtils.canPlayerHurt(player, r.entityHit);
		}
		return false;
	}

	@Override
	protected void castSpellClient(World world, BlockPos pos, EntityPlayer player) {
		RayTraceResult r = MouseHooks.getMouseOverExtended(15);
		if(r != null && r.entityHit instanceof EntityLivingBase && !r.entityHit.isDead)
			PacketDispatcher.sendToServer(new MobSpellMessage(r.entityHit.getEntityId(), 0));
	}

	@Override
	protected void castSpellServer(World world, BlockPos pos, EntityPlayer player) {}
}
