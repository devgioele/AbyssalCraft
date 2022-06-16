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

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.api.ritual.EnumRitualParticle;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.common.entity.EntityDreadSpawn;
import com.shinoow.abyssalcraft.common.entity.EntityGreaterDreadSpawn;
import com.shinoow.abyssalcraft.common.entity.EntityLesserDreadbeast;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NecronomiconDreadSpawnRitual extends NecronomiconRitual {

	public NecronomiconDreadSpawnRitual() {
		super("dreadSpawn", 2, 500F, ACItems.getInstance().dread_fragment, new ItemStack(ACBlocks.getInstance().dreadstone.getBlock()),
				new ItemStack(ACBlocks.getInstance().abyssalnite_stone.getBlock()), ACItems.getInstance().chunk_of_abyssalnite,
				ACItems.getInstance().dreaded_chunk_of_abyssalnite, Items.ROTTEN_FLESH, ACItems.getInstance().dreaded_shard_of_abyssalnite,
				ACItems.getInstance().abyssalnite_ingot);
		setRitualParticle(EnumRitualParticle.SMOKE_PILLARS);
	}

	@Override
	public boolean canCompleteRitual(World world, BlockPos pos, EntityPlayer player) {
		List<EntityDreadSpawn> dreadSpawns = world.getEntitiesWithinAABB(EntityDreadSpawn.class, new AxisAlignedBB(pos).grow(16, 3, 16));
		List<EntityGreaterDreadSpawn> greaterDreadSpawns = world.getEntitiesWithinAABB(EntityGreaterDreadSpawn.class, new AxisAlignedBB(pos).grow(16, 3, 16));
		List<EntityLesserDreadbeast> lesserDreadBeasts = world.getEntitiesWithinAABB(EntityLesserDreadbeast.class, new AxisAlignedBB(pos).grow(16, 3, 16));
		return !dreadSpawns.isEmpty() || !greaterDreadSpawns.isEmpty() || !lesserDreadBeasts.isEmpty();
	}

	@Override
	protected void completeRitualServer(World world, BlockPos pos, EntityPlayer player) {
		List<EntityDreadSpawn> dreadSpawns = new ArrayList<>();
		List<EntityGreaterDreadSpawn> greaterDreadSpawns = new ArrayList<>();
		List<EntityLesserDreadbeast> lesserDreadBeasts = new ArrayList<>();

		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(pos).grow(16, 3, 16));
		for(Entity entity : entities){
			if(entity instanceof EntityDreadSpawn)
				dreadSpawns.add((EntityDreadSpawn) entity);
			if(entity instanceof EntityGreaterDreadSpawn)
				greaterDreadSpawns.add((EntityGreaterDreadSpawn) entity);
			if(entity instanceof EntityLesserDreadbeast)
				lesserDreadBeasts.add((EntityLesserDreadbeast) entity);
		}

		if(!dreadSpawns.isEmpty())
			for(EntityDreadSpawn spawn : dreadSpawns){
				EntityDreadSpawn copy = new EntityDreadSpawn(world);
				copy.copyLocationAndAnglesFrom(spawn);
				world.spawnEntity(copy);
			}
		if(!greaterDreadSpawns.isEmpty())
			for(EntityGreaterDreadSpawn spawn : greaterDreadSpawns){
				EntityGreaterDreadSpawn copy = new EntityGreaterDreadSpawn(world);
				copy.copyLocationAndAnglesFrom(spawn);
				world.spawnEntity(copy);
			}
		if(!lesserDreadBeasts.isEmpty())
			for(EntityLesserDreadbeast spawn : lesserDreadBeasts){
				EntityLesserDreadbeast copy = new EntityLesserDreadbeast(world);
				copy.copyLocationAndAnglesFrom(spawn);
				world.spawnEntity(copy);
			}
	}

	@Override
	protected void completeRitualClient(World world, BlockPos pos, EntityPlayer player) {}
}
