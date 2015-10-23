package com.vnp.mlook.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MLookViewPager extends ViewPager {
	private boolean EnalbleScroll;

	public MLookViewPager(Context context) {
		super(context);
	}

	public MLookViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setEnalbleScroll(boolean isEnable) {
		EnalbleScroll = isEnable;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (!EnalbleScroll) {
			return true;
		}
		
		return super.onTouchEvent(arg0);
	}
}
