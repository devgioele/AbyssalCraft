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
package com.shinoow.abyssalcraft.api.item;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.lib.item.ItemMetadata;
import com.shinoow.abyssalcraft.lib.util.Reflections;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Arrays;
import java.util.Objects;

/**
 * Contains all items added in AbyssalCraft
 *
 * @author shinoow
 */
public class ACItems {

	public static ItemStack liquid_coralium_bucket_stack;
	public static ItemStack liquid_antimatter_bucket_stack;

	//	public static Item shadow_titan_armor_plate;
	public static Item devsword, shadowPlate, shoggoth_projectile, oblivion_catalyst, gateway_key,
			staff_of_the_gatekeeper, powerstone_tracker, eye_of_the_abyss, dreaded_gateway_key,
			dreaded_shard_of_abyssalnite, dreaded_chunk_of_abyssalnite, chunk_of_abyssalnite,
			abyssalnite_ingot, coralium_gem, coralium_gem_cluster_2, coralium_gem_cluster_3,
			coralium_gem_cluster_4, coralium_gem_cluster_5, coralium_gem_cluster_6,
			coralium_gem_cluster_7, coralium_gem_cluster_8, coralium_gem_cluster_9, coralium_pearl,
			chunk_of_coralium, refined_coralium_ingot, coralium_plate, transmutation_gem,
			coralium_plagued_flesh, coralium_plagued_flesh_on_a_bone, darkstone_pickaxe,
			darkstone_axe, darkstone_shovel, darkstone_sword, darkstone_hoe, abyssalnite_pickaxe,
			abyssalnite_axe, abyssalnite_shovel, abyssalnite_sword, abyssalnite_hoe,
			refined_coralium_pickaxe, refined_coralium_axe, refined_coralium_shovel,
			refined_coralium_sword, refined_coralium_hoe, abyssalnite_helmet,
			abyssalnite_chestplate, abyssalnite_leggings, abyssalnite_boots,
			dreaded_abyssalnite_helmet, dreaded_abyssalnite_chestplate,
			dreaded_abyssalnite_leggings, dreaded_abyssalnite_boots, refined_coralium_helmet,
			refined_coralium_chestplate, refined_coralium_leggings, refined_coralium_boots,
			plated_coralium_helmet, plated_coralium_chestplate, plated_coralium_leggings,
			plated_coralium_boots, depths_helmet, depths_chestplate, depths_leggings, depths_boots,
			shadow_fragment, shadow_shard, shadow_gem, shard_of_oblivion, coralium_longbow,
			coralium_brick, cudgel, dreadium_ingot, dread_fragment, dreadium_helmet,
			dreadium_chestplate, dreadium_leggings, dreadium_boots, dreadium_pickaxe, dreadium_axe,
			dreadium_shovel, dreadium_sword, dreadium_hoe, carbon_cluster, dense_carbon_cluster,
			methane, nitre, sulfur, crystal, crystal_shard, dread_cloth, dreadium_plate,
			dreadium_katana_blade, dreadium_katana_hilt, dreadium_katana,
			dread_plagued_gateway_key,
			rlyehian_gateway_key, dreadium_samurai_helmet, dreadium_samurai_chestplate,
			dreadium_samurai_leggings, dreadium_samurai_boots, tin_ingot, copper_ingot, anti_beef,
			anti_chicken, anti_pork, rotten_anti_flesh, anti_bone, anti_spider_eye,
			sacthoths_soul_harvesting_blade, ethaxium_brick, ethaxium_ingot, life_crystal,
			ethaxium_helmet, ethaxium_chestplate, ethaxium_leggings, ethaxium_boots,
			ethaxium_pickaxe, ethaxium_axe, ethaxium_shovel, ethaxium_sword, ethaxium_hoe, coin,
			cthulhu_engraved_coin, elder_engraved_coin, jzahar_engraved_coin, blank_engraving,
			cthulhu_engraving, elder_engraving, jzahar_engraving, eldritch_scale, omothol_flesh,
			anti_plagued_flesh, anti_plagued_flesh_on_a_bone, necronomicon,
			abyssal_wasteland_necronomicon, dreadlands_necronomicon, omothol_necronomicon,
			abyssalnomicon, small_crystal_bag, medium_crystal_bag, large_crystal_bag,
			huge_crystal_bag, shoggoth_flesh, ingot_nugget, staff_of_rending, essence, skin,
			ritual_charm, cthulhu_charm, hastur_charm, jzahar_charm, azathoth_charm,
			nyarlathotep_charm, yog_sothoth_charm, shub_niggurath_charm, hastur_engraved_coin,
			azathoth_engraved_coin, nyarlathotep_engraved_coin, yog_sothoth_engraved_coin,
			shub_niggurath_engraved_coin, hastur_engraving, azathoth_engraving,
			nyarlathotep_engraving, yog_sothoth_engraving, shub_niggurath_engraving,
			essence_of_the_gatekeeper, interdimensional_cage, crystal_fragment, stone_tablet,
			scroll, unique_scroll, antidote, darklands_oak_door, dreadlands_door, charcoal,
			configurator, configurator_shard, silver_key, book_of_many_faces, generic_meat,
			cooked_generic_meat;

	public static Item[] getItems() {
		// Get all static fields that are an Item or an Item subtype
		return Arrays.stream(Reflections.getStaticFields(ACItems.class))
				.filter(field -> Item.class.isAssignableFrom(field.getType())).map(field -> {
					try {
						return (Item) field.get(null);
					} catch (IllegalAccessException e) {
						return null;
					}
				}).toArray(Item[]::new);
	}

	public static void registerItemsVariants() {
		Item[] items = getItems();
		// Register item variants for all items that have variants
		for (Item item : items) {
			if (item instanceof ItemMetadata) {
				ItemMetadata itemMetadata = (ItemMetadata) item;
				String[] variationNames = itemMetadata.getVariationNames();
				registerItemVariants(itemMetadata, variationNames);
			}
		}
	}

	public static void registerItemsRenders() {
		Item[] items = getItems();
		for (Item item : items) {
			if (item instanceof ItemMetadata) {
				ItemMetadata itemMetadata = (ItemMetadata) item;
				String[] variationNames = itemMetadata.getVariationNames();
				for (int i = 0; i < variationNames.length; i++) {
					String variationName = variationNames[i];
					registerItemRender(item, i, variationName);
				}
			} else {
				registerItemRender(item, 0,
						Objects.requireNonNull(item.getRegistryName()).getPath());
			}
		}
	}

	public static void registerItems() {
		Item[] items = getItems();
		for (Item item : items) {
			registerItem(item, item.getTranslationKey());
		}
	}

	public static void registerItem(Item item, String name) {
		item.setRegistryName(new ResourceLocation(AbyssalCraft.modid, name));
	}

	private static void registerItemVariants(ItemMetadata item, String[] variationNames) {
		ModelBakery.registerItemVariants(item, resourceLocationsOf(variationNames));
	}

	public static void registerItemRender(Item item, int meta, String tileName) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(AbyssalCraft.modid + ":" + tileName, "inventory"));
	}

	private static ResourceLocation[] resourceLocationsOf(String... resourceNames) {
		return Arrays.stream(resourceNames)
				.map(resourceName -> new ResourceLocation(AbyssalCraft.modid, resourceName))
				.toArray(ResourceLocation[]::new);
	}


}
