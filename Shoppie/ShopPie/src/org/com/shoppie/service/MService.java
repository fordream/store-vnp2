package org.com.shoppie.service;

import com.ict.library.common.CommonLog;
import com.ict.library.database.DataStore;
import com.ict.library.service.RestClient;
import com.ict.library.service.RestClient.RequestMethod;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MService extends Service {
	boolean isStart = false;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (!isStart) {
					isStart = true;
					while (isStart) {
						RestClient restClient = new RestClient(
								"http://shoppie.top50.vn/index.php/api/webservice/merchants");
						try {
							restClient.execute(RequestMethod.GET);
						} catch (Exception e) {
						}

						if (restClient.getResponseCode() == 200) {
							DataStore dataStore = DataStore.getInstance();
							dataStore.save("logo", restClient.getResponse());
						}

						try {
							Thread.sleep(5 * 1000);
						} catch (InterruptedException e) {
						}
					}
				}
			}
		}).start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		isStart = false;
		super.onDestroy();
	}
}
