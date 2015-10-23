package org.vnp.flyingtiger;

import android.view.View;

public class VisibleView implements Runnable {
	private View view;
	public static final long TIME = 500;

	public VisibleView(View view) {
		this.view = view;
	}

	@Override
	public void run() {
		view.setVisibility(View.VISIBLE);
	}
}