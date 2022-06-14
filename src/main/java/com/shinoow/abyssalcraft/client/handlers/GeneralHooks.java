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
package com.shinoow.abyssalcraft.client.handlers;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.energy.IEnergyContainerItem;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.api.item.ICrystal;
import com.shinoow.abyssalcraft.api.item.IUnlockableItem;
import com.shinoow.abyssalcraft.api.necronomicon.condition.caps.NecroDataCapability;
import com.shinoow.abyssalcraft.api.spell.IScroll;
import com.shinoow.abyssalcraft.api.spell.Spell;
import com.shinoow.abyssalcraft.api.spell.SpellUtils;
import com.shinoow.abyssalcraft.common.blocks.BlockACSlab;
import com.shinoow.abyssalcraft.init.BlockHandler;
import com.shinoow.abyssalcraft.init.ItemHandler;
import com.shinoow.abyssalcraft.lib.ACLib;
import com.shinoow.abyssalcraft.lib.NecronomiconText;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

import java.util.Arrays;

import static com.shinoow.abyssalcraft.lib.util.ParticleUtil.makeVoidFog;

public class GeneralHooks {

	public static float partialTicks = 0;

	@SubscribeEvent
	public void onUpdateFOV(FOVUpdateEvent event) {
		float fov = event.getFov();

		if( event.getEntity().isHandActive() && event.getEntity().getActiveItemStack() != null
				&& event.getEntity().getActiveItemStack().getItem() == ACItems.coralium_longbow) {
			int duration = event.getEntity().getItemInUseCount();
			float multiplier = duration / 20.0F;

			if( multiplier > 1.0F )
				multiplier = 1.0F;
			else
				multiplier *= multiplier;

			fov *= 1.0F - multiplier * 0.15F;
		}

		event.setNewfov(fov);
	}

	@SubscribeEvent
	public void tooltipStuff(ItemTooltipEvent event){
		ItemStack stack = event.getItemStack();

		if(stack.getItem() instanceof IEnergyContainerItem)
			event.getToolTip().add(1, String.format("%d/%d PE", (int)((IEnergyContainerItem)stack.getItem()).getContainedEnergy(stack), ((IEnergyContainerItem)stack.getItem()).getMaxEnergy(stack)));

		if(!APIUtils.display_names)
			if(stack.getItem() instanceof IUnlockableItem && event.getEntityPlayer() != null && !NecroDataCapability.getCap(event.getEntityPlayer()).isUnlocked(((IUnlockableItem)stack.getItem()).getUnlockCondition(stack), event.getEntityPlayer())){
				event.getToolTip().remove(0);
				event.getToolTip().add(0, "Lorem ipsum");
			}
		if(stack.getItem() instanceof IScroll) {
			Spell spell = SpellUtils.getSpell(stack);
			if(spell != null){
				event.getToolTip().add(1, I18n.format(NecronomiconText.LABEL_SPELL_NAME)+": "+TextFormatting.AQUA+spell.getLocalizedName());
				event.getToolTip().add(2, I18n.format(NecronomiconText.LABEL_SPELL_PE)+": "+(int)spell.getReqEnergy());
				event.getToolTip().add(3, I18n.format(NecronomiconText.LABEL_SPELL_TYPE)+": "+TextFormatting.GOLD+I18n.format(NecronomiconText.getSpellType(spell.requiresCharging())));
			}
		}
		if(stack.getItem() instanceof ICrystal)
			event.getToolTip().add(String.format("%s: %s", I18n.format("tooltip.crystal"), ((ICrystal) stack.getItem()).getFormula(stack)));
	}

	@SubscribeEvent
	public void tooltipFont(RenderTooltipEvent.Pre event) {
		if(!APIUtils.display_names && event.getLines().get(0).startsWith("\u00A7fLorem ipsum"))
			event.setFontRenderer(AbyssalCraftAPI.getAkloFont());
	}

	@SubscribeEvent
	public void renderTick(RenderTickEvent event) {
		if(event.phase == Phase.START)
			partialTicks = event.renderTickTime;
	}

	@SubscribeEvent
	public void clientTickEnd(ClientTickEvent event) {
		if(event.phase == Phase.END) {
			GuiScreen gui = Minecraft.getMinecraft().currentScreen;
			if(gui == null || !gui.doesGuiPauseGame())
				partialTicks = 0;
		}
	}

