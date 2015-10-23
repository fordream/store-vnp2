package org.vnp.storeapp.views;

import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
//org.vnp.storeapp.views.MTetView
public class MTetView extends TextView {

	public MTetView(Context context) {
		super(context);
		StoreAppUtils.setTextType(this);
	}

	public MTetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		StoreAppUtils.setTextType(this);
	}

	public MTetView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		StoreAppUtils.setTextType(this);
	}
}