package com.viewpagerindicator.db;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import vnp.mr.mintmedical.item.S11;
import android.content.Context;

public class DBS11 extends BaseDB {

	public DBS11(Context context) {
		super(context);
	}

	@Override
	public Object getData() {
		List<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(getDataStr());
			for (int i = 0; i < array.length(); i++) {
				S11 item = new S11(array.getJSONObject(i));
				list.add(item);
			}
		} catch (JSONException e) {
		}

		return list;
	}

	public Object getData(String doctor_id) {
		return null;
	}

	@Override
	public String getKey() {
		return "DBS11";
	}

}
