/**
 * 
 */
package org.vnp.storeapp.base;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * @author tvuong1pc
 * 
 */
public class MViewPagerNoScroll extends ViewPager {

	/**
	 * @param context
	 */
	public MViewPagerNoScroll(Context context) {
		super(context);
		//setEnabled(false);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public MViewPagerNoScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
		//setEnabled(false);
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent arg0) {
//		return true;
//	}
}