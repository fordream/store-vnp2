package com.example.mytaken.util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.vnp.core.common.CommonAndroid;
import com.vnp.core.common.VNPResize;

public class BanDuongUtils {

	public static final class SIZE {
		public static final int HEIGHT_HEADER = 50;
		public static final int TEXTSIZEBUTTONHEADER = 30;
		public static final int WIDTHBUTTONHEADER = 65;
		public static final int HEIGHTBUTTONHEADER = 40;
		public static final int TEXTSIZEEDITTEXT = 20;
		public static final int TEXTSIZENORMAL = 20;
		public static final int HEIGHT_REGISTER_CHECKBOX = 35;
		public static final int WIDTH_REGISTER_CHECKBOX_TEXT = 200;
	};

	public static void setTypeface(TextView view) {
		CommonAndroid.FONT.setTypefaceFromAsset(view, "arial.ttf");
	}

	public static void setTypefaceBold(TextView view) {
		CommonAndroid.FONT.setTypefaceFromAsset(view, "ARIALBD.TTF");
	}

	public static void resizeButton(View view) {
		VNPResize.getInstance().resizeSacle(view, 257, 43);
	}

	public static void resizeLogo(View view) {
		VNPResize.getInstance().resizeSacle(view, 265, 185);
	}

}