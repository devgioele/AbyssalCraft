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
package com.shinoow.abyssalcraft.common.entity.anti;

import com.shinoow.abyssalcraft.api.entity.IAntiEntity;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.common.util.ExplosionUtil;
import com.shinoow.abyssalcraft.lib.ACConfig;
import com.shinoow.abyssalcraft.lib.ACLoot;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAntiPig extends EntityAnimal implements IAntiEntity {

	public EntityAntiPig(World par1World)
	{
		super(par1World);
		setSize(0.9F, 0.9F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 1.25D));
		tasks.addTask(3, new EntityAIMate(this, 1.0D));
		tasks.addTask(4, new EntityAITempt(this, 1.2D, Items.CARROT_ON_A_STICK, false));
		tasks.addTask(4, new EntityAITempt(this, 1.2D, Items.CARROT, false));
		tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
		tasks.addTask(6, new EntityAIWander(this, 1.0D));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.ENTITY_PIG_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundEvents.ENTITY_PIG_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_PIG_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block par4Block)
	{
		playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
	}

	@Override
	protected Item getDropItem()
	{
		return ACItems.getInstance().anti_pork;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int j = rand.nextInt(3) + 1 + rand.nextInt(1 + par2);

		for (int k = 0; k < j; ++k)
			if (isBurning())
				dropItem(ACItems.getInstance().anti_pork, 1);
			else
				dropItem(ACItems.getInstance().anti_pork, 1);
	}

	@Override
	protected ResourceLocation getLootTable(){
		return ACLoot.ENTITY_ANTI_PIG;
	}

	@Override
	protected void collideWithEntity(Entity par1Entity)
	{
		if(!world.isRemote && par1Entity instanceof EntityPig){
			boolean flag = world.getGameRules().getBoolean("mobGriefing");
			if(ACConfig.nuclearAntimatterExplosions)
				ExplosionUtil.newODBExplosion(world, this, posX, posY, posZ, ACConfig.antimatterExplosionSize, true, flag);
			else world.createExplosion(this, posX, posY, posZ, 5, flag);
			setDead();
		}
		else par1Entity.applyEntityCollision(this);
	}

	@Override
	public EntityAntiPig createChild(EntityAgeable par1EntityAgeable)
	{
		return new EntityAntiPig(world);
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return !par1ItemStack.isEmpty() && par1ItemStack.getItem() == Items.CARROT;
	}
}
