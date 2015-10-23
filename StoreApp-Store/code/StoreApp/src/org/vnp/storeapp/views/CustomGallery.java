package org.vnp.storeapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Gallery;
import android.widget.LinearLayout;

public class CustomGallery extends Gallery {

	public CustomGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

//	public void setMargin(Context ctx, int width) {
//		LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//		localLayoutParams.leftMargin = (width - tileWidth - 2 * gapBetweenTiles)
//				* -1;
//		setLayoutParams(localLayoutParams);
//
//	}
}