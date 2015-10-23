/**
 * 
 */
package com.hoatieu;

import android.content.Context;
import android.graphics.Canvas;
import android.location.Location;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.hoatieu.location.VNPLocationUtils;

/**
 * @author teemo
 * 
 */
public class MakerInforView extends BaseView {

	public MakerInforView(Context context) {
		super(context);
		init(R.layout.makerinfor);
		setWillNotDraw(false);
	}

	public MakerInforView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(R.layout.makerinfor);
		setWillNotDraw(false);
	}

	@Override
	public void init(int res) {
		super.init(res);

		setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setVisibility(GONE);
			}
		});
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		resize.resizeSacle(this, android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(getView(R.id.myinfor_location), 300,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		
		resize.setTextsize(getView(R.id.makerinfor_txt_tentau), 50);
		resize.setTextsize(getView(R.id.makerinfor_txt_sohieu), 15);
		resize.setTextsize(getView(R.id.makerinfor_txt_toado), 15);
		resize.setTextsize(getView(R.id.makerinfor_txt_khoangcach), 15);
		resize.setTextsize(getView(R.id.makerinfor_txt_thoitiet), 15);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vnp.core.view.CustomLinearLayoutView#setGender()
	 */
	@Override
	public void setGender() {

	}

	public void updateMakerInforView(LatLng arg0, Marker marker) {
		clear(R.id.makerinfor_txt_tentau);
		clear(R.id.makerinfor_txt_sohieu);
		clear(R.id.makerinfor_txt_toado);
		clear(R.id.makerinfor_txt_khoangcach);
		clear(R.id.makerinfor_txt_thoitiet);

		final LatLng location = marker.getPosition();
		setText(R.id.makerinfor_txt_tentau, marker.getTitle());
		setText(R.id.makerinfor_txt_sohieu, marker.getSnippet());
		setText(R.id.makerinfor_txt_toado,
				Utility.round(location.latitude, 6) + " : " + Utility.round(location.longitude, 6));
		final Location location2 = VNPLocationUtils.getInstance().lastKnownLocation;
		if (location2 != null)
			setText(R.id.makerinfor_txt_khoangcach,
					getDistance(location,
							new LatLng(location2.getLatitude(), location2.getLongitude()))
							/ 1000 + " km");

	}

	public double getDistance(LatLng LatLng1, LatLng LatLng2) {
		double distance = 0;
		Location locationA = new Location("A");
		locationA.setLatitude(LatLng1.latitude);
		locationA.setLongitude(LatLng1.longitude);
		Location locationB = new Location("B");
		locationB.setLatitude(LatLng2.latitude);
		locationB.setLongitude(LatLng2.longitude);
		distance = locationA.distanceTo(locationB);
		distance = Utility.round(distance, 2);
		return distance;

	}

	private void setText(int makerinforTxtToado, String string) {
		((TextView) findViewById(makerinforTxtToado)).setText(string);
	}

	private void clear(int makerinforTxtTentau) {
		((TextView) findViewById(makerinforTxtTentau)).setText("");
	}
}