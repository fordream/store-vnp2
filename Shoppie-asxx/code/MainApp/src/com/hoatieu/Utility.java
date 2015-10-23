/****************************************************************************
 *  Utility class
 *  That is class of common functions
 *  
 *  Created by Sky Holdings Vietnam.
 *  Copyright (C) 2013 Sky Holdings Vietnam Co., Ltd. All Rights Reserved.
 ****************************************************************************/
package com.hoatieu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utility {
	public static final int progress_bar_type = 0;

	/**
	 * Check internet conection by context
	 */
	public static boolean checkInternetConnection(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}

		return false;
	}

	@SuppressWarnings("deprecation")
	public static void showDialogAlert(String title, String value, Context activity) {
		if (activity != null) {
			AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
			alertDialog.setTitle(title);
			alertDialog.setMessage(value);
			alertDialog.setButton(activity.getResources().getString(R.string.close), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				}
			});

			alertDialog.show();
		}
	}

	public static boolean isblankOrNull(String str) {

		if (str == null)
			return true;

		return "".equals(str.trim());
	}

	public static boolean canParseToDuble(String longitude) {
		try {
			Double.parseDouble(longitude);
			return true;
		} catch (Exception ex) {

		}
		return false;
	}

	public static double round(double distance, int i) {
		distance = (long) (distance * (10 ^ i));
		return distance / (10 ^ i);
	}

}
