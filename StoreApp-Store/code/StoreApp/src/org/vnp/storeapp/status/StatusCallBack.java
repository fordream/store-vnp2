package org.vnp.storeapp.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;

public class StatusCallBack extends BaseCallBack {

	public StatusCallBack(Context context) {
		super(context);
		url = StoreAppUtils.URLSTATUS;
		tag = StatusCallBack.class.getName();
	}

	@Override
	public Object parseResponse() {

		return null;
	}

	public int getStatus() {
		try {
			return new JSONObject(getResponse()).getInt("status");
		} catch (JSONException e) {
			return -1;
		}
	}

	public String getMessage() {
		try {
			return new JSONObject(getResponse()).getString("message");
		} catch (JSONException e) {
			return "";
		}
	}

}