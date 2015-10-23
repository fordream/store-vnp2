package com.vnp.mlook.utils;

import android.content.Context;

public class DBAdapter {

	public static DBAdapter bdAdapter;

	public static DBAdapter getInStance() {
		if (bdAdapter == null)
			bdAdapter = new DBAdapter();

		return bdAdapter;
	}

	public void init(Context context) {
		// /TODO
	}

	public boolean isLogin() {
		return false;
	}
}
