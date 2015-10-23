package com.xing.joy.play.util;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class CommonResize {
	public static void resizeLandW480H320(View view, int width, int height) {
		final float SCREEN_HIGHT = 320;
		final float SCREEN_WIDTH = 480;
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		Context context = view.getContext();
		Display display = ((Activity) context).getWindowManager()
				.getDefaultDisplay();
		float res_width = display.getWidth();
		float res_height = display.getHeight();

		float scale = res_height / SCREEN_HIGHT;

		if (SCREEN_HIGHT / res_height < SCREEN_WIDTH / res_width) {
			scale = res_width / SCREEN_WIDTH;
		}

		layoutParams.width = (int) (width * scale);
		layoutParams.height = (int) (height * scale);

		view.setLayoutParams(layoutParams);
	}

	public static void sendViewToPositionLandW480H320(View view, int left,
			int top) {
		final float SCREEN_HIGHT = 320;
		final float SCREEN_WIDTH = 480;
		Context context = view.getContext();
		Display display = ((Activity) context).getWindowManager()
				.getDefaultDisplay();
		float res_width = display.getWidth();
		float res_height = display.getHeight();

		float scale = res_height / SCREEN_HIGHT;
		if (SCREEN_HIGHT / res_height < SCREEN_WIDTH / res_width) {
			scale = res_width / SCREEN_WIDTH;
		}

		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

		int _left = (int) (left * scale);
		int _top = (int) (top * scale);

		if (layoutParams instanceof RelativeLayout.LayoutParams) {
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					layoutParams.width, layoutParams.height);
			lp.setMargins(_left, _top, 0, 0);
			view.setLayoutParams(lp);
		} else if (layoutParams instanceof LinearLayout.LayoutParams) {
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					layoutParams.width, layoutParams.height);
			lp.setMargins(_left, _top, 0, 0);
			view.setLayoutParams(lp);
		} else if (layoutParams instanceof FrameLayout.LayoutParams) {
			FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
					layoutParams.width, layoutParams.height);
			lp.setMargins(_left, _top, 0, 0);
			view.setLayoutParams(lp);
		} else if (layoutParams instanceof TableRow.LayoutParams) {
			TableRow.LayoutParams lp = new TableRow.LayoutParams(
					layoutParams.width, layoutParams.height);
			lp.setMargins(_left, _top, 0, 0);
			view.setLayoutParams(lp);
		} else if (layoutParams instanceof TableLayout.LayoutParams) {
			TableLayout.LayoutParams lp = new TableLayout.LayoutParams(
					layoutParams.width, layoutParams.height);
			lp.setMargins(_left, _top, 0, 0);
			view.setLayoutParams(lp);
		}
	}

	public static void resizeWidth960(View view, int width, int height) {
		final float SCREEN_WIDTH = 960;
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		Context context = view.getContext();
		Display display = ((Activity) context).getWindowManager()
				.getDefaultDisplay();
		float res_width = display.getWidth();

		float scale = res_width / SCREEN_WIDTH;

		layoutParams.width = (int) (width * scale);
		layoutParams.height = (int) (height * scale);

		view.setLayoutParams(layoutParams);
	}

	public static void resizeHeight960(View view, int width, int height) {
		final float SCREEN_HEGIT = 960;
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		Context context = view.getContext();
		Display display = ((Activity) context).getWindowManager()
				.getDefaultDisplay();
		float res_height = display.getHeight();

		float scale = res_height / SCREEN_HEGIT;

		layoutParams.width = (int) (width * scale);
		layoutParams.height = (int) (height * scale);

		view.setLayoutParams(layoutParams);
	}

	public static int getSizeByScreen960(Context context, int sizeFirst) {
		final float SCREEN_WIDTH = 960;
		Display display = ((Activity) context).getWindowManager()
				.getDefaultDisplay();
		float scale = (float) display.getWidth() / (float) SCREEN_WIDTH;
		return (int) (sizeFirst * scale);
	}

}
