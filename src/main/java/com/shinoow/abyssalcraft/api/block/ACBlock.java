package com.shinoow.abyssalcraft.api.block;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.item.IUnlockableItem;
import com.shinoow.abyssalcraft.api.necronomicon.condition.IUnlockCondition;
import com.shinoow.abyssalcraft.common.blocks.itemblock.ItemBlockAC;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;

import static com.shinoow.abyssalcraft.api.item.ACItems.registerItem;
import static com.shinoow.abyssalcraft.api.item.ACItems.registerItemRender;

public class ACBlock {

	private final Block block;
	private final ItemBlock itemBlock;
	private final String tileName;

	public ACBlock(String tileName, Block block, ItemBlock itemBlock,
				   IUnlockCondition unlockCondition) {
		this.tileName = tileName;
		((IUnlockableItem) Item.getItemFromBlock(block)).setUnlockCondition(unlockCondition);
		this.block = block;
		this.itemBlock = itemBlock;
	}

	public ACBlock(int tileVariation, Block block, ItemBlock itemBlock,
				   IUnlockCondition unlockCondition) {
		this(block.getTranslationKey() + '_' + tileVariation, block, itemBlock, unlockCondition);
	}

	public ACBlock(Block block, ItemBlock itemBlock, IUnlockCondition unlockCondition) {
		this(block.getTranslationKey(), block, itemBlock, unlockCondition);
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
		this(tileName, block, itemBlock, null);
	}

	public ACBlock(String tileName, Block block, IUnlockCondition unlockCondition) {
		this(tileName, block, new ItemBlockAC(block), unlockCondition);
	}

	public ACBlock(String tileName, Block block) {
		this(tileName, block, new ItemBlockAC(block));
	}

	public ACBlock(Block block, ItemBlock itemBlock) {
		this(block, itemBlock, null);
	}

	public ACBlock(Block block, IUnlockCondition unlockCondition) {
		this(block, new ItemBlockAC(block), unlockCondition);
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
		block.setRegistryName(new ResourceLocation(AbyssalCraft.modid, name));
		if (itemBlock != null) {
			registerItem(itemBlock, name);
		}
		registerItemRender(Item.getItemFromBlock(block), 0, tileName);
	}


}
