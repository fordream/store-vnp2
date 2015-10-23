/**
 * 
 */
package org.core.store.app;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author tvuong1pc
 * 
 */
public class StoreViewPagerNoScroll extends ViewPager {

	/**
	 * @param context
	 */
	public StoreViewPagerNoScroll(Context context) {
		super(context);
		setEnabled(false);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public StoreViewPagerNoScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
		setEnabled(false);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return true;
	}
}