package org.vnp.peaps.base;

import android.content.Context;

import com.vnp.core.datastore.DataStore;

public class AccountDB {
	private Context context;

	public AccountDB(Context context) {
		this.context = context;
		DataStore.getInstance().init(context);
	}

	public void saveEmail(String email) {
		DataStore.getInstance().save("myemail", email);
	}

	public String getEmail() {
		return DataStore.getInstance().get("myemail", "");
	}

	public void saveMyInfor(String response) {
		DataStore.getInstance().save("MyInfor", response);
	}

	public String getMyInfor() {
		return DataStore.getInstance().get("MyInfor", "");
	}

	public void saveScope(String scope) {
		DataStore.getInstance().save("scope", scope);
	}

	public String getScope() {
		return DataStore.getInstance().get("scope", "");
	}

	public void saveTooken(String token) {
		DataStore.getInstance().save("token", token);
		
	}
	
	public String gettoken() {
		return DataStore.getInstance().get("token", "");
	}
}