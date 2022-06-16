package com.shinoow.abyssalcraft.common.items;

import com.shinoow.abyssalcraft.common.blocks.BlockACDoor;
import net.minecraft.block.Block;
import net.minecraft.item.ItemDoor;

public class ItemACDoor extends ItemDoor {

	private final BlockACDoor block;

	public ItemACDoor(Block block) {
		super(block);
		this.block = (BlockACDoor) block;
	}

	public void selfReferenceToBlock() {
		block.setItem(this);
	}

}
