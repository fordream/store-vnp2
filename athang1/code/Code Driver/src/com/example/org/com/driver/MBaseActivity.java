package com.example.org.com.driver;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.vnp.core.activity.BaseActivity;
import com.vnp.core.common.LogUtils;
import com.vnp.core.common.VNPResize;

public abstract class MBaseActivity extends BaseActivity implements Runnable {

	public Handler handler = new Handler();

	public void resize(int res, int width, int height) {
		VNPResize.getInstance().resizeSacle(findViewById(res), width, height);
	}

	public void setTextSize(int res, int heightTextView) {
		VNPResize.getInstance().setTextsize(findViewById(res), heightTextView);
		LogUtils.e("ABCD", heightTextView + " : " + (findViewById(res) == null)
				+ " " + VNPResize.getInstance().getTextSize());
	}

	public void setTextSize(TextView boldTextView, int mainM2Text2) {
		VNPResize.getInstance().setTextsize(boldTextView, mainM2Text2);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		VNPResize.getInstance().init(this, 320, 480, null, null);
	}
}