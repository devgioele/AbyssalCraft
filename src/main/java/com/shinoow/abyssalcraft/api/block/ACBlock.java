package com.shinoow.abyssalcraft.api.block;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.item.IUnlockableItem;
import com.shinoow.abyssalcraft.api.necronomicon.condition.IUnlockCondition;
import com.shinoow.abyssalcraft.common.blocks.itemblock.ItemBlockAC;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Arrays;

import static com.shinoow.abyssalcraft.api.item.ACItems.registerItem;
import static com.shinoow.abyssalcraft.api.item.ACItems.registerItemRender;

public class ACBlock {

	private final Block block;
	private final ItemBlock itemBlock;
	private final String tileName;
	private final boolean hasModel;

	public ACBlock(String tileName, Block block, ItemBlock itemBlock,
				   IUnlockCondition unlockCondition, boolean hasModel) {
		this.tileName = tileName;
		((IUnlockableItem) Item.getItemFromBlock(block)).setUnlockCondition(unlockCondition);
		this.block = block;
		this.itemBlock = itemBlock;
		this.hasModel = hasModel;
	}

	public ACBlock(String tileName, Block block, ItemBlock itemBlock,
				   IUnlockCondition unlockCondition) {
		this(tileName, block, itemBlock, unlockCondition, false);
	}

	public ACBlock(int tileVariation, Block block, ItemBlock itemBlock,
				   IUnlockCondition unlockCondition) {
		this(block.getTranslationKey() + '_' + tileVariation, block, itemBlock, unlockCondition,
				false);
	}

	public ACBlock(Block block, ItemBlock itemBlock, IUnlockCondition unlockCondition) {
		this(block.getTranslationKey(), block, itemBlock, unlockCondition, false);
	}

	public ACBlock(Block block, ItemBlock itemBlock, IUnlockCondition unlockCondition,
				   boolean hasModel) {
		this(block.getTranslationKey(), block, itemBlock, unlockCondition, hasModel);
	}

	public ACBlock(int tileVariation, Block block, ItemBlock itemBlock) {
		this(tileVariation, block, itemBlock, null);
	}

	public ACBlock(int tileVariation, Block block, IUnlockCondition unlockCondition) {
		this(tileVariation, block, new ItemBlockAC(block), unlockCondition);
	}

	public ACBlock(int tileVariation, Block block) {
		this(tileVariation, block, new ItemBlockAC(block));
	}

	public ACBlock(String tileName, Block block, ItemBlock itemBlock) {
		this(tileName, block, itemBlock, null, false);
	}

	public ACBlock(Block block, ItemBlock itemBlock) {
		this(block, itemBlock, null);
	}

	public ACBlock(Block block, ItemBlock itemBlock, boolean hasModel) {
		this(block, itemBlock, null, hasModel);
	}

	public ACBlock(Block block, IUnlockCondition unlockCondition) {
		this(block, new ItemBlockAC(block), unlockCondition);
	}

	public ACBlock(Block block, IUnlockCondition unlockCondition, boolean hasModel) {
		this(block, new ItemBlockAC(block), unlockCondition, hasModel);
	}

	public ACBlock(Block block, boolean hasModel) {
		this(block, new ItemBlockAC(block), hasModel);
	}

	public ACBlock(Block block) {
		this(block, new ItemBlockAC(block));
	}

	public Block getBlock() {
		return block;
	}

	public ItemBlock getItemBlock() {
		return itemBlock;
	}

	public boolean hasTranslationKey(String... keys) {
		return Arrays.stream(keys).anyMatch(key -> key.equals(block.getTranslationKey()));
	}

	public void register() {
		String name = block.getTranslationKey();
		// Register block
		block.setRegistryName(new ResourceLocation(AbyssalCraft.modid, name));
		// Register item if there is any
		if (itemBlock != null) {
			registerItem(itemBlock, name);
		}
		// Register item render
		registerItemRender(Item.getItemFromBlock(block), 0, tileName);
		// Register model if there is any
		if (hasModel) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
					new ModelResourceLocation(AbyssalCraft.modid + ":" + block.getTranslationKey(),
							"inventory"));
		}
	}


}
