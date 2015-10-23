package com.vnp.mlook.views;

import android.content.Context;
import android.util.AttributeSet;

import com.ict.library.view.CustomLinearLayoutView;
import com.vnp.mlook.R;

public class FooterView extends CustomLinearLayoutView {
	public FooterView(Context context) {
		super(context);
		init(R.layout.footer);
	}

	public FooterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.footer);
	}

	@Override
	public void init(int res) {
		super.init(res);
	}

	@Override
	public void setGender() {

	}

}
