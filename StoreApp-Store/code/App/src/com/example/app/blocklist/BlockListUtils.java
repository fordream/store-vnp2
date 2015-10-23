package com.example.app.blocklist;

public class BlockListUtils {
	public static final int WIDTH = 300;
	public static final int HEIGHT = 450;
	public static final int BLOCK_SPACE = 4 * 3;

	public static int getWidthAllBlock() {
		return WIDTH * 3 + BLOCK_SPACE * 2;
	}

	public static int getHeightTwoBlock() {
		return HEIGHT * 2 + BLOCK_SPACE;
	}
}
