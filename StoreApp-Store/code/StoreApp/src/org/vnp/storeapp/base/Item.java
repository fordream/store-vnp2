package org.vnp.storeapp.base;

import org.vnp.storeapp.BlockType;

public class Item {
	public static final int BASE = 0;
	private BlockType type;

	public Item(BlockType type) {
		this.type = type;
	}

	public BlockType getType() {
		return type;
	}

	public void setType(BlockType type) {
		this.type = type;
	}

}