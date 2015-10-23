package org.vnp.storeapp.activity;

import org.vnp.storeapp.R;
import org.vnp.storeapp.base.MBaseActivity;
import org.vnp.storeapp.status.StatusCallBack;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.common.CommonAndroid;
import com.vnp.core.common.VNPResize;

public class SplashActivity extends MBaseActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		CommonAndroid.GPS.onpenGPS(this);

		setContentView(R.layout.splashactivity);
		resize.resizeSacle(findViewById(R.id.imageView_about), 480, 480);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
		alphaAnimation.setDuration(5000l);
		alphaAnimation.setAnimationListener(this);
		findViewById(R.id.imageView_about).startAnimation(alphaAnimation);

		TextView configSizeTextView = (TextView) findViewById(R.id._vnpConfigSizeTextView);
		StoreAppUtils.setTextType(configSizeTextView);

		resize.init(this, StoreAppUtils.BASE_WIDTH, StoreAppUtils.BASE_HEIGHT,
				new VNPResize.ICompleteInit() {
					@Override
					public void complete() {
						if (!isFinishing()) {
							callApi();
						}
					}

				}, configSizeTextView);
	}

	private void callApi() {
		final StatusCallBack statusCallBack = new StatusCallBack(this) {
			@Override
			public void onCallBack(Object arg0) {
				super.onCallBack(arg0);
				if (getStatus() == 1) {
					Intent intent = new Intent(SplashActivity.this,
							BlocksListActivity.class);
					startActivity(intent);
					finish();
				} else if (getStatus() == -1) {
					show("Please check your network status");
				} else {
					show(getMessage());
				}
			}

		};
		new ExeCallBack().executeAsynCallBack(statusCallBack);
		// Intent intent = new Intent(this, BlocksListActivity.class);
		// startActivity(intent);
		// finish();
	}

	private void show(String string) {
		Builder builder = new Builder(this);
		builder.setMessage(string);
		builder.setCancelable(false);
		builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		builder.show();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	@Override
	public void onAnimationEnd(Animation animation) {
	}
}