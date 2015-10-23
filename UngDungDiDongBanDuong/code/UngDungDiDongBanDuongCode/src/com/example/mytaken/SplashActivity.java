package com.example.mytaken;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mytaken.util.BanDuongUtils;
import com.vnp.core.activity.BaseActivity;
import com.vnp.core.common.VNPResize;
import com.vnp.core.common.VNPResize.ICompleteInit;

public class SplashActivity extends BaseActivity implements ICompleteInit {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		BanDuongUtils.setTypeface((TextView) findViewById(R.id.textView1));
		VNPResize.getInstance().init(this, 320, 480, this,
				(TextView) findViewById(R.id.textView1));
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	@Override
	public void complete() {
		if (!isFinishing()) {
			finish();

			startActivity(new Intent(this, MainActivity.class));
		}
	}
}