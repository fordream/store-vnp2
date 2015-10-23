package com.viewpagerindicator.db;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import vnp.mr.mintmedical.item.Clinic;
import vnp.mr.mintmedical.item.S51Item;
import vnp.mr.mintmedical.item.S52Item;
import android.content.Context;

public class DBS51 extends BaseDB {

	public DBS51(Context context) {
		super(context);
	}

	@Override
	public Object getData() {
		List<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(getDataStr());
			for (int i = 0; i < array.length(); i++) {
				S51Item item = new S51Item(array.getJSONObject(i));
				list.add(item);
			}
		} catch (JSONException e) {
		}
		return list;
	}

	public Object getData(String doctor_id) {
		List<Object> list = new ArrayList<Object>();

		try {
			JSONArray array = new JSONArray(getDataStr());
			for (int i = 0; i < array.length(); i++) {
				S51Item item = new S51Item(array.getJSONObject(i));

				if (item.id.equals(doctor_id)) {
					JSONArray detail = array.getJSONObject(i).getJSONArray("detail");
					for(int j = 0; j < detail.length() ; j ++){
						S52Item item2 = new S52Item(detail.getJSONObject(j));
						list.add(item2);
					}
					break;
				}
			}
		} catch (JSONException e) {
		}
		return list;
	}

	@Override
	public String getKey() {
		return "DBS51";
	}
}
