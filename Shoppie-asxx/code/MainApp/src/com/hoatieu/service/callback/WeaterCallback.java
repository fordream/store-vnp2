package com.hoatieu.service.callback;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.hoatieu.Constant;
import com.hoatieu.Weather;
import com.vnp.core.callback.CallBack;
import com.vnp.core.service.RestClient;
import com.vnp.core.service.RestClient.RequestMethod;

public class WeaterCallback extends CallBack {
	LatLng latLng;

	public WeaterCallback(LatLng latLng) {
		this.latLng = latLng;
	}

	@Override
	public Object execute() {
		String url = String.format(Constant.API_WETHER_URL, Constant.APPID_WEATHER,
				latLng.latitude, latLng.longitude);

		RestClient restClient = new RestClient(url);
		try {
			restClient.execute(RequestMethod.GET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return restClient;
	}

	@Override
	public void onCallBack(Object arg0) {
		RestClient restClient = (RestClient) arg0;
		String cityName = null;
		List<Object> weathers = new ArrayList<Object>();
		if (restClient.getResponseCode() == 200) {
			try {
				JSONObject jsonObject = new JSONObject(restClient.getResponse());
				cityName = jsonObject.getJSONObject("city").getString("name");
				JSONArray array = jsonObject.getJSONArray("list");

				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					Weather weather = new Weather(object);
					weathers.add(weather);
				}
			} catch (Exception exception) {
			}
		}
		onCallBack(restClient.getResponseCode() == 200, cityName, weathers);
	}

	public void onCallBack(boolean b, String cityName, List<Object> weathers) {

	}
}
