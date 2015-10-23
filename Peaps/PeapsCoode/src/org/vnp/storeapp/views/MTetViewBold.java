package org.vnp.storeapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.vnp.core.common.CommonAndroid;

//org.vnp.storeapp.views.MTetView
public class MTetViewBold extends TextView {

	public MTetViewBold(Context context) {
		super(context);
		CommonAndroid.FONT.setTypefaceFromAsset(this, "ARIALBD.TTF");
	}

	public MTetViewBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		CommonAndroid.FONT.setTypefaceFromAsset(this, "ARIALBD.TTF");
	}

	public MTetViewBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		CommonAndroid.FONT.setTypefaceFromAsset(this, "ARIALBD.TTF");
	}
}