package org.com.shoppie.util;

import android.app.Activity;
import android.content.Context;
import android.view.Display;

public class CommonSIZE {
	public static final int SIZE_WIDTH = 320;
	public static final int SIZE_WIDTH_HEIGHT = 480;

	public static final int TOP = 60;

	public static int getSize(Context context, int height) {
		Display display = ((Activity) context).getWindowManager()
				.getDefaultDisplay();

		return height * display.getWidth() / SIZE_WIDTH;
	}

}