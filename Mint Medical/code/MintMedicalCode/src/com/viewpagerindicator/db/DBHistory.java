package com.viewpagerindicator.db;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vnp.mr.mintmedical.item.S4ItemForShow;
import android.content.Context;
import android.util.Log;

public class DBHistory extends BaseDB {

	public DBHistory(Context context) {
		super(context);
	}

	@Override
	public Object getData() {
		List<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(getDataStr());
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				String time = jsonObject.getString("time");
				JSONArray jsonArray = jsonObject.getJSONArray("array");
				
				for (int j = 0; j < jsonArray.length(); j++) {
					S4ItemForShow s4ItemForShow = new S4ItemForShow(time,jsonArray.getJSONObject(j));
					list.add(s4ItemForShow);
				}
			}
		} catch (JSONException e) {
		}

		

		return list;
	}

	public Object getData(String doctor_id) {
		// List<Object> list = (List<Object> )getData();
		// for(Object doctor : list){
		// if(doctor_id.equals(((Clinic)doctor).id)){
		// return doctor;
		// }
		// }
		return null;
	}

	@Override
	public String getKey() {
		return "DBHistory";
	}

}
