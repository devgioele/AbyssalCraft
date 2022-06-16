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

import java.util.Random;

import com.shinoow.abyssalcraft.api.entity.IAntiEntity;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.common.util.ExplosionUtil;
import com.shinoow.abyssalcraft.lib.ACConfig;
import com.shinoow.abyssalcraft.lib.ACLoot;

import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityAntiSpider extends EntityMob implements IAntiEntity {

	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityAntiSpider.class, DataSerializers.BYTE);

	public EntityAntiSpider(World par1World)
	{
		super(par1World);
		setSize(1.4F, 0.9F);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(4, new AIAntiSpiderAttack(this));
		tasks.addTask(5, new EntityAIWander(this, 0.8D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		targetTasks.addTask(2, new AIAntiSpiderTarget(this, EntitySpider.class));
		targetTasks.addTask(3, new AIAntiSpiderTarget(this, EntityPlayer.class));
		targetTasks.addTask(4, new AIAntiSpiderTarget(this, EntityIronGolem.class));
	}

	@Override
	public double getMountedYOffset()
	{
		return height * 0.5F;
	}

	@Override
	protected PathNavigate createNavigator(World worldIn)
	{
		return new PathNavigateClimber(this, worldIn);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataManager.register(CLIMBING, new Byte((byte)0));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!world.isRemote)
			setBesideClimbableBlock(collidedHorizontally);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ACConfig.hardcoreMode ? 48.0D : 24.0D);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if(ACConfig.hardcoreMode && par1Entity instanceof EntityPlayer)
			par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this).setDamageBypassesArmor().setDamageIsAbsolute(), 1.5F * (float)(ACConfig.damageAmpl > 1.0D ? ACConfig.damageAmpl : 1));

		return super.attackEntityAsMob(par1Entity);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.ENTITY_SPIDER_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundEvents.ENTITY_SPIDER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_SPIDER_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	protected Item getDropItem()
	{
		return Items.STRING;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		super.dropFewItems(par1, par2);

		if (par1 && (rand.nextInt(3) == 0 || rand.nextInt(1 + par2) > 0))
			dropItem(ACItems.getInstance().anti_spider_eye, 1);
	}

	@Override
	public boolean isOnLadder()
	{
		return isBesideClimbableBlock();
	}

	@Override
	public void setInWeb() {}

	@Override
	protected ResourceLocation getLootTable(){
		return ACLoot.ENTITY_ANTI_SPIDER;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
	{
		return par1PotionEffect.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(par1PotionEffect);
	}

	@Override
	protected void collideWithEntity(Entity par1Entity)
	{
		if(!world.isRemote && par1Entity instanceof EntitySpider){
			boolean flag = world.getGameRules().getBoolean("mobGriefing");
			if(ACConfig.nuclearAntimatterExplosions)
				ExplosionUtil.newODBExplosion(world, this, posX, posY, posZ, ACConfig.antimatterExplosionSize, true, flag);
			else world.createExplosion(this, posX, posY, posZ, 5, flag);
			setDead();
		}
		else par1Entity.applyEntityCollision(this);
	}

	/**
	 * Returns true if the WatchableObject (Byte) is 0x01 otherwise returns false. The WatchableObject is updated using
	 * setBesideClimableBlock.
	 */
	public boolean isBesideClimbableBlock()
	{
		return (dataManager.get(CLIMBING) & 1) != 0;
	}

	/**
	 * Updates the WatchableObject (Byte) created in entityInit(), setting it to 0x01 if par1 is true or 0x00 if it is
	 * false.
	 */
	public void setBesideClimbableBlock(boolean par1)
	{
		byte b0 = dataManager.get(CLIMBING);

		if (par1)
			b0 = (byte)(b0 | 1);
		else
			b0 &= -2;

		dataManager.set(CLIMBING, b0);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData)
	{
		Object p_110161_1_1 = super.onInitialSpawn(difficulty, par1EntityLivingData);

		if (world.rand.nextInt(100) == 0)
		{
			EntityAntiSkeleton entityskeleton = new EntityAntiSkeleton(world);
			entityskeleton.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
			entityskeleton.onInitialSpawn(difficulty, (IEntityLivingData)null);
			world.spawnEntity(entityskeleton);
			entityskeleton.startRiding(this);
		}

		if (p_110161_1_1 == null)
		{
			p_110161_1_1 = new EntityAntiSpider.GroupData();

			if (world.getDifficulty() == EnumDifficulty.HARD && world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty())
				((EntityAntiSpider.GroupData)p_110161_1_1).func_111104_a(world.rand);
		}

		if (p_110161_1_1 instanceof EntityAntiSpider.GroupData)
		{
			Potion i = ((EntityAntiSpider.GroupData)p_110161_1_1).field_111105_a;

			if (Potion.getIdFromPotion(i) > 0)
				addPotionEffect(new PotionEffect(i, Integer.MAX_VALUE));
		}

		return (IEntityLivingData)p_110161_1_1;
	}

	@Override
	public float getEyeHeight()
	{
		return 0.65F;
	}

	static class AIAntiSpiderAttack extends EntityAIAttackMelee
	{
		public AIAntiSpiderAttack(EntityAntiSpider p_i45819_1_)
		{
			super(p_i45819_1_, 1.0D, true);
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		@Override
		public boolean shouldContinueExecuting()
		{
			float f = attacker.getBrightness();

			if (f >= 0.5F && attacker.getRNG().nextInt(100) == 0)
			{
				attacker.setAttackTarget((EntityLivingBase)null);
				return false;
			} else
				return super.shouldContinueExecuting();
		}

		@Override
		protected double getAttackReachSqr(EntityLivingBase attackTarget)
		{
			return 4.0F + attackTarget.width;
		}
	}

	static class AIAntiSpiderTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget
	{
		public AIAntiSpiderTarget(EntityAntiSpider p_i45818_1_, Class<T> classTarget)
		{
			super(p_i45818_1_, classTarget, true);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean shouldExecute()
		{
			float f = taskOwner.getBrightness();
			return f >= 0.5F ? false : super.shouldExecute();
		}
	}

	public static class GroupData implements IEntityLivingData
	{
		public Potion field_111105_a;

		public void func_111104_a(Random par1Random)
		{
			int i = par1Random.nextInt(5);

			if (i <= 1)
				field_111105_a = MobEffects.SPEED;
			else if (i <= 2)
				field_111105_a = MobEffects.STRENGTH;
			else if (i <= 3)
				field_111105_a = MobEffects.REGENERATION;
			else if (i <= 4)
				field_111105_a = MobEffects.INVISIBILITY;
		}
	}

}
