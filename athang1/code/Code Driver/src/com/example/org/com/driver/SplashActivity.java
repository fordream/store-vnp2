package com.example.org.com.driver;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.vnp.core.common.VNPResize;
import com.vnp.core.common.VNPResize.ICompleteInit;

public class SplashActivity extends MBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		VNPResize.getInstance().init(this, 320, 480, new ICompleteInit() {

			@Override
			public void complete() {
				if (!isFinishing()) {
					startActivity(new Intent(SplashActivity.this,
							LoginScreen.class));
					finish();
				}
			}
		}, (TextView) getView(R.id.baseTextView1));
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	@Override
	public void run() {

	}
}