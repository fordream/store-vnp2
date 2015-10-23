package com.example.mytaken.db;

import com.vnp.core.datastore.DataStore;

import android.content.Context;

public class DBBanDuong {
	private Context context;

	public DBBanDuong(Context mContext) {
		context = mContext;
		DataStore.getInstance().init(mContext);
	}

	public boolean isLogin() {
		return DataStore.getInstance().get("login", false);
	}

	public void saveLogin(boolean status) {
		DataStore.getInstance().save("login", status);
	}

}