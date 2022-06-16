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
package com.shinoow.abyssalcraft.api.block;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.biome.IDarklandsBiome;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.api.necronomicon.condition.BiomePredicateCondition;
import com.shinoow.abyssalcraft.api.necronomicon.condition.DimensionCondition;
import com.shinoow.abyssalcraft.api.necronomicon.condition.EntityCondition;
import com.shinoow.abyssalcraft.common.blocks.*;
import com.shinoow.abyssalcraft.common.blocks.itemblock.*;
import com.shinoow.abyssalcraft.common.blocks.tile.*;
import com.shinoow.abyssalcraft.common.world.gen.WorldGenDLT;
import com.shinoow.abyssalcraft.common.world.gen.WorldGenDrT;
import com.shinoow.abyssalcraft.lib.ACLib;
import com.shinoow.abyssalcraft.lib.ACTabs;
import com.shinoow.abyssalcraft.lib.util.Reflections;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Arrays;


/**
 * Contains all blocks added in AbyssalCraft
 *
 * @author shinoow
 */
public class ACBlocks {

	private static ACBlocks INSTANCE;

	public ACBlock Darkbrickslab2, Darkcobbleslab2, abyslab2, Darkstoneslab2, DLTslab2, Altar,
			dreadbrickslab2, abydreadbrickslab2, cstonebrickslab2, ethaxiumslab2, house,
			darkethaxiumslab2, abycobbleslab2, dreadcobbleslab2, abydreadcobbleslab2,
			cstonecobbleslab2, darkstone, abyssal_stone, dreadstone, abyssalnite_stone,
			coralium_stone, ethaxium, omothol_stone, monolith_stone, darkstone_cobblestone,
			abyssal_cobblestone, dreadstone_cobblestone, abyssalnite_cobblestone,
			coralium_cobblestone, darkstone_brick, chiseled_darkstone_brick,
			cracked_darkstone_brick, glowing_darkstone_bricks, darkstone_brick_slab,
			darkstone_cobblestone_slab, darkstone_brick_stairs, darkstone_cobblestone_stairs,
			darklands_oak_leaves, darklands_oak_wood, darklands_oak_wood_2, darklands_oak_sapling,
			abyssal_stone_brick, chiseled_abyssal_stone_brick, cracked_abyssal_stone_brick,
			abyssal_stone_brick_slab, abyssal_stone_brick_stairs, coralium_ore, abyssalnite_ore,
			abyssal_stone_brick_fence, darkstone_cobblestone_wall, oblivion_deathbomb,
			block_of_abyssalnite, block_of_refined_coralium, block_of_dreadium, block_of_ethaxium,
			coralium_infused_stone, odb_core, wooden_crate, darkstone_slab, darkstone_doubleslab,
			darkstone_button, darkstone_pressure_plate, darklands_oak_planks, darklands_oak_button,
			darklands_oak_pressure_plate, darklands_oak_stairs, darklands_oak_slab,
			liquid_coralium,
			dreadlands_infused_powerstone, abyssal_coralium_ore, abyssal_stone_button,
			abyssal_stone_pressure_plate, darkstone_brick_fence, darklands_oak_fence,
			dreadlands_abyssalnite_ore, dreaded_abyssalnite_ore, dreadstone_brick,
			chiseled_dreadstone_brick, cracked_dreadstone_brick, abyssalnite_stone_brick,
			chiseled_abyssalnite_stone_brick, cracked_abyssalnite_stone_brick, dreadlands_grass,
			dreadlands_log, dreadlands_leaves, dreadlands_sapling, dreadlands_planks,
			depths_ghoul_head, pete_head, mr_wilson_head, dr_orange_head, dreadstone_brick_stairs,
			dreadstone_brick_fence, dreadstone_brick_slab, abyssalnite_stone_brick_stairs,
			abyssalnite_stone_brick_fence, abyssalnite_stone_brick_slab, liquid_antimatter,
			coralium_stone_brick, chiseled_coralium_stone_brick, cracked_coralium_stone_brick,
			coralium_stone_brick_fence, coralium_stone_brick_slab, coralium_stone_brick_stairs,
			coralium_stone_button, coralium_stone_pressure_plate, chagaroth_altar_top,
			chagaroth_altar_bottom, crystallizer_idle, crystallizer_active, transmutator_idle,
			transmutator_active, dreadguard_spawner, chagaroth_spawner, dreadlands_wood_fence,
			nitre_ore, abyssal_iron_ore, abyssal_gold_ore, abyssal_diamond_ore, abyssal_nitre_ore,
			abyssal_tin_ore, abyssal_copper_ore, pearlescent_coralium_ore, liquified_coralium_ore,
			solid_lava, ethaxium_brick, chiseled_ethaxium_brick, cracked_ethaxium_brick,
			ethaxium_pillar, ethaxium_brick_stairs, ethaxium_brick_slab, ethaxium_brick_fence,
			engraver, materializer, dark_ethaxium_brick, chiseled_dark_ethaxium_brick,
			cracked_dark_ethaxium_brick, dark_ethaxium_pillar, dark_ethaxium_brick_stairs,
			dark_ethaxium_brick_slab, dark_ethaxium_brick_fence, ritual_altar_stone,
			ritual_altar_darkstone, ritual_altar_abyssal_stone, ritual_altar_coralium_stone,
			ritual_altar_dreadstone, ritual_altar_abyssalnite_stone, ritual_altar_ethaxium,
			ritual_altar_dark_ethaxium, ritual_pedestal_stone, ritual_pedestal_darkstone,
			ritual_pedestal_abyssal_stone, ritual_pedestal_coralium_stone,
			ritual_pedestal_dreadstone, ritual_pedestal_abyssalnite_stone,
			ritual_pedestal_ethaxium,
			ritual_pedestal_dark_ethaxium, shoggoth_ooze, cthulhu_statue, hastur_statue,
			jzahar_statue, azathoth_statue, nyarlathotep_statue, yog_sothoth_statue,
			shub_niggurath_statue, shoggoth_biomass, energy_pedestal, monolith_pillar,
			sacrificial_altar, overworld_energy_pedestal, abyssal_wasteland_energy_pedestal,
			dreadlands_energy_pedestal, omothol_energy_pedestal, overworld_sacrificial_altar,
			abyssal_wasteland_sacrificial_altar, dreadlands_sacrificial_altar,
			omothol_sacrificial_altar, jzahar_spawner, minion_of_the_gatekeeper_spawner,
			mimic_fire,
			decorative_cthulhu_statue, decorative_hastur_statue, decorative_jzahar_statue,
			decorative_azathoth_statue, decorative_nyarlathotep_statue,
			decorative_yog_sothoth_statue, decorative_shub_niggurath_statue, iron_crystal_cluster,
			gold_crystal_cluster, sulfur_crystal_cluster, carbon_crystal_cluster,
			oxygen_crystal_cluster, hydrogen_crystal_cluster, nitrogen_crystal_cluster,
			phosphorus_crystal_cluster, potassium_crystal_cluster, nitrate_crystal_cluster,
			methane_crystal_cluster, redstone_crystal_cluster, abyssalnite_crystal_cluster,
			coralium_crystal_cluster, dreadium_crystal_cluster, blaze_crystal_cluster,
			tin_crystal_cluster, copper_crystal_cluster, silicon_crystal_cluster,
			magnesium_crystal_cluster, aluminium_crystal_cluster, silica_crystal_cluster,
			alumina_crystal_cluster, magnesia_crystal_cluster, zinc_crystal_cluster,
			calcium_crystal_cluster, beryllium_crystal_cluster, beryl_crystal_cluster,
			energy_collector, energy_relay, energy_container, overworld_energy_collector,
			abyssal_wasteland_energy_collector, dreadlands_energy_collector,
			omothol_energy_collector, overworld_energy_relay, abyssal_wasteland_energy_relay,
			dreadlands_energy_relay, omothol_energy_relay, overworld_energy_container,
			abyssal_wasteland_energy_container, dreadlands_energy_container,
			omothol_energy_container, abyssal_sand, fused_abyssal_sand, abyssal_sand_glass,
			dreadlands_dirt, abyssal_cobblestone_slab, abyssal_cobblestone_stairs,
			abyssal_cobblestone_wall, dreadstone_cobblestone_slab, dreadstone_cobblestone_stairs,
			dreadstone_cobblestone_wall, abyssalnite_cobblestone_slab,
			abyssalnite_cobblestone_stairs, abyssalnite_cobblestone_wall,
			coralium_cobblestone_slab,
			coralium_cobblestone_stairs, coralium_cobblestone_wall, luminous_thistle,
			wastelands_thorn, rending_pedestal, state_transformer, energy_depositioner,
			calcified_stone, darklands_oak_door, dreadlands_door, multi_block,
			sequential_brewing_stand, portal_anchor;

