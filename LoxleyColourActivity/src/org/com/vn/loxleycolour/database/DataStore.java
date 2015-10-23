package org.com.vn.loxleycolour.database;

import java.util.StringTokenizer;

import org.com.vn.loxleycolour._interface.Const;

import android.content.Context;

public class DataStore implements Const {

	public static final String NAME = DataStore.class.getName();
	private static DataStore _shareInstance = new DataStore();
	private static Context mContext;

	private DataStore() {
	}

	public static void init(Context context) {
		if (mContext == null) {
			mContext = context;
		}
	}

	public static DataStore getInstance() {
		return _shareInstance;
	}

	public void saveConfig(String name, String string) {
		mContext.getSharedPreferences(NAME, 0).edit().putString(name, string)
				.commit();
	}

	public void saveConfig(String name, boolean string) {
		mContext.getSharedPreferences(NAME, 0).edit().putBoolean(name, string)
				.commit();
	}

	public boolean getConfig(String name, boolean defalut) {
		return mContext.getSharedPreferences(NAME, 0).getBoolean(name, defalut);
	}

	public String getConfig(String name, String defalut) {
		return mContext.getSharedPreferences(NAME, 0).getString(name, defalut);
	}

	public String getId() {
		String User = getConfig(REMEMBER_USER, "");
		StringTokenizer tokenizer = new StringTokenizer(User, ",");
		return tokenizer.nextToken();
	}
}
