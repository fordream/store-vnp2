package com.example.org.com.driver;

import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.example.org.com.driver.view.ProCallView;
import com.example.org.com.driver.view.ProDB;

public class WalkthroughActivity extends MBaseActivity implements
		OnClickListener {
	private ProCallView proCallView;
	Handler mHandler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			if (!isFinishing()) {
				mHandler.sendEmptyMessageDelayed(0, 1000);
				if(proCallView!= null){
					Random random = new Random();
					proCallView.setRotate((random.nextBoolean() ? 1 : -1 )*random.nextInt(90));
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wall);

		getView(R.id.wallm2_tv_img2).setOnClickListener(this);
		handler.post(this);
		proCallView = getView(R.id.proCallView1);
		((TextView) getView(R.id.baseTextView1)).setText(R.string.w1);
		proCallView.setStatus(ProCallView.CONNECTING, 0);
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				proCallView.setStatus(ProCallView.CONNECTED, 0);
				handler.postDelayed(runnable, 1000l);
				((TextView) findViewById(R.id.baseTextView1))
						.setTextColor(Color
								.parseColor(getString(R.string.w2_color)));
				((TextView) getView(R.id.baseTextView1)).setText(R.string.w2);
			}
		}, 3000l);

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// update status
				if (!isFinishing()) {
					findViewById(R.id.wall_m1).setVisibility(View.GONE);
					findViewById(R.id.wall_m2).setVisibility(View.VISIBLE);
				}
			}
		}, 8000l);

		ProDB proDB = new ProDB(this);

		if (proDB.isEndWall()) {
			startActivity(new Intent(this, MainActivity.class));
			finish();
		}

		mHandler.sendEmptyMessageDelayed(0, 1000);
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if (!isFinishing()) {
				proCallView.setStatus(ProCallView.CONNECTED,0);
				handler.postDelayed(runnable, 1000l);
			}
		}
	};

	@Override
	public void run() {
		float scale = 49f / 31f;
		resize(R.id.login_id_1, LayoutParams.MATCH_PARENT, (int) (50 * scale));
		resize(R.id.imageView1, (int) (128 * scale), (int) (31 * scale));

		// 210 160
		resize(R.id.proCallView1, 210, 160);
		resize(R.id.baseTextView1, 270, LayoutParams.WRAP_CONTENT);
		setTextSize(R.id.baseTextView1, 28);

		setTextSize(R.id.wallm2_tv_1, 28);
		setTextSize(R.id.wallm2_tv_2, 28);
		setTextSize(R.id.wallm2_tv_3, 20);
		setTextSize(R.id.wallm2_tv_4, 28);
		resize(R.id.wallm2_tv_img1, 180, 180);
		resize(R.id.wallm2_tv_img2, 60, 60);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.wallm2_tv_img2) {
			ProDB proDB = new ProDB(this);
			proDB.saveEndWall(true);
			startActivity(new Intent(this, MainActivity.class));
			finish();
		}
	}
}