package com.vnp.mlook.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.ict.library.view.CustomLinearLayoutView;
import com.vnp.mlook.R;
import com.vnp.mlook.utils.DBAdapter;

public class MenuPersionView extends CustomLinearLayoutView {

	public MenuPersionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.persion_menu_infor);
	}

	public MenuPersionView(Context context) {
		super(context);
		init(R.layout.persion_menu_infor);
	}

	public void update() {

	}

	@Override
	public void init(int res) {
		super.init(res);
		setWillNotDraw(false);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (DBAdapter.getInStance().isLogin()) {
			if (getVisibility() == GONE) {
				setVisibility(VISIBLE);
			}
		} else if (getVisibility() == VISIBLE) {
			setVisibility(GONE);
		}
		invalidate();
	}

	@Override
	public void setGender() {

	}

}
