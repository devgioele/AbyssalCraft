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
package com.shinoow.abyssalcraft.common.ritual;

import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.common.structures.omothol.StructureJzaharTemple;
import com.shinoow.abyssalcraft.lib.ACLib;
import com.shinoow.abyssalcraft.lib.util.RitualUtil;
import com.shinoow.abyssalcraft.lib.util.SpecialTextUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class NecronomiconRespawnJzaharRitual extends NecronomiconRitual {

	public NecronomiconRespawnJzaharRitual() {
		super("respawnJzahar", 3, ACLib.omothol_id, 20000F, new Object[]{
				new ItemStack(ACItems.getInstance().essence, 1, 2),
				ACItems.getInstance().shard_of_oblivion,
				new ItemStack(ACItems.getInstance().essence, 1, 2),
				ACItems.getInstance().shard_of_oblivion,
				new ItemStack(ACItems.getInstance().essence, 1, 2),
				ACItems.getInstance().shard_of_oblivion,
				new ItemStack(ACItems.getInstance().essence, 1, 2),
				ACItems.getInstance().shard_of_oblivion
		});

	}

	@Override
	public boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player) {

		return pos.getX() == 4 && pos.getY() == 54 && pos.getZ() == 85;
	}

	@Override
	protected void completeRitualClient(World world, BlockPos pos, EntityPlayer player) {
	}

	@Override
	protected void completeRitualServer(World world, BlockPos pos, EntityPlayer player) {
		StructureJzaharTemple temple = new StructureJzaharTemple();
		temple.generate(world, world.rand, new BlockPos(4, 53, 7));
		RitualUtil.tryAltar(world, pos, 4);
		world.getChunk(pos).markDirty();
		SpecialTextUtil.JzaharGroup(world,
				I18n.translateToLocalFormatted("message.jzahar.respawn", player.getName()));
	}

}
