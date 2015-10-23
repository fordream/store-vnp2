package org.vnp.storeapp.views;

import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
//org.vnp.storeapp.views.MTetView
public class MTetViewBold extends TextView {

	public MTetViewBold(Context context) {
		super(context);
		StoreAppUtils.setTextTypeBold(this);
	}

	public MTetViewBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		StoreAppUtils.setTextTypeBold(this);
	}

	public MTetViewBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		StoreAppUtils.setTextTypeBold(this);
	}
}