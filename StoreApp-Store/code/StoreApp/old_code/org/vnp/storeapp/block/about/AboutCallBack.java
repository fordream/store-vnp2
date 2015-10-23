package org.vnp.storeapp.block.about;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;

public class AboutCallBack extends BaseCallBack {

	public AboutCallBack(Context context) {
		super(context);
		url = StoreAppUtils.URLABOUT;
		tag = AboutCallBack.class.getName();
	}


	@Override
	public Object parseResponse() {
		About about = null;
		try {
			about = new About(new JSONObject(getResponse()).getJSONObject("about"));
		} catch (JSONException e) {
		}
		return about;
	}
}