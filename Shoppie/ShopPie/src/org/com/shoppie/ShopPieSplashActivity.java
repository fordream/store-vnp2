package org.com.shoppie;

import com.ict.library.service.LocationService;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class ShopPieSplashActivity extends MBaseActivity {
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_pie_splash);

		imageView = getView(R.id.imageView1);
		
		startService(new Intent(this,LocationService.class));
	}

	@Override
	protected void onResume() {
		super.onResume();

		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(2000);
		alphaAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if (!isFinishing()) {
					finish();
					startActivity(new Intent(ShopPieSplashActivity.this,
							MainActivity.class));
				}
			}
		});

		imageView.startAnimation(alphaAnimation);
	}

	@Override
	protected Object _doInBackground() {
		return null;
	}

	@Override
	protected void _onPostExecute(Object data) {

	}

}
