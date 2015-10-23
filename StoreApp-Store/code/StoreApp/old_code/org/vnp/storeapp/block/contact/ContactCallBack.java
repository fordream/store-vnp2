package org.vnp.storeapp.block.contact;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;

public class ContactCallBack extends BaseCallBack {

	public ContactCallBack(Context context) {
		super(context);
		url = StoreAppUtils.URLCONTACT;
		tag = ContactCallBack.class.getName();
	}


	@Override
	public Object parseResponse() {
		Contact about = null;
		try {
			about = new Contact(new JSONObject(getResponse()).getJSONObject("contact"));
		} catch (JSONException e) {
		}
		return about;
	}
}