	@SubscribeEvent
	public void voidFog(LivingUpdateEvent event) {
		if(event.getEntityLiving() == Minecraft.getMinecraft().player)
			makeVoidFog(event.getEntityLiving().world, event.getEntityLiving());
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event){
		if(event.getEntity() == Minecraft.getMinecraft().player)
			AbyssalCraft.proxy.resetParticleCount();
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event){
		ACItems.registerItemsVariants();
		// TODO: Make this edge case conform to how items are usually registered
		ModelLoader.setCustomMeshDefinition(ACItems.staff_of_the_gatekeeper, stack -> stack.hasTagCompound() && stack.getTagCompound().getInteger("Mode") == 1 ? new ModelResourceLocation("abyssalcraft:staff2", "inventory") : new ModelResourceLocation("abyssalcraft:staff", "inventory"));

		ACItems.registerItemsRenders();

		registerFluidModel(ACBlocks.liquid_coralium, "cor");
		registerFluidModel(ACBlocks.liquid_antimatter, "anti");

		ModelLoader.setCustomStateMapper(ACBlocks.darklands_oak_leaves, new StateMap.Builder().ignore(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE).build());
		ModelLoader.setCustomStateMapper(ACBlocks.dreadlands_leaves, new StateMap.Builder().ignore(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE).build());
		ModelLoader.setCustomStateMapper(ACBlocks.oblivion_deathbomb, new StateMap.Builder().ignore(BlockTNT.EXPLODE).build());
		ModelLoader.setCustomStateMapper(ACBlocks.odb_core, new StateMap.Builder().ignore(BlockTNT.EXPLODE).build());
		ModelLoader.setCustomStateMapper(ACBlocks.darkstone_brick_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.darkstone_cobblestone_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.abyssal_stone_brick_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.darkstone_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.darklands_oak_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.dreadstone_brick_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.abyssalnite_stone_brick_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.coralium_stone_brick_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.ethaxium_brick_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.dark_ethaxium_brick_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.darklands_oak_sapling, new StateMap.Builder().ignore(BlockSapling.TYPE).build());
		ModelLoader.setCustomStateMapper(ACBlocks.dreadlands_sapling, new StateMap.Builder().ignore(BlockSapling.TYPE).build());
		ModelLoader.setCustomStateMapper(ACBlocks.mimic_fire, new StateMap.Builder().ignore(BlockFire.AGE).build());
		ModelLoader.setCustomStateMapper(ACBlocks.darkstone_cobblestone_wall, new StateMap.Builder().ignore(BlockWall.VARIANT).build());
		//		ModelLoader.setCustomStateMapper(ACBlocks.crystal_cluster, new StateMap.Builder().ignore(BlockCrystalCluster.TYPE).build());
		//		ModelLoader.setCustomStateMapper(ACBlocks.crystal_cluster2, new StateMap.Builder().ignore(BlockCrystalCluster2.TYPE).build());
		ModelLoader.setCustomStateMapper(ACBlocks.abyssal_cobblestone_wall, new StateMap.Builder().ignore(BlockWall.VARIANT).build());
		ModelLoader.setCustomStateMapper(ACBlocks.dreadstone_cobblestone_wall, new StateMap.Builder().ignore(BlockWall.VARIANT).build());
		ModelLoader.setCustomStateMapper(ACBlocks.abyssalnite_cobblestone_wall, new StateMap.Builder().ignore(BlockWall.VARIANT).build());
		ModelLoader.setCustomStateMapper(ACBlocks.coralium_cobblestone_wall, new StateMap.Builder().ignore(BlockWall.VARIANT).build());
		ModelLoader.setCustomStateMapper(ACBlocks.abyssal_cobblestone_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.dreadstone_cobblestone_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.abyssalnite_cobblestone_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());
		ModelLoader.setCustomStateMapper(ACBlocks.coralium_cobblestone_slab, new StateMap.Builder().ignore(BlockACSlab.VARIANT_PROPERTY).build());

		ModelLoader.setCustomStateMapper(ACBlocks.darklands_oak_door, new StateMap.Builder().ignore(BlockDoor.POWERED).build());
		ModelLoader.setCustomStateMapper(ACBlocks.dreadlands_door, new StateMap.Builder().ignore(BlockDoor.POWERED).build());

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.cthulhu_statue), 0, new ModelResourceLocation("abyssalcraft:cthulhustatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.decorative_cthulhu_statue), 0, new ModelResourceLocation("abyssalcraft:cthulhustatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.hastur_statue), 0, new ModelResourceLocation("abyssalcraft:hasturstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.decorative_hastur_statue), 0, new ModelResourceLocation("abyssalcraft:hasturstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.jzahar_statue), 0, new ModelResourceLocation("abyssalcraft:jzaharstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.decorative_jzahar_statue), 0, new ModelResourceLocation("abyssalcraft:jzaharstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.azathoth_statue), 0, new ModelResourceLocation("abyssalcraft:azathothstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.decorative_azathoth_statue), 0, new ModelResourceLocation("abyssalcraft:azathothstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.nyarlathotep_statue), 0, new ModelResourceLocation("abyssalcraft:nyarlathotepstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.decorative_nyarlathotep_statue), 0, new ModelResourceLocation("abyssalcraft:nyarlathotepstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.yog_sothoth_statue), 0, new ModelResourceLocation("abyssalcraft:yogsothothstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.decorative_yog_sothoth_statue), 0, new ModelResourceLocation("abyssalcraft:yogsothothstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.shub_niggurath_statue), 0, new ModelResourceLocation("abyssalcraft:shubniggurathstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.decorative_shub_niggurath_statue), 0, new ModelResourceLocation("abyssalcraft:shubniggurathstatue", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.engraver), 0, new ModelResourceLocation("abyssalcraft:engraver", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.oblivion_deathbomb), 0, new ModelResourceLocation("abyssalcraft:odb", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.chagaroth_altar_top), 0, new ModelResourceLocation("abyssalcraft:dreadaltartop", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ACBlocks.chagaroth_altar_bottom), 0, new ModelResourceLocation("abyssalcraft:dreadaltarbottom", "inventory"));

		registerItemRender(ACBlocks.darkstone, 0, "darkstone");
		registerItemRender(ACBlocks.abyssal_stone, 0, "abystone");
		registerItemRender(ACBlocks.dreadstone, 0, "dreadstone");
		registerItemRender(ACBlocks.abyssalnite_stone, 0, "abydreadstone");
		registerItemRender(ACBlocks.coralium_stone, 0, "cstone");
		registerItemRender(ACBlocks.ethaxium, 0, "ethaxium");
		registerItemRender(ACBlocks.omothol_stone, 0, "omotholstone");
		registerItemRender(ACBlocks.monolith_stone, 0, "monolithstone");
		registerItemRender(ACBlocks.darkstone_cobblestone, 0, "darkstone_cobble");
		registerItemRender(ACBlocks.abyssal_cobblestone, 0, "abyssalcobblestone");
		registerItemRender(ACBlocks.dreadstone_cobblestone, 0, "dreadstonecobblestone");
		registerItemRender(ACBlocks.abyssalnite_cobblestone, 0, "abyssalnitecobblestone");
		registerItemRender(ACBlocks.coralium_cobblestone, 0, "coraliumcobblestone");
		registerItemRender(ACBlocks.darkstone_brick, 0, "darkstone_brick_0");
		registerItemRender(ACBlocks.chiseled_darkstone_brick, 0, "darkstone_brick_1");
		registerItemRender(ACBlocks.cracked_darkstone_brick, 0, "darkstone_brick_2");
		registerItemRender(ACBlocks.glowing_darkstone_bricks, 0);
		registerItemRender(ACBlocks.darkstone_brick_slab, 0);
		registerItemRender(BlockHandler.Darkbrickslab2, 0);
		registerItemRender(ACBlocks.darkstone_cobblestone_slab, 0);
		registerItemRender(BlockHandler.Darkcobbleslab2, 0);
		registerItemRender(ACBlocks.darkstone_brick_stairs, 0);
		registerItemRender(ACBlocks.darkstone_cobblestone_stairs, 0);
		registerItemRender(ACBlocks.darklands_oak_leaves, 0);
		registerItemRender(ACBlocks.darklands_oak_wood, 0);
		registerItemRender(ACBlocks.darklands_oak_wood_2, 0);
		registerItemRender(ACBlocks.darklands_oak_sapling, 0);
		registerItemRender(ACBlocks.abyssal_stone_brick, 0, "abybrick_0");
		registerItemRender(ACBlocks.chiseled_abyssal_stone_brick, 0, "abybrick_1");
		registerItemRender(ACBlocks.cracked_abyssal_stone_brick, 0, "abybrick_2");
		registerItemRender(ACBlocks.abyssal_stone_brick_slab, 0);
		registerItemRender(BlockHandler.abyslab2, 0);
		registerItemRender(ACBlocks.abyssal_stone_brick_stairs, 0);
		registerItemRender(ACBlocks.coralium_ore, 0);
		registerItemRender(ACBlocks.abyssalnite_ore, 0);
		registerItemRender(ACBlocks.abyssal_stone_brick_fence, 0);
		registerItemRender(ACBlocks.darkstone_cobblestone_wall, 0);
		registerItemRender(ACBlocks.block_of_abyssalnite, 0, "abyblock");
		registerItemRender(ACBlocks.block_of_refined_coralium, 0, "corblock");
		registerItemRender(ACBlocks.block_of_dreadium, 0, "dreadiumblock");
		registerItemRender(ACBlocks.block_of_ethaxium, 0, "ethaxiumblock");
		registerItemRender(ACBlocks.coralium_infused_stone, 0);
		registerItemRender(ACBlocks.odb_core, 0);
		registerItemRender(ACBlocks.wooden_crate, 0);
		registerItemRender(ACBlocks.darkstone_slab, 0);
		registerItemRender(BlockHandler.Darkstoneslab2, 0);
		registerItemRender(ACBlocks.darkstone_button, 0);
		registerItemRender(ACBlocks.darkstone_pressure_plate, 0);
		registerItemRender(ACBlocks.darklands_oak_planks, 0);
		registerItemRender(ACBlocks.darklands_oak_button, 0);
		registerItemRender(ACBlocks.darklands_oak_pressure_plate, 0);
		registerItemRender(ACBlocks.darklands_oak_stairs, 0);
		registerItemRender(ACBlocks.darklands_oak_slab, 0);
		registerItemRender(BlockHandler.DLTslab2, 0);
		registerItemRender(ACBlocks.dreadlands_infused_powerstone, 0);
		registerItemRender(ACBlocks.abyssal_coralium_ore, 0);
		registerItemRender(BlockHandler.Altar, 0);
		registerItemRender(ACBlocks.abyssal_stone_button, 0);
		registerItemRender(ACBlocks.abyssal_stone_pressure_plate, 0);
		registerItemRender(ACBlocks.darkstone_brick_fence, 0);
		registerItemRender(ACBlocks.darklands_oak_fence, 0);
		registerItemRender(ACBlocks.dreadlands_abyssalnite_ore, 0);
		registerItemRender(ACBlocks.dreaded_abyssalnite_ore, 0);
		registerItemRender(ACBlocks.dreadstone_brick, 0, "dreadbrick_0");
		registerItemRender(ACBlocks.chiseled_dreadstone_brick, 0, "dreadbrick_1");
		registerItemRender(ACBlocks.cracked_dreadstone_brick, 0, "dreadbrick_2");
		registerItemRender(ACBlocks.abyssalnite_stone_brick, 0, "abydreadbrick_0");
		registerItemRender(ACBlocks.chiseled_abyssalnite_stone_brick, 0, "abydreadbrick_1");
		registerItemRender(ACBlocks.cracked_abyssalnite_stone_brick, 0, "abydreadbrick_2");
		registerItemRender(ACBlocks.dreadlands_grass, 0);
		registerItemRender(ACBlocks.dreadlands_log, 0);
		registerItemRender(ACBlocks.dreadlands_leaves, 0);
		registerItemRender(ACBlocks.dreadlands_sapling, 0);
		registerItemRender(ACBlocks.dreadlands_planks, 0);
		registerItemRender(ACBlocks.depths_ghoul_head, 0);
		registerItemRender(ACBlocks.pete_head, 0);
		registerItemRender(ACBlocks.mr_wilson_head, 0);
		registerItemRender(ACBlocks.dr_orange_head, 0);
		registerItemRender(ACBlocks.dreadstone_brick_stairs, 0);
		registerItemRender(ACBlocks.dreadstone_brick_fence, 0);
		registerItemRender(ACBlocks.dreadstone_brick_slab, 0);
		registerItemRender(BlockHandler.dreadbrickslab2, 0);
		registerItemRender(ACBlocks.abyssalnite_stone_brick_stairs, 0);
		registerItemRender(ACBlocks.abyssalnite_stone_brick_fence, 0);
		registerItemRender(ACBlocks.abyssalnite_stone_brick_slab, 0);
		registerItemRender(BlockHandler.abydreadbrickslab2, 0);

		registerItemRender(ACBlocks.coralium_stone_brick, 0, "cstonebrick_0");
		registerItemRender(ACBlocks.chiseled_coralium_stone_brick, 0, "cstonebrick_1");
		registerItemRender(ACBlocks.cracked_coralium_stone_brick, 0, "cstonebrick_2");
		registerItemRender(ACBlocks.coralium_stone_brick_fence, 0);
		registerItemRender(ACBlocks.coralium_stone_brick_slab, 0);
		registerItemRender(BlockHandler.cstonebrickslab2, 0);
		registerItemRender(ACBlocks.coralium_stone_brick_stairs, 0);
		registerItemRender(ACBlocks.coralium_stone_button, 0);
		registerItemRender(ACBlocks.coralium_stone_pressure_plate, 0);
		registerItemRender(ACBlocks.crystallizer_idle, 0);
		registerItemRender(ACBlocks.crystallizer_active, 0);
		registerItemRender(ACBlocks.transmutator_idle, 0);
		registerItemRender(ACBlocks.transmutator_active, 0);
		registerItemRender(ACBlocks.dreadguard_spawner, 0);
		registerItemRender(ACBlocks.chagaroth_spawner, 0);
		registerItemRender(ACBlocks.jzahar_spawner, 0);
		registerItemRender(ACBlocks.dreadlands_wood_fence, 0);
		registerItemRender(ACBlocks.nitre_ore, 0);
		registerItemRender(ACBlocks.abyssal_iron_ore, 0);
		registerItemRender(ACBlocks.abyssal_gold_ore, 0);
		registerItemRender(ACBlocks.abyssal_diamond_ore, 0);
		registerItemRender(ACBlocks.abyssal_nitre_ore, 0);
		registerItemRender(ACBlocks.abyssal_tin_ore, 0);
		registerItemRender(ACBlocks.abyssal_copper_ore, 0);
		registerItemRender(ACBlocks.pearlescent_coralium_ore, 0);
		registerItemRender(ACBlocks.liquified_coralium_ore, 0);
		registerItemRender(ACBlocks.solid_lava, 0);
		registerItemRender(ACBlocks.ethaxium_brick, 0, "ethaxiumbrick_0");
		registerItemRender(ACBlocks.chiseled_ethaxium_brick, 0, "ethaxiumbrick_1");
		registerItemRender(ACBlocks.cracked_ethaxium_brick, 0, "ethaxiumbrick_2");
		registerItemRender(ACBlocks.ethaxium_pillar, 0);
		registerItemRender(ACBlocks.ethaxium_brick_stairs, 0);
		registerItemRender(ACBlocks.ethaxium_brick_slab, 0);
		registerItemRender(BlockHandler.ethaxiumslab2, 0);
		registerItemRender(ACBlocks.ethaxium_brick_fence, 0);
		registerItemRender(BlockHandler.house, 0);
		registerItemRender(ACBlocks.materializer, 0);
		registerItemRender(ACBlocks.dark_ethaxium_brick, 0, "darkethaxiumbrick_0");
		registerItemRender(ACBlocks.chiseled_dark_ethaxium_brick, 0, "darkethaxiumbrick_1");
		registerItemRender(ACBlocks.cracked_dark_ethaxium_brick, 0, "darkethaxiumbrick_2");
		registerItemRender(ACBlocks.dark_ethaxium_pillar, 0);
		registerItemRender(ACBlocks.dark_ethaxium_brick_stairs, 0);
		registerItemRender(ACBlocks.dark_ethaxium_brick_slab, 0);
		registerItemRender(BlockHandler.darkethaxiumslab2, 0);
		registerItemRender(ACBlocks.dark_ethaxium_brick_fence, 0);
		registerItemRender(ACBlocks.ritual_altar_stone, 0, "ritualaltar_0");
		registerItemRender(ACBlocks.ritual_altar_darkstone, 0, "ritualaltar_1");
		registerItemRender(ACBlocks.ritual_altar_abyssal_stone, 0, "ritualaltar_2");
		registerItemRender(ACBlocks.ritual_altar_coralium_stone, 0, "ritualaltar_3");
		registerItemRender(ACBlocks.ritual_altar_dreadstone, 0, "ritualaltar_4");
		registerItemRender(ACBlocks.ritual_altar_abyssalnite_stone, 0, "ritualaltar_5");
		registerItemRender(ACBlocks.ritual_altar_ethaxium, 0, "ritualaltar_6");
		registerItemRender(ACBlocks.ritual_altar_dark_ethaxium, 0, "ritualaltar_7");
		registerItemRender(ACBlocks.ritual_pedestal_stone, 0, "ritualpedestal_0");
		registerItemRender(ACBlocks.ritual_pedestal_darkstone, 0, "ritualpedestal_1");
		registerItemRender(ACBlocks.ritual_pedestal_abyssal_stone, 0, "ritualpedestal_2");
		registerItemRender(ACBlocks.ritual_pedestal_coralium_stone, 0, "ritualpedestal_3");
		registerItemRender(ACBlocks.ritual_pedestal_dreadstone, 0, "ritualpedestal_4");
		registerItemRender(ACBlocks.ritual_pedestal_abyssalnite_stone, 0, "ritualpedestal_5");
		registerItemRender(ACBlocks.ritual_pedestal_ethaxium, 0, "ritualpedestal_6");
		registerItemRender(ACBlocks.ritual_pedestal_dark_ethaxium, 0, "ritualpedestal_7");
		registerItemRender(ACBlocks.shoggoth_ooze, 0);
		registerItemRender(ACBlocks.shoggoth_biomass, 0);
		registerItemRender(ACBlocks.energy_pedestal, 0);
		registerItemRender(ACBlocks.monolith_pillar, 0);
		registerItemRender(ACBlocks.sacrificial_altar, 0);
		registerItemRender(ACBlocks.overworld_energy_pedestal, 0, "tieredenergypedestal_0");
		registerItemRender(ACBlocks.abyssal_wasteland_energy_pedestal, 0, "tieredenergypedestal_1");
		registerItemRender(ACBlocks.dreadlands_energy_pedestal, 0, "tieredenergypedestal_2");
		registerItemRender(ACBlocks.omothol_energy_pedestal, 0, "tieredenergypedestal_3");
		registerItemRender(ACBlocks.overworld_sacrificial_altar, 0, "tieredsacrificialaltar_0");
		registerItemRender(ACBlocks.abyssal_wasteland_sacrificial_altar, 0, "tieredsacrificialaltar_1");
		registerItemRender(ACBlocks.dreadlands_sacrificial_altar, 0, "tieredsacrificialaltar_2");
		registerItemRender(ACBlocks.omothol_sacrificial_altar, 0, "tieredsacrificialaltar_3");
		registerItemRender(ACBlocks.minion_of_the_gatekeeper_spawner, 0);
		registerItemRender(ACBlocks.mimic_fire, 0);
		registerItemRender(ACBlocks.iron_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.gold_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.sulfur_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.carbon_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.oxygen_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.hydrogen_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.nitrogen_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.phosphorus_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.potassium_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.nitrate_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.methane_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.redstone_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.abyssalnite_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.coralium_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.dreadium_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.blaze_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.tin_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.copper_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.silicon_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.magnesium_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.aluminium_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.silica_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.alumina_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.magnesia_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.zinc_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.calcium_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.beryllium_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.beryl_crystal_cluster, 0, "crystalcluster");
		registerItemRender(ACBlocks.energy_collector, 0);
		registerItemRender(ACBlocks.energy_relay, 0);
		registerItemRender(ACBlocks.energy_container, 0);
		registerItemRender(ACBlocks.overworld_energy_collector, 0, "tieredenergycollector_0");
		registerItemRender(ACBlocks.abyssal_wasteland_energy_collector, 0, "tieredenergycollector_1");
		registerItemRender(ACBlocks.dreadlands_energy_collector, 0, "tieredenergycollector_2");
		registerItemRender(ACBlocks.omothol_energy_collector, 0, "tieredenergycollector_3");
		registerItemRender(ACBlocks.overworld_energy_relay, 0, "owenergyrelay");
		registerItemRender(ACBlocks.abyssal_wasteland_energy_relay, 0, "awenergyrelay");
		registerItemRender(ACBlocks.dreadlands_energy_relay, 0, "dlenergyrelay");
		registerItemRender(ACBlocks.omothol_energy_relay, 0, "omtenergyrelay");
		registerItemRender(ACBlocks.overworld_energy_container, 0, "tieredenergycontainer_0");
		registerItemRender(ACBlocks.abyssal_wasteland_energy_container, 0, "tieredenergycontainer_1");
		registerItemRender(ACBlocks.dreadlands_energy_container, 0, "tieredenergycontainer_2");
		registerItemRender(ACBlocks.omothol_energy_container, 0, "tieredenergycontainer_3");
		registerItemRender(ACBlocks.abyssal_sand, 0);
		registerItemRender(ACBlocks.fused_abyssal_sand, 0);
		registerItemRender(ACBlocks.abyssal_sand_glass, 0);
		registerItemRender(ACBlocks.dreadlands_dirt, 0);
		registerItemRender(ACBlocks.abyssal_cobblestone_stairs, 0);
		registerItemRender(ACBlocks.abyssal_cobblestone_slab, 0);
		registerItemRender(BlockHandler.abycobbleslab2, 0);
		registerItemRender(ACBlocks.abyssal_cobblestone_wall, 0);
		registerItemRender(ACBlocks.dreadstone_cobblestone_stairs, 0);
		registerItemRender(ACBlocks.dreadstone_cobblestone_slab, 0);
		registerItemRender(BlockHandler.dreadcobbleslab2, 0);
		registerItemRender(ACBlocks.dreadstone_cobblestone_wall, 0);
		registerItemRender(ACBlocks.abyssalnite_cobblestone_stairs, 0);
		registerItemRender(ACBlocks.abyssalnite_cobblestone_slab, 0);
		registerItemRender(BlockHandler.abydreadcobbleslab2, 0);
		registerItemRender(ACBlocks.abyssalnite_cobblestone_wall, 0);
		registerItemRender(ACBlocks.coralium_cobblestone_stairs, 0);
		registerItemRender(ACBlocks.coralium_cobblestone_slab, 0);
		registerItemRender(BlockHandler.cstonecobbleslab2, 0);
		registerItemRender(ACBlocks.coralium_cobblestone_wall, 0);
		registerItemRender(ACBlocks.luminous_thistle, 0);
		registerItemRender(ACBlocks.wastelands_thorn, 0);
		registerItemRender(ACBlocks.rending_pedestal, 0);
		registerItemRender(ACBlocks.state_transformer, 0);
		registerItemRender(ACBlocks.energy_depositioner, 0);
		registerItemRender(ACBlocks.calcified_stone, 0);
		registerItemRender(ACBlocks.multi_block, 0);
		registerItemRender(ACBlocks.sequential_brewing_stand, 0);
		registerItemRender(ACBlocks.portal_anchor, 0);
	}

	private void registerFluidModel(Block fluidBlock, String name) {
		Item item = Item.getItemFromBlock(fluidBlock);

		ModelBakery.registerItemVariants(item);

		final ModelResourceLocation modelResourceLocation = new ModelResourceLocation("abyssalcraft:fluid", name);

		ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);

		ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
				return modelResourceLocation;
			}
		});
	}

	protected void registerItemRender(Item item, int meta, String res){
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("abyssalcraft:" + res, "inventory"));
	}

	protected void registerItemRender(Item item, int meta){
		registerItemRender(item, meta, item.getRegistryName().getPath());
	}

	protected void registerItemRenders(Item item, int metas){
		for(int i = 0; i < metas; i++)
			registerItemRender(item, i);
	}

	protected void registerItemRender(Block block, int meta, String res){
		registerItemRender(Item.getItemFromBlock(block), meta, res);
	}

	protected void registerItemRender(Block block, int meta){
		registerItemRender(block, meta, block.getRegistryName().getPath());
	}

	protected void registerItemRenders(Block block, int metas){
		for(int i = 0; i < metas; i++)
			registerItemRender(block, i);
	}


}
