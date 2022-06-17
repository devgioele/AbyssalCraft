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
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.biome.ACBiomes;
import com.shinoow.abyssalcraft.api.biome.IDarklandsBiome;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.energy.EnergyEnum;
import com.shinoow.abyssalcraft.api.entity.IDreadEntity;
import com.shinoow.abyssalcraft.api.necronomicon.condition.*;
import com.shinoow.abyssalcraft.common.items.*;
import com.shinoow.abyssalcraft.common.items.armor.*;
import com.shinoow.abyssalcraft.lib.ACLib;
import com.shinoow.abyssalcraft.lib.ACTabs;
import com.shinoow.abyssalcraft.lib.item.ItemCharm;
import com.shinoow.abyssalcraft.lib.item.ItemMetadata;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Arrays;
import java.util.Objects;

/**
 * Contains all items added in AbyssalCraft
 *
 * @author shinoow
 */
public class ACItems {

	private static ACItems INSTANCE;

	public ItemStack liquid_coralium_bucket_stack;
	public ItemStack liquid_antimatter_bucket_stack;

	// Secret developer items
	public final Item devsword = new SpecialTool("devsword");
	public final Item shoggoth_projectile = new ItemACBasic("shoggoth_projectile");
	//	public final Item shadow_titan_armor_plate;

