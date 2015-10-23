package org.vnp.storeapp.block.contact;

import org.vnp.storeapp.base.BaseCallBack;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.content.Context;

public abstract class ContactForMeCallBack extends BaseCallBack {

	public ContactForMeCallBack(Context context, String name, String email,
			String message, String telephone) {
		super(context);
		url = StoreAppUtils.URLCONTACTFORME;
		tag = ContactForMeCallBack.class.getName();

		addparams("name", name);
		addparams("email", email);
		addparams("telephone", telephone);
		addparams("message", message);
	}


	@Override
	public Object parseResponse() {
		return null;
	}
}