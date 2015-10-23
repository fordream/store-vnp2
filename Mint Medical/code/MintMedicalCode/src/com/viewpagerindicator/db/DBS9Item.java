package com.viewpagerindicator.db;

import org.json.JSONException;
import org.json.JSONObject;

import vnp.mr.mintmedical.item.S9Item;
import vnp.mr.mintmedical.item.UserLogin;
import android.content.Context;

public class DBS9Item extends BaseDB {

	public DBS9Item(Context context) {
		super(context);
	}

	@Override
	public Object getData() {
		S9Item item = new S9Item(null);

		try {
			item = new S9Item(new JSONObject(getDataStr()));
		} catch (JSONException e) {
		}
		return item;
	}

	public Object getData(String doctor_id) {
		return null;
	}

	@Override
	public String getKey() {
		DBUserLogin dbUserLogin = new DBUserLogin(context);
		return ((UserLogin) dbUserLogin.getData()).custId + "DBS9Item";
	}
}