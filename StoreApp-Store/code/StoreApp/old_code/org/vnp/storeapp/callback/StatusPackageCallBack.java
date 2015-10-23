package org.vnp.storeapp.callback;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.model.ItemStatusPackage;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;

public class StatusPackageCallBack extends BaseCallBack {

	public StatusPackageCallBack(Context context) {
		super(context);
		url = StoreAppUtils.URLSTATUSPACKAGE;
		tag = StatusPackageCallBack.class.getName();
	}


	@Override
	public Object parseResponse() {
		ItemStatusPackage callBack = null;
		try {
			callBack = new ItemStatusPackage(new JSONObject(getResponse()));
		} catch (JSONException e) {
		}
		return callBack;
	}
}