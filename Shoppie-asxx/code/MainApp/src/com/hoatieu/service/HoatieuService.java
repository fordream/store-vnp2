package com.hoatieu.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gcm.GCMRegistrar;
import com.hoatieu.Constant;
import com.hoatieu.MyInforView;
import com.hoatieu.Utility;
import com.hoatieu.location.VNPLocationUtils;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.vnp.core.datastore.DataStore;
import com.vnp.core.service.RestClient;
import com.vnp.core.service.RestClient.RequestMethod;

public class HoatieuService extends Service {

	private Map<String, Boolean> mUrl = new HashMap<String, Boolean>();

	@Override
	public void onCreate() {
		super.onCreate();
		mUrl.clear();
	}

	private final IBinder mBinder = new HoatieuBinder();

	/**
	 * Class used for the client Binder. Because we know this service always
	 * runs in the same process as its clients, we don't need to deal with IPC.
	 */
	public class HoatieuBinder extends Binder {
		HoatieuService getService() {
			return HoatieuService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	public void login(final String localName, final String localMobile, final String localID) {

		JsonHttpResponseHandler callBack = new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				Log.e("AAAAA1", "sucree");
				DataStore.getInstance().save("localMobile", localMobile);
				DataStore.getInstance().save("localName", localName);
				DataStore.getInstance().save("localID", localID);
				Intent intent = new Intent("LOGIN");
				sendBroadcast(intent);
			}

			@Override
			public void onFailure(Throwable error) {
				Log.e("AAAAA1", "da");
				Intent intent = new Intent("LOGIN");
				intent.putExtra("message", error.getMessage());
				sendBroadcast(intent);
			}
		};
		Map<String, String> mParams = new HashMap<String, String>();
		mParams.put("localMobile", localMobile);
		mParams.put("localName", localName);
		mParams.put("localID", localID);
		mParams.put("deviceType", "Android");
		mParams.put("deviceToken", GCMRegistrar.getRegistrationId(this));

		Location location = VNPLocationUtils.getInstance().lastKnownLocation;
		if (location != null) {
			mParams.put("latitude", location.getLatitude() + "");
			mParams.put("longitude", location.getLongitude() + "");
		}

		execute(mParams, Constant.API_REGIS_URL, callBack);

	}

	private void execute(Map<String, String> mParams, String url, JsonHttpResponseHandler callBack) {
		JSONObject jsonParams = new JSONObject();
		try {
			Set<String> keys = mParams.keySet();
			for (String key : keys) {
				jsonParams.put(key, mParams.get(key));
			}
		} catch (Exception exception) {

		}
		new LoginService(this).sendData(url, jsonParams, callBack);
	}

	public boolean isLogined() {
		return !Utility.isblankOrNull(DataStore.getInstance().get("localMobile", ""))
				&& !Utility.isblankOrNull(DataStore.getInstance().get("localName", ""))
				&& !Utility.isblankOrNull(DataStore.getInstance().get("localID", ""));
	}

	public void sendStatus() {
		final String BAODONG = MyInforView.BAODONG;
		String myStatus = "0";
		if (DataStore.getInstance().get(BAODONG, false)) {
			myStatus = "1";
		}

		String phoneNumber = DataStore.getInstance().get("localMobile", "");

		Map<String, String> mParams = new HashMap<String, String>();
		mParams.put("mobile", phoneNumber);
		mParams.put("status", myStatus);

		Location location = VNPLocationUtils.getInstance().lastKnownLocation;

		if (location != null) {
			mParams.put("latitude", location.getLatitude() + "");
			mParams.put("longitude", location.getLongitude() + "");
		}

		execute(mParams, Constant.API_HELP, new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject response) {
				Log.e("AAAAA", (response == null) + "");
				boolean status = DataStore.getInstance().get(BAODONG, false);
				DataStore.getInstance().save(BAODONG, !status);
			}

			public void onFailure(Throwable error) {
				Log.e("AAAAA", "Error");
			}
		});
	}

	public void getPriends() {
		try {
			LoginService service = new LoginService(this);
			JsonHttpResponseHandler callBack = new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(JSONObject response) {
					try {
						JSONArray arrayObject = response.getJSONArray("locations");
						// successgetInfo(arrayObject);
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}

				@Override
				public void onFailure(Throwable error) {

					// errorgetShip();
				}
			};
			JSONObject jsonParams = new JSONObject();
			service.getData(Constant.API_GETFRIENDS, jsonParams, callBack);
		} catch (Exception e) {
			// errorgetShip();
		}
	}

	public void loadWeather(double lat, double log) {

		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// RestClient client = new RestClient(
		// "http://api.openweathermap.org/data/2.5/find?lat=20&lon=105&APPID=8dae1d74724aedf66e43d9cac2de2538");
		// try {
		// client.execute(RequestMethod.GET);
		// } catch (Exception e) {
		// }
		//
		// }
		// }).start();

	}
}