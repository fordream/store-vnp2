package org.vnp.flyingtiger.activity;

import jp.flyingtiger.arcamera.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.vnp.core.base.activity.BaseActivity;

public class NewSplashActitiy extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.a_bot_to_top, R.anim.a_nothing);
		setContentView(R.layout.nspplash);

		Animation animation = AnimationUtils.loadAnimation(this, R.anim.plash);
		animation.setAnimationListener(animationListener);
		findViewById(R.id.msplash_main).setAnimation(animation);
	}

	private AnimationListener animationListener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {

		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			if (!isFinishing()) {
				startActivity(new Intent(NewSplashActitiy.this,
						SplashActitiy.class));
				finish();
			}
		}
	};

	@Override
	protected void onPause() {
		super.onPause();
		if (!isFinishing())
			finish();
	}
}