package com.vnp.mlook.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.ict.library.view.CustomLinearLayoutView;
import com.vnp.mlook.R;

public class Menu3View extends CustomLinearLayoutView {

	public Menu3View(Context context) {
		super(context);
		init(R.layout.menu3view);
	}

	public Menu3View(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.menu3view);
	}

	@Override
	public void init(int res) {
		super.init(res);
		setVisibility(View.GONE);
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setEnabled(false);
				AlphaAnimation animation = new AlphaAnimation(1f, 0f);
				animation.setDuration(500);
				animation.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {

					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						setVisibility(View.GONE);
						setEnabled(true);
					}
				});
				startAnimation(animation);
			}
		});
	}

	@Override
	public void setGender() {

	}
}
