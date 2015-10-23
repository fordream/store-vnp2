package com.hoatieu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.google.android.gcm.GCMRegistrar;
import com.hoatieu.location.VNPLocationUtils;
import com.hoatieu.service.HoatieuServerManager;

public class SplashActivity extends BaseActivity implements AnimationListener {

	/**
	 * tvuong1
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		VNPLocationUtils.getInstance().requestLocationUpdate();

		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);

		final String regId = GCMRegistrar.getRegistrationId(this);
		if (regId.equals(""))
			GCMRegistrar.register(this, Constant.SENDER_ID);

		Log.e("ABC", regId);

		AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
		alphaAnimation.setDuration(3000);
		alphaAnimation.setAnimationListener(this);

		findViewById(R.id.login_load).startAnimation(alphaAnimation);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		findViewById(R.id.login_load).setVisibility(View.VISIBLE);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (!HoatieuServerManager.getIntance().mService.isLogined()) {
			Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		} else {
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}
}