	private ACBlocks() {
		DimensionCondition abyssal_wasteland = new DimensionCondition(ACLib.abyssal_wasteland_id);
		DimensionCondition dreadlands = new DimensionCondition(ACLib.dreadlands_id);
		DimensionCondition omothol = new DimensionCondition(ACLib.omothol_id);

		abyssal_stone = new ACBlock(
				new BlockACStone(BlockACStone.EnumStoneType.ABYSSAL_STONE).setTranslationKey(
						"abystone"), abyssal_wasteland);
		darkstone = new ACBlock(
				new BlockACStone(BlockACStone.EnumStoneType.DARKSTONE).setTranslationKey(
						"darkstone"));
		dreadstone = new ACBlock(
				new BlockACStone(BlockACStone.EnumStoneType.DREADSTONE).setTranslationKey(
						"dreadstone"), dreadlands);
		abyssalnite_stone = new ACBlock(
				new BlockACStone(BlockACStone.EnumStoneType.ABYSSALNITE_STONE).setTranslationKey(
						"abydreadstone"), dreadlands);
		coralium_stone = new ACBlock(
				new BlockACStone(BlockACStone.EnumStoneType.CORALIUM_STONE).setTickRandomly(true)
						.setTranslationKey("cstone"), abyssal_wasteland);
		ethaxium = new ACBlock(
				new BlockACStone(BlockACStone.EnumStoneType.ETHAXIUM).setTranslationKey(
						"ethaxium"),
				omothol);
		omothol_stone = new ACBlock(
				new BlockACStone(BlockACStone.EnumStoneType.OMOTHOL_STONE).setTranslationKey(
						"omotholstone"), omothol);
		monolith_stone = new ACBlock(
				new BlockACStone(BlockACStone.EnumStoneType.MONOLITH_STONE).setTranslationKey(
						"monolithstone"));
		darkstone_brick = new ACBlock(0,
				new BlockACBrick(1.65F, 12.0F, MapColor.BLACK).setTranslationKey(
						"darkstone_brick"));
		chiseled_darkstone_brick = new ACBlock(1,
				new BlockACBrick(1.65F, 12.0F, MapColor.BLACK).remap("darkstone_brick",
								BlockACBrick.EnumBrickType.CHISELED)
						.setTranslationKey("chiseled_darkstone_brick"));
		cracked_darkstone_brick = new ACBlock(2,
				new BlockACBrick(1.65F, 12.0F, MapColor.BLACK).remap("darkstone_brick",
								BlockACBrick.EnumBrickType.CRACKED)
						.setTranslationKey("cracked_darkstone_brick"));
		darkstone_cobblestone = new ACBlock(new BlockACCobblestone(
				BlockACCobblestone.EnumCobblestoneType.DARKSTONE).setTranslationKey(
				"darkstone_cobble"));
		abyssal_cobblestone = new ACBlock(new BlockACCobblestone(
				BlockACCobblestone.EnumCobblestoneType.ABYSSAL_STONE).setTranslationKey(
				"abyssalcobblestone"), abyssal_wasteland);
		dreadstone_cobblestone = new ACBlock(new BlockACCobblestone(
				BlockACCobblestone.EnumCobblestoneType.DREADSTONE).setTranslationKey(
				"dreadstonecobblestone"), dreadlands);
		abyssalnite_cobblestone = new ACBlock(new BlockACCobblestone(
				BlockACCobblestone.EnumCobblestoneType.ABYSSALNITE_STONE).setTranslationKey(
				"abyssalnitecobblestone"), dreadlands);
		coralium_cobblestone = new ACBlock(new BlockACCobblestone(
				BlockACCobblestone.EnumCobblestoneType.CORALIUM_STONE).setTranslationKey(
				"coraliumcobblestone"), abyssal_wasteland);
		glowing_darkstone_bricks = new ACBlock(
				new BlockACBasic(Material.ROCK, "pickaxe", 3, 55F, 3000F, SoundType.STONE,
						MapColor.BLACK).setLightLevel(1.0F).setTranslationKey("dsglow"));
		darkstone_brick_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, SoundType.STONE, MapColor.BLACK).setHardness(
						1.65F).setResistance(12.0F).setTranslationKey("darkbrickslab1"),
				new ItemSlabAC(darkstone_brick_slab.getBlock(), darkstone_brick_slab.getBlock(),
						Darkbrickslab2.getBlock())).setStateMap(BlockACSlab.VARIANT_PROPERTY);
		Darkbrickslab2 = new ACBlock(
				new BlockACDoubleSlab(darkstone_brick_slab.getBlock(), Material.ROCK).setHardness(
						1.65F).setResistance(12.0F).setTranslationKey("darkbrickslab2"),
				new ItemSlabAC(Darkbrickslab2.getBlock(), darkstone_brick_slab.getBlock(),
						Darkbrickslab2.getBlock()));
		darkstone_cobblestone_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, SoundType.STONE, MapColor.BLACK).setHardness(
						1.65F).setResistance(12.0F).setTranslationKey("darkcobbleslab1"),
				new ItemSlabAC(darkstone_cobblestone_slab.getBlock(),
						darkstone_cobblestone_slab.getBlock(),
						Darkcobbleslab2.getBlock())).setStateMap(BlockACSlab.VARIANT_PROPERTY);
		Darkcobbleslab2 = new ACBlock(new BlockACDoubleSlab(darkstone_cobblestone_slab.getBlock(),
				Material.ROCK).setHardness(1.65F).setResistance(12.0F)
				.setTranslationKey("darkcobbleslab2"),
				new ItemSlabAC(Darkcobbleslab2.getBlock(), darkstone_cobblestone_slab.getBlock(),
						Darkcobbleslab2.getBlock()));
		darkstone_brick_stairs = new ACBlock(
				new BlockACStairs(darkstone_brick.getBlock()).setHardness(1.65F)
						.setResistance(12.0F).setTranslationKey("dbstairs"));
		darkstone_cobblestone_stairs = new ACBlock(
				new BlockACStairs(darkstone_cobblestone.getBlock()).setHardness(1.65F)
						.setResistance(12.0F).setTranslationKey("dcstairs"));
		darklands_oak_sapling = new ACBlock(
				new BlockACSapling(new WorldGenDLT(true)).setHardness(0.0F).setResistance(0.0F)
						.setTranslationKey("dltsapling")).setStateMap(BlockSapling.TYPE);
		darklands_oak_leaves = new ACBlock(
				new BlockACLeaves(darklands_oak_sapling.getBlock(), MapColor.BLUE).setHardness(0.2F)
						.setResistance(1.0F).setTranslationKey("dltleaves")).setStateMap(
				BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE);
		darklands_oak_wood = new ACBlock(
				new BlockACLog(MapColor.BROWN).setHardness(2.0F).setResistance(1.0F)
						.setTranslationKey("dltlog"));
		darklands_oak_wood_2 = new ACBlock(
				new BlockACLog(MapColor.BROWN).setHardness(2.0F).setResistance(1.0F)
						.setTranslationKey("dltlog"));
		abyssal_stone_brick = new ACBlock(0,
				new BlockACBrick(2, 1.8F, 12.0F, MapColor.GREEN).setTranslationKey("abybrick"),
				abyssal_wasteland);
		chiseled_abyssal_stone_brick = new ACBlock(1,
				new BlockACBrick(2, 1.8F, 12.0F, MapColor.GREEN).remap("abybrick",
								BlockACBrick.EnumBrickType.CHISELED)
						.setTranslationKey("chiseled_abyssal_stone_brick"), abyssal_wasteland);
		cracked_abyssal_stone_brick = new ACBlock(2,
				new BlockACBrick(2, 1.8F, 12.0F, MapColor.GREEN).remap("abybrick",
								BlockACBrick.EnumBrickType.CRACKED)
						.setTranslationKey("cracked_abyssal_stone_brick"), abyssal_wasteland);
		abyssal_stone_brick_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, "pickaxe", 2, SoundType.STONE,
						MapColor.GREEN).setCreativeTab(ACTabs.tabBlock).setHardness(1.8F)
						.setResistance(12.0F).setTranslationKey("abyslab1"),
				new ItemSlabAC(abyssal_stone_brick_slab.getBlock(),
						abyssal_stone_brick_slab.getBlock(),
						abyslab2.getBlock()).setUnlockCondition(abyssal_wasteland)).setStateMap(
				BlockACSlab.VARIANT_PROPERTY);
		abyslab2 = new ACBlock(
				new BlockACDoubleSlab(abyssal_stone_brick_slab.getBlock(), Material.ROCK,
						"pickaxe",
						2).setHardness(1.8F).setResistance(12.0F).setTranslationKey("abyslab2"),
				new ItemSlabAC(abyslab2.getBlock(), abyssal_stone_brick_slab.getBlock(),
						abyslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		abyssal_stone_brick_stairs = new ACBlock(
				new BlockACStairs(abyssal_stone_brick.getBlock(), "pickaxe", 2).setHardness(1.65F)
						.setResistance(12.0F).setTranslationKey("abystairs"), abyssal_wasteland);
		coralium_ore = new ACBlock(new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("coraliumore"));
		abyssalnite_ore = new ACBlock(new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("abyore"),
				new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
		abyssal_stone_brick_fence = new ACBlock(
				new BlockACFence(Material.ROCK, "pickaxe", 2, SoundType.STONE,
						MapColor.GREEN).setHardness(1.8F).setResistance(12.0F)
						.setTranslationKey("abyfence"), abyssal_wasteland);
		darkstone_cobblestone_wall = new ACBlock(
				new BlockACWall(darkstone_cobblestone.getBlock()).setHardness(1.65F)
						.setResistance(12.0F).setTranslationKey("dscwall")).setStateMap(
				BlockWall.VARIANT);
		wooden_crate = new ACBlock(new BlockCrate().setHardness(3.0F).setResistance(6.0F)
				.setTranslationKey("woodencrate"));
		oblivion_deathbomb = new ACBlock(
				new BlockODB().setHardness(3.0F).setResistance(0F).setTranslationKey("odb"),
				new ItemODB(oblivion_deathbomb.getBlock()), abyssal_wasteland, true).setStateMap(
				BlockTNT.EXPLODE);
		block_of_abyssalnite = new ACBlock(
				new IngotBlock(IngotBlock.EnumIngotType.ABYSSALNITE).setTranslationKey("abyblock"),
				new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
		block_of_refined_coralium = new ACBlock(
				new IngotBlock(IngotBlock.EnumIngotType.REFINED_CORALIUM).setTranslationKey(
						"corblock"), abyssal_wasteland);
		block_of_dreadium = new ACBlock(
				new IngotBlock(IngotBlock.EnumIngotType.DREADIUM).setTranslationKey(
						"dreadiumblock"), dreadlands);
		block_of_ethaxium = new ACBlock(
				new IngotBlock(IngotBlock.EnumIngotType.ETHAXIUM).setTranslationKey(
						"ethaxiumblock"), omothol);
		coralium_infused_stone =
				new ACBlock(new BlockACOre(3, 3.0F, 6.0F).setTranslationKey("coraliumstone"));
		odb_core = new ACBlock(new BlockODBcore().setHardness(3.0F).setResistance(0F)
				.setTranslationKey("odbcore")).setStateMap(BlockTNT.EXPLODE);
		darkstone_slab = new ACBlock(new BlockACSingleSlab(Material.ROCK, SoundType.STONE,
				MapColor.BLACK).setCreativeTab(ACTabs.tabBlock).setHardness(1.65F)
				.setResistance(12.0F).setTranslationKey("darkstoneslab1"),
				new ItemSlabAC(darkstone_slab.getBlock(), darkstone_slab.getBlock(),
						Darkstoneslab2.getBlock())).setStateMap(BlockACSlab.VARIANT_PROPERTY);
		Darkstoneslab2 = new ACBlock(
				new BlockACDoubleSlab(darkstone_slab.getBlock(), Material.ROCK).setHardness(1.65F)
						.setResistance(12.0F).setTranslationKey("darkstoneslab2"),
				new ItemSlabAC(Darkstoneslab2.getBlock(), darkstone_slab.getBlock(),
						Darkstoneslab2.getBlock()));
		darkstone_button = new ACBlock(
				new BlockACButton(true, "DS").setHardness(0.6F).setResistance(12.0F)
						.setTranslationKey("dsbutton"));
		darkstone_pressure_plate = new ACBlock(
				new BlockACPressureplate("DS", Material.ROCK,
						BlockACPressureplate.Sensitivity.MOBS,
						SoundType.STONE).setHardness(0.6F).setResistance(12.0F)
						.setTranslationKey("dspplate"));
		darklands_oak_planks = new ACBlock(
				new BlockACBasic(Material.WOOD, 2.0F, 5.0F, SoundType.WOOD,
						MapColor.BROWN).setTranslationKey("dltplank"));
		darklands_oak_button = new ACBlock(new BlockACButton(true, "DLTplank").setHardness(0.5F)
				.setTranslationKey("dltbutton"));
		darklands_oak_pressure_plate = new ACBlock(
				new BlockACPressureplate("DLTplank", Material.WOOD,
						BlockACPressureplate.Sensitivity.EVERYTHING, SoundType.WOOD).setHardness(
						0.5F).setTranslationKey("dltpplate"));
		darklands_oak_stairs = new ACBlock(
				new BlockACStairs(darklands_oak_planks.getBlock()).setHardness(2.0F)
						.setResistance(5.0F).setTranslationKey("dltstairs"));
		darklands_oak_slab = new ACBlock(
				new BlockACSingleSlab(Material.WOOD, SoundType.WOOD, MapColor.BROWN).setHardness(
						2.0F).setResistance(5.0F).setTranslationKey("dltslab1"),
				new ItemSlabAC(darklands_oak_slab.getBlock(), darklands_oak_slab.getBlock(),
						DLTslab2.getBlock())).setStateMap(BlockACSlab.VARIANT_PROPERTY);
		DLTslab2 = new ACBlock(
				new BlockACDoubleSlab(darklands_oak_slab.getBlock(), Material.WOOD).setHardness(
						2.0F).setResistance(5.0F).setTranslationKey("dltslab2"),
				new ItemSlabAC(DLTslab2.getBlock(), darklands_oak_slab.getBlock(),
						DLTslab2.getBlock()), abyssal_wasteland);
		dreadlands_infused_powerstone = new ACBlock(
				new BlockPSDL().setHardness(50.0F).setResistance(3000F)
						.setCreativeTab(ACTabs.tabDecoration).setTranslationKey("psdl"));
		abyssal_coralium_ore =
				new ACBlock(new BlockACOre(3, 3.0F, 6.0F).setTranslationKey("abycorore"),
						abyssal_wasteland);
		Altar = new ACBlock(new BlockAltar().setHardness(4.0F).setResistance(300.0F)
				.setTranslationKey("altar"));
		abyssal_stone_button = new ACBlock(
				new BlockACButton(false, "pickaxe", 2, "AS").setHardness(0.8F).setResistance(12.0F)
						.setTranslationKey("abybutton"), abyssal_wasteland);
		abyssal_stone_pressure_plate = new ACBlock(
				new BlockACPressureplate("AS", Material.ROCK,
						BlockACPressureplate.Sensitivity.MOBS,
						"pickaxe", 2, SoundType.STONE).setHardness(0.8F).setResistance(12.0F)
						.setTranslationKey("abypplate"), abyssal_wasteland);
		darkstone_brick_fence = new ACBlock(
				new BlockACFence(Material.ROCK, SoundType.STONE, MapColor.BLACK).setHardness(1.65F)
						.setResistance(12.0F).setTranslationKey("dsbfence"));
		darklands_oak_fence = new ACBlock(
				new BlockACFence(Material.WOOD, SoundType.WOOD, MapColor.BROWN).setHardness(2.0F)
						.setResistance(5.0F).setTranslationKey("dltfence"));
		dreaded_abyssalnite_ore =
				new ACBlock(new BlockACOre(4, 2.5F, 20.0F).setTranslationKey("dreadore"),
						dreadlands);
		dreadlands_abyssalnite_ore =
				new ACBlock(new BlockACOre(4, 2.5F, 20.0F).setTranslationKey("abydreadore"),
						dreadlands);
		dreadstone_brick = new ACBlock(0,
				new BlockACBrick(4, 2.5F, 20.0F, MapColor.RED).setTranslationKey("dreadbrick"),
				dreadlands);
		chiseled_dreadstone_brick = new ACBlock(1,
				new BlockACBrick(4, 2.5F, 20.0F, MapColor.RED).remap("dreadbrick",
								BlockACBrick.EnumBrickType.CHISELED)
						.setTranslationKey("chiseled_dreadstone_brick"), dreadlands);
		cracked_dreadstone_brick = new ACBlock(2,
				new BlockACBrick(4, 2.5F, 20.0F, MapColor.RED).remap("dreadbrick",
								BlockACBrick.EnumBrickType.CRACKED)
						.setTranslationKey("cracked_dreadstone_brick"), dreadlands);
		abyssalnite_stone_brick = new ACBlock(0,
				new BlockACBrick(4, 2.5F, 20.0F, MapColor.PURPLE).setTranslationKey(
						"abydreadbrick"), dreadlands);
		chiseled_abyssalnite_stone_brick = new ACBlock(1,
				new BlockACBrick(4, 2.5F, 20.0F, MapColor.PURPLE).remap("abydreadstone",
								BlockACBrick.EnumBrickType.CHISELED)
						.setTranslationKey("chiseled_abyssalnite_stone_brick"), dreadlands);
		cracked_abyssalnite_stone_brick = new ACBlock(2,
				new BlockACBrick(4, 2.5F, 20.0F, MapColor.PURPLE).remap("abydreadbrick",
								BlockACBrick.EnumBrickType.CRACKED)
						.setTranslationKey("cracked_abyssalnite_stone_brick"),
				dreadlands).setStateMap(BlockSapling.TYPE);
		dreadlands_sapling = new ACBlock(
				new BlockACSapling(new WorldGenDrT(true)).setHardness(0.0F).setResistance(0.0F)
						.setTranslationKey("dreadsapling"), dreadlands);
		dreadlands_log = new ACBlock(
				new BlockACLog(MapColor.RED).setHardness(2.0F).setResistance(12.0F)
						.setTranslationKey("dreadlog"), dreadlands);
		dreadlands_leaves = new ACBlock(
				new BlockACLeaves(dreadlands_sapling.getBlock(), MapColor.RED).setHardness(0.2F)
						.setResistance(1.0F).setTranslationKey("dreadleaves"),
				dreadlands).setStateMap(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE);
		dreadlands_planks = new ACBlock(new BlockACBasic(Material.WOOD, 2.0F, 5.0F, SoundType.WOOD,
				MapColor.RED).setTranslationKey("dreadplanks"), dreadlands);
		depths_ghoul_head = new ACBlock(new BlockDGhead().setTranslationKey("dghead"),
				new EntityCondition(AbyssalCraft.modid + ":depthsghoul"));
		liquid_coralium = new ACBlock(new BlockCLiquid().setResistance(500.0F).setLightLevel(1.0F)
				.setTranslationKey("cwater"));
		dreadlands_grass =
				new ACBlock(new BlockDreadGrass().setHardness(0.4F).setTranslationKey(
						"dreadgrass"),
						dreadlands);
		pete_head = new ACBlock(new BlockDGhead().setTranslationKey("phead"),
				new EntityCondition(AbyssalCraft.modid + ":depthsghoul"));
		mr_wilson_head = new ACBlock(new BlockDGhead().setTranslationKey("whead"),
				new EntityCondition(AbyssalCraft.modid + ":depthsghoul"));
		dr_orange_head = new ACBlock(new BlockDGhead().setTranslationKey("ohead"),
				new EntityCondition(AbyssalCraft.modid + ":depthsghoul"));
		dreadstone_brick_stairs = new ACBlock(
				new BlockACStairs(dreadstone_brick.getBlock(), "pickaxe", 4).setHardness(2.5F)
						.setResistance(20.0F).setTranslationKey("dreadbrickstairs"), dreadlands);
		dreadstone_brick_fence = new ACBlock(
				new BlockACFence(Material.ROCK, "pickaxe", 4, SoundType.STONE,
						MapColor.RED).setHardness(2.5F).setResistance(20.0F)
						.setTranslationKey("dreadbrickfence"), dreadlands);
		dreadstone_brick_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, "pickaxe", 4, SoundType.STONE,
						MapColor.RED).setHardness(2.5F).setResistance(20.0F)
						.setTranslationKey("dreadbrickslab1"),
				new ItemSlabAC(dreadstone_brick_slab.getBlock(), dreadstone_brick_slab.getBlock(),
						dreadbrickslab2.getBlock()).setUnlockCondition(dreadlands)).setStateMap(
				BlockACSlab.VARIANT_PROPERTY);
		dreadbrickslab2 = new ACBlock(
				new BlockACDoubleSlab(dreadstone_brick_slab.getBlock(), Material.ROCK, "pickaxe",
						4).setHardness(2.5F).setResistance(20.0F)
						.setTranslationKey("dreadbrickslab2"),
				new ItemSlabAC(dreadbrickslab2.getBlock(), dreadstone_brick_slab.getBlock(),
						dreadbrickslab2.getBlock()).setUnlockCondition(dreadlands));
		abyssalnite_stone_brick_stairs = new ACBlock(
				new BlockACStairs(abyssalnite_stone_brick.getBlock(), "pickaxe", 4).setHardness(
						2.5F).setResistance(20.0F).setTranslationKey("abydreadbrickstairs"),
				dreadlands);
		abyssalnite_stone_brick_fence = new ACBlock(
				new BlockACFence(Material.ROCK, "pickaxe", 4, SoundType.STONE,
						MapColor.PURPLE).setHardness(2.5F).setResistance(20.0F)
						.setTranslationKey("abydreadbrickfence"), dreadlands);
		abyssalnite_stone_brick_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, "pickaxe", 4, SoundType.STONE,
						MapColor.PURPLE).setHardness(2.5F).setResistance(20.0F)
						.setTranslationKey("abydreadbrickslab1"),
				new ItemSlabAC(abyssalnite_stone_brick_slab.getBlock(),
						abyssalnite_stone_brick_slab.getBlock(),
						abydreadbrickslab2.getBlock()).setUnlockCondition(dreadlands)).setStateMap(
				BlockACSlab.VARIANT_PROPERTY);
		abydreadbrickslab2 = new ACBlock(
				new BlockACDoubleSlab(abyssalnite_stone_brick_slab.getBlock(), Material.ROCK,
						"pickaxe", 4).setHardness(2.5F).setResistance(20.0F)
						.setTranslationKey("abydreadbrickslab2"),
				new ItemSlabAC(abydreadbrickslab2.getBlock(),
						abyssalnite_stone_brick_slab.getBlock(),
						abydreadbrickslab2.getBlock()).setUnlockCondition(dreadlands));
		liquid_antimatter = new ACBlock(
				new BlockAntiliquid().setResistance(500.0F).setLightLevel(0.5F)
						.setTranslationKey("antiwater"));
		coralium_stone_brick = new ACBlock(0,
				new BlockACBrick(1.5F, 10.0F, MapColor.CYAN).setTranslationKey("cstonebrick"),
				abyssal_wasteland);
		chiseled_coralium_stone_brick = new ACBlock(1,
				new BlockACBrick(1.5F, 10.0F, MapColor.CYAN).remap("cstonebrick",
								BlockACBrick.EnumBrickType.CHISELED)
						.setTranslationKey("chiseled_coralium_stone_brick"), abyssal_wasteland);
		cracked_coralium_stone_brick = new ACBlock(2,
				new BlockACBrick(1.5F, 10.0F, MapColor.CYAN).remap("cstonebrick",
								BlockACBrick.EnumBrickType.CRACKED)
						.setTranslationKey("cracked_coralium_stone_brick"), abyssal_wasteland);
		coralium_stone_brick_fence = new ACBlock(
				new BlockACFence(Material.ROCK, SoundType.STONE, MapColor.CYAN).setHardness(1.5F)
						.setResistance(10.0F).setTranslationKey("cstonebrickfence"),
				abyssal_wasteland);
		coralium_stone_brick_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, SoundType.STONE, MapColor.CYAN).setHardness(
						1.5F).setResistance(10.0F).setTranslationKey("cstonebrickslab1"),
				new ItemSlabAC(coralium_stone_brick_slab.getBlock(),
						coralium_stone_brick_slab.getBlock(),
						cstonebrickslab2.getBlock()).setUnlockCondition(
						abyssal_wasteland)).setStateMap(BlockACSlab.VARIANT_PROPERTY);
		cstonebrickslab2 = new ACBlock(new BlockACDoubleSlab(coralium_stone_brick_slab.getBlock(),
				Material.ROCK).setHardness(1.5F).setResistance(10.0F)
				.setTranslationKey("cstonebrickslab2"),
				new ItemSlabAC(cstonebrickslab2.getBlock(), coralium_stone_brick_slab.getBlock(),
						cstonebrickslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		coralium_stone_brick_stairs = new ACBlock(
				new BlockACStairs(coralium_stone_brick.getBlock(), "pickaxe", 0).setHardness(1.5F)
						.setResistance(10.0F).setTranslationKey("cstonebrickstairs"),
				abyssal_wasteland);
		coralium_stone_button = new ACBlock(
				new BlockACButton(false, "cstone").setHardness(0.6F).setResistance(12.0F)
						.setTranslationKey("cstonebutton"), abyssal_wasteland);
		coralium_stone_pressure_plate = new ACBlock(
				new BlockACPressureplate("cstone", Material.ROCK,
						BlockACPressureplate.Sensitivity.MOBS, SoundType.STONE).setHardness(0.6F)
						.setResistance(12.0F).setTranslationKey("cstonepplate"),
				abyssal_wasteland);
		chagaroth_altar_top = new ACBlock(
				new BlockDreadAltarTop().setHardness(30.0F).setResistance(300.0F)
						.setCreativeTab(ACTabs.tabDecoration).setTranslationKey("dreadaltartop"),
				dreadlands, true);
		chagaroth_altar_bottom = new ACBlock(
				new BlockDreadAltarBottom().setHardness(30.0F).setResistance(300.0F)
						.setCreativeTab(ACTabs.tabDecoration).setTranslationKey(
								"dreadaltarbottom"),
				dreadlands, true);
		crystallizer_idle = new ACBlock(
				new BlockCrystallizer(false).setHardness(2.5F).setResistance(12.0F)
						.setTranslationKey("crystallizer"), dreadlands);
		crystallizer_active = new ACBlock(
				new BlockCrystallizer(true).setHardness(2.5F).setResistance(12.0F)
						.setLightLevel(0.875F).setTranslationKey("crystallizer_on"), dreadlands);
		transmutator_idle = new ACBlock(
				new BlockTransmutator(false).setHardness(2.5F).setResistance(12.0F)
						.setTranslationKey("transmutator"), abyssal_wasteland);
		transmutator_active = new ACBlock(
				new BlockTransmutator(true).setHardness(2.5F).setResistance(12.0F)
						.setLightLevel(0.875F).setTranslationKey("transmutator_on"),
				abyssal_wasteland);
		dreadguard_spawner =
				new ACBlock(new BlockSingleMobSpawner().setTranslationKey("dreadguardspawner"));
		chagaroth_spawner =
				new ACBlock(new BlockSingleMobSpawner().setTranslationKey("chagarothspawner"));
		dreadlands_wood_fence = new ACBlock(
				new BlockACFence(Material.WOOD, SoundType.WOOD, MapColor.RED).setHardness(2.0F)
						.setResistance(5.0F).setTranslationKey("drtfence"), dreadlands);
		nitre_ore = new ACBlock(new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("nitreore"));
		abyssal_iron_ore = new ACBlock(new BlockACOre(2, 3.0F, 6.0F).setTranslationKey(
				"abyiroore"),
				abyssal_wasteland);
		abyssal_gold_ore =
				new ACBlock(new BlockACOre(2, 5.0F, 10.0F).setTranslationKey("abygolore"),
						abyssal_wasteland);
		abyssal_diamond_ore =
				new ACBlock(new BlockACOre(2, 5.0F, 10.0F).setTranslationKey("abydiaore"),
						abyssal_wasteland);
		abyssal_nitre_ore =
				new ACBlock(new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("abynitore"));
		abyssal_tin_ore = new ACBlock(new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("abytinore"
		));
		abyssal_copper_ore =
				new ACBlock(new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("abycopore"));
		pearlescent_coralium_ore =
				new ACBlock(new BlockACOre(5, 8.0F, 10.0F).setTranslationKey("abypcorore"),
						abyssal_wasteland);
		liquified_coralium_ore =
				new ACBlock(new BlockACOre(4, 10.0F, 12.0F).setTranslationKey("abylcorore"),
						abyssal_wasteland);
		solid_lava = new ACBlock(new BlockSolidLava("solidlava"));
		ethaxium_brick = new ACBlock(0,
				new BlockACBrick(8, 100.0F, Float.MAX_VALUE, MapColor.CLOTH).setTranslationKey(
						"ethaxiumbrick"), omothol);
		chiseled_ethaxium_brick = new ACBlock(1,
				new BlockACBrick(8, 100.0F, Float.MAX_VALUE, MapColor.CLOTH).remap("ethaxiumbrick",
								BlockACBrick.EnumBrickType.CHISELED)
						.setTranslationKey("chiseled_ethaxium_brick"), omothol);
		cracked_ethaxium_brick = new ACBlock(2,
				new BlockACBrick(8, 100.0F, Float.MAX_VALUE, MapColor.CLOTH).remap("ethaxiumbrick",
								BlockACBrick.EnumBrickType.CRACKED)
						.setTranslationKey("cracked_ethaxium_brick"), omothol);
		ethaxium_pillar = new ACBlock(
				new BlockEthaxiumPillar(100.0F, MapColor.CLOTH).setTranslationKey(
						"ethaxiumpillar"),
				omothol);
		ethaxium_brick_stairs = new ACBlock(
				new BlockACStairs(ethaxium_brick.getBlock(), "pickaxe", 8).setHardness(100.0F)
						.setResistance(Float.MAX_VALUE).setTranslationKey("ethaxiumbrickstairs"),
				omothol);
		ethaxium_brick_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, "pickaxe", 8, SoundType.STONE,
						MapColor.CLOTH).setHardness(100.0F).setResistance(Float.MAX_VALUE)
						.setTranslationKey("ethaxiumbrickslab1"),
				new ItemSlabAC(ethaxium_brick_slab.getBlock(), ethaxium_brick_slab.getBlock(),
						ethaxiumslab2.getBlock()).setUnlockCondition(omothol)).setStateMap(
				BlockACSlab.VARIANT_PROPERTY);
		ethaxiumslab2 = new ACBlock(
				new BlockACDoubleSlab(ethaxium_brick_slab.getBlock(), Material.ROCK, "pickaxe",
						8).setHardness(100.0F).setResistance(Float.MAX_VALUE)
						.setTranslationKey("ethaxiumbrickslab2"),
				new ItemSlabAC(ethaxiumslab2.getBlock(), ethaxium_brick_slab.getBlock(),
						ethaxiumslab2.getBlock()).setUnlockCondition(omothol));
		ethaxium_brick_fence = new ACBlock(
				new BlockACFence(Material.ROCK, "pickaxe", 8, SoundType.STONE,
						MapColor.CLOTH).setHardness(100.0F).setResistance(Float.MAX_VALUE)
						.setTranslationKey("ethaxiumfence"), omothol);
		engraver = new ACBlock(new BlockEngraver().setHardness(2.5F).setResistance(12.0F)
				.setTranslationKey("engraver"), omothol, true);
		house = new ACBlock(new BlockHouse().setHardness(1.0F).setResistance(Float.MAX_VALUE)
				.setTranslationKey("engraver_on"));
		materializer =
				new ACBlock(new BlockMaterializer().setTranslationKey("materializer"), omothol);
		dark_ethaxium_brick = new ACBlock(0,
				new BlockACBrick(8, 150.0F, Float.MAX_VALUE).setTranslationKey(
						"darkethaxiumbrick"),
				omothol);
		chiseled_dark_ethaxium_brick = new ACBlock(1,
				new BlockACBrick(8, 150.0F, Float.MAX_VALUE).remap("darkethaxiumbrick",
								BlockACBrick.EnumBrickType.CHISELED)
						.setTranslationKey("chiseled_dark_ethaxium_brick"), omothol);
		cracked_dark_ethaxium_brick = new ACBlock(2,
				new BlockACBrick(8, 150.0F, Float.MAX_VALUE).remap("darkethaxiumbrick",
								BlockACBrick.EnumBrickType.CRACKED)
						.setTranslationKey("cracked_dark_ethaxium_brick"), omothol);
		dark_ethaxium_pillar = new ACBlock(
				new BlockEthaxiumPillar(150.0F, MapColor.STONE).setTranslationKey(
						"darkethaxiumpillar"), omothol);
		dark_ethaxium_brick_stairs = new ACBlock(
				new BlockACStairs(dark_ethaxium_brick.getBlock(), "pickaxe", 8).setHardness(150.0F)
						.setResistance(Float.MAX_VALUE)
						.setTranslationKey("darkethaxiumbrickstairs"), omothol);
		dark_ethaxium_brick_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, "pickaxe", 8, SoundType.STONE).setHardness(
								150.0F).setResistance(Float.MAX_VALUE)
						.setTranslationKey("darkethaxiumbrickslab1"),
				new ItemSlabAC(dark_ethaxium_brick_slab.getBlock(),
						dark_ethaxium_brick_slab.getBlock(),
						ethaxiumslab2.getBlock()).setUnlockCondition(omothol)).setStateMap(
				BlockACSlab.VARIANT_PROPERTY);
		darkethaxiumslab2 = new ACBlock(
				new BlockACDoubleSlab(dark_ethaxium_brick_slab.getBlock(), Material.ROCK,
						"pickaxe",
						8).setHardness(150.0F).setResistance(Float.MAX_VALUE)
						.setTranslationKey("darkethaxiumbrickslab2"),
				new ItemSlabAC(darkethaxiumslab2.getBlock(), dark_ethaxium_brick_slab.getBlock(),
						darkethaxiumslab2.getBlock()).setUnlockCondition(omothol));
		dark_ethaxium_brick_fence = new ACBlock(
				new BlockACFence(Material.ROCK, "pickaxe", 8, SoundType.STONE,
						MapColor.STONE).setHardness(150.0F).setResistance(Float.MAX_VALUE)
						.setTranslationKey("darkethaxiumbrickfence"), omothol);
		ritual_altar_stone = new ACBlock(0,
				new BlockRitualAltar(Blocks.COBBLESTONE::getDefaultState, 0,
						BlockRitualAltar.EnumRitualMatType.COBBLESTONE).setTranslationKey(
						"ritualaltar"));
		ritual_altar_darkstone = new ACBlock(1,
				new BlockRitualAltar(() -> darkstone_cobblestone.getBlock().getDefaultState(), 0,
						BlockRitualAltar.EnumRitualMatType.DARKSTONE_COBBLESTONE).setTranslationKey(
						"ritualaltar"));
		ritual_altar_abyssal_stone = new ACBlock(2,
				new BlockRitualAltar(() -> abyssal_cobblestone.getBlock().getDefaultState(), 1,
						BlockRitualAltar.EnumRitualMatType.ABYSSAL_STONE_BRICK).setTranslationKey(
						"ritualaltar"));
		ritual_altar_coralium_stone = new ACBlock(3,
				new BlockRitualAltar(() -> coralium_cobblestone.getBlock().getDefaultState(), 1,
						BlockRitualAltar.EnumRitualMatType.CORALIUM_STONE_BRICK).setTranslationKey(
						"ritualaltar"));
		ritual_altar_dreadstone = new ACBlock(4,
				new BlockRitualAltar(() -> dreadstone_cobblestone.getBlock().getDefaultState(), 2,
						BlockRitualAltar.EnumRitualMatType.DREADSTONE_BRICK).setTranslationKey(
						"ritualaltar"));
		ritual_altar_abyssalnite_stone = new ACBlock(5,
				new BlockRitualAltar(() -> abyssalnite_cobblestone.getBlock().getDefaultState(), 2,
						BlockRitualAltar.EnumRitualMatType.ABYSSALNITE_STONE_BRICK).setTranslationKey(
						"ritualaltar"));
		ritual_altar_ethaxium = new ACBlock(6,
				new BlockRitualAltar(() -> ethaxium_brick.getBlock().getDefaultState(), 3,
						BlockRitualAltar.EnumRitualMatType.ETHAXIUM_BRICK).setTranslationKey(
						"ritualaltar"));
		ritual_altar_dark_ethaxium = new ACBlock(7,
				new BlockRitualAltar(() -> dark_ethaxium_brick.getBlock().getDefaultState(), 3,
						BlockRitualAltar.EnumRitualMatType.DARK_ETHAXIUM_BRICK).setTranslationKey(
						"ritualaltar"));
		ritual_pedestal_stone = new ACBlock(0,
				new BlockRitualPedestal(Blocks.COBBLESTONE::getDefaultState, 0,
						BlockRitualAltar.EnumRitualMatType.COBBLESTONE).setTranslationKey(
						"ritualpedestal"));
		ritual_pedestal_darkstone = new ACBlock(1,
				new BlockRitualPedestal(() -> darkstone_cobblestone.getBlock().getDefaultState()
						, 0,
						BlockRitualAltar.EnumRitualMatType.DARKSTONE_COBBLESTONE).setTranslationKey(
						"ritualpedestal"));
		ritual_pedestal_abyssal_stone = new ACBlock(2,
				new BlockRitualPedestal(() -> abyssal_cobblestone.getBlock().getDefaultState(), 1,
						BlockRitualAltar.EnumRitualMatType.ABYSSAL_STONE_BRICK).setTranslationKey(
						"ritualpedestal"));
		ritual_pedestal_coralium_stone = new ACBlock(3,
				new BlockRitualPedestal(() -> coralium_cobblestone.getBlock().getDefaultState(), 1,
						BlockRitualAltar.EnumRitualMatType.CORALIUM_STONE_BRICK).setTranslationKey(
						"ritualpedestal"));
		ritual_pedestal_dreadstone = new ACBlock(4,
				new BlockRitualPedestal(() -> dreadstone_cobblestone.getBlock().getDefaultState(),
						2, BlockRitualAltar.EnumRitualMatType.DREADSTONE_BRICK).setTranslationKey(
						"ritualpedestal"));
		ritual_pedestal_abyssalnite_stone = new ACBlock(5,
				new BlockRitualPedestal(() -> abyssalnite_cobblestone.getBlock().getDefaultState(),
						2,
						BlockRitualAltar.EnumRitualMatType.ABYSSALNITE_STONE_BRICK).setTranslationKey(
						"ritualpedestal"));
		ritual_pedestal_ethaxium = new ACBlock(6,
				new BlockRitualPedestal(() -> ethaxium_brick.getBlock().getDefaultState(), 3,
						BlockRitualAltar.EnumRitualMatType.ETHAXIUM_BRICK).setTranslationKey(
						"ritualpedestal"));
		ritual_pedestal_dark_ethaxium = new ACBlock(7,
				new BlockRitualPedestal(() -> dark_ethaxium_brick.getBlock().getDefaultState(), 3,
						BlockRitualAltar.EnumRitualMatType.DARK_ETHAXIUM_BRICK).setTranslationKey(
						"ritualpedestal"));
		shoggoth_ooze = new ACBlock(new BlockShoggothOoze().setTranslationKey("shoggothblock"),
				new ItemShoggothOoze(shoggoth_ooze.getBlock()));
		cthulhu_statue = new ACBlock(
				new BlockStatue(BlockStatue.EnumDeityType.CTHULHU).setTranslationKey(
						"cthulhustatue"), true);
		hastur_statue = new ACBlock(
				new BlockStatue(BlockStatue.EnumDeityType.HASTUR).setTranslationKey(
						"hasturstatue"),
				true);
		jzahar_statue = new ACBlock(
				new BlockStatue(BlockStatue.EnumDeityType.JZAHAR).setTranslationKey(
						"jzaharstatue"),
				true);
		azathoth_statue = new ACBlock(
				new BlockStatue(BlockStatue.EnumDeityType.AZATHOTH).setTranslationKey(
						"azathothstatue"), true);
		nyarlathotep_statue = new ACBlock(
				new BlockStatue(BlockStatue.EnumDeityType.NYARLATHOTEP).setTranslationKey(
						"nyarlathotepstatue"), true);
		yog_sothoth_statue = new ACBlock(
				new BlockStatue(BlockStatue.EnumDeityType.YOGSOTHOTH).setTranslationKey(
						"yogsothothstatue"), true);
		shub_niggurath_statue = new ACBlock(
				new BlockStatue(BlockStatue.EnumDeityType.SHUBNIGGURATH).setTranslationKey(
						"shubniggurathstatue"), true);
		shoggoth_biomass = new ACBlock(new BlockShoggothBiomass());
		energy_pedestal = new ACBlock(new BlockEnergyPedestal(),
				new ItemPEContainerBlock(energy_pedestal.getBlock()));
		monolith_pillar = new ACBlock(new BlockMonolithPillar());
		sacrificial_altar = new ACBlock(new BlockSacrificialAltar(),
				new ItemPEContainerBlock(sacrificial_altar.getBlock()));
		overworld_energy_pedestal = new ACBlock("tieredenergypedestal_0",
				new BlockTieredEnergyPedestal(
						BlockTieredEnergyPedestal.EnumDimType.OVERWORLD).setTranslationKey(
						"overworld_energy_pedestal"),
				new ItemPEContainerBlock(overworld_energy_pedestal.getBlock()));
		abyssal_wasteland_energy_pedestal = new ACBlock("tieredenergypedestal_1",
				new BlockTieredEnergyPedestal(
						BlockTieredEnergyPedestal.EnumDimType.ABYSSAL_WASTELAND).setTranslationKey(
						"abyssal_wasteland_energy_pedestal"),
				new ItemPEContainerBlock(abyssal_wasteland_energy_pedestal.getBlock()),
				abyssal_wasteland);
		dreadlands_energy_pedestal = new ACBlock("tieredenergypedestal_2",
				new BlockTieredEnergyPedestal(
						BlockTieredEnergyPedestal.EnumDimType.DREADLANDS).setTranslationKey(
						"dreadlands_energy_pedestal"),
				new ItemPEContainerBlock(dreadlands_energy_pedestal.getBlock()), dreadlands);
		omothol_energy_pedestal = new ACBlock("tieredenergypedestal_3",
				new BlockTieredEnergyPedestal(
						BlockTieredEnergyPedestal.EnumDimType.OMOTHOL).setTranslationKey(
						"omothol_energy_pedestal"),
				new ItemPEContainerBlock(omothol_energy_pedestal.getBlock()), omothol);
		overworld_sacrificial_altar = new ACBlock("tieredsacrificialaltar_0",
				new BlockTieredSacrificialAltar(
						BlockTieredEnergyPedestal.EnumDimType.OVERWORLD).setTranslationKey(
						"overworld_sacrificial_altar"),
				new ItemPEContainerBlock(overworld_sacrificial_altar.getBlock()));
		abyssal_wasteland_sacrificial_altar = new ACBlock("tieredsacrificialaltar_1",
				new BlockTieredSacrificialAltar(
						BlockTieredEnergyPedestal.EnumDimType.ABYSSAL_WASTELAND).setTranslationKey(
						"abyssal_wasteland_sacrificial_altar"),
				new ItemPEContainerBlock(abyssal_wasteland_sacrificial_altar.getBlock()),
				abyssal_wasteland);
		dreadlands_sacrificial_altar = new ACBlock("tieredsacrificialaltar_2",
				new BlockTieredSacrificialAltar(
						BlockTieredEnergyPedestal.EnumDimType.DREADLANDS).setTranslationKey(
						"dreadlands_sacrificial_altar"),
				new ItemPEContainerBlock(dreadlands_sacrificial_altar.getBlock()), dreadlands);
		omothol_sacrificial_altar = new ACBlock("tieredsacrificialaltar_3",
				new BlockTieredSacrificialAltar(
						BlockTieredEnergyPedestal.EnumDimType.OMOTHOL).setTranslationKey(
						"omothol_sacrificial_altar"),
				new ItemPEContainerBlock(omothol_sacrificial_altar.getBlock()), omothol);
		jzahar_spawner =
				new ACBlock(new BlockSingleMobSpawner().setTranslationKey("jzaharspawner"));
		minion_of_the_gatekeeper_spawner = new ACBlock(
				new BlockSingleMobSpawner().setTranslationKey("gatekeeperminionspawner"));
		mimic_fire = new ACBlock(new BlockMimicFire().setTranslationKey("fire")).setStateMap(
				BlockFire.AGE);
		decorative_cthulhu_statue = new ACBlock(
				new BlockDecorativeStatue(BlockStatue.EnumDeityType.CTHULHU).setTranslationKey(
						"cthulhustatue"),
				new ItemDecorativeStatueBlock(decorative_cthulhu_statue.getBlock()), true);
		decorative_hastur_statue = new ACBlock(
				new BlockDecorativeStatue(BlockStatue.EnumDeityType.HASTUR).setTranslationKey(
						"hasturstatue"),
				new ItemDecorativeStatueBlock(decorative_hastur_statue.getBlock()), true);
		decorative_jzahar_statue = new ACBlock(
				new BlockDecorativeStatue(BlockStatue.EnumDeityType.JZAHAR).setTranslationKey(
						"jzaharstatue"),
				new ItemDecorativeStatueBlock(decorative_jzahar_statue.getBlock()), true);
		decorative_azathoth_statue = new ACBlock(
				new BlockDecorativeStatue(BlockStatue.EnumDeityType.AZATHOTH).setTranslationKey(
						"azathothstatue"),
				new ItemDecorativeStatueBlock(decorative_azathoth_statue.getBlock()), true);
		decorative_nyarlathotep_statue = new ACBlock(
				new BlockDecorativeStatue(BlockStatue.EnumDeityType.NYARLATHOTEP).setTranslationKey(
						"nyarlathotepstatue"),
				new ItemDecorativeStatueBlock(decorative_nyarlathotep_statue.getBlock()), true);
		decorative_yog_sothoth_statue = new ACBlock(
				new BlockDecorativeStatue(BlockStatue.EnumDeityType.YOGSOTHOTH).setTranslationKey(
						"yogsothothstatue"),
				new ItemDecorativeStatueBlock(decorative_yog_sothoth_statue.getBlock()), true);
		decorative_shub_niggurath_statue = new ACBlock(new BlockDecorativeStatue(
				BlockStatue.EnumDeityType.SHUBNIGGURATH).setTranslationKey("shubniggurathstatue"),
				new ItemDecorativeStatueBlock(decorative_shub_niggurath_statue.getBlock()), true);
		iron_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.IRON),
				new ItemCrystalClusterBlock(iron_crystal_cluster.getBlock()));
		gold_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.GOLD),
				new ItemCrystalClusterBlock(gold_crystal_cluster.getBlock()));
		sulfur_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.SULFUR),
				new ItemCrystalClusterBlock(sulfur_crystal_cluster.getBlock()));
		carbon_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.CARBON),
				new ItemCrystalClusterBlock(carbon_crystal_cluster.getBlock()));
		oxygen_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.OXYGEN),
				new ItemCrystalClusterBlock(oxygen_crystal_cluster.getBlock()));
		hydrogen_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.HYDROGEN),
				new ItemCrystalClusterBlock(hydrogen_crystal_cluster.getBlock()));
		nitrogen_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.NITROGEN),
				new ItemCrystalClusterBlock(nitrogen_crystal_cluster.getBlock()));
		phosphorus_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.PHOSPHORUS),
				new ItemCrystalClusterBlock(phosphorus_crystal_cluster.getBlock()));
		potassium_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.POTASSIUM),
				new ItemCrystalClusterBlock(potassium_crystal_cluster.getBlock()));
		nitrate_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.NITRATE),
				new ItemCrystalClusterBlock(nitrate_crystal_cluster.getBlock()));
		methane_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.METHANE),
				new ItemCrystalClusterBlock(methane_crystal_cluster.getBlock()));
		redstone_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.REDSTONE),
				new ItemCrystalClusterBlock(redstone_crystal_cluster.getBlock()));
		abyssalnite_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.ABYSSALNITE),
				new ItemCrystalClusterBlock(abyssalnite_crystal_cluster.getBlock()));
		coralium_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.CORALIUM),
				new ItemCrystalClusterBlock(coralium_crystal_cluster.getBlock()));
		dreadium_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.DREADIUM),
				new ItemCrystalClusterBlock(dreadium_crystal_cluster.getBlock()));
		blaze_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType.BLAZE),
				new ItemCrystalClusterBlock(blaze_crystal_cluster.getBlock()));
		tin_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.TIN),
				new ItemCrystalClusterBlock(tin_crystal_cluster.getBlock()));
		copper_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.COPPER),
				new ItemCrystalClusterBlock(copper_crystal_cluster.getBlock()));
		silicon_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.SILICON),
				new ItemCrystalClusterBlock(silicon_crystal_cluster.getBlock()));
		magnesium_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.MAGNESIUM),
				new ItemCrystalClusterBlock(magnesium_crystal_cluster.getBlock()));
		aluminium_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.ALUMINIUM),
				new ItemCrystalClusterBlock(aluminium_crystal_cluster.getBlock()));
		silica_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.SILICA),
				new ItemCrystalClusterBlock(silica_crystal_cluster.getBlock()));
		alumina_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.ALUMINA),
				new ItemCrystalClusterBlock(alumina_crystal_cluster.getBlock()));
		magnesia_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.MAGNESIA),
				new ItemCrystalClusterBlock(magnesia_crystal_cluster.getBlock()));
		zinc_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.ZINC),
				new ItemCrystalClusterBlock(zinc_crystal_cluster.getBlock()));
		calcium_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.CALCIUM),
				new ItemCrystalClusterBlock(calcium_crystal_cluster.getBlock()));
		beryllium_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.BERYLLIUM),
				new ItemCrystalClusterBlock(beryllium_crystal_cluster.getBlock()));
		beryl_crystal_cluster = new ACBlock("crystalcluster",
				new BlockCrystalCluster().remap(BlockCrystalCluster.EnumCrystalType2.BERYL),
				new ItemCrystalClusterBlock(beryl_crystal_cluster.getBlock()));
		energy_collector = new ACBlock(new BlockEnergyCollector(),
				new ItemPEContainerBlock(energy_collector.getBlock()));
		energy_relay = new ACBlock(new BlockEnergyRelay(),
				new ItemPEContainerBlock(energy_relay.getBlock()));
		energy_container = new ACBlock(new BlockEnergyContainer(),
				new ItemPEContainerBlock(energy_container.getBlock()));
		overworld_energy_collector = new ACBlock("tieredenergycollector_0",
				new BlockTieredEnergyCollector(
						BlockTieredEnergyPedestal.EnumDimType.OVERWORLD).setTranslationKey(
						"overworld_energy_collector"),
				new ItemPEContainerBlock(overworld_energy_collector.getBlock()));
		abyssal_wasteland_energy_collector = new ACBlock("tieredenergycollector_1",
				new BlockTieredEnergyCollector(
						BlockTieredEnergyPedestal.EnumDimType.ABYSSAL_WASTELAND).setTranslationKey(
						"abyssal_wasteland_energy_collector"),
				new ItemPEContainerBlock(abyssal_wasteland_energy_collector.getBlock()),
				abyssal_wasteland);
		dreadlands_energy_collector = new ACBlock("tieredenergycollector_2",
				new BlockTieredEnergyCollector(
						BlockTieredEnergyPedestal.EnumDimType.DREADLANDS).setTranslationKey(
						"dreadlands_energy_collector"),
				new ItemPEContainerBlock(dreadlands_energy_collector.getBlock()), dreadlands);
		omothol_energy_collector = new ACBlock("tieredenergycollector_3",
				new BlockTieredEnergyCollector(
						BlockTieredEnergyPedestal.EnumDimType.OMOTHOL).setTranslationKey(
						"omothol_energy_collector"),
				new ItemPEContainerBlock(omothol_energy_collector.getBlock()), omothol);
		overworld_energy_relay = new ACBlock("owenergyrelay", new BlockTieredEnergyRelay(
				BlockTieredEnergyPedestal.EnumDimType.OVERWORLD).setTranslationKey(
				"overworld_energy_relay"),
				new ItemPEContainerBlock(overworld_energy_relay.getBlock()));
		abyssal_wasteland_energy_relay = new ACBlock("awenergyrelay", new BlockTieredEnergyRelay(
				BlockTieredEnergyPedestal.EnumDimType.ABYSSAL_WASTELAND).setTranslationKey(
				"abyssal_wasteland_energy_relay"),
				new ItemPEContainerBlock(abyssal_wasteland_energy_relay.getBlock()),
				abyssal_wasteland);
		dreadlands_energy_relay = new ACBlock("dlenergyrelay", new BlockTieredEnergyRelay(
				BlockTieredEnergyPedestal.EnumDimType.DREADLANDS).setTranslationKey(
				"dreadlands_energy_relay"),
				new ItemPEContainerBlock(dreadlands_energy_relay.getBlock()), dreadlands);
		omothol_energy_relay = new ACBlock("omtenergyrelay", new BlockTieredEnergyRelay(
				BlockTieredEnergyPedestal.EnumDimType.OMOTHOL).setTranslationKey(
				"omothol_energy_relay"), new ItemPEContainerBlock(omothol_energy_relay.getBlock()),
				omothol);
		overworld_energy_container = new ACBlock("tieredenergycontainer_0",
				new BlockTieredEnergyContainer(
						BlockTieredEnergyPedestal.EnumDimType.OVERWORLD).setTranslationKey(
						"overworld_energy_container"),
				new ItemPEContainerBlock(overworld_energy_container.getBlock()));
		abyssal_wasteland_energy_container = new ACBlock("tieredenergycontainer_1",
				new BlockTieredEnergyContainer(
						BlockTieredEnergyPedestal.EnumDimType.ABYSSAL_WASTELAND).setTranslationKey(
						"abyssal_wasteland_energy_container"),
				new ItemPEContainerBlock(abyssal_wasteland_energy_container.getBlock()),
				abyssal_wasteland);
		dreadlands_energy_container = new ACBlock("tieredenergycontainer_2",
				new BlockTieredEnergyContainer(
						BlockTieredEnergyPedestal.EnumDimType.DREADLANDS).setTranslationKey(
						"dreadlands_energy_container"),
				new ItemPEContainerBlock(dreadlands_energy_container.getBlock()), dreadlands);
		omothol_energy_container = new ACBlock("tieredenergycontainer_3",
				new BlockTieredEnergyContainer(
						BlockTieredEnergyPedestal.EnumDimType.OMOTHOL).setTranslationKey(
						"omothol_energy_container"),
				new ItemPEContainerBlock(omothol_energy_container.getBlock()), omothol);
		abyssal_sand = new ACBlock(new BlockAbyssalSand(), abyssal_wasteland);
		fused_abyssal_sand = new ACBlock(new BlockFusedAbyssalSand(), abyssal_wasteland);
		abyssal_sand_glass = new ACBlock(new BlockAbyssalSandGlass(), abyssal_wasteland);
		dreadlands_dirt = new ACBlock(new BlockDreadlandsDirt(), dreadlands);
		abyssal_cobblestone_stairs = new ACBlock(
				new BlockACStairs(abyssal_cobblestone.getBlock(), "pickaxe", 2).setTranslationKey(
						"abyssalcobblestonestairs"), abyssal_wasteland);
		abyssal_cobblestone_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, "pickaxe", 2, SoundType.STONE,
						MapColor.GREEN).setHardness(2.6F).setResistance(12.0F)
						.setTranslationKey("abyssalcobblestoneslab1"),
				new ItemSlabAC(abyssal_cobblestone_slab.getBlock(),
						abyssal_cobblestone_slab.getBlock(),
						abycobbleslab2.getBlock()).setUnlockCondition(
						abyssal_wasteland)).setStateMap(BlockACSlab.VARIANT_PROPERTY);
		abycobbleslab2 = new ACBlock(
				new BlockACDoubleSlab(abyssal_cobblestone_slab.getBlock(), Material.ROCK,
						"pickaxe",
						2).setHardness(2.6F).setResistance(12.0F)
						.setTranslationKey("abyssalcobblestoneslab2"),
				new ItemSlabAC(abycobbleslab2.getBlock(), abyssal_cobblestone_slab.getBlock(),
						abycobbleslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		abyssal_cobblestone_wall = new ACBlock(
				new BlockACWall(abyssal_cobblestone.getBlock(), 2).setHardness(2.6F)
						.setResistance(12.0F).setTranslationKey("abyssalcobblestonewall"),
				abyssal_wasteland).setStateMap(BlockWall.VARIANT);
		dreadstone_cobblestone_stairs = new ACBlock(
				new BlockACStairs(dreadstone_cobblestone.getBlock(), "pickaxe",
						4).setTranslationKey("dreadstonecobblestonestairs"), dreadlands);
		dreadstone_cobblestone_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, "pickaxe", 4, SoundType.STONE,
						MapColor.RED).setHardness(3.3F).setResistance(20.0F)
						.setTranslationKey("dreadstonecobblestoneslab1"),
				new ItemSlabAC(dreadstone_cobblestone_slab.getBlock(),
						dreadstone_cobblestone_slab.getBlock(),
						dreadcobbleslab2.getBlock()).setUnlockCondition(dreadlands)).setStateMap(
				BlockACSlab.VARIANT_PROPERTY);
		dreadcobbleslab2 = new ACBlock(
				new BlockACDoubleSlab(dreadstone_cobblestone_slab.getBlock(), Material.ROCK,
						"pickaxe", 4).setHardness(3.3F).setResistance(20.0F)
						.setTranslationKey("dreadstonecobblestoneslab2"),
				new ItemSlabAC(dreadcobbleslab2.getBlock(), dreadstone_cobblestone_slab.getBlock(),
						dreadcobbleslab2.getBlock()).setUnlockCondition(dreadlands));
		dreadstone_cobblestone_wall = new ACBlock(
				new BlockACWall(dreadstone_cobblestone.getBlock(), 4).setHardness(3.3F)
						.setResistance(20.0F).setTranslationKey("dreadstonecobblestonewall"),
				dreadlands).setStateMap(BlockWall.VARIANT);
		abyssalnite_cobblestone_stairs = new ACBlock(
				new BlockACStairs(abyssalnite_cobblestone.getBlock(), "pickaxe",
						4).setTranslationKey("abyssalnitecobblestonestairs"), dreadlands);
		abyssalnite_cobblestone_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, "pickaxe", 4, SoundType.STONE,
						MapColor.PURPLE).setHardness(3.3F).setResistance(20.0F)
						.setTranslationKey("abyssalnitecobblestoneslab1"),
				new ItemSlabAC(abyssalnite_cobblestone_slab.getBlock(),
						abyssalnite_cobblestone_slab.getBlock(),
						abydreadcobbleslab2.getBlock()).setUnlockCondition(dreadlands)).setStateMap(
				BlockACSlab.VARIANT_PROPERTY);
		abydreadcobbleslab2 = new ACBlock(
				new BlockACDoubleSlab(abyssalnite_cobblestone_slab.getBlock(), Material.ROCK,
						"pickaxe", 4).setHardness(3.3F).setResistance(20.0F)
						.setTranslationKey("abyssalnitecobblestoneslab2"),
				new ItemSlabAC(abydreadcobbleslab2.getBlock(),
						abyssalnite_cobblestone_slab.getBlock(),
						abydreadcobbleslab2.getBlock()).setUnlockCondition(dreadlands));
		abyssalnite_cobblestone_wall = new ACBlock(
				new BlockACWall(abyssalnite_cobblestone.getBlock(), 4).setHardness(3.3F)
						.setResistance(20.0F).setTranslationKey("abyssalnitecobblestonewall"),
				dreadlands).setStateMap(BlockWall.VARIANT);
		coralium_cobblestone_stairs = new ACBlock(
				new BlockACStairs(coralium_cobblestone.getBlock()).setTranslationKey(
						"coraliumcobblestonestairs"), abyssal_wasteland);
		coralium_cobblestone_slab = new ACBlock(
				new BlockACSingleSlab(Material.ROCK, SoundType.STONE, MapColor.CYAN).setHardness(
						2.0F).setResistance(10.0F).setTranslationKey("coraliumcobblestoneslab1"),
				new ItemSlabAC(coralium_cobblestone_slab.getBlock(),
						coralium_cobblestone_slab.getBlock(),
						cstonecobbleslab2.getBlock()).setUnlockCondition(
						abyssal_wasteland)).setStateMap(BlockACSlab.VARIANT_PROPERTY);
		cstonecobbleslab2 = new ACBlock(new BlockACDoubleSlab(coralium_cobblestone_slab.getBlock(),
				Material.ROCK).setHardness(2.0F).setResistance(10.0F)
				.setTranslationKey("coraliumcobblestoneslab2"),
				new ItemSlabAC(cstonecobbleslab2.getBlock(), coralium_cobblestone_slab.getBlock(),
						cstonecobbleslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		coralium_cobblestone_wall = new ACBlock(
				new BlockACWall(coralium_cobblestone.getBlock()).setHardness(2.0F)
						.setResistance(10.0F).setTranslationKey("coraliumcobblestonewall"),
				abyssal_wasteland).setStateMap(BlockWall.VARIANT);
		luminous_thistle = new ACBlock(new BlockLuminousThistle(), abyssal_wasteland);
		wastelands_thorn = new ACBlock(new BlockWastelandsThorn(), abyssal_wasteland);
		rending_pedestal = new ACBlock(new BlockRendingPedestal(),
				new ItemRendingPedestalBlock(rending_pedestal.getBlock()));
		state_transformer = new ACBlock(new BlockStateTransformer());
		energy_depositioner = new ACBlock(new BlockEnergyDepositioner());
		calcified_stone = new ACBlock(new BlockCalcifiedStone());
		darklands_oak_door = new ACBlock(new BlockACDoor(Material.WOOD, 3.0F, 15.0F,
				SoundType.WOOD,
				MapColor.BROWN).setTranslationKey("door_dlt"), (ItemBlock) null).setStateMap(
				BlockDoor.POWERED);
		dreadlands_door = new ACBlock(new BlockACDoor(Material.WOOD, 3.0F, 15.0F, SoundType.WOOD,
				MapColor.RED).setTranslationKey("door_drt"), (ItemBlock) null).setStateMap(
				BlockDoor.POWERED);
		multi_block = new ACBlock(new BlockMultiblock());
		sequential_brewing_stand =
				new ACBlock(new BlockSequentialBrewingStand(), new DimensionCondition(-1));
		portal_anchor = new ACBlock(new BlockPortalAnchor());
	}

	public static ACBlocks getInstance() {
		if (INSTANCE == null) INSTANCE = new ACBlocks();
		return INSTANCE;
	}

	public ACBlocks registerFireInfo() {
		Blocks.FIRE.setFireInfo(darklands_oak_planks.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(DLTslab2.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(darklands_oak_slab.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(darklands_oak_fence.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(darklands_oak_stairs.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(darklands_oak_wood.getBlock(), 5, 5);
		Blocks.FIRE.setFireInfo(darklands_oak_wood_2.getBlock(), 5, 5);
		Blocks.FIRE.setFireInfo(darklands_oak_leaves.getBlock(), 30, 60);
		Blocks.FIRE.setFireInfo(dreadlands_planks.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(dreadlands_wood_fence.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(dreadlands_log.getBlock(), 5, 5);
		Blocks.FIRE.setFireInfo(dreadlands_leaves.getBlock(), 30, 60);
		return this;
	}

	public ACBlocks registerBlockTiles() {
		registerTileEntity(TileEntityCrate.class, "tileEntityCrate");
		registerTileEntity(TileEntityDGhead.class, "tileEntityDGhead");
		registerTileEntity(TileEntityPhead.class, "tileEntityPhead");
		registerTileEntity(TileEntityWhead.class, "tileEntityWhead");
		registerTileEntity(TileEntityOhead.class, "tileEntityOhead");
		registerTileEntity(TileEntityCrystallizer.class, "tileEntityCrystallizer");
		registerTileEntity(TileEntityTransmutator.class, "tileEntityTransmutator");
		registerTileEntity(TileEntityDreadguardSpawner.class, "tileEntityDradguardSpawner");
		registerTileEntity(TileEntityChagarothSpawner.class, "tileEntityChagarothSpawner");
		registerTileEntity(TileEntityEngraver.class, "tileEntityEngraver");
		registerTileEntity(TileEntityMaterializer.class, "tileEntityMaterializer");
		registerTileEntity(TileEntityRitualAltar.class, "tileEntityRitualAltar");
		registerTileEntity(TileEntityRitualPedestal.class, "tileEntityRitualPedestal");
		registerTileEntity(TileEntityStatue.class, "tileEntityStatue");
		registerTileEntity(TileEntityShoggothBiomass.class, "tileEntityShoggothBiomass");
		registerTileEntity(TileEntityEnergyPedestal.class, "tileEntityEnergyPedestal");
		registerTileEntity(TileEntitySacrificialAltar.class, "tileEntitySacrificialAltar");
		registerTileEntity(TileEntityTieredEnergyPedestal.class, "tileEntityTieredEnergyPedestal");
		registerTileEntity(TileEntityTieredSacrificialAltar.class,
				"tileEntityTieredSacrificialAltar");
		registerTileEntity(TileEntityJzaharSpawner.class, "tileEntityJzaharSpawner");
		registerTileEntity(TileEntityGatekeeperMinionSpawner.class,
				"tileEntityGatekeeperMinionSpawner");
		registerTileEntity(TileEntityEnergyCollector.class, "tileEntityEnergyCollector");
		registerTileEntity(TileEntityEnergyRelay.class, "tileEntityEnergyRelay");
		registerTileEntity(TileEntityEnergyContainer.class, "tileEntityEnergyContainer");
		registerTileEntity(TileEntityTieredEnergyCollector.class,
				"tileEntityTieredEnergyCollector");
		registerTileEntity(TileEntityTieredEnergyRelay.class, "tileEntityTieredEnergyRelay");
		registerTileEntity(TileEntityTieredEnergyContainer.class,
				"tileEntityTieredEnergyContainer");
		registerTileEntity(TileEntityRendingPedestal.class, "tileEntityRendingPedestal");
		registerTileEntity(TileEntityStateTransformer.class, "tileEntityStateTransformer");
		registerTileEntity(TileEntityEnergyDepositioner.class, "tileEntityEnergyDepositioner");
		registerTileEntity(TileEntityMultiblock.class, "tileEntityMultiblock");
		registerTileEntity(TileEntitySequentialBrewingStand.class,
				"tileEntitySequentialBrewingStand");
		registerTileEntity(TileEntityPortalAnchor.class, "tileEntityPortalAnchor");
		return this;
	}

	private static void registerTileEntity(Class<? extends TileEntity> tileEntity,
										   String resource) {
		GameRegistry.registerTileEntity(tileEntity,
				new ResourceLocation(AbyssalCraft.modid, resource));
	}

	public ACBlocks registerBlocks() {
		ACBlock[] acBlocks = getACBlocks();
		for (ACBlock acBlock : acBlocks) {
			acBlock.register();
		}
		return this;
	}

	public ACBlocks registerLiquidBlocks() {
		((BlockCLiquid) liquid_coralium.getBlock()).addBlocks();
		return this;
	}

	public ACBlocks registerItemsRenders() {
		ACBlock[] acBlocks = getACBlocks();
		for (ACBlock acBlock : acBlocks) {
			acBlock.registerItemRender();
		}
		return this;
	}

	public ACBlocks registerModels() {
		ACBlock[] acBlocks = getACBlocks();
		for (ACBlock acBlock : acBlocks) {
			acBlock.registerModel();
		}
		return this;
	}

	public ACBlock[] getACBlocks() {
		return Arrays.stream(Reflections.getStaticFields(ACItems.class))
				.filter(field -> ACBlock.class.isAssignableFrom(field.getType())).map(field -> {
					try {
						return (ACBlock) field.get(null);
					} catch (IllegalAccessException e) {
						return null;
					}
				}).toArray(ACBlock[]::new);
	}

}
