package com.google.android.gms.auth.sample.helloauth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.widget.TextView;

import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class IAbstractGetNameTaskCallBack {
	public static final String ACTION_CALLBACK = "IAbstractGetNameTaskCallBack";
	public static final String CALLBACK_VALUE = "IAbstractGetNameTaskCallBack_KEY";
	public static final int CALLBACK_TYPEEROR = 0;
	public static final int CALLBACK_TYPESUCESS = 1;
	public static final String CALLBACK_TYPE = "CALLBACK_TYPE";
	public static final String CALLBACK_EMAIL = "CALLBACK_EMAIL";

	public void sendBroadCast(int typeCallback, String str) {
		Intent intent = new Intent(ACTION_CALLBACK);
		intent.putExtra(CALLBACK_TYPE, typeCallback);
		intent.putExtra(CALLBACK_VALUE, str);
		intent.putExtra(CALLBACK_EMAIL, mEmail);
		activity.sendBroadcast(intent);
	}

	private Activity activity;
	private TextView mOut;

	public IAbstractGetNameTaskCallBack(Activity activity, TextView mOut) {
		this.activity = activity;
		this.mOut = mOut;
	}

	public void handleException(final UserRecoverableAuthException e) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (e instanceof GooglePlayServicesAvailabilityException) {
					int statusCode = ((GooglePlayServicesAvailabilityException) e)
							.getConnectionStatusCode();
					Dialog dialog = GooglePlayServicesUtil
							.getErrorDialog(
									statusCode,
									activity,
									GoogleApi.REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
					dialog.show();
				} else if (e instanceof UserRecoverableAuthException) {
					Intent intent = ((UserRecoverableAuthException) e)
							.getIntent();
					activity.startActivityForResult(
							intent,
							GoogleApi.REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
				}
			}
		});
	}

	public void handlesuccess(final String name, final String response) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mOut != null) {
					// mOut.setText(name + "!");
				}
				sendBroadCast(CALLBACK_TYPESUCESS, response);
			}
		});
	}

	public void showErrorMessage(final String message) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mOut != null) {
					// mOut.setText(message);
				}
				sendBroadCast(CALLBACK_TYPEEROR, message);
			}
		});
	}

	private String mEmail;

	public void setEmail(String mEmail) {
		this.mEmail = mEmail;
	}
}
