/**
 * 
 */
package com.hoatieu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.location.Location;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.hoatieu.location.VNPCompassUtils;
import com.hoatieu.location.VNPLocationUtils;
import com.hoatieu.service.HoatieuServerManager;
import com.vnp.core.datastore.DataStore;

/**
 * @author teemo
 * 
 */
public class MyInforView extends BaseView {
	public static final String BAODONG = "BAODONG";

	public MyInforView(Context context) {
		super(context);
		init(R.layout.myinfor);
		setWillNotDraw(false);
	}

	public MyInforView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.myinfor);
		setWillNotDraw(false);
	}

	@Override
	public void init(int res) {
		super.init(res);
		findViewById(R.id.myinfor_img_baodong).setOnClickListener(listener);
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getContext().startActivity(new Intent(getContext(), PushHistotyActivity.class));
			}
		});
	}

	private View.OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			HoatieuServerManager.getIntance().mService.sendStatus();
		}
	};

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Location location = VNPLocationUtils.getInstance().lastKnownLocation;
		TextView textView = (TextView) findViewById(R.id.makerinfor_txt_toado);

		if (location != null) {
			textView.setText(Utility.round(location.getLatitude(), 6) + " : "
					+ Utility.round(location.getLongitude(), 6));
		} else {
			textView.setText("");
		}

		VNPCompassUtils compassUtils = VNPCompassUtils.getInstance();
		TextView huongdo = (TextView) findViewById(R.id.makerinfor_txt_tentau);

		TextView huongText = (TextView) findViewById(R.id.makerinfor_txt_sohieu);
		huongText.setText("");
		huongdo.setText(DataStore.getInstance().get("localMobile", ""));

		if (compassUtils.isSupported()) {
			huongText.setText(compassUtils.currentDegree + "");
		} else {
			huongText.setText("");
		}

		// huongText.setText(DataStore.getInstance().get("localMobile", ""));
		try {
			if (!DataStore.getInstance().get(BAODONG, false)) {
				findViewById(R.id.myinfor_img_baodong).setBackgroundResource(R.drawable.bao_dong);
			} else {
				findViewById(R.id.myinfor_img_baodong).setBackgroundResource(
						R.drawable.huy_bao_dong);
			}
		} catch (Exception ex) {
		}

		invalidate();
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		resize.resizeSacle(this, android.view.ViewGroup.LayoutParams.MATCH_PARENT, 100);
		resize.resizeSacle(getView(R.id.myfinfor_main_content), 300, 80);
		resize.resizeSacle(getView(R.id.myinfor_img_baodong), 80, 80);
		resize.resizeSacle(getView(R.id.myinfor_location), 200, 80);

		// resize.resizeSacle(getView(R.id.makerinfor_txt_tentau), 200, 50);
		// resize.resizeSacle(getView(R.id.makerinfor_txt_sohieu), 200, 15);
		// resize.resizeSacle(getView(R.id.makerinfor_txt_toado), 200, 15);

		resize.setTextsize(getView(R.id.makerinfor_txt_tentau), 50);
		resize.setTextsize(getView(R.id.makerinfor_txt_sohieu), 15);
		resize.setTextsize(getView(R.id.makerinfor_txt_toado), 15);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnp.core.view.CustomLinearLayoutView#setGender()
	 */
	@Override
	public void setGender() {

	}
}