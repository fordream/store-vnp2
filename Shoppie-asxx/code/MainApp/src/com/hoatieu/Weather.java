package com.hoatieu;

import org.json.JSONObject;

import android.util.Log;

public class Weather {
	public Weather(JSONObject jsonObject) {
		try {
			dt = jsonObject.getString("dt");
			tem = jsonObject.getJSONObject("main").getString("temp");
			temp_min = jsonObject.getJSONObject("main").getString("temp_min");
			temp_max = jsonObject.getJSONObject("main").getString("temp_max");
			main = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
			description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
		} catch (Exception exception) {
			Log.e("ABCA","d",exception);

		}
	}

	String dt;
	String tem;
	String temp_min;
	String temp_max;
	String main;
	String description;
}
