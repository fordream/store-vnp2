package com.example.org.com.driver.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.example.org.com.driver.R;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class ProCallView extends CustomLinearLayoutView implements Runnable {

	public ProCallView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.procallview);
		findViewById(R.id.wallBackground).setBackgroundResource(
				R.drawable.wall4);
		findViewById(R.id.wallBackground_1).setBackgroundResource(
				R.drawable.wall4_1);
		post(this);
	}

	public static final int CONNECTING = 0;
	public static final int CONNECTED = 1;
	public int res = R.drawable.wall4_1;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	int myStatus = CONNECTING;
	int round = -10;

	public void setStatus(int status, int number) {
		myStatus = status;

		if (status == CONNECTING) {
			findViewById(R.id.wallBackground).setBackgroundResource(
					R.drawable.wall4);
			findViewById(R.id.wallBackground_1).setBackgroundResource(
					R.drawable.wall4_1);
			res = R.drawable.wall4_1;
		} else if (status == CONNECTED) {
			findViewById(R.id.wallBackground).setBackgroundResource(
					R.drawable.wall2);
			findViewById(R.id.wallBackground_1).setBackgroundResource(
					R.drawable.wall2_1);
			res = R.drawable.wall2_1;
		}
	}

	float currentDegree = 0;

	private void update(final long duration, final float degree) {
		post(new Runnable() {
			@Override
			public void run() {
				Animation animation = new RotateAnimation(currentDegree,
						-degree, findViewById(R.id.m1animation).getWidth() / 2,
						findViewById(R.id.m1animation).getHeight());
				animation.setDuration(duration);
				animation.setFillAfter(true);
				findViewById(R.id.m1animation).startAnimation(animation);
				currentDegree = -degree;
			}
		});
	}

	@Override
	public void setGender() {

	}

	@Override
	public void showHeader(boolean arg0) {

	}

	@Override
	public void run() {
		VNPResize.getInstance().resizeSacle(findViewById(R.id.wallBackground),
				210, 155);
		VNPResize.getInstance().resizeSacle(findViewById(R.id.m11_1), 210, 160);
		VNPResize.getInstance().resizeSacle(findViewById(R.id.l1110),
				android.view.ViewGroup.LayoutParams.MATCH_PARENT, 40);
		VNPResize.getInstance().resizeSacle(
				findViewById(R.id.wallBackground_1), 18, 74);

		update(0, 90);
	}

	public void setRotate(int nextInt) {
		if (myStatus == CONNECTED) {
			update(1000l, nextInt);
		}
	}
}
