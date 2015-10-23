package org.vnp.peaps;

import com.google.android.gms.auth.sample.helloauth.GoogleApi;
import com.google.android.gms.common.AccountPicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class PeapsUtils {

	public static boolean isDeviceOnline(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}


}
