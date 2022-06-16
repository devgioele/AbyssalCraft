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
package com.shinoow.abyssalcraft.common;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.energy.EnergyEnum.DeityType;
import com.shinoow.abyssalcraft.api.energy.disruption.DisruptionHandler;
import com.shinoow.abyssalcraft.api.energy.disruption.DisruptionPotion;
import com.shinoow.abyssalcraft.api.energy.disruption.DisruptionSpawn;
import com.shinoow.abyssalcraft.api.energy.disruption.DisruptionSwarm;
import com.shinoow.abyssalcraft.api.entity.EntityUtil;
import com.shinoow.abyssalcraft.api.entity.IOmotholEntity;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.api.item.ItemEngraving;
import com.shinoow.abyssalcraft.api.rending.Rending;
import com.shinoow.abyssalcraft.api.rending.RendingRegistry;
import com.shinoow.abyssalcraft.api.ritual.*;
import com.shinoow.abyssalcraft.api.spell.SpellRegistry;
import com.shinoow.abyssalcraft.common.disruptions.*;
import com.shinoow.abyssalcraft.common.entity.*;
import com.shinoow.abyssalcraft.common.entity.demon.EntityEvilSheep;
import com.shinoow.abyssalcraft.common.ritual.*;
import com.shinoow.abyssalcraft.common.spells.*;
import com.shinoow.abyssalcraft.lib.ACConfig;
import com.shinoow.abyssalcraft.lib.ACLib;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class AbyssalCrafting {

	public static void addRecipes() {
		addBlockSmelting();
		addItemSmelting();
		addCrystallization();
		addTransmutation();
		addEngraving();
		addMaterialization();
		addRitualRecipes();
		addDisruptions();
		addSpells();
		addRendings();
	}

	private static void addBlockSmelting() {
		ACItems items = ACItems.getInstance();
		ACBlocks blocks = ACBlocks.getInstance();
		GameRegistry.addSmelting(new ItemStack(blocks.darkstone_cobblestone.getBlock()),
				new ItemStack(blocks.darkstone.getBlock()), 0.1F);
		AbyssalCraftAPI.addOreSmelting("oreAbyssalnite", "ingotAbyssalnite", 3F);
		AbyssalCraftAPI.addOreSmelting("oreCoralium", "gemCoralium", 3F);
		GameRegistry.addSmelting(blocks.darklands_oak_wood.getBlock(),
				new ItemStack(Items.COAL, 1, 1), 1F);
		GameRegistry.addSmelting(blocks.darklands_oak_wood_2.getBlock(),
				new ItemStack(Items.COAL, 1, 1), 1F);
		GameRegistry.addSmelting(blocks.coralium_infused_stone.getBlock(),
				new ItemStack(items.coralium_pearl), 3F);
		GameRegistry.addSmelting(blocks.pearlescent_coralium_ore.getBlock(),
				new ItemStack(items.coralium_pearl), 3F);
		GameRegistry.addSmelting(blocks.liquified_coralium_ore.getBlock(),
				new ItemStack(items.refined_coralium_ingot), 3F);
		GameRegistry.addSmelting(blocks.dreaded_abyssalnite_ore.getBlock(),
				new ItemStack(items.abyssalnite_ingot, 1), 3F);
		GameRegistry.addSmelting(new ItemStack(blocks.coralium_stone.getBlock()),
				new ItemStack(items.coralium_brick, 1), 0.1F);
		GameRegistry.addSmelting(blocks.nitre_ore.getBlock(), new ItemStack(items.nitre, 1), 1F);
		GameRegistry.addSmelting(blocks.abyssal_iron_ore.getBlock(),
				new ItemStack(Items.IRON_INGOT, 1), 0.7F);
		GameRegistry.addSmelting(blocks.abyssal_gold_ore.getBlock(),
				new ItemStack(Items.GOLD_INGOT, 1), 1F);
		GameRegistry.addSmelting(blocks.abyssal_diamond_ore.getBlock(),
				new ItemStack(Items.DIAMOND, 1), 1F);
		GameRegistry.addSmelting(blocks.abyssal_nitre_ore.getBlock(), new ItemStack(items.nitre,
						1),
				1F);
		GameRegistry.addSmelting(blocks.abyssal_tin_ore.getBlock(),
				new ItemStack(items.tin_ingot, 1), 0.7F);
		GameRegistry.addSmelting(blocks.abyssal_copper_ore.getBlock(),
				new ItemStack(items.copper_ingot, 1), 0.7F);
		GameRegistry.addSmelting(new ItemStack(blocks.ethaxium.getBlock()),
				new ItemStack(items.ethaxium_brick), 0.2F);
		GameRegistry.addSmelting(new ItemStack(blocks.ethaxium_brick.getBlock(), 1, 0),
				new ItemStack(blocks.cracked_ethaxium_brick.getBlock(), 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(blocks.dark_ethaxium_brick.getBlock(), 1, 0),
				new ItemStack(blocks.cracked_dark_ethaxium_brick.getBlock(), 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(blocks.darkstone_brick.getBlock(), 1, 0),
				new ItemStack(blocks.cracked_darkstone_brick.getBlock(), 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(blocks.abyssal_stone_brick.getBlock(), 1, 0),
				new ItemStack(blocks.cracked_abyssal_stone_brick.getBlock(), 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(blocks.dreadstone_brick.getBlock(), 1, 0),
				new ItemStack(blocks.cracked_dreadstone_brick.getBlock(), 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(blocks.abyssalnite_stone_brick.getBlock(), 1, 0),
				new ItemStack(blocks.cracked_abyssalnite_stone_brick.getBlock(), 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(blocks.coralium_stone_brick.getBlock(), 1, 0),
				new ItemStack(blocks.cracked_coralium_stone_brick.getBlock(), 1), 0.1F);
		GameRegistry.addSmelting(blocks.abyssal_sand.getBlock(),
				new ItemStack(blocks.abyssal_sand_glass.getBlock()), 0.1F);
		GameRegistry.addSmelting(new ItemStack(blocks.abyssal_cobblestone.getBlock()),
				new ItemStack(blocks.abyssal_stone.getBlock()), 0.1F);
		GameRegistry.addSmelting(new ItemStack(blocks.dreadstone_cobblestone.getBlock()),
				new ItemStack(blocks.dreadstone.getBlock()), 0.1F);
		GameRegistry.addSmelting(new ItemStack(blocks.abyssalnite_cobblestone.getBlock()),
				new ItemStack(blocks.abyssalnite_stone.getBlock()), 0.1F);
		GameRegistry.addSmelting(new ItemStack(blocks.coralium_cobblestone.getBlock()),
				new ItemStack(blocks.coralium_stone.getBlock()), 0.1F);
		GameRegistry.addSmelting(blocks.dreadlands_log.getBlock(), new ItemStack(items.charcoal),
				1F);
		GameRegistry.addSmelting(new ItemStack(items.generic_meat),
				new ItemStack(items.cooked_generic_meat), 0.35F);
	}

	private static void addItemSmelting() {
		ACItems items = ACItems.getInstance();
		GameRegistry.addSmelting(items.chunk_of_abyssalnite,
				new ItemStack(items.abyssalnite_ingot),
				3F);
		GameRegistry.addSmelting(items.chunk_of_coralium,
				new ItemStack(items.refined_coralium_ingot, 2), 3F);
		GameRegistry.addSmelting(items.dreaded_chunk_of_abyssalnite,
				new ItemStack(items.abyssalnite_ingot), 3F);
		GameRegistry.addSmelting(items.coin, new ItemStack(Items.IRON_INGOT, 4), 0.5F);

		if (ACConfig.smeltingRecipes) {
			addArmorSmelting(Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE,
					Items.LEATHER_LEGGINGS,
					Items.LEATHER_BOOTS, new ItemStack(Items.LEATHER));
			addArmorSmelting(items.abyssalnite_helmet, items.abyssalnite_chestplate,
					items.abyssalnite_leggings, items.abyssalnite_boots,
					new ItemStack(items.ingot_nugget, 1, 0));
			addArmorSmelting(items.refined_coralium_helmet, items.refined_coralium_chestplate,
					items.refined_coralium_leggings, items.refined_coralium_boots,
					new ItemStack(items.ingot_nugget, 1, 1));
			addArmorSmelting(items.dreadium_helmet, items.dreadium_chestplate,
					items.dreadium_leggings, items.dreadium_boots,
					new ItemStack(items.ingot_nugget, 1, 2));
			addArmorSmelting(items.ethaxium_helmet, items.ethaxium_chestplate,
					items.ethaxium_leggings, items.ethaxium_boots,
					new ItemStack(items.ingot_nugget, 1, 3));
		}
	}

	private static void addCrystallization() {
		ACItems items = ACItems.getInstance();
		ACBlocks blocks = ACBlocks.getInstance();
		AbyssalCraftAPI.addSingleCrystallization(Items.BLAZE_POWDER,
				new ItemStack(items.crystal_shard, 4, 15), 0.1F);
		AbyssalCraftAPI.addSingleCrystallization(new ItemStack(Items.DYE, 1, 15),
				new ItemStack(items.crystal_shard, 4, 7), 0.0F);
		AbyssalCraftAPI.addCrystallization(items.dreaded_chunk_of_abyssalnite,
				new ItemStack(items.crystal_shard, 4, 12),
				new ItemStack(items.crystal_shard, 4, 14), 0.2F);
		AbyssalCraftAPI.addCrystallization(Items.WATER_BUCKET,
				new ItemStack(items.crystal_shard, 12, 5), new ItemStack(items.crystal_shard, 6,
						4),
				0.1F);
		AbyssalCraftAPI.addCrystallization(
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER), new ItemStack(items.crystal_shard, 6, 5),
				new ItemStack(items.crystal_shard, 3, 4), 0.1F);
		AbyssalCraftAPI.addCrystallization(items.methane, new ItemStack(items.crystal_shard, 4, 4),
				new ItemStack(items.crystal, 16, 5), 0.1F);
		AbyssalCraftAPI.addCrystallization(Items.GUNPOWDER,
				new ItemStack(items.crystal_shard, 16, 9), new ItemStack(items.crystal_shard, 4,
						2),
				0.1F);
		AbyssalCraftAPI.addCrystallization(Blocks.OBSIDIAN,
				new ItemStack(items.crystal_shard, 4, 21),
				new ItemStack(items.crystal_shard, 4, 23), 0.1F);
		AbyssalCraftAPI.addCrystallization(Blocks.STONE, new ItemStack(items.crystal_shard, 4, 21),
				new ItemStack(items.crystal_shard, 4, 23), 0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.crystal, 1, 21),
				new ItemStack(items.crystal, 1, 18), new ItemStack(items.crystal, 2, 4), 0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.crystal_shard, 1, 21),
				new ItemStack(items.crystal_shard, 1, 18), new ItemStack(items.crystal_shard, 2,
						4),
				0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.crystal, 1, 22),
				new ItemStack(items.crystal, 2, 20), new ItemStack(items.crystal, 3, 4), 0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.crystal_shard, 1, 22),
				new ItemStack(items.crystal_shard, 2, 20), new ItemStack(items.crystal_shard, 3,
						4),
				0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.crystal, 1, 23),
				new ItemStack(items.crystal, 1, 19), new ItemStack(items.crystal, 1, 4), 0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.crystal_shard, 1, 23),
				new ItemStack(items.crystal_shard, 1, 19), new ItemStack(items.crystal_shard, 1,
						4),
				0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.crystal, 1, 10),
				new ItemStack(items.crystal, 1, 3), new ItemStack(items.crystal, 4, 5), 0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.crystal_shard, 1, 10),
				new ItemStack(items.crystal_shard, 1, 3), new ItemStack(items.crystal_shard, 4, 5),
				0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.crystal, 1, 9),
				new ItemStack(items.crystal, 1, 6), new ItemStack(items.crystal, 3, 4), 0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.crystal_shard, 1, 9),
				new ItemStack(items.crystal_shard, 1, 6), new ItemStack(items.crystal_shard, 3, 4),
				0.1F);
		AbyssalCraftAPI.addSingleCrystallization(Items.ROTTEN_FLESH,
				new ItemStack(items.crystal_shard, 8, 7), 0.1F);
		AbyssalCraftAPI.addSingleCrystallization(new ItemStack(items.shoggoth_flesh, 1, 0),
				new ItemStack(items.crystal_shard, 8, 7), 0.2F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.shoggoth_flesh, 1, 1),
				new ItemStack(items.crystal_shard, 8, 7),
				new ItemStack(items.crystal_fragment, 2, 13), 0.2F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.shoggoth_flesh, 1, 2),
				new ItemStack(items.crystal_shard, 8, 7),
				new ItemStack(items.crystal_fragment, 2, 14), 0.2F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.shoggoth_flesh, 1, 3),
				new ItemStack(items.crystal_shard, 8, 7),
				new ItemStack(items.crystal_fragment, 2, 3), 0.2F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.shoggoth_flesh, 1, 4),
				new ItemStack(items.crystal_shard, 8, 7), new ItemStack(items.shadow_gem, 1),
				0.2F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.coralium_plagued_flesh),
				new ItemStack(items.crystal_shard, 8, 7),
				new ItemStack(items.crystal_fragment, 1, 13), 0.2F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.coralium_plagued_flesh_on_a_bone),
				new ItemStack(items.crystal_shard, 12, 7),
				new ItemStack(items.crystal_fragment, 1, 13), 0.2F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.skin, 1, 0),
				new ItemStack(items.crystal_shard, 8, 7), new ItemStack(items.essence, 1, 0),
				0.2F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.skin, 1, 1),
				new ItemStack(items.crystal_shard, 8, 7), new ItemStack(items.essence, 1, 1),
				0.2F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(items.skin, 1, 2),
				new ItemStack(items.crystal_shard, 8, 7), new ItemStack(items.essence, 1, 2),
				0.2F);
		AbyssalCraftAPI.addCrystallization(items.dreaded_shard_of_abyssalnite,
				new ItemStack(items.crystal_shard, 1, 12),
				new ItemStack(items.crystal_shard, 4, 14), 0.2F);
		AbyssalCraftAPI.addSingleCrystallization(Items.BONE,
				new ItemStack(items.crystal_shard, 4, 25), 0.2F);
		AbyssalCraftAPI.addCrystallization(Items.PRISMARINE_SHARD,
				new ItemStack(items.crystal_shard, 4, 21),
				new ItemStack(items.crystal_shard, 4, 27), 0.1F);
		AbyssalCraftAPI.addCrystallization(Items.PRISMARINE_CRYSTALS,
				new ItemStack(items.crystal_shard, 4, 21),
				new ItemStack(items.crystal_shard, 4, 27), 0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(Blocks.PRISMARINE, 1, 0),
				new ItemStack(items.crystal_shard, 16, 21),
				new ItemStack(items.crystal_shard, 16, 27), 0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(Blocks.PRISMARINE, 1, 1),
				new ItemStack(items.crystal, 4, 21), new ItemStack(items.crystal, 4, 27), 0.1F);
		AbyssalCraftAPI.addCrystallization(new ItemStack(Blocks.PRISMARINE, 1, 2),
				new ItemStack(items.crystal_shard, 32, 21),
				new ItemStack(items.crystal_shard, 32, 27), 0.1F);
		AbyssalCraftAPI.addCrystallization(Items.EGG, new ItemStack(items.crystal_shard, 4, 25),
				new ItemStack(items.crystal_shard, 4, 7), 0.1F);

		if (ACConfig.crystal_rework) {
			AbyssalCraftAPI.addSingleCrystallization(items.refined_coralium_ingot,
					new ItemStack(items.crystal, 1, 13), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.chunk_of_coralium,
					new ItemStack(items.crystal, 1, 13), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(blocks.liquified_coralium_ore.getBlock(),
					new ItemStack(items.crystal, 1, 13), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.abyssalnite_ingot,
					new ItemStack(items.crystal, 1, 12), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.chunk_of_abyssalnite,
					new ItemStack(items.crystal, 1, 12), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.dreadium_ingot,
					new ItemStack(items.crystal, 1, 14), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(Items.IRON_INGOT,
					new ItemStack(items.crystal, 1, 0), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(Items.GOLD_INGOT,
					new ItemStack(items.crystal, 1, 1), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(Items.REDSTONE,
					new ItemStack(items.crystal, 1, 11), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.charcoal,
					new ItemStack(items.crystal, 1, 3), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(
					new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE),
					new ItemStack(items.crystal, 1, 3), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreAbyssalnite", "crystalAbyssalnite", 2,
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreCoralium", "crystalCoralium", 2, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreIron", "crystalIron", 2, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreGold", "crystalGold", 2, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotTin", "crystalTin", 1, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreTin", "crystalTin", 2, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotCopper", "crystalCopper", 1, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreCopper", "crystalCopper", 2, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotAluminum", "crystalAluminium", 1, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotAluminium", "crystalAluminium", 1,
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreAluminum", "crystalAluminium", 2, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("blockCopper", "crystalClusterCopper", 1,
					0.9F);
			AbyssalCraftAPI.addSingleCrystallization("blockTin", "crystalClusterTin", 1, 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.GOLD_BLOCK,
					new ItemStack(blocks.gold_crystal_cluster.getBlock()), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.IRON_BLOCK,
					new ItemStack(blocks.iron_crystal_cluster.getBlock()), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(
					new ItemStack(blocks.block_of_abyssalnite.getBlock()),
					new ItemStack(blocks.abyssalnite_crystal_cluster.getBlock()), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(
					new ItemStack(blocks.block_of_refined_coralium.getBlock()),
					new ItemStack(blocks.coralium_crystal_cluster.getBlock()), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(
					new ItemStack(blocks.block_of_dreadium.getBlock()),
					new ItemStack(blocks.dreadium_crystal_cluster.getBlock()), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.COAL_ORE,
					new ItemStack(items.crystal, 2, 3), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.COAL_BLOCK,
					new ItemStack(blocks.carbon_crystal_cluster.getBlock()), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.REDSTONE_ORE,
					new ItemStack(items.crystal, 2, 11), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.REDSTONE_BLOCK,
					new ItemStack(blocks.redstone_crystal_cluster.getBlock()), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization("ingotZinc", "crystalZinc", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreZinc", "crystalZinc", 2, 0.1F);
			AbyssalCraftAPI.addCrystallization(blocks.dreaded_abyssalnite_ore.getBlock(),
					new ItemStack(items.crystal, 2, 12), new ItemStack(items.crystal, 2, 14),
					0.2F);
			AbyssalCraftAPI.addCrystallization("dustSaltpeter", "crystalPotassium", 1,
					"crystalNitrate", 1, 0.1F);
			AbyssalCraftAPI.addCrystallization("oreSaltpeter", "crystalPotassium", 2,
					"crystalNitrate", 2, 0.1F);
			AbyssalCraftAPI.addCrystallization("ingotBronze", "crystalCopper", 3, "crystalTin", 1,
					0.4F);
			AbyssalCraftAPI.addCrystallization(new ItemStack(Items.DYE, 1, 4),
					new ItemStack(items.crystal, 6, 21), new ItemStack(items.crystal, 4, 2),
					0.15F);
			AbyssalCraftAPI.addCrystallization(Blocks.LAPIS_ORE,
					new ItemStack(items.crystal, 12, 21), new ItemStack(items.crystal, 8, 2),
					0.15F);
			AbyssalCraftAPI.addCrystallization(Blocks.LAPIS_BLOCK,
					new ItemStack(blocks.silica_crystal_cluster.getBlock(), 6),
					new ItemStack(blocks.sulfur_crystal_cluster.getBlock(), 4), 1.0F);
			AbyssalCraftAPI.addCrystallization("ingotBrass", "crystalCopper", 3, "crystalZinc", 2,
					0.5F);
			AbyssalCraftAPI.addCrystallization("oreBrass", "crystalCopper", 6, "crystalZinc", 4,
					0.5F);
			AbyssalCraftAPI.addSingleCrystallization(Items.GOLD_NUGGET,
					new ItemStack(items.crystal_shard, 1, 1), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(new ItemStack(items.ingot_nugget, 1, 0),
					new ItemStack(items.crystal_shard, 1, 12), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(new ItemStack(items.ingot_nugget, 1, 1),
					new ItemStack(items.crystal_shard, 1, 13), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(new ItemStack(items.ingot_nugget, 1, 2),
					new ItemStack(items.crystal_shard, 1, 14), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetIron", "crystalShardIron", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetCopper", "crystalShardCopper", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetTin", "crystalShardTin", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetAluminium", "crystalShardAluminium",
					0.1F);
			AbyssalCraftAPI.addCrystallization("nuggetBronze", "crystalShardCopper", 3,
					"crystalShardTin", 1, 0.1F);
			AbyssalCraftAPI.addCrystallization("nuggetBrass", "crystalShardCopper", 3,
					"crystalShardZinc", 2, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.coralium_gem,
					new ItemStack(items.crystal_shard, 1, 13), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetZinc", "crystalShardZinc", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetMagnesium", "crystalShardMagnesium",
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotMagnesium", "crystalMagnesium", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetCalcium", "crystalShardCalcium", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotCalcium", "crystalCalcium", 0.1F);

			//Crystallization for dusts
			AbyssalCraftAPI.addSingleCrystallization("dustIron", "crystalIron", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustGold", "crystalGold", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustTin", "crystalTin", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustCopper", "crystalCopper", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustCoal", "crystalCarbon", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustAluminium", "crystalAluminium", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustSulfur", "crystalSulfur", 0.1F);
			AbyssalCraftAPI.addCrystallization("dustBronze", "crystalCopper", 3, "crystalTin", 1,
					0.1F);
			AbyssalCraftAPI.addCrystallization("dustBrass", "crystalCopper", 3, "crystalZinc", 2,
					0.1F);
		} else {
			AbyssalCraftAPI.addSingleCrystallization(items.refined_coralium_ingot,
					new ItemStack(items.crystal_shard, 4, 13), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.chunk_of_coralium,
					new ItemStack(items.crystal_shard, 4, 13), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(blocks.liquified_coralium_ore.getBlock(),
					new ItemStack(items.crystal_shard, 4, 13), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.abyssalnite_ingot,
					new ItemStack(items.crystal_shard, 4, 12), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.chunk_of_abyssalnite,
					new ItemStack(items.crystal_shard, 4, 12), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.dreadium_ingot,
					new ItemStack(items.crystal_shard, 4, 14), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(Items.IRON_INGOT,
					new ItemStack(items.crystal_shard, 4, 0), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(Items.GOLD_INGOT,
					new ItemStack(items.crystal_shard, 4, 1), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(Items.REDSTONE,
					new ItemStack(items.crystal_shard, 4, 11), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.charcoal,
					new ItemStack(items.crystal_shard, 4, 3), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(
					new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE),
					new ItemStack(items.crystal_shard, 4, 3), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreAbyssalnite", "crystalShardAbyssalnite"
					, 4,
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreCoralium", "crystalShardCoralium", 4,
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreIron", "crystalShardIron", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreGold", "crystalShardGold", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotTin", "crystalShardTin", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreTin", "crystalShardTin", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotCopper", "crystalShardCopper", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreCopper", "crystalShardCopper", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotAluminum", "crystalShardAluminium", 4,
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotAluminium", "crystalShardAluminium", 4,
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreAluminum", "crystalShardAluminium", 4,
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("blockCopper", "crystalCopper", 4, 0.9F);
			AbyssalCraftAPI.addSingleCrystallization("blockTin", "crystalTin", 4, 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.GOLD_BLOCK,
					new ItemStack(items.crystal, 4, 1), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.IRON_BLOCK,
					new ItemStack(items.crystal, 4, 0), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(
					new ItemStack(blocks.block_of_abyssalnite.getBlock()),
					new ItemStack(items.crystal, 4, 12), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(
					new ItemStack(blocks.block_of_refined_coralium.getBlock()),
					new ItemStack(items.crystal, 4, 13), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(
					new ItemStack(blocks.block_of_dreadium.getBlock()),
					new ItemStack(items.crystal, 4, 14), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.COAL_ORE,
					new ItemStack(items.crystal_shard, 4, 3), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.COAL_BLOCK,
					new ItemStack(items.crystal, 4, 3), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.REDSTONE_ORE,
					new ItemStack(items.crystal_shard, 4, 11), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(Blocks.REDSTONE_BLOCK,
					new ItemStack(items.crystal, 4, 11), 0.9F);
			AbyssalCraftAPI.addSingleCrystallization("ingotZinc", "crystalZinc", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("oreZinc", "crystalZinc", 0.1F);
			AbyssalCraftAPI.addCrystallization(blocks.dreaded_abyssalnite_ore.getBlock(),
					new ItemStack(items.crystal_shard, 4, 12),
					new ItemStack(items.crystal_shard, 4, 14), 0.2F);
			AbyssalCraftAPI.addCrystallization("dustSaltpeter", "crystalShardPotassium", 4,
					"crystalShardNitrate", 4, 0.1F);
			AbyssalCraftAPI.addCrystallization("oreSaltpeter", "crystalShardPotassium", 4,
					"crystalShardNitrate", 4, 0.1F);
			AbyssalCraftAPI.addCrystallization("ingotBronze", "crystalShardCopper", 3,
					"crystalShardTin", 1, 0.4F);
			AbyssalCraftAPI.addCrystallization(new ItemStack(Items.DYE, 1, 4),
					new ItemStack(items.crystal, 2, 21), new ItemStack(items.crystal_shard, 16, 2),
					0.15F);
			AbyssalCraftAPI.addCrystallization(Blocks.LAPIS_ORE,
					new ItemStack(items.crystal_shard, 24, 21),
					new ItemStack(items.crystal_shard, 16, 2), 0.15F);
			AbyssalCraftAPI.addCrystallization(Blocks.LAPIS_BLOCK,
					new ItemStack(items.crystal, 24, 21), new ItemStack(items.crystal, 16, 2),
					1.0F);
			AbyssalCraftAPI.addCrystallization("ingotBrass", "crystalShardCopper", 12,
					"crystalShardZinc", 8, 0.5F);
			AbyssalCraftAPI.addCrystallization("oreBrass", "crystalShardCopper", 12,
					"crystalShardZinc", 8, 0.5F);
			AbyssalCraftAPI.addSingleCrystallization(Items.GOLD_NUGGET,
					new ItemStack(items.crystal_shard, 1, 1), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(new ItemStack(items.ingot_nugget, 1, 0),
					new ItemStack(items.crystal_shard, 1, 12), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(new ItemStack(items.ingot_nugget, 1, 1),
					new ItemStack(items.crystal_shard, 1, 13), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(new ItemStack(items.ingot_nugget, 1, 2),
					new ItemStack(items.crystal_shard, 1, 14), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetIron", "crystalShardIron", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetCopper", "crystalShardCopper", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetTin", "crystalShardTin", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetAluminium", "crystalShardAluminium",
					0.1F);
			AbyssalCraftAPI.addCrystallization("nuggetBronze", "crystalShardCopper", 3,
					"crystalShardTin", 1, 0.1F);
			AbyssalCraftAPI.addCrystallization("nuggetBrass", "crystalShardCopper", 3,
					"crystalShardZinc", 2, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization(items.coralium_gem,
					new ItemStack(items.crystal_shard, 1, 13), 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetZinc", "crystalShardZinc", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetMagnesium", "crystalShardMagnesium",
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotMagnesium", "crystalShardMagnesium", 4,
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("nuggetCalcium", "crystalShardCalcium", 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("ingotCalcium", "crystalShardCalcium", 4,
					0.1F);

			//Crystallization for dusts
			AbyssalCraftAPI.addSingleCrystallization("dustIron", "crystalShardIron", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustGold", "crystalShardGold", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustTin", "crystalShardTin", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustCopper", "crystalShardCopper", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustCoal", "crystalShardCarbon", 4, 0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustAluminium", "crystalShardAluminium", 4,
					0.1F);
			AbyssalCraftAPI.addSingleCrystallization("dustSulfur", "crystalShardSulfur", 4, 0.1F);
			AbyssalCraftAPI.addCrystallization("dustBronze", "crystalShardCopper", 12,
					"crystalShardTin", 4, 0.1F);
			AbyssalCraftAPI.addCrystallization("dustBrass", "crystalShardCopper", 12,
					"crystalShardZinc", 8, 0.1F);
		}
	}

	private static void addTransmutation() {
		ACItems items = ACItems.getInstance();
		ACBlocks blocks = ACBlocks.getInstance();
		AbyssalCraftAPI.addTransmutation(new ItemStack(blocks.darkstone.getBlock()),
				new ItemStack(Blocks.STONE, 1), 0.0F);
		AbyssalCraftAPI.addTransmutation(items.dreaded_shard_of_abyssalnite,
				new ItemStack(items.dreadium_ingot, 1), 0.2F);
		AbyssalCraftAPI.addTransmutation(Blocks.STONE, new ItemStack(blocks.darkstone.getBlock()),
				0.0F);
		AbyssalCraftAPI.addTransmutation(Blocks.STONEBRICK,
				new ItemStack(blocks.darkstone_brick.getBlock(), 1, 0), 0.0F);
		AbyssalCraftAPI.addTransmutation(new ItemStack(blocks.darkstone_brick.getBlock(), 1, 0),
				new ItemStack(Blocks.STONEBRICK), 0.0F);
		AbyssalCraftAPI.addTransmutation(Blocks.COBBLESTONE,
				new ItemStack(blocks.darkstone_cobblestone.getBlock()), 0.0F);
		AbyssalCraftAPI.addTransmutation(new ItemStack(blocks.darkstone_cobblestone.getBlock()),
				new ItemStack(Blocks.COBBLESTONE, 1), 0.0F);
		AbyssalCraftAPI.addTransmutation(
				new ItemStack(blocks.coralium_stone_brick.getBlock(), 1, 0),
				new ItemStack(items.coralium_brick, 4), 0.0F);
		AbyssalCraftAPI.addTransmutation(items.coralium_brick,
				new ItemStack(blocks.coralium_stone.getBlock()), 0.0F);
		AbyssalCraftAPI.addTransmutation(items.dense_carbon_cluster, new ItemStack(Items.DIAMOND),
				0.5F);
		AbyssalCraftAPI.addTransmutation(items.dread_plagued_gateway_key,
				new ItemStack(items.rlyehian_gateway_key), 1.0F);

		AbyssalCraftAPI.addTransmutation(Items.LAVA_BUCKET,
				new ItemStack(blocks.solid_lava.getBlock()), 0.0F);
		AbyssalCraftAPI.addTransmutation(Blocks.END_STONE,
				new ItemStack(blocks.ethaxium.getBlock()), 0.0F);
		AbyssalCraftAPI.addTransmutation(new ItemStack(blocks.ethaxium.getBlock()),
				new ItemStack(Blocks.END_STONE), 0.0F);
		AbyssalCraftAPI.addTransmutation(new ItemStack(blocks.ethaxium_brick.getBlock(), 1, 0),
				new ItemStack(blocks.ethaxium.getBlock()), 0.0F);
		AbyssalCraftAPI.addTransmutation(items.anti_beef, new ItemStack(Items.COOKED_BEEF), 0.3F);
		AbyssalCraftAPI.addTransmutation(items.anti_pork, new ItemStack(Items.COOKED_PORKCHOP),
				0.3F);
		AbyssalCraftAPI.addTransmutation(items.anti_chicken, new ItemStack(Items.COOKED_CHICKEN),
				0.3F);
		AbyssalCraftAPI.addTransmutation(items.anti_bone, new ItemStack(Items.BONE, 2), 0.3F);
		AbyssalCraftAPI.addTransmutation(items.rotten_anti_flesh,
				new ItemStack(Items.ROTTEN_FLESH, 2), 0.3F);
		AbyssalCraftAPI.addTransmutation(items.anti_spider_eye, new ItemStack(Items.SPIDER_EYE, 2),
				0.3F);
		AbyssalCraftAPI.addTransmutation(items.anti_plagued_flesh,
				new ItemStack(items.coralium_plagued_flesh, 2), 0.3F);
		AbyssalCraftAPI.addTransmutation(items.anti_plagued_flesh_on_a_bone,
				new ItemStack(items.coralium_plagued_flesh_on_a_bone, 2), 0.3F);

		if (ACConfig.crystal_rework) {
			AbyssalCraftAPI.addTransmutation(
					new ItemStack(blocks.abyssalnite_crystal_cluster.getBlock()),
					new ItemStack(items.abyssalnite_ingot, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(
					new ItemStack(blocks.coralium_crystal_cluster.getBlock()),
					new ItemStack(items.refined_coralium_ingot, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(
					new ItemStack(blocks.dreadium_crystal_cluster.getBlock()),
					new ItemStack(items.dreadium_ingot, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(blocks.iron_crystal_cluster.getBlock()),
					new ItemStack(Items.IRON_INGOT, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(blocks.gold_crystal_cluster.getBlock()),
					new ItemStack(Items.GOLD_INGOT, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(
					new ItemStack(blocks.sulfur_crystal_cluster.getBlock()),
					new ItemStack(items.sulfur, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(blocks.blaze_crystal_cluster.getBlock()),
					new ItemStack(Items.BLAZE_POWDER, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(
					new ItemStack(blocks.redstone_crystal_cluster.getBlock()),
					new ItemStack(Items.REDSTONE, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(
					new ItemStack(blocks.methane_crystal_cluster.getBlock()),
					new ItemStack(items.methane, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(blocks.tin_crystal_cluster.getBlock()),
					new ItemStack(items.tin_ingot, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(
					new ItemStack(blocks.copper_crystal_cluster.getBlock()),
					new ItemStack(items.copper_ingot, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalClusterAluminium", "ingotAluminum", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalClusterAluminium", "ingotAluminium", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalClusterZinc", "ingotZinc", 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 12),
					new ItemStack(items.ingot_nugget, 1, 0), 0.1F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 13),
					new ItemStack(items.ingot_nugget, 1, 1), 0.1F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 14),
					new ItemStack(items.ingot_nugget, 1, 2), 0.1F);
			AbyssalCraftAPI.addTransmutation("crystalAluminium", "nuggetAluminum", 0.1F);
			AbyssalCraftAPI.addTransmutation("crystalAluminium", "nuggetAluminium", 0.1F);
			AbyssalCraftAPI.addTransmutation("crystalIron", "nuggetIron", 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 1),
					new ItemStack(Items.GOLD_NUGGET), 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalCopper", "nuggetCopper", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalTin", "nuggetTin", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalZinc", "nuggetZinc", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalMagnesium", "nuggetMagnesium", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalClusterMagnesium", "ingotMagnesium", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalCalcium", "nuggetCalcium", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalClusterCalcium", "ingotCalcium", 0.2F);
		} else {
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 12),
					new ItemStack(items.abyssalnite_ingot, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 13),
					new ItemStack(items.refined_coralium_ingot, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 14),
					new ItemStack(items.dreadium_ingot, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 0),
					new ItemStack(Items.IRON_INGOT, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 1),
					new ItemStack(Items.GOLD_INGOT, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 2),
					new ItemStack(items.sulfur, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 15),
					new ItemStack(Items.BLAZE_POWDER, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 11),
					new ItemStack(Items.REDSTONE, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 5),
					new ItemStack(items.crystal, 1, 5), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 4),
					new ItemStack(items.crystal, 1, 4), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 6),
					new ItemStack(items.crystal, 1, 6), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 10),
					new ItemStack(items.methane, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 16),
					new ItemStack(items.tin_ingot, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal, 1, 17),
					new ItemStack(items.copper_ingot, 1), 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalAluminium", "ingotAluminum", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalAluminium", "ingotAluminium", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalZinc", "ingotZinc", 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal_shard, 1, 12),
					new ItemStack(items.ingot_nugget, 1, 0), 0.1F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal_shard, 1, 13),
					new ItemStack(items.ingot_nugget, 1, 1), 0.1F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal_shard, 1, 14),
					new ItemStack(items.ingot_nugget, 1, 2), 0.1F);
			AbyssalCraftAPI.addTransmutation("crystalShardAluminium", "nuggetAluminum", 0.1F);
			AbyssalCraftAPI.addTransmutation("crystalShardAluminium", "nuggetAluminium", 0.1F);
			AbyssalCraftAPI.addTransmutation("crystalShardIron", "nuggetIron", 0.2F);
			AbyssalCraftAPI.addTransmutation(new ItemStack(items.crystal_shard, 1, 1),
					new ItemStack(Items.GOLD_NUGGET), 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalShardCopper", "nuggetCopper", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalShardTin", "nuggetTin", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalShardZinc", "nuggetZinc", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalShardMagnesium", "nuggetMagnesium", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalMagnesium", "ingotMagnesium", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalShardCalcium", "nuggetCalcium", 0.2F);
			AbyssalCraftAPI.addTransmutation("crystalCalcium", "ingotCalcium", 0.2F);
		}
	}

	private static void addEngraving() {
		ACItems items = ACItems.getInstance();
		AbyssalCraftAPI.addCoin(items.coin);
		AbyssalCraftAPI.addCoin(items.cthulhu_engraved_coin);
		AbyssalCraftAPI.addCoin(items.elder_engraved_coin);
		AbyssalCraftAPI.addCoin(items.jzahar_engraved_coin);
		AbyssalCraftAPI.addCoin(items.hastur_engraved_coin);
		AbyssalCraftAPI.addCoin(items.azathoth_engraved_coin);
		AbyssalCraftAPI.addCoin(items.nyarlathotep_engraved_coin);
		AbyssalCraftAPI.addCoin(items.yog_sothoth_engraved_coin);
		AbyssalCraftAPI.addCoin(items.shub_niggurath_engraved_coin);
		AbyssalCraftAPI.addEngraving(items.coin, (ItemEngraving) items.blank_engraving, 0.0F);
		AbyssalCraftAPI.addEngraving(items.cthulhu_engraved_coin,
				(ItemEngraving) items.cthulhu_engraving, 0.5F);
		AbyssalCraftAPI.addEngraving(items.elder_engraved_coin,
				(ItemEngraving) items.elder_engraving, 0.5F);
		AbyssalCraftAPI.addEngraving(items.jzahar_engraved_coin,
				(ItemEngraving) items.jzahar_engraving, 0.5F);
		AbyssalCraftAPI.addEngraving(items.hastur_engraved_coin,
				(ItemEngraving) items.hastur_engraving, 0.5F);
		AbyssalCraftAPI.addEngraving(items.azathoth_engraved_coin,
				(ItemEngraving) items.azathoth_engraving, 0.5F);
		AbyssalCraftAPI.addEngraving(items.nyarlathotep_engraved_coin,
				(ItemEngraving) items.nyarlathotep_engraving, 0.5F);
		AbyssalCraftAPI.addEngraving(items.yog_sothoth_engraved_coin,
				(ItemEngraving) items.yog_sothoth_engraving, 0.5F);
		AbyssalCraftAPI.addEngraving(items.shub_niggurath_engraved_coin,
				(ItemEngraving) items.shub_niggurath_engraving, 0.5F);
	}

	private static void addMaterialization() {
		ACItems items = ACItems.getInstance();
		ACBlocks blocks = ACBlocks.getInstance();
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.BONE),
				new ItemStack(items.crystal, 1, 25));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.ROTTEN_FLESH),
				new ItemStack(items.crystal, 1, 7));
		AbyssalCraftAPI.addMaterialization(new ItemStack(items.coralium_plagued_flesh),
				new ItemStack(items.crystal, 1, 7), new ItemStack(items.crystal, 1, 13));
		AbyssalCraftAPI.addMaterialization(new ItemStack(items.coralium_plagued_flesh_on_a_bone),
				new ItemStack(items.crystal, 1, 7), new ItemStack(items.crystal, 1, 25),
				new ItemStack(items.crystal, 1, 13));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.COAL, 1, 0),
				new ItemStack(items.crystal, 1, 3));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.COAL, 1, 1),
				new ItemStack(items.crystal, 1, 3));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.IRON_INGOT),
				new ItemStack(items.crystal, 1, 0));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.GOLD_INGOT),
				new ItemStack(items.crystal, 1, 1));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.BLAZE_POWDER),
				new ItemStack(items.crystal, 1, 15));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.BLAZE_ROD),
				new ItemStack(items.crystal, 2, 15));
		AbyssalCraftAPI.addMaterialization(new ItemStack(items.abyssalnite_ingot),
				new ItemStack(items.crystal, 1, 12));
		AbyssalCraftAPI.addMaterialization(new ItemStack(items.refined_coralium_ingot),
				new ItemStack(items.crystal, 1, 13));
		AbyssalCraftAPI.addMaterialization(new ItemStack(items.dreadium_ingot),
				new ItemStack(items.crystal, 1, 14));
		AbyssalCraftAPI.addMaterialization("ingotTin", new ItemStack(items.crystal, 1, 16));
		AbyssalCraftAPI.addMaterialization("ingotCopper", new ItemStack(items.crystal, 1, 17));
		AbyssalCraftAPI.addMaterialization(new ItemStack(items.methane),
				new ItemStack(items.crystal, 1, 10));
		AbyssalCraftAPI.addMaterialization(new ItemStack(items.nitre),
				new ItemStack(items.crystal, 1, 8), new ItemStack(items.crystal, 1, 9));
		AbyssalCraftAPI.addMaterialization(new ItemStack(items.sulfur),
				new ItemStack(items.crystal, 1, 2));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.REDSTONE),
				new ItemStack(items.crystal, 1, 11));
		AbyssalCraftAPI.addMaterialization(new ItemStack(items.dread_fragment),
				new ItemStack(items.crystal, 2, 7), new ItemStack(items.crystal, 1, 14));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.STONE),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23));
		AbyssalCraftAPI.addMaterialization("oreGold", new ItemStack(items.crystal, 1, 21),
				new ItemStack(items.crystal, 1, 23), new ItemStack(items.crystal, 1, 1));
		AbyssalCraftAPI.addMaterialization("oreIron", new ItemStack(items.crystal, 1, 21),
				new ItemStack(items.crystal, 1, 23), new ItemStack(items.crystal, 1, 0));
		AbyssalCraftAPI.addMaterialization("oreCoal", new ItemStack(items.crystal, 1, 21),
				new ItemStack(items.crystal, 1, 23), new ItemStack(items.crystal, 1, 3));
		AbyssalCraftAPI.addMaterialization("oreRedstone", new ItemStack(items.crystal, 1, 21),
				new ItemStack(items.crystal, 1, 23), new ItemStack(items.crystal, 1, 11));
		AbyssalCraftAPI.addMaterialization(new ItemStack(blocks.coralium_ore.getBlock()),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23),
				new ItemStack(items.crystal, 1, 13));
		AbyssalCraftAPI.addMaterialization(new ItemStack(blocks.abyssalnite_ore.getBlock()),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23),
				new ItemStack(items.crystal, 1, 12));
		AbyssalCraftAPI.addMaterialization(new ItemStack(blocks.coralium_infused_stone.getBlock()),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23),
				new ItemStack(items.crystal, 2, 13));
		AbyssalCraftAPI.addMaterialization(new ItemStack(blocks.nitre_ore.getBlock()),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23),
				new ItemStack(items.crystal, 1, 8), new ItemStack(items.crystal, 1, 9));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.SPONGE),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 3));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.ELYTRA),
				new ItemStack(items.crystal, 8, 3), new ItemStack(items.crystal, 13, 5),
				new ItemStack(items.crystal, 5, 4), new ItemStack(items.crystal, 1, 6));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.PRISMARINE_SHARD),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 27));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.PRISMARINE_CRYSTALS),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 27));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.PRISMARINE, 1, 0),
				new ItemStack(items.crystal, 4, 21), new ItemStack(items.crystal, 4, 27));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.PRISMARINE, 1, 1),
				new ItemStack(items.crystal, 9, 21), new ItemStack(items.crystal, 9, 27));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.PRISMARINE, 1, 2),
				new ItemStack(items.crystal, 8, 21), new ItemStack(items.crystal, 8, 27),
				new ItemStack(items.crystal, 1, 3));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.SEA_LANTERN),
				new ItemStack(items.crystal, 9, 21), new ItemStack(items.crystal, 9, 27));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.EGG),
				new ItemStack(items.crystal, 1, 25), new ItemStack(items.crystal, 1, 7));
		AbyssalCraftAPI.addMaterialization(new ItemStack(items.charcoal),
				new ItemStack(items.crystal, 1, 3), new ItemStack(items.crystal, 1, 14));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.COAL_BLOCK),
				new ItemStack(items.crystal, 9, 3));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.REDSTONE_BLOCK),
				new ItemStack(items.crystal, 9, 11));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.DIAMOND),
				new ItemStack(items.crystal, 64, 3));
		AbyssalCraftAPI.addMaterialization("oreDiamond", new ItemStack(items.crystal, 1, 21),
				new ItemStack(items.crystal, 1, 23), new ItemStack(items.crystal, 64, 3));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.STONE, 1, 1),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.STONE, 1, 2),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.STONE, 1, 3),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.STONE, 1, 4),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.STONE, 1, 5),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.STONE, 1, 6),
				new ItemStack(items.crystal, 1, 21), new ItemStack(items.crystal, 1, 23));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.LEATHER),
				new ItemStack(items.crystal, 1, 7));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.RABBIT_HIDE),
				new ItemStack(items.crystal, 1, 7));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.RABBIT_FOOT),
				new ItemStack(items.crystal, 1, 7), new ItemStack(items.crystal, 1, 25));
		AbyssalCraftAPI.addMaterialization("logWood", new ItemStack(items.crystal, 6, 3),
				new ItemStack(items.crystal, 10, 5), new ItemStack(items.crystal, 5, 4));
		AbyssalCraftAPI.addMaterialization("plankWood", new ItemStack(items.crystal, 6, 3),
				new ItemStack(items.crystal, 10, 5), new ItemStack(items.crystal, 5, 4));
		AbyssalCraftAPI.addMaterialization("treeSapling", new ItemStack(items.crystal, 6, 3),
				new ItemStack(items.crystal, 10, 5), new ItemStack(items.crystal, 5, 4));
		AbyssalCraftAPI.addMaterialization("treeLeaves", new ItemStack(items.crystal, 6, 3),
				new ItemStack(items.crystal, 10, 5), new ItemStack(items.crystal, 5, 4));
		AbyssalCraftAPI.addMaterialization("vine", new ItemStack(items.crystal, 6, 3),
				new ItemStack(items.crystal, 10, 5), new ItemStack(items.crystal, 5, 4));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.DEADBUSH),
				new ItemStack(items.crystal, 6, 3), new ItemStack(items.crystal, 10, 5),
				new ItemStack(items.crystal, 5, 4));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Items.DYE, 1, 4),
				new ItemStack(items.crystal, 6, 21), new ItemStack(items.crystal, 4, 2));
		AbyssalCraftAPI.addMaterialization("oreLapis", new ItemStack(items.crystal, 7, 21),
				new ItemStack(items.crystal, 4, 2), new ItemStack(items.crystal, 1, 23));
		AbyssalCraftAPI.addMaterialization(new ItemStack(Blocks.LAPIS_BLOCK),
				new ItemStack(items.crystal, 54, 21), new ItemStack(items.crystal, 36, 2));
		if (OreDictionary.getOres("listAllmeatraw", false).size() > 1)
			AbyssalCraftAPI.addMaterialization("listAllmeatraw",
					new ItemStack(items.crystal, 14, 5), new ItemStack(items.crystal, 5, 3),
					new ItemStack(items.crystal, 6, 4), new ItemStack(items.crystal, 1, 6));
		else {
			AbyssalCraftAPI.addMaterialization(new ItemStack(Items.BEEF),
					new ItemStack(items.crystal, 14, 5), new ItemStack(items.crystal, 5, 3),
					new ItemStack(items.crystal, 6, 4), new ItemStack(items.crystal, 1, 6));
			AbyssalCraftAPI.addMaterialization(new ItemStack(Items.CHICKEN),
					new ItemStack(items.crystal, 14, 5), new ItemStack(items.crystal, 5, 3),
					new ItemStack(items.crystal, 6, 4), new ItemStack(items.crystal, 1, 6));
			AbyssalCraftAPI.addMaterialization(new ItemStack(Items.PORKCHOP),
					new ItemStack(items.crystal, 14, 5), new ItemStack(items.crystal, 5, 3),
					new ItemStack(items.crystal, 6, 4), new ItemStack(items.crystal, 1, 6));
			AbyssalCraftAPI.addMaterialization(new ItemStack(Items.MUTTON),
					new ItemStack(items.crystal, 14, 5), new ItemStack(items.crystal, 5, 3),
					new ItemStack(items.crystal, 6, 4), new ItemStack(items.crystal, 1, 6));
			AbyssalCraftAPI.addMaterialization(new ItemStack(Items.RABBIT),
					new ItemStack(items.crystal, 14, 5), new ItemStack(items.crystal, 5, 3),
					new ItemStack(items.crystal, 6, 4), new ItemStack(items.crystal, 1, 6));
			AbyssalCraftAPI.addMaterialization(new ItemStack(items.generic_meat),
					new ItemStack(items.crystal, 14, 5), new ItemStack(items.crystal, 5, 3),
					new ItemStack(items.crystal, 6, 4), new ItemStack(items.crystal, 1, 6));
		}
	}

	private static void addRitualRecipes() {
		ACItems items = ACItems.getInstance();
		ACBlocks blocks = ACBlocks.getInstance();
		//Overworld progression
		Object[] tgofferings = new Object[]{
				new ItemStack(Items.DIAMOND),
				new ItemStack(Items.BLAZE_POWDER),
				new ItemStack(Items.ENDER_PEARL),
				new ItemStack(Items.BLAZE_POWDER),
				new ItemStack(Items.DIAMOND),
				new ItemStack(Items.BLAZE_POWDER),
				new ItemStack(Items.ENDER_PEARL),
				new ItemStack(Items.BLAZE_POWDER)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("transmutationGem", 0, 300F,
						new ItemStack(items.transmutation_gem),
						new ItemStack(items.coralium_pearl),
						tgofferings));
		Object[] ocofferings = new Object[]{
				new ItemStack(Items.REDSTONE),
				new ItemStack(items.shard_of_oblivion),
				new ItemStack(Items.REDSTONE),
				new ItemStack(items.shard_of_oblivion),
				new ItemStack(Items.REDSTONE),
				new ItemStack(items.shard_of_oblivion),
				new ItemStack(Items.REDSTONE),
				new ItemStack(items.shard_of_oblivion)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("oblivionCatalyst", 0, OreDictionary.WILDCARD_VALUE,
						5000F, true, new ItemStack(items.oblivion_catalyst),
						new ItemStack(Items.ENDER_EYE), ocofferings));
		RitualRegistry.instance().registerRitual(new NecronomiconPortalRitual());

		//Abyssal Wasteland progression
		Object[] asorahofferings = new Object[]{
				new ItemStack(Items.GOLD_INGOT),
				new ItemStack(items.transmutation_gem, 1, OreDictionary.WILDCARD_VALUE),
				new ItemStack(Items.GOLD_INGOT),
				items.liquid_coralium_bucket_stack,
				new ItemStack(Items.GOLD_INGOT),
				new ItemStack(Blocks.ENCHANTING_TABLE),
				new ItemStack(Items.GOLD_INGOT)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconSummonRitual("summonAsorah", 1, ACLib.abyssal_wasteland_id, 1000F,
						EntityDragonBoss.class, asorahofferings).setNBTSensitive());
		Object[] gk2offerings = new Object[]{
				new ItemStack(items.transmutation_gem, 1, OreDictionary.WILDCARD_VALUE),
				null,
				new ItemStack(blocks.dreadlands_infused_powerstone.getBlock()),
				null,
				new ItemStack(items.eye_of_the_abyss)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("asorahGatewayKey", 1, ACLib.abyssal_wasteland_id,
						10000F, new ItemStack(items.dreaded_gateway_key),
						new ItemStack(items.gateway_key), gk2offerings));

		//Dreadlands Progression
		Object[] dreadaltarbofferings = new Object[]{
				items.dread_cloth,
				Items.BONE,
				items.dreadium_ingot,
				blocks.dreadstone.getBlock(),
				items.dreaded_gateway_key,
				blocks.dreadstone.getBlock(),
				items.dreadium_ingot,
				Items.BONE
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconCreationRitual("altarOfChagarothBottom", 2, ACLib.dreadlands_id,
						20000F, true, new ItemStack(blocks.chagaroth_altar_bottom.getBlock()),
						dreadaltarbofferings));
		Object[] dreadaltartofferings = new Object[]{
				Items.BUCKET,
				"stickWood",
				items.dread_cloth,
				items.dreadium_ingot,
				items.dread_cloth,
				items.dreadium_ingot,
				items.dread_cloth,
				"stickWood"
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconCreationRitual("altarOfChagarothTop", 2, ACLib.dreadlands_id,
						20000F, true, new ItemStack(blocks.chagaroth_altar_top.getBlock()),
						dreadaltartofferings));
		Object[] cageofferings = new Object[]{
				Blocks.IRON_BARS,
				Blocks.IRON_BARS,
				Blocks.IRON_BARS,
				Blocks.IRON_BARS,
				Blocks.IRON_BARS,
				Blocks.IRON_BARS,
				Blocks.IRON_BARS,
				Blocks.IRON_BARS
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("interdimensionalcage", 2, 1000F,
						new ItemStack(items.interdimensional_cage), items.shard_of_oblivion,
						cageofferings));

		//Omothol progression
		RitualRegistry.instance().registerRitual(new NecronomiconRespawnJzaharRitual());

		//Everything else
		Object[] depthsofferings = new Object[]{
				new ItemStack(items.coralium_gem_cluster_9),
				new ItemStack(items.coralium_gem_cluster_9),
				items.liquid_coralium_bucket_stack,
				new ItemStack(Blocks.VINE),
				new ItemStack(Blocks.WATERLILY),
				new ItemStack(items.transmutation_gem, 1, OreDictionary.WILDCARD_VALUE),
				new ItemStack(items.coralium_plagued_flesh)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("depthsHelmet", 1, ACLib.abyssal_wasteland_id, 300F,
						new ItemStack(items.depths_helmet),
						new ItemStack(items.refined_coralium_helmet),
						depthsofferings).setNBTSensitive());
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("depthsChestplate", 1, ACLib.abyssal_wasteland_id,
						300F, new ItemStack(items.depths_chestplate),
						new ItemStack(items.refined_coralium_chestplate),
						depthsofferings).setNBTSensitive());
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("depthsLeggings", 1, ACLib.abyssal_wasteland_id,
						300F, new ItemStack(items.depths_leggings),
						new ItemStack(items.refined_coralium_leggings),
						depthsofferings).setNBTSensitive());
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("depthsBoots", 1, ACLib.abyssal_wasteland_id, 300F,
						new ItemStack(items.depths_boots),
						new ItemStack(items.refined_coralium_boots),
						depthsofferings).setNBTSensitive());
		RitualRegistry.instance().registerRitual(new NecronomiconBreedingRitual());
		Object[] sacthothofferings = new Object[]{
				new ItemStack(items.oblivion_catalyst),
				new ItemStack(Blocks.OBSIDIAN),
				items.liquid_coralium_bucket_stack,
				new ItemStack(Blocks.OBSIDIAN),
				items.liquid_antimatter_bucket_stack,
				new ItemStack(Blocks.OBSIDIAN),
				new ItemStack(blocks.odb_core.getBlock()),
				new ItemStack(Blocks.OBSIDIAN)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconSummonRitual("summonSacthoth", 1, 1000F, EntitySacthoth.class,
						sacthothofferings).setNBTSensitive());
		RitualRegistry.instance().registerRitual(new NecronomiconDreadSpawnRitual());
		Object[] coraoeofferings = new Object[]{
				new ItemStack(items.coralium_plagued_flesh),
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER),
				new ItemStack(items.coralium_plagued_flesh),
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER),
				new ItemStack(items.coralium_plagued_flesh),
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER),
				new ItemStack(items.coralium_plagued_flesh),
				new ItemStack(Items.GUNPOWDER)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconPotionAoERitual("corPotionAoE", 1, 300F,
						AbyssalCraftAPI.coralium_plague, coraoeofferings));
		Object[] dreaoeofferings = new Object[]{
				new ItemStack(items.dread_fragment),
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER),
				new ItemStack(items.dread_fragment),
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER),
				new ItemStack(items.dread_fragment),
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER),
				new ItemStack(items.dread_fragment),
				new ItemStack(Items.GUNPOWDER)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconPotionAoERitual("drePotionAoE", 2, 300F,
						AbyssalCraftAPI.dread_plague, dreaoeofferings));
		Object[] antiaoeofferings = new Object[]{
				new ItemStack(items.rotten_anti_flesh),
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER),
				new ItemStack(items.rotten_anti_flesh),
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER),
				new ItemStack(items.rotten_anti_flesh),
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
						PotionTypes.WATER),
				new ItemStack(items.rotten_anti_flesh),
				new ItemStack(Items.GUNPOWDER)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconPotionAoERitual("antiPotionAoE", 0, 300F,
						AbyssalCraftAPI.antimatter_potion, antiaoeofferings));
		RitualRegistry.instance().registerRitual(new NecronomiconResurrectionRitual());
		Object[] facebookofferings = new Object[]{
				new ItemStack(items.crystal, 1, 3),
				new ItemStack(items.crystal, 1, 5),
				new ItemStack(items.crystal, 1, 6),
				new ItemStack(items.crystal, 1, 4),
				new ItemStack(items.crystal, 1, 7),
				new ItemStack(items.crystal, 1, 2),
				Items.FEATHER,
				Items.DYE
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("facebook", 2, 2000F,
						new ItemStack(items.book_of_many_faces), new ItemStack(Items.BOOK),
						facebookofferings));
		RitualRegistry.instance().registerRitual(new NecronomiconCleansingRitual());
		RitualRegistry.instance().registerRitual(new NecronomiconCorruptionRitual());
		RitualRegistry.instance().registerRitual(new NecronomiconCuringRitual());
		RitualRegistry.instance().registerRitual(new NecronomiconMassEnchantRitual());
		Object[] spiritTabletOfferings = new Object[]{
				null,
				new ItemStack(items.configurator_shard, 1, OreDictionary.WILDCARD_VALUE),
				null,
				new ItemStack(items.configurator_shard, 1, OreDictionary.WILDCARD_VALUE),
				null,
				new ItemStack(items.configurator_shard, 1, OreDictionary.WILDCARD_VALUE),
				null,
				new ItemStack(items.configurator_shard, 1, OreDictionary.WILDCARD_VALUE)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("spiritTablet", 3, OreDictionary.WILDCARD_VALUE,
						5000F, new ItemStack(items.configurator),
						new ItemStack(items.life_crystal),
						spiritTabletOfferings));
		Object[] cthulhuofferings = new Object[]{
				new ItemStack(items.shoggoth_flesh, 1, 0),
				new ItemStack(items.shoggoth_flesh, 1, 0),
				new ItemStack(items.shoggoth_flesh, 1, 0),
				new ItemStack(items.shoggoth_flesh, 1, 0),
				new ItemStack(items.shoggoth_flesh, 1, 0),
				new ItemStack(items.essence, 1, 0),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 2)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("cthulhuStatue", 4, OreDictionary.WILDCARD_VALUE,
						20000F, true, new ItemStack(blocks.cthulhu_statue.getBlock()),
						new ItemStack(blocks.monolith_stone.getBlock()), cthulhuofferings));
		Object[] hasturofferings = new Object[]{
				new ItemStack(items.shoggoth_flesh, 1, 1),
				new ItemStack(items.shoggoth_flesh, 1, 1),
				new ItemStack(items.shoggoth_flesh, 1, 1),
				new ItemStack(items.shoggoth_flesh, 1, 1),
				new ItemStack(items.shoggoth_flesh, 1, 1),
				new ItemStack(items.essence, 1, 0),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 2)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("hasturStatue", 4, OreDictionary.WILDCARD_VALUE,
						20000F, true, new ItemStack(blocks.hastur_statue.getBlock()),
						new ItemStack(blocks.monolith_stone.getBlock()), hasturofferings));
		Object[] jzaharofferings = new Object[]{
				items.eldritch_scale,
				items.eldritch_scale,
				items.eldritch_scale,
				items.eldritch_scale,
				items.eldritch_scale,
				new ItemStack(items.essence, 1, 0),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 2)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("jzaharStatue", 4, OreDictionary.WILDCARD_VALUE,
						20000F, true, new ItemStack(blocks.jzahar_statue.getBlock()),
						new ItemStack(blocks.monolith_stone.getBlock()), jzaharofferings));
		Object[] azathothofferings = new Object[]{
				new ItemStack(items.shoggoth_flesh, 1, 0),
				new ItemStack(items.shoggoth_flesh, 1, 1),
				new ItemStack(items.shoggoth_flesh, 1, 2),
				new ItemStack(items.shoggoth_flesh, 1, 3),
				new ItemStack(items.shoggoth_flesh, 1, 4),
				new ItemStack(items.essence, 1, 0),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 2)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("azathothStatue", 4, OreDictionary.WILDCARD_VALUE,
						20000F, true, new ItemStack(blocks.azathoth_statue.getBlock()),
						new ItemStack(blocks.monolith_stone.getBlock()), azathothofferings));
		Object[] nyarlathotepofferings = new Object[]{
				new ItemStack(items.shoggoth_flesh, 1, 2),
				new ItemStack(items.shoggoth_flesh, 1, 2),
				new ItemStack(items.shoggoth_flesh, 1, 2),
				new ItemStack(items.shoggoth_flesh, 1, 2),
				new ItemStack(items.shoggoth_flesh, 1, 2),
				new ItemStack(items.essence, 1, 0),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 2)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("nyarlathotepStatue", 4,
						OreDictionary.WILDCARD_VALUE, 20000F, true,
						new ItemStack(blocks.nyarlathotep_statue.getBlock()),
						new ItemStack(blocks.monolith_stone.getBlock()), nyarlathotepofferings));
		Object[] yogsothothofferings = new Object[]{
				new ItemStack(items.shoggoth_flesh, 1, 3),
				new ItemStack(items.shoggoth_flesh, 1, 3),
				new ItemStack(items.shoggoth_flesh, 1, 3),
				new ItemStack(items.shoggoth_flesh, 1, 3),
				new ItemStack(items.shoggoth_flesh, 1, 3),
				new ItemStack(items.essence, 1, 0),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 2)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("yogsothothStatue", 4, OreDictionary.WILDCARD_VALUE,
						20000F, true, new ItemStack(blocks.yog_sothoth_statue.getBlock()),
						new ItemStack(blocks.monolith_stone.getBlock()), yogsothothofferings));
		Object[] shubniggurathofferings = new Object[]{
				new ItemStack(items.shoggoth_flesh, 1, 4),
				new ItemStack(items.shoggoth_flesh, 1, 4),
				new ItemStack(items.shoggoth_flesh, 1, 4),
				new ItemStack(items.shoggoth_flesh, 1, 4),
				new ItemStack(items.shoggoth_flesh, 1, 4),
				new ItemStack(items.essence, 1, 0),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 2)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("shubniggurathStatue", 4,
						OreDictionary.WILDCARD_VALUE, 20000F, true,
						new ItemStack(blocks.shub_niggurath_statue.getBlock()),
						new ItemStack(blocks.monolith_stone.getBlock()), shubniggurathofferings));
		Object[] psdlofferings = new Object[]{
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 1)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("powerStone", 4, ACLib.dreadlands_id, 5000F,
						new ItemStack(blocks.dreadlands_infused_powerstone.getBlock()),
						blocks.coralium_infused_stone.getBlock(), psdlofferings));
		Object[] ethofferings = new Object[]{
				items.ethaxium_brick,
				items.ethaxium_brick,
				items.life_crystal,
				items.ethaxium_brick,
				items.ethaxium_brick
		};
		RitualRegistry.instance().registerRitual(new NecronomiconPurgingRitual());
		RitualRegistry.instance().registerRitual(
				new NecronomiconCreationRitual("ethaxiumIngot", 3, ACLib.omothol_id, 1000F,
						new ItemStack(items.ethaxium_ingot), ethofferings));
		Object[] dreadofferings = new Object[]{
				new ItemStack(items.essence, 1, 1),
				items.dreaded_shard_of_abyssalnite,
				items.dreaded_shard_of_abyssalnite,
				items.dreaded_shard_of_abyssalnite,
				items.dreaded_shard_of_abyssalnite,
				items.dreaded_shard_of_abyssalnite,
				items.dreaded_shard_of_abyssalnite,
				items.dreaded_shard_of_abyssalnite
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("dreadHelmet", 2, ACLib.dreadlands_id, 500F,
						new ItemStack(items.dreaded_abyssalnite_helmet), items.abyssalnite_helmet,
						dreadofferings));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("dreadChestplate", 2, ACLib.dreadlands_id, 500F,
						new ItemStack(items.dreaded_abyssalnite_chestplate),
						items.abyssalnite_chestplate, dreadofferings));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("dreadLeggings", 2, ACLib.dreadlands_id, 500F,
						new ItemStack(items.dreaded_abyssalnite_leggings),
						items.abyssalnite_leggings, dreadofferings));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("dreadBoots", 2, ACLib.dreadlands_id, 500F,
						new ItemStack(items.dreaded_abyssalnite_boots), items.abyssalnite_boots,
						dreadofferings));
		Object[] rcoffers = new Object[]{
				items.shadow_fragment,
				Items.ARROW,
				items.shadow_fragment,
				Items.ARROW,
				items.shadow_fragment,
				Items.ARROW,
				items.shadow_fragment,
				Items.ARROW
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("rangeCharm", 0, 100F,
						new ItemStack(items.ritual_charm, 1, 1),
						new ItemStack(items.ritual_charm, 1, 0), rcoffers));
		Object[] dcoffers = new Object[]{
				items.shadow_fragment,
				Items.REDSTONE,
				items.shadow_fragment,
				Items.REDSTONE,
				items.shadow_fragment,
				Items.REDSTONE,
				items.shadow_fragment,
				Items.REDSTONE
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("durationCharm", 0, 100F,
						new ItemStack(items.ritual_charm, 1, 2),
						new ItemStack(items.ritual_charm, 1, 0), dcoffers));
		Object[] pcoffers = new Object[]{
				items.shadow_fragment,
				Items.GLOWSTONE_DUST,
				items.shadow_fragment,
				Items.GLOWSTONE_DUST,
				items.shadow_fragment,
				Items.GLOWSTONE_DUST,
				items.shadow_fragment,
				Items.GLOWSTONE_DUST
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("powerCharm", 0, 100F,
						new ItemStack(items.ritual_charm, 1, 3),
						new ItemStack(items.ritual_charm, 1, 0), pcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("crangeCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.cthulhu_charm, 1, 1),
						new ItemStack(items.cthulhu_charm, 1, 0), rcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("cdurationCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.cthulhu_charm, 1, 2),
						new ItemStack(items.cthulhu_charm, 1, 0), dcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("cpowerCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.cthulhu_charm, 1, 3),
						new ItemStack(items.cthulhu_charm, 1, 0), pcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("hrangeCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.hastur_charm, 1, 1),
						new ItemStack(items.hastur_charm, 1, 0), rcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("hdurationCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.hastur_charm, 1, 2),
						new ItemStack(items.hastur_charm, 1, 0), dcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("hpowerCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.hastur_charm, 1, 3),
						new ItemStack(items.hastur_charm, 1, 0), pcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("jrangeCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.jzahar_charm, 1, 1),
						new ItemStack(items.jzahar_charm, 1, 0), rcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("jdurationCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.jzahar_charm, 1, 2),
						new ItemStack(items.jzahar_charm, 1, 0), dcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("jpowerCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.jzahar_charm, 1, 3),
						new ItemStack(items.jzahar_charm, 1, 0), pcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("arangeCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.azathoth_charm, 1, 1),
						new ItemStack(items.azathoth_charm, 1, 0), rcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("adurationCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.azathoth_charm, 1, 2),
						new ItemStack(items.azathoth_charm, 1, 0), dcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("apowerCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.azathoth_charm, 1, 3),
						new ItemStack(items.azathoth_charm, 1, 0), pcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("nrangeCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.nyarlathotep_charm, 1, 1),
						new ItemStack(items.nyarlathotep_charm, 1, 0), rcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("ndurationCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.nyarlathotep_charm, 1, 2),
						new ItemStack(items.nyarlathotep_charm, 1, 0), dcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("npowerCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.nyarlathotep_charm, 1, 3),
						new ItemStack(items.nyarlathotep_charm, 1, 0), pcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("yrangeCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.yog_sothoth_charm, 1, 1),
						new ItemStack(items.yog_sothoth_charm, 1, 0), rcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("ydurationCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.yog_sothoth_charm, 1, 2),
						new ItemStack(items.yog_sothoth_charm, 1, 0), dcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("ypowerCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.yog_sothoth_charm, 1, 3),
						new ItemStack(items.yog_sothoth_charm, 1, 0), pcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("srangeCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.shub_niggurath_charm, 1, 1),
						new ItemStack(items.shub_niggurath_charm, 1, 0), rcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("sdurationCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.shub_niggurath_charm, 1, 2),
						new ItemStack(items.shub_niggurath_charm, 1, 0), dcoffers));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("spowerCharm", 3, ACLib.omothol_id, 200F,
						new ItemStack(items.shub_niggurath_charm, 1, 3),
						new ItemStack(items.shub_niggurath_charm, 1, 0), pcoffers));
		Object[] ccoffers = new Object[]{
				items.cthulhu_engraved_coin,
				items.cthulhu_engraved_coin,
				items.cthulhu_engraved_coin,
				items.cthulhu_engraved_coin,
				items.cthulhu_engraved_coin,
				items.cthulhu_engraved_coin,
				items.cthulhu_engraved_coin,
				items.cthulhu_engraved_coin
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("cthulhuCharm", 4, 2000F,
						new ItemStack(items.cthulhu_charm, 1, 0),
						new ItemStack(items.ritual_charm, 1, 0), ccoffers));
		Object[] hcoffers = new Object[]{
				items.hastur_engraved_coin,
				items.hastur_engraved_coin,
				items.hastur_engraved_coin,
				items.hastur_engraved_coin,
				items.hastur_engraved_coin,
				items.hastur_engraved_coin,
				items.hastur_engraved_coin,
				items.hastur_engraved_coin
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("hasturCharm", 4, 2000F,
						new ItemStack(items.hastur_charm, 1, 0),
						new ItemStack(items.ritual_charm, 1, 0), hcoffers));
		Object[] jcoffers = new Object[]{
				items.jzahar_engraved_coin,
				items.jzahar_engraved_coin,
				items.jzahar_engraved_coin,
				items.jzahar_engraved_coin,
				items.jzahar_engraved_coin,
				items.jzahar_engraved_coin,
				items.jzahar_engraved_coin,
				items.jzahar_engraved_coin
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("jzaharCharm", 4, 2000F,
						new ItemStack(items.jzahar_charm, 1, 0),
						new ItemStack(items.ritual_charm, 1, 0), jcoffers));
		Object[] acoffers = new Object[]{
				items.azathoth_engraved_coin,
				items.azathoth_engraved_coin,
				items.azathoth_engraved_coin,
				items.azathoth_engraved_coin,
				items.azathoth_engraved_coin,
				items.azathoth_engraved_coin,
				items.azathoth_engraved_coin,
				items.azathoth_engraved_coin
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("azathothCharm", 4, 2000F,
						new ItemStack(items.azathoth_charm, 1, 0),
						new ItemStack(items.ritual_charm, 1, 0), acoffers));
		Object[] ncoffers = new Object[]{
				items.nyarlathotep_engraved_coin,
				items.nyarlathotep_engraved_coin,
				items.nyarlathotep_engraved_coin,
				items.nyarlathotep_engraved_coin,
				items.nyarlathotep_engraved_coin,
				items.nyarlathotep_engraved_coin,
				items.nyarlathotep_engraved_coin,
				items.nyarlathotep_engraved_coin
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("nyarlathotepCharm", 4, 2000F,
						new ItemStack(items.nyarlathotep_charm, 1, 0),
						new ItemStack(items.ritual_charm, 1, 0), ncoffers));
		Object[] ycoffers = new Object[]{
				items.yog_sothoth_engraved_coin,
				items.yog_sothoth_engraved_coin,
				items.yog_sothoth_engraved_coin,
				items.yog_sothoth_engraved_coin,
				items.yog_sothoth_engraved_coin,
				items.yog_sothoth_engraved_coin,
				items.yog_sothoth_engraved_coin,
				items.yog_sothoth_engraved_coin
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("yogsothothCharm", 4, 2000F,
						new ItemStack(items.yog_sothoth_charm, 1, 0),
						new ItemStack(items.ritual_charm, 1, 0), ycoffers));
		Object[] scoffers = new Object[]{
				items.shub_niggurath_engraved_coin,
				items.shub_niggurath_engraved_coin,
				items.shub_niggurath_engraved_coin,
				items.shub_niggurath_engraved_coin,
				items.shub_niggurath_engraved_coin,
				items.shub_niggurath_engraved_coin,
				items.shub_niggurath_engraved_coin,
				items.shub_niggurath_engraved_coin
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("shubniggurathCharm", 4, 2000F,
						new ItemStack(items.shub_niggurath_charm, 1, 0),
						new ItemStack(items.ritual_charm, 1, 0), scoffers));
		Object[] owoffers = new Object[]{
				items.shadow_shard,
				Blocks.COBBLESTONE,
				items.coralium_gem,
				new ItemStack(blocks.darkstone_cobblestone.getBlock()),
				items.shadow_shard,
				Blocks.COBBLESTONE,
				items.coralium_gem,
				new ItemStack(blocks.darkstone_cobblestone.getBlock())
		};
		Object[] awoffers = new Object[]{
				items.shadow_shard,
				new ItemStack(blocks.abyssal_stone_brick.getBlock(), 1, 0),
				items.coralium_gem,
				new ItemStack(blocks.coralium_stone_brick.getBlock(), 1, 0),
				items.shadow_shard,
				new ItemStack(blocks.abyssal_stone_brick.getBlock(), 1, 0),
				items.coralium_gem,
				new ItemStack(blocks.coralium_stone_brick.getBlock(), 1, 0)
		};
		Object[] dloffers = new Object[]{
				items.shadow_shard,
				blocks.dreadstone_brick.getBlock(),
				items.coralium_gem,
				new ItemStack(blocks.abyssalnite_stone_brick.getBlock(), 1, 0),
				items.shadow_shard,
				new ItemStack(blocks.dreadstone_brick.getBlock(), 1, 0),
				items.coralium_gem,
				new ItemStack(blocks.abyssalnite_stone_brick.getBlock(), 1, 0)
		};
		Object[] omtoffers = new Object[]{
				items.shadow_shard,
				new ItemStack(blocks.ethaxium_brick.getBlock(), 1, 0),
				items.coralium_gem,
				new ItemStack(blocks.dark_ethaxium_brick.getBlock(), 1, 0),
				items.shadow_shard,
				new ItemStack(blocks.ethaxium_brick.getBlock(), 1, 0),
				items.coralium_gem,
				new ItemStack(blocks.dark_ethaxium_brick.getBlock(), 1, 0)
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("epOWupgrade", 0, 400F,
						new ItemStack(blocks.overworld_energy_pedestal.getBlock()),
						blocks.energy_pedestal.getBlock(), owoffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("epAWupgrade", 1, 800F,
						new ItemStack(blocks.abyssal_wasteland_energy_pedestal.getBlock()),
						new ItemStack(blocks.overworld_energy_pedestal.getBlock()),
						awoffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("epDLupgrade", 2, 1200F,
						new ItemStack(blocks.dreadlands_energy_pedestal.getBlock()),
						new ItemStack(blocks.abyssal_wasteland_energy_pedestal.getBlock()),
						dloffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("epOMTupgrade", 3, 1600F,
						new ItemStack(blocks.omothol_energy_pedestal.getBlock()),
						new ItemStack(blocks.dreadlands_energy_pedestal.getBlock()),
						omtoffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("saOWupgrade", 0, 400F,
						new ItemStack(blocks.overworld_sacrificial_altar.getBlock()),
						blocks.sacrificial_altar.getBlock(), owoffers).setTags("PotEnergy",
						"CollectionLimit", "CoolDown"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("saAWupgrade", 1, 800F,
						new ItemStack(blocks.abyssal_wasteland_sacrificial_altar.getBlock()),
						new ItemStack(blocks.overworld_sacrificial_altar.getBlock()),
						awoffers).setTags("PotEnergy", "CollectionLimit", "CoolDown"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("saDLupgrade", 2, 1200F,
						new ItemStack(blocks.dreadlands_sacrificial_altar.getBlock()),
						new ItemStack(blocks.abyssal_wasteland_sacrificial_altar.getBlock()),
						dloffers).setTags("PotEnergy", "CollectionLimit", "CoolDown"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("saOMTupgrade", 3, 1600F,
						new ItemStack(blocks.omothol_sacrificial_altar.getBlock()),
						new ItemStack(blocks.dreadlands_sacrificial_altar.getBlock()),
						omtoffers).setTags("PotEnergy", "CollectionLimit", "CoolDown"));
		Object[] staffofferings = new Object[]{
				new ItemStack(items.essence, 1, 1),
				new ItemStack(items.essence, 1, 2),
				items.eldritch_scale,
				items.ethaxium_ingot,
				items.rlyehian_gateway_key,
				items.ethaxium_ingot,
				items.eldritch_scale,
				new ItemStack(items.essence, 1, 0)
		};
		String[] tags = {"energyShadow", "energyAbyssal", "energyDread", "energyOmothol", "ench"};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("jzaharStaff", 4, ACLib.omothol_id, 15000F,
						new ItemStack(items.staff_of_the_gatekeeper),
						new ItemStack(items.staff_of_rending, 1, 3), staffofferings).setTags(tags));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("silverKey", 4, ACLib.omothol_id, 20000F, true,
						new ItemStack(items.silver_key), new ItemStack(items.rlyehian_gateway_key),
						new Object[]{
								items.ethaxium_ingot,
								items.ethaxium_ingot,
								items.ethaxium_ingot,
								items.ethaxium_ingot,
								items.ethaxium_ingot,
								items.ethaxium_ingot,
								items.ethaxium_ingot,
								items.ethaxium_ingot
						}));
		RitualRegistry.instance().registerRitual(new NecronomiconWeatherRitual());
		Object[] containerofferings = new Object[]{
				blocks.energy_collector.getBlock(),
				items.shadow_shard,
				blocks.energy_collector.getBlock(),
				items.shadow_shard,
				blocks.energy_collector.getBlock(),
				items.shadow_shard,
				blocks.energy_collector.getBlock(),
				items.shadow_shard
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("energyContainer", 0, 100F,
						new ItemStack(blocks.energy_container.getBlock()),
						blocks.energy_pedestal.getBlock(), containerofferings).setTags(
						"PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("ecolOWupgrade", 0, 400F,
						new ItemStack(blocks.overworld_energy_collector.getBlock()),
						blocks.energy_collector.getBlock(), owoffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("ecolAWupgrade", 1, 800F,
						new ItemStack(blocks.abyssal_wasteland_energy_collector.getBlock()),
						new ItemStack(blocks.overworld_energy_collector.getBlock()),
						awoffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("ecolDLupgrade", 2, 1200F,
						new ItemStack(blocks.dreadlands_energy_collector.getBlock()),
						new ItemStack(blocks.abyssal_wasteland_energy_collector.getBlock()),
						dloffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("ecolOMTupgrade", 3, 1600F,
						new ItemStack(blocks.omothol_energy_collector.getBlock()),
						new ItemStack(blocks.dreadlands_energy_collector.getBlock()),
						omtoffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("erOWupgrade", 0, 400F,
						new ItemStack(blocks.overworld_energy_relay.getBlock()),
						blocks.energy_relay.getBlock(), owoffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("erAWupgrade", 1, 800F,
						new ItemStack(blocks.abyssal_wasteland_energy_relay.getBlock()),
						new ItemStack(blocks.overworld_energy_relay.getBlock()), awoffers).setTags(
						"PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("erDLupgrade", 2, 1200F,
						new ItemStack(blocks.dreadlands_energy_relay.getBlock()),
						new ItemStack(blocks.abyssal_wasteland_energy_relay.getBlock()),
						dloffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("erOMTupgrade", 3, 1600F,
						new ItemStack(blocks.omothol_energy_relay.getBlock()),
						new ItemStack(blocks.dreadlands_energy_relay.getBlock()),
						omtoffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("econOWupgrade", 0, 400F,
						new ItemStack(blocks.overworld_energy_container.getBlock()),
						blocks.energy_container.getBlock(), owoffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("econAWupgrade", 1, 800F,
						new ItemStack(blocks.abyssal_wasteland_energy_container.getBlock()),
						new ItemStack(blocks.overworld_energy_container.getBlock()),
						awoffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("econDLupgrade", 2, 1200F,
						new ItemStack(blocks.dreadlands_energy_container.getBlock()),
						new ItemStack(blocks.abyssal_wasteland_energy_container.getBlock()),
						dloffers).setTags("PotEnergy"));
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("econOMTupgrade", 3, 1600F,
						new ItemStack(blocks.omothol_energy_container.getBlock()),
						new ItemStack(blocks.dreadlands_energy_container.getBlock()),
						omtoffers).setTags("PotEnergy"));
		Object[] sorawofferings = new Object[]{
				items.shadow_gem,
				new ItemStack(blocks.abyssal_stone.getBlock()),
				items.coralium_plagued_flesh,
				new ItemStack(blocks.abyssal_stone.getBlock()),
				items.coralium_plagued_flesh,
				new ItemStack(blocks.abyssal_stone.getBlock()),
				items.coralium_plagued_flesh,
				new ItemStack(blocks.abyssal_stone.getBlock())
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("sorAWupgrade", 1, ACLib.abyssal_wasteland_id,
						1000F,
						new ItemStack(items.staff_of_rending, 1, 1),
						new ItemStack(items.staff_of_rending, 1, 0), sorawofferings).setTags(tags));
		Object[] sordlofferings = new Object[]{
				items.shadow_gem,
				new ItemStack(blocks.dreadstone.getBlock()),
				items.dread_fragment,
				new ItemStack(blocks.dreadstone.getBlock()),
				items.dread_fragment,
				new ItemStack(blocks.dreadstone.getBlock()),
				items.dread_fragment,
				new ItemStack(blocks.dreadstone.getBlock())
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("sorDLupgrade", 2, ACLib.dreadlands_id, 2000F,
						new ItemStack(items.staff_of_rending, 1, 2),
						new ItemStack(items.staff_of_rending, 1, 1), sordlofferings).setTags(tags));
		Object[] soromtofferings = new Object[]{
				items.shadow_gem,
				new ItemStack(blocks.omothol_stone.getBlock()),
				items.omothol_flesh,
				new ItemStack(blocks.omothol_stone.getBlock()),
				items.omothol_flesh,
				new ItemStack(blocks.omothol_stone.getBlock()),
				items.omothol_flesh,
				new ItemStack(blocks.omothol_stone.getBlock())
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconInfusionRitual("sorOMTupgrade", 3, ACLib.omothol_id, 3000F,
						new ItemStack(items.staff_of_rending, 1, 3),
						new ItemStack(items.staff_of_rending, 1, 2), soromtofferings).setTags(
						tags));
		RitualRegistry.instance().registerRitual(new NecronomiconHouseRitual());
		Object[] basicscrollofferings =
				{Items.BOOK, null, Items.BOOK, null, Items.BOOK, null, Items.BOOK};
		RitualRegistry.instance().registerRitual(
				new NecronomiconCreationRitual("basicScroll", 0, 100.0F,
						new ItemStack(items.scroll, 1, 0), basicscrollofferings));
		Object[] lesserscrollofferings = {
				Items.BOOK,
				null,
				blocks.wastelands_thorn.getBlock(),
				null,
				Items.BOOK,
				null,
				blocks.luminous_thistle.getBlock()
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconCreationRitual("lesserScroll", 1, 1000F,
						new ItemStack(items.scroll, 1, 1), lesserscrollofferings));
		Object[] moderatescrollofferings = {
				Items.BOOK,
				null,
				items.dread_fragment,
				null,
				Items.BOOK,
				null,
				items.dreaded_shard_of_abyssalnite
		};
		RitualRegistry.instance().registerRitual(
				new NecronomiconCreationRitual("moderateScroll", 2, 2000F,
						new ItemStack(items.scroll, 1, 2), moderatescrollofferings));
	}

	private static void addDisruptions() {
		DisruptionHandler.instance().registerDisruption(new DisruptionLightning());
		DisruptionHandler.instance().registerDisruption(new DisruptionFire());
		DisruptionHandler.instance().registerDisruption(
				new DisruptionSpawn("spawnShoggoth", null, EntityLesserShoggoth.class));
		DisruptionHandler.instance()
				.registerDisruption(new DisruptionPotion("poisonPotion", null, MobEffects.POISON));
		DisruptionHandler.instance().registerDisruption(
				new DisruptionPotion("slownessPotion", null, MobEffects.SLOWNESS));
		DisruptionHandler.instance().registerDisruption(
				new DisruptionPotion("weaknessPotion", null, MobEffects.WEAKNESS));
		DisruptionHandler.instance()
				.registerDisruption(new DisruptionPotion("witherPotion", null, MobEffects.WITHER));
		DisruptionHandler.instance().registerDisruption(
				new DisruptionPotion("coraliumPotion", null, AbyssalCraftAPI.coralium_plague));
		DisruptionHandler.instance().registerDisruption(new DisruptionPotentialEnergy());
		DisruptionHandler.instance().registerDisruption(new DisruptionFreeze());
		DisruptionHandler.instance().registerDisruption(
				new DisruptionSwarm("swarmShadow", null, EntityShadowCreature.class,
						EntityShadowMonster.class, EntityShadowBeast.class));
		DisruptionHandler.instance().registerDisruption(new DisruptionFireRain());
		DisruptionHandler.instance().registerDisruption(new DisruptionDisplaceEntities());
		//		DisruptionHandler.instance().registerDisruption(new DisruptionMonolith()); //uncomment at some point when there's a lot more disruptions
		DisruptionHandler.instance().registerDisruption(new DisruptionTeleportRandomly());
		DisruptionHandler.instance().registerDisruption(new DisruptionDrainNearbyPE());
		DisruptionHandler.instance().registerDisruption(
				new DisruptionSwarm("swarmSheep", DeityType.SHUBNIGGURATH, EntityEvilSheep.class,
						EntitySheep.class));
		DisruptionHandler.instance().registerDisruption(new DisruptionAnimalCorruption());
		//		DisruptionHandler.instance().registerDisruption(new DisruptionCorruption());
		DisruptionHandler.instance().registerDisruption(new DisruptionOoze());
		DisruptionHandler.instance().registerDisruption(new DisruptionRandomSwarm());
		DisruptionHandler.instance().registerDisruption(new DisruptionRandomSpawn());
		DisruptionHandler.instance().registerDisruption(
				new DisruptionSpawn("spawnShubOffspring", DeityType.SHUBNIGGURATH,
						EntityShubOffspring.class));
	}

	private static void addSpells() {
		if (ACConfig.entropy_spell) SpellRegistry.instance().registerSpell(new EntropySpell());
		if (ACConfig.life_drain_spell) SpellRegistry.instance().registerSpell(new LifeDrainSpell());
		if (ACConfig.mining_spell) SpellRegistry.instance().registerSpell(new MiningSpell());
		if (ACConfig.grasp_of_cthulhu_spell)
			SpellRegistry.instance().registerSpell(new GraspofCthulhuSpell());
		if (ACConfig.invisibility_spell)
			SpellRegistry.instance().registerSpell(new InvisibilitySpell());
		if (ACConfig.detachment_spell)
			SpellRegistry.instance().registerSpell(new DetachmentSpell());
		if (ACConfig.steal_vigor_spell)
			SpellRegistry.instance().registerSpell(new StealVigorSpell());
		if (ACConfig.sirens_song_spell)
			SpellRegistry.instance().registerSpell(new SirensSongSpell());
		if (ACConfig.undeath_to_dust_spell)
			SpellRegistry.instance().registerSpell(new UndeathtoDustSpell());
		if (ACConfig.ooze_removal_spell)
			SpellRegistry.instance().registerSpell(new OozeRemovalSpell());
		if (ACConfig.teleport_hostile_spell)
			SpellRegistry.instance().registerSpell(new TeleportHostilesSpell());
		if (ACConfig.display_routes_spell)
			SpellRegistry.instance().registerSpell(new DisplayRoutesSpell());
		if (ACConfig.toggle_state_spell)
			SpellRegistry.instance().registerSpell(new ToggleStateSpell());
		if (ACConfig.floating_spell) SpellRegistry.instance().registerSpell(new FloatingSpell());
		if (ACConfig.teleport_home_spell)
			SpellRegistry.instance().registerSpell(new TeleportHomeSpell());
	}

	private static void addRendings() {
		ACItems items = ACItems.getInstance();
		RendingRegistry.instance().registerRending(
				new Rending("Abyssal", 100, new ItemStack(items.essence, 1, 0),
						e -> e.world.provider.getDimension() == ACLib.abyssal_wasteland_id &&
								EntityUtil.isCoraliumPlagueCarrier(e) && e.isNonBoss(),
						"ac.rending.essence_aw", ACLib.abyssal_wasteland_id));
		RendingRegistry.instance().registerRending(
				new Rending("Dread", 100, new ItemStack(items.essence, 1, 1),
						e -> e.world.provider.getDimension() == ACLib.dreadlands_id &&
								EntityUtil.isDreadPlagueCarrier(e) && e.isNonBoss(),
						"ac.rending.essence_dl", ACLib.dreadlands_id));
		RendingRegistry.instance().registerRending(
				new Rending("Omothol", 100, new ItemStack(items.essence, 1, 2),
						e -> e.world.provider.getDimension() == ACLib.omothol_id &&
								e instanceof IOmotholEntity &&
								e.getCreatureAttribute() != AbyssalCraftAPI.SHADOW && e.isNonBoss(),
						"ac.rending.essence_omt", ACLib.omothol_id));
		RendingRegistry.instance().registerRending(
				new Rending("Shadow", 200, new ItemStack(items.shadow_gem),
						e -> e.getCreatureAttribute() == AbyssalCraftAPI.SHADOW && e.isNonBoss(),
						"ac.rending.shadowgem", OreDictionary.WILDCARD_VALUE));
	}

	private static void addArmorSmelting(Item helmet, Item chestplate, Item pants, Item boots,
										 ItemStack nugget) {
		GameRegistry.addSmelting(helmet, nugget, 1F);
		GameRegistry.addSmelting(chestplate, nugget, 1F);
		GameRegistry.addSmelting(pants, nugget, 1F);
		GameRegistry.addSmelting(boots, nugget, 1F);
	}

}
