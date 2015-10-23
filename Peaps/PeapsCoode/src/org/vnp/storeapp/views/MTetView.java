package org.vnp.storeapp.views;

import com.vnp.core.common.CommonAndroid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
//org.vnp.storeapp.views.MTetView
public class MTetView extends TextView {

	public MTetView(Context context) {
		super(context);
		CommonAndroid.FONT.setTypefaceFromAsset(this, "arial.ttf");
	}

	public MTetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		CommonAndroid.FONT.setTypefaceFromAsset(this, "arial.ttf");
	}

	public MTetView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		CommonAndroid.FONT.setTypefaceFromAsset(this, "arial.ttf");
	}
}