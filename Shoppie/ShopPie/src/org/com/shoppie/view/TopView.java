/**
 * 
 */
package org.com.shoppie.view;

import org.com.shoppie.R;
import org.com.shoppie.util.CommonSIZE;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * @author tvuong1pc
 * 
 */
public class TopView extends RelativeLayout {

	/**
	 * @param context
	 */
	public TopView(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public TopView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public TopView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		setBackgroundResource(R.drawable.top);
	}
}
