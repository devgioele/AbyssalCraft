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
package com.shinoow.abyssalcraft.common.entity.demon;

import com.shinoow.abyssalcraft.common.entity.EntityLesserShoggoth;
import com.shinoow.abyssalcraft.lib.ACConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class EntityEvilChicken extends EntityMob implements IShearable {

	public float field_70886_e;
	public float destPos;
	public float field_70884_g;
	public float field_70888_h;
	public float field_70889_i = 1.0F;

	public EntityEvilChicken(World par1World) {
		super(par1World);
		setSize(0.3F, 0.7F);
		((PathNavigateGround)getNavigator()).setAvoidsWater(true);
		isImmuneToFire = true;
		double var2 = 0.35D;
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, var2, true));
		tasks.addTask(3, new EntityAIWander(this, var2));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		if(ACConfig.hardcoreMode){
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		} else getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if(ACConfig.hardcoreMode && par1Entity instanceof EntityPlayer)
			par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this).setDamageBypassesArmor().setDamageIsAbsolute(), 1.5F * (float)(ACConfig.damageAmpl > 1.0D ? ACConfig.damageAmpl : 1));

		return super.attackEntityAsMob(par1Entity);
	}

	@Override
	public String getName()
	{
		return StatCollector.translateToLocal("entity.Chicken.name");
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.chicken.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.ghast.scream";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.chicken.hurt";
	}

	@Override
	protected void playStepSound(BlockPos pos, Block par4)
	{
		playSound("mob.chicken.step", 0.15F, 1.0F);
	}

	@Override
	public void fall(float p_70069_1_, float par2) {}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		field_70888_h = field_70886_e;
		field_70884_g = destPos;
		destPos = (float)(destPos + (onGround ? -1 : 4) * 0.3D);

		if (destPos < 0.0F)
			destPos = 0.0F;

		if (destPos > 1.0F)
			destPos = 1.0F;

		if (!onGround && field_70889_i < 1.0F)
			field_70889_i = 1.0F;

		field_70889_i = (float)(field_70889_i * 0.9D);

		if (!onGround && motionY < 0.0D)
			motionY *= 0.6D;

		field_70886_e += field_70889_i * 2.0F;
	}

	@Override
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);

		if(!worldObj.isRemote)
			if(!(par1DamageSource.getEntity() instanceof EntityLesserShoggoth))
			{
				EntityDemonChicken demonchicken = new EntityDemonChicken(worldObj);
				demonchicken.copyLocationAndAnglesFrom(this);
				worldObj.removeEntity(this);
				demonchicken.onInitialSpawn(worldObj.getDifficultyForLocation(new BlockPos(posX, posY, posZ)), (IEntityLivingData)null);
				worldObj.spawnEntityInWorld(demonchicken);
			}
	}

	@Override
	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
	{
		int j = rand.nextInt(3) + rand.nextInt(1 + p_70628_2_);

		for (int k = 0; k < j; ++k)
			dropItem(Items.feather, 1);

		dropItem(Items.chicken, 1);
	}

	@Override public boolean isShearable(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos){ return true; }
	@Override
	public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
	{
		int i = 1 + rand.nextInt(3);

		java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
		for (int j = 0; j < i; ++j)
			ret.add(new ItemStack(Items.feather));

		playSound("mob.sheep.shear", 1.0F, 1.0F);
		playSound("mob.ghast.scream", 1.0F, 0.2F);
		if(!worldObj.isRemote){
			EntityDemonChicken demonchicken = new EntityDemonChicken(worldObj);
			demonchicken.copyLocationAndAnglesFrom(this);
			worldObj.removeEntity(this);
			demonchicken.onInitialSpawn(worldObj.getDifficultyForLocation(new BlockPos(posX, posY, posZ)), (IEntityLivingData)null);
			worldObj.spawnEntityInWorld(demonchicken);
		}
		return ret;
	}
}