	//Misc items
	public final Item oblivion_catalyst = new ItemOC();
	public final Item staff_of_the_gatekeeper =
			new ItemStaff().setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item gateway_key = new ItemGatewayKey(0, "gatewaykey");
	public final Item powerstone_tracker = new ItemTrackerPSDL().setUnlockCondition(
			new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item eye_of_the_abyss =
			new ItemEoA().setUnlockCondition(new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item dreaded_gateway_key =
			new ItemGatewayKey(1, "gatewaykeydl").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item coralium_brick = new ItemACBasic("cbrick").setUnlockCondition(
			new MultiBiomeCondition(ACBiomes.abyssal_wastelands,
					ACBiomes.coralium_infested_swamp));
	public final Item cudgel =
			new ItemCudgel().setUnlockCondition(new EntityCondition("abyssalcraft:gskeleton"));
	public final Item carbon_cluster = new ItemACBasic("carboncluster").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item dense_carbon_cluster =
			new ItemACBasic("densecarboncluster").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item methane = new ItemACBasic("methane");
	public final Item nitre = new ItemACBasic("nitre");
	public final Item sulfur = new ItemACBasic("sulfur");
	public final Item rlyehian_gateway_key =
			new ItemGatewayKey(2, "gatewaykeyjzh").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item tin_ingot = new ItemACBasic("tiningot");
	public final Item copper_ingot = new ItemACBasic("copperingot");
	public final Item life_crystal = new ItemACBasic("lifecrystal").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item shoggoth_flesh =
			new ItemMetadata("shoggothflesh", "overworld", "abyssalwasteland", "dreadlands",
					"omothol", "darkrealm");
	public final Item eldritch_scale = new ItemACBasic("eldritchscale").setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id));
	public final Item omothol_flesh = new ItemOmotholFlesh(3, 0.3F, false).setUnlockCondition(
			new EntityCondition("abyssalcraft:omotholghoul"));
	public final Item necronomicon = new ItemNecronomicon("necronomicon", 0);
	public final Item abyssal_wasteland_necronomicon =
			new ItemNecronomicon("necronomicon_cor", 1).setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item dreadlands_necronomicon =
			new ItemNecronomicon("necronomicon_dre", 2).setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item omothol_necronomicon =
			new ItemNecronomicon("necronomicon_omt", 3).setUnlockCondition(
					new DimensionCondition(ACLib.omothol_id));
	public final Item abyssalnomicon =
			new ItemNecronomicon("abyssalnomicon", 4).setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id));
	public final Item small_crystal_bag =
			new ItemCrystalBag("crystalbag_small").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item medium_crystal_bag =
			new ItemCrystalBag("crystalbag_medium").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item large_crystal_bag =
			new ItemCrystalBag("crystalbag_large").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item huge_crystal_bag = new ItemCrystalBag("crystalbag_huge").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item ingot_nugget =
			new ItemMetadataMisc("nugget", "abyssalnite", "coralium", "dreadium", "ethaxium");
	public final Item essence =
			new ItemMetadataMisc("essence", "abyssalwasteland", "dreadlands", "omothol");
	public final Item skin =
			new ItemMetadataMisc("skin", "abyssalwasteland", "dreadlands", "omothol");
	public final Item essence_of_the_gatekeeper = new ItemGatekeeperEssence().setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id));
	public final Item interdimensional_cage = new ItemInterdimensionalCage().setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item stone_tablet = new ItemStoneTablet();
	public final Item scroll = new ItemScroll("scroll", "basic", "lesser", "moderate", "greater");
	public final Item unique_scroll = new ItemScroll("unique_scroll", "antimatter", "oblivion");
	public final Item antidote = new ItemAntidote();
	public final ItemACDoor darklands_oak_door = (ItemACDoor) new ItemACDoor(
			ACBlocks.getInstance().darklands_oak_door.getBlock()).setTranslationKey("door_dlt");
	public final ItemACDoor dreadlands_door = (ItemACDoor) new ItemACDoor(
			ACBlocks.getInstance().dreadlands_door.getBlock()).setTranslationKey("door_drt");
	public final Item configurator_shard =
			new ItemMetadataMisc("configurator_shard", "0", "1", "2", "3").setUnlockCondition(
					new DimensionCondition(ACLib.omothol_id));
	public final Item silver_key = new ItemGatewayKey(3, "silver_key");
	public final Item book_of_many_faces = new ItemFaceBook("face_book");

	//Coins
	public final Item coin =
			new ItemCoin("blankcoin").setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item cthulhu_engraved_coin = new ItemCoin("cthulhucoin").setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id));
	public final Item elder_engraved_coin =
			new ItemCoin("eldercoin").setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item jzahar_engraved_coin =
			new ItemCoin("jzaharcoin").setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item blank_engraving = new ItemEngraving("blank", 50).setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id)).setCreativeTab(ACTabs.tabCoins);
	public final Item cthulhu_engraving = new ItemEngraving("cthulhu", 10).setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id)).setCreativeTab(ACTabs.tabCoins);
	public final Item elder_engraving = new ItemEngraving("elder", 10).setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id)).setCreativeTab(ACTabs.tabCoins);
	public final Item jzahar_engraving = new ItemEngraving("jzahar", 10).setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id)).setCreativeTab(ACTabs.tabCoins);
	public final Item hastur_engraved_coin =
			new ItemCoin("hasturcoin").setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item azathoth_engraved_coin = new ItemCoin("azathothcoin").setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id));
	public final Item nyarlathotep_engraved_coin =
			new ItemCoin("nyarlathotepcoin").setUnlockCondition(
					new DimensionCondition(ACLib.omothol_id));
	public final Item yog_sothoth_engraved_coin =
			new ItemCoin("yogsothothcoin").setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id));
	public final Item shub_niggurath_engraved_coin =
			new ItemCoin("shubniggurathcoin").setUnlockCondition(
					new DimensionCondition(ACLib.omothol_id));
	public final Item hastur_engraving = new ItemEngraving("hastur", 10).setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id)).setCreativeTab(ACTabs.tabCoins);
	public final Item azathoth_engraving = new ItemEngraving("azathoth", 10).setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id)).setCreativeTab(ACTabs.tabCoins);
	public final Item nyarlathotep_engraving =
			new ItemEngraving("nyarlathotep", 10).setUnlockCondition(
					new DimensionCondition(ACLib.omothol_id)).setCreativeTab(ACTabs.tabCoins);
	public final Item yog_sothoth_engraving =
			new ItemEngraving("yogsothoth", 10).setUnlockCondition(
					new DimensionCondition(ACLib.omothol_id)).setCreativeTab(ACTabs.tabCoins);
	public final Item shub_niggurath_engraving =
			new ItemEngraving("shubniggurath", 10).setUnlockCondition(
					new DimensionCondition(ACLib.omothol_id)).setCreativeTab(ACTabs.tabCoins);

	//Charms
	public final Item ritual_charm = new ItemCharm("ritualcharm", null);
	public final Item cthulhu_charm = new ItemCharm("cthulhucharm", EnergyEnum.DeityType.CTHULHU);
	public final Item hastur_charm = new ItemCharm("hasturcharm", EnergyEnum.DeityType.HASTUR);
	public final Item jzahar_charm = new ItemCharm("jzaharcharm", EnergyEnum.DeityType.JZAHAR);
	public final Item azathoth_charm =
			new ItemCharm("azathothcharm", EnergyEnum.DeityType.AZATHOTH);
	public final Item nyarlathotep_charm =
			new ItemCharm("nyarlathotepcharm", EnergyEnum.DeityType.NYARLATHOTEP);
	public final Item yog_sothoth_charm =
			new ItemCharm("yogsothothcharm", EnergyEnum.DeityType.YOGSOTHOTH);
	public final Item shub_niggurath_charm =
			new ItemCharm("shubniggurathcharm", EnergyEnum.DeityType.SHUBNIGGURATH);

	//Ethaxium
	public final Item ethaxium_brick = new ItemACBasic("ethbrick").setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id));
	public final Item ethaxium_ingot = new ItemACBasic("ethaxiumingot").setUnlockCondition(
			new DimensionCondition(ACLib.omothol_id));

	//anti-items
	public final Item anti_beef = new ItemAntiFood("antibeef").setUnlockCondition(
			new BiomeCondition(ACBiomes.coralium_infested_swamp));
	public final Item anti_chicken = new ItemAntiFood("antichicken").setUnlockCondition(
			new BiomeCondition(ACBiomes.coralium_infested_swamp));
	public final Item anti_pork = new ItemAntiFood("antipork").setUnlockCondition(
			new BiomeCondition(ACBiomes.coralium_infested_swamp));
	public final Item rotten_anti_flesh = new ItemAntiFood("antiflesh").setUnlockCondition(
			new BiomeCondition(ACBiomes.coralium_infested_swamp));
	public final Item anti_bone = new ItemACBasic("antibone").setUnlockCondition(
			new BiomeCondition(ACBiomes.coralium_infested_swamp));
	public final Item anti_spider_eye =
			new ItemAntiFood("antispidereye", false).setUnlockCondition(
			new BiomeCondition(ACBiomes.coralium_infested_swamp));
	public final Item anti_plagued_flesh =
			new ItemCorflesh(0, 0, false, "anticorflesh").setUnlockCondition(
					new BiomeCondition(ACBiomes.coralium_infested_swamp));
	public final Item anti_plagued_flesh_on_a_bone =
			new ItemCorbone(0, 0, false, "anticorbone").setUnlockCondition(
					new BiomeCondition(ACBiomes.coralium_infested_swamp));

	//crystals
	public final Item crystal = new ItemCrystal("crystal").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item crystal_shard = new ItemCrystal("crystalshard", true).setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item crystal_fragment =
			new ItemCrystal("crystalfragment", true).setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));

	//Shadow items
	public final Item shadow_fragment = new ItemACBasic("shadowfragment").setUnlockCondition(
			new MultiEntityCondition("abyssalcraft:shadowcreature", "abyssalcraft:shadowmonster",
					"abyssalcraft:shadowbeast"));
	public final Item shadow_shard = new ItemACBasic("shadowshard").setUnlockCondition(
			new MultiEntityCondition("abyssalcraft:shadowcreature", "abyssalcraft:shadowmonster",
					"abyssalcraft:shadowbeast"));
	public final Item shadow_gem = new ItemACBasic("shadowgem").setUnlockCondition(
			new MultiEntityCondition("abyssalcraft:shadowcreature", "abyssalcraft:shadowmonster",
					"abyssalcraft:shadowbeast"));
	public final Item shard_of_oblivion = new ItemACBasic("oblivionshard").setUnlockCondition(
			new MultiEntityCondition("abyssalcraft:shadowcreature", "abyssalcraft:shadowmonster",
					"abyssalcraft:shadowbeast"));
	public final Item shadowPlate = new ItemACBasic("shadowplate");

	//Dread items
	public final Item dreaded_shard_of_abyssalnite =
			new ItemACBasic("dreadshard").setUnlockCondition(
					new MultiEntityCondition("abyssalcraft:dreadguard",
							"abyssalcraft:greaterdreadspawn", "abyssalcraft:lesserdreadbeast"));
	public final Item dreaded_chunk_of_abyssalnite =
			new ItemACBasic("dreadchunk").setUnlockCondition(
					new EntityCondition("abyssalcraft:dreadgolem"));
	public final Item dreadium_ingot = new ItemACBasic("dreadiumingot").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item dread_fragment = new ItemACBasic("dreadfragment").setUnlockCondition(
			new EntityPredicateCondition(IDreadEntity.class::isAssignableFrom));
	public final Item dread_cloth = new ItemACBasic("dreadcloth").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_plate = new ItemACBasic("dreadplate").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_katana_blade = new ItemACBasic("dreadblade").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item dread_plagued_gateway_key = new ItemACBasic("dreadkey").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item charcoal = new ItemACBasic("cha_rcoal").setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));

	//Abyssalnite items
	public final Item chunk_of_abyssalnite = new ItemACBasic("abychunk").setUnlockCondition(
			new EntityCondition("abyssalcraft:abygolem"));
	public final Item abyssalnite_ingot = new ItemACBasic("abyingot").setUnlockCondition(
			new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));

	//Coralium items
	public final Item coralium_gem_cluster_2 = new ItemCoraliumcluster("ccluster2", "2");
	public final Item coralium_gem_cluster_3 = new ItemCoraliumcluster("ccluster3", "3");
	public final Item coralium_gem_cluster_4 = new ItemCoraliumcluster("ccluster4", "4");
	public final Item coralium_gem_cluster_5 = new ItemCoraliumcluster("ccluster5", "5");
	public final Item coralium_gem_cluster_6 = new ItemCoraliumcluster("ccluster6", "6");
	public final Item coralium_gem_cluster_7 = new ItemCoraliumcluster("ccluster7", "7");
	public final Item coralium_gem_cluster_8 = new ItemCoraliumcluster("ccluster8", "8");
	public final Item coralium_gem_cluster_9 = new ItemCoraliumcluster("ccluster9", "9");
	public final Item coralium_pearl = new ItemACBasic("cpearl");
	public final Item chunk_of_coralium = new ItemACBasic("cchunk").setUnlockCondition(
			new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item refined_coralium_ingot = new ItemACBasic("cingot").setUnlockCondition(
			new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item coralium_plate = new ItemACBasic("platec").setUnlockCondition(
			new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item coralium_gem = new ItemACBasic("coralium");
	public final Item transmutation_gem = new ItemCorb();
	public final Item coralium_plagued_flesh =
			new ItemCorflesh(2, 0.1F, false, "corflesh").setUnlockCondition(
					new EntityCondition("abyssalcraft:abyssalzombie"));
	public final Item coralium_plagued_flesh_on_a_bone =
			new ItemCorbone(2, 0.1F, false, "corbone").setUnlockCondition(
					new EntityCondition("abyssalcraft:depthsghoul"));
	public final Item coralium_longbow = new ItemCoraliumBow(20.0F, 0, 8, 16).setUnlockCondition(
			new DimensionCondition(ACLib.abyssal_wasteland_id));

	//Tools
	public final Item darkstone_pickaxe =
			new ItemACPickaxe(AbyssalCraftAPI.darkstoneTool, "dpick", 1);
	public final Item darkstone_axe = new ItemACAxe(AbyssalCraftAPI.darkstoneTool, "daxe", 1);
	public final Item darkstone_shovel =
			new ItemACShovel(AbyssalCraftAPI.darkstoneTool, "dshovel", 1);
	public final Item darkstone_sword = new ItemACSword(AbyssalCraftAPI.darkstoneTool, "dsword");
	public final Item darkstone_hoe = new ItemACHoe(AbyssalCraftAPI.darkstoneTool, "dhoe");
	public final Item abyssalnite_pickaxe =
			new ItemACPickaxe(AbyssalCraftAPI.abyssalniteTool, "apick", 4,
					TextFormatting.DARK_AQUA).setUnlockCondition(
					new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
	public final Item abyssalnite_axe = new ItemACAxe(AbyssalCraftAPI.abyssalniteTool, "aaxe", 4,
			TextFormatting.DARK_AQUA).setUnlockCondition(
			new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
	public final Item abyssalnite_shovel =
			new ItemACShovel(AbyssalCraftAPI.abyssalniteTool, "ashovel", 4,
					TextFormatting.DARK_AQUA).setUnlockCondition(
					new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
	public final Item abyssalnite_sword = new ItemACSword(AbyssalCraftAPI.abyssalniteTool,
			"asword",
			TextFormatting.DARK_AQUA).setUnlockCondition(
			new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
	public final Item abyssalnite_hoe = new ItemACHoe(AbyssalCraftAPI.abyssalniteTool, "ahoe",
			TextFormatting.DARK_AQUA).setUnlockCondition(
			new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
	public final Item refined_coralium_pickaxe =
			new ItemACPickaxe(AbyssalCraftAPI.refinedCoraliumTool, "corpick", 5,
					TextFormatting.AQUA).setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item refined_coralium_axe =
			new ItemACAxe(AbyssalCraftAPI.refinedCoraliumTool, "coraxe", 5,
					TextFormatting.AQUA).setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item refined_coralium_shovel =
			new ItemACShovel(AbyssalCraftAPI.refinedCoraliumTool, "corshovel", 5,
					TextFormatting.AQUA).setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item refined_coralium_sword =
			new ItemACSword(AbyssalCraftAPI.refinedCoraliumTool, "corsword",
					TextFormatting.AQUA).setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item refined_coralium_hoe =
			new ItemACHoe(AbyssalCraftAPI.refinedCoraliumTool, "corhoe",
					TextFormatting.AQUA).setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item dreadium_pickaxe =
			new ItemACPickaxe(AbyssalCraftAPI.dreadiumTool, "dreadiumpickaxe", 6,
					TextFormatting.DARK_RED).setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_axe = new ItemACAxe(AbyssalCraftAPI.dreadiumTool, "dreadiumaxe", 6,
			TextFormatting.DARK_RED).setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_shovel =
			new ItemACShovel(AbyssalCraftAPI.dreadiumTool, "dreadiumshovel", 6,
					TextFormatting.DARK_RED).setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_sword =
			new ItemACSword(AbyssalCraftAPI.dreadiumTool, "dreadiumsword",
					TextFormatting.DARK_RED).setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_hoe = new ItemACHoe(AbyssalCraftAPI.dreadiumTool, "dreadiumhoe",
			TextFormatting.DARK_RED).setUnlockCondition(
			new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_katana_hilt =
			new ItemDreadiumKatana("dreadhilt", ItemDreadiumKatana.hilt).setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_katana =
			new ItemDreadiumKatana("dreadkatana", ItemDreadiumKatana.katana).setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item sacthoths_soul_harvesting_blade =
			new ItemSoulReaper("soulreaper").setUnlockCondition(
					new EntityCondition("abyssalcraft:shadowboss"));
	public final Item ethaxium_pickaxe = new ItemEthaxiumPickaxe(AbyssalCraftAPI.ethaxiumTool,
			"ethaxiumpickaxe").setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item ethaxium_axe = new ItemACAxe(AbyssalCraftAPI.ethaxiumTool, "ethaxiumaxe", 8,
			TextFormatting.AQUA).setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item ethaxium_shovel =
			new ItemACShovel(AbyssalCraftAPI.ethaxiumTool, "ethaxiumshovel", 8,
					TextFormatting.AQUA).setUnlockCondition(
					new DimensionCondition(ACLib.omothol_id));
	public final Item ethaxium_sword =
			new ItemACSword(AbyssalCraftAPI.ethaxiumTool, "ethaxiumsword",
					TextFormatting.AQUA).setUnlockCondition(
					new DimensionCondition(ACLib.omothol_id));
	public final Item ethaxium_hoe = new ItemACHoe(AbyssalCraftAPI.ethaxiumTool, "ethaxiumhoe",
			TextFormatting.AQUA).setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item staff_of_rending = new ItemStaffOfRending().setUnlockCondition(
			new MultiEntityCondition("abyssalcraft:shadowcreature", "abyssalcraft:shadowmonster",
					"abyssalcraft:shadowbeast"));
	public final Item configurator =
			new ItemConfigurator().setUnlockCondition(new DimensionCondition(ACLib.omothol_id));

	//Armor
	public final Item abyssalnite_helmet =
			new ItemAbyssalniteArmor(AbyssalCraftAPI.abyssalniteArmor, 5, EntityEquipmentSlot.HEAD,
					"ahelmet").setUnlockCondition(
					new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
	public final Item abyssalnite_chestplate =
			new ItemAbyssalniteArmor(AbyssalCraftAPI.abyssalniteArmor, 5,
					EntityEquipmentSlot.CHEST,
					"aplate").setUnlockCondition(
					new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
	public final Item abyssalnite_leggings =
			new ItemAbyssalniteArmor(AbyssalCraftAPI.abyssalniteArmor, 5, EntityEquipmentSlot.LEGS,
					"alegs").setUnlockCondition(
					new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
	public final Item abyssalnite_boots =
			new ItemAbyssalniteArmor(AbyssalCraftAPI.abyssalniteArmor, 5, EntityEquipmentSlot.FEET,
					"aboots").setUnlockCondition(
					new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
	public final Item dreaded_abyssalnite_helmet =
			new ItemDreadArmor(AbyssalCraftAPI.dreadedAbyssalniteArmor, 5,
					EntityEquipmentSlot.HEAD,
					"dhelmet").setUnlockCondition(new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreaded_abyssalnite_chestplate =
			new ItemDreadArmor(AbyssalCraftAPI.dreadedAbyssalniteArmor, 5,
					EntityEquipmentSlot.CHEST, "dplate").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreaded_abyssalnite_leggings =
			new ItemDreadArmor(AbyssalCraftAPI.dreadedAbyssalniteArmor, 5,
					EntityEquipmentSlot.LEGS,
					"dlegs").setUnlockCondition(new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreaded_abyssalnite_boots =
			new ItemDreadArmor(AbyssalCraftAPI.dreadedAbyssalniteArmor, 5,
					EntityEquipmentSlot.FEET,
					"dboots").setUnlockCondition(new DimensionCondition(ACLib.dreadlands_id));
	public final Item refined_coralium_helmet =
			new ItemCoraliumArmor(AbyssalCraftAPI.refinedCoraliumArmor, 5,
					EntityEquipmentSlot.HEAD,
					"corhelmet").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item refined_coralium_chestplate =
			new ItemCoraliumArmor(AbyssalCraftAPI.refinedCoraliumArmor, 5,
					EntityEquipmentSlot.CHEST, "corplate").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item refined_coralium_leggings =
			new ItemCoraliumArmor(AbyssalCraftAPI.refinedCoraliumArmor, 5,
					EntityEquipmentSlot.LEGS,
					"corlegs").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item refined_coralium_boots =
			new ItemCoraliumArmor(AbyssalCraftAPI.refinedCoraliumArmor, 5,
					EntityEquipmentSlot.FEET,
					"corboots").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item plated_coralium_helmet =
			new ItemCoraliumPArmor(AbyssalCraftAPI.platedCoraliumArmor, 5,
					EntityEquipmentSlot.HEAD,
					"corhelmetp").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item plated_coralium_chestplate =
			new ItemCoraliumPArmor(AbyssalCraftAPI.platedCoraliumArmor, 5,
					EntityEquipmentSlot.CHEST, "corplatep").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item plated_coralium_leggings =
			new ItemCoraliumPArmor(AbyssalCraftAPI.platedCoraliumArmor, 5,
					EntityEquipmentSlot.LEGS,
					"corlegsp").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item plated_coralium_boots =
			new ItemCoraliumPArmor(AbyssalCraftAPI.platedCoraliumArmor, 5,
					EntityEquipmentSlot.FEET,
					"corbootsp").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item depths_helmet =
			new ItemDepthsArmor(AbyssalCraftAPI.depthsArmor, 5, EntityEquipmentSlot.HEAD,
					"depthshelmet").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item depths_chestplate =
			new ItemDepthsArmor(AbyssalCraftAPI.depthsArmor, 5, EntityEquipmentSlot.CHEST,
					"depthsplate").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item depths_leggings =
			new ItemDepthsArmor(AbyssalCraftAPI.depthsArmor, 5, EntityEquipmentSlot.LEGS,
					"depthslegs").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item depths_boots =
			new ItemDepthsArmor(AbyssalCraftAPI.depthsArmor, 5, EntityEquipmentSlot.FEET,
					"depthsboots").setUnlockCondition(
					new DimensionCondition(ACLib.abyssal_wasteland_id));
	public final Item dreadium_helmet =
			new ItemDreadiumArmor(AbyssalCraftAPI.dreadiumArmor, 5, EntityEquipmentSlot.HEAD,
					"dreadiumhelmet").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_chestplate =
			new ItemDreadiumArmor(AbyssalCraftAPI.dreadiumArmor, 5, EntityEquipmentSlot.CHEST,
					"dreadiumplate").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_leggings =
			new ItemDreadiumArmor(AbyssalCraftAPI.dreadiumArmor, 5, EntityEquipmentSlot.LEGS,
					"dreadiumlegs").setUnlockCondition(new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_boots =
			new ItemDreadiumArmor(AbyssalCraftAPI.dreadiumArmor, 5, EntityEquipmentSlot.FEET,
					"dreadiumboots").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_samurai_helmet =
			new ItemDreadiumSamuraiArmor(AbyssalCraftAPI.dreadiumSamuraiArmor, 5,
					EntityEquipmentSlot.HEAD, "dreadiumsamuraihelmet").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_samurai_chestplate =
			new ItemDreadiumSamuraiArmor(AbyssalCraftAPI.dreadiumSamuraiArmor, 5,
					EntityEquipmentSlot.CHEST, "dreadiumsamuraiplate").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_samurai_leggings =
			new ItemDreadiumSamuraiArmor(AbyssalCraftAPI.dreadiumSamuraiArmor, 5,
					EntityEquipmentSlot.LEGS, "dreadiumsamurailegs").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item dreadium_samurai_boots =
			new ItemDreadiumSamuraiArmor(AbyssalCraftAPI.dreadiumSamuraiArmor, 5,
					EntityEquipmentSlot.FEET, "dreadiumsamuraiboots").setUnlockCondition(
					new DimensionCondition(ACLib.dreadlands_id));
	public final Item ethaxium_helmet =
			new ItemEthaxiumArmor(AbyssalCraftAPI.ethaxiumArmor, 5, EntityEquipmentSlot.HEAD,
					"ethaxiumhelmet").setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item ethaxium_chestplate =
			new ItemEthaxiumArmor(AbyssalCraftAPI.ethaxiumArmor, 5, EntityEquipmentSlot.CHEST,
					"ethaxiumplate").setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item ethaxium_leggings =
			new ItemEthaxiumArmor(AbyssalCraftAPI.ethaxiumArmor, 5, EntityEquipmentSlot.LEGS,
					"ethaxiumlegs").setUnlockCondition(new DimensionCondition(ACLib.omothol_id));
	public final Item ethaxium_boots =
			new ItemEthaxiumArmor(AbyssalCraftAPI.ethaxiumArmor, 5, EntityEquipmentSlot.FEET,
					"ethaxiumboots").setUnlockCondition(new DimensionCondition(ACLib.omothol_id));

	//Food
	public final Item generic_meat = new ItemFood(4, 0.4f, true).setTranslationKey("generic_meat")
			.setCreativeTab(ACTabs.tabFood);
	public final Item cooked_generic_meat =
			new ItemFood(9, 0.9f, true).setTranslationKey("cooked_generic_meat")
					.setCreativeTab(ACTabs.tabFood);

	public static ACItems getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ACItems();
		}
		return INSTANCE;
	}

	private ACItems() {
		darklands_oak_door.selfReferenceToBlock();
		dreadlands_door.selfReferenceToBlock();
	}

	public Item[] getItems() {
		// Get all fields that are an Item or an Item subtype
		return Arrays.stream(ACItems.class.getDeclaredFields())
				.filter(field -> Item.class.isAssignableFrom(field.getType())).map(field -> {
					try {
						return (Item) field.get(null);
					} catch (IllegalAccessException e) {
						return null;
					}
				}).toArray(Item[]::new);
	}

	public ACItems registerItemsVariants() {
		Item[] items = getItems();
		// Register item variants for all items that have variants
		for (Item item : items) {
			if (item instanceof ItemMetadata) {
				ItemMetadata itemMetadata = (ItemMetadata) item;
				String[] variationNames = itemMetadata.getVariationNames();
				registerItemVariants(itemMetadata, variationNames);
			}
		}
		return this;
	}

	public ACItems registerItemsRenders() {
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
		return this;
	}

	public ACItems registerItems() {
		Item[] items = getItems();
		for (Item item : items) {
			registerItem(item, item.getTranslationKey());
		}
		return this;
	}

	private static void registerItemVariants(ItemMetadata item, String[] variationNames) {
		ModelBakery.registerItemVariants(item, resourceLocationsOf(variationNames));
	}

	public static void registerItemRender(Item item, int meta, String tileName) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(AbyssalCraft.modid + ":" + tileName, "inventory"));
	}

	public static void registerItem(Item item, String name) {
		item.setRegistryName(new ResourceLocation(AbyssalCraft.modid, name));
	}

	private static ResourceLocation[] resourceLocationsOf(String... resourceNames) {
		return Arrays.stream(resourceNames)
				.map(resourceName -> new ResourceLocation(AbyssalCraft.modid, resourceName))
				.toArray(ResourceLocation[]::new);
	}


}
