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
import com.shinoow.abyssalcraft.lib.NecronomiconText;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

import static com.shinoow.abyssalcraft.lib.util.ParticleUtil.makeVoidFog;

public class GeneralHooks {

	public static float partialTicks = 0;

	@SubscribeEvent
	public void onUpdateFOV(FOVUpdateEvent event) {
		float fov = event.getFov();

		if (event.getEntity().isHandActive() && event.getEntity().getActiveItemStack() != null &&
				event.getEntity().getActiveItemStack().getItem() ==
						ACItems.getInstance().coralium_longbow) {
			int duration = event.getEntity().getItemInUseCount();
			float multiplier = duration / 20.0F;

			if (multiplier > 1.0F) multiplier = 1.0F;
			else multiplier *= multiplier;

			fov *= 1.0F - multiplier * 0.15F;
		}

		event.setNewfov(fov);
	}

	@SubscribeEvent
	public void tooltipStuff(ItemTooltipEvent event) {
		ItemStack stack = event.getItemStack();

		if (stack.getItem() instanceof IEnergyContainerItem) event.getToolTip().add(1,
				String.format("%d/%d PE",
						(int) ((IEnergyContainerItem) stack.getItem()).getContainedEnergy(stack),
						((IEnergyContainerItem) stack.getItem()).getMaxEnergy(stack)));

		if (!APIUtils.display_names)
			if (stack.getItem() instanceof IUnlockableItem && event.getEntityPlayer() != null &&
					!NecroDataCapability.getCap(event.getEntityPlayer()).isUnlocked(
							((IUnlockableItem) stack.getItem()).getUnlockCondition(stack),
							event.getEntityPlayer())) {
				event.getToolTip().remove(0);
				event.getToolTip().add(0, "Lorem ipsum");
			}
		if (stack.getItem() instanceof IScroll) {
			Spell spell = SpellUtils.getSpell(stack);
			if (spell != null) {
				event.getToolTip().add(1, I18n.format(NecronomiconText.LABEL_SPELL_NAME) + ": " +
						TextFormatting.AQUA + spell.getLocalizedName());
				event.getToolTip().add(2, I18n.format(NecronomiconText.LABEL_SPELL_PE) + ": " +
						(int) spell.getReqEnergy());
				event.getToolTip().add(3, I18n.format(NecronomiconText.LABEL_SPELL_TYPE) + ": " +
						TextFormatting.GOLD +
						I18n.format(NecronomiconText.getSpellType(spell.requiresCharging())));
			}
		}
		if (stack.getItem() instanceof ICrystal) event.getToolTip()
				.add(String.format("%s: %s", I18n.format("tooltip.crystal"),
						((ICrystal) stack.getItem()).getFormula(stack)));
	}

	@SubscribeEvent
	public void tooltipFont(RenderTooltipEvent.Pre event) {
		if (!APIUtils.display_names && event.getLines().get(0).startsWith("\u00A7fLorem ipsum"))
			event.setFontRenderer(AbyssalCraftAPI.getAkloFont());
	}

	@SubscribeEvent
	public void renderTick(RenderTickEvent event) {
		if (event.phase == Phase.START) partialTicks = event.renderTickTime;
	}

	@SubscribeEvent
	public void clientTickEnd(ClientTickEvent event) {
		if (event.phase == Phase.END) {
			GuiScreen gui = Minecraft.getMinecraft().currentScreen;
			if (gui == null || !gui.doesGuiPauseGame()) partialTicks = 0;
		}
	}

	@SubscribeEvent
	public void voidFog(LivingUpdateEvent event) {
		if (event.getEntityLiving() == Minecraft.getMinecraft().player)
			makeVoidFog(event.getEntityLiving().world, event.getEntityLiving());
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event) {
		if (event.getEntity() == Minecraft.getMinecraft().player)
			AbyssalCraft.proxy.resetParticleCount();
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		ACItems.getInstance().registerItemsVariants();
		// TODO: Make this edge case conform to how items are usually registered
		ModelLoader.setCustomMeshDefinition(ACItems.getInstance().staff_of_the_gatekeeper,
				stack -> stack.hasTagCompound() && stack.getTagCompound().getInteger("Mode") == 1 ?
						new ModelResourceLocation("abyssalcraft:staff2", "inventory") :
						new ModelResourceLocation("abyssalcraft:staff", "inventory"));
		ACItems.getInstance().registerItemsRenders().registerItemsRenders();
		ACBlocks.getInstance().registerModels();

		registerFluidModel(ACBlocks.getInstance().liquid_coralium.getBlock(), "cor");
		registerFluidModel(ACBlocks.getInstance().liquid_antimatter.getBlock(), "anti");
	}

	private void registerFluidModel(Block fluidBlock, String name) {
		Item item = Item.getItemFromBlock(fluidBlock);
		ModelBakery.registerItemVariants(item);
		final ModelResourceLocation modelResourceLocation =
				new ModelResourceLocation("abyssalcraft:fluid", name);
		ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);
		ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState blockState) {
				return modelResourceLocation;
			}
		});
	}

}
