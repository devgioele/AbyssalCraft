package com.shinoow.abyssalcraft.api.block;

import com.shinoow.abyssalcraft.api.item.IUnlockableItem;
import com.shinoow.abyssalcraft.api.necronomicon.condition.IUnlockCondition;
import com.shinoow.abyssalcraft.common.blocks.itemblock.ItemBlockAC;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.Arrays;

public class ACBlock {

	private final Block block;
	private final ItemBlock itemBlock;

	public ACBlock(Block block, ItemBlock itemBlock, IUnlockCondition unlockCondition) {
		((IUnlockableItem) Item.getItemFromBlock(block)).setUnlockCondition(unlockCondition);
		this.block = block;
		this.itemBlock = itemBlock;
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

}
