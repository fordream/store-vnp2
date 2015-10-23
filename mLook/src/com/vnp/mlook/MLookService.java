package com.vnp.mlook;

import java.io.Serializable;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.ict.library.service.CommonRestClient;
import com.ict.library.service.CommonRestClient.RequestMethod;
import com.vnp.mlook.utils.MLookServiceAction;
import com.vnp.mlook.utils.MLookServiceAction.MLookServiceStatus;

@SuppressLint("NewApi")
public class MLookService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// update call list friend

		// update new Message

		// update new comment

		// update new
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Serializable serializable = intent.getSerializableExtra("action");
		if (serializable instanceof MLookServiceAction.MLookServiceStatus) {
			MLookServiceAction.MLookServiceStatus lookServiceStatus = (MLookServiceAction.MLookServiceStatus) serializable;
			startThread(lookServiceStatus, intent);
		}
		return super.onStartCommand(intent, flags, startId);
	}

	private void startThread(final MLookServiceStatus lookServiceStatus,
			final Intent intent) {
		new Thread() {
			public void run() {
				if (lookServiceStatus == MLookServiceStatus.LOGIN) {
					String user = intent.getExtras().getString("user");
					String password = intent.getExtras().getString("password");
					CommonRestClient commonRestClient = new CommonRestClient(
							getServer(MLookServiceStatus.LOGIN));
					commonRestClient.addParam("user", user);
					commonRestClient.addParam("password", password);

					try {
						commonRestClient.execute(RequestMethod.GET);
					} catch (Exception e) {
					}

					// if (commonRestClient.getResponseCode() == 200) {
					//
					// } else {
					//
					// }

					Intent intent = new Intent("login");
					sendBroadcast(intent);
				}
			}

		}.start();
	}

	private String getServer(MLookServiceStatus login) {

		if (login == MLookServiceStatus.LOGIN) {
			return getString(R.string.server) + "login";
		}
		return "";
	};
}