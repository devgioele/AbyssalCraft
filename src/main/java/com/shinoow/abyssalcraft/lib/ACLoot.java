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
package com.shinoow.abyssalcraft.lib;

import com.shinoow.abyssalcraft.AbyssalCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

/**
 * Loot Table references
 * @author shinoow
 *
 */
public class ACLoot {

	public static void init(){ /* Nothing special here, just initializing all the static things */}

	public static final ResourceLocation CHEST_ABYSSAL_STRONGHOLD_CORRIDOR = LootTableList.register(new ResourceLocation(
			AbyssalCraft.modid, "chests/stronghold_corridor"));
	public static final ResourceLocation CHEST_ABYSSAL_STRONGHOLD_CROSSING = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "chests/stronghold_crossing"));
	public static final ResourceLocation CHEST_DREADLANDS_MINESHAFT = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "chests/mineshaft"));
	public static final ResourceLocation CHEST_OMOTHOL_BLACKSMITH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "chests/omothol/blacksmith"));
	public static final ResourceLocation CHEST_OMOTHOL_HOUSE = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "chests/omothol/house"));
	public static final ResourceLocation CHEST_OMOTHOL_LIBRARY = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "chests/omothol/library"));
	public static final ResourceLocation CHEST_OMOTHOL_FARMHOUSE = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "chests/omothol/farmhouse"));
	public static final ResourceLocation CHEST_OMOTHOL_STORAGE_JUNK = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "chests/omothol/storage_junk"));
	public static final ResourceLocation CHEST_OMOTHOL_STORAGE_TREASURE = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "chests/omothol/storage_treasure"));

	public static final ResourceLocation ENTITY_DEPTHS_GHOUL = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/depths_ghoul"));
	public static final ResourceLocation ENTITY_DEPTHS_GHOUL_PETE = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/depths_ghoul_pete"));
	public static final ResourceLocation ENTITY_DEPTHS_GHOUL_WILSON = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/depths_ghoul_wilson"));
	public static final ResourceLocation ENTITY_DEPTHS_GHOUL_ORANGE = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/depths_ghoul_orange"));
	public static final ResourceLocation ENTITY_SKELETON_GOLIATH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/skeleton_goliath"));
	public static final ResourceLocation ENTITY_ABYSSAL_ANTI_ZOMBIE = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/abyssal_anti_zombie"));
	public static final ResourceLocation ENTITY_ABYSSAL_ZOMBIE = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/abyssal_zombie"));
	public static final ResourceLocation ENTITY_ABYSSALNITE_GOLEM = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/abyssalnite_golem"));
	public static final ResourceLocation ENTITY_ANTI_BAT = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/anti_bat"));
	public static final ResourceLocation ENTITY_ANTI_CHICKEN = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/anti_chicken"));
	public static final ResourceLocation ENTITY_ANTI_COW = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/anti_cow"));
	public static final ResourceLocation ENTITY_ANTI_CREEPER = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/anti_creeper"));
	public static final ResourceLocation ENTITY_ANTI_GHOUL = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/anti_ghoul"));
	public static final ResourceLocation ENTITY_ANTI_PIG = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/anti_pig"));
	public static final ResourceLocation ENTITY_ANTI_PLAYER = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/anti_player"));
	public static final ResourceLocation ENTITY_ANTI_SKELETON = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/anti_skeleton"));
	public static final ResourceLocation ENTITY_ANTI_SPIDER = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/anti_spider"));
	public static final ResourceLocation ENTITY_ANTI_ZOMBIE = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/anti_zombie"));
	public static final ResourceLocation ENTITY_DEMON_CHICKEN = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/demon_chicken"));
	public static final ResourceLocation ENTITY_DEMON_COW = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/demon_cow"));
	public static final ResourceLocation ENTITY_DEMON_PIG = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/demon_pig"));
	public static final ResourceLocation ENTITY_DREAD_SPAWN = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/dread_spawn"));
	public static final ResourceLocation ENTITY_DREADED_ABYSSALNITE_GOLEM = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/dreaded_abyssalnite_golem"));
	public static final ResourceLocation ENTITY_DREADGUARD = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/dreadguard"));
	public static final ResourceLocation ENTITY_DREADLING = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/dreadling"));
	public static final ResourceLocation ENTITY_EVIL_CHICKEN = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/evil_chicken"));
	public static final ResourceLocation ENTITY_EVIL_COW = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/evil_cow"));
	public static final ResourceLocation ENTITY_EVIL_PIG = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/evil_pig"));
	public static final ResourceLocation ENTITY_FIST_OF_CHAGAROTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/fist_of_chagaroth"));
	public static final ResourceLocation ENTITY_GREATER_DREAD_SPAWN = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/greater_dread_spawn"));
	public static final ResourceLocation ENTITY_LESSER_ABYSSAL_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/lesser_abyssal_shoggoth"));
	public static final ResourceLocation ENTITY_LESSER_DREADBEAST = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/lesser_dreadbeast"));
	public static final ResourceLocation ENTITY_LESSER_DREADED_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/lesser_dreaded_shoggoth"));
	public static final ResourceLocation ENTITY_LESSER_OMOTHOL_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/lesser_omothol_shoggoth"));
	public static final ResourceLocation ENTITY_LESSER_SHADOW_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/lesser_shadow_shoggoth"));
	public static final ResourceLocation ENTITY_LESSER_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/lesser_shoggoth"));
	public static final ResourceLocation ENTITY_MINION_OF_THE_GATEKEEPER = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/minion_of_the_gatekeeper"));
	public static final ResourceLocation ENTITY_OMOTHOL_GHOUL = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/omothol_ghoul"));
	public static final ResourceLocation ENTITY_REMNANT = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/remnant"));
	public static final ResourceLocation ENTITY_SHADOW_BEAST = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/shadow_beast"));
	public static final ResourceLocation ENTITY_SHADOW_CREATURE = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/shadow_creature"));
	public static final ResourceLocation ENTITY_SHADOW_MONSTER = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/shadow_monster"));
	public static final ResourceLocation ENTITY_SPAWN_OF_CHAGAROTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/spawn_of_chagaroth"));
	public static final ResourceLocation ENTITY_SPECTRAL_DRAGON = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/spectral_dragon"));
	public static final ResourceLocation ENTITY_EVIL_SHEEP = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/evil_sheep"));
	public static final ResourceLocation ENTITY_DEMON_SHEEP = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/demon_sheep"));
	public static final ResourceLocation ENTITY_CORALIUM_INFESTED_SQUID = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/coralium_infested_squid"));
	public static final ResourceLocation ENTITY_ASORAH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/asorah"));
	public static final ResourceLocation ENTITY_CHAGAROTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/chagaroth"));
	public static final ResourceLocation ENTITY_JZAHAR = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/jzahar"));
	public static final ResourceLocation ENTITY_SACTHOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/sacthoth"));
	public static final ResourceLocation ENTITY_REMNANT_BANKER = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/remnant_banker"));
	public static final ResourceLocation ENTITY_REMNANT_BLACKSMITH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/remnant_blacksmith"));
	public static final ResourceLocation ENTITY_REMNANT_BUTCHER = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/remnant_butcher"));
	public static final ResourceLocation ENTITY_REMNANT_LIBRARIAN = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/remnant_librarian"));
	public static final ResourceLocation ENTITY_REMNANT_MASTER_BLACKSMITH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/remnant_master_blacksmith"));
	public static final ResourceLocation ENTITY_REMNANT_PRIEST = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/remnant_priest"));
	public static final ResourceLocation ENTITY_SHUB_OFFSPRING = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/shub_offspring"));
	public static final ResourceLocation ENTITY_ABYSSAL_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/abyssal_shoggoth"));
	public static final ResourceLocation ENTITY_DREADED_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/dreaded_shoggoth"));
	public static final ResourceLocation ENTITY_OMOTHOL_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/omothol_shoggoth"));
	public static final ResourceLocation ENTITY_SHADOW_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/shadow_shoggoth"));
	public static final ResourceLocation ENTITY_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/shoggoth"));
	public static final ResourceLocation ENTITY_GREATER_ABYSSAL_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/greater_abyssal_shoggoth"));
	public static final ResourceLocation ENTITY_GREATER_DREADED_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/greater_dreaded_shoggoth"));
	public static final ResourceLocation ENTITY_GREATER_OMOTHOL_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/greater_omothol_shoggoth"));
	public static final ResourceLocation ENTITY_GREATER_SHADOW_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/greater_shadow_shoggoth"));
	public static final ResourceLocation ENTITY_GREATER_SHOGGOTH = LootTableList.register(new ResourceLocation(AbyssalCraft.modid, "entities/greater_shoggoth"));

}
