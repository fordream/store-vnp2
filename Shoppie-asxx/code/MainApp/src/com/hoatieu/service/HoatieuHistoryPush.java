package com.hoatieu.service;

import android.content.Context;

import com.vnp.core.datastore.DataStore;

public class HoatieuHistoryPush {
	public static final String TAG = "HoatieuHistoryPush";
	private DataStore dataStore = DataStore.getInstance();

	public HoatieuHistoryPush(Context context) {
		dataStore.init(context);
	}

	public void saveHistory(long time, String message,String stauts) {
		int count = getCount() + 1;
		dataStore.save(TAG + "COUNT", count);
		dataStore.save(TAG + "TIME" + count, time);
		dataStore.save(TAG + "MESSAGE" + count, message);
		dataStore.save(TAG + "STAUTS" + count, stauts);
	}

	public long getTime(int index) {
		return dataStore.get(TAG + "TIME" + (index + 1), 0l);
	}
	public String getStatus(int index) {
		return dataStore.get(TAG + "STAUTS" + (index + 1), "");
	}
	public String getMessage(int index) {
		return dataStore.get(TAG + "MESSAGE" + (index + 1), "");
	}

	public int getCount() {
		return dataStore.get(TAG + "COUNT", 0);
	}
}