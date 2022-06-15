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

import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.lib.util.Reflections;

import java.util.Arrays;


/**
 * Contains all blocks added in AbyssalCraft
 *
 * @author shinoow
 */
public class ACBlocks {

	public static ACBlock Darkbrickslab2, Darkcobbleslab2, abyslab2, Darkstoneslab2, DLTslab2,
			Altar, dreadbrickslab2, abydreadbrickslab2, cstonebrickslab2, ethaxiumslab2, house,
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


	public static void registerBlocks() {
		ACBlock[] acBlocks = getACBlocks();
		for (ACBlock acBlock : acBlocks) {
			acBlock.register();
		}
	}

	public static void registerItemsRenders() {
		ACBlock[] acBlocks = getACBlocks();
		for (ACBlock acBlock : acBlocks) {
			acBlock.registerItemRender();
		}
	}

	public static void registerModels() {
		ACBlock[] acBlocks = getACBlocks();
		for (ACBlock acBlock : acBlocks) {
			acBlock.registerModel();
		}
	}

	public static ACBlock[] getACBlocks() {
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
