package org.vnp.storeapp.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.vnp.core.callback.CallBack;
import com.vnp.core.service.RestClient;
import com.vnp.core.service.RestClient.RequestMethod;

public abstract class BaseCallBack extends CallBack {
	public Context mContext;
	public String url;
	public String tag;
	public RestClient restClient;
	private Map<String, String> params = new HashMap<String, String>();
	private Object[] oCallBack;

	public void setCallBack(Object[] oCallBack) {
		this.oCallBack = oCallBack;
	}

	public void addparams(String name, String value) {
		params.put(name, value);
	}

	public BaseCallBack(Context context) {
		mContext = context;
	}

	public void saveResponse(String response) {
		SharedPreferences preferences = mContext.getSharedPreferences(tag, 0);
		preferences.edit().putString(tag, response).commit();
	}

	public String getResponse() {
		if (mContext != null) {
			SharedPreferences preferences = mContext.getSharedPreferences(tag,
					0);
			return preferences.getString(tag, "");
		}
		return "";
	}

	@Override
	public Object execute() {
		restClient = new RestClient(url);
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			restClient.addParam(key, params.get(key));
		}

		try {
			restClient.execute(RequestMethod.GET);
			if (restClient.getResponseCode() == 200) {
				if (canSaveResponse(restClient.getResponse()))
					saveResponse(restClient.getResponse());
			}
		} catch (Exception e) {
		}

		return parseResponse();
	}

	public boolean canSaveResponse(String response) {
		return true;
	}

	public abstract Object parseResponse();

	public void initFirst() {

	}

	@Override
	public void onCallBack(Object arg0) {
		if (oCallBack != null) {
			((Context) oCallBack[0]).sendBroadcast(new Intent(oCallBack[1]
					.toString()));
		}
	}
}