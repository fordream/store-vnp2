package org.vnp.peaps;

import org.vnp.peaps.base.MBaseActivity;

import com.vnp.core.common.VNPResize.ICompleteInit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends MBaseActivity implements ICompleteInit {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		initVNPResize(this, 320, 0, this,
				(TextView) findViewById(R.id.splash_txt));
	}

	@Override
	public void complete() {
		if (!isFinishing()) {
			Intent intent = new Intent(this, MainActivity.class);
			// intent.putExtra(HelloActivity.TYPE_KEY, Type.FOREGROUND.name());
			startActivity(intent);
			finish();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
