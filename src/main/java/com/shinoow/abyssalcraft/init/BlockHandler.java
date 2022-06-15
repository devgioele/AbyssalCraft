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
package com.shinoow.abyssalcraft.init;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.biome.IDarklandsBiome;
import com.shinoow.abyssalcraft.api.block.ACBlock;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.item.IUnlockableItem;
import com.shinoow.abyssalcraft.api.necronomicon.condition.*;
import com.shinoow.abyssalcraft.common.blocks.*;
import com.shinoow.abyssalcraft.common.blocks.BlockACBrick.EnumBrickType;
import com.shinoow.abyssalcraft.common.blocks.BlockACCobblestone.EnumCobblestoneType;
import com.shinoow.abyssalcraft.common.blocks.BlockACStone.EnumStoneType;
import com.shinoow.abyssalcraft.common.blocks.BlockCrystalCluster.EnumCrystalType;
import com.shinoow.abyssalcraft.common.blocks.BlockCrystalCluster.EnumCrystalType2;
import com.shinoow.abyssalcraft.common.blocks.BlockRitualAltar.EnumRitualMatType;
import com.shinoow.abyssalcraft.common.blocks.BlockStatue.EnumDeityType;
import com.shinoow.abyssalcraft.common.blocks.BlockTieredEnergyPedestal.EnumDimType;
import com.shinoow.abyssalcraft.common.blocks.IngotBlock.EnumIngotType;
import com.shinoow.abyssalcraft.common.blocks.itemblock.*;
import com.shinoow.abyssalcraft.common.blocks.tile.*;
import com.shinoow.abyssalcraft.common.world.gen.WorldGenDLT;
import com.shinoow.abyssalcraft.common.world.gen.WorldGenDrT;
import com.shinoow.abyssalcraft.lib.ACLib;
import com.shinoow.abyssalcraft.lib.ACTabs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler implements ILifeCycleHandler {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		DimensionCondition abyssal_wasteland = new DimensionCondition(ACLib.abyssal_wasteland_id);
		DimensionCondition dreadlands = new DimensionCondition(ACLib.dreadlands_id);
		DimensionCondition omothol = new DimensionCondition(ACLib.omothol_id);

		ACBlocks.abyssal_stone =new ACBlock( new BlockACStone(EnumStoneType.ABYSSAL_STONE).setTranslationKey("abystone"),abyssal_wasteland);
		ACBlocks.darkstone =new ACBlock(new BlockACStone(EnumStoneType.DARKSTONE).setTranslationKey("darkstone"));
		ACBlocks.dreadstone =new ACBlock( new BlockACStone(EnumStoneType.DREADSTONE).setTranslationKey("dreadstone"),dreadlands);
		ACBlocks.abyssalnite_stone =new ACBlock( new BlockACStone(EnumStoneType.ABYSSALNITE_STONE).setTranslationKey("abydreadstone"),dreadlands);
		ACBlocks.coralium_stone =new ACBlock( new BlockACStone(EnumStoneType.CORALIUM_STONE).setTickRandomly(true).setTranslationKey("cstone"),abyssal_wasteland);
		ACBlocks.ethaxium =new ACBlock( new BlockACStone(EnumStoneType.ETHAXIUM).setTranslationKey("ethaxium"), omothol);
		ACBlocks.omothol_stone =new ACBlock( new BlockACStone(EnumStoneType.OMOTHOL_STONE).setTranslationKey("omotholstone"), omothol);
		ACBlocks.monolith_stone =new ACBlock( new BlockACStone(EnumStoneType.MONOLITH_STONE).setTranslationKey("monolithstone"));
		ACBlocks.darkstone_brick =new ACBlock(0, new BlockACBrick(1.65F, 12.0F, MapColor.BLACK).setTranslationKey("darkstone_brick"));
		ACBlocks.chiseled_darkstone_brick =new ACBlock(1, new BlockACBrick(1.65F, 12.0F, MapColor.BLACK).remap("darkstone_brick", EnumBrickType.CHISELED).setTranslationKey("chiseled_darkstone_brick"));
		ACBlocks.cracked_darkstone_brick =new ACBlock( 2, new BlockACBrick(1.65F, 12.0F, MapColor.BLACK).remap("darkstone_brick", EnumBrickType.CRACKED).setTranslationKey("cracked_darkstone_brick"));
		ACBlocks.darkstone_cobblestone =new ACBlock( new BlockACCobblestone(EnumCobblestoneType.DARKSTONE).setTranslationKey("darkstone_cobble"));
		ACBlocks.abyssal_cobblestone =new ACBlock( new BlockACCobblestone(EnumCobblestoneType.ABYSSAL_STONE).setTranslationKey("abyssalcobblestone"),abyssal_wasteland);
		ACBlocks.dreadstone_cobblestone =new ACBlock( new BlockACCobblestone(EnumCobblestoneType.DREADSTONE).setTranslationKey("dreadstonecobblestone"), dreadlands);
		ACBlocks.abyssalnite_cobblestone =new ACBlock( new BlockACCobblestone(EnumCobblestoneType.ABYSSALNITE_STONE).setTranslationKey("abyssalnitecobblestone"),dreadlands);
		ACBlocks.coralium_cobblestone =new ACBlock( new BlockACCobblestone(EnumCobblestoneType.CORALIUM_STONE).setTranslationKey("coraliumcobblestone"),abyssal_wasteland);
		ACBlocks.glowing_darkstone_bricks =new ACBlock( new BlockACBasic(Material.ROCK, "pickaxe", 3, 55F, 3000F, SoundType.STONE, MapColor.BLACK).setLightLevel(1.0F).setTranslationKey("dsglow"));
		ACBlocks.darkstone_brick_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, SoundType.STONE, MapColor.BLACK).setHardness(1.65F).setResistance(12.0F).setTranslationKey("darkbrickslab1"), new ItemSlabAC(ACBlocks.darkstone_brick_slab.getBlock(), ACBlocks.darkstone_brick_slab.getBlock(), ACBlocks.Darkbrickslab2.getBlock()));
		ACBlocks.Darkbrickslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.darkstone_brick_slab.getBlock(), Material.ROCK).setHardness(1.65F).setResistance(12.0F).setTranslationKey("darkbrickslab2"), new ItemSlabAC(ACBlocks.Darkbrickslab2.getBlock(), ACBlocks.darkstone_brick_slab.getBlock(), ACBlocks.Darkbrickslab2.getBlock()));
		ACBlocks.darkstone_cobblestone_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, SoundType.STONE, MapColor.BLACK).setHardness(1.65F) .setResistance(12.0F).setTranslationKey("darkcobbleslab1"), new ItemSlabAC(ACBlocks.darkstone_cobblestone_slab.getBlock(), ACBlocks.darkstone_cobblestone_slab.getBlock(), ACBlocks.Darkcobbleslab2.getBlock()));
		ACBlocks.Darkcobbleslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.darkstone_cobblestone_slab.getBlock(), Material.ROCK).setHardness(1.65F) .setResistance(12.0F).setTranslationKey("darkcobbleslab2"), new ItemSlabAC(ACBlocks.Darkcobbleslab2.getBlock(), ACBlocks.darkstone_cobblestone_slab.getBlock(), ACBlocks.Darkcobbleslab2.getBlock()));
		ACBlocks.darkstone_brick_stairs =new ACBlock( new BlockACStairs(ACBlocks.darkstone_brick.getBlock()).setHardness(1.65F).setResistance(12.0F).setTranslationKey("dbstairs"));
		ACBlocks.darkstone_cobblestone_stairs =new ACBlock( new BlockACStairs(ACBlocks.darkstone_cobblestone.getBlock()).setHardness(1.65F).setResistance(12.0F).setTranslationKey("dcstairs"));
		ACBlocks.darklands_oak_sapling =new ACBlock( new BlockACSapling(new WorldGenDLT(true)).setHardness(0.0F).setResistance(0.0F).setTranslationKey("dltsapling"));
		ACBlocks.darklands_oak_leaves =new ACBlock( new BlockACLeaves(ACBlocks.darklands_oak_sapling.getBlock(), MapColor.BLUE).setHardness(0.2F).setResistance(1.0F).setTranslationKey("dltleaves"));
		ACBlocks.darklands_oak_wood =new ACBlock( new BlockACLog(MapColor.BROWN).setHardness(2.0F).setResistance(1.0F).setTranslationKey("dltlog"));
		ACBlocks.darklands_oak_wood_2 =new ACBlock( new BlockACLog(MapColor.BROWN).setHardness(2.0F).setResistance(1.0F).setTranslationKey("dltlog"));
		ACBlocks.abyssal_stone_brick =new ACBlock(0, new BlockACBrick(2, 1.8F, 12.0F, MapColor.GREEN).setTranslationKey("abybrick"), abyssal_wasteland);
		ACBlocks.chiseled_abyssal_stone_brick =new ACBlock(1, new BlockACBrick(2, 1.8F, 12.0F, MapColor.GREEN).remap("abybrick", EnumBrickType.CHISELED).setTranslationKey("chiseled_abyssal_stone_brick"), abyssal_wasteland);
		ACBlocks.cracked_abyssal_stone_brick =new ACBlock( 2,new BlockACBrick(2, 1.8F, 12.0F, MapColor.GREEN).remap("abybrick", EnumBrickType.CRACKED).setTranslationKey("cracked_abyssal_stone_brick"), abyssal_wasteland);
		ACBlocks.abyssal_stone_brick_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, "pickaxe", 2, SoundType.STONE, MapColor.GREEN).setCreativeTab(ACTabs.tabBlock).setHardness(1.8F).setResistance(12.0F).setTranslationKey("abyslab1"), new ItemSlabAC(ACBlocks.abyssal_stone_brick_slab.getBlock(), ACBlocks.abyssal_stone_brick_slab.getBlock(), ACBlocks.abyslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		ACBlocks.abyslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.abyssal_stone_brick_slab.getBlock(), Material.ROCK, "pickaxe", 2).setHardness(1.8F).setResistance(12.0F).setTranslationKey("abyslab2"), new ItemSlabAC(ACBlocks.abyslab2.getBlock(), ACBlocks.abyssal_stone_brick_slab.getBlock(), ACBlocks.abyslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		ACBlocks.abyssal_stone_brick_stairs =new ACBlock( new BlockACStairs(ACBlocks.abyssal_stone_brick.getBlock(), "pickaxe", 2).setHardness(1.65F).setResistance(12.0F).setTranslationKey("abystairs"), abyssal_wasteland);
		ACBlocks.coralium_ore =new ACBlock( new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("coraliumore"));
		ACBlocks.abyssalnite_ore =new ACBlock( new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("abyore"), new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
		ACBlocks.abyssal_stone_brick_fence =new ACBlock( new BlockACFence(Material.ROCK, "pickaxe", 2, SoundType.STONE, MapColor.GREEN).setHardness(1.8F).setResistance(12.0F).setTranslationKey("abyfence"), abyssal_wasteland);
		ACBlocks.darkstone_cobblestone_wall =new ACBlock( new BlockACWall(ACBlocks.darkstone_cobblestone.getBlock()).setHardness(1.65F).setResistance(12.0F).setTranslationKey("dscwall"));
		ACBlocks.wooden_crate =new ACBlock( new BlockCrate().setHardness(3.0F).setResistance(6.0F).setTranslationKey("woodencrate"));
		ACBlocks.oblivion_deathbomb =new ACBlock( new BlockODB().setHardness(3.0F).setResistance(0F).setTranslationKey("odb"), new ItemODB(ACBlocks.oblivion_deathbomb.getBlock()), abyssal_wasteland);
		ACBlocks.block_of_abyssalnite =new ACBlock( new IngotBlock(EnumIngotType.ABYSSALNITE).setTranslationKey("abyblock"),new BiomePredicateCondition(b -> b instanceof IDarklandsBiome));
		ACBlocks.block_of_refined_coralium =new ACBlock( new IngotBlock(EnumIngotType.REFINED_CORALIUM).setTranslationKey("corblock"), abyssal_wasteland);
		ACBlocks.block_of_dreadium =new ACBlock( new IngotBlock(EnumIngotType.DREADIUM).setTranslationKey("dreadiumblock"), dreadlands);
		ACBlocks.block_of_ethaxium =new ACBlock( new IngotBlock(EnumIngotType.ETHAXIUM).setTranslationKey("ethaxiumblock"), omothol);
		ACBlocks.coralium_infused_stone =new ACBlock( new BlockACOre(3, 3.0F, 6.0F).setTranslationKey("coraliumstone"));
		ACBlocks.odb_core =new ACBlock( new BlockODBcore().setHardness(3.0F).setResistance(0F).setTranslationKey("odbcore"));
		ACBlocks.darkstone_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, SoundType.STONE, MapColor.BLACK).setCreativeTab(ACTabs.tabBlock).setHardness(1.65F).setResistance(12.0F).setTranslationKey("darkstoneslab1"), new ItemSlabAC(ACBlocks.darkstone_slab.getBlock(), ACBlocks.darkstone_slab.getBlock(), ACBlocks.Darkstoneslab2.getBlock()));
		ACBlocks.Darkstoneslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.darkstone_slab.getBlock(), Material.ROCK).setHardness(1.65F).setResistance(12.0F).setTranslationKey("darkstoneslab2"), new ItemSlabAC(ACBlocks.Darkstoneslab2.getBlock(), ACBlocks.darkstone_slab.getBlock(), ACBlocks.Darkstoneslab2.getBlock()));
		ACBlocks.darkstone_button =new ACBlock( new BlockACButton(true, "DS").setHardness(0.6F).setResistance(12.0F).setTranslationKey("dsbutton"));
		ACBlocks.darkstone_pressure_plate =new ACBlock( new BlockACPressureplate("DS", Material.ROCK, BlockACPressureplate.Sensitivity.MOBS, SoundType.STONE).setHardness(0.6F).setResistance(12.0F).setTranslationKey("dspplate"));
		ACBlocks.darklands_oak_planks =new ACBlock( new BlockACBasic(Material.WOOD, 2.0F, 5.0F, SoundType.WOOD, MapColor.BROWN).setTranslationKey("dltplank"));
		ACBlocks.darklands_oak_button =new ACBlock( new BlockACButton(true, "DLTplank").setHardness(0.5F).setTranslationKey("dltbutton"));
		ACBlocks.darklands_oak_pressure_plate =new ACBlock( new BlockACPressureplate("DLTplank", Material.WOOD, BlockACPressureplate.Sensitivity.EVERYTHING, SoundType.WOOD).setHardness(0.5F).setTranslationKey("dltpplate"));
		ACBlocks.darklands_oak_stairs =new ACBlock( new BlockACStairs(ACBlocks.darklands_oak_planks.getBlock()).setHardness(2.0F).setResistance(5.0F).setTranslationKey("dltstairs"));
		ACBlocks.darklands_oak_slab =new ACBlock( new BlockACSingleSlab(Material.WOOD, SoundType.WOOD, MapColor.BROWN).setHardness(2.0F).setResistance(5.0F).setTranslationKey("dltslab1"), new ItemSlabAC(ACBlocks.darklands_oak_slab.getBlock(), ACBlocks.darklands_oak_slab.getBlock(), ACBlocks.DLTslab2.getBlock()));
		ACBlocks.DLTslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.darklands_oak_slab.getBlock(), Material.WOOD).setHardness(2.0F).setResistance(5.0F).setTranslationKey("dltslab2"), new ItemSlabAC(ACBlocks.DLTslab2.getBlock(), ACBlocks.darklands_oak_slab.getBlock(), ACBlocks.DLTslab2.getBlock()), abyssal_wasteland);
		ACBlocks.dreadlands_infused_powerstone =new ACBlock( new BlockPSDL().setHardness(50.0F).setResistance(3000F).setCreativeTab(ACTabs.tabDecoration).setTranslationKey("psdl"));
		ACBlocks.abyssal_coralium_ore =new ACBlock( new BlockACOre(3, 3.0F, 6.0F).setTranslationKey("abycorore"), abyssal_wasteland);
		ACBlocks.Altar =new ACBlock( new BlockAltar().setHardness(4.0F).setResistance(300.0F).setTranslationKey("altar"));
		ACBlocks.abyssal_stone_button =new ACBlock( new BlockACButton(false, "pickaxe", 2, "AS").setHardness(0.8F).setResistance(12.0F).setTranslationKey("abybutton"), abyssal_wasteland);
		ACBlocks.abyssal_stone_pressure_plate =new ACBlock( new BlockACPressureplate("AS", Material.ROCK, BlockACPressureplate.Sensitivity.MOBS, "pickaxe", 2, SoundType.STONE).setHardness(0.8F).setResistance(12.0F).setTranslationKey("abypplate"), abyssal_wasteland);
		ACBlocks.darkstone_brick_fence =new ACBlock( new BlockACFence(Material.ROCK, SoundType.STONE, MapColor.BLACK).setHardness(1.65F).setResistance(12.0F).setTranslationKey("dsbfence"));
		ACBlocks.darklands_oak_fence =new ACBlock( new BlockACFence(Material.WOOD, SoundType.WOOD, MapColor.BROWN).setHardness(2.0F).setResistance(5.0F).setTranslationKey("dltfence"));
		ACBlocks.dreaded_abyssalnite_ore =new ACBlock( new BlockACOre(4, 2.5F, 20.0F).setTranslationKey("dreadore"), dreadlands);
		ACBlocks.dreadlands_abyssalnite_ore =new ACBlock( new BlockACOre(4, 2.5F, 20.0F).setTranslationKey("abydreadore"), dreadlands);
		ACBlocks.dreadstone_brick =new ACBlock( 0, new BlockACBrick(4, 2.5F, 20.0F, MapColor.RED).setTranslationKey("dreadbrick"), dreadlands);
		ACBlocks.chiseled_dreadstone_brick =new ACBlock(1,  new BlockACBrick(4, 2.5F, 20.0F, MapColor.RED).remap("dreadbrick", EnumBrickType.CHISELED).setTranslationKey("chiseled_dreadstone_brick"), dreadlands);
		ACBlocks.cracked_dreadstone_brick =new ACBlock(2, new BlockACBrick(4, 2.5F, 20.0F, MapColor.RED).remap("dreadbrick", EnumBrickType.CRACKED).setTranslationKey("cracked_dreadstone_brick"), dreadlands);
		ACBlocks.abyssalnite_stone_brick =new ACBlock( 0, new BlockACBrick(4, 2.5F, 20.0F, MapColor.PURPLE).setTranslationKey("abydreadbrick"), dreadlands);
		ACBlocks.chiseled_abyssalnite_stone_brick =new ACBlock(1, new BlockACBrick(4, 2.5F, 20.0F, MapColor.PURPLE).remap("abydreadstone", EnumBrickType.CHISELED).setTranslationKey("chiseled_abyssalnite_stone_brick"), dreadlands);
		ACBlocks.cracked_abyssalnite_stone_brick =new ACBlock(2, new BlockACBrick(4, 2.5F, 20.0F, MapColor.PURPLE).remap("abydreadbrick", EnumBrickType.CRACKED).setTranslationKey("cracked_abyssalnite_stone_brick"), dreadlands);
		ACBlocks.dreadlands_sapling =new ACBlock( new BlockACSapling(new WorldGenDrT(true)).setHardness(0.0F).setResistance(0.0F).setTranslationKey("dreadsapling"),dreadlands);
		ACBlocks.dreadlands_log =new ACBlock( new BlockACLog(MapColor.RED).setHardness(2.0F).setResistance(12.0F).setTranslationKey("dreadlog"),dreadlands);
		ACBlocks.dreadlands_leaves =new ACBlock( new BlockACLeaves(ACBlocks.dreadlands_sapling.getBlock(), MapColor.RED).setHardness(0.2F).setResistance(1.0F).setTranslationKey("dreadleaves"),dreadlands);
		ACBlocks.dreadlands_planks =new ACBlock( new BlockACBasic(Material.WOOD, 2.0F, 5.0F, SoundType.WOOD, MapColor.RED).setTranslationKey("dreadplanks"),dreadlands);
		ACBlocks.depths_ghoul_head =new ACBlock( new BlockDGhead().setTranslationKey("dghead"),new EntityCondition(AbyssalCraft.modid + ":depthsghoul"));
		ACBlocks.liquid_coralium =new ACBlock( new BlockCLiquid().setResistance(500.0F).setLightLevel(1.0F).setTranslationKey("cwater"));
		ACBlocks.dreadlands_grass =new ACBlock( new BlockDreadGrass().setHardness(0.4F).setTranslationKey("dreadgrass"), dreadlands);
		ACBlocks.pete_head =new ACBlock( new BlockDGhead().setTranslationKey("phead"), new EntityCondition(AbyssalCraft.modid + ":depthsghoul"));
		ACBlocks.mr_wilson_head =new ACBlock( new BlockDGhead().setTranslationKey("whead"), new EntityCondition(AbyssalCraft.modid + ":depthsghoul"));
		ACBlocks.dr_orange_head =new ACBlock( new BlockDGhead().setTranslationKey("ohead"),new EntityCondition(AbyssalCraft.modid + ":depthsghoul"));
		ACBlocks.dreadstone_brick_stairs =new ACBlock( new BlockACStairs(ACBlocks.dreadstone_brick.getBlock(), "pickaxe", 4).setHardness(2.5F).setResistance(20.0F).setTranslationKey("dreadbrickstairs"),dreadlands);
		ACBlocks.dreadstone_brick_fence =new ACBlock( new BlockACFence(Material.ROCK, "pickaxe", 4, SoundType.STONE, MapColor.RED).setHardness(2.5F).setResistance(20.0F).setTranslationKey("dreadbrickfence"),dreadlands);
		ACBlocks.dreadstone_brick_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, "pickaxe", 4, SoundType.STONE, MapColor.RED).setHardness(2.5F).setResistance(20.0F).setTranslationKey("dreadbrickslab1"), new ItemSlabAC(ACBlocks.dreadstone_brick_slab.getBlock(), ACBlocks.dreadstone_brick_slab.getBlock(), ACBlocks.dreadbrickslab2.getBlock()).setUnlockCondition(dreadlands));
		ACBlocks.dreadbrickslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.dreadstone_brick_slab.getBlock(), Material.ROCK, "pickaxe", 4).setHardness(2.5F).setResistance(20.0F).setTranslationKey("dreadbrickslab2"), new ItemSlabAC(ACBlocks.dreadbrickslab2.getBlock(), ACBlocks.dreadstone_brick_slab.getBlock(), ACBlocks.dreadbrickslab2.getBlock()).setUnlockCondition(dreadlands));
		ACBlocks.abyssalnite_stone_brick_stairs =new ACBlock( new BlockACStairs(ACBlocks.abyssalnite_stone_brick.getBlock(), "pickaxe", 4).setHardness(2.5F).setResistance(20.0F).setTranslationKey("abydreadbrickstairs"),dreadlands);
		ACBlocks.abyssalnite_stone_brick_fence =new ACBlock( new BlockACFence(Material.ROCK, "pickaxe", 4, SoundType.STONE, MapColor.PURPLE).setHardness(2.5F).setResistance(20.0F).setTranslationKey("abydreadbrickfence"),dreadlands);
		ACBlocks.abyssalnite_stone_brick_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, "pickaxe", 4, SoundType.STONE, MapColor.PURPLE).setHardness(2.5F).setResistance(20.0F).setTranslationKey("abydreadbrickslab1"), new ItemSlabAC(ACBlocks.abyssalnite_stone_brick_slab.getBlock(), ACBlocks.abyssalnite_stone_brick_slab.getBlock(), ACBlocks.abydreadbrickslab2.getBlock()).setUnlockCondition(dreadlands));
		ACBlocks.abydreadbrickslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.abyssalnite_stone_brick_slab.getBlock(), Material.ROCK, "pickaxe", 4).setHardness(2.5F).setResistance(20.0F).setTranslationKey("abydreadbrickslab2"), new ItemSlabAC(ACBlocks.abydreadbrickslab2.getBlock(), ACBlocks.abyssalnite_stone_brick_slab.getBlock(), ACBlocks.abydreadbrickslab2.getBlock()).setUnlockCondition(dreadlands));
		ACBlocks.liquid_antimatter =new ACBlock( new BlockAntiliquid().setResistance(500.0F).setLightLevel(0.5F).setTranslationKey("antiwater"));
		ACBlocks.coralium_stone_brick =new ACBlock( 0,new BlockACBrick(1.5F, 10.0F, MapColor.CYAN).setTranslationKey("cstonebrick"),abyssal_wasteland);
		ACBlocks.chiseled_coralium_stone_brick =new ACBlock(1, new BlockACBrick(1.5F, 10.0F, MapColor.CYAN).remap("cstonebrick", EnumBrickType.CHISELED).setTranslationKey("chiseled_coralium_stone_brick"),abyssal_wasteland);
		ACBlocks.cracked_coralium_stone_brick =new ACBlock( 2, new BlockACBrick(1.5F, 10.0F, MapColor.CYAN).remap("cstonebrick", EnumBrickType.CRACKED).setTranslationKey("cracked_coralium_stone_brick"),abyssal_wasteland);
		ACBlocks.coralium_stone_brick_fence =new ACBlock( new BlockACFence(Material.ROCK, SoundType.STONE, MapColor.CYAN).setHardness(1.5F).setResistance(10.0F).setTranslationKey("cstonebrickfence"),abyssal_wasteland);
		ACBlocks.coralium_stone_brick_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, SoundType.STONE, MapColor.CYAN).setHardness(1.5F).setResistance(10.0F).setTranslationKey("cstonebrickslab1"), new ItemSlabAC(ACBlocks.coralium_stone_brick_slab.getBlock(), ACBlocks.coralium_stone_brick_slab.getBlock(), ACBlocks.cstonebrickslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		ACBlocks.cstonebrickslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.coralium_stone_brick_slab.getBlock(), Material.ROCK).setHardness(1.5F).setResistance(10.0F).setTranslationKey("cstonebrickslab2"), new ItemSlabAC(ACBlocks.cstonebrickslab2.getBlock(), ACBlocks.coralium_stone_brick_slab.getBlock(), ACBlocks.cstonebrickslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		ACBlocks.coralium_stone_brick_stairs =new ACBlock( new BlockACStairs(ACBlocks.coralium_stone_brick.getBlock(), "pickaxe", 0).setHardness(1.5F).setResistance(10.0F).setTranslationKey("cstonebrickstairs"),abyssal_wasteland);
		ACBlocks.coralium_stone_button =new ACBlock( new BlockACButton(false, "cstone").setHardness(0.6F).setResistance(12.0F).setTranslationKey("cstonebutton"),abyssal_wasteland);
		ACBlocks.coralium_stone_pressure_plate =new ACBlock( new BlockACPressureplate("cstone", Material.ROCK, BlockACPressureplate.Sensitivity.MOBS, SoundType.STONE).setHardness(0.6F).setResistance(12.0F).setTranslationKey("cstonepplate"),abyssal_wasteland);
		ACBlocks.chagaroth_altar_top =new ACBlock( new BlockDreadAltarTop().setHardness(30.0F).setResistance(300.0F).setCreativeTab(ACTabs.tabDecoration).setTranslationKey("dreadaltartop"),dreadlands);
		ACBlocks.chagaroth_altar_bottom =new ACBlock( new BlockDreadAltarBottom().setHardness(30.0F).setResistance(300.0F).setCreativeTab(ACTabs.tabDecoration).setTranslationKey("dreadaltarbottom"),dreadlands);
		ACBlocks.crystallizer_idle =new ACBlock( new BlockCrystallizer(false).setHardness(2.5F).setResistance(12.0F).setTranslationKey("crystallizer"),dreadlands);
		ACBlocks.crystallizer_active =new ACBlock( new BlockCrystallizer(true).setHardness(2.5F).setResistance(12.0F).setLightLevel(0.875F).setTranslationKey("crystallizer_on"),dreadlands);
		ACBlocks.transmutator_idle =new ACBlock( new BlockTransmutator(false).setHardness(2.5F).setResistance(12.0F).setTranslationKey("transmutator"),abyssal_wasteland);
		ACBlocks.transmutator_active =new ACBlock( new BlockTransmutator(true).setHardness(2.5F).setResistance(12.0F).setLightLevel(0.875F).setTranslationKey("transmutator_on"),abyssal_wasteland);
		ACBlocks.dreadguard_spawner =new ACBlock( new BlockSingleMobSpawner().setTranslationKey("dreadguardspawner"));
		ACBlocks.chagaroth_spawner =new ACBlock( new BlockSingleMobSpawner().setTranslationKey("chagarothspawner"));
		ACBlocks.dreadlands_wood_fence =new ACBlock( new BlockACFence(Material.WOOD, SoundType.WOOD, MapColor.RED).setHardness(2.0F).setResistance(5.0F).setTranslationKey("drtfence"),dreadlands);
		ACBlocks.nitre_ore =new ACBlock( new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("nitreore"));
		ACBlocks.abyssal_iron_ore =new ACBlock( new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("abyiroore"), abyssal_wasteland);
		ACBlocks.abyssal_gold_ore =new ACBlock( new BlockACOre(2, 5.0F, 10.0F).setTranslationKey("abygolore"), abyssal_wasteland);
		ACBlocks.abyssal_diamond_ore =new ACBlock( new BlockACOre(2, 5.0F, 10.0F).setTranslationKey("abydiaore"),abyssal_wasteland);
		ACBlocks.abyssal_nitre_ore =new ACBlock( new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("abynitore"));
		ACBlocks.abyssal_tin_ore =new ACBlock( new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("abytinore"));
		ACBlocks.abyssal_copper_ore =new ACBlock( new BlockACOre(2, 3.0F, 6.0F).setTranslationKey("abycopore"));
		ACBlocks.pearlescent_coralium_ore =new ACBlock( new BlockACOre(5, 8.0F, 10.0F).setTranslationKey("abypcorore"),abyssal_wasteland);
		ACBlocks.liquified_coralium_ore =new ACBlock( new BlockACOre(4, 10.0F, 12.0F).setTranslationKey("abylcorore"),abyssal_wasteland);
		ACBlocks.solid_lava =new ACBlock( new BlockSolidLava("solidlava"));
		ACBlocks.ethaxium_brick =new ACBlock( 0,new BlockACBrick(8, 100.0F, Float.MAX_VALUE, MapColor.CLOTH).setTranslationKey("ethaxiumbrick"),omothol);
		ACBlocks.chiseled_ethaxium_brick =new ACBlock(1, new BlockACBrick(8, 100.0F, Float.MAX_VALUE, MapColor.CLOTH).remap("ethaxiumbrick", EnumBrickType.CHISELED).setTranslationKey("chiseled_ethaxium_brick"),omothol);
		ACBlocks.cracked_ethaxium_brick =new ACBlock(2, new BlockACBrick(8, 100.0F, Float.MAX_VALUE, MapColor.CLOTH).remap("ethaxiumbrick", EnumBrickType.CRACKED).setTranslationKey("cracked_ethaxium_brick"),omothol);
		ACBlocks.ethaxium_pillar =new ACBlock( new BlockEthaxiumPillar(100.0F, MapColor.CLOTH).setTranslationKey("ethaxiumpillar"),omothol);
		ACBlocks.ethaxium_brick_stairs =new ACBlock( new BlockACStairs(ACBlocks.ethaxium_brick.getBlock(), "pickaxe", 8).setHardness(100.0F).setResistance(Float.MAX_VALUE).setTranslationKey("ethaxiumbrickstairs"),omothol);
		ACBlocks.ethaxium_brick_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, "pickaxe", 8, SoundType.STONE, MapColor.CLOTH).setHardness(100.0F).setResistance(Float.MAX_VALUE).setTranslationKey("ethaxiumbrickslab1"), new ItemSlabAC(ACBlocks.ethaxium_brick_slab.getBlock(), ACBlocks.ethaxium_brick_slab.getBlock(), ACBlocks.ethaxiumslab2.getBlock()).setUnlockCondition(omothol));
		ACBlocks.ethaxiumslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.ethaxium_brick_slab.getBlock(), Material.ROCK, "pickaxe", 8).setHardness(100.0F).setResistance(Float.MAX_VALUE).setTranslationKey("ethaxiumbrickslab2"), new ItemSlabAC(ACBlocks.ethaxiumslab2.getBlock(), ACBlocks.ethaxium_brick_slab.getBlock(), ACBlocks.ethaxiumslab2.getBlock()).setUnlockCondition(omothol));
		ACBlocks.ethaxium_brick_fence =new ACBlock( new BlockACFence(Material.ROCK, "pickaxe", 8, SoundType.STONE, MapColor.CLOTH).setHardness(100.0F).setResistance(Float.MAX_VALUE).setTranslationKey("ethaxiumfence"),omothol);
		ACBlocks.engraver =new ACBlock( new BlockEngraver().setHardness(2.5F).setResistance(12.0F).setTranslationKey("engraver"),omothol);
		ACBlocks.house =new ACBlock( new BlockHouse().setHardness(1.0F).setResistance(Float.MAX_VALUE).setTranslationKey("engraver_on"));
		ACBlocks.materializer =new ACBlock( new BlockMaterializer().setTranslationKey("materializer"),omothol);
		ACBlocks.dark_ethaxium_brick =new ACBlock(0, new BlockACBrick(8, 150.0F, Float.MAX_VALUE).setTranslationKey("darkethaxiumbrick"),omothol);
		ACBlocks.chiseled_dark_ethaxium_brick =new ACBlock(1, new BlockACBrick(8, 150.0F, Float.MAX_VALUE).remap("darkethaxiumbrick", EnumBrickType.CHISELED).setTranslationKey("chiseled_dark_ethaxium_brick"),omothol);
		ACBlocks.cracked_dark_ethaxium_brick =new ACBlock(2, new BlockACBrick(8, 150.0F, Float.MAX_VALUE).remap("darkethaxiumbrick", EnumBrickType.CRACKED).setTranslationKey("cracked_dark_ethaxium_brick"),omothol);
		ACBlocks.dark_ethaxium_pillar =new ACBlock( new BlockEthaxiumPillar(150.0F, MapColor.STONE).setTranslationKey("darkethaxiumpillar"),omothol);
		ACBlocks.dark_ethaxium_brick_stairs =new ACBlock( new BlockACStairs(ACBlocks.dark_ethaxium_brick.getBlock(), "pickaxe", 8).setHardness(150.0F).setResistance(Float.MAX_VALUE).setTranslationKey("darkethaxiumbrickstairs"),omothol);
		ACBlocks.dark_ethaxium_brick_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, "pickaxe", 8, SoundType.STONE).setHardness(150.0F).setResistance(Float.MAX_VALUE).setTranslationKey("darkethaxiumbrickslab1"), new ItemSlabAC(ACBlocks.dark_ethaxium_brick_slab.getBlock(), ACBlocks.dark_ethaxium_brick_slab.getBlock(), ACBlocks.ethaxiumslab2.getBlock()).setUnlockCondition(omothol));
		ACBlocks.darkethaxiumslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.dark_ethaxium_brick_slab.getBlock(), Material.ROCK, "pickaxe", 8).setHardness(150.0F).setResistance(Float.MAX_VALUE).setTranslationKey("darkethaxiumbrickslab2"), new ItemSlabAC(ACBlocks.darkethaxiumslab2.getBlock(), ACBlocks.dark_ethaxium_brick_slab.getBlock(), ACBlocks.darkethaxiumslab2.getBlock()).setUnlockCondition(omothol));
		ACBlocks.dark_ethaxium_brick_fence =new ACBlock( new BlockACFence(Material.ROCK, "pickaxe", 8, SoundType.STONE, MapColor.STONE).setHardness(150.0F).setResistance(Float.MAX_VALUE).setTranslationKey("darkethaxiumbrickfence"),omothol);
		ACBlocks.ritual_altar_stone =new ACBlock( 0,new BlockRitualAltar(
				Blocks.COBBLESTONE::getDefaultState, 0, EnumRitualMatType.COBBLESTONE).setTranslationKey("ritualaltar"));
		ACBlocks.ritual_altar_darkstone =new ACBlock( 1,new BlockRitualAltar(() -> ACBlocks.darkstone_cobblestone.getBlock().getDefaultState(), 0, EnumRitualMatType.DARKSTONE_COBBLESTONE).setTranslationKey("ritualaltar"));
		ACBlocks.ritual_altar_abyssal_stone =new ACBlock(2, new BlockRitualAltar(() -> ACBlocks.abyssal_cobblestone.getBlock().getDefaultState(), 1, EnumRitualMatType.ABYSSAL_STONE_BRICK).setTranslationKey("ritualaltar"));
		ACBlocks.ritual_altar_coralium_stone =new ACBlock( 3,new BlockRitualAltar(() -> ACBlocks.coralium_cobblestone.getBlock().getDefaultState(), 1, EnumRitualMatType.CORALIUM_STONE_BRICK).setTranslationKey("ritualaltar"));
		ACBlocks.ritual_altar_dreadstone =new ACBlock( 4, new BlockRitualAltar(() -> ACBlocks.dreadstone_cobblestone.getBlock().getDefaultState(), 2, EnumRitualMatType.DREADSTONE_BRICK).setTranslationKey("ritualaltar"));
		ACBlocks.ritual_altar_abyssalnite_stone =new ACBlock(5, new BlockRitualAltar(() -> ACBlocks.abyssalnite_cobblestone.getBlock().getDefaultState(), 2, EnumRitualMatType.ABYSSALNITE_STONE_BRICK).setTranslationKey("ritualaltar"));
		ACBlocks.ritual_altar_ethaxium =new ACBlock( 6, new BlockRitualAltar(() -> ACBlocks.ethaxium_brick.getBlock().getDefaultState(), 3, EnumRitualMatType.ETHAXIUM_BRICK).setTranslationKey("ritualaltar"));
		ACBlocks.ritual_altar_dark_ethaxium =new ACBlock(7, new BlockRitualAltar(() -> ACBlocks.dark_ethaxium_brick.getBlock().getDefaultState(), 3, EnumRitualMatType.DARK_ETHAXIUM_BRICK).setTranslationKey("ritualaltar"));
		ACBlocks.ritual_pedestal_stone =new ACBlock(0, new BlockRitualPedestal(
				Blocks.COBBLESTONE::getDefaultState, 0, EnumRitualMatType.COBBLESTONE).setTranslationKey("ritualpedestal"));
		ACBlocks.ritual_pedestal_darkstone =new ACBlock(1, new BlockRitualPedestal(() -> ACBlocks.darkstone_cobblestone.getBlock().getDefaultState(), 0, EnumRitualMatType.DARKSTONE_COBBLESTONE).setTranslationKey("ritualpedestal"));
		ACBlocks.ritual_pedestal_abyssal_stone =new ACBlock(2,  new BlockRitualPedestal(() -> ACBlocks.abyssal_cobblestone.getBlock().getDefaultState(), 1, EnumRitualMatType.ABYSSAL_STONE_BRICK).setTranslationKey("ritualpedestal"));
		ACBlocks.ritual_pedestal_coralium_stone =new ACBlock( 3,new BlockRitualPedestal(() -> ACBlocks.coralium_cobblestone.getBlock().getDefaultState(), 1, EnumRitualMatType.CORALIUM_STONE_BRICK).setTranslationKey("ritualpedestal"));
		ACBlocks.ritual_pedestal_dreadstone =new ACBlock( 4,new BlockRitualPedestal(() -> ACBlocks.dreadstone_cobblestone.getBlock().getDefaultState(), 2, EnumRitualMatType.DREADSTONE_BRICK).setTranslationKey("ritualpedestal"));
		ACBlocks.ritual_pedestal_abyssalnite_stone =new ACBlock(5, new BlockRitualPedestal(() -> ACBlocks.abyssalnite_cobblestone.getBlock().getDefaultState(), 2, EnumRitualMatType.ABYSSALNITE_STONE_BRICK).setTranslationKey("ritualpedestal"));
		ACBlocks.ritual_pedestal_ethaxium =new ACBlock(6, new BlockRitualPedestal(() -> ACBlocks.ethaxium_brick.getBlock().getDefaultState(), 3, EnumRitualMatType.ETHAXIUM_BRICK).setTranslationKey("ritualpedestal"));
		ACBlocks.ritual_pedestal_dark_ethaxium =new ACBlock(7, new BlockRitualPedestal(() -> ACBlocks.dark_ethaxium_brick.getBlock().getDefaultState(), 3, EnumRitualMatType.DARK_ETHAXIUM_BRICK).setTranslationKey("ritualpedestal"));
		ACBlocks.shoggoth_ooze =new ACBlock( new BlockShoggothOoze().setTranslationKey("shoggothblock"), new ItemShoggothOoze(ACBlocks.shoggoth_ooze.getBlock()));
		ACBlocks.cthulhu_statue =new ACBlock( new BlockStatue(EnumDeityType.CTHULHU).setTranslationKey("cthulhustatue"));
		ACBlocks.hastur_statue =new ACBlock( new BlockStatue(EnumDeityType.HASTUR).setTranslationKey("hasturstatue"));
		ACBlocks.jzahar_statue =new ACBlock( new BlockStatue(EnumDeityType.JZAHAR).setTranslationKey("jzaharstatue"));
		ACBlocks.azathoth_statue =new ACBlock( new BlockStatue(EnumDeityType.AZATHOTH).setTranslationKey("azathothstatue"));
		ACBlocks.nyarlathotep_statue =new ACBlock( new BlockStatue(EnumDeityType.NYARLATHOTEP).setTranslationKey("nyarlathotepstatue"));
		ACBlocks.yog_sothoth_statue =new ACBlock( new BlockStatue(EnumDeityType.YOGSOTHOTH).setTranslationKey("yogsothothstatue"));
		ACBlocks.shub_niggurath_statue =new ACBlock( new BlockStatue(EnumDeityType.SHUBNIGGURATH).setTranslationKey("shubniggurathstatue"));
		ACBlocks.shoggoth_biomass =new ACBlock( new BlockShoggothBiomass());
		ACBlocks.energy_pedestal =new ACBlock( new BlockEnergyPedestal(), new ItemPEContainerBlock(ACBlocks.energy_pedestal.getBlock()));
		ACBlocks.monolith_pillar =new ACBlock( new BlockMonolithPillar());
		ACBlocks.sacrificial_altar =new ACBlock( new BlockSacrificialAltar(), new ItemPEContainerBlock(ACBlocks.sacrificial_altar.getBlock()));
		ACBlocks.overworld_energy_pedestal =new ACBlock("tieredenergypedestal_0", new BlockTieredEnergyPedestal(EnumDimType.OVERWORLD).setTranslationKey("overworld_energy_pedestal"), new ItemPEContainerBlock(ACBlocks.overworld_energy_pedestal.getBlock()));
		ACBlocks.abyssal_wasteland_energy_pedestal =new ACBlock("tieredenergypedestal_1", new BlockTieredEnergyPedestal(EnumDimType.ABYSSAL_WASTELAND).setTranslationKey("abyssal_wasteland_energy_pedestal"), new ItemPEContainerBlock(ACBlocks.abyssal_wasteland_energy_pedestal.getBlock()), abyssal_wasteland);
		ACBlocks.dreadlands_energy_pedestal =new ACBlock( "tieredenergypedestal_2",new BlockTieredEnergyPedestal(EnumDimType.DREADLANDS).setTranslationKey("dreadlands_energy_pedestal"), new ItemPEContainerBlock(ACBlocks.dreadlands_energy_pedestal.getBlock()),dreadlands);
		ACBlocks.omothol_energy_pedestal =new ACBlock( "tieredenergypedestal_3",new BlockTieredEnergyPedestal(EnumDimType.OMOTHOL).setTranslationKey("omothol_energy_pedestal"), new ItemPEContainerBlock(ACBlocks.omothol_energy_pedestal.getBlock()), omothol);
		ACBlocks.overworld_sacrificial_altar =new ACBlock("tieredsacrificialaltar_0", new BlockTieredSacrificialAltar(EnumDimType.OVERWORLD).setTranslationKey("overworld_sacrificial_altar"), new ItemPEContainerBlock(ACBlocks.overworld_sacrificial_altar.getBlock()));
		ACBlocks.abyssal_wasteland_sacrificial_altar =new ACBlock( "tieredsacrificialaltar_1", new BlockTieredSacrificialAltar(EnumDimType.ABYSSAL_WASTELAND).setTranslationKey("abyssal_wasteland_sacrificial_altar"), new ItemPEContainerBlock(ACBlocks.abyssal_wasteland_sacrificial_altar.getBlock()), abyssal_wasteland);
		ACBlocks.dreadlands_sacrificial_altar =new ACBlock( "tieredsacrificialaltar_2",new BlockTieredSacrificialAltar(EnumDimType.DREADLANDS).setTranslationKey("dreadlands_sacrificial_altar"), new ItemPEContainerBlock(ACBlocks.dreadlands_sacrificial_altar.getBlock()),dreadlands);
		ACBlocks.omothol_sacrificial_altar =new ACBlock( "tieredsacrificialaltar_3", new BlockTieredSacrificialAltar(EnumDimType.OMOTHOL).setTranslationKey("omothol_sacrificial_altar"), new ItemPEContainerBlock(ACBlocks.omothol_sacrificial_altar.getBlock()), omothol);
		ACBlocks.jzahar_spawner =new ACBlock( new BlockSingleMobSpawner().setTranslationKey("jzaharspawner"));
		ACBlocks.minion_of_the_gatekeeper_spawner =new ACBlock( new BlockSingleMobSpawner().setTranslationKey("gatekeeperminionspawner"));
		ACBlocks.mimic_fire =new ACBlock( new BlockMimicFire().setTranslationKey("fire"));
		ACBlocks.decorative_cthulhu_statue =new ACBlock( new BlockDecorativeStatue(EnumDeityType.CTHULHU).setTranslationKey("cthulhustatue"), new ItemDecorativeStatueBlock(ACBlocks.decorative_cthulhu_statue.getBlock()));
		ACBlocks.decorative_hastur_statue =new ACBlock( new BlockDecorativeStatue(EnumDeityType.HASTUR).setTranslationKey("hasturstatue"), new ItemDecorativeStatueBlock(ACBlocks.decorative_hastur_statue.getBlock()));
		ACBlocks.decorative_jzahar_statue =new ACBlock( new BlockDecorativeStatue(EnumDeityType.JZAHAR).setTranslationKey("jzaharstatue"), new ItemDecorativeStatueBlock(ACBlocks.decorative_jzahar_statue.getBlock()));
		ACBlocks.decorative_azathoth_statue =new ACBlock( new BlockDecorativeStatue(EnumDeityType.AZATHOTH).setTranslationKey("azathothstatue"), new ItemDecorativeStatueBlock(ACBlocks.decorative_azathoth_statue.getBlock()));
		ACBlocks.decorative_nyarlathotep_statue =new ACBlock( new BlockDecorativeStatue(EnumDeityType.NYARLATHOTEP).setTranslationKey("nyarlathotepstatue"), new ItemDecorativeStatueBlock(ACBlocks.decorative_nyarlathotep_statue.getBlock()));
		ACBlocks.decorative_yog_sothoth_statue =new ACBlock( new BlockDecorativeStatue(EnumDeityType.YOGSOTHOTH).setTranslationKey("yogsothothstatue"), new ItemDecorativeStatueBlock(ACBlocks.decorative_yog_sothoth_statue.getBlock()));
		ACBlocks.decorative_shub_niggurath_statue =new ACBlock( new BlockDecorativeStatue(EnumDeityType.SHUBNIGGURATH).setTranslationKey("shubniggurathstatue"), new ItemDecorativeStatueBlock(ACBlocks.decorative_shub_niggurath_statue.getBlock()));
		ACBlocks.iron_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.IRON), new ItemCrystalClusterBlock(ACBlocks.iron_crystal_cluster.getBlock()));
		ACBlocks.gold_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.GOLD), new ItemCrystalClusterBlock(ACBlocks.gold_crystal_cluster.getBlock()));
		ACBlocks.sulfur_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.SULFUR), new ItemCrystalClusterBlock(ACBlocks.sulfur_crystal_cluster.getBlock()));
		ACBlocks.carbon_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.CARBON), new ItemCrystalClusterBlock(ACBlocks.carbon_crystal_cluster.getBlock()));
		ACBlocks.oxygen_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.OXYGEN), new ItemCrystalClusterBlock(ACBlocks.oxygen_crystal_cluster.getBlock()));
		ACBlocks.hydrogen_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.HYDROGEN), new ItemCrystalClusterBlock(ACBlocks.hydrogen_crystal_cluster.getBlock()));
		ACBlocks.nitrogen_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.NITROGEN), new ItemCrystalClusterBlock(ACBlocks.nitrogen_crystal_cluster.getBlock()));
		ACBlocks.phosphorus_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.PHOSPHORUS), new ItemCrystalClusterBlock(ACBlocks.phosphorus_crystal_cluster.getBlock()));
		ACBlocks.potassium_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.POTASSIUM), new ItemCrystalClusterBlock(ACBlocks.potassium_crystal_cluster.getBlock()));
		ACBlocks.nitrate_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.NITRATE), new ItemCrystalClusterBlock(ACBlocks.nitrate_crystal_cluster.getBlock()));
		ACBlocks.methane_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.METHANE), new ItemCrystalClusterBlock(ACBlocks.methane_crystal_cluster.getBlock()));
		ACBlocks.redstone_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.REDSTONE), new ItemCrystalClusterBlock(ACBlocks.redstone_crystal_cluster.getBlock()));
		ACBlocks.abyssalnite_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.ABYSSALNITE), new ItemCrystalClusterBlock(ACBlocks.abyssalnite_crystal_cluster.getBlock()));
		ACBlocks.coralium_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.CORALIUM), new ItemCrystalClusterBlock(ACBlocks.coralium_crystal_cluster.getBlock()));
		ACBlocks.dreadium_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.DREADIUM), new ItemCrystalClusterBlock(ACBlocks.dreadium_crystal_cluster.getBlock()));
		ACBlocks.blaze_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType.BLAZE), new ItemCrystalClusterBlock(ACBlocks.blaze_crystal_cluster.getBlock()));
		ACBlocks.tin_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.TIN), new ItemCrystalClusterBlock(ACBlocks.tin_crystal_cluster.getBlock()));
		ACBlocks.copper_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.COPPER), new ItemCrystalClusterBlock(ACBlocks.copper_crystal_cluster.getBlock()));
		ACBlocks.silicon_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.SILICON), new ItemCrystalClusterBlock(ACBlocks.silicon_crystal_cluster.getBlock()));
		ACBlocks.magnesium_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.MAGNESIUM), new ItemCrystalClusterBlock(ACBlocks.magnesium_crystal_cluster.getBlock()));
		ACBlocks.aluminium_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.ALUMINIUM), new ItemCrystalClusterBlock(ACBlocks.aluminium_crystal_cluster.getBlock()));
		ACBlocks.silica_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.SILICA), new ItemCrystalClusterBlock(ACBlocks.silica_crystal_cluster.getBlock()));
		ACBlocks.alumina_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.ALUMINA), new ItemCrystalClusterBlock(ACBlocks.alumina_crystal_cluster.getBlock()));
		ACBlocks.magnesia_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.MAGNESIA), new ItemCrystalClusterBlock(ACBlocks.magnesia_crystal_cluster.getBlock()));
		ACBlocks.zinc_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.ZINC), new ItemCrystalClusterBlock(ACBlocks.zinc_crystal_cluster.getBlock()));
		ACBlocks.calcium_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.CALCIUM), new ItemCrystalClusterBlock(ACBlocks.calcium_crystal_cluster.getBlock()));
		ACBlocks.beryllium_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.BERYLLIUM), new ItemCrystalClusterBlock(ACBlocks.beryllium_crystal_cluster.getBlock()));
		ACBlocks.beryl_crystal_cluster =new ACBlock("crystalcluster", new BlockCrystalCluster().remap(EnumCrystalType2.BERYL), new ItemCrystalClusterBlock(ACBlocks.beryl_crystal_cluster.getBlock()));
		ACBlocks.energy_collector =new ACBlock( new BlockEnergyCollector(), new ItemPEContainerBlock(ACBlocks.energy_collector.getBlock()));
		ACBlocks.energy_relay =new ACBlock( new BlockEnergyRelay(), new ItemPEContainerBlock(ACBlocks.energy_relay.getBlock()));
		ACBlocks.energy_container =new ACBlock( new BlockEnergyContainer(), new ItemPEContainerBlock(ACBlocks.energy_container.getBlock()));
		ACBlocks.overworld_energy_collector =new ACBlock("tieredenergycollector_0",new BlockTieredEnergyCollector(EnumDimType.OVERWORLD).setTranslationKey("overworld_energy_collector"), new ItemPEContainerBlock(ACBlocks.overworld_energy_collector.getBlock()));
		ACBlocks.abyssal_wasteland_energy_collector =new ACBlock("tieredenergycollector_1", new BlockTieredEnergyCollector(EnumDimType.ABYSSAL_WASTELAND).setTranslationKey("abyssal_wasteland_energy_collector"), new ItemPEContainerBlock(ACBlocks.abyssal_wasteland_energy_collector.getBlock()),abyssal_wasteland);
		ACBlocks.dreadlands_energy_collector =new ACBlock("tieredenergycollector_2", new BlockTieredEnergyCollector(EnumDimType.DREADLANDS).setTranslationKey("dreadlands_energy_collector"), new ItemPEContainerBlock(ACBlocks.dreadlands_energy_collector.getBlock()),dreadlands);
		ACBlocks.omothol_energy_collector =new ACBlock("tieredenergycollector_3", new BlockTieredEnergyCollector(EnumDimType.OMOTHOL).setTranslationKey("omothol_energy_collector"), new ItemPEContainerBlock(ACBlocks.omothol_energy_collector.getBlock()),omothol);
		ACBlocks.overworld_energy_relay =new ACBlock("owenergyrelay", new BlockTieredEnergyRelay(EnumDimType.OVERWORLD).setTranslationKey("overworld_energy_relay"), new ItemPEContainerBlock(ACBlocks.overworld_energy_relay.getBlock()));
		ACBlocks.abyssal_wasteland_energy_relay =new ACBlock("awenergyrelay", new BlockTieredEnergyRelay(EnumDimType.ABYSSAL_WASTELAND).setTranslationKey("abyssal_wasteland_energy_relay"), new ItemPEContainerBlock(ACBlocks.abyssal_wasteland_energy_relay.getBlock()),abyssal_wasteland);
		ACBlocks.dreadlands_energy_relay =new ACBlock("dlenergyrelay", new BlockTieredEnergyRelay(EnumDimType.DREADLANDS).setTranslationKey("dreadlands_energy_relay"), new ItemPEContainerBlock(ACBlocks.dreadlands_energy_relay.getBlock()),dreadlands);
		ACBlocks.omothol_energy_relay =new ACBlock( "omtenergyrelay",new BlockTieredEnergyRelay(EnumDimType.OMOTHOL).setTranslationKey("omothol_energy_relay"), new ItemPEContainerBlock(ACBlocks.omothol_energy_relay.getBlock()),omothol);
		ACBlocks.overworld_energy_container =new ACBlock( "tieredenergycontainer_0", new BlockTieredEnergyContainer(EnumDimType.OVERWORLD).setTranslationKey("overworld_energy_container"), new ItemPEContainerBlock(ACBlocks.overworld_energy_container.getBlock()));
		ACBlocks.abyssal_wasteland_energy_container =new ACBlock("tieredenergycontainer_1", new BlockTieredEnergyContainer(EnumDimType.ABYSSAL_WASTELAND).setTranslationKey("abyssal_wasteland_energy_container"), new ItemPEContainerBlock(ACBlocks.abyssal_wasteland_energy_container.getBlock()),abyssal_wasteland);
		ACBlocks.dreadlands_energy_container =new ACBlock("tieredenergycontainer_2", new BlockTieredEnergyContainer(EnumDimType.DREADLANDS).setTranslationKey("dreadlands_energy_container"), new ItemPEContainerBlock(ACBlocks.dreadlands_energy_container.getBlock()),dreadlands);
		ACBlocks.omothol_energy_container =new ACBlock("tieredenergycontainer_3", new BlockTieredEnergyContainer(EnumDimType.OMOTHOL).setTranslationKey("omothol_energy_container"), new ItemPEContainerBlock(ACBlocks.omothol_energy_container.getBlock()),omothol);
		ACBlocks.abyssal_sand =new ACBlock( new BlockAbyssalSand(),abyssal_wasteland);
		ACBlocks.fused_abyssal_sand =new ACBlock( new BlockFusedAbyssalSand(),abyssal_wasteland);
		ACBlocks.abyssal_sand_glass =new ACBlock( new BlockAbyssalSandGlass(),abyssal_wasteland);
		ACBlocks.dreadlands_dirt =new ACBlock( new BlockDreadlandsDirt(),dreadlands);
		ACBlocks.abyssal_cobblestone_stairs =new ACBlock( new BlockACStairs(ACBlocks.abyssal_cobblestone.getBlock(), "pickaxe", 2).setTranslationKey("abyssalcobblestonestairs"),abyssal_wasteland);
		ACBlocks.abyssal_cobblestone_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, "pickaxe", 2, SoundType.STONE, MapColor.GREEN).setHardness(2.6F).setResistance(12.0F).setTranslationKey("abyssalcobblestoneslab1"), new ItemSlabAC(ACBlocks.abyssal_cobblestone_slab.getBlock(), ACBlocks.abyssal_cobblestone_slab.getBlock(), ACBlocks.abycobbleslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		ACBlocks.abycobbleslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.abyssal_cobblestone_slab.getBlock(), Material.ROCK, "pickaxe", 2).setHardness(2.6F).setResistance(12.0F).setTranslationKey("abyssalcobblestoneslab2"), new ItemSlabAC(ACBlocks.abycobbleslab2.getBlock(), ACBlocks.abyssal_cobblestone_slab.getBlock(), ACBlocks.abycobbleslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		ACBlocks.abyssal_cobblestone_wall =new ACBlock( new BlockACWall(ACBlocks.abyssal_cobblestone.getBlock(), 2).setHardness(2.6F).setResistance(12.0F).setTranslationKey("abyssalcobblestonewall"),abyssal_wasteland);
		ACBlocks.dreadstone_cobblestone_stairs =new ACBlock( new BlockACStairs(ACBlocks.dreadstone_cobblestone.getBlock(), "pickaxe", 4).setTranslationKey("dreadstonecobblestonestairs"),dreadlands);
		ACBlocks.dreadstone_cobblestone_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, "pickaxe", 4, SoundType.STONE, MapColor.RED).setHardness(3.3F).setResistance(20.0F).setTranslationKey("dreadstonecobblestoneslab1"), new ItemSlabAC(ACBlocks.dreadstone_cobblestone_slab.getBlock(), ACBlocks.dreadstone_cobblestone_slab.getBlock(), ACBlocks.dreadcobbleslab2.getBlock()).setUnlockCondition(dreadlands));
		ACBlocks.dreadcobbleslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.dreadstone_cobblestone_slab.getBlock(), Material.ROCK, "pickaxe", 4).setHardness(3.3F).setResistance(20.0F).setTranslationKey("dreadstonecobblestoneslab2"), new ItemSlabAC(ACBlocks.dreadcobbleslab2.getBlock(), ACBlocks.dreadstone_cobblestone_slab.getBlock(), ACBlocks.dreadcobbleslab2.getBlock()).setUnlockCondition(dreadlands));
		ACBlocks.dreadstone_cobblestone_wall =new ACBlock( new BlockACWall(ACBlocks.dreadstone_cobblestone.getBlock(), 4).setHardness(3.3F).setResistance(20.0F).setTranslationKey("dreadstonecobblestonewall"),dreadlands);
		ACBlocks.abyssalnite_cobblestone_stairs =new ACBlock( new BlockACStairs(ACBlocks.abyssalnite_cobblestone.getBlock(), "pickaxe", 4).setTranslationKey("abyssalnitecobblestonestairs"),dreadlands);
		ACBlocks.abyssalnite_cobblestone_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, "pickaxe", 4, SoundType.STONE, MapColor.PURPLE).setHardness(3.3F).setResistance(20.0F).setTranslationKey("abyssalnitecobblestoneslab1"), new ItemSlabAC(ACBlocks.abyssalnite_cobblestone_slab.getBlock(), ACBlocks.abyssalnite_cobblestone_slab.getBlock(), ACBlocks.abydreadcobbleslab2.getBlock()).setUnlockCondition(dreadlands));
		ACBlocks.abydreadcobbleslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.abyssalnite_cobblestone_slab.getBlock(), Material.ROCK, "pickaxe", 4).setHardness(3.3F).setResistance(20.0F).setTranslationKey("abyssalnitecobblestoneslab2"), new ItemSlabAC(ACBlocks.abydreadcobbleslab2.getBlock(), ACBlocks.abyssalnite_cobblestone_slab.getBlock(), ACBlocks.abydreadcobbleslab2.getBlock()).setUnlockCondition(dreadlands));
		ACBlocks.abyssalnite_cobblestone_wall =new ACBlock( new BlockACWall(ACBlocks.abyssalnite_cobblestone.getBlock(), 4).setHardness(3.3F).setResistance(20.0F).setTranslationKey("abyssalnitecobblestonewall"),dreadlands);
		ACBlocks.coralium_cobblestone_stairs =new ACBlock( new BlockACStairs(ACBlocks.coralium_cobblestone.getBlock()).setTranslationKey("coraliumcobblestonestairs"),abyssal_wasteland);
		ACBlocks.coralium_cobblestone_slab =new ACBlock( new BlockACSingleSlab(Material.ROCK, SoundType.STONE, MapColor.CYAN).setHardness(2.0F).setResistance(10.0F).setTranslationKey("coraliumcobblestoneslab1"), new ItemSlabAC(ACBlocks.coralium_cobblestone_slab.getBlock(), ACBlocks.coralium_cobblestone_slab.getBlock(), ACBlocks.cstonecobbleslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		ACBlocks.cstonecobbleslab2 =new ACBlock( new BlockACDoubleSlab(ACBlocks.coralium_cobblestone_slab.getBlock(), Material.ROCK).setHardness(2.0F).setResistance(10.0F).setTranslationKey("coraliumcobblestoneslab2"), new ItemSlabAC(ACBlocks.cstonecobbleslab2.getBlock(), ACBlocks.coralium_cobblestone_slab.getBlock(), ACBlocks.cstonecobbleslab2.getBlock()).setUnlockCondition(abyssal_wasteland));
		ACBlocks.coralium_cobblestone_wall =new ACBlock( new BlockACWall(ACBlocks.coralium_cobblestone.getBlock()).setHardness(2.0F).setResistance(10.0F).setTranslationKey("coraliumcobblestonewall"),abyssal_wasteland);
		ACBlocks.luminous_thistle =new ACBlock( new BlockLuminousThistle(),abyssal_wasteland);
		ACBlocks.wastelands_thorn =new ACBlock( new BlockWastelandsThorn(),abyssal_wasteland);
		ACBlocks.rending_pedestal =new ACBlock( new BlockRendingPedestal(), new ItemRendingPedestalBlock(ACBlocks.rending_pedestal.getBlock()));
		ACBlocks.state_transformer =new ACBlock( new BlockStateTransformer());
		ACBlocks.energy_depositioner =new ACBlock( new BlockEnergyDepositioner());
		ACBlocks.calcified_stone =new ACBlock( new BlockCalcifiedStone());
		ACBlocks.darklands_oak_door =new ACBlock( new BlockACDoor(Material.WOOD, 3.0F, 15.0F, SoundType.WOOD, MapColor.BROWN).setTranslationKey("door_dlt"),
				(ItemBlock) null);
		ACBlocks.dreadlands_door =new ACBlock( new BlockACDoor(Material.WOOD, 3.0F, 15.0F, SoundType.WOOD, MapColor.RED).setTranslationKey("door_drt"),
				(ItemBlock) null);
		ACBlocks.multi_block =new ACBlock( new BlockMultiblock());
		ACBlocks.sequential_brewing_stand =new ACBlock( new BlockSequentialBrewingStand(), new DimensionCondition(-1));
		ACBlocks.portal_anchor =new ACBlock( new BlockPortalAnchor());

		GameRegistry.registerTileEntity(TileEntityCrate.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityCrate"));
		GameRegistry.registerTileEntity(TileEntityDGhead.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityDGhead"));
		GameRegistry.registerTileEntity(TileEntityPhead.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityPhead"));
		GameRegistry.registerTileEntity(TileEntityWhead.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityWhead"));
		GameRegistry.registerTileEntity(TileEntityOhead.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityOhead"));
		GameRegistry.registerTileEntity(TileEntityCrystallizer.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityCrystallizer"));
		GameRegistry.registerTileEntity(TileEntityTransmutator.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityTransmutator"));
		GameRegistry.registerTileEntity(TileEntityDreadguardSpawner.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityDradguardSpawner"));
		GameRegistry.registerTileEntity(TileEntityChagarothSpawner.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityChagarothSpawner"));
		GameRegistry.registerTileEntity(TileEntityEngraver.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityEngraver"));
		GameRegistry.registerTileEntity(TileEntityMaterializer.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityMaterializer"));
		GameRegistry.registerTileEntity(TileEntityRitualAltar.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityRitualAltar"));
		GameRegistry.registerTileEntity(TileEntityRitualPedestal.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityRitualPedestal"));
		GameRegistry.registerTileEntity(TileEntityStatue.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityStatue"));
		GameRegistry.registerTileEntity(TileEntityShoggothBiomass.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityShoggothBiomass"));
		GameRegistry.registerTileEntity(TileEntityEnergyPedestal.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityEnergyPedestal"));
		GameRegistry.registerTileEntity(TileEntitySacrificialAltar.class, new ResourceLocation(AbyssalCraft.modid, "tileEntitySacrificialAltar"));
		GameRegistry.registerTileEntity(TileEntityTieredEnergyPedestal.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityTieredEnergyPedestal"));
		GameRegistry.registerTileEntity(TileEntityTieredSacrificialAltar.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityTieredSacrificialAltar"));
		GameRegistry.registerTileEntity(TileEntityJzaharSpawner.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityJzaharSpawner"));
		GameRegistry.registerTileEntity(TileEntityGatekeeperMinionSpawner.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityGatekeeperMinionSpawner"));
		GameRegistry.registerTileEntity(TileEntityEnergyCollector.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityEnergyCollector"));
		GameRegistry.registerTileEntity(TileEntityEnergyRelay.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityEnergyRelay"));
		GameRegistry.registerTileEntity(TileEntityEnergyContainer.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityEnergyContainer"));
		GameRegistry.registerTileEntity(TileEntityTieredEnergyCollector.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityTieredEnergyCollector"));
		GameRegistry.registerTileEntity(TileEntityTieredEnergyRelay.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityTieredEnergyRelay"));
		GameRegistry.registerTileEntity(TileEntityTieredEnergyContainer.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityTieredEnergyContainer"));
		GameRegistry.registerTileEntity(TileEntityRendingPedestal.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityRendingPedestal"));
		GameRegistry.registerTileEntity(TileEntityStateTransformer.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityStateTransformer"));
		GameRegistry.registerTileEntity(TileEntityEnergyDepositioner.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityEnergyDepositioner"));
		GameRegistry.registerTileEntity(TileEntityMultiblock.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityMultiblock"));
		GameRegistry.registerTileEntity(TileEntitySequentialBrewingStand.class, new ResourceLocation(AbyssalCraft.modid, "tileEntitySequentialBrewingStand"));
		GameRegistry.registerTileEntity(TileEntityPortalAnchor.class, new ResourceLocation(AbyssalCraft.modid, "tileEntityPortalAnchor"));

		ACBlocks.registerBlocks();

		Blocks.FIRE.setFireInfo(ACBlocks.darklands_oak_planks.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(ACBlocks.DLTslab2.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(ACBlocks.darklands_oak_slab.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(ACBlocks.darklands_oak_fence.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(ACBlocks.darklands_oak_stairs.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(ACBlocks.darklands_oak_wood.getBlock(), 5, 5);
		Blocks.FIRE.setFireInfo(ACBlocks.darklands_oak_wood_2.getBlock(), 5, 5);
		Blocks.FIRE.setFireInfo(ACBlocks.darklands_oak_leaves.getBlock(), 30, 60);

		Blocks.FIRE.setFireInfo(ACBlocks.dreadlands_planks.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(ACBlocks.dreadlands_wood_fence.getBlock(), 5, 20);
		Blocks.FIRE.setFireInfo(ACBlocks.dreadlands_log.getBlock(), 5, 5);
		Blocks.FIRE.setFireInfo(ACBlocks.dreadlands_leaves.getBlock(), 30, 60);
	}

	@Override
	public void init(FMLInitializationEvent event) {
	}

	private void addCondition(Block block, IUnlockCondition condition){
		((IUnlockableItem) Item.getItemFromBlock(block)).setUnlockCondition(condition);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		((BlockCLiquid) ACBlocks.liquid_coralium.getBlock()).addBlocks();
	}

	@Override
	public void loadComplete(FMLLoadCompleteEvent event) {}

}
