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
package com.shinoow.abyssalcraft.lib.util;

import java.util.function.BiConsumer;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.lib.ACConfig;

import com.shinoow.abyssalcraft.lib.ACLib;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Utility class for particle-related stuff
 * @author shinoow
 *
 */
public class ParticleUtil {

	/**
	 * Spawn particles in a line between two positions
	 * @param from Origin BlockPos
	 * @param to Desination BlockPos
	 * @param density How densely the particles will spawn (higher number = more particles)
	 * @param func Function to spawn the particles in (first vector is the one between the
	 * positions, the second is the coordinates for the particle to spawn at), world context
	 * should be supplied by the invoker of the method
	 */
	public static void spawnParticleLine(BlockPos from, BlockPos to, int density, BiConsumer<Vec3d, Vec3d> func) {
		spawnParticleLine(from, to, density, 1, func);
	}

	/**
	 * Spawn particles in a line between two positions
	 * @param from Origin BlockPos
	 * @param to Desination BlockPos
	 * @param density How densely the particles will spawn (higher number = more particles)
	 * @param increments How much to increment the spawning loop by (affects density)
	 * @param func Function to spawn the particles in (first vector is the one between the
	 * positions, the second is the coordinates for the particle to spawn at), world context
	 * should be supplied by the invoker of the method
	 */
	public static void spawnParticleLine(BlockPos from, BlockPos to, int density, int increments, BiConsumer<Vec3d, Vec3d> func) {
		Vec3d vec = new Vec3d(to.subtract(from)).normalize();

		double d = Math.sqrt(to.distanceSq(from));
		for(int i = 0; i < d * density; i+=increments){
			double i1 = i / (double)density;
			double xp = from.getX() + vec.x * i1 + .5;
			double yp = from.getY() + vec.y * i1 + .5;
			double zp = from.getZ() + vec.z * i1 + .5;
			func.accept(vec, new Vec3d(xp, yp, zp));
		}
	}

	/**
	 * Spawns the particles seen around Shadow mobs
	 * @param entity Entity to spawn the particles by
	 */
	public static void spawnShadowParticles(EntityLivingBase entity) {
		if(entity.getCreatureAttribute() == AbyssalCraftAPI.SHADOW)
			for (int i = 0; i < 2 * (entity.getBrightness() > 0.1f ? entity.getBrightness() : 0) && ACConfig.particleEntity; ++i)
				actuallySpawnThem(entity);
		else
			actuallySpawnThem(entity);
	}

	private static void actuallySpawnThem(EntityLivingBase entity) {
		entity.getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
				entity.posX + (entity.getRNG().nextDouble() - 0.5D) * entity.width,
				entity.posY + entity.getRNG().nextDouble() * entity.height,
				entity.posZ + (entity.getRNG().nextDouble() - 0.5D) * entity.width, 0.0D, 0.0D, 0.0D);
	}

	public static void makeVoidFog(World world, Entity entity)
	{
		if(Minecraft.getMinecraft().isGamePaused() || !world.isRemote) return;
		if(world.provider.getDimension() != ACLib.dark_realm_id && world.provider.getDimension() != ACLib.omothol_id
				&& world.provider.getDimension() != ACLib.abyssal_wasteland_id && world.provider.getDimension() != ACLib.dreadlands_id) return;
		byte b0 = 16;
		byte b1 = 14;
		int x = MathHelper.floor(entity.posX);
		int y = MathHelper.floor(entity.posY);
		int z = MathHelper.floor(entity.posZ);
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		boolean darkRealm = world.provider.getDimension() == ACLib.dark_realm_id;
		for (int l = 0; l < 100; ++l)
		{
			int i1 = x + world.rand.nextInt(b0) - world.rand.nextInt(b0);
			int j1 = y + world.rand.nextInt(b0) - world.rand.nextInt(b0);
			int k1 = z + world.rand.nextInt(b0) - world.rand.nextInt(b0);
			if(darkRealm) {
				if(j1 > y+b1 || j1+b1 < y) return;
				if(i1 > x+b1 || i1+b1 < x) return;
				if(k1 > z+b1 || k1+b1 < z) return;
			}
			pos.setPos(i1, j1, k1);
			IBlockState state = world.getBlockState(pos);

			if (state.getMaterial() == Material.AIR)
				if (world.getLightFromNeighbors(pos) == 0)
				{
					boolean canSpawn = false;
					if(darkRealm)
						canSpawn = world.canBlockSeeSky(pos);
					else if(world.provider.getDimension() == ACLib.omothol_id){
						if(world.getBlockState(pos.down()).getMaterial() != Material.AIR ||
								world.getBlockState(pos.down(2)).getMaterial() != Material.AIR ||
								world.getBlockState(pos.down(3)).getMaterial() != Material.AIR)
							canSpawn = true;
					} else if(j1 <= 6)
						if(world.getBlockState(pos.up()).getMaterial() != Material.AIR ||
								world.getBlockState(pos.up(2)).getMaterial() != Material.AIR)
							canSpawn = true;

					if(canSpawn)
						world.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, i1 + world.rand.nextFloat(), j1 + world.rand.nextFloat(), k1 + world.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
				}
		}
	}
}
