package com.hoatieu;

import java.util.List;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.hoatieu.service.callback.WeaterCallback;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.common.BaseAdapter;
import com.vnp.core.common.BaseAdapter.CommonGenderView;
import com.vnp.core.view.CustomLinearLayoutView;

public class WeatherActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pushhistoryactivity);
		LatLng latLng = new LatLng(getIntent().getDoubleExtra("lat", 0d), getIntent()
				.getDoubleExtra("lng", 0d));
		WeaterCallback callback = new WeaterCallback(latLng) {
			@Override
			public void onCallBack(boolean b, String cityName, List<Object> weathers) {
				super.onCallBack(b, cityName, weathers);
				if (!isFinishing())
					if (b) {
						updateUi(cityName, weathers);
					} else {
						Builder builder = new Builder(WeatherActivity.this);
						builder.setCancelable(false);
						builder.setMessage("Can't load");
						builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								finish();
							}
						});
						builder.show();
					}
			}
		};
		((TextView) findViewById(R.id.textView1)).setText(R.string.thoitiet);
		new ExeCallBack().executeAsynCallBack(callback);
	}

	protected void onStop() {
		super.onStop();
	}

	private void updateUi(String cityName, List<Object> weathers) {
		((TextView) findViewById(R.id.textView1)).setText(cityName);

		((ListView) findViewById(R.id.listView1)).setAdapter(new BaseAdapter(this, weathers,
				new CommonGenderView() {
					@Override
					public CustomLinearLayoutView getView(Context arg0, Object arg1) {
						return new WeatherViewItem(arg0);
					}
				}));
	}

	@Override
	protected void onStart() {
		super.onStart();
		resize.resize(findViewById(R.id.LinearLayout1), resize.getWidthScreen() * 80 / 100,
				resize.getHeightScreen() * 80 / 100);
		resize.resizeSacle(findViewById(R.id.textView1), LayoutParams.MATCH_PARENT, 40);
		resize.setTextsize(findViewById(R.id.textView1), 40);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	class WeatherViewItem extends CustomLinearLayoutView {

		public WeatherViewItem(Context context) {
			super(context);
			init(R.layout.pushhistoryitem);
		}

		@Override
		protected void onAttachedToWindow() {
			super.onAttachedToWindow();
			// resize.resizeSacle(this,
			// android.view.ViewGroup.LayoutParams.MATCH_PARENT, 80);
			resize.resizeSacle(this, android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			resize.resizeSacle(findViewById(R.id.imageView1), 35, 35);
			resize.setTextsize(findViewById(R.id.textView1), 35);
			resize.setTextsize(findViewById(R.id.textView2), 20);
		}

		@Override
		public void setGender() {
			Weather weather = (Weather) getData();
			// 273,15
			((TextView) findViewById(R.id.textView1)).setText(String.format("do c : %s (%s - %s)",
					getC(weather.tem), getC(weather.temp_min), getC(weather.temp_max)));
			((TextView) findViewById(R.id.textView2)).setText(String.format(" comment : %s (%s)",
					weather.main, weather.description));
			findViewById(R.id.imageView1).setBackgroundResource(R.drawable.rain);
		}

		private String getC(String tem) {
			try {
				return String.valueOf(Math.round(Double.parseDouble(tem) - 273.15d));
			} catch (Exception exception) {
				return tem;
			}
		}
	}

}