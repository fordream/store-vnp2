package com.hoatieu;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hoatieu.location.VNPCompassUtils;
import com.hoatieu.location.VNPLocationUtils;
import com.hoatieu.model.FriendsShip;
import com.hoatieu.model.Storm;
import com.hoatieu.service.callback.LoadContentFromServer;
import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.datastore.DataStore;

public class MainActivity extends BaseActivity implements OnMapLongClickListener,
		OnMarkerClickListener, OnClickListener, SensorEventListener {
	// TODO
	// refresh
	// 1. show data map on map : my location, my friend, my bao
	// 2. call for load data
	// 3. when load data complete. show data on map
	private MarkerOptions myMarkerOptions;

	// 1.fixme
	private void updateMapContent(boolean isFirst) {
		if (googleMap != null) {
			/**
			 * clear for update
			 */
			googleMap.clear();

			String dataFriend = DataStore.getInstance().get(Constant.API_GETFRIENDS, null);
			String datagetStorm = DataStore.getInstance().get(Constant.API_GETSTORM, null);
			String myPhone = DataStore.getInstance().get("localMobile", "");

			/**
			 * Update my location
			 */
			Location mLocation = VNPLocationUtils.getInstance().lastKnownLocation;
			if (myMarkerOptions == null) {
				if (mLocation != null) {
					myMarkerOptions = new MarkerOptions().position(
							new LatLng(mLocation.getLatitude(), mLocation.getLongitude())).icon(
							BitmapDescriptorFactory.fromResource(R.drawable.my_location));
				}
			} else {
				if (mLocation != null) {
					myMarkerOptions.position(new LatLng(mLocation.getLatitude(), mLocation
							.getLongitude()));
				}
			}

			if (myMarkerOptions != null) {
				googleMap.addMarker(myMarkerOptions);
			}

			if (isFirst && mLocation != null) {
				googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
						new LatLng(mLocation.getLatitude(), mLocation.getLongitude()), ROOMSIZE));
			}
			try {
				JSONArray jsonArray = new JSONObject(dataFriend).getJSONArray("locations");
				for (int i = 0; i < jsonArray.length(); i++) {
					FriendsShip friendsShip = new FriendsShip(jsonArray.getJSONObject(i));
					if (friendsShip.canParseToDuble()) {
						double longitude = Double.parseDouble(friendsShip.getLongitude());
						double latitude = Double.parseDouble(friendsShip.getLatitude());
						MarkerOptions markerOptions = new MarkerOptions();
						markerOptions.position(new LatLng(latitude, longitude));
						markerOptions.title(friendsShip.getLocalName());

						String status = friendsShip.getStatus();

						Log.e("STATUS", status + "");
						if ("1".equals(friendsShip.getStatus())) {
							markerOptions.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.tau_thuong));
						} else if ("2".equals(friendsShip.getStatus())) {
							markerOptions.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.tau_cuu_ho));
						} else if ("3".equals(friendsShip.getStatus())) {
							markerOptions.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.tau_nguy_hiem));
						} else if ("0".equals(friendsShip.getStatus())) {
							markerOptions
									.icon(BitmapDescriptorFactory.fromResource(R.drawable.dao));
						} else if ("4".equals(friendsShip.getStatus())) {
							markerOptions.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.khuvucnguyhiem));
						}
						googleMap.addMarker(markerOptions);
					}
				}
			} catch (Exception exception) {

			}

			try {
				JSONArray jsonArray = new JSONObject(datagetStorm).getJSONArray("locations");
				for (int i = 0; i < jsonArray.length(); i++) {
					Storm storm = new Storm(jsonArray.getJSONObject(i));
					if (storm.canParseToDuble()) {
						double longitude = Double.parseDouble(storm.getLongitude());
						double latitude = Double.parseDouble(storm.getLatitude());
						MarkerOptions markerOptions = new MarkerOptions();
						markerOptions.position(new LatLng(latitude, longitude));
						markerOptions.title(storm.getEventName());
						markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bao));
						// if ("binhthuong".equals(storm.getStatus())) {
						// markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.tau_thuong));
						// } else if ("TauCuuHo".equals(storm.getStatus())) {
						// markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.tau_cuu_ho));
						// } else {
						// markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.tau_nguy_hiem));
						// }
						googleMap.addMarker(markerOptions);
					}
				}
			} catch (Exception exception) {

			}

		}
	}

	// 2.call for load data
	// 3. when load data complete. show data on map
	private void loadContentFromServer() {
		new ExeCallBack().executeAsynCallBack(new LoadContentFromServer(this) {
			@Override
			public void onCallBack(Object arg0) {
				super.onCallBack(arg0);
				if (!isFinishing()) {
					updateMapContent(false);
				}
			}
		});
	}

	private static float ROOMSIZE = 6;
	private MakerInforView makerInforView;
	private VNPCompassUtils compassUtils = VNPCompassUtils.getInstance();
	private GoogleMap googleMap;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/**
		 * set reisize infor
		 */
		int width = 50;
		addResizeInfor(R.id.imageView1, width, width);
		addResizeInfor(R.id.button_message, width, width);
		addResizeInfor(R.id.button_mylocation, width, width);

		// add action
		findViewById(R.id.button_mylocation).setOnClickListener(this);
		findViewById(R.id.button_message).setOnClickListener(this);

		makerInforView = (MakerInforView) findViewById(R.id.makerInforView1);
		makerInforView.setVisibility(View.GONE);

		googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		if (googleMap == null) {
			Toast.makeText(this, R.string.notsupportmap, Toast.LENGTH_SHORT).show();
		} else {
			googleMap.setOnMapLongClickListener(this);
			googleMap.setOnMarkerClickListener(this);
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(ROOMSIZE), 2000, null);
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			googleMap.getUiSettings().setCompassEnabled(true);
			googleMap.getUiSettings().setZoomGesturesEnabled(true);
		}

		compassUtils.init(this);
		compassUtils.addSensorEventListener(this);

		updateMapContent(true);
		loadContentFromServer();
	}

	@Override
	protected void onResume() {
		super.onResume();
		compassUtils.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		compassUtils.onPause();
	}

	/**
	 * Map logn click
	 */
	@Override
	public void onMapLongClick(final LatLng arg0) {
		Builder builder = new Builder(MainActivity.this);
		builder.setTitle("");
		builder.setMessage("Thong tin thoi tiet");
		builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
				intent.putExtra("lat", arg0.latitude);
				intent.putExtra("lng", arg0.longitude);
				startActivity(intent);
			}
		});

		builder.setNegativeButton("cancel", null);
		builder.show();
	}

	/**
	 * Map click maker
	 */
	@Override
	public boolean onMarkerClick(Marker marker) {
		LatLng arg0 = marker.getPosition();
		makerInforView.setVisibility(View.VISIBLE);
		makerInforView.updateMakerInforView(arg0, marker);
		return false;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button_mylocation) {
			Location location = VNPLocationUtils.getInstance().lastKnownLocation;
			if (googleMap != null && location != null) {
				googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
						new LatLng(location.getLatitude(), location.getLongitude()),
						googleMap.getCameraPosition().zoom));
			}
		} else if (R.id.button_message == v.getId()) {
			startActivity(new Intent(v.getContext(), PushHistotyActivity.class));
		}
	}

	/**
	 * sensor
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		RotateAnimation ra = new RotateAnimation(compassUtils.currentDegree, -compassUtils.degree,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(210);
		ra.setFillAfter(true);
		findViewById(R.id.imageView1).startAnimation(ra);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

}