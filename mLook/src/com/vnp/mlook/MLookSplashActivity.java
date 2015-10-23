package com.vnp.mlook;

import com.vnp.mlook.acticities.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class MLookSplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mlook_splash);

		Animation animation = new AlphaAnimation(0.5f, 1f);
		animation.setDuration(2 * 1000);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				startActivity(new Intent(MLookSplashActivity.this,
						MainActivity.class));
				finish();
			}
		});
		findViewById(R.id.progressBar1).startAnimation(animation);
	}
}
