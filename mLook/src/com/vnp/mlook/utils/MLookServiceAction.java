package com.vnp.mlook.utils;

import com.vnp.core.facebook.Facebook;
import com.vnp.mlook.MLookService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MLookServiceAction {

	public enum MLookServiceStatus {
		LOGIN, //
		LOGIN_FACE, //
		LOGIN_GMAIL, //
		LOGIN_TWITTER, //

	}

	private Context context;

	public MLookServiceAction(Context context) {
		super();
		this.context = context;
	}

	public void callServer(MLookServiceStatus status, Bundle bundle) {

		if (status == MLookServiceStatus.LOGIN_FACE) {
		} else if (status == MLookServiceStatus.LOGIN_GMAIL) {
		} else if (status == MLookServiceStatus.LOGIN_TWITTER) {
		} else {
			Intent intent = new Intent(context, MLookService.class);
			intent.putExtras(bundle);
			intent.putExtra("action", status);
			context.startService(intent);
		}
	}

}
