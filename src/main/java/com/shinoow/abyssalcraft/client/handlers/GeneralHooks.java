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

}
