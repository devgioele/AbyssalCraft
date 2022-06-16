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

import java.util.List;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.lib.ACConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional.Interface;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IVisDiscountGear;

@Interface(iface = "thaumcraft.api.items.IVisDiscountGear", modid = "thaumcraft")
public class ItemDepthsArmor extends ItemACArmor implements IVisDiscountGear {
	public ItemDepthsArmor(ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4, String name){
		super(par2EnumArmorMaterial, par3, par4, name);
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack) {
		return TextFormatting.DARK_RED + super.getItemStackDisplayName(par1ItemStack);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String layer)
	{
		ACItems items = ACItems.getInstance();
		if(stack.getItem() == items.depths_helmet || stack.getItem() == items.depths_chestplate || stack.getItem() == items.depths_boots)
			return "abyssalcraft:textures/armor/depths_1.png";

		if(stack.getItem() == items.depths_leggings)
			return "abyssalcraft:textures/armor/depths_2.png";
		else return null;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack) {
		if(world.isRemote || !ACConfig.armorPotionEffects) return;
		ACItems items = ACItems.getInstance();
		if (itemstack.getItem() == items.depths_helmet)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 20, 0, false, false));
			if(world.provider.isSurfaceWorld())
				player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 260, 0, false, false));
			if(player.getActivePotionEffect(AbyssalCraftAPI.coralium_plague) !=null)
				player.removePotionEffect(AbyssalCraftAPI.coralium_plague);
		}
		if (itemstack.getItem() == items.depths_chestplate)
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 0, false, false));
		if (itemstack.getItem() == items.depths_leggings)
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 20, 1, false, false));
		if (itemstack.getItem() == items.depths_boots)
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 1, false, false));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks){
		final ResourceLocation coraliumBlur = new ResourceLocation("abyssalcraft:textures/misc/coraliumblur.png");


		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && stack != null && stack.getItem() == ACItems.getInstance().depths_helmet) {

			GlStateManager.disableDepth();
			GlStateManager.depthMask(false);
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, (float)ACConfig.depthsHelmetOverlayOpacity);

			Minecraft.getMinecraft().renderEngine.bindTexture(coraliumBlur);

			Tessellator t = Tessellator.getInstance();
			BufferBuilder wr = t.getBuffer();

			wr.begin(7, DefaultVertexFormats.POSITION_TEX);
			wr.pos(0.0D, resolution.getScaledHeight(), 90.0D).tex(0.0D, 1.0D).endVertex();
			wr.pos(resolution.getScaledWidth(), resolution.getScaledHeight(), 90.0D).tex(1.0D, 1.0D).endVertex();
			wr.pos(resolution.getScaledWidth(), 0.0D, 90.0D).tex(1.0D, 0.0D).endVertex();
			wr.pos(0.0D, 0.0D, 90.0D).tex(0.0D, 0.0D).endVertex();
			t.draw();

			GlStateManager.depthMask(true);
			GlStateManager.enableDepth();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	@Method(modid = "thaumcraft")
	public int getVisDiscount(ItemStack stack, EntityPlayer player) {
		ACItems items = ACItems.getInstance();
		return stack.getItem() == items.depths_helmet ? 5 : stack.getItem() == items.depths_chestplate ? 2 :
			stack.getItem() == items.depths_leggings ? 2 : stack.getItem() == items.depths_boots ? 1 : 0;
	}

	@Override
	public void addInformation(ItemStack is, World player, List<String> l, ITooltipFlag B){
		if(Loader.isModLoaded("thaumcraft")){
			ACItems items = ACItems.getInstance();
			if(is.getItem() == items.depths_helmet)
				l.add("\u00A75"+I18n.format("tc.visdiscount")+": 5%");
			if(is.getItem() == items.depths_chestplate)
				l.add("\u00A75"+I18n.format("tc.visdiscount")+": 2%");
			if(is.getItem() == items.depths_leggings)
				l.add("\u00A75"+I18n.format("tc.visdiscount")+": 2%");
			if(is.getItem() == items.depths_boots)
				l.add("\u00A75"+I18n.format("tc.visdiscount")+": 1%");
		}
	}
}
